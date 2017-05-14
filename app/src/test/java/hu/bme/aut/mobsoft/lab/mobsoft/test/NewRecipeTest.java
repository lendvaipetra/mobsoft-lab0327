package hu.bme.aut.mobsoft.lab.mobsoft.test;

import android.net.Uri;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import hu.bme.aut.mobsoft.lab.mobsoft.BuildConfig;
import hu.bme.aut.mobsoft.lab.mobsoft.model.Recipe;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed.DetailedPresenter;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.detailed.DetailedScreen;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe.NewRecipePresenter;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.newrecipe.NewRecipeScreen;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes.RecipesPresenter;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes.RecipesScreen;
import hu.bme.aut.mobsoft.lab.mobsoft.utils.RobolectricDaggerTestRunner;

import static hu.bme.aut.mobsoft.lab.mobsoft.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class NewRecipeTest {

    private NewRecipePresenter newRecipePresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        newRecipePresenter = new NewRecipePresenter();
    }

    //Set working directory in run configuration! ../app
    @Test
    public void test() {
        NewRecipeScreen newRecipeScreen = mock(NewRecipeScreen.class);
        newRecipePresenter.attachScreen(newRecipeScreen);

        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("ingredient one");
        int id = 3;

        Recipe recipe = new Recipe(id, "recipe test", ingredients, "dir", "uri");

        newRecipePresenter.addRecipe(recipe);
        ArgumentCaptor<String> newRecipeNameCapture = ArgumentCaptor.forClass(String.class);
        verify(newRecipeScreen, times(1)).showMessage(newRecipeNameCapture.capture() + " successfully saved.");
        verify(newRecipeScreen, times(1)).navigateBack();

        String modifiedName ="recipe test2";
        recipe.setName(modifiedName);
        newRecipePresenter.modifyRecipe(recipe);
        ArgumentCaptor<String> updatedRecipeNameCapture = ArgumentCaptor.forClass(String.class);
        verify(newRecipeScreen, times(2)).showMessage(updatedRecipeNameCapture.capture() + " successfully updated.");
        verify(newRecipeScreen, times(2)).navigateBack();

        newRecipePresenter.getRecipe(id);
        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(newRecipeScreen, times(1)).fillFields(recipeCaptor.capture());

        Recipe capturedRecipe = recipeCaptor.getValue();
        assertEquals(modifiedName, capturedRecipe.getName());
    }

    @After
    public void tearDown() {
        newRecipePresenter.detachScreen();
    }
}
