package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import static helper.RandomHelper.*;

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
    public void loginPositiveTest()
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

    //HOMEWORK 23

    @Test
    public void loginNegativeTest_WrongEmail()
    {
        loginScreen = new LoginScreen(driver);
        loginScreen.typeFormLogin
                (
                UserDTO.builder()
                .username(generateString(11))
                .password("Ngfg*135")
                .build()
                );
        Assert.assertTrue(loginScreen.errorMessageIsExisted("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_WrongPassword()
    {
        loginScreen = new LoginScreen(driver);
        loginScreen.typeFormLogin
                (
                UserDTO.builder()
                .username("test132@gmail.com")
                .password("135")
                .build()
                );
       Assert.assertTrue(loginScreen.errorMessageIsExisted("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_EmptyEmail()
    {
        loginScreen = new LoginScreen(driver);
        loginScreen.typeFormLogin
                (
                        UserDTO.builder()
                                .username("")
                                .password("Ngfg*135")
                                .build()
                );
        Assert.assertTrue(loginScreen.errorMessageIsExisted("All fields must be filled and agree terms"));
    }


    @Test
    public void loginNegativeTest_EmptyPassword()
    {
        loginScreen = new LoginScreen(driver);
        loginScreen.typeFormLogin
                (
                        UserDTO.builder()
                                .username("test132@gmail.com")
                                .password("")
                                .build()
                );
        Assert.assertTrue(loginScreen.errorMessageIsExisted("All fields must be filled and agree terms"));
    }
}
