package hu.bme.aut.mobsoft.lab.mobsoft.network.recipe;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RecipeApi {

    /**
     * Recipes.
     * The endpoint returns information about the recipes. The response includes the basic details about each recipe.
     * @return Call<List<Recipe>>
     */

    @GET("recipe")
    Call<List<Recipe>> recipeGet();



    /**
     * Edit Recipe.
     * This request is used for editing a recipe.
     * @param recipe Edited recipe details.
     * @return Call<Void>
     */

    @PUT("recipe")
    Call<Void> recipePut(
            @Body Recipe recipe
    );


    /**
     * New Recipe.
     * This request is used for creating a recipe.
     * @param recipe New recipe details.
     * @return Call<Void>
     */

    @POST("recipe")
    Call<Void> recipePost(
            @Body Recipe recipe
    );


    /**
     * Recipe.
     * The endpoint returns information about a recipe. The response includes the basic details about a recipe indentified by the id.
     * @param id Unique recipe identifier.
     * @return Call<Recipe>
     */

    @GET("recipe/{id}")
    Call<Recipe> recipeIdGet(
            @Path("id") int id
    );


    /**
     * Delete Recipe.
     * This request is used for deleting a recipe.
     * @param id Unique recipe identifier.
     * @return Call<Void>
     */

    @DELETE("recipe/{id}")
    Call<Void> recipeIdDelete(
            @Path("id") int id
    );
}
