package hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events;

import java.util.List;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;

/**
 * Created by Petra on 2017. 04. 17..
 */

public class GetRecipeEvent {

    private Recipe recipe;
    private Throwable throwable;

    public GetRecipeEvent() {
    }


    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipes(Recipe recipe) {
        this.recipe = recipe;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
