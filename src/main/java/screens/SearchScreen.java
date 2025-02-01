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

    @FindBy(id = "android:id/next")
    AndroidElement btnNextMonth;

    @FindBy(xpath = "//*[@text='1']")
    AndroidElement iconOfTheFirstDay;

    @FindBy(id = "android:id/button1")
    AndroidElement btnOk;

    @FindBy(id = "com.telran.ilcarro:id/editTo")
    AndroidElement inputTo;

    @FindBy(xpath = "//*[@text='30']")
    AndroidElement iconOfTheLastDay;

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

    public void searchCarWOCalendar(SearchDto searchDto)
    {
        inputLocation.sendKeys(searchDto.getCity());
    }

    public void searchCarWithCalendar(SearchDto searchDto)
    {
        inputLocation.sendKeys(searchDto.getCity());
        clickWait(inputFrom,5);
        clickWait(iconOfTheFirstDay,5);
        btnOk.click();
        clickWait(inputTo,5);
        clickWait(btnNextMonth,5);
        clickWait(iconOfTheLastDay,5);
        btnOk.click();
    }

    public void clickBtnYalla()
    {
        clickWait(btnSearchYalla,5);
    }

    public void searchWithInputFrom(SearchDto searchDto)
    {
        inputLocation.sendKeys(searchDto.getCity());
        clickWait(inputFrom,5);
        clickWait(btnNextMonth,5);
        clickWait(iconOfTheFirstDay,5);
        btnOk.click();
    }
}
