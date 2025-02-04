package ui_mobile;

import config.AppiumConfig;
import dto.SearchDto;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

public class SearchCarTests extends AppiumConfig
{
    LoginScreen loginScreen;
    private String email = "test132@gmail.com";
    private String password = "Ngfg*135";
    SearchScreen searchScreen;
    ErrorScreen errorScreen;
    SearchResultScreen searchResultScreen;

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
        searchResultScreen = new SearchResultScreen(driver);
        errorScreen = new ErrorScreen(driver);

    }

    @Test
    public void searchCarWOCalendarPositive()
    {
        SearchDto searchDto = SearchDto.builder()
                .city("Rehovot")
                .startDate("12/03/25")
                .endDate("31/03/25")
                .build();
        searchScreen.searchCarWOCalendar(searchDto);
        searchScreen.clickBtnYalla();
        Assert.assertTrue(searchResultScreen.validateMovingToSearchResult("Search result",5));
    }

    @Test
    public void searchCarWithCalendarPositive()
    {
        SearchDto searchDto = SearchDto.builder()
                .city("Rehovot")
                .startDate("")
                .endDate("")
                .build();
        searchScreen.searchCarWithCalendar(searchDto);
        searchScreen.clickBtnYalla();
        Assert.assertTrue(searchResultScreen.validateMovingToSearchResult("Search result",5));
    }

    @Test
    public void searchCarPositiveWithCalendar_Lesson25()
    {
        searchScreen.findCarWithCalendar("Rehovot",
                "10 March 2026", "12 April 2027");
        Assert.assertTrue(searchResultScreen.validateMovingToSearchResult("Search result",5));
    }

    @Test
    public void searchCarNegative_emptyCity()
    {
        searchScreen.clickBtnYalla();
        Assert.assertTrue(errorScreen.errorMessageIsExisted("City can't be empty"));
    }

    @Test
    public void searchCarNegative_noNameDates()//a strange error message, so I didn't know how to call
    {
        SearchDto searchDto = SearchDto.builder()
                .city("Rehovot")
                .startDate("")
                .endDate("")
                .build();
        searchScreen.searchWithInputFrom(searchDto);
        searchScreen.clickBtnYalla();
        Assert.assertTrue(errorScreen.errorMessageIsExisted("To date can't be before from date"));
    }
}
