package com.automation.pages;

import com.automation.base.BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class QuizuPage extends BasePOMPage {
	
	@AndroidFindBy(id = "com.samnetworks.quizu:id/button0")
	private AndroidElement startBtn;
	
	@AndroidFindBy(id = "com.samnetworks.quizu:id/button0")
	private AndroidElement firstBtn;
	
	

	public QuizuPage(AppiumDriver appiumDriver) {
		super(appiumDriver);
	}
	
	public void clickStartBtn() {
		startBtn.click();
	}
	
	public void clickfirstBtn() {
		firstBtn.click();
	}
}
