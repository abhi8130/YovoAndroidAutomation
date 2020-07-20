package com.automation.tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.base.BaseClass;
import com.automation.pages.CICDPage;
import com.automation.pages.LoginPage;
import com.automation.pages.QuizuPage;

public class CICDTest extends BaseClass {

	CICDPage ciCDPage;
	
	public CICDTest() {
		super();
	}

	@BeforeClass
	//@Parameters({ "udid", "port", "platformName", "deviceName" })
	@Parameters({ "url", "platformName", "deviceName" })
	public void setUpCICDPageClass(String url, String platformName, String deviceName) throws Exception {
		try {
			BaseClass.createInstance(url, platformName, deviceName);
			ciCDPage = new CICDPage(BaseClass.getDriver());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 1)
	public void validateHelloWorldTxt() throws InterruptedException {
		String getText = ciCDPage.verifyHelloWorldTxt();
		Assert.assertEquals(getText, "Hello World!");
		System.out.println("getText from validateHelloWorldTxt() : " + getText);
	}
	
	@Test(priority = 2)
	public void validateCICDPipeLineTxt() throws InterruptedException {
		String getText = ciCDPage.verifyCICDPipeLineTxt();
		Assert.assertEquals(getText, "CICDPipeLine");
		System.out.println("getText from validateCICDPipeLineTxt() : " + getText);
	}
	
		
	@AfterClass
	public void quitDriver() {
		getDriver().quit();
	}
}
