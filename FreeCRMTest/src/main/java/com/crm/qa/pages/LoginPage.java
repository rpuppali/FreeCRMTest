package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.BaseTest;

public class LoginPage extends BaseTest{
	
	//Page Factory - OR:
	@FindBy(xpath="//input[@id='ctl00_MainContent_username']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='ctl00_MainContent_password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='ctl00_MainContent_login_button']")
	WebElement loginBtn;
	
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		System.out.println("Weborders....Title "+driver.getTitle());
		return driver.getTitle();
	}
	
	 /**Page Methods*/
    public LoginPage loginToApp(String username1, String password1) {
    	username.sendKeys(username1);
		password.sendKeys(password1);
		loginBtn.click();
        return this;
    }
	
}
