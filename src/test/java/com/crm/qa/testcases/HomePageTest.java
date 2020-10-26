package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	TasksPage tasksPage;
	TestUtil testUtil;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil = new TestUtil();
	}
	
	@Test(priority = 1)
	public void validateHomePageTitleTest() {
		String homeTitle = homePage.homePageTitle();
		Assert.assertEquals(homeTitle, "CRMPRO","Home Page Title not matched");
	}
	
	@Test(priority = 2)
	public void validateUserNameLabelTest() {
		testUtil.switchToMainPanelFrame();
		Assert.assertTrue(homePage.verifyUserName());
		
	}
	
	@Test(priority = 3)
	public void validateContactsLinkTest() {
		testUtil.switchToMainPanelFrame();
		contactsPage = homePage.clickOnContactsLink();
		
	}
	
	@Test(priority = 4)
	public void validateDealsLinkTest() {
		testUtil.switchToMainPanelFrame();
		dealsPage = homePage.clickOnDealsLink();
		
	}
	
	@Test(priority = 5)
	public void validateTasksLinkTest() {
		testUtil.switchToMainPanelFrame();
		tasksPage = homePage.clickOnTasksLink();
		
	}
	
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
