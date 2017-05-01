package hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication;
import hu.bme.aut.mobsoft.lab.mobsoft.R;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed.DetailedActivity;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe.NewRecipeActivity;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class RecipesActivity extends AppCompatActivity implements RecipesScreen {
    @Inject
    RecipesPresenter recipesPresenter;
    boolean login;

    GridView recipesGrid;
    RecipeGridViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        MobSoftApplication.injector.inject(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            login = extras.getBoolean("Login");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(!login) return false;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipes_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addRecipe:
                navigateToNewRecipe();
                return true;
            case R.id.removeRecipe:
                //recipesPresenter.removeRecipe();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void navigateToNewRecipe(){
        Intent intent = new Intent(getApplicationContext(), NewRecipeActivity.class);
        intent.putExtra("New", true);
        intent.putExtra("RecipeId", adapter.getCount());
        startActivity(intent);
    }

    @Override
    public void navigateToDetailedRecipe(int id){
        Intent intent = new Intent(getApplicationContext(), DetailedActivity.class);
        intent.putExtra("Login", login);
        intent.putExtra("RecipeId", id);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recipesPresenter.attachScreen(this);
        recipesPresenter.prepareGridViewContent();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recipesPresenter.detachScreen();
    }
    @Override
    public void showRecipes(){}

    @Override
    public void createGridView(ArrayList<String> titles, ArrayList<Uri> imageUris) {
        recipesGrid = (GridView) findViewById(R.id.recipesGrid);
        adapter = new RecipeGridViewAdapter(this, titles, imageUris);
        recipesGrid.setAdapter(adapter);

        recipesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                navigateToDetailedRecipe(position);
            }
        });
    }

    private class RecipeGridViewAdapter extends BaseAdapter {
        private Activity activity;
        private List<String> titles = new ArrayList<>();
        private List<Uri> imageUris = new ArrayList<>();

        public RecipeGridViewAdapter(Activity activity, ArrayList<String> titles, ArrayList<Uri> imageUris) {
            this.activity = activity;
            this.titles = titles;
            this.imageUris = imageUris;
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public String getItem(int position) {
            return titles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // create a new ImageView for each item referenced by the Adapter
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = activity.getLayoutInflater();

            if(convertView==null)
            {
                convertView = inflater.inflate(R.layout.gridview_cell, null);
            }

            TextView titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            titleTextView.setText(titles.get(position));
            imageView.setImageURI(imageUris.get(position));

            return convertView;
        }

    }
}
