package in.biencaps.erp.testCases;

import static org.testng.Assert.*;
import java.util.*;
import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import in.biencaps.erp.pages.*;
import in.biencaps.erp.utilities.*;

/* This class is extended BaseTest class.
 *  BaseTest has driver so you can inherite driver from BaseTest to this class
 */
public class SelfTaskUpdateFunctionality extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(SelfTaskUpdateFunctionality.class);

	public static String actualTaskTitleAfterClearAndUpdateFromDayView;
	public static String actualLastSelfTaskTitleInDayView;
	public static String actualLastSelfTaskStatusInDayView;
	public static String actualLastSelfTaskPriorityInDayView;
	public static String actualLastSelfTaskProjectInDayView;
	public static String actualLastSelfTaskDueDateInDayView;
	public static List<String> actualLastRowTaskDepartmentsInDayView;
	public static String actualLastSelfTaskVerifiedStatusInDayView;
	public static String actualLastSelfTaskCommentInDayView;

	protected MyTasksPage myTasks;
	protected CommonTestMethods commonMethods;
	protected MyActivitiesFunctionality myActivities;
	protected DashboardPage dashboard;
	protected LogoutFunctionality logOutFun;
	protected LoginAndForgotPasswordFunctionality loginFun;
	protected WebElementActions webElementActions;
	private String actualHigherAuthorityNameForViewTasks;

	@Test(priority = 1)
	public void verifyHigherAuthorityViewedTasks() throws InterruptedException {
		myTasks = new MyTasksPage(driver);
		commonMethods = new CommonTestMethods();
		myActivities = new MyActivitiesFunctionality();
		dashboard = new DashboardPage();
		logOutFun = new LogoutFunctionality();
		webElementActions = new WebElementActions();
		loginFun = new LoginAndForgotPasswordFunctionality();

		logOutFun.verifyLogOutEmployee();

		commonMethods.verifyLoginEmployeeByGivingValidUserIdAndValidPassword(
				Constants.teamLeadLevelTesterEmployeeUserId, Constants.teamLeadLevelTesterEmployeePassword);

		actualHigherAuthorityNameForViewTasks = commonMethods
				.verifyEmployeeNameAfterLoggedIn(Constants.teamLeadLevelTesterEmployeeUserId);
		log.info("Actual higher authority employee name for tasks view at dashboard page is: "
				+ actualHigherAuthorityNameForViewTasks + "\n");

		for (int i = 0; i < webElementActions.sizeOfListOfWebElement(dashboard.rolesForLevelView); i++) {
			String lastRoleForLevelView = dashboard.checkRoleForLevelView();
			log.info("Last role for level view is: " + lastRoleForLevelView);

			if (lastRoleForLevelView.equalsIgnoreCase(
					DataGenerator.employeeUserIdsAndRolesOnTestServer().get(Constants.employeeUserId))) {
				dashboard.clickOnRandomRoleForLevelView();
				Thread.sleep(1000);

				dashboard.clickOnEmployeeFromLevelView(LoginAndForgotPasswordFunctionality.actualEmployeeName);
				Thread.sleep(1000);

				commonMethods.verifyToastMessageInBlueColor("after viwed tasks by higher authority",
						"You viewed " + LoginAndForgotPasswordFunctionality.actualEmployeeName + "'s Tasks");
			} else {
				dashboard.clickOnLastArrowForLevelView();
				Thread.sleep(1000);
			}
		}

		logOutFun.verifyLogOutEmployee();

		commonMethods.verifyLoginEmployeeByGivingValidUserIdAndValidPassword(Constants.employeeUserId,
				Constants.employeePassword);

		loginFun.checkEmployeeNameAfterLoggedIn();

		myTasks.clickOnDayButton();
		Thread.sleep(1500);

		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.scrollHorizantally(1600);

		String actualTaskViwedEmployeeName = myTasks
				.checkViwedHigherAuthorityNameInDayView(actualHigherAuthorityNameForViewTasks);
		log.info("Actual task viewed higher authority employee name in day view is: " + actualTaskViwedEmployeeName);

		myTasks.scrollHorizantally(-1600);
	}

	// Prints last task details like title, status, priority, project, due date etc
	@Test(priority = 2)
	public void verifyLastTaskDetailsInDayView() throws InterruptedException {
		// Passed driver get from BaseTest to my tasks page i.e. page object model and
		// selenium methods, common test methods class
		myTasks.clickOnDayButton();
		Thread.sleep(1500);

		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		actualLastSelfTaskTitleInDayView = myTasks.checkLastTaskTitleInDayView();
		log.info("Actual Last self task title in day view is: " + actualLastSelfTaskTitleInDayView);

		actualLastSelfTaskStatusInDayView = myTasks.checkLastTaskStatusTextInDayView();
		log.info("Actual Last self task status in day view is: " + actualLastSelfTaskStatusInDayView);

		actualLastSelfTaskPriorityInDayView = myTasks.checkLastTaskPriorityTextInDayView();
		log.info("Actual Last self task priority in day view is: " + actualLastSelfTaskPriorityInDayView);

		try {
			actualLastSelfTaskProjectInDayView = myTasks.checkLastTaskProjectTextInDayView();
			log.info("Actual Last self task project in day view is: " + actualLastSelfTaskProjectInDayView);
		} catch (Exception e) {
			System.out.println("Last project not found in day view");
			actualLastSelfTaskProjectInDayView = "";
			log.info("Actual Last self task project in day view is: " + actualLastSelfTaskProjectInDayView);
		}

		myTasks.scrollHorizantally(1600);
		Thread.sleep(1000);

		actualLastSelfTaskDueDateInDayView = myTasks.checkLastTaskDueDateTextInDayView();
		log.info("Actual Last self task due date in day view is: " + actualLastSelfTaskDueDateInDayView);

		actualLastRowTaskDepartmentsInDayView = myTasks.checkLastRowTaskDepartmentTextsInDayView();
		log.info("Actual Last self task departments in day view is: " + actualLastRowTaskDepartmentsInDayView);

		actualLastSelfTaskVerifiedStatusInDayView = myTasks.checkLastTaskVerifiedStatusTextInDayView();
		log.info("Actual Last self task verified status in day view is: " + actualLastSelfTaskVerifiedStatusInDayView);

		myTasks.clickOnLastTaskCommentTextfieldInDayView();
		Thread.sleep(1000);

		myTasks.scrollUntilCommentTextfieldInUpdateTaskSidebar();
		Thread.sleep(1000);

		actualLastSelfTaskCommentInDayView = myTasks.checkLastTaskCommentTextInUpdateTaskSidebar();
		log.info("Actual Last self task comment in day view is: " + actualLastSelfTaskCommentInDayView + "\n");

		myTasks.clickOnCloseIconOfSidebarForUpdateTaskInDayView();
		Thread.sleep(1000);
	}

	// Update self task title, checks updated task title,
	// validate it whether it is matched with input task title
	// Also checks log message after updated task title
	@Test(priority = 3, enabled = false)
	public void verifyUpdateSelfTaskTitleFromDayViewAndCheckTaskTitleAndLogs() throws InterruptedException {
		myTasks.scrollHorizantally(-1500);

		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		String validTaskTitle = faker.book().title();

		myTasks.clickOnLastTaskTitleTextfieldInDayView();
		Thread.sleep(1000);

		myTasks.clearLastTaskTitleTextfieldInDayView();
		Thread.sleep(1000);

		myTasks.enterTaskTitleInLastTaskTitleTextfieldDayView(validTaskTitle);

		commonMethods.verifyToastMessage("after task clear and update in day view", "task title updated successfully");

		actualTaskTitleAfterClearAndUpdateFromDayView = myTasks.checkLastTaskTitleInDayView();
		log.info("Actual task title after clear and update from day view is: "
				+ actualTaskTitleAfterClearAndUpdateFromDayView);

		if (actualTaskTitleAfterClearAndUpdateFromDayView.length() > 26) {
			assertTrue(actualTaskTitleAfterClearAndUpdateFromDayView.startsWith(validTaskTitle));
		} else {
			assertEquals(actualTaskTitleAfterClearAndUpdateFromDayView, validTaskTitle);
		}

		myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "title",
				actualLastSelfTaskTitleInDayView, validTaskTitle, actualTaskTitleAfterClearAndUpdateFromDayView);
	}

	// Update self task status, checks updated task status,
	// validate it whether it is matched with input task status
	// Also checks log message after updated task status
	@Test(priority = 4)
	public void verifyUpdateSelfTaskStatusFromDayViewAndCheckTaskStatusAndLogs() throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.clickOnLastTaskStatusInDayView();
//		Thread.sleep(1000);

		List<String> taskStatusValuesFromDropdown = myTasks.listOfTaskStatusValuesFromDropdownInDayView();
		log.info("Task status values from dropdown are: " + taskStatusValuesFromDropdown);
		String LastTaskStatus = "";

		if (taskStatusValuesFromDropdown.isEmpty() == true) {
			log.info("Task status values from dropdown is empty");
		} else {

			do {
				// Generate a Last index within the range of the list
				int LastIndex = random.nextInt(taskStatusValuesFromDropdown.size());
				// Retrieve the number at the Last index
				LastTaskStatus = taskStatusValuesFromDropdown.get(LastIndex);
			} while (LastTaskStatus.equalsIgnoreCase("Submitted"));

			log.info("Actual selected task status value from dropdown in day view is: " + LastTaskStatus);

			myTasks.clickOnSelectedTaskStatusValueFromDropdownInDayView(LastTaskStatus);

			if (LastTaskStatus.equalsIgnoreCase(actualLastSelfTaskStatusInDayView)) {
				commonMethods.verifyToastMessage("after selected already exist task priority in day view",
						"task status already updated");

				myTasks.clickOnRefreshButtonInDayView();
				Thread.sleep(1000);

				String actualUpdatedTaskStatusInDayView = myTasks.checkLastTaskStatusTextInDayView();
				log.info("Actual updated task status in day view is: " + actualUpdatedTaskStatusInDayView);
				assertEquals(actualUpdatedTaskStatusInDayView, LastTaskStatus);
			} else {
				commonMethods.verifyToastMessage("after task status updated in day view",
						"task status updated successfully");

				myTasks.clickOnRefreshButtonInDayView();
				Thread.sleep(1000);

				String actualUpdatedTaskStatusInDayView = myTasks.checkLastTaskStatusTextInDayView();
				log.info("Actual updated task status in day view is: " + actualUpdatedTaskStatusInDayView);
				assertEquals(actualUpdatedTaskStatusInDayView, LastTaskStatus);

				myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "status",
						actualLastSelfTaskStatusInDayView, LastTaskStatus,
						actualTaskTitleAfterClearAndUpdateFromDayView);
			}
		}
	}

	// Update self task priority, checks updated task priority,
	// validate it whether it is matched with input task priority
	// Also checks log message after updated task priority
	@Test(priority = 5)
	public void verifyUpdateSelfTaskPriorityFromDayViewAndCheckTaskPriorityAndLogs() throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.clickOnLastTaskPriorityInDayView();
//		Thread.sleep(1000);

		String actualSelectedTaskPriorityValueFromDropdown = myTasks
				.checkSelectedTaskPriorityValueFromDropdownInDayView();
		log.info(
				"Actual selected task priority value from dropdown is: " + actualSelectedTaskPriorityValueFromDropdown);

		myTasks.clickOnSelectedTaskPriorityValueFromDropdownInDayView(actualSelectedTaskPriorityValueFromDropdown);

		if (actualSelectedTaskPriorityValueFromDropdown.equalsIgnoreCase(actualLastSelfTaskPriorityInDayView)) {
			commonMethods.verifyToastMessage("after selected already exist task priority in day view",
					"task priority already updated");

			myTasks.clickOnRefreshButtonInDayView();
			Thread.sleep(1000);

			String actualUpdatedTaskPriorityInDayView = myTasks.checkLastTaskPriorityTextInDayView();
			log.info("Actual updated task priority in day view is: " + actualUpdatedTaskPriorityInDayView);
			assertEquals(actualUpdatedTaskPriorityInDayView, actualSelectedTaskPriorityValueFromDropdown);
		} else {
			commonMethods.verifyToastMessage("after task priority updated in day view",
					"task priority updated successfully");

			myTasks.clickOnRefreshButtonInDayView();
			Thread.sleep(1000);

			String actualUpdatedTaskPriorityInDayView = myTasks.checkLastTaskPriorityTextInDayView();
			log.info("Actual updated task priority in day view is: " + actualUpdatedTaskPriorityInDayView);
			assertEquals(actualUpdatedTaskPriorityInDayView, actualSelectedTaskPriorityValueFromDropdown);

			myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "priority",
					actualLastSelfTaskPriorityInDayView, actualSelectedTaskPriorityValueFromDropdown,
					actualTaskTitleAfterClearAndUpdateFromDayView);
		}
	}

	// Update self task start and end time, checks updated task start and end time,
	// validate it whether it is matched with input task start and end time
	// Also checks log message after updated task start and end time
	@Test(priority = 6, enabled = false)
	public void verifyUpdateSelfTaskTimeFromDayViewAndCheckTaskTimeAndLogs() throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.clickOnLastRowStartTimeTextfield();

		String actualLastRowTaskStartTimeInDayView = myTasks.checkSelectedTimeOfLastRowTaskInDayView();
		log.info("Actual selected task start time in day view is: " + actualLastRowTaskStartTimeInDayView);
//		Thread.sleep(1000);

		myTasks.clickOnSelectedTaskTimeValueFromDropdownInDayView(actualLastRowTaskStartTimeInDayView);

		myTasks.clickOnRefreshButtonInDayView();

		commonMethods.verifyToastMessage("after task start time updated in day view",
				"Start-time Updated successfully");

		if (actualLastRowTaskStartTimeInDayView.substring(0, 1).equals("0")) {
			myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "start time",
					"No Time", "0" + actualLastRowTaskStartTimeInDayView,
					actualTaskTitleAfterClearAndUpdateFromDayView);
		} else {
			myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "start time",
					"No Time", actualLastRowTaskStartTimeInDayView, actualTaskTitleAfterClearAndUpdateFromDayView);
		}

		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.clickOnLastRowEndTimeTextfield();

		String actualLastRowTaskEndTimeInDayView = myTasks.checkSelectedTimeOfLastRowTaskInDayView();
		log.info("Actual selected task end time in day view is: " + actualLastRowTaskStartTimeInDayView);
//		Thread.sleep(1000);

		myTasks.clickOnSelectedTaskTimeValueFromDropdownInDayView(actualLastRowTaskEndTimeInDayView);

		myTasks.clickOnRefreshButtonInDayView();

		if (actualLastRowTaskEndTimeInDayView.equalsIgnoreCase(actualLastRowTaskStartTimeInDayView)) {
			commonMethods.verifyToastMessage("after selected task end time same as task start time in day view",
					"select end-time after start time");
		} else {
			commonMethods.verifyToastMessage("after task end time updated in day view",
					"End time Updated successfully");

			if (actualLastRowTaskEndTimeInDayView.substring(0, 1).equals("0")) {
				myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "end time",
						"No Time", "0" + actualLastRowTaskEndTimeInDayView,
						actualTaskTitleAfterClearAndUpdateFromDayView);
			} else {
				myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "end time",
						"No Time", actualLastRowTaskEndTimeInDayView, actualTaskTitleAfterClearAndUpdateFromDayView);
			}
		}
	}

	// Update self task due date, checks updated task due date,
	// validate it whether it is matched with input task due date
	// Also checks log message after updated task due date
	@Test(priority = 7)
	public void verifyUpdateSelfTaskDueDateFromDayViewAndCheckTaskDueDateAndLogs() throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.scrollHorizantally(1600);
		Thread.sleep(1000);

		myTasks.clickOnLastTaskDueDateInDayView();
//		Thread.sleep(1000);

		String dateValueFromDueDateString = actualLastSelfTaskDueDateInDayView.substring(8);
		int indexForTaskDueDateSelection = faker.number().numberBetween(Integer.parseInt(dateValueFromDueDateString),
				Integer.parseInt(dateValueFromDueDateString) + 5);

		String actualSelectedTaskDueDateValueFromCalendarInDayView = myTasks
				.checkSelectedTaskDueDateValueFromCalendarInDayView(indexForTaskDueDateSelection);
		log.info("Actual selected task due date value from calendar in day view is: "
				+ actualSelectedTaskDueDateValueFromCalendarInDayView);

		myTasks.clickOnSelectedTaskDueDateValueFromCalendarInDayView(
				actualSelectedTaskDueDateValueFromCalendarInDayView);

		if (dateValueFromDueDateString.startsWith("0") && actualSelectedTaskDueDateValueFromCalendarInDayView
				.equalsIgnoreCase(actualLastSelfTaskDueDateInDayView.substring(9))) {
			commonMethods.verifyToastMessage("after selected already exist task due date in day view",
					"task dueDate already updated");

			myTasks.clickOnRefreshButtonInDayView();
			Thread.sleep(1000);

			String actualUpdatedTaskDueDateInDayView = myTasks.checkLastTaskDueDateTextInDayView();
			log.info("Actual updated task due date in day view is: " + actualUpdatedTaskDueDateInDayView);
			assertEquals(actualUpdatedTaskDueDateInDayView, actualLastSelfTaskDueDateInDayView);
		} else if (!dateValueFromDueDateString.startsWith("0") && actualSelectedTaskDueDateValueFromCalendarInDayView
				.equalsIgnoreCase(actualLastSelfTaskDueDateInDayView.substring(8))) {
			commonMethods.verifyToastMessage("after selected already exist task due date in day view",
					"task dueDate already updated");

			myTasks.clickOnRefreshButtonInDayView();
			Thread.sleep(1000);

			String actualUpdatedTaskDueDateInDayView = myTasks.checkLastTaskDueDateTextInDayView();
			log.info("Actual updated task due date in day view is: " + actualUpdatedTaskDueDateInDayView);
			assertEquals(actualUpdatedTaskDueDateInDayView, actualLastSelfTaskDueDateInDayView);
		} else {
			commonMethods.verifyToastMessage("after task due date updated in day view",
					"task DueDate updated successfully");

			myTasks.clickOnRefreshButtonInDayView();
			Thread.sleep(1000);

			String actualUpdatedTaskDueDateInDayView = myTasks.checkLastTaskDueDateTextInDayView();
			log.info("Actual updated task due date in day view is: " + actualUpdatedTaskDueDateInDayView);

			myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "due date",
					actualLastSelfTaskDueDateInDayView, actualUpdatedTaskDueDateInDayView,
					actualTaskTitleAfterClearAndUpdateFromDayView);
		}
	}

	// Update self task department, checks updated task department,
	// validate it whether it is matched with input task department
	// Also checks log message after updated task department
	@Test(priority = 8)
	public void verifyUpdateSelfTaskDepartmentFromDayViewAndCheckTaskDepartmentAndLogs() throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.scrollHorizantally(1600);

		myTasks.clickOnLastTaskDepartmentInDayView();
//		Thread.sleep(1000);

		String actualSelectedTaskDepartmentValueFromDropdownInDayView = myTasks
				.checkSelectedTaskDepartmentValueFromDropdownInDayView();
		log.info("Actual selected task department value from dropdown in day view is: "
				+ actualSelectedTaskDepartmentValueFromDropdownInDayView);

		myTasks.clickOnSelectedTaskDepartmentValueFromDropdownInDayView(
				actualSelectedTaskDepartmentValueFromDropdownInDayView);

		commonMethods.verifyToastMessage("after task department updated in day view",
				"task Departments Updated successfully");

		myTasks.clickOnRefreshButtonInDayView();
		Thread.sleep(1000);

		String actualUpdatedTaskDepartmentInDayView = myTasks.checkLastTaskDepartmentTextInDayView();
		log.info("Actual updated task department in day view is: " + actualUpdatedTaskDepartmentInDayView);
		assertEquals(actualUpdatedTaskDepartmentInDayView, actualSelectedTaskDepartmentValueFromDropdownInDayView);

		if (actualLastRowTaskDepartmentsInDayView.size() > 1) {
			if (actualLastRowTaskDepartmentsInDayView.get(0)
					.equalsIgnoreCase(actualSelectedTaskDepartmentValueFromDropdownInDayView)) {
				myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "department tags",
						actualLastRowTaskDepartmentsInDayView.get(0) + ", "
								+ actualLastRowTaskDepartmentsInDayView.get(1),
						actualSelectedTaskDepartmentValueFromDropdownInDayView + ", "
								+ actualLastRowTaskDepartmentsInDayView.get(1),
						actualTaskTitleAfterClearAndUpdateFromDayView);
			} else if (actualLastRowTaskDepartmentsInDayView.get(1)
					.equalsIgnoreCase(actualSelectedTaskDepartmentValueFromDropdownInDayView)) {
				myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "department tags",
						actualLastRowTaskDepartmentsInDayView.get(0) + ", "
								+ actualLastRowTaskDepartmentsInDayView.get(1),
						actualLastRowTaskDepartmentsInDayView.get(0) + ", "
								+ actualSelectedTaskDepartmentValueFromDropdownInDayView,
						actualTaskTitleAfterClearAndUpdateFromDayView);
			} else {
				myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "department tags",
						actualLastRowTaskDepartmentsInDayView.get(0) + ", "
								+ actualLastRowTaskDepartmentsInDayView.get(1),
						actualLastRowTaskDepartmentsInDayView.get(0) + ", "
								+ actualLastRowTaskDepartmentsInDayView.get(1) + ", "
								+ actualSelectedTaskDepartmentValueFromDropdownInDayView,
						actualTaskTitleAfterClearAndUpdateFromDayView);
			}
		} else {
			if (actualLastRowTaskDepartmentsInDayView.get(0)
					.equalsIgnoreCase(actualSelectedTaskDepartmentValueFromDropdownInDayView)) {
				myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "department tags",
						actualLastRowTaskDepartmentsInDayView.get(0),
						actualSelectedTaskDepartmentValueFromDropdownInDayView,
						actualTaskTitleAfterClearAndUpdateFromDayView);
			} else {
				myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "department tags",
						actualLastRowTaskDepartmentsInDayView.get(0),
						actualLastRowTaskDepartmentsInDayView.get(0) + ", "
								+ actualSelectedTaskDepartmentValueFromDropdownInDayView,
						actualTaskTitleAfterClearAndUpdateFromDayView);
			}
		}
	}

	// Try to add gilink without selecting project
	// Check error message
	@Test(priority = 9)
	public void verifyUpdateSelfTaskGitlinkFromDayViewWithoutSelectingProject() throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.scrollHorizantally(1600);
		Thread.sleep(1000);

		myTasks.clickOnLastGitlinkIconInDayView();
//		Thread.sleep(1000);

		String actualTextForNotSelectingProjectOfTaskBeforeAddGitlinkInDayView = myTasks
				.checkTextForNotSelectingProjectBeforeGitlinkAddInDayView();
		log.info("Actual text for not selecting project of task before add gitlink in day view is: "
				+ actualTextForNotSelectingProjectOfTaskBeforeAddGitlinkInDayView);
		assertEquals(actualTextForNotSelectingProjectOfTaskBeforeAddGitlinkInDayView,
				"please assign a project to this task");

		myTasks.scrollHorizantally(-1600);
	}

	// Update self task project, checks updated task project,
	// validate it whether it is matched with input task project
	// Also checks log message after updated task project
	@Test(priority = 10, dependsOnMethods = "verifyUpdateSelfTaskGitlinkFromDayViewWithoutSelectingProject")
	public void verifyUpdateSelfTaskProjectFromDayViewAndCheckTaskProjectAndLogs() throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.clickOnLastTaskProjectInDayView();
//		Thread.sleep(1000);

		String actualSelectedTaskProjectValueFromDropdownInDayView = myTasks
				.checkSelectedTaskProjectValueFromDropdownInDayView();
		log.info("Actual selected task project value from dropdown is: "
				+ actualSelectedTaskProjectValueFromDropdownInDayView);

		myTasks.clickOnSelectedTaskProjectValueFromDropdownInDayView(
				actualSelectedTaskProjectValueFromDropdownInDayView);

		if (actualLastSelfTaskProjectInDayView.equalsIgnoreCase(actualSelectedTaskProjectValueFromDropdownInDayView)) {
			commonMethods.verifyToastMessage("after selected already exist task project in day view",
					"task Project already updated");

			myTasks.clickOnRefreshButtonInDayView();
			Thread.sleep(1000);

			String actualUpdatedTaskProjectInDayView = myTasks.checkLastTaskProjectTextInDayView();
			log.info("Actual updated task project in day view is: " + actualUpdatedTaskProjectInDayView);
			assertEquals(actualUpdatedTaskProjectInDayView, actualSelectedTaskProjectValueFromDropdownInDayView);
		} else {
			commonMethods.verifyToastMessage("after task project updated in day view",
					"task project updated successfully");

			myTasks.clickOnRefreshButtonInDayView();
			Thread.sleep(1000);

			String actualUpdatedTaskProjectInDayView = myTasks.checkLastTaskProjectTextInDayView();
			log.info("Actual updated task project in day view is: " + actualUpdatedTaskProjectInDayView);
			assertEquals(actualUpdatedTaskProjectInDayView, actualSelectedTaskProjectValueFromDropdownInDayView);

			if (actualLastSelfTaskProjectInDayView.equalsIgnoreCase("")) {
				myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "project",
						"No project", actualSelectedTaskProjectValueFromDropdownInDayView,
						actualTaskTitleAfterClearAndUpdateFromDayView);
			} else {
				myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "project",
						actualLastSelfTaskProjectInDayView, actualSelectedTaskProjectValueFromDropdownInDayView,
						actualTaskTitleAfterClearAndUpdateFromDayView);
			}
		}
	}

	// update self task gitlink by giving invalid gitlink
	// Checks error toast message
	@Test(priority = 11, dependsOnMethods = "verifyUpdateSelfTaskProjectFromDayViewAndCheckTaskProjectAndLogs")
	public void verifyUpdateSelfTaskGitlinkByGivingInvalidGitlinkFromDayViewAndCheckTaskGitlinkAndLogs()
			throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.scrollHorizantally(1600);
		Thread.sleep(1000);

		myTasks.clickOnLastGitlinkIconInDayView();
		Thread.sleep(1000);

		String invalidTaskGitlinkInput = faker.animal().name();

		myTasks.enterGitlinkValueInDayView(invalidTaskGitlinkInput);

		commonMethods.verifyToastMessage("after invalid task git link entered in day view", "Enter valid git link");

		myTasks.clickOnRefreshButtonInDayView();
		Thread.sleep(1000);
	}

	// Update self task gitlink, checks updated task gitlink,
	// validate it whether it is matched with input task gitlink
	// Also checks log message after updated task gitlink
	@Test(priority = 12, dependsOnMethods = "verifyUpdateSelfTaskProjectFromDayViewAndCheckTaskProjectAndLogs")
	public void verifyUpdateSelfTaskGitlinkByGivingValidGitlinkFromDayViewAndCheckTaskGitlinkAndLogs()
			throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.scrollHorizantally(1600);
		Thread.sleep(1000);

		myTasks.clickOnLastGitlinkIconInDayView();
		Thread.sleep(1000);

		String taskGitlinkInput = "https://github.com/" + faker.number().numberBetween(1, 10) + "";

		myTasks.enterGitlinkValueInDayView(taskGitlinkInput);

		commonMethods.verifyToastMessage("after task git link updated by giving valid gitlink in day view",
				"task gitLink Updated successfully");

		String actualGitlinkPushTextInDayView = myTasks.checkGitlinkPushedtextInDayView();
		log.info("Actual git link pushed text in day view is: " + actualGitlinkPushTextInDayView);
		assertEquals(actualGitlinkPushTextInDayView, "Pushed");

		myTasks.clickOnLastGitlinkIconInDayView();
		Thread.sleep(1000);

		String actualUpdatedTaskGitlinkValueInDayView = myTasks.checkNewUpdatedGitlinkInDayView();
		log.info("Actual updated task git link value in day view is: " + actualUpdatedTaskGitlinkValueInDayView);
		assertEquals(actualUpdatedTaskGitlinkValueInDayView, taskGitlinkInput);

		myTasks.clickOnRefreshButtonInDayView();
		Thread.sleep(1000);

//		ctm.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "has added git link in",
//				actualTaskTitleAfterClearAndUpdateFromDayView);
	}

	// Update self task status, checks updated task verified status,
	// validate it whether it is matched with input task verified status
	// Also checks log message after updated task verified status
	@Test(priority = 13, dependsOnMethods = "verifyUpdateSelfTaskGitlinkByGivingValidGitlinkFromDayViewAndCheckTaskGitlinkAndLogs")
	public void verifyUpdateSelfTaskVerifiedStatusFromDayViewAndCheckTaskVerifiedStatusAndLogs()
			throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.scrollHorizantally(1600);
		Thread.sleep(1000);

		myTasks.clickOnLastTaskVerifiedStatusInDayView();

		String actualSelectedTaskVerifiedStatusValueFromDropdownInDayView = myTasks
				.checkSelectedTaskVerifiedStatusValueFromDropdownInDayView();
		log.info("Actual selected task verified status value from dropdown is: "
				+ actualSelectedTaskVerifiedStatusValueFromDropdownInDayView);

		myTasks.clickOnSelectedTaskVerifiedStatusValueFromDropdownInDayView(
				actualSelectedTaskVerifiedStatusValueFromDropdownInDayView);

		if (actualLastSelfTaskVerifiedStatusInDayView
				.equalsIgnoreCase(actualSelectedTaskVerifiedStatusValueFromDropdownInDayView)) {
			commonMethods.verifyToastMessage("after task selected already exist verified status in day view",
					"task Verified status already updated");

			myTasks.clickOnRefreshButtonInDayView();
			Thread.sleep(1000);

			String actualUpdatedTaskVerifiedStatusInDayView = myTasks.checkLastTaskVerifiedStatusTextInDayView();
			log.info("Actual updated task verified status in day view is: " + actualUpdatedTaskVerifiedStatusInDayView);
			assertEquals(actualUpdatedTaskVerifiedStatusInDayView,
					actualSelectedTaskVerifiedStatusValueFromDropdownInDayView);
		} else {
			commonMethods.verifyToastMessage("after task verified status updated in day view",
					"task verification status Updated successfully");

			myTasks.clickOnRefreshButtonInDayView();
			Thread.sleep(1000);

			String actualUpdatedTaskVerifiedStatusInDayView = myTasks.checkLastTaskVerifiedStatusTextInDayView();
			log.info("Actual updated task verified status in day view is: " + actualUpdatedTaskVerifiedStatusInDayView);
			assertEquals(actualUpdatedTaskVerifiedStatusInDayView,
					actualSelectedTaskVerifiedStatusValueFromDropdownInDayView);

			myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "verification status",
					actualLastSelfTaskVerifiedStatusInDayView,
					actualSelectedTaskVerifiedStatusValueFromDropdownInDayView,
					actualTaskTitleAfterClearAndUpdateFromDayView);
		}
	}

	// Update self task comment
	// Check and validate task comment after update and input task comment matched
	// or not
	// Checks and validate log message after task comment add
	@Test(priority = 14)
	public void verifyUpdateSelfTaskCommentFromDayViewAndCheckTaskCommentAndLogs() throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.scrollHorizantally(1600);
		Thread.sleep(1000);

		String taskCommentInput = faker.app().name();

		myTasks.clickOnLastTaskCommentTextfieldInDayView();
		Thread.sleep(1000);

		myTasks.scrollUntilCommentTextfieldInUpdateTaskSidebar();
		Thread.sleep(1000);

		if (actualLastSelfTaskCommentInDayView.equalsIgnoreCase("") || actualLastSelfTaskCommentInDayView == null) {
			myTasks.enterTaskCommentValueInSidebarForUpdateTaskInDayView(taskCommentInput);
		} else {
			myTasks.clearTaskCommentTextfieldInSidebarForUpdateTaskInDayView();
			myTasks.enterTaskCommentValueInSidebarForUpdateTaskInDayView(taskCommentInput);
		}
		Thread.sleep(1000);

		myTasks.clickOnSaveButtonInSidebarForUpdateTaskInDayView();

		commonMethods.verifyToastMessage("after task comment updated in day view", "Task updated successfully");

		myTasks.clickOnRefreshButtonInDayView();
		Thread.sleep(2000);

		String actualUpdatedTastCommentValueInDayView = myTasks.checkLastTaskCommentTextInDayView();
		log.info("Actual updated task comment value in day view is: " + actualUpdatedTastCommentValueInDayView);

		if (actualUpdatedTastCommentValueInDayView.length() > 16) {
			assertTrue(actualUpdatedTastCommentValueInDayView.startsWith(taskCommentInput.substring(0, 16)));
		} else {
			assertEquals(actualUpdatedTastCommentValueInDayView, taskCommentInput);
		}

		myTasks.clickOnLastTaskCommentTextfieldInDayView();
		Thread.sleep(1500);

		myTasks.scrollUntilCommentTextfieldInUpdateTaskSidebar();
		Thread.sleep(1000);

		String actualUpdatedTastCommentValueOfSidebarForUpdateTaskInDayView = myTasks
				.checkLastTaskCommentTextInUpdateTaskSidebar();
		log.info("Actual updated task comment value of sidebar for update task in day view is: "
				+ actualUpdatedTastCommentValueOfSidebarForUpdateTaskInDayView);
		assertEquals(actualUpdatedTastCommentValueOfSidebarForUpdateTaskInDayView, taskCommentInput);

		myTasks.clickOnCloseIconOfSidebarForUpdateTaskInDayView();
		Thread.sleep(1000);

		if (actualLastSelfTaskCommentInDayView.equalsIgnoreCase("")) {
			myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "comment", "null",
					actualUpdatedTastCommentValueInDayView, actualTaskTitleAfterClearAndUpdateFromDayView);
		} else {
			myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "comment",
					actualLastSelfTaskCommentInDayView, actualUpdatedTastCommentValueInDayView,
					actualTaskTitleAfterClearAndUpdateFromDayView);
		}
	}
}