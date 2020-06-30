package com.automation.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import com.automation.base.BaseClass;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.connection.HasNetworkConnection;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestUtil extends BaseClass {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public static void waitForElementToBeClickable(By by) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 3000);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public static void waitForElementToPresenceOfElementLocated(By by) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	// open notifications tray
	public static String readOTP() throws Exception {
		((AndroidDriver<MobileElement>) getDriver()).openNotifications();
		try {
			/*
			 * Activity activity = new Activity("com.google.android.apps.messaging",
			 * "com.google.android.apps.messaging.ui.ConversationListActivity");
			 * ((AndroidDriver<MobileElement>) driver).startActivity(activity);
			 * System.out.println("OTP READ");
			 */

			String otp = ((AndroidDriver) getDriver())
					.findElementByXPath("//*[contains(@text,'Your login otp for YOVO')]").getText().split("is ")[1];
			System.out.println(otp);
			return otp;
		} catch (NoSuchElementException e) {
			throw new java.lang.Exception("Notification not found");
		}
	}

	// close notification tray
	public static void closeNotificationTray() {
		((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
	}

	public static void scroll(String resourceId, String Text) {
		try {
			MobileElement element = (MobileElement) getDriver().findElement(
					MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"resourceId\"))"
							+ ".setMaxSearchSwipes(3).scrollIntoView(" + "new UiSelector().text(\"Text\"))"));
			// return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void swipeverticalDown() {
		try {
			Dimension size = getDriver().manage().window().getSize();
			System.out.println("Size" + size);
			int height = size.getHeight();
			int width = size.getWidth();
			int x = width / 2;
			int top_y = (int) (height * 0.80);
			int bottom_y = (int) (height * 0.20);
			TouchAction ts = new TouchAction(getDriver());
			ts.longPress(PointOption.point(x, top_y)).moveTo(PointOption.point(x, bottom_y)).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void swipeverticalUp() {
		try {
			Dimension size = getDriver().manage().window().getSize();
			System.out.println("Size" + size);
			int height = size.getHeight();
			int width = size.getWidth();
			int x = width / 2;
			int top_y = (int) (height * 0.80);
			int bottom_y = (int) (height * 0.20);
			TouchAction ts = new TouchAction(getDriver());
			ts.longPress(PointOption.point(x, bottom_y)).moveTo(PointOption.point(x, top_y)).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clickElementFromListByIndex(String loc, int index) {
		List<MobileElement> elements = getDriver().findElementsById(loc);
		elements.get(index).click();
	}

	public static void clickElementFromListByText(By loc, String text) {
		try {
			List<MobileElement> list = getDriver().findElements(loc);
			for (int i = 0; i < list.size(); i++) {
				MobileElement values = list.get(i);
				if (values.getText().equalsIgnoreCase(text))
					values.click();
			}
		} catch (Exception e) {
			System.out.println("Exception Message: " + e.getMessage());
			System.out.println("Exception StackTrace: " + e.getStackTrace());
		}
	}

	/*
	 * MobileElement element = driver.findElement(MobileBy.AndroidUIAutomator(
	 * "new UiScrollable(new UiSelector().resourceId(\"com.getyovo:id/view_pager\")).scrollIntoView("
	 * + "new UiSelector().text(\"20\"))")); Thread.sleep(3000);
	 */

	public static void longPressOnElement(WebElement holdElement) {
		try {
			AndroidTouchAction t = new AndroidTouchAction(getDriver());
			t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(holdElement))
					.withDuration(Duration.ofMillis(10000))).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String captureScreenAsFile(WebDriver driver, String screenshotName) throws IOException {
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			// after execution, you could see a folder "FailedTestsScreenshots"
			// under src folder
			String destination = System.getProperty("user.dir") + File.separator + "\\FrameworkReports\\"
					+ screenshotName + dateName + ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			return destination;
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotName;
	}

	public static String captureScreenAsBase64(WebDriver driver, String screenshotName) throws IOException {
		try {
			TakesScreenshot newScreen = (TakesScreenshot) driver;
			String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
			return "data:image/jpg;base64, " + scnShot ;
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return screenshotName;
	}

	public static String captureFullScreeen(WebDriver driver, String screenShotName) throws Exception {
		try {
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			String destination = System.getProperty("user.dir") + "/FullPage_ErrorScreenshots/" + screenShotName
					+ ".png";
			ImageIO.write(screenshot.getImage(), "PNG", new File(destination));
			return destination;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenShotName;
	}

	public static void allowAppPermission() {
		try {
			while (getDriver().findElements(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).size() > 0) {
				getDriver().findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// enable & disable mobile data,wifi, airplane mode
	public static void turnOnWifi() {
		ConnectionState state = ((HasNetworkConnection) getDriver()).setConnection(new ConnectionStateBuilder()
                .withWiFiEnabled()
                .build());
	}
	
	//run app in background
	public static void runAppInBackground() {
		getDriver().runAppInBackground(Duration.ofSeconds(5));
	}
	
	public String dateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void log(String txt) {
		BaseClass base = new BaseClass();
		String msg = Thread.currentThread().getId() + ":" + base.getPlatform() + ":" + base.getDeviceName() + ":"
				+ Thread.currentThread().getStackTrace()[2].getClassName() + ":" + txt;

		System.out.println(msg);

		String strFile = "logs" + File.separator + base.getPlatform() + "_" + base.getDeviceName() + File.separator
				+ base.getDateTime();

		File logFile = new File(strFile);

		if (!logFile.exists()) {
			logFile.mkdirs();
		}

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(logFile + File.separator + "log.txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println(msg);
		printWriter.close();
	}

	public Logger log() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
	}

	public void startRecordingVideo() {
		try {
			((CanRecordScreen) getDriver()).startRecordingScreen();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Found exception at startRecordingVideo_method ");
		}
	}

	public void stopRecordingVideo(ITestResult result) {
		String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();

		Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
		String dirPath = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName")
				+ File.separator + getDateTime() + File.separator
				+ result.getTestClass().getRealClass().getSimpleName();

		File videoDir = new File(dirPath);

		synchronized (videoDir) {
			if (!videoDir.exists()) {
				videoDir.mkdirs();
			}
		}
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
			stream.write(Base64.decodeBase64(media));
			stream.close();
			log().info("video path: " + videoDir + File.separator + result.getName() + ".mp4");
		} catch (Exception e) {
			log().error("error during video capture" + e.toString());
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
