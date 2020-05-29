package com.automation.tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.automation.base.BaseClass;
import com.automation.pages.HomePage;
import com.automation.pages.UserProfilePage;
import com.automation.util.TestUtil;

public class UserProfilePageTest extends BaseClass {

	UserProfilePage userProfilePage;
	HomePage homePage;

	public UserProfilePageTest() {
		super();
	}

	@BeforeClass
	@Parameters({ "platformName", "url"})
	public void setUpUserProfileClass(String platformName, String url, String udid) throws Exception {
		try {
			initialize_driver(platformName, url);
			userProfilePage = new UserProfilePage(driver);
			homePage = new HomePage(driver);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	public void verifyUserNameOnProfilePageTest() {
		try {
			// homePage.clickContinueBtnAfterSplashScreen();
			log.info("*** Executing verifyUserNameOnProfilePageTest***");
			log.info("waitForElementToBeClickable - user_name ");
			TestUtil.waitForElementToBeClickable(By.id("user_name"));
			log.info("getUserNamefromHomeScreen");
			String usernameOnHomeScreeen = homePage.getUserNamefromHomePage();
			userProfilePage = homePage.clickUserName();
			log.info("getUserNameOnUserProfile");
			String usernameOnProfile = userProfilePage.getUserNameOnUserProfile();
			Assert.assertEquals(usernameOnHomeScreeen, usernameOnProfile);
			log.info("Assertion passed - username");
			log.info("clickOnBackBtn");
			userProfilePage.clickOnBackBtn();
			boolean flag = userProfilePage.verifySendCoinsTxt();
			Assert.assertTrue(flag);
			log.info("Assertion Passed - SendCoinsTxt is displayed");
			log.info("validateuserNameOnProfilePageTest");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Found exception - verifyUserNameOnProfilePageTest ");
		}
	}

	@AfterClass
	public void tearDown() {
		getDriver().quit();
	}

}
