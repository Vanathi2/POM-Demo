package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//OR - Page Factory

	@FindBy(name = "username")
	WebElement username;
	@FindBy(name = "password")
	WebElement password;
	@FindBy(xpath = "//input[@type = 'submit']")
	WebElement submit;
	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	@CacheLookup
	WebElement signup;
	@FindBy(xpath = "//img[contains(@class,'img-responsives')]")
	@CacheLookup
	WebElement crmlogo;
	
	//initialize OR using PageFactory.initElements  - all the variables will be initialized with driver
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public Boolean validateCRMImage() {
		return crmlogo.isDisplayed();
	}
	
	public HomePage login(String uname, String pwd) {
		username.sendKeys(uname);
		password.sendKeys(pwd);
		submit.click();
		return new HomePage();
		
	}
}
