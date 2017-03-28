package hu.bme.aut.mobsoft.lab.mobsoft.ui;

import android.content.Context;

import javax.inject.Singleton;

import hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed.DetailedPresenter;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.main.MainPresenter;
import android.content.Context;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe.NewRecipePresenter;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes.RecipesPresenter;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public DetailedPresenter provideDetailedPresenter() {
        return new DetailedPresenter();
    }

    @Provides
    @Singleton
    public NewRecipePresenter provideNewRecipePresenter() {
        return new NewRecipePresenter();
    }

    @Provides
    @Singleton
    public RecipesPresenter provideRecipesPresenter() { return new RecipesPresenter(); }

}
