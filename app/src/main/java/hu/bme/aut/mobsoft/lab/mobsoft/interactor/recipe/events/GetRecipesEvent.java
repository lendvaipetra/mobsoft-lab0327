package hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events;

import java.util.List;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;

/**
 * Created by Petra on 2017. 04. 30..
 */

public class GetRecipesEvent {

    private List<Recipe> recipes;
    private Throwable throwable;

    public GetRecipesEvent() {
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
