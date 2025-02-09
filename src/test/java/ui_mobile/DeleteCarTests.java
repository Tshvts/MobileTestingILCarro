package ui_mobile;

import config.AppiumConfig;
import config.CarController;
import dto.CarsDto;
import dto.UserDTO;
import io.restassured.response.Response;
import org.testng.Assert;
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
        CarController carController = new CarController();
        carController.login();
        Response responseBeforeTest = carController.getUserCars(carController.tokenDto.getAccessToken());
        int quantityCarBefore = responseBeforeTest.body().as(CarsDto.class).getCars().length;
        myCarsScreen.deleteFirstCar();
        Response responseAfterTest = carController.getUserCars(carController.tokenDto.getAccessToken());
        int quantityCarAfter = responseAfterTest.body().as(CarsDto.class).getCars().length;
        System.out.println(quantityCarBefore + "X" + quantityCarAfter);
        Assert.assertEquals(quantityCarBefore-1,quantityCarAfter);
    }
}
