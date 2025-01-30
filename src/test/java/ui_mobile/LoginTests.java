package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import static helper.RandomHelper.*;

public class LoginTests extends AppiumConfig
{
    LoginScreen loginScreen;
    private String email = "test132@gmail.com";
    private String password = "Ngfg*135";

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
                .username(email)
                .password(password)
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
                .password(password)
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
                .username(email)
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
                                .password(password)
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
                                .username(email)
                                .password("")
                                .build()
                );
        Assert.assertTrue(loginScreen.errorMessageIsExisted("All fields must be filled and agree terms"));
    }

    //LESSON 23

    @Test
    public void loginNegativeTest_EmailWithSpaces()
    {
        loginScreen = new LoginScreen(driver);
        loginScreen.typeFormLogin
                (
                        UserDTO.builder()
                                .username(" " + email + " ")
                                .password(password)
                                .build()
                );
        Assert.assertTrue(new SearchScreen(driver).popUpMessageIsExisted("Login success!"));
    }

    @Test
    public void loginNegativeTest_EmailWOAt()
    {
        loginScreen = new LoginScreen(driver);
        loginScreen.typeFormLogin
                (
                        UserDTO.builder()
                                .username("test132gmail.com")
                                .password(password)
                                .build()
                );
        Assert.assertTrue(new ErrorScreen(driver).errorMessageIsExisted("Login or Password incorrect"));
    }
}
