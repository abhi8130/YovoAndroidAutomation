package com.automation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class UserProfilePage extends BasePOMPage {

	@AndroidFindBy(id = "user_name")
	private AndroidElement userProfile;

	@AndroidFindBy(id = "back_button")
	private AndroidElement backBtn;

	@AndroidFindBy(id = "send_coin_text")
	private AndroidElement sendCoinTxt;

	public UserProfilePage(AndroidDriver<MobileElement> driver) {
		super(driver);
	}

	public String getUserNameOnUserProfile() {
		return userProfile.getText();
	}

	public void clickOnBackBtn() {
		backBtn.click();
	}

	public boolean verifySendCoinsTxt() {
		return sendCoinTxt.isDisplayed();
	}
}
