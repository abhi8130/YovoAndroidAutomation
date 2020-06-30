package com.automation.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
import com.automation.pages.HomePage;
import com.automation.reports.ExtentReport;
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
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

//@Listeners({ TestAllureListener.class,TestNGlisteners.class})
public class HomePageTest extends BaseClass {
	HomePage homePage;
	TestUtil utils;

	public HomePageTest() {
		super();
	}

	@Parameters({"port", "platformName", "deviceName" })
	@BeforeMethod
	public void prepareTest(int port, String platformName, String deviceName) {
		try {
			BaseClass.createInstance(port, platformName, deviceName);
		} catch (AppiumServerHasNotBeenStartedLocallyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		homePage = new HomePage(BaseClass.getDriver());
		utils = new TestUtil();
		utils.startRecordingVideo();
		System.out.println("Currrent thread id: " + Thread.currentThread().getId());
	}

	@Test(priority = 1)
	public void testmethod() {
		System.out.println("Enter testMethod");
		utils.log("---Enter testmethod---");
		BaseClass.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		if (homePage.isMelvinIntroCloseBtnDisplayed() == true)
			homePage.clickMelvinIntroCloseBtn();
		else
			System.out.println("MelvinIntroCloseBtn is not displayed");

		boolean flag = homePage.validateTop50Txt();
		Assert.assertTrue(flag);
	}

	/*
	 * @Test(priority = 2) public void swipeToNxtVideoTest() throws
	 * InterruptedException { try { System.out.println("Enter SwipeMethod");
	 * utils.log("***Enter SwipeMethod***");
	 * BaseClass.getDriver().manage().timeouts().implicitlyWait(5000,
	 * TimeUnit.MILLISECONDS); if (homePage.isMelvinIntroCloseBtnDisplayed() ==
	 * true) homePage.clickMelvinIntroCloseBtn(); else
	 * System.out.println("MelvinIntroCloseBtn is not displayed");
	 * 
	 * Thread.sleep(3000); Dimension size =
	 * BaseClass.getDriver().manage().window().getSize(); System.out.println("Size"
	 * + size); int height = size.getHeight(); int width = size.getWidth(); int x =
	 * width / 2; int top_y = (int) (height * 0.80); int bottom_y = (int) (height *
	 * 0.20); TouchAction ts = new TouchAction(BaseClass.getDriver());
	 * ts.longPress(PointOption.point(x, top_y)).moveTo(PointOption.point(x,
	 * bottom_y)).release().perform(); } catch (Exception e) { e.printStackTrace();
	 * System.out.println("Found Exception - swipeToNxtVideoTest"); } }
	 */

	
	/*
	 * @Test(priority = 3) public void clickOnPrivacyPolicy() throws
	 * InterruptedException {
	 * BaseClass.getDriver().manage().timeouts().implicitlyWait(5000,
	 * TimeUnit.MILLISECONDS); if (homePage.isMelvinIntroCloseBtnDisplayed() ==
	 * true) homePage.clickMelvinIntroCloseBtn(); else
	 * System.out.println("MelvinIntroCloseBtn is not displayed");
	 * 
	 * homePage.clickOnProfileLayoutFromNavigationBar();
	 * homePage.clickPrivacyPolicyTxt(); Thread.sleep(4000); Dimension size =
	 * BaseClass.getDriver().manage().window().getSize(); System.out.println("Size"
	 * + size); int height = size.getHeight(); int width = size.getWidth(); int x =
	 * width / 2; int top_y = (int) (height * 0.80); int bottom_y = (int) (height *
	 * 0.20); TouchAction ts = new TouchAction(BaseClass.getDriver());
	 * ts.longPress(PointOption.point(x, top_y)).moveTo(PointOption.point(x,
	 * bottom_y)).release().perform(); Thread.sleep(3000); }
	 */
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		utils.stopRecordingVideo(result);
		System.out.println("Tear Down Driver");
		BaseClass.removeDriver();	}

}