package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumConfig
{
    public static AppiumDriver<AndroidElement> driver;

    @BeforeMethod
    public void setUp()
    {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Nex2");
        desiredCapabilities.setCapability("platformVersion", "8.0");
        //desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");-> the second option
        desiredCapabilities.setCapability("appPackage", "com.telran.ilcarro");
        desiredCapabilities.setCapability("appActivity", ".SplashActivity");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        String urlNex2 = "http://localhost:4723/wd/hub"; // way where is driver

        try
        {
            driver = new AppiumDriver<>(new URL(urlNex2), desiredCapabilities);
        }

        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void tearDown()
    {
//        driver.quit();
    }
}