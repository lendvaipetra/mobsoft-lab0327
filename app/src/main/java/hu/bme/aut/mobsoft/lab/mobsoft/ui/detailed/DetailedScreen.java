package hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public interface DetailedScreen {
    void navigateToModifyRecipe();
    void navigateToRecipes();
    void showRecipe(Recipe recipe);
}
