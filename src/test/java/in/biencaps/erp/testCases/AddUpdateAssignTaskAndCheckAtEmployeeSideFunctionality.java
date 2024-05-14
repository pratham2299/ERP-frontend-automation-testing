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

	public String actualTaskOwnerName;

	@Test(priority = 1)
	public void verifyAssignTaskFromLevel() throws InterruptedException {
		myTasks = new MyTasksPage(driver);
		commonMethods = new CommonTestMethods();
		loginFun = new LoginAndForgotPasswordFunctionality();
		logOutFun = new LogoutFunctionality();
		requestFun = new RequestFunctionality();
		request = new RequestPage();
		dashboard = new DashboardPage();
		myActivities = new MyActivitiesFunctionality();
		webElementActions = new WebElementActions();

		logOutFun.verifyLogOutEmployee();

		commonMethods.verifyLoginEmployeeByGivingValidUserIdAndValidPassword(
				Constants.teamLeadLevelTesterEmployeeUserId, Constants.teamLeadLevelTesterEmployeePassword);

		actualTaskOwnerName = commonMethods
				.verifyEmployeeNameAfterLoggedIn(Constants.teamLeadLevelTesterEmployeeUserId);
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

				commonMethods.verifyToastMessageAfterTaskAddFromSidebar(
						actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar);
			} else {
				dashboard.clickOnLastArrowForLevelView();
				Thread.sleep(1000);
			}
		}
	}

	@Test(priority = 2)
	public void verifyTaskAddedFromLevelViewByHigherAuthorityInEmployeeDayView() throws InterruptedException {
		commonMethods.verifyAddedTaskCheckInDayView("after added task from level view to lower level employee",
				taskTitleInputWhileAddFromLevelViewSidebar, actualTaskStatusWhileAddTaskFromLevelViewSidebar,
				actualTaskPriorityWhileAddTaskFromLevelViewSidebar, actualTaskProjectWhileAddTaskFromLevelViewSidebar,
				actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualTaskDepartmentWhileAddTaskFromLevelViewSidebar, actualTaskOwnerName,
				actualTaskCommentWhileAddTaskFromLevelViewSidebar);

		myTasks.clickOnWeekButton();

		commonMethods.verifyNewAddedTaskInWeekView("after added task from level view to lower level employee",
				taskTitleInputWhileAddFromLevelViewSidebar,
				actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar);
	}

	@Test(priority = 3)
	public void verifyNotificationAfterAssignedTaskFromHigherAuthorityAtLowerLevelEmployeeSide()
			throws InterruptedException {
		logOutFun.verifyLogOutEmployee();

		commonMethods.verifyLoginEmployeeByGivingValidUserIdAndValidPassword(Constants.employeeUserId,
				Constants.employeePassword);

		String actualTaskAssignedEmployeeName = commonMethods.verifyEmployeeNameAfterLoggedIn(Constants.employeeUserId);
		log.info("Actual task assigned employee name at dashboard page is: " + actualTaskAssignedEmployeeName + "\n");

		commonMethods.verifyNotificationMessage(actualTaskOwnerName,
				"after added task from level view to lower level employee", "assigned you a task",
				taskTitleInputWhileAddFromLevelViewSidebar);
	}

	@Test(priority = 4)
	public void verifyAssignedTaskDetailsInDayViewAndWeekViewAndLogMessage() throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		commonMethods.verifyAddedTaskCheckInDayView("after added task from level view to lower level employee",
				taskTitleInputWhileAddFromLevelViewSidebar, actualTaskStatusWhileAddTaskFromLevelViewSidebar,
				actualTaskPriorityWhileAddTaskFromLevelViewSidebar, actualTaskProjectWhileAddTaskFromLevelViewSidebar,
				actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualTaskDepartmentWhileAddTaskFromLevelViewSidebar, actualTaskOwnerName,
				actualTaskCommentWhileAddTaskFromLevelViewSidebar);

		myTasks.clickOnWeekButton();

		commonMethods.verifyNewAddedTaskInWeekView("after added task from level view to lower level employee",
				taskTitleInputWhileAddFromLevelViewSidebar,
				actualScheduleDateFieldValueWhileAddTaskFromLevelViewSidebar,
				actualDueDateFieldValueWhileAddTaskFromLevelViewSidebar);

		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		myActivities.verifyLogMessage(actualTaskOwnerName, "has added new task",
				taskTitleInputWhileAddFromLevelViewSidebar);
	}

}
