package in.biencaps.erp.utilities;

import static org.testng.Assert.*;

import java.util.List;

import org.apache.logging.log4j.*;

import in.biencaps.erp.pages.*;
import in.biencaps.erp.testCases.*;

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

	// This is re usable method of login with valid user Id and valid password
	public void verify_Login_Employee_By_Giving_Valid_User_Id_And_Valid_Password(String userId, String password)
			throws InterruptedException {
		login = new LoginPage();
		webElementActions = new WebElementActions();
		myTasks = new MyTasksPage(driver);
		dashboard = new DashboardPage();
		requestFun = new RequestFunctionality();
		employee = new EmployeePage();

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
		Thread.sleep(500);

		login.clickOnEyeIconNearPasswordTextfield();

		login.clickOnLoginButton();

		verify_Toast_Message("after successfull logged in", "Login Successfully");
	}

	public String verify_Employee_Name_After_Logged_In(String userId) {
		dashboard = new DashboardPage();

		String actualEmployeeName = dashboard.checkEmployeeNameAtDashboard();

		String correspondingEmployeeNameFromHashmap = LoginAndForgotPasswordFunctionality.employeeUserIdsAndNames
				.get(userId);
		assertEquals(actualEmployeeName, correspondingEmployeeNameFromHashmap);

		return actualEmployeeName;
	}

	// Created toast message common method
	// after each task add
	// Also it check stoast message text
	// and handled exception
	public void verify_Toast_Message_After_Task_Add_From_Sidebar(String date) throws InterruptedException {
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
	}

	// Created toast message common method
	// Also it check stoast message text
	// and handled exception
	public void verify_Toast_Message(String message, String toastMessageText) throws InterruptedException {
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
	}

	// Created toast message common method
	// Also it check stoast message text
	// and handled exception
	public void verify_Toast_MessageInBlueColor(String message, String toastMessageText) throws InterruptedException {
		myTasks = new MyTasksPage(driver);

		String actualToastMessage = myTasks.checkToastMessageInBlueColor(toastMessageText);
		log.info("Actual toast message " + message + " is: " + actualToastMessage + "\n");

		if (actualToastMessage.equals(null) || actualToastMessage.isBlank() || actualToastMessage.isEmpty()) {
			log.error("Toast message text is null or empty");
		} else {
			assertEquals(actualToastMessage, toastMessageText);
			Thread.sleep(500);

			myTasks.clickOnCloseIconOfToastMessage();
		}
	}

	/*
	 * This method is responsible for checking total tasks in day view. Also it is
	 * validating means comparing total tasks in day view and variable value of
	 * total tasks after add or delete task If falied it print catch statement
	 */
	public void verify_Total_Tasks_Count_In_Day_View_After_Added_Or_Deleted_Task(int totalTasksCount) {
		myTasks = new MyTasksPage(driver);

		String completedTasksCountOutOfTotalTasksCountInDayView = myTasks
				.checkCompletedTaskCountOutOfTotalTasksCountInDayView();
		log.info("Completed tasks count out of total tasks count in day view is: "
				+ completedTasksCountOutOfTotalTasksCountInDayView);
		int totaTasksCountInDayView = Integer.parseInt(completedTasksCountOutOfTotalTasksCountInDayView.substring(2));
		log.info("Total tasks count in day view is: " + totaTasksCountInDayView + "\n");

		assertEquals(totaTasksCountInDayView, totalTasksCount);
	}

	/*
	 * This method is responsible for checking total completed tasks in day view
	 * Also it is validating means comparing total completed tasks in day view and
	 * variable value of total completed tasks after add or delete task If falied it
	 * print catch statement
	 */
	public void verify_Total_Completed_Tasks_Count_In_Day_View(int totalCompletedTasksCount) {
		myTasks = new MyTasksPage(driver);

		String completedTasksCountOutOfTotalTasksCountInDayView = myTasks
				.checkCompletedTaskCountOutOfTotalTasksCountInDayView();
		log.info("Completed tasks count out of total tasks count in day view is: "
				+ completedTasksCountOutOfTotalTasksCountInDayView);
		int totaCompletedTasksCountInDayView = Integer
				.parseInt(completedTasksCountOutOfTotalTasksCountInDayView.substring(0, 1));
		log.info("Total completed tasks count in day view is: " + totaCompletedTasksCountInDayView + "\n");

		assertEquals(totaCompletedTasksCountInDayView, totalCompletedTasksCount);
	}

	/*
	 * This method is responsible for checking total tasks in day view Also it is
	 * validating means comparing total tasks in day view and variable value of
	 * total tasks before add task
	 */
	public int get_Total_Tasks_Count_In_Day_View() {
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
	public void verify_Percentage_Of_Completed_Tasks_In_Day_View(int completedTasks, int totalTasks) {
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
	}

	// Checks added task details in day view
	public void verify_Added_Task_Check_In_Day_View(String message, String taskTitle, String taskStatus,
			String taskPriority, String taskProject, String taskStartDate, String taskDueDate, String taskDepartment,
			String taskAssignee, String taskComment) throws InterruptedException {
		myTasks.clickOnRefreshButtonInDayView();

		myTasks.scrollUptoBottomOfTaskDivInDayView();

		int newAddedTaskRowIndex = myTasks.checkNewAddedTaskTitleRowIndexInDayView(taskTitle);

		String actualTaskTitleInDayView = myTasks.checkRandomTaskTitleInDayView(newAddedTaskRowIndex);
		log.info("Actual added task title " + message + " in day view is: " + actualTaskTitleInDayView);

		if (actualTaskTitleInDayView.length() > 26) {
			assertTrue(actualTaskTitleInDayView.startsWith(taskTitle.substring(0, 27)));
		} else {
			assertEquals(actualTaskTitleInDayView, taskTitle);
		}

		String actualTaskStatusInDayView = myTasks.checkRandomTaskStatusTextInDayView(newAddedTaskRowIndex);
		log.info("Actual added task status " + message + " in day view is: " + actualTaskStatusInDayView);
		assertEquals(actualTaskStatusInDayView, taskStatus);

		String actualTaskPriorityInDayView = myTasks.checkRandomTaskPriorityTextInDayView(newAddedTaskRowIndex);
		log.info("Actual added task priority " + message + " in day view is: " + actualTaskPriorityInDayView);
		assertEquals(actualTaskPriorityInDayView, taskPriority);

		try {
			String actualTaskProjectInDayView = myTasks.checkRandomTaskProjectTextInDayView(newAddedTaskRowIndex);
			log.info("Actual added task project " + message + " in day view is: " + actualTaskProjectInDayView);
			assertEquals(actualTaskProjectInDayView, taskProject);
		} catch (Exception e) {
			log.info("Project field is not selected while add task " + message + "");
		}

		myTasks.scrollHorizantally(1500);

		String actualTaskScheduleDateInDayView = myTasks.checkRandomTaskScheduleDateTextInDayView(newAddedTaskRowIndex);
		log.info("Actual added task schedule date " + message + " in day view is: " + actualTaskScheduleDateInDayView);
		assertEquals(actualTaskScheduleDateInDayView, taskStartDate);

		String actualTaskDueDateInDayView = myTasks.checkRandomTaskDueDateTextInDayView(newAddedTaskRowIndex);
		log.info("Actual added task due date " + message + " in day view is: " + actualTaskDueDateInDayView);
		assertEquals(actualTaskDueDateInDayView, taskDueDate);

		List<String> taskDepartmentValuesInDayView = myTasks
				.checkRandomTaskDepartmentTextInDayView(newAddedTaskRowIndex);
		String actualTaskDepartmentValueInDayView = taskDepartmentValuesInDayView.get(0);
		log.info("Actual  task department value added " + message + " in day view is: "
				+ actualTaskDepartmentValueInDayView);
		assertEquals(actualTaskDepartmentValueInDayView, taskDepartment);

		String actualTaskAssigneeNameInDayView = myTasks.checkLastTaskAssigneeNameTextInDayView();
		log.info("Actual task assignee name added " + message + " in day view is: " + actualTaskAssigneeNameInDayView);
		assertEquals(actualTaskAssigneeNameInDayView, taskAssignee);

		String actualTaskCommentInDayView = myTasks.checkRandomTaskCommentTextInDayView(newAddedTaskRowIndex);
		log.info("Actual task comment added " + message + " in day view is: " + actualTaskCommentInDayView);

		if (taskComment.equalsIgnoreCase(null) || taskComment.isBlank() || taskComment.isEmpty()) {
			log.info("Task comment is empty or null" + "\n");
		} else {
			if (actualTaskCommentInDayView.length() > 16) {
				assertTrue(actualTaskCommentInDayView.startsWith(taskComment.substring(0, 16)));
			} else {
				assertEquals(actualTaskCommentInDayView, taskComment);
			}

			myTasks.clickOnRandomTaskCommentTextfieldInDayView(newAddedTaskRowIndex);
			Thread.sleep(1000);

			myTasks.scrollUntilCommentTextfieldInUpdateTaskSidebar();

			String actualTaskCommentInSidebarForUpdateTaskInDayView = myTasks
					.checkRandomTaskCommentTextInUpdateTaskSidebar();
			log.info("Actual task comment in sidebar for update task added " + message + " in day view is: "
					+ actualTaskCommentInSidebarForUpdateTaskInDayView + "\n");
			assertEquals(actualTaskCommentInSidebarForUpdateTaskInDayView, taskComment);

			myTasks.clickOnCloseIconOfSidebarForUpdateTaskInDayView();
			Thread.sleep(1000);
		}

		myTasks.scrollHorizantally(-1500);
	}

	public void verify_Added_Task_In_Week_View(String message, String taskTitle, String taskScheduleDate,
			String taskDueDate) throws InterruptedException {
		String actualLastDateInWeekView = myTasks.checkLastDateInWeekView();
		log.info("Actual last date in week view is: " + actualLastDateInWeekView);

		String actualFirstDateInWeekView = myTasks.checkFirstDateInWeekView();
		log.info("Actual first date in week view is: " + actualFirstDateInWeekView);

		if (Integer.parseInt(actualLastDateInWeekView) < Integer.parseInt(taskDueDate.substring(8))) {
			myTasks.clickOnNextWeekSelectIcon();
			Thread.sleep(1000);

			try {
				myTasks.clickOnAllExpandButtonsInWeekView();
				Thread.sleep(1000);

				myTasks.scrollUntilAllCollapseButtonsInWeekView();
				Thread.sleep(1000);

				String actualNewAddedTaskTitleInWeekView = myTasks.checkNewAddedTaskTitleInWeekView(taskTitle);
				log.info("Actual new added task title " + message + " in week view is: "
						+ actualNewAddedTaskTitleInWeekView);
				assertTrue(actualNewAddedTaskTitleInWeekView.startsWith(taskTitle));
			} catch (Exception e) {
				String actualNewAddedTaskTitleInWeekView = myTasks.checkNewAddedTaskTitleInWeekView(taskTitle);
				log.info("Actual new added task title " + message + " in week view is: "
						+ actualNewAddedTaskTitleInWeekView);
				assertTrue(actualNewAddedTaskTitleInWeekView.startsWith(taskTitle));
			}
		} else if (Integer.parseInt(actualFirstDateInWeekView) > Integer.parseInt(taskScheduleDate.substring(8))) {
			myTasks.clickOnPreviousWeekSelectIcon();
			Thread.sleep(1000);

			try {
				myTasks.clickOnAllExpandButtonsInWeekView();
				Thread.sleep(1000);

				myTasks.scrollUntilAllCollapseButtonsInWeekView();
				Thread.sleep(1000);

				String actualNewAddedTaskTitleInWeekView = myTasks.checkNewAddedTaskTitleInWeekView(taskTitle);
				log.info("Actual new added task title " + message + " in week view is: "
						+ actualNewAddedTaskTitleInWeekView);
				assertTrue(actualNewAddedTaskTitleInWeekView.startsWith(taskTitle));
			} catch (Exception e) {
				String actualNewAddedTaskTitleInWeekView = myTasks.checkNewAddedTaskTitleInWeekView(taskTitle);
				log.info("Actual new added task title " + message + " in week view is: "
						+ actualNewAddedTaskTitleInWeekView);
				assertTrue(actualNewAddedTaskTitleInWeekView.startsWith(taskTitle));
			}
		} else {
			try {
				myTasks.clickOnAllExpandButtonsInWeekView();
				Thread.sleep(1000);

				myTasks.scrollUntilAllCollapseButtonsInWeekView();
				Thread.sleep(1000);

				String actualNewAddedTaskTitleInWeekView = myTasks.checkNewAddedTaskTitleInWeekView(taskTitle);
				log.info("Actual new added task title " + message + " in week view is: "
						+ actualNewAddedTaskTitleInWeekView);
				assertTrue(actualNewAddedTaskTitleInWeekView.startsWith(taskTitle));
			} catch (Exception e) {
				String actualNewAddedTaskTitleInWeekView = myTasks.checkNewAddedTaskTitleInWeekView(taskTitle);
				log.info("Actual new added task title " + message + " in week view is: "
						+ actualNewAddedTaskTitleInWeekView);
				assertTrue(actualNewAddedTaskTitleInWeekView.startsWith(taskTitle));
			}
		}

	}

	public void verifyloggedInEmployeeNameNotificationMessageAndRequestForAllHigherAuthority(String role,
			String loginEmployeeUserId, String loginEmployeePassword, String requestSenderEmployeeName,
			String requestAction, String taskTitle) throws InterruptedException {
		verify_Login_Employee_By_Giving_Valid_User_Id_And_Valid_Password(loginEmployeeUserId, loginEmployeePassword);

		String actualHigherAuthorityEmployeeName = verify_Employee_Name_After_Logged_In(loginEmployeeUserId);
		log.info("Actual " + role + " employee name at dashboard page is: " + actualHigherAuthorityEmployeeName + "\n");

		verifyNotificationMessage(requestSenderEmployeeName,
				"after employee sent task " + requestAction + " request at " + role + " employee side", requestAction,
				taskTitle);

		requestFun.verify_Request_In_Received_Request_Card(requestSenderEmployeeName, requestAction, taskTitle);
	}

	// Check notification message
	public void verifyNotificationMessage(String employeeName, String message, String notificationMessage,
			String taskTitle) throws InterruptedException {
		dashboard = new DashboardPage();

		dashboard.clickOnNotificationIcon();

		List<String> actualNotificationMessages = dashboard.checkNotificationMessages();

		String receivedNotificationMessage = "\"" + employeeName + "\" " + notificationMessage + " \"" + taskTitle
				+ "\"";
		try {
			assertTrue(actualNotificationMessages.contains(receivedNotificationMessage));

			webElementActions.refreshThePage();
			Thread.sleep(3000);
		} catch (Exception e) {
			log.error("Notification message " + message + " is incorrect" + "\n");
			webElementActions.refreshThePage();
		}
	}
}
