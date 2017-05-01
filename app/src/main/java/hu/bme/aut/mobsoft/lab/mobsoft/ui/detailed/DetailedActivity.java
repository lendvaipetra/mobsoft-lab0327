package hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication;
import hu.bme.aut.mobsoft.lab.mobsoft.R;
import hu.bme.aut.mobsoft.lab.mobsoft.Utility;
import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe.NewRecipeActivity;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes.RecipesActivity;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class DetailedActivity extends AppCompatActivity implements DetailedScreen {

    @Inject
    DetailedPresenter detailedPresenter;
    boolean login = false;
    int id = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        MobSoftApplication.injector.inject(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            login = extras.getBoolean("Login");
            id = extras.getInt("RecipeId");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(!login) return false;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detailed_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.modifyRecipe:
                navigateToModifyRecipe();
                return true;
            case R.id.removeRecipe:
                detailedPresenter.removeRecipe(id);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void navigateToModifyRecipe(){
        Intent intent = new Intent(getApplicationContext(), NewRecipeActivity.class);
        intent.putExtra("New", false);
        intent.putExtra("RecipeId", id);
        startActivity(intent);
    }

    @Override
    public void navigateToRecipes(){
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        detailedPresenter.attachScreen(this);
        detailedPresenter.getRecipe(id);
    }

    @Override
    protected void onStop() {
        super.onStop();
        detailedPresenter.detachScreen();
    }

    @Override
    public void showRecipe(Recipe recipe){
        TextView recipeNameTextView  = (TextView)findViewById(R.id.recipeNameTextView);
        ListView ingredientsListView  = (ListView)findViewById(R.id.recipeIngredientsListView);
        TextView directionsTextView  = (TextView)findViewById(R.id.recipeDetailsContentTextView);
        ImageView recipeImage = (ImageView)findViewById(R.id.recipeImage);


        recipeNameTextView.setText(recipe.getName());
        directionsTextView.setText(recipe.getDirections());
        recipeImage.setImageURI(Uri.parse(recipe.getImage()));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, recipe.getIngredients());

        ingredientsListView.setAdapter(adapter);
        Utility.setListViewHeightBasedOnChildren(ingredientsListView);
    }


}
