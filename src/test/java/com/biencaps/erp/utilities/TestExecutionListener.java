package com.biencaps.erp.utilities;

import org.testng.*;

import com.biencaps.erp.testCases.BaseTest;

public class TestExecutionListener implements ITestListener {
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Started: " + result.getName() + "\n");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed: " + result.getName() + "\n");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed: " + result.getName() + "\n");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName() + "\n");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Not implemented
	}

	@Override
	public void onStart(ITestContext context) {
		// Not implemented
	}

	@Override
	public void onFinish(ITestContext context) {
		// Not implemented
	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// Do nothing before method invocation
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// Check if the test method failed due to WebDriverException (browser forcefully
		// closed)
		if (testResult.getThrowable() != null
				&& testResult.getThrowable().getCause() instanceof org.openqa.selenium.WebDriverException) {
			// Stop the execution of TestNG XML by throwing SkipException
//			throw new org.testng.SkipException("Execution stopped due to browser forcefully closed.");
			BaseTest.driver.quit();
		}
	}
}
