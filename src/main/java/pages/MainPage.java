package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    WebDriver driver;

    private By createIssueButton = By.id("create_link");
    private By dropdownMenu = By.id("header-details-user-fullname");  //click to dropdown on logout
    private By logout = By.id("log_out");
    private By dashboard = By.id("home_link");
    private By logoButton = By.id("logo");

    MainPage(WebDriver driver) {
        this.driver = driver;
    }

    By dropdownMenu() {
        return (dropdownMenu);
    }

    By logout() {
        return (logout);
    }

    By dashboard() {
        return (dashboard);
    }

    By createIssueButton() {
        return (createIssueButton);
    }

    By logoButton() {
        return (logoButton);
    }
}
