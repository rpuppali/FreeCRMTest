package practiceMethods;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.exec.util.StringUtils;


public class Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Stacktrace = "org.testng.Assert.fail(Assert.java:93) at org.testng.Assert.fail(Assert.java:100) at com.crm.qa.testcases.AppTestCase.TestCaseID_5(AppTestCases.java:55) at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)at java.lang.reflect.Method.invoke(Unknown Source)at org.testng.internal.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:108)";    
		String segments[] = Stacktrace.split("TestCaseID_5");
		String s2=segments[1];
		System.out.println(" present  "+s2);
		String s3 = s2.substring(s2.indexOf("(") + 1, s2.indexOf(")"));
		System.out.println(" Final line " +"("+s3+")");
		
	}
	 static int isSubstring(
		        String s1, String s2)
		    {
		        int M = s1.length();
		        int N = s2.length();
		 
		        /* A loop to slide pat[] one by one */
		        for (int i = 0; i <= N - M; i++) {
		            int j;
		 
		            /* For current index i, check for
		            pattern match */
		            for (j = 0; j < M; j++)
		                if (s2.charAt(i + j)
		                    != s1.charAt(j))
		                    break;
		 
		            if (j == M)
		                return i;
		        }
		 
		        return -1;
		    }
}
