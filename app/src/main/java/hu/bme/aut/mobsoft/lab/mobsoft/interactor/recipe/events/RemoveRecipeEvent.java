package hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;

public class RemoveRecipeEvent {
    private int recipeId;
    private Throwable throwable;

    public RemoveRecipeEvent() {
    }

    public RemoveRecipeEvent(int recipeId, Throwable throwable) {
        this.recipeId = recipeId;
        this.throwable = throwable;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int id) {
        this.recipeId = recipeId;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
