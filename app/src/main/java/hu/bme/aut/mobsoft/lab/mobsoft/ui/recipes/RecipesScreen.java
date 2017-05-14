package hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes;

import android.net.Uri;

import java.util.ArrayList;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public interface RecipesScreen {
    void navigateToNewRecipe();
    void navigateToDetailedRecipe(int id);
    void createGridView(ArrayList<String> titles, ArrayList<Uri> imageUris);
}
