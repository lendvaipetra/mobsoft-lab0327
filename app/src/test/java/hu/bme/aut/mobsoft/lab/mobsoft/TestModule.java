package hu.bme.aut.mobsoft.lab.mobsoft;

import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

import hu.bme.aut.mobsoft.lab.mobsoft.ui.UIModule;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed.DetailedPresenter;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.main.MainPresenter;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe.NewRecipePresenter;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes.RecipesPresenter;
import hu.bme.aut.mobsoft.lab.mobsoft.utils.UIExecutor;

@Module
public class TestModule {

    private final UIModule UIModule;

    public TestModule(Context context) {
        this.UIModule = new UIModule(context);
    }

    @Provides
    public Context provideContext() {
        return UIModule.provideContext();
    }


    @Provides
    public DetailedPresenter provideDetailedPresenter() {
        return UIModule.provideDetailedPresenter();
    }

    @Provides
    public MainPresenter provideMainPresenter() {
        return UIModule.provideMainPresenter();
    }

    @Provides
    public NewRecipePresenter provideNewRecipePresenter() {
        return UIModule.provideNewRecipePresenter();
    }

    @Provides
    public RecipesPresenter provideRecipesPresenter() {
        return UIModule.provideRecipesPresenter();
    }


    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public Executor provideNetworkExecutor() {
        return new UIExecutor();
    }
}
