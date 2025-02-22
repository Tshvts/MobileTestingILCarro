package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseScreen
{
    AppiumDriver<AndroidElement> driver;

    public BaseScreen(AppiumDriver<AndroidElement> driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public boolean textElementIsPresent(AndroidElement element, String text, int time)
    {
        try
        {
            return new WebDriverWait(driver,time).until(ExpectedConditions.textToBePresentInElement(element,text));
        }

        catch (TimeoutException e)
        {
            System.out.println("Created exception ===> textElementIsPresent");
            e.printStackTrace();
            return false;
        }
    }

    public void pause(int time)
    {
        try {
            Thread.sleep(1000L+time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickWait(AndroidElement element, int time)
    {
        new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(element)).click();
    }
}
