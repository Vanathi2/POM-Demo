package com.crm.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;



public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtil testUtil;
	String sheetName = "Contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
		contactsPage = new ContactsPage();
		TestUtil.switchToMainPanelFrame();
		contactsPage = homePage.clickOnContactsLink();
		
	}
	
	@Test(priority = 1)
	public void contactsLabelTest() {
		
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts label is missing in the page");
	}
	
	@Test(priority = 2)
	public void selectContactsTest() {
		
		contactsPage.selectContactsByName("aa aaa");
		contactsPage.selectContactsByName("Aanuj D");  
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object testdata[][] = TestUtil.getTestData(sheetName);
		return testdata;
	}
	
	@Test(priority = 3, dataProvider = "getCRMTestData" )
	public void validateCreateNewContactTest(String title, String firstname, String surname, String company) {
		
		driver.navigate().refresh();
		TestUtil.switchToMainPanelFrame();
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title, firstname, surname, company);
		
	}
	
/*	@Test(priority = 3)
	public void validateCreateNewContactTest() {
		
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact("Mr.", "Smile", "vans", "Google");
	}
	
	*/
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
