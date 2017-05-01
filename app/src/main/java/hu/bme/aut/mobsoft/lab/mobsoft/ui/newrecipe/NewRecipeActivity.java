package hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication;
import hu.bme.aut.mobsoft.lab.mobsoft.R;
import hu.bme.aut.mobsoft.lab.mobsoft.Utility;
import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class NewRecipeActivity extends AppCompatActivity implements NewRecipeScreen {
    @Inject
    NewRecipePresenter newRecipePresenter;
    EditText recipeNameEditText;
    EditText directionsEditText;
    ImageView recipeImage;
    ListView ingredientsListView;
    ArrayAdapter<String> ingredientsListAdapter;
    List<String> ingredients = new ArrayList<String>();
    boolean newPicture = false;
    int PICK_IMAGE_REQUEST = 1;

    Uri recipeImageUri = null;
    boolean isNew = true;
    int id = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newrecipe);

        MobSoftApplication.injector.inject(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            isNew = extras.getBoolean("New");
            id = extras.getInt("RecipeId");
        }

        recipeNameEditText  = (EditText)findViewById(R.id.recipeNameEditText);
        directionsEditText  = (EditText)findViewById(R.id.recipeDirectionsEditText);
        recipeImage = (ImageView)findViewById(R.id.recipeImage);
        ingredientsListView = (ListView) findViewById(R.id.recipeIngredientsListView);
        recipeImageUri = Uri.parse(getResources().getString(R.string.defaultImageUri));

        ingredientsListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, ingredients);

        ingredientsListView.setAdapter(ingredientsListAdapter);

        if(!isNew) {
            ingredientsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int pos, long id) {
                    ingredientsListAdapter.remove(ingredients.get(pos));
                    Utility.setListViewHeightBasedOnChildren(ingredientsListView);
                    return true;
                }
            });
        }

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String recipeName = recipeNameEditText.getText().toString();
                String directions = directionsEditText.getText().toString();

                newRecipePresenter.save(isNew, id, recipeName, ingredients, directions, recipeImageUri);
            }
        });

        Button addIngredientButton = (Button) findViewById(R.id.addIngredientButton);
        addIngredientButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                EditText ingredientEditText  = (EditText)findViewById(R.id.recipeIngredientsEditText);
                String ingredient = ingredientEditText.getText().toString();
                ingredientsListAdapter.add(ingredient);

                Utility.setListViewHeightBasedOnChildren(ingredientsListView);
            }
        });

        Button addImageButton = (Button) findViewById(R.id.addImageButton);
        addImageButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
           newPicture = true;
           recipeImageUri = data.getData();
           recipeImage.setImageURI(recipeImageUri);
       }
    }

    @Override
    protected void onStart() {
        super.onStart();
        newRecipePresenter.attachScreen(this);

        if(!isNew){
            newRecipePresenter.getRecipe(id);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        newRecipePresenter.detachScreen();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateBack() {
        finish();
    }

    @Override
    public void fillFields(Recipe recipe) {
        recipeNameEditText.setText(recipe.getName());
        directionsEditText.setText(recipe.getDirections());
        if(!newPicture) {
            recipeImageUri = Uri.parse(recipe.getImage());
            recipeImage.setImageURI(recipeImageUri);

        }

        for (String ingredient: recipe.getIngredients()) {
            ingredientsListAdapter.add(ingredient);
        }

        Utility.setListViewHeightBasedOnChildren(ingredientsListView);
    }
}
