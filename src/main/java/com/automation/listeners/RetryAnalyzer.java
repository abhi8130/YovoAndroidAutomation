package com.automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	// retry listener execute only for failed testcases - IRetryAnalyzer
	public boolean retry(ITestResult result) {
		int retryCount = 0;
		int maxRetryLimit = 3;

		if (retryCount <= maxRetryLimit) {
			System.out.println("Failed testcase: " + result.getName() + "with status "
					+ getResultStatusName(result.getStatus()) + "for the " + (retryCount + 1) + "time(s)");
			retryCount++;
		}
		return false;
	}

	public String getResultStatusName(int status) {
		String resultName = null;
		if (status == 1)
			resultName = "SUCCESS";
		if (status == 2)
			resultName = "FAILURE";
		if (status == 3)
			resultName = "SKIP";
		return resultName;
	}
}
