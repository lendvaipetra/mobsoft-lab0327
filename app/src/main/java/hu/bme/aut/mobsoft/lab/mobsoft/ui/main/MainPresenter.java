package hu.bme.aut.mobsoft.lab.mobsoft.ui.main;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.user.UsersInteractor;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.user.events.IsUserInDBEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.user.events.SaveUserEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.model.User;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.Presenter;

import static hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication.injector;

public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    UsersInteractor usersInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    int id = 2;
    User newUser = null;

    public MainPresenter() {
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }


    public void login(String username, String password) {
        newUser = new User(++id, username, password);
        isUserInDB(newUser);
    }

    public void continueWithoutLogin() {
        if(screen!= null) screen.navigateToRecipes();
    }

    public void addUser(final User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                usersInteractor.saveUser(user);
            }
        });
    }

    public void onEventMainThread(SaveUserEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
            Log.e("Networking", "Error saving user", event.getThrowable());
        } else {
            if (screen != null) {
                continueWithoutLogin();
            }
        }
    }

    public void isUserInDB(final User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                usersInteractor.isUserInDB(user);
            }
        });
    }


    public void onEventMainThread(IsUserInDBEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
            Log.e("Networking", "Error in isUserInDB", event.getThrowable());
        } else {
            if (screen != null) {
                boolean isUserInDB= event.isUserInDB();
                if(!isUserInDB) addUser(newUser);
                else continueWithoutLogin();
            }
        }
    }
}