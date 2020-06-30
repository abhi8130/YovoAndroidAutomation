package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class LoginPage extends BasePOMPage {

	// object repository login
	@AndroidFindBy(id = "layout5")
	private AndroidElement layout5_ProfileTab;

	@AndroidFindBy(id = "com.getyovo:id/bottom_view")
	private AndroidElement signUpLoginTxt;

	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView'and @text='Sign Up/Log In")
	private AndroidElement signUpLogInTxt;

	@AndroidFindBy(id = "com.getyovo:id/number_header")
	private AndroidElement enterPhoneNumber;

	@AndroidFindBy(id = "com.google.android.gms:id/cancel")
	private AndroidElement noneOftheAboveTxt;

	@AndroidFindBy(id = "com.getyovo:id/continue_button")
	private AndroidElement continueButtonOnSignUpLoginPage;

	@AndroidFindBy(id = "com.getyovo:id/mobile_number")
	private AndroidElement mobileNumberPlaceholder;

	@AndroidFindBy(id = "com.getyovo:id/show_number")
	private AndroidElement showNumber;

	@AndroidFindBy(id = "com.getyovo:id/otp_view")
	private AndroidElement otpView;

	@AndroidFindBy(id = "com.getyovo:id/continue_with_otp")
	private AndroidElement continueBtnOntopPage;

	@AndroidFindBy(id = "com.getyovo:id/user_name")
	private AndroidElement userNameOnProfilePage;

	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView' and @text='097735 44748']")
	private AndroidElement phoneNumber;

	// object repository logout
	@AndroidFindBy(id = "com.getyovo:id/options_view_scrolling")
	private AndroidElement optionsView;

	@AndroidFindBy(id = "com.getyovo:id/coin_balance")
	private AndroidElement coinBalance;

	@AndroidFindBy(id = "com.getyovo:id/logout")
	private AndroidElement logoutOption;

	@AndroidFindBy(id = "android:id/content")
	private AndroidElement logoutAccountPopUp;

	@AndroidFindBy(id = "com.getyovo:id/logout")
	private AndroidElement logoutTxt;

	@AndroidFindBy(id = "com.getyovo:id/send_coin_text")
	private AndroidElement sendCoinTxt;

	// initialize objects
	public LoginPage(AppiumDriver appiumDriver) {
		super(appiumDriver);
	}

	// log in actions
	public boolean isSignUpLoginTxtDisplayed() {
		try {
			return signUpLoginTxt.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isUsernameOnProfilePageDisplayed() {
		return userNameOnProfilePage.isDisplayed();
	}

	public void clickOnPlacehoderEnterYourMobileNumber() {
		enterPhoneNumber.click();
	}

	@Step("Click on Back button")
	public void clickOnProfileLayoutFromNavigationBar() {
		layout5_ProfileTab.click();
	}

	public void clickMobileNumberPlaceholder() {
		mobileNumberPlaceholder.click();
	}

	public void enterMobileNumber(String mobileNumber) {
		mobileNumberPlaceholder.sendKeys(mobileNumber);
	}

	public void clickNoneOftheAboveTxt() {
		noneOftheAboveTxt.click();
	}

	public boolean verifyContinueBtnOnSignUpLoginPage() {
		return continueButtonOnSignUpLoginPage.isDisplayed();
	}

	public void clickContinueBtnOnSignUpLoginPage() {
		continueButtonOnSignUpLoginPage.click();
	}

	public void enterOTP(String otp) {
		otpView.sendKeys(otp);
	}

	public void clickOnContinueBtnOtpPage() {
		continueBtnOntopPage.click();
	}

	public void clickPhoneNumber() {
		phoneNumber.click();
	}

	// logout actions
	public void clickOptionsView() {
		optionsView.click();
	}

	public void clickLogoutOption() {
		logoutOption.click();
	}

	public void clickLogout() {
		logoutTxt.click();
	}

	public boolean isSendCoinTxtDisplayed() {
		try {
			return sendCoinTxt.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isCoinBalanceDisplayed() {
		try {
			return coinBalance.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
