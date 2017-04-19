package hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.RecipesInteractor;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.GetRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.RemoveRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.Presenter;

import static hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication.injector;

public class RecipesPresenter extends Presenter<RecipesScreen> {

    @Inject
    RecipesInteractor recipesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public RecipesPresenter() {
    }
    @Override
    public void attachScreen(RecipesScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void showRecipeList() {

    }

    public void getRecipes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.getRecipes();
            }
        });
    }

    public void removeRecipe() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Recipe r = new Recipe();
                recipesInteractor.removeRecipe(r);
            }
        });
    }


    public void onEventMainThread(GetRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                //screen.showMessage("error");
            }
            Log.e("Networking", "Error reading favourites", event.getThrowable());
        } else {
            if (screen != null) {
                for (Recipe r : event.getRecipes()) {
                    //screen.showMessage(t.getName());;
                }
            }
        }
    }

    public void onEventMainThread(RemoveRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                //screen.showMessage("error");
            }
            Log.e("Networking", "Error reading favourites", event.getThrowable());
        } else {
            if (screen != null) {

                    //screen.showMessage(event.getRecipe().getName());;

            }
        }
    }
}
