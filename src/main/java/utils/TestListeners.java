package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener{
    private static final Logger LOGGER = LogManager.getLogger(TestListeners.class.getName());

    public void onTestStart(ITestResult iTestResult) {
        LOGGER.info("onTestStart" + iTestResult);

    }

    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.info("onTestSuccess"  + iTestResult );

    }

    public void onTestFailure(ITestResult iTestResult) {
        LOGGER.info("onTestFailure"  + iTestResult);

    }

    public void onTestSkipped(ITestResult iTestResult) {
        LOGGER.info("onTestSkipped"  + iTestResult);


    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
         DriverManager.initDriver();
    }

    public void onFinish(ITestContext iTestContext) {
        LOGGER.info("onFinish"  + iTestContext);

    }



}