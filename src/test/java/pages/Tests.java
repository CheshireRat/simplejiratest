package pages;

import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.DriverManager;

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
        Assert.assertTrue(mainPage.createIssueButtonSize() != 0); //Create button presented
        //Assert.assertTrue(driver.findElements(mainPage.createIssueButton()).size() != 0);

    }

    @BeforeTest
    public void setUpLogout() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);

    }

    @Test
    @DisplayName("Human-readable test name")
    public void logout() {
        mainPage.dropdownMenuClick();  //click to dropdown on logout
        mainPage.logoutClick();
        mainPage.dashboardClick();
        wait.until(ExpectedConditions.presenceOfElementLocated(mainPage.dashboard()));
        Assert.assertTrue(mainPage.dashboardSize() != 0); //Log In button presented
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

        mainPage.createIssueButtonClick();
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.issueTypeField()));

        //input issue type
        issuePage.issueTypeFieldInput(issueType);

        wait.until(ExpectedConditions.elementToBeClickable(issuePage.summaryField()));

        //summary

        issuePage.summaryFieldInput(issueSummary);

        //input description
        issuePage.descriptionFieldInput(issueDescription);

        //create issue
        issuePage.submitIssueButtonClick();

        Assert.assertTrue(mainPage.logoButtonSize() != 0);
//Assert.assertTrue(driver.findElements(mainPage.logoButton()).size() != 0);
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
        mainPage.navigate(baseUrl + issueID);

        issuePage.labelsEditButtonClick();

//TODO - check it, unstable
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.removeLabelsButton(labelName)));
        issuePage.removeLabelsButtonClick(labelName);

        issuePage.enterButtonPress();
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
        mainPage.navigate(baseUrl + issueID);

        issuePage.labelsEditButtonClick();
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.labelsTextInput()));

        issuePage.labelsTextInput(labelName);
        // wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.labelsTextInput()));
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.labelByName(labelName)));
        Assert.assertTrue(issuePage.labelByName(labelName) != null);
        // Assert.assertTrue(driver.findElements(issuePage.labelByName(labelName)) != null);
    }

    @BeforeTest
    public void setUpChangeIssueStatus() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        issuePage = new IssuePage(driver);

    }

    @Test
    public void changeIssueStatus() {
        mainPage.navigate(baseUrl + issueID);

        issuePage.workflowDropdownClick();
        issuePage.workflowDropdownInProgressClick();
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.issueStatusInProgress()));
        Assert.assertTrue(issuePage.issueStatusInProgress() != null);

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
        mainPage.navigate(baseUrl + issueID);
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.descriptionEdit()));
        issuePage.descriptionEditClick();
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.description()));
        issuePage.descriptionClear();
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.emptyDescription()));
        Assert.assertTrue(issuePage.emptyDescriptionSize() != 0);
    }
}
