package com.automation.pages;

import com.automation.base.BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CICDPage extends BasePOMPage {
	
	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView'and @text='Hello World!']")
	private AndroidElement HelloWorldTxt;
	
	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView'and @text='CICDPipeLine1']")
	private AndroidElement CICDPipeLineTxt;

	public CICDPage(AppiumDriver appiumDriver) {
		super(appiumDriver);
	}
	
	public String verifyHelloWorldTxt() {
		return HelloWorldTxt.getText();
	}
	
	public String verifyCICDPipeLineTxt() {
		return CICDPipeLineTxt.getText();
	}
}
