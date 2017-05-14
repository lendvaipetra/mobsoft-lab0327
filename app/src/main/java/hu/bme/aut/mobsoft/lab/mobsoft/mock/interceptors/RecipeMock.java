package hu.bme.aut.mobsoft.lab.mobsoft.mock.interceptors;

import android.net.Uri;

import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;
import hu.bme.aut.mobsoft.lab.mobsoft.network.NetworkConfig;
import hu.bme.aut.mobsoft.lab.mobsoft.repository.MemoryRepository;
import hu.bme.aut.mobsoft.lab.mobsoft.utils.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static hu.bme.aut.mobsoft.lab.mobsoft.mock.interceptors.MockHelper.bodyToString;
import static hu.bme.aut.mobsoft.lab.mobsoft.mock.interceptors.MockHelper.makeResponse;

public class RecipeMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString = "";
        int responseCode = 0;
        Headers headers = request.headers();


        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "recipe") && request.method().equals("GET")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            responseString = GsonHelper.getGson().toJson(memoryRepository.getRecipes());
            responseCode = 200;
        }else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "recipe") && request.method().equals("GET")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);

            int id = Integer.parseInt(uri.getLastPathSegment());

            Recipe recipe =  memoryRepository.getRecipe(id);

            if(recipe == null) {
                responseCode = 404;
                responseString = "Recipe not found.";
            }
            else {
                responseCode = 200;
                responseString = GsonHelper.getGson().toJson(recipe);
            }
        }else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "recipe") && request.method().equals("POST")) {
            String recipeString = bodyToString(request);
            Recipe recipe = GsonHelper.getGson().fromJson(recipeString, Recipe.class);
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            try{
                memoryRepository.saveRecipe(recipe);
                responseCode = 201;
            }
            catch(Exception ex){
                responseCode = 500;
                responseString = "Unexpected error happened.";
            }

        }else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "recipe") && request.method().equals("PUT")) {
            String recipeString = bodyToString(request);
            Recipe recipe = GsonHelper.getGson().fromJson(recipeString, Recipe.class);
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            try{
                memoryRepository.updateRecipe(recipe);
                responseCode = 200;
            }
            catch(Exception ex){
                responseCode = 500;
                responseString = "Unexpected error happened.";
            }

        }else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "recipe") && request.method().equals("DELETE")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);

            int id = Integer.parseInt(uri.getLastPathSegment());

            Recipe recipe =  memoryRepository.getRecipe(id);
            if(recipe!= null) {
                memoryRepository.removeRecipe(id);
                responseCode = 200;
            }
            else{
                responseCode = 404;
                responseString = "Recipe already deleted.";
            }
        }
        else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
