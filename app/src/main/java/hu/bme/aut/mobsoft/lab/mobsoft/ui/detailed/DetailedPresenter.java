package hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed;

import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.RecipesInteractor;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.GetRecipeEvent;
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

    void showRecipe(){}

    public void getRecipes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.getRecipes();
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
                for(Recipe r : event.getRecipes()){
                    //screen.showMessage(t.getName());;
                }
            }
        }
    }
}
