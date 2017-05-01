package hu.bme.aut.mobsoft.lab.mobsoft.interactor.user.events;

import java.util.List;

import hu.bme.aut.mobsoft.lab.mobsoft.model.User;

/**
 * Created by Petra on 2017. 04. 29..
 */

public class IsUserInDBEvent {
    private boolean isInDB;
    private Throwable throwable;

    public IsUserInDBEvent() {
    }


    public boolean isUserInDB() {
        return isInDB;
    }

    public void setIsUserInDB(boolean isInDB) {
        this.isInDB = isInDB;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
