package hu.bme.aut.mobsoft.lab.mobsoft.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;
import hu.bme.aut.mobsoft.lab.mobsoft.model.User;

/**
 * Created by Petra on 2017. 04. 17..
 */

public class MemoryRepository implements Repository {
    private static final long MINUTE = 60 * 1000;

    public static List<Recipe> recipes;
    public static List<User> users;
    @Override
    public void open(Context context) {
        List<String> ingredients = new ArrayList<String>();
        ingredients.add("ingredient one");
        ingredients.add("ingredient two");

        Recipe recipe1 = new Recipe(0,"recipe one",  ingredients, "direction one", "url one");
        Recipe recipe2 = new Recipe(1,"recipe two", ingredients, "direction two", "url two");

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
    public void saveRecipe(Recipe recipe) {
        recipes.add(recipe);
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
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    @Override
    public boolean isInDB(Recipe recipe) {
        return recipes.contains(recipe);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void saveUser(User user) {
        users.add(user);
    }

    @Override
    public boolean isInDB(User user) {
        return users.contains(user);
    }
}
