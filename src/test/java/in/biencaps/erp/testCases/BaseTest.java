package in.biencaps.erp.testCases;

import java.io.*;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.testng.*;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.*;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;

import in.biencaps.erp.utilities.*;
import io.restassured.RestAssured;

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
	public Connection connection;

	@BeforeSuite
	public void launch_The_Website() throws Exception {
		try {
			driver_Setup();
		} catch (Exception e) {
			log.error("Window is not available" + "\n");
			driver.quit();
		}
	}

	public void driver_Setup() throws Exception {
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

		if (Constants.environment.equalsIgnoreCase("test")) {
			driver.get(Constants.websiteUrlOnTestEnvironemnt);
		} else {
			driver.get(Constants.websiteUrlOnStagingEnvironemnt);
		}
		log.info("Chrome browser launched");

		driver.manage().window().maximize();
		log.info("Browser window maximized" + "\n");
	}

	public void setup_Backend_API() {
		RestAssured.baseURI = Constants.backendUrl;
		log.info("Connected to backend API" + "\n");
	}

	public void setup_Database() throws Exception {
		// Connect to the database
		String url = Constants.databaseUrl;
		String user = Constants.databaseUserName;
		String password = Constants.databasePassword;
		connection = DriverManager.getConnection(url, user, password);
		log.info("Connected to database" + "\n");
	}

	@BeforeTest
	public void extent_Report_Setup() {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "Reports"
				+ File.separator + Constants.extentReportHTMLFileName);
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.STANDARD);
		extent.setSystemInfo("HostName", Constants.extentReportHostName);
		extent.setSystemInfo("UserName", Constants.extentReportPassword);
		sparkReporter.config().setDocumentTitle(Constants.extentReportDocumentTitle);
		sparkReporter.config().setReportName(Constants.extentReportName);
	}

	@BeforeMethod
	public void create_Extent_Report_For_Each_Test_Method(Method testMethod) {
		logger = extent.createTest(testMethod.getName());
	}

	@AfterMethod
	public void test_Method_Exection_Result(ITestResult result) {
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

	@AfterSuite
	public void tear_Down() throws InterruptedException {
		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.quit();
			log.info("All browser closed" + "\n");
		} else {
			log.info("Browser is still opened" + "\n");
		}
		extent.flush();
	}
}
