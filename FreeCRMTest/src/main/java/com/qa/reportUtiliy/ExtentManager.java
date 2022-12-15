package com.qa.reportUtiliy;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
public class ExtentManager {
	 private static ExtentReports extent;
	 public static String reportfilename = "Test_Results_";
		public static String reportfilePath = System.getProperty("user.dir") + "\\TestResults\\";
		public static String TestResults = System.getProperty("user.dir") + "\\TestResults\\TestRunResults.html";
	 
	    public synchronized static ExtentReports getReporter() {
	        if (extent == null) {
	            //Set HTML reporting file location
	            String workingDir = reportfilePath;
	            if (System.getProperty("os.name").toLowerCase().contains("win")) {
	                extent = new ExtentReports(CreateFileWithTimeStamp(),true);
	            }
	            else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
	                extent = new ExtentReports(CreateFileWithTimeStamp(),true);
	            }
	        }
	        return extent;
	    }
	    
	 // Create a new file
		public static String CreateFileWithTimeStamp() {

			// create a new file with Time Stamp
			File file = new File(reportfilePath + "\\" + reportfilename
					+ GetCurrentTimeStamp().replace(":", "_").replace(".", "_") + ".html");
			String filepath = file.toString();

			try {
				if (!file.exists()) {
					file.createNewFile();
					System.out.println("File is created; file name is " + file.getName());
				} else {
					System.out.println("File already exist");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return filepath;

		}

		// Get current system time
		public static String GetCurrentTimeStamp() {
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");// dd/MM/yyyy
			Date now = new Date();
			String strDate = sdfDate.format(now);
			return strDate;
		}

		// Get Current Host Name
		public static String GetCurrentTestHostName() throws UnknownHostException {
			InetAddress localMachine = InetAddress.getLocalHost();
			String hostName = localMachine.getHostName();
			return hostName;
		}

		// Get Current User Name
		public static String GetCurrentTestUserName() {
			return System.getProperty("user.name");
		}
}