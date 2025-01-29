package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig
{
    LoginScreen loginScreen;

    @BeforeMethod
    public void beforeLogin()
    {
        new SplashScreen(driver);
        new SearchScreen(driver).goToLoginPage();

    }

    @Test
    public void loginPositiveTests()
    {
        loginScreen = new LoginScreen(driver);
        loginScreen.typeFormLogin
                (
                UserDTO.builder()
                .username("test132@gmail.com")
                .password("Ngfg*135")
                .build()
                );
        Assert.assertTrue(new SearchScreen(driver).popUpMessageIsExisted("Login success!"));
    }
}
