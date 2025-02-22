package screens;

import dto.UserDTO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class LoginScreen extends BaseScreen
{

    public LoginScreen(AppiumDriver<AndroidElement> driver)
    {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/editLoginEmail")
    AndroidElement inputEmail;

    @FindBy(id = "com.telran.ilcarro:id/editLoginPassword")
    AndroidElement inputPassword;

    @FindBy(id = "com.telran.ilcarro:id/loginBtn")
    AndroidElement btnYalla;

    @FindBy(id = "android:id/message")
    AndroidElement errorMessage;

    public void typeFormLogin(UserDTO user)
    {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        clickWait(btnYalla,5);
    }

    public boolean errorMessageIsExisted(String text)
    {
        return textElementIsPresent(errorMessage, text, 5);
    }
}
