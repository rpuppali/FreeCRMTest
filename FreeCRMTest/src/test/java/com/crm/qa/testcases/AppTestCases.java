package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.qa.base.BaseTest;
import com.qa.reportUtiliy.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class AppTestCases extends BaseTest{
 
    @Test(priority = 0, description = " Login Scenario with  username and password.")
    public void TestCaseID_1() {
    	String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        //ExtentReports Description
        ExtentTestManager.startTest(methodName, "Login Scenario with  username and password");
        loginPage.loginToApp(prop.getProperty("username"), prop.getProperty("password"));
           
    }
//    @Test(priority = 1, description = " Verify")
//    public void TestCaseID_2() {
//    	String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
//        //ExtentReports Description
//        ExtentTestManager.startTest(methodName, "Verify Title");
//        homePage.verifyHomePageTitle();
//           
//    }
    
	@Test(priority = 2, description = "PlaceOrder")
	public void TestCaseID_3() throws Exception {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		// ExtentReports Description
		ExtentTestManager.startTest(methodName, "placeOrder");
		
		String CustomerName = xlReader.GetTestData("MasterTestData", "PlaceOrder", "CustomerName", methodName);
		String street1 = xlReader.GetTestData("MasterTestData", "PlaceOrder", "street1", methodName);
		String city1 = xlReader.GetTestData("MasterTestData", "PlaceOrder", "city1", methodName);
		ExtentTestManager.info(LogStatus.INFO, CustomerName);
		homePage.placeOrder(CustomerName, street1, city1);

	}
   @Test(priority = 3, description = "Logout")
    public void TestCaseID_4() {
    	String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        //ExtentReports Description
        ExtentTestManager.startTest(methodName, "Logout");
        homePage.logout();
           
    }
//    @Test(priority = 3, description = "Failed")
//    public void TestCaseID_5() {
//    	String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
//        //ExtentReports Description
//    	 ExtentTestManager.startTest(methodName, "Failed");
//        Assert.fail();
//           
//    }
 

}
