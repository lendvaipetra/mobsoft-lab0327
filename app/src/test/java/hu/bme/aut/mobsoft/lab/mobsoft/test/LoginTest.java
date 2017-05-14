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
import hu.bme.aut.mobsoft.lab.mobsoft.model.User;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.main.MainPresenter;
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
public class LoginTest {

    private MainPresenter mainPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        mainPresenter = new MainPresenter();
    }

    //Set working directory in run configuration! ../app
    @Test
    public void test() {
        MainScreen mainScreen = mock(MainScreen.class);
        mainPresenter.attachScreen(mainScreen);
        User user = new User(0, "username", "password");

        mainPresenter.login(user.getName(), user.getPassword());
        verify(mainScreen, times(1)).navigateToRecipes();

        mainPresenter.login(user.getName(), user.getPassword());
        verify(mainScreen, times(2)).navigateToRecipes();
    }

    @After
    public void tearDown() {
        mainPresenter.detachScreen();
    }
}
