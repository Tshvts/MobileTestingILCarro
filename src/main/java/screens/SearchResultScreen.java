package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultScreen extends BaseScreen
{

    public SearchResultScreen(AppiumDriver<AndroidElement> driver)
    {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/resultTitle")
    AndroidElement titleSearchResult;

    public boolean validateMovingToSearchResult(String text, int time)
    {
        return textElementIsPresent(titleSearchResult,text,time);
    }
}
