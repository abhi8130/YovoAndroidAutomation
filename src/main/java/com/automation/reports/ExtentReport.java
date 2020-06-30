package com.automation.reports;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.automation.util.TestUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest logger;
	static Map<Integer, ExtentTest> extentTestMap = new HashMap();
	static TestUtil utils = new TestUtil();

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			/*
			 * ExtentHtmlReporter html = new ExtentHtmlReporter("Extent.html");
			 * html.config().setDocumentTitle("Appium Framework");
			 * html.config().setReportName("Yovo Android Automation");
			 * html.config().setTheme(Theme.DARK); extent = new ExtentReports();
			 * extent.attachReporter(html);
			 */
			File ResultDir = new File(
					System.getProperty("user.dir") + File.separator + "/FrameworkReports/" + utils.dateTime());
			// Defining Directory/Folder Name
			if (!ResultDir.exists()) { // Checks that Directory/Folder Doesn't Exists!
				ResultDir.mkdir();
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ssaa");
			htmlReporter = new ExtentHtmlReporter(ResultDir + "/" + "Report" + " " + utils.dateTime() + " .html");
			htmlReporter.config().setDocumentTitle("Automation Report");
			htmlReporter.config().setReportName("YOVO AUTOMATION");
			htmlReporter.config().setTheme(Theme.DARK);

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host Name", "localhost");
			extent.setSystemInfo("Environment", "ubuntu");
			extent.setSystemInfo("User Name", "Abhishek Chauhan");

		}

		return extent;
	}

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = getReporter().createTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}