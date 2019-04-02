package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener{

    public void onTestStart(ITestResult iTestResult) {
        System.out.println("onTestStart " + iTestResult);
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("onTestSuccess "+ iTestResult);
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("onTestFailure "+ iTestResult);
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("onTestSkipped "+ iTestResult);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("onTestFailedButWithinSuccessPercentage "+ iTestResult);
    }

    public void onStart(ITestContext iTestContext) {
         DriverManager.initDriver();
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("onFinish "+ iTestContext);
    }



}