package screens;

import dto.SearchDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

public class SearchScreen extends BaseScreen
{

    public SearchScreen(AppiumDriver<AndroidElement> driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//android.widget.ImageView[@content-desc= 'More options']")
    AndroidElement btnMoreOptions;

    @FindBy(xpath = "//*[@text = 'Registration']")
    AndroidElement btnRegistration;

    @FindBy(xpath = "//*[@text = 'Login']")
    AndroidElement btnLogin;


    @FindBy(xpath = "//*[@text='Login success!']")
    AndroidElement popUpMessage;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")
    AndroidElement btnMyCars;

    @FindBy(id = "com.telran.ilcarro:id/editLocation")
    AndroidElement inputLocation;

    @FindBy(id = "com.telran.ilcarro:id/editFrom")
    AndroidElement inputFrom;

    @FindBy(id = "com.telran.ilcarro:id/editTo")
    AndroidElement inputTo;

    @FindBy(id = "com.telran.ilcarro:id/searchBtn")
    AndroidElement btnSearchYalla;

    public void goToRegistrationPage()
    {
        clickWait(btnMoreOptions,5);
        clickWait(btnRegistration,5);
    }

    public void goToLoginPage()
    {
        clickWait(btnMoreOptions,5);
        clickWait(btnLogin,5);
    }

    public void goToMyCarsPage()
    {
        clickWait(btnMoreOptions,5);
        clickWait(btnMyCars,5);
    }

    public boolean popUpMessageIsExisted(String text)
    {
        return textElementIsPresent(popUpMessage, text, 5);
    }

    public void searchCarWithCalendar(SearchDto searchDto)
    {
        inputLocation.sendKeys(searchDto.getCity());
//        inputFrom.click();
//        inputFrom.sendKeys(searchDto.getStartDate());
//        inputFrom.sendKeys(Keys.ENTER);
//        inputTo.click();
//        inputTo.sendKeys(searchDto.getEndDate());
//        inputTo.sendKeys(Keys.ENTER);
        clickWait(btnSearchYalla,5);
    }
}
