package hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication;
import hu.bme.aut.mobsoft.lab.mobsoft.R;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class RecipesActivity extends AppCompatActivity implements RecipesScreen {
    @Inject
    RecipesPresenter recipesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recipesPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        recipesPresenter.detachScreen();
    }
    @Override
    public void showRecipes(){}

    @Override
    public void navigateToNewRecipe(){}

    @Override
    public void navigateToDetailedRecipe(){}

}
