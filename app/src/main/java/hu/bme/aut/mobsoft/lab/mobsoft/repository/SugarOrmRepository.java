package hu.bme.aut.mobsoft.lab.mobsoft.repository;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;
import hu.bme.aut.mobsoft.lab.mobsoft.model.User;

public class SugarOrmRepository implements Repository{
    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<Recipe> getRecipes() {
        return SugarRecord.listAll(Recipe.class);
    }

    @Override
    public Recipe getRecipe(int id) {
        return  SugarRecord.findById(Recipe.class, id);
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        SugarRecord.saveInTx(recipe);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        List<Recipe> recipes = getRecipes();

        for (Recipe r : recipes) {
            if (r.getId() == recipe.getId()) {
                SugarRecord.saveInTx(recipe);            }

        }
    }

    @Override
    public void removeRecipe(int id) {
        Recipe recipe = getRecipe(id);
        SugarRecord.deleteInTx(recipe);
    }

    @Override
    public boolean isInDB(Recipe recipe) {
        return SugarRecord.findById(Recipe.class, recipe.getId()) != null;
    }

    @Override
    public void saveUser(User user) {
        SugarRecord.saveInTx(user);
    }

    @Override
    public boolean isInDB(User user) {
        return SugarRecord.findById(User.class, user.getId()) != null;
    }
}
