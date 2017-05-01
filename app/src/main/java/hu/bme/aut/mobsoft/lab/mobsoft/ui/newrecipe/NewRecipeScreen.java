package hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public interface NewRecipeScreen {

    void showMessage(String text);

    void navigateBack();

    void fillFields(Recipe recipe);
}
