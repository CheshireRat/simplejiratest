package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.File;

public class ChromeExpansionsManagement {

    ChromeDriver driver;

    @Parameters("browser")
    @Test
    public  void installExt() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();

        //install ext from /C:/QA/extension.crx
        options.addExtensions (new File(PropertyReader.readValue("extPath")));
        DesiredCapabilities capabilities = new DesiredCapabilities ();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        Thread.sleep(5000);

        //register ext
       driver.navigate( ).to("https://lingualeo.com/ru/profile?utm_source=ll_plugin&utm_medium=plugin&utm_campaign=logindialog");
        driver.findElement(By.id("email")).sendKeys("nuzhin.ivan@gmail.com");
        driver.findElement(By.id("password")).sendKeys("test");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div[3]/form/input[4]")).click( );
        Thread.sleep(5000);

       //open site and call pop-up
        driver.navigate( ).to("https://bash.im");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[contains(text(),'Утверждено')]//text()[1]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Утверждено')]//text()[1]")).click();
        Assert.assertTrue(driver.findElement(By.id("lleo_context")).isDisplayed()); //Pop - up presented
    }
}