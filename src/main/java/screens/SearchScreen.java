package screens;

import dto.SearchDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

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

    @FindBy(id = "android:id/prev")
    AndroidElement btnPrevMonth;

    @FindBy(xpath = "//*[@text='1']")
    AndroidElement iconOfTheFirstDay;

    @FindBy(id = "android:id/button1")
    AndroidElement btnOkCalendar;

    @FindBy(id = "com.telran.ilcarro:id/editTo")
    AndroidElement inputTo;

    @FindBy(xpath = "//*[@text='30']")
    AndroidElement iconOfTheLastDay;

    @FindBy(id = "com.telran.ilcarro:id/searchBtn")
    AndroidElement btnSearchYalla;

    @FindBy(id = "android:id/date_picker_header_year")
    AndroidElement btnYear;

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
        btnOkCalendar.click();
        clickWait(inputTo,5);
        clickWait(btnNextMonth,5);
        clickWait(iconOfTheLastDay,5);
        btnOkCalendar.click();
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
        btnOkCalendar.click();
    }

    //Lesson 25
    public void findCarWithCalendar(String city, String dateStart, String dateEnd)
    {
        inputLocation.sendKeys(city);
        clickWait(inputFrom,5);
        setDateCalendar(dateStart);
        clickWait(btnOkCalendar,5);
        clickWait(inputTo,5);
        setDateCalendar(dateEnd);
        clickWait(btnOkCalendar,5);
        clickWait(btnSearchYalla,5);
    }

    private void setDateCalendar(String dateStart)
    {
        String[] arrayDate = dateStart.split(" ");
        //========year
        if (LocalDate.now().getYear() != Integer.parseInt(arrayDate[2]))
        {
            clickWait(btnYear, 5);
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@text='" + arrayDate[2] + "']")
                    )).click();
        }
        //========months
        int month = returnNumOfMonth(arrayDate[1]);
        int currentMonth = LocalDate.now().getMonthValue()+1;
        int quantityClick = month - currentMonth;
        if(quantityClick>0)
            for (int i = 0; i<quantityClick; i++)
            {
                clickWait(btnNextMonth,3);
            }
        else if (quantityClick<0)
        {
            for (int i = 0; i<Math.abs(quantityClick); i++)
            {
                clickWait(btnNextMonth,3);
            }
        }

        //========days
        pause(3);
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@content-desc='"
                                + dateStart + "']"))).click();
    }

    private int returnNumOfMonth(String month)
    {
        Month month1 = Month.valueOf(month.toUpperCase(Locale.ENGLISH));
        return month1.getValue();
    }
}
