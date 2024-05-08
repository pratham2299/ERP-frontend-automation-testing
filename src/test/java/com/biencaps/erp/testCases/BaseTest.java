package com.biencaps.erp.testCases;

import java.io.*;
import java.lang.reflect.Method;

import java.util.*;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.testng.*;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.biencaps.erp.utilities.*;
import com.github.javafaker.Faker;

public class BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static final Logger log = LogManager.getLogger(BaseTest.class);

	public static WebDriver driver;
	public static Faker faker;
	public static Random random;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public static ExtentTest logger;

	@BeforeSuite
	public void launchTheWebsite() throws Exception {
		driverSetUp();
	}

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

		try {
			driver.get(Constants.websiteUrl);
			log.info("Chrome browser launched");

			driver.manage().window().maximize();
			log.info("Browser window maximized" + "\n");
		} catch (NoSuchWindowException e) {
			log.error("Window is not available" + "\n");
			driver.quit();
		}
	}

	@BeforeTest
	public void extentReportSetup() {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "Reports"
				+ File.separator + "ERP Extent Reports.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.STANDARD);
		extent.setSystemInfo("HostName", "RHEL8");
		extent.setSystemInfo("UserName", "root");
		sparkReporter.config().setDocumentTitle("ERP Automation Testing Report");
		sparkReporter.config().setReportName("ERP Autoamtion Test Exection Results By Prathamesh Dhasade");
	}

	@BeforeMethod
	public void createExtentReportForEachTestMethod(Method testMethod) {
		logger = extent.createTest(testMethod.getName());
	}

	@AfterMethod
	public void testMethodExectionResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else {
			logger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
		}
	}

	@AfterTest
	public void flushExtentReport() {
		extent.flush();
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
//		extent.flush();
	}
}
