package com.crm.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		testUtil = new TestUtil();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String homePageTitle =homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO", "HomePage Title not matched");
	}
	
	@Test(priority = 2)
	public void verifyUserNametest() {
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());		
	}
	
	@Test(priority = 3)
	public void verifyContactsLinkText() {
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
