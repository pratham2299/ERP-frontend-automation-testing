package in.biencaps.erp.utilities;

import static org.testng.Assert.*;

import java.util.*;

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

	// This is re usable method of login with valid user Id and valid password
	public void verify_Login_Employee_By_Giving_Valid_User_Id_And_Valid_Password(String userId, String password)
			throws InterruptedException {
		LoginPage login = new LoginPage();

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
		DashboardPage dashboard = new DashboardPage();

		String actualEmployeeName = dashboard.checkEmployeeNameAtDashboard();

		String correspondingEmployeeNameFromHashmap = DataGenerator.employeeUserIdsAndNamesOnTestEnvironment()
				.get(userId);
		assertEquals(actualEmployeeName, correspondingEmployeeNameFromHashmap);

		return actualEmployeeName;
	}

	// Created toast message common method
	// after each task add
	// Also it check stoast message text
	// and handled exception
	public void verify_Toast_Message_After_Task_Add_From_Sidebar(String date) throws InterruptedException {
		MyTasksPage myTasks = new MyTasksPage(driver);

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
		MyTasksPage myTasks = new MyTasksPage(driver);

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

	public void goToDayView() throws InterruptedException {
		DashboardPage dashboard = new DashboardPage();
		MyTasksPage myTasks = new MyTasksPage(driver);

		dashboard.clickOnMyTasksSection();
		Thread.sleep(1500);

		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		myTasks.clickOnRefreshButtonInDayView();
		Thread.sleep(1500);

		myTasks.scrollUptoBottomOfTaskDivInDayView();
	}

	// Created toast message common method
	// Also it check stoast message text
	// and handled exception
	public void verify_Toast_MessageInBlueColor(String message, String toastMessageText) throws InterruptedException {
		MyTasksPage myTasks = new MyTasksPage(driver);

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
		MyTasksPage myTasks = new MyTasksPage(driver);

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
		MyTasksPage myTasks = new MyTasksPage(driver);

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
		MyTasksPage myTasks = new MyTasksPage(driver);

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
		MyTasksPage myTasks = new MyTasksPage(driver);

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
		MyTasksPage myTasks = new MyTasksPage(driver);

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
		MyTasksPage myTasks = new MyTasksPage(driver);

		String actualLastDateInWeekView = myTasks.checkLastDateInWeekView();

		String actualFirstDateInWeekView = myTasks.checkFirstDateInWeekView();

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

	public List<String> getAllHigherAuthoritiesNamesOfLoggedInEmployee() throws InterruptedException {
		DashboardPage dashboard = new DashboardPage();
		ProfilePage profile = new ProfilePage();

		dashboard.clickOnEmployeeNameAtDashboard();
		Thread.sleep(1000);

		profile.clickOnEditInfoButton();

		List<String> loggedInEmployeesAllHigherAuthorities = profile.getAllReportingAuthorities();
		log.info("All higher authorities of logged in employee are: " + loggedInEmployeesAllHigherAuthorities);

		List<String> reportingAuthoritiesUserIds = new ArrayList<String>();

		for (String higherAuthority : loggedInEmployeesAllHigherAuthorities) {
			reportingAuthoritiesUserIds.add(DataGenerator
					.getUserIdByName(DataGenerator.employeeUserIdsAndNamesOnTestEnvironment(), higherAuthority));
		}

		log.info("All reporting authorities user Ids are: " + reportingAuthoritiesUserIds);
		return reportingAuthoritiesUserIds;
	}

	public String reject_Task_Title_Change_Request_By_Higher_Authority(List<String> reportingAuthoritiesUserIds)
			throws InterruptedException {
		DashboardPage dashboard = new DashboardPage();
		WebElementActions webElementActions = new WebElementActions();
		RequestPage request = new RequestPage();
		LogoutTests logOutFun = new LogoutTests();

		webElementActions.refreshThePage();

		logOutFun.verify_LogOut_Employee();

		int randomIndex = random.nextInt(reportingAuthoritiesUserIds.size());
		String userId = reportingAuthoritiesUserIds.get(randomIndex);
		String password = DataGenerator.employeeUserIdsAndPasswordsOnTestingEnvironment().get(userId);

		verify_Login_Employee_By_Giving_Valid_User_Id_And_Valid_Password(userId, password);

		String actualHigherAuthorityEmployeeName = verify_Employee_Name_After_Logged_In(userId);
		log.info("Actual higher authority name at dashboard is: " + actualHigherAuthorityEmployeeName);

		dashboard.clickOnRequestSectionLink();

		request.clickOnRefreshIconInRequestSection();
		Thread.sleep(2000);

		request.clickOnFirstRejectButton();
		Thread.sleep(1000);

		request.clickOnRejectSendButtonInRequestSection();
		Thread.sleep(2000);

		return actualHigherAuthorityEmployeeName;
	}

	public String accept_Task_Title_Change_Request_By_Higher_Authority(List<String> reportingAuthoritiesUserIds)
			throws InterruptedException {
		DashboardPage dashboard = new DashboardPage();
		WebElementActions webElementActions = new WebElementActions();
		RequestPage request = new RequestPage();
		LogoutTests logOutFun = new LogoutTests();

		webElementActions.refreshThePage();

		logOutFun.verify_LogOut_Employee();

		int randomIndex = random.nextInt(reportingAuthoritiesUserIds.size());
		String userId = reportingAuthoritiesUserIds.get(randomIndex);
		String password = DataGenerator.employeeUserIdsAndPasswordsOnTestingEnvironment().get(userId);

		verify_Login_Employee_By_Giving_Valid_User_Id_And_Valid_Password(userId, password);

		String actualHigherAuthorityEmployeeName = verify_Employee_Name_After_Logged_In(userId);
		log.info("Actual higher authority name at dashboard is: " + actualHigherAuthorityEmployeeName);

		dashboard.clickOnRequestSectionLink();

		request.clickOnRefreshIconInRequestSection();
		Thread.sleep(2000);

		request.clickOnFirstApproveButton();
		Thread.sleep(1000);

		return actualHigherAuthorityEmployeeName;
	}

	public void verify_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority(
			List<String> reportingAuthoritiesUserIds, String notificationMessage, String taskScheduleDate,
			String taskDueDate, String taskOwner, String requestAction, String requestSenderEmployeeName,
			String taskTitle) throws InterruptedException {
		WebElementActions webElementActions = new WebElementActions();
		RequestTests requestFun = new RequestTests();
		LogoutTests logOutFun = new LogoutTests();

		for (String higherAuthorityUserId : reportingAuthoritiesUserIds) {
			webElementActions.refreshThePage();

			logOutFun.verify_LogOut_Employee();

			String userId = higherAuthorityUserId;
			String password = DataGenerator.employeeUserIdsAndPasswordsOnTestingEnvironment().get(userId);

			verify_Login_Employee_By_Giving_Valid_User_Id_And_Valid_Password(userId, password);

			String actualRequestActionTakerHigherAuthority = verify_Employee_Name_After_Logged_In(userId);
			log.info("Actual higher authority name at dashboard is: " + actualRequestActionTakerHigherAuthority);
//			notification.verifyNotificationMessage(requestSenderEmployeeName, notificationMessage);

			if (taskScheduleDate.equalsIgnoreCase(taskDueDate)) {
				String taskOwnerNameWithTaskDate = taskOwner + " on " + taskScheduleDate + "";
				requestFun.verify_Request_In_Received_Request_Card(requestSenderEmployeeName, taskOwnerNameWithTaskDate,
						requestAction, taskTitle);
			} else {
				String taskOwnerNameWithTaskDate = taskOwner + " on " + taskScheduleDate + " to " + taskDueDate + "";
				requestFun.verify_Request_In_Received_Request_Card(requestSenderEmployeeName, taskOwnerNameWithTaskDate,
						requestAction, taskTitle);
			}
		}
	}
}