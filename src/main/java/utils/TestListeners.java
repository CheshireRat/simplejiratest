package utils;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener{

    public void onTestStart(ITestResult iTestResult) {
        System.out.println("onTestStart");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("onTestSuccess");
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("onTestFailure");
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("onTestSkipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("onTestFailedButWithinSuccessPercentage");
    }

    public void onStart(ITestContext iTestContext) {
         DriverManager.initDriver();
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("onFinish");
    }



}