package hu.bme.aut.mobsoft.lab.mobsoft.model;

import com.orm.dsl.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Petra on 2017. 04. 17..
 */
@Table
public class Recipe {

    private int id = -1;
    private String name;
    private List<String> ingredients = new ArrayList<String>();
    private String directions;
    private String image;

    public Recipe(){}

    public Recipe(int id, String name, List<String> ingredients, String directions, String image){
        this.id = id;
        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
        this.directions = directions;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
    //Miért kell ide a new ArrayList
    public void setIngredients(List<String> ingredients) {
        this.ingredients = new ArrayList<>(ingredients);
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
