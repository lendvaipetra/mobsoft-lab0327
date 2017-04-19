package hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events;

import java.util.List;

import hu.bme.aut.mobsoft.lab.mobsoft.model.User;


/**
 * Created by Petra on 2017. 04. 17..
 */

public class GetUserEvent {
    private List<User> users;
    private Throwable throwable;

    public GetUserEvent() {
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
