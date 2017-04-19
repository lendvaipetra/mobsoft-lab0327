package hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe;

import java.util.List;
import javax.inject.Inject;
import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.GetRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.RemoveRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.SaveRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;
import hu.bme.aut.mobsoft.lab.mobsoft.repository.Repository;

public class RecipesInteractor {
    @Inject
    Repository repository;
    @Inject
    EventBus bus;

    public RecipesInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void getRecipes() {
        GetRecipeEvent event = new GetRecipeEvent();
        try {
            List<Recipe> recipes = repository.getRecipes();
            event.setRecipes(recipes);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void saveRecipe(Recipe recipe) {

        SaveRecipeEvent event = new SaveRecipeEvent();
        event.setRecipe(recipe);
        try {
            repository.saveRecipe(recipe);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void updateRecipe(Recipe recipe) {
        try {
            repository.updateRecipe(recipe);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeRecipe(Recipe recipe) {
        RemoveRecipeEvent event = new RemoveRecipeEvent();
        event.setRecipe(recipe);
        try {
            repository.removeRecipe(recipe);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
