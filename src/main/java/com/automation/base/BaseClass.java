package com.automation.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.automation.config.ConfigProperties;
import com.automation.util.TestUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseClass {
	private static BaseClass classInstance = null;
	protected static AppiumDriverLocalService service;
	public static String mDirpath = System.getProperty("user.dir");
	public static String mApkfilepath = mDirpath + "/app/quizu_release.apk";
	protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected static ThreadLocal<String> platform = new ThreadLocal<String>();
	protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();
	protected static ThreadLocal<String> deviceName = new ThreadLocal<String>();
	static TestUtil utils = new TestUtil();

	public static AppiumDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(AppiumDriver driver2) {
		driver.set(driver2);
	}

	public static void removeDriver() {
		if (driver != null) {
			driver.get().quit();
			driver.remove();
		}
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public static void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	public String getPlatform() {
		return platform.get();
	}

	public static void setPlatform(String platform2) {
		platform.set(platform2);
	}

	public String getDeviceName() {
		return deviceName.get();
	}

	public static void setDeviceName(String deviceName2) {
		deviceName.set(deviceName2);
	}

	/*
	 * public BaseClass() { PageFactory.initElements(new
	 * AppiumFieldDecorator(getDriver()), this); }
	 */

	public static void createInstance(String url, String platformName, String deviceName)
			throws AppiumServerHasNotBeenStartedLocallyException, Exception {

		AppiumDriver driver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();

		setDateTime(utils.dateTime());
		setPlatform(platformName);
		setDeviceName(deviceName);

		String strFile = "logs" + File.separator + platformName + "_" + deviceName;
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		// route logs to separate file for each thread
		ThreadContext.put("ROUTINGKEY", strFile);
		utils.log().info("log path: " + strFile);

		//capabilities.setCapability(MobileCapabilityType.UDID, udid);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.APP, "app/build/outputs/apk/release/app-release.apk");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 15);
		//capabilities.setCapability("appPackage", ConfigProperties.getDataProperties("androidAppPackage"));
		//capabilities.setCapability("appActivity", ConfigProperties.getDataProperties("androidAppActivity"));
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("skipUnlock", true);
		// capabilities.setCapability( "noReset", true);
		// capabilities.setCapability("fullReset", false);
		try {
			driver = new AndroidDriver(new URL(url), capabilities);
			setDriver(driver);
			System.out.println("Driver is now set with threadlocal");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @AfterMethod(alwaysRun = true) public void getResult(ITestResult result)
	 * throws Exception { if (result.getStatus() == ITestResult.FAILURE) {
	 * logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() +
	 * " - Test Case Failed", ExtentColor.RED)); logger.log(Status.FAIL,
	 * MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed",
	 * ExtentColor.RED));
	 * 
	 * String screenshotPath = TestUtil.captureScreenAsBase64(getDriver(),
	 * result.getName()); logger.fail("Snapshot below: " +
	 * logger.addScreenCaptureFromPath(screenshotPath));
	 * 
	 * } else if (result.getStatus() == ITestResult.SKIP) { logger.log(Status.SKIP,
	 * MarkupHelper.createLabel(result.getName() + " - Test Case Skipped",
	 * ExtentColor.ORANGE)); } else if (result.getStatus() == ITestResult.SUCCESS) {
	 * logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() +
	 * " Test Case PASSED", ExtentColor.GREEN)); } extent.flush(); }
	 * 
	 * public void LOGWithScreenshot(ITestResult result) throws IOException,
	 * InvalidFormatException { String Base64StringofScreenshot = ""; File src =
	 * ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); byte[]
	 * fileContent = FileUtils.readFileToByteArray(src); Base64StringofScreenshot =
	 * "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
	 * if (result.getStatus() == ITestResult.SUCCESS) logger.pass(" Screenshot" +
	 * "\n" + logger.addScreenCaptureFromPath(Base64StringofScreenshot)); else
	 * logger.fail("Screenshot" + "\n" +
	 * logger.addScreenCaptureFromPath(Base64StringofScreenshot)); }
	 */
}
