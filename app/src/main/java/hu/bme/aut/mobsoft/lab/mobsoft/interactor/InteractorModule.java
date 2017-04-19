package hu.bme.aut.mobsoft.lab.mobsoft.interactor;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.RecipesInteractor;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.UsersInteractor;

@Module
public class InteractorModule {

    @Provides
    public RecipesInteractor provideRecipes() {
        return new RecipesInteractor();
    }

    @Provides
    public UsersInteractor provideUsers() {
        return new UsersInteractor();
    }
}