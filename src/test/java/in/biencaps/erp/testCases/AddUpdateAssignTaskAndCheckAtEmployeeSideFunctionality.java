package in.biencaps.erp.testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import in.biencaps.erp.pages.DashboardPage;
import in.biencaps.erp.pages.MyTasksPage;
import in.biencaps.erp.pages.RequestPage;
import in.biencaps.erp.utilities.CommonTestMethods;
import in.biencaps.erp.utilities.Constants;
import in.biencaps.erp.utilities.DataGenerator;
import in.biencaps.erp.utilities.WebElementActions;

public class AddUpdateAssignTaskAndCheckAtEmployeeSideFunctionality extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(AddUpdateAssignTaskAndCheckAtEmployeeSideFunctionality.class);

	protected MyTasksPage myTasks;
	protected DashboardPage dashboard;
	protected CommonTestMethods commonMethods;
	protected LogoutFunctionality logOutFun;
	protected LoginAndForgotPasswordFunctionality loginFun;
	protected RequestFunctionality requestFun;
	protected RequestPage request;
	protected MyActivitiesFunctionality myActivities;
	protected WebElementActions webElementActions;

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

	@Test(priority = 1)
	public void verify_Assign_Task_From_Level() throws InterruptedException {
		myTasks = new MyTasksPage(driver);
		commonMethods = new CommonTestMethods();
		loginFun = new LoginAndForgotPasswordFunctionality();
		logOutFun = new LogoutFunctionality();
		requestFun = new RequestFunctionality();
		request = new RequestPage();
		dashboard = new DashboardPage();
		myActivities = new MyActivitiesFunctionality();
		webElementActions = new WebElementActions();

		logOutFun.verify_LogOut_Employee();

		commonMethods.verify_Login_Employee_By_Giving_Valid_User_Id_And_Valid_Password(
				Constants.teamLeadLevelTesterEmployeeUserId, Constants.teamLeadLevelTesterEmployeePassword);

		actualTaskOwnerName = commonMethods
				.verify_Employee_Name_After_Logged_In(Constants.teamLeadLevelTesterEmployeeUserId);
		log.info("Actual task owner employee name at dashboard page is: " + actualTaskOwnerName + "\n");

		for (int i = 0; i < webElementActions.sizeOfListOfWebElement(dashboard.rolesForLevelView); i++) {
			String lastRoleForLevelView = dashboard.checkRoleForLevelView();
			log.info("Last role for level view is: " + lastRoleForLevelView);

			if (lastRoleForLevelView.equalsIgnoreCase(
					DataGenerator.employeeUserIdsAndRolesOnTestServer().get(Constants.employeeUserId))) {
				dashboard.clickOnRandomRoleForLevelView();
				Thread.sleep(1000);

				dashboard.clickOnEmployeeFromLevelView(LoginAndForgotPasswordFunctionality.actualEmployeeName);
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
				assertEquals(actualTaskAssigneeNameWhileAddTaskFromLevelViewSidebar,
						LoginAndForgotPasswordFunctionality.actualEmployeeName);

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

				myTasks.clickOnStatusDropdownWhileAddTaskFromSidebar();

				// Select random status value except submitted value from dropdoiwn list
				List<String> taskStatusValuesFromDropdownWhileAddTask = myTasks
						.checkSelectedTaskStatusValueFromDropdownWhileAddTaskFromSidebar();

				String randomTaskStatus;

				do {
					// Generate a random index within the range of the list
					int randomIndex = random.nextInt(taskStatusValuesFromDropdownWhileAddTask.size());
					// Retrieve the number at the random index
					randomTaskStatus = taskStatusValuesFromDropdownWhileAddTask.get(randomIndex);
				} while (randomTaskStatus.equalsIgnoreCase("Submitted"));
				log.info(
						"Actual selected task status value from dropdown while add task from month view and at particular date is: "
								+ randomTaskStatus);

				myTasks.selectRandomStatusWhileAddTaskFromSidebar(randomTaskStatus);

				myTasks.clickOnStatusFieldLabelWhileAddTaskFromSidebar();

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
					log.info("Project field not selected any value");
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
				dashboard.clickOnLastArrowForLevelView();
				Thread.sleep(1000);
			}
		}
	}

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

	@Test(priority = 3)
	public void verify_Notification_After_Assigned_Task_From_Higher_Authority_At_Lower_Level_Employee_Side()
			throws InterruptedException {
		logOutFun.verify_LogOut_Employee();

		commonMethods.verify_Login_Employee_By_Giving_Valid_User_Id_And_Valid_Password(Constants.employeeUserId,
				Constants.employeePassword);

		String actualTaskAssignedEmployeeName = commonMethods
				.verify_Employee_Name_After_Logged_In(Constants.employeeUserId);
		log.info("Actual task assigned employee name at dashboard page is: " + actualTaskAssignedEmployeeName + "\n");

		commonMethods.verifyNotificationMessage(actualTaskOwnerName,
				"after added task from level view to lower level employee", "assigned you a task",
				taskTitleInputWhileAddFromLevelViewSidebar);
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
	public void verifyAssignTaskTitleUpdateAndCheckRequestInMyRequestCard() throws InterruptedException {
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

		requestFun
				.verify_Request_In_My_Requests_Card(LoginAndForgotPasswordFunctionality.actualEmployeeName,
						"wants to change title from \"" + actualLastAssignTaskTitleInDayView + "\" to \""
								+ actualTaskTitleAfterClearAndUpdateFromDayView + "\" in",
						actualLastAssignTaskTitleInDayView);
	}
}
