package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class HomePage extends TestBase{
	
	//OR
	@FindBy(xpath = "//td[contains(text(),'User: Demo User')]")
	WebElement userNameLabel;
	
	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
		
	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContact;
	
	
	//PageFactory - init
	public HomePage() {
		PageFactory.initElements(driver, this);
		
	}
	
	public String homePageTitle() {
		return driver.getTitle();
	}
	
	public Boolean verifyUserName() {
		Boolean flag = userNameLabel.isDisplayed();
		return flag;
	}
	public String userNameLabel() {
		return userNameLabel.getText();
	}
	
	public ContactsPage clickOnContactsLink() {
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
		
	}
	
	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		return new TasksPage();
		
	}
	
	public void clickOnNewContactLink() {
		
	//	TestUtil.clickable(driver,contactsLink,20);
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContact.click();
		
	}
	
	
}
