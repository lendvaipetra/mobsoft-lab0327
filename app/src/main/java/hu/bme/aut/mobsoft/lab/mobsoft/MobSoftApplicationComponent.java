package hu.bme.aut.mobsoft.lab.mobsoft;
import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.InteractorModule;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.RecipesInteractor;
import hu.bme.aut.mobsoft.lab.mobsoft.interactor.recipe.UsersInteractor;
import hu.bme.aut.mobsoft.lab.mobsoft.repository.RepositoryModule;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.UIModule;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed.DetailedActivity;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed.DetailedPresenter;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.main.MainActivity;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.main.MainPresenter;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe.NewRecipeActivity;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe.NewRecipePresenter;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes.RecipesActivity;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes.RecipesPresenter;


@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, InteractorModule.class})
public interface MobSoftApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(DetailedActivity detailedActivity);
    void inject(RecipesActivity recipesActivity);
    void inject(NewRecipeActivity newRecipeActivity);

    void inject(RecipesInteractor recipesInteractor);
    void inject(UsersInteractor usersInteractor);

    void inject(MobSoftApplication mobSoftApplication);

    void inject(MainPresenter mainPresenter);
    void inject(DetailedPresenter detailedPresenter);
    void inject(RecipesPresenter recipesPresenter);
    void inject(NewRecipePresenter newRecipePresenter);
}
