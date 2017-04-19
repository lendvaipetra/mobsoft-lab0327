package hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events;


import hu.bme.aut.mobsoft.lab.mobsoft.model.User;

public class SaveUserEvent {
    private User user;
    private Throwable throwable;

    public SaveUserEvent() {
    }

    public SaveUserEvent(User user, Throwable throwable) {
        this.user = user;
        this.throwable = throwable;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}
