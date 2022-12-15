package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.WebEventListener;
import com.crm.qa.util.Xls_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String chromeDriver = System.getProperty("user.dir") + "\\BrowserDrivers\\chromedriver 86.exe";
	public static String ieDriver = System.getProperty("user.dir") + "\\BrowserDrivers\\IEDriverServer.exe";
	public static String firefoxDriver = System.getProperty("user.dir") + "\\BrowserDrivers\\geckodriver.exe";
	public static WebDriver driver;
	public HomePage homePage;
	public LoginPage loginPage;
	public Xls_Reader xlReader;

	public BaseTest() {
		try {
			DOMConfigurator.configure("log4j.xml");
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/crm" + "/qa/config/config.properties");
			prop.load(ip);
			xlReader = new Xls_Reader();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@BeforeClass
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			/*System.setProperty("webdriver.chrome.driver", chromeDriver);
			driver = new ChromeDriver();*/
			WebDriverManager.chromedriver().version("108.0.5359.71").setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized"); 
			options.addArguments("enable-automation"); 
			options.addArguments("--no-sandbox"); 
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation"); 
			options.addArguments("--disable-gpu"); 
			String WebdriverTimeout=prop.getProperty("WebdriverTimeout"); 
			 driver = new ChromeDriver(); 
			long wedriverTimeout=Long.parseLong(WebdriverTimeout);
			driver.manage().timeouts().implicitlyWait(wedriverTimeout,  TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		   
		} else if (browserName.equals("IE")) {
			DesiredCapabilities returnCapabilities = DesiredCapabilities.internetExplorer();
			returnCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
					true);
			returnCapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			//WebDriverManager.iedriver().version("11.0").setup();
			System.setProperty("webdriver.ie.driver", ieDriver);
			driver = new InternetExplorerDriver(returnCapabilities);
		}
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		String WebdriverTimeout=prop.getProperty("WebdriverTimeout");  
		long wedriverTimeout=Long.parseLong(WebdriverTimeout);
		driver.manage().timeouts().implicitlyWait(wedriverTimeout,  TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));

	}

	@BeforeMethod
	public void methodLevelSetup() {
		homePage = new HomePage();
		loginPage = new LoginPage();
	}

	@AfterClass
	public void teardown() throws Exception {
		driver.quit();
		
	}
	@AfterSuite
	public void killDrivers() throws Exception{
		Runtime.getRuntime().exec("taskkill /F /IM chromeDriver.exe");
		 Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		System.out.println("Killing chrome driver process...");
		System.out.println("Killing IE driver process...");
	}

}
