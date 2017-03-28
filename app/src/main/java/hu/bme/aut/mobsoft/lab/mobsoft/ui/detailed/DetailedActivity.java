package hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication;
import hu.bme.aut.mobsoft.lab.mobsoft.R;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class DetailedActivity extends AppCompatActivity implements DetailedScreen {

    @Inject
    DetailedPresenter detailedPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        MobSoftApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        detailedPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        detailedPresenter.detachScreen();
    }
    @Override
    public void navigateToModifyRecipe() {

    }
    @Override
    public void navigateToRecipes() {

    }
    @Override
    public void showRecipe(){}
}
