package screens;

import dto.UserDTO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationScreen extends BaseScreen
{

    public RegistrationScreen(AppiumDriver<AndroidElement> driver)
    {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/editRegName")
    AndroidElement inputName;

    @FindBy(id = "com.telran.ilcarro:id/editRegLastName")
    AndroidElement inputLastName;

    @FindBy(id = "com.telran.ilcarro:id/editRegEmail")
    AndroidElement inputEmail;

    @FindBy(id = "com.telran.ilcarro:id/editRegPassword")
    AndroidElement inputPassword;

    @FindBy(id = "com.telran.ilcarro:id/checkBoxAgree")
    AndroidElement checkbox;

    @FindBy(id = "com.telran.ilcarro:id/regBtn")
    AndroidElement btnYalla;

    @FindBy(xpath = "//*[@text='Registration success!']")
    AndroidElement popUpMessage;

    @FindBy(id = "android:id/message")
    AndroidElement errorMessage;

    @FindBy(xpath = "android:id/button1")
    AndroidElement btnOkErrorMessage;

    public void typeRegistrationForm(UserDTO user)
    {
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        clickWait(checkbox,5);
        clickWait(btnYalla,5);
    }

    public boolean popUpMessageIsExisted(String text)
    {
       return textElementIsPresent(popUpMessage, text, 5);
    }

    public boolean errorMessageIsExisted(String text)
    {
       return textElementIsPresent(errorMessage, text, 5);
    }
}
