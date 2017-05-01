package hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes;

import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.RecipesInteractor;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.GetRecipeEvent;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.events.GetRecipesEvent;
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

    public void prepareGridViewContent() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.getRecipes();
            }
        });
    }

    public void onEventMainThread(GetRecipesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();

            Log.e("Networking", "Error reading recipes", event.getThrowable());
        } else {
            if (screen != null) {
                ArrayList<String> titles = new ArrayList<>();
                ArrayList<Uri> imageUris = new ArrayList<>();
                for (Recipe r : event.getRecipes()) {
                    titles.add(r.getName());
                    imageUris.add(Uri.parse(r.getImage()));
                }
                screen.createGridView(titles, imageUris);
            }
        }
    }
}
