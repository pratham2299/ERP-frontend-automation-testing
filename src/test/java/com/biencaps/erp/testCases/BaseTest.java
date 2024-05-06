package com.biencaps.erp.testCases;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.testng.*;
import org.testng.annotations.*;

import com.biencaps.erp.utilities.*;
import com.github.javafaker.Faker;

public class BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static final Logger log = LogManager.getLogger(BaseTest.class);

	public static WebDriver driver;
	public static Faker faker;
	public static Random random;

	@BeforeSuite
	public void driverSetUp() throws Exception {
		if (Constants.browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--ignore-certificate-errors");
			driver = new FirefoxDriver(options);
		} else if (Constants.browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--ignore-certificate-errors");
			driver = new ChromeDriver(options);
		} else if (Constants.browserName.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--ignore-certificate-errors");
			driver = new EdgeDriver(options);
		} else {
			log.info("Invalid browser name");
		}

		faker = new Faker();
		random = new Random();

		driver.get(Constants.websiteUrl);
		log.info("Chrome browser launched");

		driver.manage().window().maximize();
		log.info("Browser window maximized" + "\n");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
			captureScreenshot(result.getMethod().getMethodName());
		}
		// Quit WebDriver
		// driver.quit();
	}

	private void captureScreenshot(String methodName) {
		// Convert WebDriver to TakesScreenshot
		TakesScreenshot ts = (TakesScreenshot) driver;

		// Capture screenshot as File
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		// Define screenshot destination directory
		String destDir = "C:\\Users\\Lenovo\\eclipse-workspace\\ERP_Website_Automation_Testing\\Screenshots\\";

		// Create directory if it does not exist
		Path path = Paths.get(destDir);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Define screenshot destination path
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String destPath = destDir + methodName + "_" + timeStamp + ".png";

		// Copy screenshot file to destination path
		try {
			Files.copy(srcFile.toPath(), Paths.get(destPath));
			System.out.println("Screenshot captured: " + destPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.quit();
			log.info("All browser closed");
		} else {
			log.info("Browser is still opened");
		}
	}
}
