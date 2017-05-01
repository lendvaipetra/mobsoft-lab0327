package hu.bme.aut.mobsoft.lab.mobsoft.repository;

import android.content.Context;

import java.util.List;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;
import hu.bme.aut.mobsoft.lab.mobsoft.model.User;

/**
 * Created by Petra on 2017. 04. 17..
 */

public interface Repository {
    void open(Context context);

    void close();

    List<Recipe> getRecipes();

    Recipe getRecipe(int id);

    void saveRecipe(Recipe recipe);

    void updateRecipe(Recipe recipe);

    void removeRecipe(int id);

    boolean isInDB(Recipe recipe);

    void saveUser(User user);

    boolean isInDB(User user);
}
