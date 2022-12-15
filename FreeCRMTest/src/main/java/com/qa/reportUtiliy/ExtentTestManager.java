package com.qa.reportUtiliy;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.HashMap;
import java.util.Map;
 
/**
 * OB: extentTestMap holds the information of thread ids and ExtentTest instances.
 * ExtentReports instance created by calling getReporter() method from ExtentManager.
 * At startTest() method, an instance of ExtentTest created and put into extentTestMap with current thread id.
 * At endTest() method, test ends and ExtentTest instance got from extentTestMap via current thread id.
 * At getTest() method, return ExtentTest instance in extentTestMap by using current thread id.
 */
public class ExtentTestManager {
    static Map extentTestMap = new HashMap();
    static ExtentReports extent = ExtentManager.getReporter();
    static ExtentTest test;
 
    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }
 
    public static synchronized void endTest() {
        extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }
    
    public static synchronized void info(LogStatus logStatus,String details) {
    	//test.log(logStatus, stepName, details);
    	test.log(logStatus, details);
    }
 
    public static synchronized ExtentTest startTest(String testName, String desc) {
        test = extent.startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
}