package hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.RecipesInteractor;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.GetRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.SaveRecipeEvent;
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

    void save(){}

    public void addRecipe() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Recipe r = new Recipe();
                recipesInteractor.saveRecipe(r);
            }
        });
    }

    public void modifyRecipe() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Recipe r = new Recipe();
                recipesInteractor.updateRecipe(r);
            }
        });
    }

    public void onEventMainThread(SaveRecipeEvent event) {
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
