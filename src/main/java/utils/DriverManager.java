package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PropertyReader;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static EventFiringWebDriver eventDriver = null;
    private static WebDriver driver = null;

    public static void initDriver() {
        System.setProperty("webdriver.chrome.driver", PropertyReader.readValue("chromedriverPath"));
        driver = new ChromeDriver();
        DriverManager.eventDriver = new EventFiringWebDriver(driver);
        eventDriver.register(new EventListener());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //TODO - ask google how to do it correctly

    }

    public static WebDriver getDriver(String browser) {

        if(browser.equalsIgnoreCase("chrome")) {
            return eventDriver;
        } else if(browser.equalsIgnoreCase("firefox")) {
            return eventDriver; //TODO - add firefox support
        } else
            return eventDriver; //TODO - handle error
    }





//    public static WebDriverWait waiting() {
//        WebDriverWait wait = new WebDriverWait(driver, 20);
//
//        return wait;
//
//    }

}