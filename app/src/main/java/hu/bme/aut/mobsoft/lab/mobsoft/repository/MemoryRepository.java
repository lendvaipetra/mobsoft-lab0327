package hu.bme.aut.mobsoft.lab.mobsoft.repository;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;
import hu.bme.aut.mobsoft.lab.mobsoft.model.User;

/**
 * Created by Petra on 2017. 04. 17..
 */

public class MemoryRepository implements Repository {

    public static List<Recipe> recipes;
    public static List<User> users;

    @Override
    public void open(Context context) {
        List<String> ingredients = new ArrayList<String>();
        ingredients.add("ingredient one");
        ingredients.add("ingredient two");
        ingredients.add("ingredient three");
        ingredients.add("ingredient four");
        ingredients.add("ingredient five");

        String uri = "android.resource://hu.bme.aut.mobsoft.lab.mobsoft/drawable/default_recipe";
        Recipe recipe1 = new Recipe(0,"recipe one",  ingredients, "direction one", uri);
        Recipe recipe2 = new Recipe(1,"recipe two", ingredients, "direction two", uri);

        recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);

        User user1 = new User(0,"user one",  "pwd");
        User user2 = new User(1,"user two", "pwd");

        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
    }

    @Override
    public void close() {

    }

    @Override
    public List<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public Recipe getRecipe(int id) {
        return recipes.get(id);
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        recipes.add(recipe.getId(), recipe);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
       for (int i=0; i<recipes.size(); i++) {
            if (recipes.get(i).getId() == (recipe.getId())) {
                recipes.set(i, recipe);
            }
        }
    }

    @Override
    public void removeRecipe(int id) {
        recipes.remove(id);
    }

    @Override
    public boolean isInDB(Recipe recipe) {
        boolean isInDB = false;
        for (Recipe r: recipes) {
            if(r.getId() == recipe.getId()) isInDB = true;
        }
        return isInDB;
    }

    @Override
    public void saveUser(User user) {
        users.add(user);
    }

    @Override
    public boolean isInDB(User user) {
        boolean isInDB = false;
        for (User u: users) {
            if(u.getName().equals(user.getName())){
                isInDB = true;
            }
        }

        return isInDB;
    }
}
