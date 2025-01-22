package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
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

    public void goToRegistrationPage()
    {
        clickWait(btnMoreOptions,5);
        clickWait(btnRegistration,5);
    }
}
