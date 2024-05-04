package com.biencaps.erp.testCases;

import static org.testng.Assert.*;

import org.apache.logging.log4j.*;

import com.biencaps.erp.pageObjectModels.*;
import com.biencaps.erp.utilities.Constants;

/* This class is extended BaseTest class.
 *  BaseTest has driver so you can inherite driver from BaseTest to this class
 */
public class LogoutFunctionality extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(LogoutFunctionality.class);

	protected MyTasksPage myTasks;
	protected DashboardPage dashboard;

	public void verifyLogOutEmployee() throws InterruptedException {
		// Passed driver get from BaseTest to my tasks page, dashboard page i.e. page
		// object model
		myTasks = new MyTasksPage(driver);
		dashboard = new DashboardPage(driver);

		dashboard.clickOnLogOutIcon();
//		Thread.sleep(2000);

		dashboard.clickOnLogOutButton();
		log.info("Logout successfully");
//		Thread.sleep(2000);

		String currentPageURLAfterLogOut = driver.getCurrentUrl();
		log.info("Current page URL after logout is: " + currentPageURLAfterLogOut + "\n");
		assertEquals(currentPageURLAfterLogOut, Constants.websiteUrl);
		Thread.sleep(2000);
	}
}
