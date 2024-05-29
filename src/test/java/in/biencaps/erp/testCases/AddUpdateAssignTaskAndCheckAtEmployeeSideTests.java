package in.biencaps.erp.testCases;

import static org.testng.Assert.*;
import java.util.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;
import in.biencaps.erp.pages.*;
import in.biencaps.erp.utilities.*;

public class AddUpdateAssignTaskAndCheckAtEmployeeSideTests extends BaseTest {
	// This is logger API dependency code. To print messages in separate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(AddUpdateAssignTaskAndCheckAtEmployeeSideTests.class);

	protected MyTasksPage myTasks;
	protected DashboardPage dashboard;
	protected CommonTestMethods commonMethods;
	protected LogoutTests logOutFun;
	protected LoginAndForgotPasswordTests loginFun;
	protected RequestTests requestFun;
	protected RequestPage request;
	protected MyActivitiesTests myActivities;
	protected WebElementActions webElementActions;
	protected NotificationMessagesTests notification;
	protected ProfilePage profile;

	private String taskTitleInputWhileAddFromLevelViewSidebar;
	private String actualTaskStatusWhileAddTaskFromLevelViewSidebar;
	private String actualTaskPriorityWhileAddTaskFromLevelViewSidebar;
	private String actualTaskProjectWhileAddTaskFromLevelViewSidebar;
	private String actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar;
	private String actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar;
	private String actualTaskDepartmentWhileAddTaskFromLevelViewSidebar;
	private String actualTaskAssigneeNameWhileAddTaskFromLevelViewSidebar;
	private String actualTaskCommentWhileAddTaskFromLevelViewSidebar;

	public static String actualTaskTitleAfterClearAndUpdateFromDayView;
	public static String actualLastAssignTaskTitleInDayView;
	public static String actualLastAssignTaskStatusInDayView;
	public static String actualLastAssignTaskPriorityInDayView;
	public static String actualLastAssignTaskProjectInDayView;
	public static String actualLastAssignTaskDueDateInDayView;
	public static List<String> actualLastRowTaskDepartmentsInDayView;
	public static String actualLastAssignTaskVerifiedStatusInDayView;
	public static String actualLastAssignTaskCommentInDayView;

	public String actualTaskOwnerName;
	private String actualTaskAssignedEmployeeName;
	private String actualRequestActionTakerHigherAuthority;
	private String requestActionForTaskTitleChange;
	private List<String> reportingAuthoritiesUserIds;

	@Test(priority = 1)
	public void verify_Assign_Task_From_Level() throws InterruptedException {
		myTasks = new MyTasksPage(driver);
		commonMethods = new CommonTestMethods();
		loginFun = new LoginAndForgotPasswordTests();
		logOutFun = new LogoutTests();
		requestFun = new RequestTests();
		request = new RequestPage();
		dashboard = new DashboardPage();
		myActivities = new MyActivitiesTests();
		webElementActions = new WebElementActions();
		notification = new NotificationMessagesTests();
		profile = new ProfilePage();

		actualTaskOwnerName = commonMethods.random_Higher_Authority_LogIn();
		actualTaskAssignedEmployeeName = LoginAndForgotPasswordTests.actualEmployeeName;

		// Here I am iterating over roles and clicked on arrow icon
		// If employee name is not found in role employee list
		for (int i = 0; i < webElementActions.sizeOfListOfWebElement(dashboard.rolesForLevelView); i++) {
			// Here I have taken last role visible
			String lastRoleForLevelView = dashboard.checkRoleForLevelView();

			// If last role is equals with logged in employee role
			// then click on employee name from level
			// And add task to him
			if (lastRoleForLevelView.equalsIgnoreCase(
					DataGenerator.employeeUserIdsAndRolesOnTestEnvironment().get(Constants.employeeUserId))) {
				// Click on employee matched role
				dashboard.clickOnRandomRoleForLevelView();
				Thread.sleep(1000);

				// Click on employee name for that I passed employee name in method as parameter
				dashboard.clickOnEmployeeFromLevelView(actualTaskAssignedEmployeeName);
				Thread.sleep(1000);

				dashboard.clickOnNewTaskButtonFromLevelView();
				Thread.sleep(1000);

				taskTitleInputWhileAddFromLevelViewSidebar = faker.book().title();

				myTasks.enterTaskNameWhileAddTaskFromSidebar(taskTitleInputWhileAddFromLevelViewSidebar);

				// Prints entered task title and checks whether entered task title and task
				// title input matches or noy
				String actualEnteredTaskTitleFromLevelViewSidebarWhileAddTask = myTasks
						.checkTaskNameAfterAddedWhileAddTaskFromSidebar(taskTitleInputWhileAddFromLevelViewSidebar);
				log.info("Actual entered task title from month view and at particular date is: "
						+ actualEnteredTaskTitleFromLevelViewSidebarWhileAddTask);

				if (actualEnteredTaskTitleFromLevelViewSidebarWhileAddTask.length() > 26) {
					assertTrue(actualEnteredTaskTitleFromLevelViewSidebarWhileAddTask
							.startsWith(taskTitleInputWhileAddFromLevelViewSidebar.substring(0, 27)));
				} else {
					assertEquals(actualEnteredTaskTitleFromLevelViewSidebarWhileAddTask,
							taskTitleInputWhileAddFromLevelViewSidebar);
				}

				// Prints schedule date value and checks whether entered schedule date and input
				// schedule date matches or not
				actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar = myTasks
						.checkSelectedTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar();
				log.info("Actual schedule date field value while add task from month view and at particular date is: "
						+ actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar);

				// Prints due date value and checks whether entered due date and input
				// due date matches or not
				actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar = myTasks
						.checkSelectedTaskDueDateValueFromCalendarWhileAddTaskFromSidebar();
				log.info("Actual due date field value while add task from month view and at particular date is: "
						+ actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar);
				assertEquals(actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar,
						actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar);

				// Prints assignee value and checks whether assignee name and
				// logged in employee name matches or not
				actualTaskAssigneeNameWhileAddTaskFromLevelViewSidebar = myTasks
						.checkTaskAssigneeNameWhileAddTaskFromSidebarAtLevelView();
				log.info("Actual task assignee name while add task from month view and at particular date is: "
						+ actualTaskAssigneeNameWhileAddTaskFromLevelViewSidebar);
				assertEquals(actualTaskAssigneeNameWhileAddTaskFromLevelViewSidebar, actualTaskAssignedEmployeeName);

				actualTaskDepartmentWhileAddTaskFromLevelViewSidebar = myTasks
						.checkTaskDepartmentWhileAddTaskFromSidebar();
				log.info("Actual task department while add task from month view and at particular date is: "
						+ actualTaskDepartmentWhileAddTaskFromLevelViewSidebar);

				myTasks.clickOnPriorityDropdownWhileAddTaskFromSidebar();

				// Select random priority value
				myTasks.clickOnRandomValueFromDropdownWhileAddTaskFromSidebar();

				myTasks.clickOnPriorityFieldLabelWhileAddTaskFromSidebar();

				actualTaskPriorityWhileAddTaskFromLevelViewSidebar = myTasks.checkTaskPriorityWhileAddTaskFromSidebar();
				log.info("Actual task priority while add task from month view and at particular date is: "
						+ actualTaskPriorityWhileAddTaskFromLevelViewSidebar);

				actualTaskStatusWhileAddTaskFromLevelViewSidebar = myTasks.checkTaskStatusWhileAddTaskFromSidebar();
				log.info("Actual task status while add task from month view and at particular date is: "
						+ actualTaskStatusWhileAddTaskFromLevelViewSidebar);

				try {
					myTasks.clickOnProjectDropdownWhileAddTaskFromSidebar();

					// Select random project
					myTasks.clickOnRandomValueFromDropdownWhileAddTaskFromSidebar();

					myTasks.clickOnProjectFieldLabelWhileAddTaskFromSidebar();

					actualTaskProjectWhileAddTaskFromLevelViewSidebar = myTasks
							.checkTaskProjectWhileAddingTaskFromSidebar();
					log.info("Actual task project while add task from month view and at particular date is: "
							+ actualTaskProjectWhileAddTaskFromLevelViewSidebar);
				} catch (Exception e) {
					log.error("Project field not selected any value");
				}

				// Prints Entered taks comment and
				// check input comment and actual value matches or not
				String validTaskComment = faker.company().industry();
				myTasks.enterTaskCommentWhileAddingTaskFromsidebar(validTaskComment);

				actualTaskCommentWhileAddTaskFromLevelViewSidebar = myTasks
						.checkTaskCommentWhileAddingTaskFromSidebar();
				log.info("Actual entered task comment while add task from month view and at particular date is: "
						+ actualTaskCommentWhileAddTaskFromLevelViewSidebar);

				myTasks.clickOnAddTasksButtonInSidebar();

				commonMethods.verify_Toast_Message_After_Task_Add_From_Sidebar(
						actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar);
			} else {
				// If employee role is not equal with visible last role then click on arrow
				dashboard.clickOnLastArrowForLevelView();
				Thread.sleep(1000);
			}
		}
	}

	// This method is checking added task in day view of employee from level
	// Also in week view from level
	@Test(priority = 2)
	public void verify_Task_Added_From_Level_View_By_Higher_Authority_In_Employee_Day_View()
			throws InterruptedException {
		commonMethods.verify_Added_Task_Check_In_Day_View("after added task from level view to lower level employee",
				taskTitleInputWhileAddFromLevelViewSidebar, actualTaskStatusWhileAddTaskFromLevelViewSidebar,
				actualTaskPriorityWhileAddTaskFromLevelViewSidebar, actualTaskProjectWhileAddTaskFromLevelViewSidebar,
				actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualTaskDepartmentWhileAddTaskFromLevelViewSidebar, actualTaskOwnerName,
				actualTaskCommentWhileAddTaskFromLevelViewSidebar);

		myTasks.clickOnWeekButton();

		commonMethods.verify_Added_Task_In_Week_View("after added task from level view to lower level employee",
				taskTitleInputWhileAddFromLevelViewSidebar,
				actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar);
	}

	// Here
	@Test(priority = 3)
	public void verify_Notification_After_Assigned_Task_From_Higher_Authority_At_Lower_Level_Employee_Side()
			throws InterruptedException {
		String actualTaskAssignedEmployeeName = commonMethods.login_Employee_And_Get_Name();
		log.info("Actual task assigned employee name at dashboard page is: " + actualTaskAssignedEmployeeName + "\n");

		String notificationMessageAtEmployeeSideAfterAssignedTaskFromHigherAuthrotiy = "\"" + actualTaskOwnerName
				+ "\" assigned you a task " + "\"" + taskTitleInputWhileAddFromLevelViewSidebar + "\"";

		notification.verifyNotificationMessage(actualTaskOwnerName,
				notificationMessageAtEmployeeSideAfterAssignedTaskFromHigherAuthrotiy);
	}

	@Test(priority = 4)
	public void verify_Assigned_Task_Details_In_Day_View_And_Week_View_And_Log_Message() throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		commonMethods.verify_Added_Task_Check_In_Day_View("after added task from level view to lower level employee",
				taskTitleInputWhileAddFromLevelViewSidebar, actualTaskStatusWhileAddTaskFromLevelViewSidebar,
				actualTaskPriorityWhileAddTaskFromLevelViewSidebar, actualTaskProjectWhileAddTaskFromLevelViewSidebar,
				actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualTaskDepartmentWhileAddTaskFromLevelViewSidebar, actualTaskOwnerName,
				actualTaskCommentWhileAddTaskFromLevelViewSidebar);

		myTasks.clickOnWeekButton();

		commonMethods.verify_Added_Task_In_Week_View("after added task from level view to lower level employee",
				taskTitleInputWhileAddFromLevelViewSidebar,
				actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar);

		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		myActivities.verify_Log_Message(actualTaskOwnerName, "has added new task",
				taskTitleInputWhileAddFromLevelViewSidebar);
	}

	// Prints last task details like title, status, priority, project, due date etc
	@Test(priority = 5)
	public void verify_Last_Task_Details_In_Day_View() throws InterruptedException {
		// Passed driver get from BaseTest to my tasks page i.e. page object model and
		// selenium methods, common test methods class
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		actualLastAssignTaskTitleInDayView = myTasks.checkLastTaskTitleInDayView();
		log.info("Actual Last assign task title in day view is: " + actualLastAssignTaskTitleInDayView);

		actualLastAssignTaskStatusInDayView = myTasks.checkLastTaskStatusTextInDayView();
		log.info("Actual Last assign task status in day view is: " + actualLastAssignTaskStatusInDayView);

		actualLastAssignTaskPriorityInDayView = myTasks.checkLastTaskPriorityTextInDayView();
		log.info("Actual Last assign task priority in day view is: " + actualLastAssignTaskPriorityInDayView);

		try {
			actualLastAssignTaskProjectInDayView = myTasks.checkLastTaskProjectTextInDayView();
			log.info("Actual Last assign task project in day view is: " + actualLastAssignTaskProjectInDayView);
		} catch (Exception e) {
			System.out.println("Last project not found in day view");
			actualLastAssignTaskProjectInDayView = "";
			log.info("Actual Last assign task project in day view is: " + actualLastAssignTaskProjectInDayView);
		}

		myTasks.scrollHorizantally(1600);
		Thread.sleep(1000);

		actualLastAssignTaskDueDateInDayView = myTasks.checkLastTaskDueDateTextInDayView();
		log.info("Actual Last assign task due date in day view is: " + actualLastAssignTaskDueDateInDayView);

		actualLastRowTaskDepartmentsInDayView = myTasks.checkLastRowTaskDepartmentTextsInDayView();
		log.info("Actual Last assign task departments in day view is: " + actualLastRowTaskDepartmentsInDayView);

		actualLastAssignTaskVerifiedStatusInDayView = myTasks.checkLastTaskVerifiedStatusTextInDayView();
		log.info("Actual Last assign task verified status in day view is: "
				+ actualLastAssignTaskVerifiedStatusInDayView);

		myTasks.clickOnLastTaskCommentTextfieldInDayView();
		Thread.sleep(1000);

		myTasks.scrollUntilCommentTextfieldInUpdateTaskSidebar();
		Thread.sleep(1000);

		actualLastAssignTaskCommentInDayView = myTasks.checkLastTaskCommentTextInUpdateTaskSidebar();
		log.info("Actual Last assign task comment in day view is: " + actualLastAssignTaskCommentInDayView + "\n");

		myTasks.clickOnCloseIconOfSidebarForUpdateTaskInDayView();
		Thread.sleep(1000);
	}

	@Test(priority = 6)
	public void verify_Assign_Task_Title_Update_And_Check_Request_In_My_Request_Card() throws InterruptedException {
		myTasks.scrollHorizantally(-1500);

		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		String validTaskTitle = faker.book().title();

		myTasks.clickOnLastTaskTitleTextfieldInDayView();
		Thread.sleep(1000);

		myTasks.clearLastTaskTitleTextfieldInDayView();
		Thread.sleep(1000);

		myTasks.enterTaskTitleInLastTaskTitleTextfieldDayView(validTaskTitle);

		commonMethods.verify_Toast_Message("after task clear and update assign task in day view",
				"Request for task title change sent successfully");

		actualTaskTitleAfterClearAndUpdateFromDayView = myTasks.checkLastTaskTitleInDayView();
		log.info("Actual task title after clear and update from day view is: "
				+ actualTaskTitleAfterClearAndUpdateFromDayView);

		if (actualTaskTitleAfterClearAndUpdateFromDayView.length() > 26) {
			assertTrue(actualTaskTitleAfterClearAndUpdateFromDayView.startsWith(validTaskTitle));
		} else {
			assertEquals(actualTaskTitleAfterClearAndUpdateFromDayView, validTaskTitle);
		}

		requestActionForTaskTitleChange = "wants to change title from \"" + actualLastAssignTaskTitleInDayView
				+ "\" to \"" + actualTaskTitleAfterClearAndUpdateFromDayView + "\" in";

		if (actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar
				.equalsIgnoreCase(actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar)) {
			String taskOwnerNameWithTaskDate = actualTaskOwnerName + " on "
					+ actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar + "";
			requestFun.verify_Request_In_My_Requests_Card(actualTaskAssignedEmployeeName, taskOwnerNameWithTaskDate,
					requestActionForTaskTitleChange, actualLastAssignTaskTitleInDayView);
		} else {
			String taskOwnerNameWithTaskDate = actualTaskOwnerName + " on "
					+ actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar + " to "
					+ actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar + "";
			requestFun.verify_Request_In_My_Requests_Card(actualTaskAssignedEmployeeName, taskOwnerNameWithTaskDate,
					requestActionForTaskTitleChange, actualLastAssignTaskTitleInDayView);
		}
	}

	@Test(priority = 7)
	public void verify_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority_Of_Task_Title_Change()
			throws InterruptedException {
		reportingAuthoritiesUserIds = commonMethods.getAllHigherAuthoritiesNamesOfLoggedInEmployee();
		System.out.println(reportingAuthoritiesUserIds);

		commonMethods.verify_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority(
				reportingAuthoritiesUserIds, "", actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar, actualTaskOwnerName,
				requestActionForTaskTitleChange, actualTaskAssignedEmployeeName, actualLastAssignTaskTitleInDayView);
	}

	@Test(dependsOnMethods = "verify_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority_Of_Task_Title_Change")
	public void reject_Task_Title_Change_Request_By_Higher_Authority() throws InterruptedException {
		actualRequestActionTakerHigherAuthority = commonMethods
				.reject_Task_Title_Change_Request_By_Higher_Authority(reportingAuthoritiesUserIds);
	}

	// Check notification message after rejected task title change request
	@Test(priority = 8)
	public void verify_Notification_Message_Of_Request_Rejected_By_Higher_Authority_At_Employee_Side()
			throws InterruptedException {
		commonMethods.login_Employee_And_Get_Name();

		notification.verifyNotificationMessage(actualRequestActionTakerHigherAuthority,
				"rejected your request for \"" + actualLastAssignTaskTitleInDayView + "\"");
	}

	// Check log message after task submit request rejected in day view
	@Test(priority = 9)
	public void verify_Log_Message_After_Task_Title_Change_Request_Rejected() throws InterruptedException {
		commonMethods.goToDayView();

		String actualLastAssignTaskTitleInDayView = myTasks.checkLastTaskTitleInDayView();
		assertEquals(actualLastAssignTaskTitleInDayView, taskTitleInputWhileAddFromLevelViewSidebar);

		myActivities.verify_Log_Message(actualRequestActionTakerHigherAuthority,
				"has rejected your request for task title change in", actualLastAssignTaskTitleInDayView);
	}

	// Check request details in my request
	@Test(priority = 10)
	public void verify_Task_Request_In_My_Request_Section_After_Rejected_Task_Title_Change_Request()
			throws InterruptedException {
		if (actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar
				.equalsIgnoreCase(actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar)) {
			String taskOwnerNameWithTaskDate = actualTaskOwnerName + " on "
					+ actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar + "";
			requestFun.verify_Task_Request_In_My_Request_Section_By_Filtering_Request_Category("Rejected",
					taskOwnerNameWithTaskDate, actualRequestActionTakerHigherAuthority, requestActionForTaskTitleChange,
					actualLastAssignTaskTitleInDayView);
		} else {
			String taskOwnerNameWithTaskDate = actualTaskOwnerName + " on "
					+ actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar + " to "
					+ actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar + "";
			requestFun.verify_Task_Request_In_My_Request_Section_By_Filtering_Request_Category("Rejected",
					taskOwnerNameWithTaskDate, actualRequestActionTakerHigherAuthority, requestActionForTaskTitleChange,
					actualLastAssignTaskTitleInDayView);
		}
	}

	// Now accept task title change request from higher authority
	// Check notification, log message, task title
	@Test(priority = 11)
	public void verify_Assign_Task_Title_Again_Update_And_Check_Request_In_My_Request_Card()
			throws InterruptedException {
		commonMethods.goToDayView();

		String validTaskTitle = faker.book().title();

		myTasks.clickOnLastTaskTitleTextfieldInDayView();
		Thread.sleep(1000);

		myTasks.clearLastTaskTitleTextfieldInDayView();
		Thread.sleep(1000);

		myTasks.enterTaskTitleInLastTaskTitleTextfieldDayView(validTaskTitle);

		commonMethods.verify_Toast_Message("after task clear and update assign task in day view",
				"Request for task title change sent successfully");

		actualTaskTitleAfterClearAndUpdateFromDayView = myTasks.checkLastTaskTitleInDayView();
		log.info("Actual task title after clear and update from day view is: "
				+ actualTaskTitleAfterClearAndUpdateFromDayView);

		if (actualTaskTitleAfterClearAndUpdateFromDayView.length() > 26) {
			assertTrue(actualTaskTitleAfterClearAndUpdateFromDayView.startsWith(validTaskTitle));
		} else {
			assertEquals(actualTaskTitleAfterClearAndUpdateFromDayView, validTaskTitle);
		}

		requestActionForTaskTitleChange = "wants to change title from \"" + actualLastAssignTaskTitleInDayView
				+ "\" to \"" + actualTaskTitleAfterClearAndUpdateFromDayView + "\" in";

		if (actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar
				.equalsIgnoreCase(actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar)) {
			String taskOwnerNameWithTaskDate = actualTaskOwnerName + " on "
					+ actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar + "";
			requestFun.verify_Request_In_My_Requests_Card(actualTaskAssignedEmployeeName, taskOwnerNameWithTaskDate,
					requestActionForTaskTitleChange, actualLastAssignTaskTitleInDayView);
		} else {
			String taskOwnerNameWithTaskDate = actualTaskOwnerName + " on "
					+ actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar + " to "
					+ actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar + "";
			requestFun.verify_Request_In_My_Requests_Card(actualTaskAssignedEmployeeName, taskOwnerNameWithTaskDate,
					requestActionForTaskTitleChange, actualLastAssignTaskTitleInDayView);
		}
	}

	@Test(priority = 12)
	public void verify_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority_Of_Task_Title_Again_Change()
			throws InterruptedException {
		reportingAuthoritiesUserIds = commonMethods.getAllHigherAuthoritiesNamesOfLoggedInEmployee();

		commonMethods.verify_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority(
				reportingAuthoritiesUserIds, "", actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar, actualTaskOwnerName,
				requestActionForTaskTitleChange, actualTaskAssignedEmployeeName, actualLastAssignTaskTitleInDayView);
	}

	// Check request based on accepted
	@Test(dependsOnMethods = "verify_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority_Of_Task_Title_Again_Change")
	public void acceptTaskTitleChangeRequestByHigherAuthority() throws InterruptedException {
		actualRequestActionTakerHigherAuthority = commonMethods
				.accept_Task_Title_Change_Request_By_Higher_Authority(reportingAuthoritiesUserIds);
	}

	// Check notification message after accepted task title change request
	@Test(priority = 13)
	public void verify_Notification_Message_Of_Request_Accepted_By_Higher_Authority_At_Employee_Side()
			throws InterruptedException {
		commonMethods.login_Employee_And_Get_Name();

		notification.verifyNotificationMessage(actualRequestActionTakerHigherAuthority,
				"accepted your request for \"" + actualLastAssignTaskTitleInDayView + "\"");
	}

	// Check log message after task task title change request accepted in day view
	@Test(priority = 14)
	public void verify_Log_Message_After_Task_Title_Change_Request_Accepted() throws InterruptedException {
		commonMethods.goToDayView();

		String actualLastAssignTaskTitleInDayView = myTasks.checkLastTaskTitleInDayView();
		assertEquals(actualLastAssignTaskTitleInDayView, actualTaskTitleAfterClearAndUpdateFromDayView);

		String firstLogMessage = "has approved request for task title change in";
		String secondLogMessage = "has changed title from \"" + actualLastAssignTaskTitleInDayView + "\" to \""
				+ actualTaskTitleAfterClearAndUpdateFromDayView + "\" in";

		myActivities.verify_Log_Message(actualRequestActionTakerHigherAuthority, firstLogMessage, secondLogMessage,
				actualTaskTitleAfterClearAndUpdateFromDayView);
	}

	// Check request details in my request
	@Test(priority = 15)
	public void verify_Task_Request_In_My_Request_Section_After_Accepted_Task_Title_Change_Request()
			throws InterruptedException {
		if (actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar
				.equalsIgnoreCase(actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar)) {
			String taskOwnerNameWithTaskDate = actualTaskOwnerName + " on "
					+ actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar + "";
			requestFun.verify_Task_Request_In_My_Request_Section_By_Filtering_Request_Category("Accepted",
					taskOwnerNameWithTaskDate, actualRequestActionTakerHigherAuthority, requestActionForTaskTitleChange,
					actualLastAssignTaskTitleInDayView);
		} else {
			String taskOwnerNameWithTaskDate = actualTaskOwnerName + " on "
					+ actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar + " to "
					+ actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar + "";
			requestFun.verify_Task_Request_In_My_Request_Section_By_Filtering_Request_Category("Accepted",
					taskOwnerNameWithTaskDate, actualRequestActionTakerHigherAuthority, requestActionForTaskTitleChange,
					actualLastAssignTaskTitleInDayView);
		}
	}
}