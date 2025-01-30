package ui_mobile;

import config.AppiumConfig;
import dto.SearchDto;
import dto.UserDTO;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

public class SearchCarTests extends AppiumConfig
{
    LoginScreen loginScreen;
    private String email = "test132@gmail.com";
    private String password = "Ngfg*135";
    SearchScreen searchScreen;
    MyCarsScreen myCarsScreen;
    AddNewCarScreen addNewCarScreen;

    @BeforeMethod
    public void preConditions()
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
    }

    @Test
    public void searchCarPositive()
    {
        SearchDto searchDto = SearchDto.builder()
                .city("Haifa")
                .startDate("12/03/25")
                .endDate("31/03/25")
                .build();
        searchScreen = new SearchScreen(driver);
        searchScreen.searchCarWithCalendar(searchDto);
    }
}
