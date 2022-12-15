
package com.qa.reportUtiliy;
import org.apache.log4j.Logger;
 
	public class Log4j {
 
		//Initialize Log4j logs
		private static Logger Log = Logger.getLogger(Log4j.class.getName());//
 
	// This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
	public static void startTestCase(String testCaseId, String status){
 
	   Log.info("****************************************************************************************");
	   Log.info("***********************"+"BEGIN -----TEST-------CASE"+" ********************************");
	   Log.info("Test Case ID:------------->"+"    "+testCaseId+ "   ");
	   Log.info("Status:----------->"+"    "+status+ " ");

	
	   }
 
	//This is to print log for the ending of the test case
	public static void endTestCase(String testCaseId,String status){
	   Log.info(testCaseId+ " has been passed successfully ");
	   Log.info("Status:----------->"+"    "+status+ " ");
	   Log.info("*************************"+"END---------TEST--------CASE"+"******************************");
	   Log.info("****************************************************************************************");
	   Log.info("																						 ");
	   Log.info("																						 ");
	   Log.info("																						 ");
 
	   }
 
    // Need to create these methods, so that they can be called  
	public static void info(String message) {
		   Log.info(message);
		   }
 
	public static void warn(String message) {
	   Log.warn(message);
	   }
 
	public static String error(String message,String Status,String errorStep) {
		   Log.info("Failed :------>"+"  "+message+ "   ");
		   Log.info("Error Step:----------->"+"    "+errorStep+ " ");
		   Log.info("Status:----------->"+"    "+Status+ " ");
		   Log.info("*************************"+"END---------TEST--------CASE"+"******************************");
		   Log.info("****************************************************************************************");
		   Log.info("																						 ");
		   Log.info("																						 ");
		   Log.info("																						 ");
		
		return message;
	}
 
	public static void fatal(String message) {
	   Log.fatal(message);
	   }
 
	public static void debug(String message) {
	   Log.debug(message);
	   }
 
	}