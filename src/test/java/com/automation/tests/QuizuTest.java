package com.automation.tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.base.BaseClass;
import com.automation.pages.LoginPage;
import com.automation.pages.QuizuPage;

public class QuizuTest extends BaseClass {

	QuizuPage quizuPage;
	
	public QuizuTest() {
		super();
	}

	@BeforeClass
	//@Parameters({ "udid", "port", "platformName", "deviceName" })
	@Parameters({ "url", "platformName", "deviceName" })
	public void setUpQuizuPageClass(String url, String platformName, String deviceName) throws Exception {
		try {
			BaseClass.createInstance(url, platformName, deviceName);
			quizuPage = new QuizuPage(BaseClass.getDriver());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 1)
	public void testmethod() throws InterruptedException {
		quizuPage.clickStartBtn();
		System.out.println("Executed testMethod on Quizu app");
	}
	
	@AfterClass
	public void quitDriver() {
		getDriver().quit();
	}
}
