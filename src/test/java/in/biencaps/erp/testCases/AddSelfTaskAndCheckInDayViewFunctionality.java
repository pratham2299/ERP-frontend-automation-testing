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
public class AddSelfTaskAndCheckInDayViewFunctionality extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(AddSelfTaskAndCheckInDayViewFunctionality.class);

	// Created variables so that it can use for checking in day view
	private String taskTitleInputWhileAddFromMonthViewAtParticularDate;
	private String actualScheduleDateFieldValueWhileAddTaskFromMonthViewAtParticularDate;
	private String actualDueDateFieldValueWhileAddTaskFromMonthViewAtParticularDate;
	private String actualTaskDepartmentWhileAddTaskFromMonthViewAtParticularDate;
	private String actualTaskAssigneeNameWhileAddTaskFromMonthViewAtParticularDate;
	private String actualTaskStatusWhileAddTaskFromMonthViewAtParticularDate;
	private String actualTaskProjectWhileAddTaskFromMonthViewAtParticularDate;
	private String actualTaskPriorityWhileAddTaskFromMonthViewAtParticularDate;
	private String actualTaskCommentWhileAddTaskFromMonthViewAtParticularDate;

	private String taskTitleInputWhileAddFromMonthViewSidebar;
	private int scheduleDateInputWhileAddTaskFromMonthViewSidebar;
	private String actualTaskScheduleDateValueWhileAddTaskFromMonthViewSidebar;
	private String actualTaskDueDateValueWhileAddTaskFromMonthViewSidebar;
	private String actualTaskDepartmentWhileAddTaskFromMonthViewSidebar;
	private String actualTaskAssigneeNameWhileAddTaskFromMonthViewSidebar;
	private String actualTaskPriorityWhileAddTaskFromMonthViewSidebar;
	private String actualTaskStatusWhileAddTaskFromMonthViewSidebar;

	private String taskTitleInputWhileAddFromDayViewSidebar;
	private String actualScheduleDateFieldValueWhileAddTaskFromDayViewSidebar;
	private String actualDueDateFieldValueWhileAddTaskFromDayViewSidebar;
	private String actualTaskDepartmentWhileAddTaskFromDayViewSidebar;
	private String actualTaskAssigneeNameWhileAddTaskFromDayViewSidebar;
	private String actualTaskStatusWhileAddTaskFromDayViewSidebar;
	private String actualTaskProjectWhileAddTaskFromDayViewSidebar;
	private String actualTaskPriorityWhileAddTaskFromDayViewSidebar;
	private String actualTaskCommentWhileAddTaskFromDayViewSidebar;

	private String taskTitleInputWhileAddFromDayView;

	private String taskTitleInputWhileAddFromWeekViewSidebar;
	private int scheduleDateInputWhileAddTaskFromWeekViewSidebar;
	private String actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar;
	private String actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar;
	private String actualTaskDepartmentWhileAddTaskFromWeekViewSidebar;
	private String actualTaskAssigneeNameWhileAddTaskFromWeekViewSidebar;
	private String actualTaskPriorityWhileAddTaskFromWeekViewSidebar;
	private String actualTaskStatusWhileAddTaskFromWeekViewSidebar;
	private String actualTaskCommentWhileAddTaskFromWeekViewSidebar;

	private String taskTitleInputWhileAddFromWeekViewAtParticularDate;
	private String actualScheduleDateFieldValueWhileAddTaskFromWeekViewAtParticularDate;
	private String actualDueDateFieldValueWhileAddTaskFromWeekViewAtParticularDate;
	private String actualTaskDepartmentWhileAddTaskFromWeekViewAtParticularDate;
	private String actualTaskAssigneeNameWhileAddTaskFromWeekViewAtParticularDate;
	private String actualTaskStatusWhileAddTaskFromWeekViewAtParticularDate;
	private String actualTaskProjectWhileAddTaskFromWeekViewAtParticularDate;
	private String actualTaskPriorityWhileAddTaskFromWeekViewAtParticularDate;
	private String actualTaskCommentWhileAddTaskFromWeekViewAtParticularDate;

	public static int totalTasksCount = 0;

	protected WebElementActions webElementActions;
	protected MyTasksPage myTasks;
	protected DashboardPage dashboard;
	protected CommonTestMethods commonMethods;
	protected MyActivitiesFunctionality myActivitiesFun;

	// This method is checking toast message when not entering task title and
	// clicked on add task button
	@Test(priority = 1)
	public void verifyAddTaskFromSidebarWithoutEnteringTaskTitle() throws InterruptedException {
		webElementActions = new WebElementActions();
		myTasks = new MyTasksPage(driver);
		dashboard = new DashboardPage();
		commonMethods = new CommonTestMethods();
		myActivitiesFun = new MyActivitiesFunctionality();

		dashboard.clickOnMyTasksSection();

		myTasks.clickOnAddTaskButtonAtRightTopSide();
		Thread.sleep(1000);

		myTasks.clickOnAddTasksButtonInSidebar();

		commonMethods.verifyToastMessage("after leaving task title field empty", "Please Add the task first");

		myTasks.clickOnCancelSidebarIcon();
		Thread.sleep(1000);
	}

	// This method checks how many total tasks present in day view before adding new
	// task and initialized in our totalTasksCount variable
	@Test(priority = 2)
	public void verifyTotalTasksCountBeforeAddNewTask() throws InterruptedException {
		myTasks.clickOnDayButton();
		Thread.sleep(1500);

		myTasks.clickOnRefreshButtonInDayView();

		myTasks.scrollUptoBottomOfTaskDivInDayView();

		int totaTasksCountBeforeAddNewTaskInDayView = commonMethods.getTotalTasksCountInDayView();
		log.info("Total tasks count before add new task in day view is: " + totaTasksCountBeforeAddNewTaskInDayView
				+ "\n");

		totalTasksCount = totalTasksCount + totaTasksCountBeforeAddNewTaskInDayView;
	}

	// This method is added task from month view at particulkar date add task button
	@Test(priority = 3)
	public void verifyAddTaskFromMonthViewAtParticularDate() throws InterruptedException {
		myTasks.clickOnMonthButton();
		Thread.sleep(1500);

		// Generated current date from dataGenerator class from utilities package
		String formattedDate = DataGenerator.generateCurrentDate("yyyy-MM-dd");
		// Get only date value from generated current date
		String currentDate = formattedDate.substring(8);

		myTasks.clickOnAddTaskButtonInMonthViewAtParticularDate(currentDate);
		Thread.sleep(1000);

		taskTitleInputWhileAddFromMonthViewAtParticularDate = faker.book().title();

		myTasks.enterTaskNameWhileAddTaskFromSidebar(taskTitleInputWhileAddFromMonthViewAtParticularDate);

		// Prints entered task title and checks whether entered task title and task
		// title input matches or noy
		String actualEnteredTaskTitleFromMonthViewAtParticularDateWhileAddTask = myTasks
				.checkTaskNameAfterAddedWhileAddTaskFromSidebar(taskTitleInputWhileAddFromMonthViewAtParticularDate);
		log.info("Actual entered task title from month view and at particular date is: "
				+ actualEnteredTaskTitleFromMonthViewAtParticularDateWhileAddTask);

		if (actualEnteredTaskTitleFromMonthViewAtParticularDateWhileAddTask.length() > 26) {
			assertTrue(actualEnteredTaskTitleFromMonthViewAtParticularDateWhileAddTask
					.startsWith(taskTitleInputWhileAddFromMonthViewAtParticularDate.substring(0, 27)));
		} else {
			assertEquals(actualEnteredTaskTitleFromMonthViewAtParticularDateWhileAddTask,
					taskTitleInputWhileAddFromMonthViewAtParticularDate);
		}

		// Prints schedule date value and checks whether entered schedule date and input
		// schedule date matches or not
		actualScheduleDateFieldValueWhileAddTaskFromMonthViewAtParticularDate = myTasks
				.checkSelectedTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar();
		log.info("Actual schedule date field value while add task from month view and at particular date is: "
				+ actualScheduleDateFieldValueWhileAddTaskFromMonthViewAtParticularDate);

		// Prints due date value and checks whether entered due date and input
		// due date matches or not
		actualDueDateFieldValueWhileAddTaskFromMonthViewAtParticularDate = myTasks
				.checkSelectedTaskDueDateValueFromCalendarWhileAddTaskFromSidebar();
		log.info("Actual due date field value while add task from month view and at particular date is: "
				+ actualDueDateFieldValueWhileAddTaskFromMonthViewAtParticularDate);

		// Prints assignee value and checks whether assignee name and
		// logged in employee name matches or not
		actualTaskAssigneeNameWhileAddTaskFromMonthViewAtParticularDate = myTasks
				.checkTaskAssigneeNameWhileAddTaskFromSidebar(LoginAndForgotPasswordFunctionality.actualEmployeeName);
		log.info("Actual task assignee name while add task from month view and at particular date is: "
				+ actualTaskAssigneeNameWhileAddTaskFromMonthViewAtParticularDate);
		assertEquals(actualTaskAssigneeNameWhileAddTaskFromMonthViewAtParticularDate,
				LoginAndForgotPasswordFunctionality.actualEmployeeName);

		actualTaskDepartmentWhileAddTaskFromMonthViewAtParticularDate = myTasks
				.checkTaskDepartmentWhileAddTaskFromSidebar();
		log.info("Actual  task department while add task from month view and at particular date is: "
				+ actualTaskDepartmentWhileAddTaskFromMonthViewAtParticularDate);

		myTasks.clickOnPriorityDropdownWhileAddTaskFromSidebar();

		// Select random priority value
		myTasks.clickOnRandomValueFromDropdownWhileAddTaskFromSidebar();

		myTasks.clickOnPriorityFieldLabelWhileAddTaskFromSidebar();

		actualTaskPriorityWhileAddTaskFromMonthViewAtParticularDate = myTasks
				.checkTaskPriorityWhileAddTaskFromSidebar();
		log.info("Actual task priority while add task from month view and at particular date is: "
				+ actualTaskPriorityWhileAddTaskFromMonthViewAtParticularDate);

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

		actualTaskStatusWhileAddTaskFromMonthViewAtParticularDate = myTasks.checkTaskStatusWhileAddTaskFromSidebar();
		log.info("Actual task status while add task from month view and at particular date is: "
				+ actualTaskStatusWhileAddTaskFromMonthViewAtParticularDate);

		try {
			myTasks.clickOnProjectDropdownWhileAddTaskFromSidebar();

			// Select random project
			myTasks.clickOnRandomValueFromDropdownWhileAddTaskFromSidebar();

			myTasks.clickOnProjectFieldLabelWhileAddTaskFromSidebar();

			actualTaskProjectWhileAddTaskFromMonthViewAtParticularDate = myTasks
					.checkTaskProjectWhileAddingTaskFromSidebar();
			log.info("Actual task project while add task from month view and at particular date is: "
					+ actualTaskProjectWhileAddTaskFromMonthViewAtParticularDate);
		} catch (Exception e) {
			log.info("Project field not selected any value");
		}

		// Prints Entered taks comment and
		// check input comment and actual value matches or not
		String validTaskComment = faker.company().industry();
		myTasks.enterTaskCommentWhileAddingTaskFromsidebar(validTaskComment);

		actualTaskCommentWhileAddTaskFromMonthViewAtParticularDate = myTasks
				.checkTaskCommentWhileAddingTaskFromSidebar();
		log.info("Actual entered task comment while add task from month view and at particular date is: "
				+ actualTaskCommentWhileAddTaskFromMonthViewAtParticularDate);

		myTasks.clickOnAddTasksButtonInSidebar();

		commonMethods.verifyToastMessageAfterTaskAddFromSidebar(
				actualScheduleDateFieldValueWhileAddTaskFromMonthViewAtParticularDate);

		// Increased task total count
		totalTasksCount = totalTasksCount + 1;
	}

	// Checks added task details from month view at particular date in day view
	@Test(dependsOnMethods = "verifyAddTaskFromMonthViewAtParticularDate")
	public void verifyAddedTaskFromMonthViewAtParticularDateCheckInDayView() throws InterruptedException {
		myTasks.clickOnDayButton();
		Thread.sleep(1500);

		myTasks.clickOnTodayButton();

		verifyAddedTaskCheckInDayView("from month view at particular date",
				taskTitleInputWhileAddFromMonthViewAtParticularDate,
				actualTaskStatusWhileAddTaskFromMonthViewAtParticularDate,
				actualTaskPriorityWhileAddTaskFromMonthViewAtParticularDate,
				actualTaskProjectWhileAddTaskFromMonthViewAtParticularDate,
				actualScheduleDateFieldValueWhileAddTaskFromMonthViewAtParticularDate,
				actualDueDateFieldValueWhileAddTaskFromMonthViewAtParticularDate,
				actualTaskDepartmentWhileAddTaskFromMonthViewAtParticularDate,
				actualTaskAssigneeNameWhileAddTaskFromMonthViewAtParticularDate,
				actualTaskCommentWhileAddTaskFromMonthViewAtParticularDate);
	}

	// Checks total tasks count after added task from month view at particular date
	// and also
	// checks log message
	@Test(dependsOnMethods = "verifyAddedTaskFromMonthViewAtParticularDateCheckInDayView")
	public void verifyTotalTasksCountAndLogMessagesInDayViewAfterNewTaskAddFromMonthViewAtParticularDate()
			throws InterruptedException {
		commonMethods.verifyTotalTasksCountInDayViewAfterAddedOrDeletedTask(totalTasksCount);

		myActivitiesFun.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "has added new task",
				taskTitleInputWhileAddFromMonthViewAtParticularDate);
	}

	// Add task from month view sidebar by giving only title and date
	@Test(priority = 4)
	public void verifyAddTaskFromMonthViewSidebarByGivingOnlyTitleAndDate() throws InterruptedException {
		myTasks.clickOnMonthButton();

		myTasks.clickOnAddTaskButtonAtRightTopSide();
		Thread.sleep(1000);

		taskTitleInputWhileAddFromMonthViewSidebar = faker.book().title();

		myTasks.enterTaskNameWhileAddTaskFromSidebar(taskTitleInputWhileAddFromMonthViewSidebar);

		String actualEnteredTaskTitleFromMonthViewSidebarWhileAddTask = myTasks
				.checkTaskNameAfterAddedWhileAddTaskFromSidebar(taskTitleInputWhileAddFromMonthViewSidebar);
		log.info("Actual entered task title from month view sidebar is: "
				+ actualEnteredTaskTitleFromMonthViewSidebarWhileAddTask);

		if (actualEnteredTaskTitleFromMonthViewSidebarWhileAddTask.length() > 26) {
			assertTrue(actualEnteredTaskTitleFromMonthViewSidebarWhileAddTask
					.startsWith(taskTitleInputWhileAddFromMonthViewSidebar.substring(0, 27)));
		} else {
			assertEquals(actualEnteredTaskTitleFromMonthViewSidebarWhileAddTask,
					taskTitleInputWhileAddFromMonthViewSidebar);
		}

		myTasks.clickOnStartDateCalendarFieldWhileAddTaskFromSidebar();

		String formattedDate = DataGenerator.generateCurrentDate("yyyy-MM-dd");

		if (formattedDate.substring(8, 9).equalsIgnoreCase("0")) {
			scheduleDateInputWhileAddTaskFromMonthViewSidebar = Integer.parseInt(formattedDate.substring(9));
			int fakeNumber = faker.number().numberBetween(1, 5);
			scheduleDateInputWhileAddTaskFromMonthViewSidebar = scheduleDateInputWhileAddTaskFromMonthViewSidebar
					- fakeNumber;
			myTasks.selectTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar(
					scheduleDateInputWhileAddTaskFromMonthViewSidebar);
		} else {
			scheduleDateInputWhileAddTaskFromMonthViewSidebar = Integer.parseInt(formattedDate.substring(8));
			scheduleDateInputWhileAddTaskFromMonthViewSidebar = faker.number().numberBetween(
					scheduleDateInputWhileAddTaskFromMonthViewSidebar - 1,
					scheduleDateInputWhileAddTaskFromMonthViewSidebar - 5);
			myTasks.selectTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar(
					scheduleDateInputWhileAddTaskFromMonthViewSidebar);
		}

		myTasks.clickOnDepartmentLabelWhileAddTaskFromSidebar();

		actualTaskScheduleDateValueWhileAddTaskFromMonthViewSidebar = myTasks
				.checkSelectedTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar();
		log.info("Actual task schedule date value while add task from month view sidebar is: "
				+ actualTaskScheduleDateValueWhileAddTaskFromMonthViewSidebar);

		actualTaskDueDateValueWhileAddTaskFromMonthViewSidebar = myTasks
				.checkSelectedTaskDueDateValueFromCalendarWhileAddTaskFromSidebar();
		log.info("Actual task due date value while add task from month view sidebar is: "
				+ actualTaskDueDateValueWhileAddTaskFromMonthViewSidebar);

		actualTaskAssigneeNameWhileAddTaskFromMonthViewSidebar = myTasks
				.checkTaskAssigneeNameWhileAddTaskFromSidebar(LoginAndForgotPasswordFunctionality.actualEmployeeName);
		log.info("Actual task assignee name while add task from month view sidebar is: "
				+ actualTaskAssigneeNameWhileAddTaskFromMonthViewSidebar);
		assertEquals(actualTaskAssigneeNameWhileAddTaskFromMonthViewSidebar,
				LoginAndForgotPasswordFunctionality.actualEmployeeName);

		actualTaskDepartmentWhileAddTaskFromMonthViewSidebar = myTasks.checkTaskDepartmentWhileAddTaskFromSidebar();
		log.info("Actual task department while add task from month view sidebar is: "
				+ actualTaskDepartmentWhileAddTaskFromMonthViewSidebar);

		actualTaskPriorityWhileAddTaskFromMonthViewSidebar = myTasks.checkTaskPriorityWhileAddTaskFromSidebar();
		log.info("Actual task priority while add task from month view sidebar is: "
				+ actualTaskPriorityWhileAddTaskFromMonthViewSidebar);

		myTasks.clickOnStatusDropdownWhileAddTaskFromSidebar();

		List<String> taskStatusValuesFromDropdownWhileAddTask = myTasks
				.checkSelectedTaskStatusValueFromDropdownWhileAddTaskFromSidebar();
		log.info("Task status values from dropdown while add task from month view sidebar are: "
				+ taskStatusValuesFromDropdownWhileAddTask);

		String randomTaskStatus;

		do {
			// Generate a random index within the range of the list
			int randomIndex = random.nextInt(taskStatusValuesFromDropdownWhileAddTask.size());
			// Retrieve the number at the random index
			randomTaskStatus = taskStatusValuesFromDropdownWhileAddTask.get(randomIndex);
		} while (randomTaskStatus.equalsIgnoreCase("Submitted"));
		log.info("Actual selected task status value from dropdown while add task from month view sidebar is: "
				+ randomTaskStatus);

		myTasks.selectRandomStatusWhileAddTaskFromSidebar(randomTaskStatus);

		myTasks.clickOnStatusFieldLabelWhileAddTaskFromSidebar();

		actualTaskStatusWhileAddTaskFromMonthViewSidebar = myTasks.checkTaskStatusWhileAddTaskFromSidebar();
		log.info("Actual task status while add task from month view sidebar is: "
				+ actualTaskStatusWhileAddTaskFromMonthViewSidebar);

		String actualTaskCommentWhileAddTaskFromMonthViewSidebar = myTasks.checkTaskCommentWhileAddingTaskFromSidebar();
		log.info("Actual task comment while add task from month view sidebar is: "
				+ actualTaskCommentWhileAddTaskFromMonthViewSidebar);

		myTasks.clickOnAddTasksButtonInSidebar();

		commonMethods
				.verifyToastMessageAfterTaskAddFromSidebar(actualTaskScheduleDateValueWhileAddTaskFromMonthViewSidebar);

		totalTasksCount = totalTasksCount + 1;
	}

	// This method checks added task details from month view sidebar in day view
	@Test(dependsOnMethods = "verifyAddTaskFromMonthViewSidebarByGivingOnlyTitleAndDate")
	public void verifyAddedTaskFromMonthViewSidebarCheckInDayView() throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		myTasks.clickOnCalendarInDayView();
		Thread.sleep(1000);

		myTasks.clickOnParticularDateFromCalendarInDayView(scheduleDateInputWhileAddTaskFromMonthViewSidebar);

		String dateWithDayInCurrentDayView = myTasks.checkDateInCurrentDayView();
		String[] seperateDateAndDayInCurrentDayView = dateWithDayInCurrentDayView.split(",");

		String day = seperateDateAndDayInCurrentDayView[0].trim();
		log.info("Day in current day view is: " + day);
		String date = seperateDateAndDayInCurrentDayView[1].trim();
		log.info("Date in current day view is: " + date);

		String formattedDate = DataGenerator.generateCurrentDate("yyyy-MM-dd");

		for (int i = 0; i < 10; i++) {
			dateWithDayInCurrentDayView = myTasks.checkDateInCurrentDayView();
			seperateDateAndDayInCurrentDayView = dateWithDayInCurrentDayView.split(",");

			day = seperateDateAndDayInCurrentDayView[0].trim();
			log.info("Day in current day view is: " + day);
			date = seperateDateAndDayInCurrentDayView[1].trim();
			log.info("Date in current day view is: " + date);

			if (date.substring(0, 2).equalsIgnoreCase(formattedDate.substring(8))) {
				verifyAddedTaskCheckInDayView("from month view sidebar", taskTitleInputWhileAddFromMonthViewSidebar,
						actualTaskStatusWhileAddTaskFromMonthViewSidebar,
						actualTaskPriorityWhileAddTaskFromMonthViewSidebar, "",
						actualTaskScheduleDateValueWhileAddTaskFromMonthViewSidebar,
						actualTaskDueDateValueWhileAddTaskFromMonthViewSidebar,
						actualTaskDepartmentWhileAddTaskFromMonthViewSidebar,
						actualTaskAssigneeNameWhileAddTaskFromMonthViewSidebar, "");

				String rollOverTaskColor = myTasks.checkRollOverTaskColor();
				log.info("Roll over task color is: " + rollOverTaskColor);
				assertEquals(rollOverTaskColor, "rgba(255, 15, 0, 1)");

				break;
			} else {
				verifyAddedTaskCheckInDayView("from month view sidebar", taskTitleInputWhileAddFromMonthViewSidebar,
						actualTaskStatusWhileAddTaskFromMonthViewSidebar,
						actualTaskPriorityWhileAddTaskFromMonthViewSidebar, "",
						actualTaskScheduleDateValueWhileAddTaskFromMonthViewSidebar,
						actualTaskDueDateValueWhileAddTaskFromMonthViewSidebar,
						actualTaskDepartmentWhileAddTaskFromMonthViewSidebar,
						actualTaskAssigneeNameWhileAddTaskFromMonthViewSidebar, "");

				if (i > 0) {
					String rollOverTaskColor = myTasks.checkRollOverTaskColor();
					log.info("Roll over task color is: " + rollOverTaskColor);
					assertEquals(rollOverTaskColor, "rgba(255, 15, 0, 1)");
				}

				myTasks.clickOnNextDayIconInDayView();
			}
		}
	}

	// Checks total tasks count after added task from month view sidebar and also
	// checks log message
	@Test(dependsOnMethods = "verifyAddedTaskFromMonthViewSidebarCheckInDayView")
	public void verifyTotalTasksCountAndLogMessagesInDayViewAfterAddingAnotherTaskFromMonthViewSidebar()
			throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		commonMethods
				.verifyTotalTasksCountInDayViewAfterAddedOrDeletedTask(commonMethods.getTotalTasksCountInDayView());

		myActivitiesFun.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName,
				"has added new task on " + actualTaskScheduleDateValueWhileAddTaskFromMonthViewSidebar + "",
				taskTitleInputWhileAddFromMonthViewSidebar);
	}

	// This method add task from day view
	@Test(priority = 5)
	public void verifyAddNewTaskFromDayView() throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		myTasks.scrollUptoBottomOfTaskDivInDayView();

		myTasks.clickOnAddTasksButtonInDayView();
		Thread.sleep(1000);

		taskTitleInputWhileAddFromDayView = faker.book().title();

		myTasks.enterTaskTitleInLastTaskTitleTextfieldDayView(taskTitleInputWhileAddFromDayView);

		myTasks.clickOnRefreshButtonInDayView();
		Thread.sleep(1000);

		commonMethods.verifyToastMessage("after task add from day view", "task added successfully");

		String actualTaskTitleAddFromDayView = myTasks.checkLastTaskTitleInDayView();
		log.info("Actual task title add from day view is: " + actualTaskTitleAddFromDayView);

		if (actualTaskTitleAddFromDayView.length() > 26) {
			assertTrue(actualTaskTitleAddFromDayView.startsWith(taskTitleInputWhileAddFromDayView.substring(0, 27)));
		} else {
			assertEquals(actualTaskTitleAddFromDayView, taskTitleInputWhileAddFromDayView);
		}

		totalTasksCount = totalTasksCount + 1;
	}

	// Checks total tasks count after added task from day view and also
	// checks log message
	@Test(dependsOnMethods = "verifyAddNewTaskFromDayView")
	public void verifyTotalTasksCountAndLogMessagesInDayViewAfterAddingAnotherTaskFromDayView()
			throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		commonMethods.verifyTotalTasksCountInDayViewAfterAddedOrDeletedTask(totalTasksCount);

		myActivitiesFun.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "has added new task",
				taskTitleInputWhileAddFromDayView);
	}

	// This method add task from day view sidebar
	@Test(priority = 6)
	public void verifyAddNewTaskFromDayViewSidebar() throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		myTasks.clickOnAddTaskButtonAtRightTopSide();
		Thread.sleep(1000);

		taskTitleInputWhileAddFromDayViewSidebar = faker.book().title();

		myTasks.enterTaskNameWhileAddTaskFromSidebar(taskTitleInputWhileAddFromDayViewSidebar);

		String actualEnteredTaskTitleFromDayViewSidebarWhileAddTask = myTasks
				.checkTaskNameAfterAddedWhileAddTaskFromSidebar(taskTitleInputWhileAddFromDayViewSidebar);
		log.info("Actual entered task title from day view sidebar is: "
				+ actualEnteredTaskTitleFromDayViewSidebarWhileAddTask);

		if (actualEnteredTaskTitleFromDayViewSidebarWhileAddTask.length() > 26) {
			assertTrue(actualEnteredTaskTitleFromDayViewSidebarWhileAddTask
					.startsWith(taskTitleInputWhileAddFromDayViewSidebar.substring(0, 27)));
		} else {
			assertEquals(actualEnteredTaskTitleFromDayViewSidebarWhileAddTask,
					taskTitleInputWhileAddFromDayViewSidebar);
		}

		String formattedDate = DataGenerator.generateCurrentDate("yyyy-MM-dd");

		actualScheduleDateFieldValueWhileAddTaskFromDayViewSidebar = myTasks
				.checkSelectedTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar();
		log.info("Actual schedule date field value while add task from day view sidebar is: "
				+ actualScheduleDateFieldValueWhileAddTaskFromDayViewSidebar);

		myTasks.clickOnEndDateCalendarFieldWhileAddTaskFromSidebar();

		if (formattedDate.substring(8).startsWith("0")) {
			int dueDateInput = Integer.parseInt(formattedDate.substring(9));
			int fakeNumber = faker.number().numberBetween(1, 5);
			dueDateInput = dueDateInput + fakeNumber;
			myTasks.selectTaskDueDateValueFromCalendarWhileAddTaskFromSidebar(dueDateInput);
		} else {
			int dueDateInput = Integer.parseInt(formattedDate.substring(8));
			dueDateInput = faker.number().numberBetween(dueDateInput + 1, dueDateInput + 5);
			myTasks.selectTaskDueDateValueFromCalendarWhileAddTaskFromSidebar(dueDateInput);
		}

		myTasks.clickOnDepartmentLabelWhileAddTaskFromSidebar();

		actualDueDateFieldValueWhileAddTaskFromDayViewSidebar = myTasks
				.checkSelectedTaskDueDateValueFromCalendarWhileAddTaskFromSidebar();
		log.info("Actual due date field value while add task from day view sidebar is: "
				+ actualDueDateFieldValueWhileAddTaskFromDayViewSidebar);

		actualTaskAssigneeNameWhileAddTaskFromDayViewSidebar = myTasks
				.checkTaskAssigneeNameWhileAddTaskFromSidebar(LoginAndForgotPasswordFunctionality.actualEmployeeName);
		log.info("Actual task assignee name while add task from day view sidebar is: "
				+ actualTaskAssigneeNameWhileAddTaskFromDayViewSidebar);
		assertEquals(actualTaskAssigneeNameWhileAddTaskFromDayViewSidebar,
				LoginAndForgotPasswordFunctionality.actualEmployeeName);

		actualTaskDepartmentWhileAddTaskFromDayViewSidebar = myTasks.checkTaskDepartmentWhileAddTaskFromSidebar();
		log.info("Actual task department while add task from day view sidebar is: "
				+ actualTaskDepartmentWhileAddTaskFromDayViewSidebar);

		myTasks.clickOnPriorityDropdownWhileAddTaskFromSidebar();

		myTasks.clickOnRandomValueFromDropdownWhileAddTaskFromSidebar();

		myTasks.clickOnPriorityFieldLabelWhileAddTaskFromSidebar();

		actualTaskPriorityWhileAddTaskFromDayViewSidebar = myTasks.checkTaskPriorityWhileAddTaskFromSidebar();
		log.info("Actual task priority while add task from day view sidebar is: "
				+ actualTaskPriorityWhileAddTaskFromDayViewSidebar);

		myTasks.clickOnStatusDropdownWhileAddTaskFromSidebar();

		List<String> taskStatusValuesFromDropdownWhileAddTask = myTasks
				.checkSelectedTaskStatusValueFromDropdownWhileAddTaskFromSidebar();
		log.info("Task status values from dropdown while add task from day view sidebar are: "
				+ taskStatusValuesFromDropdownWhileAddTask);

		String randomTaskStatus;

		do {
			// Generate a random index within the range of the list
			int randomIndex = random.nextInt(taskStatusValuesFromDropdownWhileAddTask.size());
			// Retrieve the number at the random index
			randomTaskStatus = taskStatusValuesFromDropdownWhileAddTask.get(randomIndex);
		} while (randomTaskStatus.equalsIgnoreCase("Submitted"));

		log.info("Actual selected task status value from dropdown while add task from day view sidebar is: "
				+ randomTaskStatus);

		myTasks.selectRandomStatusWhileAddTaskFromSidebar(randomTaskStatus);

		myTasks.clickOnStatusFieldLabelWhileAddTaskFromSidebar();

		actualTaskStatusWhileAddTaskFromDayViewSidebar = myTasks.checkTaskStatusWhileAddTaskFromSidebar();
		log.info("Actual task status while add task from day view sidebar is: "
				+ actualTaskStatusWhileAddTaskFromDayViewSidebar);

		try {
			myTasks.clickOnProjectDropdownWhileAddTaskFromSidebar();

			myTasks.clickOnRandomValueFromDropdownWhileAddTaskFromSidebar();

			myTasks.clickOnProjectFieldLabelWhileAddTaskFromSidebar();

			actualTaskProjectWhileAddTaskFromDayViewSidebar = myTasks.checkTaskProjectWhileAddingTaskFromSidebar();
			log.info("Actual task project while add task from day view sidebar is: "
					+ actualTaskProjectWhileAddTaskFromDayViewSidebar);
		} catch (Exception e) {
			log.info("Project field is not selected any value");
		}

		String validTaskComment = faker.company().name();
		myTasks.enterTaskCommentWhileAddingTaskFromsidebar(validTaskComment);

		actualTaskCommentWhileAddTaskFromDayViewSidebar = myTasks.checkTaskCommentWhileAddingTaskFromSidebar();
		log.info("Actual entered task comment while add task from day view sidebar is: "
				+ actualTaskCommentWhileAddTaskFromDayViewSidebar);
		assertEquals(actualTaskCommentWhileAddTaskFromDayViewSidebar, validTaskComment);

		myTasks.clickOnAddTasksButtonInSidebar();

		commonMethods
				.verifyToastMessageAfterTaskAddFromSidebar(actualScheduleDateFieldValueWhileAddTaskFromDayViewSidebar);

		totalTasksCount = totalTasksCount + 1;
	}

	// This method checks added task details from day view sidenbar in day view
	@Test(dependsOnMethods = "verifyAddNewTaskFromDayViewSidebar")
	public void verifyAddedTaskFromDayViewSidebarCheckInDayView() throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		myTasks.clickOnRefreshButtonInDayView();
		Thread.sleep(1000);

		for (int i = 0; i < 10; i++) {
			String dateWithDayInCurrentDayView = myTasks.checkDateInCurrentDayView();
			String[] seperateDateAndDayInCurrentDayView = dateWithDayInCurrentDayView.split(",");

			String day = seperateDateAndDayInCurrentDayView[0].trim();
			log.info("Day in current day view is: " + day);
			String date = seperateDateAndDayInCurrentDayView[1].trim();
			log.info("Date in current day view is: " + date);

			if (date.substring(0, 2)
					.equalsIgnoreCase(actualDueDateFieldValueWhileAddTaskFromDayViewSidebar.substring(8))) {
				verifyAddedTaskCheckInDayView("from day view sidebar", taskTitleInputWhileAddFromDayViewSidebar,
						actualTaskStatusWhileAddTaskFromDayViewSidebar,
						actualTaskPriorityWhileAddTaskFromDayViewSidebar,
						actualTaskProjectWhileAddTaskFromDayViewSidebar,
						actualScheduleDateFieldValueWhileAddTaskFromDayViewSidebar,
						actualDueDateFieldValueWhileAddTaskFromDayViewSidebar,
						actualTaskDepartmentWhileAddTaskFromDayViewSidebar,
						actualTaskAssigneeNameWhileAddTaskFromDayViewSidebar,
						actualTaskCommentWhileAddTaskFromDayViewSidebar);

				break;
			} else {
				verifyAddedTaskCheckInDayView("from day view sidebar", taskTitleInputWhileAddFromDayViewSidebar,
						actualTaskStatusWhileAddTaskFromDayViewSidebar,
						actualTaskPriorityWhileAddTaskFromDayViewSidebar,
						actualTaskProjectWhileAddTaskFromDayViewSidebar,
						actualScheduleDateFieldValueWhileAddTaskFromDayViewSidebar,
						actualDueDateFieldValueWhileAddTaskFromDayViewSidebar,
						actualTaskDepartmentWhileAddTaskFromDayViewSidebar,
						actualTaskAssigneeNameWhileAddTaskFromDayViewSidebar,
						actualTaskCommentWhileAddTaskFromDayViewSidebar);

				myTasks.clickOnNextDayIconInDayView();
			}
		}

	}

	// Checks total tasks count after added task from day view sidebar and also
	// checks log message
	@Test(dependsOnMethods = "verifyAddedTaskFromDayViewSidebarCheckInDayView")
	public void verifyTotalTasksCountAndLogMessagesInDayViewAfterAddingAnotherTaskFromDayViewSidebar()
			throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		commonMethods.verifyTotalTasksCountInDayViewAfterAddedOrDeletedTask(totalTasksCount);

		myActivitiesFun.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "has added new task",
				taskTitleInputWhileAddFromDayViewSidebar);
	}

	// This method add new task from week view at particular date
	@Test(priority = 7, enabled = false)
	public void verifyAddTaskFromWeekViewAtParticularDate() throws InterruptedException {
		myTasks.clickOnWeekButton();
		Thread.sleep(3000);

		myTasks.clickOnAddTasksButtonInWeekView();
		Thread.sleep(1500);

		taskTitleInputWhileAddFromWeekViewAtParticularDate = faker.book().title();

		myTasks.enterTaskNameWhileAddTaskFromSidebar(taskTitleInputWhileAddFromWeekViewAtParticularDate);

		String actualEnteredTaskTitleFromWeekViewAtParticularDateWhileAddTask = myTasks
				.checkTaskNameAfterAddedWhileAddTaskFromSidebar(taskTitleInputWhileAddFromWeekViewAtParticularDate);
		log.info("Actual entered task title from week view and at particular date is: "
				+ actualEnteredTaskTitleFromWeekViewAtParticularDateWhileAddTask);

		if (actualEnteredTaskTitleFromWeekViewAtParticularDateWhileAddTask.length() > 26) {
			assertTrue(actualEnteredTaskTitleFromWeekViewAtParticularDateWhileAddTask
					.startsWith(taskTitleInputWhileAddFromWeekViewAtParticularDate.substring(0, 27)));
		} else {
			assertEquals(actualEnteredTaskTitleFromWeekViewAtParticularDateWhileAddTask,
					taskTitleInputWhileAddFromWeekViewAtParticularDate);
		}

		String formattedDate = DataGenerator.generateCurrentDate("yyyy-MM-dd");

		myTasks.clickOnStartDateCalendarFieldWhileAddTaskFromSidebar();

		if (formattedDate.substring(8).startsWith("0")) {
			int dueDateInput = Integer.parseInt(formattedDate.substring(9));
			dueDateInput = faker.number().numberBetween(dueDateInput + 1, dueDateInput + 5);
			myTasks.selectTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar(dueDateInput);
		} else {
			int dueDateInput = Integer.parseInt(formattedDate.substring(8));
			dueDateInput = faker.number().numberBetween(dueDateInput + 1, dueDateInput + 5);
			myTasks.selectTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar(dueDateInput);
		}

		actualScheduleDateFieldValueWhileAddTaskFromWeekViewAtParticularDate = myTasks
				.checkSelectedTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar();
		log.info("Actual schedule date field value while add task from week view and at particular date is: "
				+ actualScheduleDateFieldValueWhileAddTaskFromWeekViewAtParticularDate);

		myTasks.clickOnDepartmentLabelWhileAddTaskFromSidebar();

		actualDueDateFieldValueWhileAddTaskFromWeekViewAtParticularDate = myTasks
				.checkSelectedTaskDueDateValueFromCalendarWhileAddTaskFromSidebar();
		log.info("Actual due date field value while add task from week view and at particular date is: "
				+ actualDueDateFieldValueWhileAddTaskFromWeekViewAtParticularDate);

		actualTaskAssigneeNameWhileAddTaskFromWeekViewAtParticularDate = myTasks
				.checkTaskAssigneeNameWhileAddTaskFromSidebar(LoginAndForgotPasswordFunctionality.actualEmployeeName);
		log.info("Actual task assignee name while add task from week view and at particular date is: "
				+ actualTaskAssigneeNameWhileAddTaskFromWeekViewAtParticularDate);
		assertEquals(actualTaskAssigneeNameWhileAddTaskFromWeekViewAtParticularDate,
				LoginAndForgotPasswordFunctionality.actualEmployeeName);

		actualTaskDepartmentWhileAddTaskFromWeekViewAtParticularDate = myTasks
				.checkTaskDepartmentWhileAddTaskFromSidebar();
		log.info("Actual task department while add task from week view and at particular date is: "
				+ actualTaskDepartmentWhileAddTaskFromWeekViewAtParticularDate);

		myTasks.clickOnPriorityDropdownWhileAddTaskFromSidebar();

		myTasks.clickOnRandomValueFromDropdownWhileAddTaskFromSidebar();

		myTasks.clickOnPriorityFieldLabelWhileAddTaskFromSidebar();

		actualTaskPriorityWhileAddTaskFromWeekViewAtParticularDate = myTasks.checkTaskPriorityWhileAddTaskFromSidebar();
		log.info("Actual task priority while add task from week view and at particular date is: "
				+ actualTaskPriorityWhileAddTaskFromWeekViewAtParticularDate);

		myTasks.clickOnStatusDropdownWhileAddTaskFromSidebar();

		List<String> taskStatusValuesFromDropdownWhileAddTask = myTasks
				.checkSelectedTaskStatusValueFromDropdownWhileAddTaskFromSidebar();
		log.info("Task status values from dropdown while add task from week view and at particular date are: "
				+ taskStatusValuesFromDropdownWhileAddTask);

		String randomTaskStatus;

		do {
			// Generate a random index within the range of the list
			int randomIndex = random.nextInt(taskStatusValuesFromDropdownWhileAddTask.size());
			// Retrieve the number at the random index
			randomTaskStatus = taskStatusValuesFromDropdownWhileAddTask.get(randomIndex);
		} while (randomTaskStatus.equalsIgnoreCase("Submitted"));

		log.info(
				"Actual selected task status value from dropdown while add task from week view and at particular date is: "
						+ randomTaskStatus);

		myTasks.selectRandomStatusWhileAddTaskFromSidebar(randomTaskStatus);

		myTasks.clickOnStatusFieldLabelWhileAddTaskFromSidebar();

		actualTaskStatusWhileAddTaskFromWeekViewAtParticularDate = myTasks.checkTaskStatusWhileAddTaskFromSidebar();
		log.info("Actual task status while add task from week view and at particular date is: "
				+ actualTaskStatusWhileAddTaskFromWeekViewAtParticularDate);

		try {
			myTasks.clickOnProjectDropdownWhileAddTaskFromSidebar();

			myTasks.clickOnRandomValueFromDropdownWhileAddTaskFromSidebar();

			myTasks.clickOnProjectFieldLabelWhileAddTaskFromSidebar();

			actualTaskProjectWhileAddTaskFromWeekViewAtParticularDate = myTasks
					.checkTaskProjectWhileAddingTaskFromSidebar();
			log.info("Actual task project while add task from week view and at particular date is: "
					+ actualTaskProjectWhileAddTaskFromWeekViewAtParticularDate);
		} catch (Exception e) {
			System.out.println("Project field not selected any value");
		}

		String validTaskComment = faker.company().industry();
		myTasks.enterTaskCommentWhileAddingTaskFromsidebar(validTaskComment);

		actualTaskCommentWhileAddTaskFromWeekViewAtParticularDate = myTasks
				.checkTaskCommentWhileAddingTaskFromSidebar();
		log.info("Actual entered task comment while add task from week view and at particular date is: "
				+ actualTaskCommentWhileAddTaskFromWeekViewAtParticularDate);
		assertEquals(actualTaskCommentWhileAddTaskFromWeekViewAtParticularDate, validTaskComment);

		myTasks.clickOnAddTasksButtonInSidebar();

		commonMethods.verifyToastMessageAfterTaskAddFromSidebar(
				actualScheduleDateFieldValueWhileAddTaskFromWeekViewAtParticularDate);

		totalTasksCount = totalTasksCount + 1;
	}

	// This method checks added task from week view sidebar at particular date in
	// week view task list
	@Test(dependsOnMethods = "verifyAddTaskFromWeekViewAtParticularDate", enabled = false)
	public void verifyNewAddedTaskFromWeekViewSidebarAtParticularDateInWeekView() throws InterruptedException {
		myTasks.clickOnWeekButton();
		Thread.sleep(1000);

		verifyNewAddedTaskInWeekView("from week view sidebar at particular date",
				taskTitleInputWhileAddFromWeekViewAtParticularDate,
				actualScheduleDateFieldValueWhileAddTaskFromWeekViewAtParticularDate,
				actualDueDateFieldValueWhileAddTaskFromWeekViewAtParticularDate);
	}

	// This method checks added task from week view sidebar at particular date in
	// day view task list
	@Test(dependsOnMethods = "verifyAddTaskFromWeekViewAtParticularDate", enabled = false)
	public void verifyAddedTaskFromWeekViewAtParticularDateCheckInDayView() throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		myTasks.clickOnCalendarInDayView();
		Thread.sleep(1000);

		if (actualTaskDueDateValueWhileAddTaskFromMonthViewSidebar.substring(8, 9).equalsIgnoreCase("0")) {
			myTasks.clickOnParticularDateFromCalendarInDayView(
					Integer.parseInt(actualTaskDueDateValueWhileAddTaskFromMonthViewSidebar.substring(9)));
		} else {
			myTasks.clickOnParticularDateFromCalendarInDayView(
					Integer.parseInt(actualTaskDueDateValueWhileAddTaskFromMonthViewSidebar.substring(8)));
		}

		verifyAddedTaskCheckInDayView("from week view at particular date",
				taskTitleInputWhileAddFromWeekViewAtParticularDate,
				actualTaskStatusWhileAddTaskFromWeekViewAtParticularDate,
				actualTaskPriorityWhileAddTaskFromWeekViewAtParticularDate,
				actualTaskProjectWhileAddTaskFromWeekViewAtParticularDate,
				actualScheduleDateFieldValueWhileAddTaskFromWeekViewAtParticularDate,
				actualDueDateFieldValueWhileAddTaskFromWeekViewAtParticularDate,
				actualTaskDepartmentWhileAddTaskFromWeekViewAtParticularDate,
				actualTaskAssigneeNameWhileAddTaskFromWeekViewAtParticularDate,
				actualTaskCommentWhileAddTaskFromWeekViewAtParticularDate);
	}

	// Checks total tasks count after added task from week view sidebar at
	// particular date and also
	// checks log message
	@Test(dependsOnMethods = "verifyAddedTaskFromWeekViewAtParticularDateCheckInDayView", enabled = false)
	public void verifyTotalTasksCountAndLogMessagesInDayViewAfterTaskAddedFromWeekViewAtParticularDate()
			throws InterruptedException {
		commonMethods.verifyTotalTasksCountInDayViewAfterAddedOrDeletedTask(totalTasksCount);

		myActivitiesFun.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "has added new task",
				taskTitleInputWhileAddFromWeekViewAtParticularDate);
	}

	// This method add new task from week view sidebar with only title, date, and
	// comment
	@Test(priority = 8)
	public void verifyAddTaskFromWeekViewSidebarByGivingOnlyTitleDateAndComment() throws InterruptedException {
		myTasks.clickOnWeekButton();
		Thread.sleep(3000);

		myTasks.clickOnAddTaskButtonAtRightTopSide();
		Thread.sleep(1500);

		taskTitleInputWhileAddFromWeekViewSidebar = faker.book().title();

		myTasks.enterTaskNameWhileAddTaskFromSidebar(taskTitleInputWhileAddFromWeekViewSidebar);
		Thread.sleep(1000);

		String actualEnteredTaskTitleFromWeekViewSidebarWhileAddTask = myTasks
				.checkTaskNameAfterAddedWhileAddTaskFromSidebar(taskTitleInputWhileAddFromWeekViewSidebar);
		log.info("Actual entered task title from week view sidebar is: "
				+ actualEnteredTaskTitleFromWeekViewSidebarWhileAddTask);

		if (actualEnteredTaskTitleFromWeekViewSidebarWhileAddTask.length() > 26) {
			assertTrue(actualEnteredTaskTitleFromWeekViewSidebarWhileAddTask
					.startsWith(taskTitleInputWhileAddFromWeekViewSidebar.substring(0, 27)));
		} else {
			assertEquals(actualEnteredTaskTitleFromWeekViewSidebarWhileAddTask,
					taskTitleInputWhileAddFromWeekViewSidebar);
		}

		myTasks.clickOnStartDateCalendarFieldWhileAddTaskFromSidebar();

		String formattedDate = DataGenerator.generateCurrentDate("yyyy-MM-dd");

		if (formattedDate.substring(8, 9).equalsIgnoreCase("0")) {
			scheduleDateInputWhileAddTaskFromWeekViewSidebar = Integer.parseInt(formattedDate.substring(9));
			int fakeNumber = faker.number().numberBetween(1, 5);
			scheduleDateInputWhileAddTaskFromWeekViewSidebar = scheduleDateInputWhileAddTaskFromWeekViewSidebar
					- fakeNumber;
			myTasks.selectTaskDueDateValueFromCalendarWhileAddTaskFromSidebar(
					scheduleDateInputWhileAddTaskFromWeekViewSidebar);
		} else {
			scheduleDateInputWhileAddTaskFromWeekViewSidebar = Integer.parseInt(formattedDate.substring(8));
			int fakeNumber = faker.number().numberBetween(1, 5);
			scheduleDateInputWhileAddTaskFromWeekViewSidebar = scheduleDateInputWhileAddTaskFromWeekViewSidebar
					- fakeNumber;
			myTasks.selectTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar(
					scheduleDateInputWhileAddTaskFromWeekViewSidebar);
		}

		myTasks.clickOnDepartmentLabelWhileAddTaskFromSidebar();

		actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar = myTasks
				.checkSelectedTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar();
		log.info("Actual task schedule date value while add task from week view sidebar is: "
				+ actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar);

		myTasks.clickOnEndDateCalendarFieldWhileAddTaskFromSidebar();

		if (formattedDate.substring(8, 9).equalsIgnoreCase("0")) {
			int dueDateInput = Integer
					.parseInt(actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar.substring(9));
			dueDateInput = faker.number().numberBetween(dueDateInput + 1, dueDateInput + 5);
			myTasks.selectTaskDueDateValueFromCalendarWhileAddTaskFromSidebar(dueDateInput);
		} else {
			int dueDateInput = Integer
					.parseInt(actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar.substring(8));
			dueDateInput = faker.number().numberBetween(dueDateInput + 1, dueDateInput + 5);
			myTasks.selectTaskDueDateValueFromCalendarWhileAddTaskFromSidebar(dueDateInput);
		}

		myTasks.clickOnDepartmentLabelWhileAddTaskFromSidebar();

		actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar = myTasks
				.checkSelectedTaskDueDateValueFromCalendarWhileAddTaskFromSidebar();
		log.info("Actual task due date value while add task from week view sidebar is: "
				+ actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar);

		actualTaskAssigneeNameWhileAddTaskFromWeekViewSidebar = myTasks
				.checkTaskAssigneeNameWhileAddTaskFromSidebar(LoginAndForgotPasswordFunctionality.actualEmployeeName);
		log.info("Actual task assignee name while add task from week view sidebar is: "
				+ actualTaskAssigneeNameWhileAddTaskFromWeekViewSidebar);
		assertEquals(actualTaskAssigneeNameWhileAddTaskFromWeekViewSidebar,
				LoginAndForgotPasswordFunctionality.actualEmployeeName);

		actualTaskDepartmentWhileAddTaskFromWeekViewSidebar = myTasks.checkTaskDepartmentWhileAddTaskFromSidebar();
		log.info("Actual task department while add task from week view sidebar is: "
				+ actualTaskDepartmentWhileAddTaskFromWeekViewSidebar);

		actualTaskPriorityWhileAddTaskFromWeekViewSidebar = myTasks.checkTaskPriorityWhileAddTaskFromSidebar();
		log.info("Actual task priority while add task from week view sidebar is: "
				+ actualTaskPriorityWhileAddTaskFromWeekViewSidebar);

		myTasks.clickOnStatusDropdownWhileAddTaskFromSidebar();

		List<String> taskStatusValuesFromDropdownWhileAddTask = myTasks
				.checkSelectedTaskStatusValueFromDropdownWhileAddTaskFromSidebar();
		log.info("Task status values from dropdown while add task from week view sidebar are: "
				+ taskStatusValuesFromDropdownWhileAddTask);

		String randomTaskStatus;

		do {
			// Generate a random index within the range of the list
			int randomIndex = random.nextInt(taskStatusValuesFromDropdownWhileAddTask.size());
			// Retrieve the number at the random index
			randomTaskStatus = taskStatusValuesFromDropdownWhileAddTask.get(randomIndex);
		} while (randomTaskStatus.equalsIgnoreCase("Submitted"));

		log.info("Actual selected task status value from dropdown while add task from week view sidebar is: "
				+ randomTaskStatus);

		myTasks.selectRandomStatusWhileAddTaskFromSidebar(randomTaskStatus);

		myTasks.clickOnStatusFieldLabelWhileAddTaskFromSidebar();

		actualTaskStatusWhileAddTaskFromWeekViewSidebar = myTasks.checkTaskStatusWhileAddTaskFromSidebar();
		log.info("Actual task status while add task from week view sidebar is: "
				+ actualTaskStatusWhileAddTaskFromWeekViewSidebar);

		String validTaskComment = faker.company().name();
		myTasks.enterTaskCommentWhileAddingTaskFromsidebar(validTaskComment);

		actualTaskCommentWhileAddTaskFromWeekViewSidebar = myTasks.checkTaskCommentWhileAddingTaskFromSidebar();
		log.info("Actual task comment while add task from week view sidebar is: "
				+ actualTaskCommentWhileAddTaskFromWeekViewSidebar);
		assertEquals(actualTaskCommentWhileAddTaskFromWeekViewSidebar, validTaskComment);

		myTasks.clickOnAddTasksButtonInSidebar();

		commonMethods
				.verifyToastMessageAfterTaskAddFromSidebar(actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar);

		totalTasksCount = totalTasksCount + 1;
	}

	// This method checks added task from week view sidebar in
	// week view task list
	@Test(dependsOnMethods = "verifyAddTaskFromWeekViewSidebarByGivingOnlyTitleDateAndComment")
	public void verifyNewAddedTaskFromWeekViewSidebarInWeekView() throws InterruptedException {
		myTasks.clickOnWeekButton();
		Thread.sleep(1000);

		verifyNewAddedTaskInWeekView("from week view sidebar", taskTitleInputWhileAddFromWeekViewSidebar,
				actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar,
				actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar);
	}

	// This method checks added task from week view sidebar in
	// day view task list
	@Test(dependsOnMethods = "verifyAddTaskFromWeekViewSidebarByGivingOnlyTitleDateAndComment")
	public void verifyAddedTaskFromWeekViewSidebarCheckInDayView() throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		myTasks.clickOnCalendarInDayView();
		Thread.sleep(1000);

		myTasks.clickOnParticularDateFromCalendarInDayView(scheduleDateInputWhileAddTaskFromWeekViewSidebar);

		String dateWithDayInCurrentDayView = myTasks.checkDateInCurrentDayView();
		String[] seperateDateAndDayInCurrentDayView = dateWithDayInCurrentDayView.split(",");

		String day = seperateDateAndDayInCurrentDayView[0].trim();
		log.info("Day in current day view is: " + day);
		String date = seperateDateAndDayInCurrentDayView[1].trim();
		log.info("Date in current day view is: " + date);

		String formattedDate = DataGenerator.generateCurrentDate("yyyy-MM-dd");

		for (int i = 0; i < 10; i++) {
			dateWithDayInCurrentDayView = myTasks.checkDateInCurrentDayView();
			seperateDateAndDayInCurrentDayView = dateWithDayInCurrentDayView.split(",");

			day = seperateDateAndDayInCurrentDayView[0].trim();
			log.info("Day in current day view is: " + day);
			date = seperateDateAndDayInCurrentDayView[1].trim();
			log.info("Date in current day view is: " + date);

			if (date.substring(0, 2)
					.equalsIgnoreCase(actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar.substring(8))) {
				verifyAddedTaskCheckInDayView("from week view sidebar", taskTitleInputWhileAddFromWeekViewSidebar,
						actualTaskStatusWhileAddTaskFromWeekViewSidebar,
						actualTaskPriorityWhileAddTaskFromWeekViewSidebar, "",
						actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar,
						actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar,
						actualTaskDepartmentWhileAddTaskFromWeekViewSidebar,
						actualTaskAssigneeNameWhileAddTaskFromWeekViewSidebar,
						actualTaskCommentWhileAddTaskFromWeekViewSidebar);

				break;
			} else {
				verifyAddedTaskCheckInDayView("from week view sidebar", taskTitleInputWhileAddFromWeekViewSidebar,
						actualTaskStatusWhileAddTaskFromWeekViewSidebar,
						actualTaskPriorityWhileAddTaskFromWeekViewSidebar, "",
						actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar,
						actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar,
						actualTaskDepartmentWhileAddTaskFromWeekViewSidebar,
						actualTaskAssigneeNameWhileAddTaskFromWeekViewSidebar,
						actualTaskCommentWhileAddTaskFromWeekViewSidebar);

				if (i > 0 && Integer.parseInt(actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar
						.substring(8)) <= Integer.parseInt(formattedDate.substring(8))) {
					String rollOverTaskColor = myTasks.checkRollOverTaskColor();
					log.info("Roll over task color is: " + rollOverTaskColor);
					assertEquals(rollOverTaskColor, "rgba(255, 15, 0, 1)");
				}

				myTasks.clickOnNextDayIconInDayView();
			}
		}
	}

	// Checks total tasks count after added task from week view sidebar and also
	// checks log message
	@Test(dependsOnMethods = "verifyAddedTaskFromWeekViewSidebarCheckInDayView")
	public void verifyTotalTasksCountAndLogMessagesInDayViewAfterAddingAnotherTaskFromWeekViewSidebar()
			throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnTodayButton();
		Thread.sleep(1000);

		commonMethods.verifyTotalTasksCountInDayViewAfterAddedOrDeletedTask(totalTasksCount);

		myActivitiesFun.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "has added new task",
				taskTitleInputWhileAddFromWeekViewSidebar);
	}

	// Checks added task details in day view
	public void verifyAddedTaskCheckInDayView(String message, String taskTitle, String taskStatus, String taskPriority,
			String taskProject, String taskStartDate, String taskDueDate, String taskDepartment, String taskAssignee,
			String taskComment) throws InterruptedException {
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

	public void verifyNewAddedTaskInWeekView(String message, String taskTitle, String taskScheduleDate,
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
}
