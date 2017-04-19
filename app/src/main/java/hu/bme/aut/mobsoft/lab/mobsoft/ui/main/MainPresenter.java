package hu.bme.aut.mobsoft.lab.mobsoft.ui.main;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.UsersInteractor;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.GetUserEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.SaveUserEvent;
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


    public void login() {

    }

    public void continueWithoutLogin() {

    }

    public void getUsers() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                usersInteractor.getUsers();
            }
        });
    }


    public void onEventMainThread(GetUserEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
            Log.e("Networking", "Error reading favourites", event.getThrowable());
        } else {
            if (screen != null) {
                for(User u : event.getUsers()){
                    screen.showMessage(u.getName());;
                }
            }
        }
    }

    public void addUser() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                User u = new User();
                u.setName("addedUser");
                usersInteractor.saveUser(u);
            }
        });
    }

    public void onEventMainThread(SaveUserEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                //screen.showMessage("error");
            }
            Log.e("Networking", "Error reading favourites", event.getThrowable());
        } else {
            if (screen != null) {
                screen.showMessage(event.getUser().getName());;

            }
        }
    }
}