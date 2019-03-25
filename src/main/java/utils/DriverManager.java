package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PropertyReader;

public class DriverManager {
    private static WebDriver driver = null;
    private static WebDriverWait wait = null;

    public static void initDriver() {
        System.setProperty("webdriver.chrome.driver", PropertyReader.readValue("chromedriverPath"));
        driver = new ChromeDriver();

    }
    public static WebDriver getDriver() {
       return driver ;
    }

    public static WebDriverWait waiting() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait ;
    }

}