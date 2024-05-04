package com.biencaps.erp.testCases;

import java.util.*;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
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
