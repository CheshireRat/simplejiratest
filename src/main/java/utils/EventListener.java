package utils;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.beans.EventHandler;


public class EventListener implements WebDriverEventListener {

    private static final Logger LOG = LogManager.getLogger(EventHandler.class);


    public void beforeAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertDismiss(WebDriver webDriver) {

    }

    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    public void beforeNavigateTo(String s, WebDriver webDriver) {
        System.out.println("beforeNavigateTo || " + webDriver.getCurrentUrl() );

    }

    public void afterNavigateTo(String s, WebDriver webDriver) {
        System.out.println("afterNavigateTo || " + webDriver.getCurrentUrl() );

    }

    public void beforeNavigateBack(WebDriver webDriver) {
        System.out.println("beforeNavigateBack || " + webDriver.getCurrentUrl() );


    }

    public void afterNavigateBack(WebDriver webDriver) {

    }

    public void beforeNavigateForward(WebDriver webDriver) {

    }

    public void afterNavigateForward(WebDriver webDriver) {

    }

    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("beforeFindBy ||  " + by + " || " + webElement);

    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("afterFindBy ||  " + by );

    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("beforeClickOn ||  "  + webElement );

    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("afterClickOn ||  "  + webElement );

    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    public void beforeScript(String s, WebDriver webDriver) {

    }

    public void afterScript(String s, WebDriver webDriver) {

    }

    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void onException(Throwable throwable, WebDriver webDriver) {
        System.out.println("onException || " + webDriver.getPageSource());

    }

    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
        System.out.println("beforeGetText");
    }

    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {
        System.out.println("afterGetText");

    }
}
