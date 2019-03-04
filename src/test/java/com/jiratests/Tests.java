package com.jiratests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class Tests {
    String url = "http://jira.hillel.it:8080/secure/Dashboard.jspa";
    String login = "Nuzhin_Ivan";
    String password;

    {
        password = "Фифифягдф1";
    }
    //TODO get credentials from file
    WebDriver driver = new ChromeDriver ();

    @Test

    public void login(){
        //TODO - cookie
        driver.navigate().to(url);
        driver.findElement(By.id("login-form-username")).click(); //click to close 'not secure connection msg'
        driver.findElement(By.id("login-form-username")).sendKeys(login); //input login
        driver.findElement(By.id("login-form-password")).click(); //click to close 'not secure connection msg'
        driver.findElement(By.id("login-form-password")).sendKeys(password); //input password
        driver.findElement(By.id("login")).click(); //click on login button
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        /* TODO - move to PageObject and rewrite, see url below. Add explicit
         * https://stackoverflow.com/questions/18843581/wait-for-element-webdriver-pageobject-pattern/18844275#18844275
         * */

        assertTrue(driver.findElements(By.id("create_link")).size() != 0); //Create button presented

    }

    @Test
    public void logout(){
        login();
        //TODO rewrite, probably it shouldn't be here
        driver.findElement(By.id("header-details-user-fullname")).click(); //click to dropdown on logout
        driver.findElement(By.id("log_out")).click(); //click to Log Out
        driver.findElement(By.xpath("/html/body/div[1]/header/nav/div/div[2]/h1/a/img")).click(); //navigate to dashboard page
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        /* TODO - move to PageObject and rewrite, see url below. Add explicit
         * https://stackoverflow.com/questions/18843581/wait-for-element-webdriver-pageobject-pattern/18844275#18844275
         * */
        assertTrue(driver.findElements(By.xpath("/html/body/div[1]/header/nav/div/div[3]/ul/li[4]/a")).size() != 0); //Log In button presented

    }
    @Test
    public void createIssue()  {
        //TODO - move issuetype, summary, desc to method parameters
        //TODO - add supporting for all issue types(epic not supported now)
        login();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.findElement(By.id("create_link")).click();

        //input issue type
        driver.findElement(By.id("issuetype-field")).click();
        driver.findElement(By.id("issuetype-field")).sendKeys("Task");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.ENTER);
        /* TODO - move to PageObject and\or rewrite. Add explicit*/
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"summary\"]")));

        //summary
        driver.findElement(By.id("summary")).click();
        driver.findElement(By.xpath("//*[@id=\"summary\"]")).sendKeys("Task summary");

        //input description
        driver.findElement(By.id ("description")).click();
        driver.findElement(By.id ("description")).sendKeys("Task descr");

        //create issue
        driver.findElement(By.id ("create-issue-submit")).click();

        assertTrue(driver.findElements(By.xpath("//*[@id=\"logo\"]/a/img")).size() != 0);

    }

}
