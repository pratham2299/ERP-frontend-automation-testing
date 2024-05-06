package com.biencaps.erp.utilities;

import static org.testng.Assert.*;

import org.apache.logging.log4j.*;
import com.biencaps.erp.pageObjectModels.*;
import com.biencaps.erp.testCases.*;

/* This class is extended BaseTest class.
 *  BaseTest has driver so you can inherite driver from BaseTest to this class
 */
public class CommonTestMethods extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public Logger log = LogManager.getLogger(CommonTestMethods.class);

	protected LoginPage login;
	protected WebElementActions webElementActions;
	protected MyTasksPage myTasks;
	protected DashboardPage dashboard;
	protected RequestFunctionality requestFun;
	protected EmployeePage employee;

//	@BeforeClass
//	public void driverPassToPageObjectModel() {
//		login = new LoginPage(driver);
//		webElementActions = new WebElementActions();
//		myTasks = new MyTasksPage(driver);
//		dashboard = new DashboardPage(driver);
//		requestFun = new RequestFunctionality();
//		employee = new EmployeePage(driver);
//	}

	// Login admin with valid credentials for re usability
	public void verifyLoginEmployeeByGivingValidUserIdAndValidPassword(String userId, String password)
			throws InterruptedException {
		login = new LoginPage(driver);
		webElementActions = new WebElementActions();
		myTasks = new MyTasksPage(driver);
		dashboard = new DashboardPage(driver);
		requestFun = new RequestFunctionality();
		employee = new EmployeePage(driver);

		String validUserId = userId;

		login.clickOnUserIdTextfield();

		login.clearUserIdTextfield();

		String actualEnteredUserId = login.enterAndCheckUserId(validUserId);
		log.info("Actual entered user Id is: " + actualEnteredUserId);
		assertEquals(actualEnteredUserId, validUserId);

		String validPassword = password;

		login.clickOnPasswordTextfield();

		login.clearPasswordTextfield();

		String actualEnteredPassword = login.enterAndCheckPassword(validPassword);
		log.info("Actual entered password is: " + actualEnteredPassword);
		assertEquals(actualEnteredPassword, validPassword);

		login.clickOnEyeIconNearPasswordTextfield();
		Thread.sleep(1000);

		login.clickOnEyeIconNearPasswordTextfield();

		login.clickOnLoginButton();

		verifyToastMessage("after successfull logged in", "Login Successfully");
		Thread.sleep(1000);

//		myTasks.clickOnCloseIconOfToastMessage();
	}

	public String verifyEmployeeNameAfterLoggedIn(String userId) {
		dashboard = new DashboardPage(driver);

		String actualEmployeeName = dashboard.checkEmployeeNameAtDashboard();

		String correspondingEmployeeNameFromHashmap = DataGenerator.employeeUserIdsAndNamesOnProduction().get(userId);
		assertEquals(actualEmployeeName, correspondingEmployeeNameFromHashmap);

		return actualEmployeeName;
	}

	// Created toast message common method
	// after each task add
	// Also it check stoast message text
	// and handled exception
	public void verifyToastMessageAfterTaskAddFromSidebar(String date) {
		try {
			myTasks = new MyTasksPage(driver);

			String actualToastMessageAfterTaskAdd = myTasks.checkTaskAddedToastMessage(date);
			log.info("Actual toast message after task add from sidebar is: " + actualToastMessageAfterTaskAdd + "\n");

			if (actualToastMessageAfterTaskAdd.equals(null) || actualToastMessageAfterTaskAdd.isBlank()
					|| actualToastMessageAfterTaskAdd.isEmpty()) {
				log.error("Toast message text is null or empty");
			} else {
				assertEquals(actualToastMessageAfterTaskAdd, "Task Added Successfully on: " + date + "");
				Thread.sleep(500);

				myTasks.clickOnCloseIconOfToastMessage();
			}
		} catch (Exception e) {
			log.error("Toast message text is not found");
		}
	}

	// Created toast message common method
	// Also it check stoast message text
	// and handled exception
	public void verifyToastMessage(String message, String toastMessageText) {
		try {
			myTasks = new MyTasksPage(driver);

			String actualToastMessage = myTasks.checkToastMessage(toastMessageText);
			log.info("Actual toast message " + message + " is: " + actualToastMessage + "\n");

			if (actualToastMessage.equals(null) || actualToastMessage.isBlank() || actualToastMessage.isEmpty()) {
				log.error("Toast message text is null or empty");
			} else {
				assertEquals(actualToastMessage, toastMessageText);
				Thread.sleep(500);

				myTasks.clickOnCloseIconOfToastMessage();
			}
		} catch (Exception e) {
			log.error("Toast message text is not found");
		}
	}

	/*
	 * This method is responsible for checking total tasks in day view. Also it is
	 * validating means comparing total tasks in day view and variable value of
	 * total tasks after add or delete task If falied it print catch statement
	 */
	public void verifyTotalTasksCountInDayViewAfterAddedOrDeletedTask(int totalTasksCount) {
		try {
			myTasks = new MyTasksPage(driver);

			String completedTasksCountOutOfTotalTasksCountInDayView = myTasks
					.checkCompletedTaskCountOutOfTotalTasksCountInDayView();
			log.info("Completed tasks count out of total tasks count in day view is: "
					+ completedTasksCountOutOfTotalTasksCountInDayView);
			int totaTasksCountInDayView = Integer
					.parseInt(completedTasksCountOutOfTotalTasksCountInDayView.substring(2));
			log.info("Total tasks count in day view is: " + totaTasksCountInDayView + "\n");

			assertEquals(totaTasksCountInDayView, totalTasksCount);
		} catch (Exception e) {
			log.error("Total tasks count is incorrect" + "\n");
		}
	}

	/*
	 * This method is responsible for checking total completed tasks in day view
	 * Also it is validating means comparing total completed tasks in day view and
	 * variable value of total completed tasks after add or delete task If falied it
	 * print catch statement
	 */
	public void verifyTotalCompletedTasksCountInDayView(int totalCompletedTasksCount) {
		try {
			myTasks = new MyTasksPage(driver);

			String completedTasksCountOutOfTotalTasksCountInDayView = myTasks
					.checkCompletedTaskCountOutOfTotalTasksCountInDayView();
			log.info("Completed tasks count out of total tasks count in day view is: "
					+ completedTasksCountOutOfTotalTasksCountInDayView);
			int totaCompletedTasksCountInDayView = Integer
					.parseInt(completedTasksCountOutOfTotalTasksCountInDayView.substring(0, 1));
			log.info("Total completed tasks count in day view is: " + totaCompletedTasksCountInDayView + "\n");

			assertEquals(totaCompletedTasksCountInDayView, totalCompletedTasksCount);
		} catch (Exception e) {
			log.error("Total tasks count is incorrect" + "\n");
		}
	}

	/*
	 * This method is responsible for checking total tasks in day view Also it is
	 * validating means comparing total tasks in day view and variable value of
	 * total tasks before add task
	 */
	public int getTotalTasksCountInDayView() {
		myTasks = new MyTasksPage(driver);

		String completedTasksCountOutOfTotalTasksCountInDayView = myTasks
				.checkCompletedTaskCountOutOfTotalTasksCountInDayView();
		log.info("Completed tasks count out of total tasks count in day view is: "
				+ completedTasksCountOutOfTotalTasksCountInDayView);
		int totaTasksCountInDayView = Integer.parseInt(completedTasksCountOutOfTotalTasksCountInDayView.substring(2));
		log.info("Total tasks count in day view is: " + totaTasksCountInDayView + "\n");

		int totalCheckBoxesCountInDayView = myTasks.checkTotalCheckBoxesInDayView();
		int sizeOfCheckBoxesAccordingToTask = totalCheckBoxesCountInDayView - 1;
		return sizeOfCheckBoxesAccordingToTask;
	}

	/*
	 * This method is responsible for check percentage of completed tasks after any
	 * task completed in day view
	 */
	public void verifyPercentageOfCompletedTasksInDayView(int completedTasks, int totalTasks) {
		try {
			myTasks = new MyTasksPage(driver);

			int totalCompetedTasksCount = completedTasks;
			int totalTasksCount = totalTasks;

			// Calculate the percentage of completed tasks
			double percentage = ((double) totalCompetedTasksCount / totalTasksCount) * 100;

			// Round down the percentage to get an integer value
			int roundedPercentageOfCompletedTasks = (int) Math.floor(percentage);
			log.info("Completed tasks percentage in day view is: " + myTasks.checkPercentageOfTaskCompletedInDayView()
					+ "%\n");
			assertEquals(roundedPercentageOfCompletedTasks, myTasks.checkPercentageOfTaskCompletedInDayView());
		} catch (Exception e) {
			log.error("Percentage of completed tasks not matching" + "\n");
		}
	}

	public void verifyloggedInEmployeeNameNotificationMessageAndRequestForAllHigherAuthority(String role,
			String loginEmployeeUserId, String loginEmployeePassword, String requestSenderEmployeeName,
			String requestAction, String taskTitle) throws InterruptedException {
		verifyLoginEmployeeByGivingValidUserIdAndValidPassword(loginEmployeeUserId, loginEmployeePassword);
		Thread.sleep(3000);

		String actualHigherAuthorityEmployeeName = verifyEmployeeNameAfterLoggedIn(loginEmployeeUserId);
		log.info("Actual " + role + " employee name at dashboard page is: " + actualHigherAuthorityEmployeeName + "\n");

//		verifyNotificationMessage(requestSenderEmployeeName,
//				"after employee sent task " + requestAction + " request at " + role + " employee side", requestAction,
//				taskTitle);

		requestFun.verifyRequestInReceivedRequestCard(requestSenderEmployeeName, requestAction, taskTitle);
	}

	// Check notification message
	public void verifyNotificationMessage(String employeeName, String message, String notificationMessage,
			String taskTitle) throws InterruptedException {
		dashboard = new DashboardPage(driver);

		dashboard.clickOnNotificationIcon();
//			Thread.sleep(6000);

		try {
			String actualNotificationMessageAfterTaskSubmittedRequestDenied = dashboard
					.checkFirstNotificationMessageForRequestDenied();
			log.info("Actual notification message " + message + " is: "
					+ actualNotificationMessageAfterTaskSubmittedRequestDenied);

			try {
				assertEquals(actualNotificationMessageAfterTaskSubmittedRequestDenied,
						"\"" + employeeName + "\" " + notificationMessage + " \"" + taskTitle + "\"");

				webElementActions.refreshThePage();
				Thread.sleep(4000);
			} catch (Exception e) {
				log.error("Notification message " + message + " is incorrect" + "\n");
				webElementActions.refreshThePage();
				Thread.sleep(4000);
			}
		} catch (Exception e) {
			log.error("Notification message is not found" + "\n");
			webElementActions.refreshThePage();
			Thread.sleep(4000);
		}
	}
}
