package hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed;

import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.RecipesInteractor;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.GetRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.RemoveRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.Presenter;

import static hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication.injector;

public class DetailedPresenter extends Presenter<DetailedScreen> {

    @Inject
    RecipesInteractor recipesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public DetailedPresenter(){}

    @Override
    public void attachScreen(DetailedScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
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
            if (screen != null) {
                //screen.showMessage("error");
            }
            Log.e("Networking", "Error reading recipe", event.getThrowable());
        } else {
            if (screen != null) {
                screen.showRecipe(event.getRecipe());
            }
        }
    }

    public void removeRecipe(final int id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.removeRecipe(id);
            }
        });
    }

    public void onEventMainThread(RemoveRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            Log.e("Networking", "Error removing recipe", event.getThrowable());
        } else {
            if (screen != null) {
               screen.navigateToRecipes();
            }
        }
    }
}
