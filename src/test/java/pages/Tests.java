package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import org.testng.Assert;
import org.testng.annotations.*;


public class Tests {

    WebDriver driver;
    LoginPage loginPage;
    PropertyReader propertyReader = new PropertyReader ();

    @BeforeTest
    public void setUp() {
        System.setProperty ("webdriver.chrome.driver", "/C:/QA/chromedriver.exe");
        driver = new ChromeDriver ( );
        loginPage = new LoginPage (driver);

    }

    @Test
    public void login() {


        loginPage.navigate (propertyReader.readValue ("url"));
        loginPage.enterUserName (propertyReader.readValue ("login"));
        loginPage.enterUserPassword (propertyReader.readValue ("password"));
        loginPage.loginClick ();

        WebElement create_link = (new WebDriverWait (driver, 10))
                .until (ExpectedConditions.presenceOfElementLocated (loginPage.createIssueButton ()));

        Assert.assertTrue (driver.findElements (loginPage.createIssueButton ()).size ( ) != 0); //Create button presented

    }

    @Test
    public void logout() {
        login ( );
        driver.findElement (By.id ("header-details-user-fullname")).click ( ); //click to dropdown on logout
        driver.findElement (By.id ("log_out")).click ( ); //click to Log Out
        driver.findElement (By.id ("home_link")).click ( ); //navigate to dashboard page
        //TODO move to the method, which will run before all tests
        WebElement login = (new WebDriverWait (driver, 10))
                .until (ExpectedConditions.presenceOfElementLocated (By.id ("home_link")));
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
