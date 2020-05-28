package com.automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.util.TestUtil;

public class TestNGlisteners implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("TestCase Started - " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("AfterTestSuccessfully Finished - " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("TestFailed - " + result.getName());	
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Skipped - " + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Failed but within success percentage" + result.getName());
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("This is onStart method" + context.getOutputDirectory());
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("This is onFinish method" + context.getPassedTests());
		System.out.println("This is onFinish method" + context.getFailedTests());
	}

	

}
