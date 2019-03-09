package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {


    WebDriver driver;


     LoginPage(WebDriver driver) {
        this.driver = driver;
    }


     void navigate(String url) {
        driver.navigate ( ).to (url);
    }

     void enterUserName(String name) {
        driver.findElement (By.id ("login-form-username")).sendKeys (name); //input login
    }
}
