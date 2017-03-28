package hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication;
import hu.bme.aut.mobsoft.lab.mobsoft.R;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class NewRecipeActivity extends AppCompatActivity implements NewRecipeScreen {
    @Inject
    NewRecipePresenter newRecipePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newrecipe);

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        newRecipePresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        newRecipePresenter.detachScreen();
    }

    @Override
    public void navigateToRecipes() {

    }
}
