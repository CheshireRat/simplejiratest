package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    WebDriver driver;

    MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By createIssueButton = By.id ("create_link");
    private By dropdownMenu = By.id ("header-details-user-fullname");  //click to dropdown on logout
    private By logout = By.id ("log_out");
    private By dashboard = By.id ("home_link");
    private By logoButton = By.id ("logo");

    By dropdownMenu() {
        return (dropdownMenu);

    }

    void logoutClick() {
        driver.findElement (logout).click ( ); //click on logout button
    }

    By dashboard() {
        return (dashboard);

    }

    void dashboardClick() {
        driver.findElement (dashboard).click ( ); //click on dashboard
    }


    By createIssueButton() {
        return (createIssueButton);

    }


    By logoButton() {
        return (logoButton);

    }
}
