package hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.GetUserEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.SaveUserEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.model.User;
import hu.bme.aut.mobsoft.lab.mobsoft.repository.Repository;

/**
 * Created by Petra on 2017. 04. 17..
 */

public class UsersInteractor {
    @Inject
    Repository repository;
    @Inject
    EventBus bus;

    public UsersInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void getUsers() {
        GetUserEvent event = new GetUserEvent();
        try {
            List<User> users = repository.getUsers();
            event.setUsers(users);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void saveUser(User user) {
        SaveUserEvent event = new SaveUserEvent();
        event.setUser(user);
        try {
            repository.saveUser(user);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
