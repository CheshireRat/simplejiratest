package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;


public class Tests {

    String url = "http://jira.hillel.it:8080/secure/Dashboard.jspa";
    String login = "Nuzhin_Ivan";
    String password;
    WebDriver driver;
    LoginPage loginPage;

    {
        password = "test";
    }

    //TODO get credentials from file
    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/C:/QA/chromedriver.exe");
        driver = new ChromeDriver ( );
        loginPage = new LoginPage (driver);
    }

    @Test
    public void login() {

        loginPage.navigate (url);
        driver.findElement (By.id ("login-form-username")).click ( ); //click to close 'not secure connection msg'
        loginPage. enterUserName(login);
        driver.findElement (By.id ("login-form-password")).click ( ); //click to close 'not secure connection msg'
        driver.findElement (By.id ("login-form-password")).sendKeys (password); //input password
        driver.findElement (By.id ("login")).click ( ); //click on login button
        WebElement create_link = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("create_link")));

        Assert.assertTrue (driver.findElements (By.id ("create_link")).size ( ) != 0); //Create button presented

    }

    @Test
    public void logout() {
        login ( );
        driver.findElement (By.id ("header-details-user-fullname")).click ( ); //click to dropdown on logout
        driver.findElement (By.id ("log_out")).click ( ); //click to Log Out
        driver.findElement (By.id ("home_link")).click ( ); //navigate to dashboard page
        //TODO move to the method, which will run before all tests
        WebElement login = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id ("home_link")));
        Assert.assertTrue (driver.findElements (By.id ("home_link")).size ( ) != 0); //Log In button presented
    }

    @Test
    public void createIssue() {
        //TODO - move issuetype, summary, desc to method parameters
        //TODO - add supporting for all issue types(epic not supported now)
        login ( );
        WebDriverWait wait = new WebDriverWait (driver, 20);
        driver.findElement (By.id ("create_link")).click ( );

        //input issue type
        driver.findElement (By.id ("issuetype-field")).click ( );
        driver.findElement (By.id ("issuetype-field")).sendKeys ("Task");
        driver.findElement (By.id ("issuetype-field")).sendKeys (Keys.ENTER);
        wait.until (ExpectedConditions.elementToBeClickable (By.id ("summary")));

        //summary
        driver.findElement (By.id ("summary")).click ( );
        driver.findElement (By.id ("summary")).sendKeys ("Task summary");

        //input description
        driver.findElement (By.id ("description")).click ( );
        driver.findElement (By.id ("description")).sendKeys ("Task descr");

        //create issue
        driver.findElement (By.id ("create-issue-submit")).click ( );

        Assert.assertTrue (driver.findElements (By.id ("logo")).size ( ) != 0);

    }

}
