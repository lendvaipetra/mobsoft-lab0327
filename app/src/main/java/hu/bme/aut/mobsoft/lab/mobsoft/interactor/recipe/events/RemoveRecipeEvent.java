package hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;

public class RemoveRecipeEvent {
    private Recipe recipe;
    private Throwable throwable;

    public RemoveRecipeEvent() {
    }

    public RemoveRecipeEvent(Recipe recipe, Throwable throwable) {
        this.recipe = recipe;
        this.throwable = throwable;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
