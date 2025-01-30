package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import static helper.RandomHelper.*;

public class RegistrationTests extends AppiumConfig
{
    private String email = "bqh1z40@yahoo.com";
    private String password = "Password123!";

    @BeforeMethod
    public void beforeTest()
    {
        new SplashScreen(driver);
        new SearchScreen(driver).goToRegistrationPage();
    }

    @Test
    public void registrationPositiveTest()
    {
        UserDTO user = UserDTO.builder()
                .firstName(generateString(6))
                .lastName(generateString(9))
                .username(generateEmail(7))
                .password(password)
                .build();
        System.out.println(user.getUsername());
        new RegistrationScreen(driver).typeRegistrationForm(user);
        Assert.assertTrue(new RegistrationScreen(driver).popUpMessageIsExisted("Registration success!"));
    }

    //HOMEWORK 23

    @Test
    public void registrationNegativeTest_EmptyName()
    {
        UserDTO user = UserDTO.builder()
                .firstName("")
                .lastName(generateString(9))
                .username(generateEmail(7))
                .password(password)
                .build();

        new RegistrationScreen(driver).typeRegistrationForm(user);
        Assert.assertTrue(new RegistrationScreen(driver).errorMessageIsExisted("All fields must be filled and agree terms"));
    }

    @Test
    public void registrationNegativeTest_EmptyLastName()
    {
        UserDTO user = UserDTO.builder()
                .firstName(generateString(6))
                .lastName("")
                .username(generateEmail(7))
                .password(password)
                .build();

        new RegistrationScreen(driver).typeRegistrationForm(user);
        Assert.assertTrue(new RegistrationScreen(driver).errorMessageIsExisted("All fields must be filled and agree terms"));
    }

    @Test
    public void registrationNegativeTest_WrongEmail()
    {
        UserDTO user = UserDTO.builder()
                .firstName(generateString(6))
                .lastName(generateString(9))
                .username(generateString(10))
                .password(password)
                .build();

        new RegistrationScreen(driver).typeRegistrationForm(user);
        Assert.assertTrue(new RegistrationScreen(driver).errorMessageIsExisted("{username=must be a well-formed email address}"));
    }

    @Test
    public void registrationNegativeTest_WrongPassword()
    {
        UserDTO user = UserDTO.builder()
                .firstName(generateString(6))
                .lastName(generateString(9))
                .username(generateEmail(7))
                .password(generateNumbers(4))
                .build();

        new RegistrationScreen(driver).typeRegistrationForm(user);
        Assert.assertTrue(new RegistrationScreen(driver).errorMessageIsExisted("{password= At least 8 characters; Must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number; Can contain special characters [@$#^&*!]}"));
    }

    //LESSON 23
    @Test
    public void registrationNegativeTest_dublicateEmail()
    {
        UserDTO user = UserDTO.builder()
                .firstName(generateString(6))
                .lastName(generateString(9))

                .username(email)
                .password(password)
                .build();

        new RegistrationScreen(driver).typeRegistrationForm(user);
        Assert.assertTrue(new ErrorScreen(driver).errorMessageIsExisted("User already exists"));
    }
}
