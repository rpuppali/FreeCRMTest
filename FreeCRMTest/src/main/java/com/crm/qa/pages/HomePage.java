package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.crm.qa.base.BaseTest;

public class HomePage extends BaseTest {


	@FindBy(xpath = "//a[@id='ctl00_logout']")
	WebElement logout;
	
	@FindBy(xpath = "//a[contains(text(),'Order')]")
	WebElement placeOrder;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_txtName']")
	WebElement customerName;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox2']")
	WebElement street;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox3']")
	WebElement city;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox4']")
	WebElement state;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox5']")
	WebElement zip;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_cardList_0']")
	WebElement card;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox6']")
	WebElement cardNum;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox1']")
	WebElement expDate;
	
	@FindBy(xpath = "//a[@id='ctl00_MainContent_fmwOrder_InsertButton']")
	WebElement process;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_txtQuantity']")
	WebElement qty;
	
	
	@FindBy(xpath = "//a[contains(text(),'View all orders')]")
	WebElement viewOrders;
	
	@FindBy(xpath = "//a[@id='ctl00_MainContent_btnCheckAll']")
	WebElement chekAll;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_btnDelete']")
	WebElement delete;
	
	@FindBy(xpath = "//select[@id='ctl00_MainContent_fmwOrder_ddlProduct']")
	WebElement product;
	
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle(){
		System.out.println("Home page ---"+driver.getTitle());
		Assert.assertEquals("Web Orders", "Web Orders sss");
		return driver.getTitle();
	}
	
	public void placeOrder(String CustomerName,String street1,String city1){
		viewOrders.click();
		chekAll.click();
		delete.click();
		placeOrder.click();
		Select se = new Select(product);
		se.selectByValue("FamilyAlbum");
		qty.sendKeys("2");
		customerName.sendKeys(CustomerName);
		street.sendKeys(street1);
		city.sendKeys(city1);
		state.sendKeys("Bangalore");
		zip.sendKeys("560056");
		card.click();;
		cardNum.sendKeys("12345");
		expDate.sendKeys("06/20");
		process.click();
		viewOrders.click();
		
	}
	
	 /**Page Methods*/
    public void logout() {
		logout.click();
    }
	
	
	
	
	
	
	
	

}
