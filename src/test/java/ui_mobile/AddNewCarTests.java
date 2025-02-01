package ui_mobile;

import config.AppiumConfig;
import dto.CarDto;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static helper.RandomHelper.*;

public class AddNewCarTests extends AppiumConfig
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
    }

    @Test
    public void addNewCarPositive()
    {
        CarDto car = CarDto.builder()
                .serialNumber(generateNumbers(6))
                .manufacture(generateString(6))
                .model(generateNumbers(3))
                .city("Haifa")
                .pricePerDay(233.22)
                .carClass("A")
                .fuel("Gas")
                .year("1975")
                .seats(4)
                .about(generateString(10))
                .build();

        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.goToCreatingPage();
        addNewCarScreen = new AddNewCarScreen(driver);
        addNewCarScreen.addNewCar(car);
        Assert.assertTrue(myCarsScreen.validatePopUpMessage("Car was added!",5));
    }

    @Test
    public void addNewCarPositive_ValidateCarList()
    {
        CarDto car = CarDto.builder()
                .serialNumber(generateNumbers(6))
                .manufacture(generateString(6))
                .model(generateNumbers(3))
                .city("Haifa")
                .pricePerDay(233.22)
                .carClass("A")
                .fuel("Gas")
                .year("1975")
                .seats(4)
                .about(generateString(10))
                .build();

        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.goToCreatingPage();
        addNewCarScreen = new AddNewCarScreen(driver);
        addNewCarScreen.addNewCar(car);
       Assert.assertEquals(myCarsScreen.scrollToLastElementCar(), car.getSerialNumber());
    }
}
