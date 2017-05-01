package hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe;

import android.net.Uri;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.RecipesInteractor;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.GetRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.SaveRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.UpdateRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.Presenter;

import static hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication.injector;

public class NewRecipePresenter extends Presenter<NewRecipeScreen> {
    @Inject
    RecipesInteractor recipesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;
    public NewRecipePresenter(){}

    @Override
    public void attachScreen(NewRecipeScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    void save(boolean isAdd, int id, String recipeName, List<String> ingredients, String directions, Uri imageUri){
        Recipe recipe = new Recipe(id, recipeName, ingredients, directions, imageUri.toString());

        if(isAdd) addRecipe(recipe);
        else modifyRecipe(recipe);
    }

    public void modifyRecipe(final Recipe recipe) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.updateRecipe(recipe);
            }
        });
    }

    public void onEventMainThread(UpdateRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();

            Log.e("Networking", "Error updating recipe", event.getThrowable());
        } else {
            if (screen != null) {
                screen.showMessage(event.getRecipe().getName() + " successfully updated.");
                screen.navigateBack();
            }
        }
    }

    public void addRecipe(final Recipe recipe) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.saveRecipe(recipe);
            }
        });
    }

    public void onEventMainThread(SaveRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();

            Log.e("Networking", "Error saving recipe", event.getThrowable());
        } else {
            if (screen != null) {
                screen.showMessage(event.getRecipe().getName() + " successfully saved.");
                screen.navigateBack();
            }
        }
    }

    public void getRecipe(final int id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.getRecipe(id);
            }
        });
    }

    public void onEventMainThread(GetRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();

            Log.e("Networking", "Error reading recipe", event.getThrowable());
        } else {
            if (screen != null) {
                screen.fillFields(event.getRecipe());
            }
        }
    }
}
