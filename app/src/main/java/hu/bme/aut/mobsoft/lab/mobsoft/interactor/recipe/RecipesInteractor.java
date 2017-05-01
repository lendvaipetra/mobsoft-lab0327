package hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe;

import java.util.List;
import javax.inject.Inject;
import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.GetRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.GetRecipesEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.RemoveRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.SaveRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.UpdateRecipeEvent;
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

    public void getRecipe(int id) {
        GetRecipeEvent event = new GetRecipeEvent();
        try {
            Recipe recipe = repository.getRecipe(id);
            event.setRecipes(recipe);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void getRecipes() {
        GetRecipesEvent event = new GetRecipesEvent();
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

        UpdateRecipeEvent event = new UpdateRecipeEvent();
        event.setRecipe(recipe);
        try {
            repository.updateRecipe(recipe);
            bus.post(event);
        } catch (Exception e) {
            e.printStackTrace();
            bus.post(event);
        }
    }

    public void removeRecipe(int id) {
        RemoveRecipeEvent event = new RemoveRecipeEvent();
        event.setRecipeId(id);
        try {
            repository.removeRecipe(id);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
