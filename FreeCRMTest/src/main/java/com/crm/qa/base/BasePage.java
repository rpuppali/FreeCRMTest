package com.crm.qa.base;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public long timeout;
 
    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,timeout);
    }
}
