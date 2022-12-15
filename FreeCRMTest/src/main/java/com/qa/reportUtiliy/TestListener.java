package com.qa.reportUtiliy;

import com.qa.reportUtiliy.Log4j;
import com.crm.qa.base.BaseTest;
import com.qa.reportUtiliy.ExtentManager;
import com.qa.reportUtiliy.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
 
public class TestListener extends BaseTest implements ITestListener {
 
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    
    private static String Fail(ITestResult iTestResult) {
        return iTestResult.getThrowable().getMessage();
    }
    
    
	private String lineNumberFail(ITestResult iTestResult) {
		//iTestResult.getThrowable().fillInStackTrace();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		iTestResult.getThrowable().printStackTrace(pw);
		String sStackTrace = sw.toString(); // stack trace as a string
		//sStackTrace.matches(iTestResult.getMethod().getConstructorOrMethod().getName());
		String TestName=iTestResult.getMethod().getConstructorOrMethod().getName();
		String segments[] = sStackTrace.split(TestName);
		String errorStep=segments[1];
		String getErrorStep = "Error Step or line Num ----  "+TestName+"."+errorStep.substring(errorStep.indexOf("(") + 1, errorStep.indexOf(")"));
		return getErrorStep;
	}

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        
        iTestContext.setAttribute("WebDriver", this.driver);
    }
 
    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for extentreports reporting!
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }
 
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " Start");
        Log4j.startTestCase(getTestMethodName(iTestResult), "start");
    }
 
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " Succeed");
        Log4j.endTestCase(getTestMethodName(iTestResult), "succeed");
        /*//Get driver from BaseTest and assign to local webDriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).driver;
        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).
            getScreenshotAs(OutputType.BASE64);
       // ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
        ExtentTestManager.getTest().log(LogStatus.PASS,
                ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));*/
        //ExtentReports log operation for passed tests.
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
    }
 
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in on TestFailure method " + getTestMethodName(iTestResult) + " Failed");
 
        //Get driver from BaseTest and assign to local webDriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).driver;
 
        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).
            getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(LogStatus.FAIL, lineNumberFail(iTestResult),
        ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot)+ "" +Fail(iTestResult));
        Log4j.error(Fail(iTestResult)," Failed ", lineNumberFail(iTestResult));
    }
 
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
        //ExtentReports log operation for skipped tests.
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        Log4j.error(Fail(iTestResult)," Test Skipped ",lineNumberFail(iTestResult));
    }
 
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}