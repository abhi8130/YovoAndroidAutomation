package com.automation.tests;

import java.net.MalformedURLException;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.automation.base.BaseClass;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.util.TestUtil;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseClass {

	LoginPage loginPage;
	String otp;

	public LoginPageTest() {
		super();
	}

	@BeforeClass
	@Parameters({ "platformName", "url"})
	public void setUpLoginPageClass(String platformName, String url, String udid) throws Exception {
		try {
			BaseClass baseClass = new BaseClass();
			baseClass.initialize_driver(platformName, url);
			loginPage = new LoginPage(driver);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 1, description = "Verify signUp/login into application using OTP")
	@Severity(SeverityLevel.NORMAL)
	@Description("TestCase Description: Verify signUp/login into application using OTP")
	public void loginUsingOtpTest() throws Exception {
		log.info("***Executing loginUsingOtpTest***");
		logger = extent.createTest("Verify signUp/login into application using OTP");
		loginPage.clickOnProfileLayoutFromNavigationBar();
		if (loginPage.isSignUpLoginTxtDisplayed() == true)
			loginPage.clickOnPlacehoderEnterYourMobileNumber();
		else
			System.out.println("bottom modal is not opened yet");
		TestUtil.waitForElementToPresenceOfElementLocated(By.id("com.google.android.gms:id/cancel"));
		loginPage.clickPhoneNumber();
		/*
		 * loginPage.clickNoneOftheAboveTxt(); boolean flag =
		 * loginPage.verifyContinueBtnOnSignUpLoginPage(); Assert.assertTrue(flag);
		 * loginPage.clickPhoneNumber();
		 * //loginPage.enterMobileNumber(prop.getProperty("mobileNumber"));
		 */ loginPage.clickContinueBtnOnSignUpLoginPage();
		TestUtil.waitForElementToPresenceOfElementLocated(
				By.xpath("//*[@class='android.widget.TextView' and @text='Resend in 45s']"));
		String getOTP = TestUtil.readOTP();
		TestUtil.closeNotificationTray();
		loginPage.enterOTP(getOTP);
		loginPage.clickOnContinueBtnOtpPage();
		TestUtil.waitForElementToPresenceOfElementLocated(By.id("com.getyovo:id/user_name"));
		log.info("loginUsingOtpTest Ended");
	}

	@Test(priority = 2, description = "Verify logout from the application")
	public void logoutTest() throws InterruptedException {
		logger = extent.createTest("Verify logout from the application");
		log.info("***Executing loginUsingOtpTest***");
		loginPage.clickOnProfileLayoutFromNavigationBar();
		if (loginPage.isCoinBalanceDisplayed() == true)
			loginPage.clickOptionsView();
		else
			log.info("Coin Balance is not found");
		loginPage.clickLogoutOption();
		loginPage.clickLogout();
		boolean isVisible = loginPage.isSendCoinTxtDisplayed();
		Assert.assertTrue(isVisible);
	}

	@AfterClass
	public void quitDriver() {
		getDriver().quit();
	}
}
