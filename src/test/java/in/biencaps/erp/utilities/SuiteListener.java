package in.biencaps.erp.utilities;

import java.io.File;
import java.lang.reflect.*;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;

import in.biencaps.erp.testCases.BaseTest;

public class SuiteListener implements ITestListener, IAnnotationTransformer {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static final Logger log = LogManager.getLogger(SuiteListener.class);

	public void onTestStart(ITestResult result) {
		log.info("Test Started: " + result.getName() + "\n");
	}

	public void onTestSuccess(ITestResult result) {
		log.info("Test Passed: " + result.getName() + "\n");
	}

	public void onTestFailure(ITestResult result) {
		log.error("Test Failed: " + result.getName() + "\n");
		String fileName = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator
				+ result.getMethod().getMethodName();
		File file = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(file, new File(fileName + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		log.info("Test Skipped: " + result.getName() + "\n");
	}

	public void transform(ITestAnnotation annotation, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

	// Custom method to check if the browser is closed
	private boolean isBrowserClosed() {
		try {
			BaseTest.driver.getTitle(); // Perform any action to check if WebDriver is still alive
			return false; // WebDriver is still alive
		} catch (NoSuchWindowException e) {
			return true; // WebDriver session is closed
		}
	}

	public void onFinish(ITestContext context) {
		if (isBrowserClosed()) {
			System.out.println("Browser session has been closed unexpectedly.");
			BaseTest.driver.quit();
			BaseTest.extent.flush();
		}
	}
}
