package pages;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.DriverManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


public class Tests {

    WebDriver driver;
    LoginPage loginPage;
    IssuePage issuePage;
    MainPage mainPage;
    WebDriverWait wait;
    Set <Cookie> allCookies;


    @Parameters("browser")
    @BeforeTest
    public void setUpLogin(String browser) {
        driver = DriverManager.getDriver(browser);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }
    @Test(groups = {"smoke"})
    public void login() {

        loginPage.navigate(PropertyReader.readValue("url"));
        loginPage.enterUserName(PropertyReader.readValue("login"));
        loginPage.enterUserPassword(PropertyReader.readValue("password"));
        loginPage.loginClick();
        wait.until(ExpectedConditions.presenceOfElementLocated(mainPage.createIssueButton()));
        Assert.assertTrue(mainPage.createIssueButtonSize() != 0); //Create button presented
        allCookies = driver.manage().getCookies();
    }
    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE) {
            driver.manage().window().maximize();
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = result.getMethod().getMethodName() + "_" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.jpg'").format(new Date());
//TODO - String.format for fileName
            FileHandler.copy(scrFile, new File("C:\\QA\\" + fileName));
            //TODO - add correct scr folder handle to properties
        }

    }
    @Parameters("browser")
    @BeforeTest
    public void setUpLogout(String browser) {
        driver = DriverManager.getDriver(browser);
        loginPage = new LoginPage(driver);
        //TODO - move cookie to method, probably DriverManager
        for(Cookie cookie : allCookies) {
            driver.manage().addCookie(cookie);
        }
    }

    @Test(groups = {"smoke"})
    public void logout() {
        loginPage.navigate(PropertyReader.readValue("url"));
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

    @Parameters("browser")
    @BeforeTest
    public void setUpCreateIssue(String browser) {
        driver = DriverManager.getDriver(browser);
        loginPage = new LoginPage(driver);
        for(Cookie cookie : allCookies) {
            driver.manage().addCookie(cookie);
        }

    }

    @Test(groups = {"smoke"})
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

    @Parameters("browser")
    @BeforeTest
    public void setUpRemoveLabel(String browser) {
        driver = DriverManager.getDriver(browser);
        loginPage = new LoginPage(driver);
        issuePage = new IssuePage(driver);
        wait = DriverManager.waiting();
        for(Cookie cookie : allCookies) {
            driver.manage().addCookie(cookie);
        }
    }

    //TODO - fix it
    @Test(groups = {"regression"})
    public void removeLabel() {
        mainPage.navigate(baseUrl + issueID);
//        Actions builder = new Actions(driver);
//        Action mouseOverHome = builder.moveToElement(issuePage.labelsEditButton()).build();
//        issuePage.labelsEditButtonClick();

//TODO - check it, unstable
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.removeLabelsButton(labelName)));
        issuePage.removeLabelsButtonClick(labelName);

        issuePage.enterButtonPress();
    }

    @Parameters("browser")
    @BeforeTest
    public void setUpAddLabelByText(String browser) {
        driver = DriverManager.getDriver(browser);
        loginPage = new LoginPage(driver);
        issuePage = new IssuePage(driver);
        for(Cookie cookie : allCookies) driver.manage().addCookie(cookie);
    }

    @Test(groups = {"regression"})
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

    @Parameters("browser")
    @BeforeTest
    public void setUpChangeIssueStatus(String browser) {
        driver = DriverManager.getDriver(browser);
        loginPage = new LoginPage(driver);
        issuePage = new IssuePage(driver);
        for(Cookie cookie : allCookies) driver.manage().addCookie(cookie);
    }

    @Test(groups = {"feature"})
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

    @Parameters("browser")
    @BeforeTest
    public void setUpAddRemoveDescription(String browser) {
        driver = DriverManager.getDriver(browser);
        loginPage = new LoginPage(driver);
        for(Cookie cookie : allCookies) driver.manage().addCookie(cookie);
    }

    @Test(groups = {"feature"})
    public void removeDescription() {
        mainPage.navigate(baseUrl + issueID);
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.descriptionEdit()));
        issuePage.descriptionEditClick();
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.description()));
        issuePage.descriptionClear();
        wait.until(ExpectedConditions.presenceOfElementLocated(issuePage.emptyDescription()));
        Assert.assertTrue(issuePage.emptyDescriptionSize() != 0);
    }

    //TODO - add test with Drag and Drop - https://pastebin.com/dYzkzEf6
}
