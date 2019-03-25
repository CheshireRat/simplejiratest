package pages;

import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.DriverManager;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Tests {

    WebDriver driver;
    LoginPage loginPage;
    IssuePage issuePage;
    MainPage mainPage;
    WebDriverWait wait;

    @BeforeTest
    public void setUpLogin() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }
    @Test
    public void login() {
        loginPage.navigate(PropertyReader.readValue("url"));
        loginPage.enterUserName(PropertyReader.readValue("login"));
        loginPage.enterUserPassword(PropertyReader.readValue("password"));
        loginPage.loginClick();
        wait.until(ExpectedConditions.presenceOfElementLocated(mainPage.createIssueButton()));

        Assert.assertTrue(driver.findElements(mainPage.createIssueButton()).size() != 0); //Create button presented

    }
    @BeforeTest
    public void setUpLogout() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        
    }
    @Test
    @DisplayName("Human-readable test name")
    public void logout() {
        driver.findElement(mainPage.dropdownMenu()).click(); //click to dropdown on logout
        driver.findElement(mainPage.logout()).click();
        driver.findElement(mainPage.dashboard()).click();
      
        wait.until(ExpectedConditions.presenceOfElementLocated(mainPage.dashboard()));
        Assert.assertTrue(driver.findElements(mainPage.dashboard()).size() != 0); //Log In button presented
    }

    String issueType = "Task";
    String issueSummary = "Task summary";
    String issueDescription = "Task descr";
    //TODO - move issuetype, summary, desc to method parameters

    @BeforeTest
    public void setUpCreateIssue() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
       
    }
    @Test
    public void createIssue() {
        //TODO - add supporting for all issue types(epic not supported now)
        driver.findElement(mainPage.createIssueButton()).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.issueTypeField()));

        //input issue type
        driver.findElement(issuePage.issueTypeField()).click();
        driver.findElement(issuePage.issueTypeField()).sendKeys(issueType);
        driver.findElement(issuePage.issueTypeField()).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(issuePage.summaryField()));

        //summary
        driver.findElement(issuePage.summaryField()).click();
        driver.findElement(issuePage.summaryField()).sendKeys(issueSummary);

        //input description
        driver.findElement(issuePage.descriptionField()).click();
        driver.findElement(issuePage.descriptionField()).sendKeys(issueDescription);

        //create issue
        driver.findElement(issuePage.submitIssueButton()).click();

        Assert.assertTrue(driver.findElements(mainPage.logoButton()).size() != 0);

    }

    String labelName = "user";

    @BeforeTest
    public void setUpRemoveLabel() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        issuePage = new IssuePage(driver);
        wait = DriverManager.waiting();
    }
    //TODO - fix it
    @Test
    public void removeLabel() {
        driver.navigate().to(baseUrl + issueID);
        driver.findElement(issuePage.labelsEditButton()).click();
//TODO - check it, unstable
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.removeLabelsButton(labelName)));
        driver.findElement(issuePage.removeLabelsButton(labelName)).click();
        Robot robot = null;
        try {
            robot = new Robot();
        } catch(AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    @BeforeTest
    public void setUpAddLabelByText() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        issuePage = new IssuePage(driver);
    }
    @Test
    //add label "user" (labelName)
    public void addLabelByText() {
        driver.navigate().to(baseUrl + issueID);
        driver.findElement(issuePage.labelsEditButton()).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.labelsTextInput()));
        driver.findElement(issuePage.labelsTextInput()).sendKeys(labelName);
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.labelsTextInput()));
        driver.findElement(issuePage.labelsTextInput()).submit();
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.labelByName(labelName)));
        Assert.assertTrue(driver.findElements(issuePage.labelByName(labelName)) != null);
    }

    @BeforeTest
    public void setUpChangeIssueStatus() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        issuePage = new IssuePage(driver);

    }
    @Test
    public void changeIssueStatus() {
        driver.navigate().to(baseUrl + issueID);
        driver.findElement(issuePage.workflowDropdown()).click();
        driver.findElement(issuePage.workflowDropdownInProgress()).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.issueStatusInProgress()));
        Assert.assertTrue(driver.findElements(issuePage.issueStatusInProgress()) != null);

    }

    String issueID = "QAAUT7-719";
    String baseUrl = "http://jira.hillel.it:8080/browse/";
//TODO move to params

    @BeforeTest
    public void setUpAddRemoveDescription() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);

    }
    @Test
    public void removeDescription() {
        driver.navigate().to(baseUrl + issueID);
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.descriptionEdit()));
        driver.findElement(issuePage.descriptionEdit()).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.description()));
        driver.findElement(issuePage.description()).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.emptyDescription()));
        Assert.assertTrue(driver.findElements(issuePage.emptyDescription()).size() != 0);
    }
}
