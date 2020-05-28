package com.automation.tests;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.base.BaseClass;
import com.automation.pages.HomePage;
import com.automation.pages.Top50Page;
import com.automation.pages.UserProfilePage;
import com.automation.util.TestUtil;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class Top50PageTest extends BaseClass {

	Top50Page top50Page;
	UserProfilePage userProfilePage;
	HomePage homePage;

	public Top50PageTest() {
		super();
	}

	@BeforeMethod
	@Parameters({"platformName","url","udid"})
	public void setUpUserProfile(String platformName, String url, String udid) throws Exception {
		initialize_driver(platformName,url,udid);
		top50Page = new Top50Page(driver);
		userProfilePage = new UserProfilePage(driver);
		homePage = new HomePage(driver);
	}

	@Test
	public void scrollOnTop50TabAndClickFromListTest() {
		try {
			log.info("*** Executing scrollOnTop50Test***");
			log.info("waitForElementToBeClickable - Top 50_Text ");
			TestUtil.waitForElementToBeClickable(By.xpath("//*[@class='android.widget.TextView'and @text='Top 50']"));
			top50Page.clickTop50Txt();
			log.info("Clicked Top 50 Txt");
			TestUtil.waitForElementToBeClickable(By.id("user_profile_image"));
			log.info("clickElementsFromList");
			TestUtil.clickElementFromListByIndex("user_profile_image", 5);
			log.info("waitForElementToBeClickable - back_button ");
			TestUtil.waitForElementToBeClickable(By.id("back_button"));
			top50Page.clickOnBackBtn();
			boolean flag = top50Page.verifyTop50Txt();
			Assert.assertTrue(flag);
			log.info("scrollOnTop50Test Ended");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Found exception - scrollOnTop50TabAndClickFromListTest ");
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		// stop appium server
		// appiumService.stop();
	}

}
