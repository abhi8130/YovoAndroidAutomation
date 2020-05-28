package com.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.base.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidBy;
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
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public static void waitForElementToPresenceOfElementLocated(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	// open notifications tray
	public static String readOTP() throws Exception {
		((AndroidDriver<MobileElement>) driver).openNotifications();
		try {
			/*
			 * Activity activity = new Activity("com.google.android.apps.messaging",
			 * "com.google.android.apps.messaging.ui.ConversationListActivity");
			 * ((AndroidDriver<MobileElement>) driver).startActivity(activity);
			 * System.out.println("OTP READ");
			 */

			String otp = ((AndroidDriver<MobileElement>) driver)
					.findElementByXPath("//*[contains(@text,'Your login otp for YOVO')]").getText().split("is ")[1];
			System.out.println(otp);
			return otp;
		} catch (NoSuchElementException e) {
			throw new java.lang.Exception("Notification not found");
		}
	}

	// close notification tray
	public static void closeNotificationTray() {
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
	}

	public static void scroll(String resourceId, String Text) {
		try {
			MobileElement element = driver.findElement(
					MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"resourceId\"))"
							+ ".setMaxSearchSwipes(3).scrollIntoView(" + "new UiSelector().text(\"Text\"))"));
			// return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void swipeverticalDown() {
		try {
			Dimension size = driver.manage().window().getSize();
			System.out.println("Size" + size);
			int height = size.getHeight();
			int width = size.getWidth();
			int x = width / 2;
			int top_y = (int) (height * 0.80);
			int bottom_y = (int) (height * 0.20);
			TouchAction ts = new TouchAction(driver);
			ts.longPress(PointOption.point(x, top_y)).moveTo(PointOption.point(x, bottom_y)).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void swipeverticalUp() {
		try {
			Dimension size = driver.manage().window().getSize();
			System.out.println("Size" + size);
			int height = size.getHeight();
			int width = size.getWidth();
			int x = width / 2;
			int top_y = (int) (height * 0.80);
			int bottom_y = (int) (height * 0.20);
			TouchAction ts = new TouchAction(driver);
			ts.longPress(PointOption.point(x, bottom_y)).moveTo(PointOption.point(x, top_y)).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clickElementFromListByIndex(String loc, int index) {
		List<MobileElement> elements = driver.findElementsById(loc);
		elements.get(index).click();
	}

	public static void clickElementFromListByText(By loc, String text) {
		try {
			List<MobileElement> list = driver.findElements(loc);
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
			AndroidTouchAction t = new AndroidTouchAction(driver);
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
			while (driver.findElements(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).size() > 0) {
				driver.findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
