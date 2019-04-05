package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;






    private By login = By.id("login-form-username");
    private By password = By.id("login-form-password");
    private By loginButton = By.id("login");

    LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    void navigate(String url) {
        driver.navigate( ).to(url);
    }

    void enterUserName(String name) {
        driver.findElement(login).click( ); //click to close 'not secure connection msg'
        driver.findElement(login).sendKeys(name); //input login
    }

    void enterUserPassword(String password) {
        driver.findElement(this.password).click( ); //click to close 'not secure connection msg'
        driver.findElement(this.password).sendKeys(password); //input password

    }

    void loginClick() {
        driver.findElement(loginButton).click( ); //click on login button
    }

}
