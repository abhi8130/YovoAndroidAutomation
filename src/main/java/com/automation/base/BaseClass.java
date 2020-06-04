package com.automation.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import javax.naming.spi.DirStateFactory.Result;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass {
	public static AndroidDriver<MobileElement> driver;
	public static Properties prop;
	public static String mDirpath;
	public static String mApkfilepath;
	public static AppiumDriverLocalService appiumService;
	public static String appiumServiceUrl;
	private static ThreadLocal<AndroidDriver<MobileElement>> tdriver = new ThreadLocal<AndroidDriver<MobileElement>>();
	public static AppiumDriverLocalService server;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static Logger log;
	public static String userName = "abhishekchauhan13";
	public static String accessKey = "rhHsE7Vd1a61TWpPGxad";
	public static DesiredCapabilities capabilities = new DesiredCapabilities();

	public static void setDriver(AndroidDriver<MobileElement> driver) {
		tdriver.set(driver);
	}

	public static synchronized AndroidDriver<MobileElement> getDriver() {
		return tdriver.get();
	}

	public BaseClass() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/automation/config/config.properties");
			prop.load(ip);

			// extend reports
			Date date = new Date();
			SimpleDateFormat dateFormatFolder = new SimpleDateFormat("dd_MMM_yyyy");
			File ResultDir = new File(System.getProperty("user.dir") + File.separator + "/FrameworkReports/"
					+ dateFormatFolder.format(date));
			// Defining Directory/Folder Name
			if (!ResultDir.exists()) { // Checks that Directory/Folder Doesn't Exists!
				ResultDir.mkdir();
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ssaa");
			htmlReporter = new ExtentHtmlReporter(
					ResultDir + "/" + "Report" + " " + dateFormat.format(date) + " .html");
			htmlReporter.config().setDocumentTitle("Automation Report");
			htmlReporter.config().setReportName("YOVO AUTOMATION");
			htmlReporter.config().setTheme(Theme.DARK);

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host Name", "localhost");
			extent.setSystemInfo("Environment", "Windows 7");
			extent.setSystemInfo("User Name", "Abhishek Chauhan");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize_driver(String platformName, String deviceName) throws Exception {

		log = LogManager.getLogger(BaseClass.class);
		BasicConfigurator.configure();

		File appDir = new File("src");
		File app = new File(appDir, "yovoapp-release.apk");
		mDirpath = System.getProperty("user.dir");
		mApkfilepath = mDirpath + "/app/Melvin app-release.apk";

		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		// capabilities.setCapability(MobileCapabilityType.UDID, udid);

		switch (platformName) {
		case "Android":

			/*
			 * // capabilities to run locally
			 * capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
			 * capabilities.setCapability("appPackage",
			 * prop.getProperty("androidAppPackage"));
			 * capabilities.setCapability("appActivity",
			 * prop.getProperty("androidAppActivity"));
			 * capabilities.setCapability("deviceName", "ASUS"); //
			 * capabilities.setCapability("app", mApkfilepath);
			 * capabilities.setCapability("unicodeKeyboard", true);
			 * capabilities.setCapability("resetKeyboard", true);
			 * capabilities.setCapability("newCommandTimeout", "15");
			 * capabilities.setCapability("autoGrantPermissions", true);
			 * capabilities.setCapability("noReset", false); driver = new
			 * AndroidDriver<MobileElement>(new URL(url), capabilities);
			 */

			/*
			 * // pCloudy capabilities capabilities.setCapability("pCloudy_Username",
			 * "abhishek@totalitycorp.com"); capabilities.setCapability("pCloudy_ApiKey",
			 * "djqfdcg5g36qbtj2pz93tsd2");
			 * capabilities.setCapability("pCloudy_DurationInMinutes", 10);
			 * capabilities.setCapability("newCommandTimeout", 600);
			 * capabilities.setCapability("launchTimeout", 90000);
			 * capabilities.setCapability("pCloudy_DeviceFullName",
			 * "ONEPLUS_5_Android_9.0.0"); capabilities.setCapability("platformVersion",
			 * "9.0.0"); capabilities.setCapability("platformName", "Android");
			 * capabilities.setCapability("automationName", "uiautomator2");
			 * capabilities.setCapability("pCloudy_ApplicationName",
			 * "New_Design_YovoDanceStar.apk"); capabilities.setCapability("appPackage",
			 * "com.getyovo"); capabilities.setCapability("appActivity",
			 * "com.mgpl.videos.activity.SplashActivity");
			 * capabilities.setCapability("pCloudy_WildNet", "false"); driver = new
			 * AndroidDriver<MobileElement>(new URL(url), capabilities);
			 */

			// browserstack capabilities
			capabilities.setCapability("platformName", platformName);
			capabilities.setCapability("device", deviceName);
			capabilities.setCapability("os_version", "9.0");
			capabilities.setCapability("app", "bs://1fc946bbd8e20df327e8f2a3b7b87ed270e17695");
			capabilities.setCapability("automationame", "uiautomator2");
			capabilities.setCapability("projectName", "Yovo Android Automtaion");
			capabilities.setCapability("buildName", "New_Design_YovoDanceStar.apk");
			capabilities.setCapability("realMobile", "true");
			capabilities.setCapability("appPackage", "com.getyovo");
			capabilities.setCapability("appActivity", "com.mgpl.videos.activity.SplashActivity");
			driver = new AndroidDriver<MobileElement>
			(new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"),
					capabilities);
			break;

		case "IOS":
			File classpathRoot = new File(System.getProperty("user.dir"));
			// File appDir = new File(classpathRoot, "/build/");
			// File app = new File(appDir, "WordPress.app");
			capabilities.setCapability("platformVersion", "9.2");
			capabilities.setCapability("deviceName", "iPhone 6");
			capabilities.setCapability("app", app.getAbsolutePath());
			// driver = new IOSDriver<MobileElement>(new
			// URL("http://127.0.0.1:4723/wd/hub"), caps);
			break;
		default:
			throw new Exception("Invalid platform! - " + platformName);
		}
		setDriver(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			String screenshotPath = TestUtil.captureScreenAsBase64(driver, result.getName());
			logger.fail("Snapshot below: " + logger.addScreenCaptureFromPath(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		}
		extent.flush();
	}

	public void LOGWithScreenshot(ITestResult result) throws IOException, InvalidFormatException {
		String Base64StringofScreenshot = "";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(src);
		Base64StringofScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
		if (result.getStatus() == ITestResult.SUCCESS)
			logger.pass(" Screenshot" + "\n" + logger.addScreenCaptureFromPath(Base64StringofScreenshot));
		else
			logger.fail("Screenshot" + "\n" + logger.addScreenCaptureFromPath(Base64StringofScreenshot));
	}
}
