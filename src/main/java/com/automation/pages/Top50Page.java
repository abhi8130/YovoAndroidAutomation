package com.automation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Top50Page extends BasePOMPage {

	@AndroidFindBy(id = "view_pager")
	private AndroidElement viewPagerToscroll;

	@AndroidFindBy(id = "back_button")
	private AndroidElement backBtn;

	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView'and @text='Top 50']")
	private AndroidElement top50Txt;

	public Top50Page(AndroidDriver<MobileElement> driver) {
		super(driver);
	}

	public void clickOnBackBtn() {
		backBtn.click();
	}

	public void clickTop50Txt() {
		top50Txt.click();
	}

	public boolean verifyTop50Txt() {
		return top50Txt.isDisplayed();
	}
}
