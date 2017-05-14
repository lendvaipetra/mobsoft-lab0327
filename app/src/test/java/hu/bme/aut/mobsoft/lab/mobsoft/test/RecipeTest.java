package hu.bme.aut.mobsoft.lab.mobsoft.test;

import android.net.Uri;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.mobsoft.lab.mobsoft.BuildConfig;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.main.MainScreen;
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
public class RecipeTest {

    private RecipesPresenter recipesPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        recipesPresenter = new RecipesPresenter();
    }

    //Set working directory in run configuration! ../app
    @Test
    public void testGetRecipes() {
        RecipesScreen recipesScreen = mock(RecipesScreen.class);
        recipesPresenter.attachScreen(recipesScreen);
        recipesPresenter.prepareGridViewContent();

        ArgumentCaptor<ArrayList> titlesCaptor = ArgumentCaptor.forClass(ArrayList.class);
        ArgumentCaptor<ArrayList> imageUrisCaptor = ArgumentCaptor.forClass(ArrayList.class);
        verify(recipesScreen, times(1)).createGridView(titlesCaptor.capture(), imageUrisCaptor.capture());

        ArrayList<String> title = titlesCaptor.getValue();
        assertEquals("recipe one", title.get(0));
        assertEquals("recipe two", title.get(1));

        ArrayList<String> imageUris = imageUrisCaptor.getValue();
        assertEquals(Uri.parse("android.resource://hu.bme.aut.mobsoft.lab.mobsoft/drawable/default_recipe"), imageUris.get(0));
        assertEquals(Uri.parse("android.resource://hu.bme.aut.mobsoft.lab.mobsoft/drawable/default_recipe"), imageUris.get(1));
    }

    @After
    public void tearDown() {
        recipesPresenter.detachScreen();
    }
}
