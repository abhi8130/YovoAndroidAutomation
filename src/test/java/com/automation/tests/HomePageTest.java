package com.automation.tests;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.automation.base.BaseClass;
import com.automation.listeners.TestAllureListener;
import com.automation.listeners.TestNGlisteners;
import com.automation.pages.HomePage;
import com.automation.util.TestUtil;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

//@Listeners({ TestAllureListener.class,TestNGlisteners.class})
public class HomePageTest extends BaseClass {

	HomePage homePage;

	public HomePageTest() {
		super();
	}

	@BeforeClass
	@Parameters({ "platformName", "url"})
	public void setUpHomePageClass(String platformName, String url) throws Exception {
		try {
			BaseClass baseClass = new BaseClass();
			baseClass.initialize_driver(platformName, url);
			homePage = new HomePage(driver);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 1, description = "Verify element i.e Top50 Txt on homepage test")
	@Severity(SeverityLevel.NORMAL)
	@Description("TestCase Description: Verify element i.e Top50 Txt on homepage")
	public void verifyeElementsOnHomePageTest() throws Exception {
		log.info("***Executing verifyElementsOnHomeScreenTest***");
		logger = extent.createTest("Verify the elements on HomePage after redirecting to the splash screen");
		if (homePage.isMelvinIntroCloseBtnDisplayed() == true)
			homePage.clickMelvinIntroCloseBtn();
		else
			System.out.println("MelvinIntroCloseBtn is not displayed");
		//log.info("wait for continue_button to be clickable");
		//TestUtil.waitForElementToBeClickable(By.id("continue_button"));
		//homePage.clickContinueBtnAfterSplashScreen();
		//log.info("Clicked on continue_button");
		//	homePage.clickBackBtn();
		log.info("waitForUserNameToBeClickable - username");
		boolean flag = homePage.validateTop50Txt();
		Assert.assertTrue(flag);
		log.info("Top50Txt isDisplayed");
		log.info("verifyElementsonHomeScreenTest Ended");
	}

	
	  @Test(priority = 2, description = "Swipe to next video test")	 
	  @Severity(SeverityLevel.NORMAL)  
	  @Description("TestCase Description: Swipe from one video to another") public
	  void swipeToNxtVideoTest() throws InterruptedException { try { logger =
	  extent.createTest("Swipe from one video to another & get the username ");
	  log.info("***Executing swipeToNxtVideoTest***");
	  //log.info("waitForElementToPresenceOfElementLocated - username");
	  //TestUtil.waitForElementToPresenceOfElementLocated(By.id("user_name"));
	  log.info("swipeverticalDown for nxt video"); TestUtil.swipeverticalDown();
	  log.info("swipeToNxtVideoTest Ended"); } catch (Exception e) {
	  e.printStackTrace(); log.error("Found Exception - swipeToNxtVideoTest"); }}
	 

	/*
	 * @Test(priority = 3, retryAnalyzer =
	 * com.automation.listeners.RetryAnalyzer.class ) public void checkFailure() {
	 * Assert.assertEquals(true, false); System.out.println("failed");
	 * 
	 * }
	 */

	@AfterClass
	public void quitDriver() {
		getDriver().quit();
	}

}