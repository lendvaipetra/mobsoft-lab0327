package hu.bme.aut.mobsoft.lab.mobsoft;
import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.UIModule;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed.DetailedActivity;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.main.MainActivity;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe.NewRecipeActivity;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes.RecipesActivity;


@Singleton
@Component(modules = {UIModule.class})
public interface MobSoftApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(DetailedActivity detailedActivity);
    void inject(RecipesActivity recipesActivity);
    void inject(NewRecipeActivity newRecipeActivity);
}
