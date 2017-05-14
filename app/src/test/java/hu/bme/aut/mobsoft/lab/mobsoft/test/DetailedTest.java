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
public class DetailedTest {

    private DetailedPresenter detailedPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        detailedPresenter = new DetailedPresenter();
    }

    //Set working directory in run configuration! ../app
    @Test
    public void test() {
        DetailedScreen detailedScreen = mock(DetailedScreen.class);
        detailedPresenter.attachScreen(detailedScreen);

        detailedPresenter.removeRecipe(2);
        verify(detailedScreen, times(1)).navigateToRecipes();

        detailedPresenter.getRecipe(2);
        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(detailedScreen, times(1)).showRecipe(recipeCaptor.capture());
        Recipe capturedRecipe = recipeCaptor.getValue();
        assertEquals(null, capturedRecipe);
    }

    @After
    public void tearDown() {
        detailedPresenter.detachScreen();
    }
}
