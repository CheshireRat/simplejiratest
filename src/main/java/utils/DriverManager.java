package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PropertyReader;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver driver = null;

    public static void initDriver() {
        System.setProperty("webdriver.chrome.driver", PropertyReader.readValue("chromedriverPath"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS); //TODO - ask google how to do it correctly
    }
    public static WebDriver getDriver() {
       return driver ;
    }

    public static WebDriverWait waiting() {
        WebDriverWait wait = new WebDriverWait(driver, 20);

        return wait ;

    }

}