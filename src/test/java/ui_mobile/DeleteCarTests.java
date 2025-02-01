package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

public class DeleteCarTests extends AppiumConfig
{
    LoginScreen loginScreen;
    private String email = "test132@gmail.com";
    private String password = "Ngfg*135";
    SearchScreen searchScreen;
    MyCarsScreen myCarsScreen;
    AddNewCarScreen addNewCarScreen;

    @BeforeMethod
    public void preconditions()
    {
        new SplashScreen(driver);
        searchScreen = new SearchScreen(driver);
        searchScreen.goToLoginPage();
        loginScreen = new LoginScreen(driver);
        loginScreen.typeFormLogin
                (
                        UserDTO.builder()
                                .username(email)
                                .password(password)
                                .build()
                );
        searchScreen.goToMyCarsPage();
        myCarsScreen = new MyCarsScreen(driver);
        addNewCarScreen = new AddNewCarScreen(driver);
    }

    @Test
    public void deleteFirstCarPositiveTest()
    {
        myCarsScreen.deleteFirstCar();
    }
}
