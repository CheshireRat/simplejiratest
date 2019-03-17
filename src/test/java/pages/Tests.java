package pages;

import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.*;

public class Tests {

    WebDriver driver;
    LoginPage loginPage;
    IssuePage issuePage;
    MainPage mainPage;
    PropertyReader propertyReader = new PropertyReader ( );

    @BeforeTest
    public void setUp() {
        System.setProperty ("webdriver.chrome.driver", propertyReader.readValue ("chromedriverPath"));
        driver = new ChromeDriver ( );
        loginPage = new LoginPage (driver);
        issuePage = new IssuePage (driver);
        mainPage = new MainPage (driver);

    }

    @Test
    @DisplayName("Human-readable test name Login")

    public void login() {
        WebDriverWait wait = new WebDriverWait (driver, 20);

        loginPage.navigate (propertyReader.readValue ("url"));
        loginPage.enterUserName (propertyReader.readValue ("login"));
        loginPage.enterUserPassword (propertyReader.readValue ("password"));
        loginPage.loginClick ( );
        wait.until (ExpectedConditions.presenceOfElementLocated (mainPage.createIssueButton ( )));

        Assert.assertTrue (driver.findElements (mainPage.createIssueButton ( )).size ( ) != 0); //Create button presented

    }

    @Test
    @DisplayName("Human-readable test name")
    public void logout() {
        login ( );
        WebDriverWait wait = new WebDriverWait (driver, 20);

        driver.findElement (mainPage.dropdownMenu ( )).click ( ); //click to dropdown on logout
        mainPage.logoutClick ( ); //click  on logout
        mainPage.dashboardClick ( );
        wait.until (ExpectedConditions.presenceOfElementLocated (mainPage.dashboard ( )));
        Assert.assertTrue (driver.findElements (mainPage.dashboard ( )).size ( ) != 0); //Log In button presented
    }

    String issueType = "Task";
    String issueSummary = "Task summary";
    String issueDescription = "Task descr";
    //TODO - move issuetype, summary, desc to method parameters

    @Test
    public void createIssue() {

        //TODO - add supporting for all issue types(epic not supported now)
        login ( );
        WebDriverWait wait = new WebDriverWait (driver, 20);

        driver.findElement (mainPage.createIssueButton ( )).click ( );

        wait.until (ExpectedConditions.presenceOfElementLocated (issuePage.issueTypeField ( )));


        //input issue type
        driver.findElement (issuePage.issueTypeField ( )).click ( );
        driver.findElement (issuePage.issueTypeField ( )).sendKeys (issueType);
        driver.findElement (issuePage.issueTypeField ( )).sendKeys (Keys.ENTER);
        wait.until (ExpectedConditions.elementToBeClickable (issuePage.summaryField ( )));

        //summary
        driver.findElement (issuePage.summaryField ( )).click ( );
        driver.findElement (issuePage.summaryField ( )).sendKeys (issueSummary);

        //input description
        driver.findElement (issuePage.descriptionField ( )).click ( );
        driver.findElement (issuePage.descriptionField ( )).sendKeys (issueDescription);

        //create issue
        driver.findElement (issuePage.submitIssueButton ( )).click ( );

        Assert.assertTrue (driver.findElements (mainPage.logoButton ( )).size ( ) != 0);

    }

}
