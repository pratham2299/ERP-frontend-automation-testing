package in.biencaps.erp.testCases;

import static org.testng.Assert.*;
import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import in.biencaps.erp.pages.*;
import in.biencaps.erp.utilities.*;

/* This class is extended BaseTest class.
 *  BaseTest has driver so you can inherite driver from BaseTest to this class
 */
public class AddSelfTaskAndCheckInDayViewTests extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(AddSelfTaskAndCheckInDayViewTests.class);

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
	protected MyActivitiesTests myActivitiesFun;

	// This method is checking toast message when not entering task title and
	// clicked on add task button
	@Test(priority = 1)
	public void verify_Add_Task_From_Sidebar_Without_Entering_Task_Title() throws InterruptedException {
		webElementActions = new WebElementActions();
		myTasks = new MyTasksPage(driver);
		dashboard = new DashboardPage();
		commonMethods = new CommonTestMethods();
		myActivitiesFun = new MyActivitiesTests();

		dashboard.clickOnMyTasksSection();

		myTasks.clickOnAddTaskButtonAtRightTopSide();
		Thread.sleep(1000);

		myTasks.clickOnAddTasksButtonInSidebar();

		commonMethods.verify_Toast_Message("after leaving task title field empty", "Please Add the task first");

		myTasks.clickOnCancelSidebarIcon();
		Thread.sleep(1000);
	}

	// This method checks how many total tasks present in day view before adding new
	// task and initialized in our totalTasksCount variable
	@Test(priority = 2)
	public void verify_Total_Tasks_Count_Before_Add_New_Task() throws InterruptedException {
		commonMethods.goToDayView();

		int totaTasksCountBeforeAddNewTaskInDayView = commonMethods.get_Total_Tasks_Count_In_Day_View();
		log.info("Total tasks count before add new task in day view is: " + totaTasksCountBeforeAddNewTaskInDayView
				+ "\n");

		totalTasksCount = totalTasksCount + totaTasksCountBeforeAddNewTaskInDayView;
	}

	// This method is added task from month view at particular date add task button
	@Test(priority = 3)
	public void verify_Add_Task_From_Month_View_At_Current_Date() throws InterruptedException {
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
				.checkTaskAssigneeNameWhileAddTaskFromSidebar();
		log.info("Actual task assignee name while add task from month view and at particular date is: "
				+ actualTaskAssigneeNameWhileAddTaskFromMonthViewAtParticularDate);
		assertEquals(actualTaskAssigneeNameWhileAddTaskFromMonthViewAtParticularDate,
				LoginAndForgotPasswordTests.actualEmployeeName);

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

		// Select random status value
		myTasks.selectRandomStatusWhileAddTaskFromSidebar();

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

		commonMethods.verify_Toast_Message_After_Task_Add_From_Sidebar(
				actualScheduleDateFieldValueWhileAddTaskFromMonthViewAtParticularDate);

		// Increased task total count
		totalTasksCount = totalTasksCount + 1;
	}

	// Checks added task details from month view at particular date in day view
	@Test(dependsOnMethods = "verify_Add_Task_From_Month_View_At_Current_Date")
	public void verify_Added_Task_From_Month_View_At_Current_Date_Check_In_Day_View() throws InterruptedException {
		commonMethods.goToDayView();

		commonMethods.verify_Added_Task_Check_In_Day_View("from month view at particular date",
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
	@Test(dependsOnMethods = "verify_Added_Task_From_Month_View_At_Current_Date_Check_In_Day_View")
	public void verify_Total_Tasks_Count_And_Log_Messages_In_Day_View_After_New_Task_Add_From_Month_View_At_Current_Date()
			throws InterruptedException {
		commonMethods.verify_Total_Tasks_Count_In_Day_View_After_Added_Or_Deleted_Task(totalTasksCount);

		myActivitiesFun.verify_Log_Message(LoginAndForgotPasswordTests.actualEmployeeName, "has added new task",
				taskTitleInputWhileAddFromMonthViewAtParticularDate);
	}

	// Add task from month view sidebar by giving only title and date
	@Test(priority = 4)
	public void verify_Add_Roll_Over_Task_At_Past_Date_Which_Start_And_End_Date_Is_Same() throws InterruptedException {
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

		actualTaskAssigneeNameWhileAddTaskFromMonthViewSidebar = myTasks.checkTaskAssigneeNameWhileAddTaskFromSidebar();
		log.info("Actual task assignee name while add task from month view sidebar is: "
				+ actualTaskAssigneeNameWhileAddTaskFromMonthViewSidebar);
		assertEquals(actualTaskAssigneeNameWhileAddTaskFromMonthViewSidebar,
				LoginAndForgotPasswordTests.actualEmployeeName);

		actualTaskDepartmentWhileAddTaskFromMonthViewSidebar = myTasks.checkTaskDepartmentWhileAddTaskFromSidebar();
		log.info("Actual task department while add task from month view sidebar is: "
				+ actualTaskDepartmentWhileAddTaskFromMonthViewSidebar);

		actualTaskPriorityWhileAddTaskFromMonthViewSidebar = myTasks.checkTaskPriorityWhileAddTaskFromSidebar();
		log.info("Actual task priority while add task from month view sidebar is: "
				+ actualTaskPriorityWhileAddTaskFromMonthViewSidebar);

		myTasks.clickOnStatusDropdownWhileAddTaskFromSidebar();

		myTasks.selectRandomStatusWhileAddTaskFromSidebar();

		myTasks.clickOnStatusFieldLabelWhileAddTaskFromSidebar();

		actualTaskStatusWhileAddTaskFromMonthViewSidebar = myTasks.checkTaskStatusWhileAddTaskFromSidebar();
		log.info("Actual task status while add task from month view sidebar is: "
				+ actualTaskStatusWhileAddTaskFromMonthViewSidebar);

		String actualTaskCommentWhileAddTaskFromMonthViewSidebar = myTasks.checkTaskCommentWhileAddingTaskFromSidebar();
		log.info("Actual task comment while add task from month view sidebar is: "
				+ actualTaskCommentWhileAddTaskFromMonthViewSidebar);

		myTasks.clickOnAddTasksButtonInSidebar();

		commonMethods.verify_Toast_Message_After_Task_Add_From_Sidebar(
				actualTaskScheduleDateValueWhileAddTaskFromMonthViewSidebar);

		totalTasksCount = totalTasksCount + 1;
	}

	// This method checks added task details from month view sidebar in day view
	@Test(dependsOnMethods = "verify_Add_Roll_Over_Task_At_Past_Date_Which_Start_And_End_Date_Is_Same")
	public void verify_Added_Roll_Over_Task_In_Each_Day_From_Start_Date_Until_Current_Day_In_Day_View()
			throws InterruptedException {
		commonMethods.goToDayView();

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
				commonMethods.verify_Added_Task_Check_In_Day_View("from month view sidebar",
						taskTitleInputWhileAddFromMonthViewSidebar, actualTaskStatusWhileAddTaskFromMonthViewSidebar,
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
				commonMethods.verify_Added_Task_Check_In_Day_View("from month view sidebar",
						taskTitleInputWhileAddFromMonthViewSidebar, actualTaskStatusWhileAddTaskFromMonthViewSidebar,
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
	@Test(dependsOnMethods = "verify_Added_Roll_Over_Task_In_Each_Day_From_Start_Date_Until_Current_Day_In_Day_View")
	public void verify_Total_Tasks_Count_And_Log_Messages_In_Day_View_After_Added_Another_Task_From_Month_View_Sidebar()
			throws InterruptedException {
		commonMethods.goToDayView();

		commonMethods.verify_Total_Tasks_Count_In_Day_View_After_Added_Or_Deleted_Task(
				commonMethods.get_Total_Tasks_Count_In_Day_View());

		myActivitiesFun.verify_Log_Message(LoginAndForgotPasswordTests.actualEmployeeName, "has added new task",
				taskTitleInputWhileAddFromMonthViewSidebar);
	}

	// This method add task from day view
	@Test(priority = 5)
	public void verify_Add_New_Task_From_Day_View_At_Current_Date() throws InterruptedException {
		commonMethods.goToDayView();

		try {
			myTasks.clickOnAddTasksButtonInDayView();
			Thread.sleep(1000);

			taskTitleInputWhileAddFromDayView = faker.book().title();

			myTasks.enterTaskTitleInLastTaskTitleTextfieldDayView(taskTitleInputWhileAddFromDayView);

			myTasks.clickOnRefreshButtonInDayView();
			Thread.sleep(1000);

			commonMethods.verify_Toast_Message("after task add from day view", "task added successfully");

			String actualTaskTitleAddFromDayView = myTasks.checkLastTaskTitleInDayView();
			log.info("Actual task title add from day view is: " + actualTaskTitleAddFromDayView);

			if (actualTaskTitleAddFromDayView.length() > 26) {
				assertTrue(
						actualTaskTitleAddFromDayView.startsWith(taskTitleInputWhileAddFromDayView.substring(0, 27)));
			} else {
				assertEquals(actualTaskTitleAddFromDayView, taskTitleInputWhileAddFromDayView);
			}

			totalTasksCount = totalTasksCount + 1;
		} catch (Exception e) {
			webElementActions.refreshThePage();
			Thread.sleep(2000);
		}
	}

	// Checks total tasks count after added task from day view and also
	// checks log message
	@Test(dependsOnMethods = "verify_Add_New_Task_From_Day_View_At_Current_Date")
	public void verify_Total_Tasks_Count_And_Log_Message_In_Day_View_After_Added_Another_Task_From_Day_View()
			throws InterruptedException {
		commonMethods.goToDayView();

		commonMethods.verify_Total_Tasks_Count_In_Day_View_After_Added_Or_Deleted_Task(totalTasksCount);

		myActivitiesFun.verify_Log_Message(LoginAndForgotPasswordTests.actualEmployeeName, "has added new task",
				taskTitleInputWhileAddFromDayView);
	}

	// This method add task from day view sidebar
	@Test(priority = 6)
	public void verify_Add_Task_From_Day_View_Sidebar_With_Start_Date_As_Current_Date_And_Due_Date_As_Future_Date()
			throws InterruptedException {
		commonMethods.goToDayView();

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

		actualTaskAssigneeNameWhileAddTaskFromDayViewSidebar = myTasks.checkTaskAssigneeNameWhileAddTaskFromSidebar();
		log.info("Actual task assignee name while add task from day view sidebar is: "
				+ actualTaskAssigneeNameWhileAddTaskFromDayViewSidebar);
		assertEquals(actualTaskAssigneeNameWhileAddTaskFromDayViewSidebar,
				LoginAndForgotPasswordTests.actualEmployeeName);

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

		myTasks.selectRandomStatusWhileAddTaskFromSidebar();

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

		commonMethods.verify_Toast_Message_After_Task_Add_From_Sidebar(
				actualScheduleDateFieldValueWhileAddTaskFromDayViewSidebar);

		totalTasksCount = totalTasksCount + 1;
	}

	// This method checks added task details from day view sidenbar in day view
	@Test(dependsOnMethods = "verify_Add_Task_From_Day_View_Sidebar_With_Start_Date_As_Current_Date_And_Due_Date_As_Future_Date")
	public void verify_Added_Task_From_Day_View_Sidebar_Check_In_Each_Day_Until_Future_Date_In_Day_View()
			throws InterruptedException {
		commonMethods.goToDayView();

		for (int i = 0; i < 10; i++) {
			String dateWithDayInCurrentDayView = myTasks.checkDateInCurrentDayView();
			String[] seperateDateAndDayInCurrentDayView = dateWithDayInCurrentDayView.split(",");

			String day = seperateDateAndDayInCurrentDayView[0].trim();
			log.info("Day in current day view is: " + day);
			String date = seperateDateAndDayInCurrentDayView[1].trim();
			log.info("Date in current day view is: " + date);

			if (date.substring(0, 2)
					.equalsIgnoreCase(actualDueDateFieldValueWhileAddTaskFromDayViewSidebar.substring(8))) {
				commonMethods.verify_Added_Task_Check_In_Day_View("from day view sidebar",
						taskTitleInputWhileAddFromDayViewSidebar, actualTaskStatusWhileAddTaskFromDayViewSidebar,
						actualTaskPriorityWhileAddTaskFromDayViewSidebar,
						actualTaskProjectWhileAddTaskFromDayViewSidebar,
						actualScheduleDateFieldValueWhileAddTaskFromDayViewSidebar,
						actualDueDateFieldValueWhileAddTaskFromDayViewSidebar,
						actualTaskDepartmentWhileAddTaskFromDayViewSidebar,
						actualTaskAssigneeNameWhileAddTaskFromDayViewSidebar,
						actualTaskCommentWhileAddTaskFromDayViewSidebar);

				break;
			} else {
				commonMethods.verify_Added_Task_Check_In_Day_View("from day view sidebar",
						taskTitleInputWhileAddFromDayViewSidebar, actualTaskStatusWhileAddTaskFromDayViewSidebar,
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
	@Test(dependsOnMethods = "verify_Added_Task_From_Day_View_Sidebar_Check_In_Each_Day_Until_Future_Date_In_Day_View")
	public void verify_Total_Tasks_Count_And_Log_Message_In_Day_View_After_Adding_Another_Task_From_Day_View_Sidebar()
			throws InterruptedException {
		commonMethods.goToDayView();

		commonMethods.verify_Total_Tasks_Count_In_Day_View_After_Added_Or_Deleted_Task(totalTasksCount);

		myActivitiesFun.verify_Log_Message(LoginAndForgotPasswordTests.actualEmployeeName, "has added new task",
				taskTitleInputWhileAddFromDayViewSidebar);
	}

	// This method add new task from week view at particular date
	@Test(priority = 7, enabled = false)
	public void verify_Add_Task_From_Week_View_At_Future_Date() throws InterruptedException {
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
				.checkTaskAssigneeNameWhileAddTaskFromSidebar();
		log.info("Actual task assignee name while add task from week view and at particular date is: "
				+ actualTaskAssigneeNameWhileAddTaskFromWeekViewAtParticularDate);
		assertEquals(actualTaskAssigneeNameWhileAddTaskFromWeekViewAtParticularDate,
				LoginAndForgotPasswordTests.actualEmployeeName);

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

		myTasks.selectRandomStatusWhileAddTaskFromSidebar();

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

		commonMethods.verify_Toast_Message_After_Task_Add_From_Sidebar(
				actualScheduleDateFieldValueWhileAddTaskFromWeekViewAtParticularDate);

		totalTasksCount = totalTasksCount + 1;
	}

	// This method checks added task from week view sidebar at particular date in
	// week view task list
	@Test(dependsOnMethods = "verify_Add_Task_From_Week_View_At_Future_Date", enabled = false)
	public void verify_Added_Task_From_Week_View_Sidebar_At_Future_Date_In_Week_View() throws InterruptedException {
		myTasks.clickOnWeekButton();
		Thread.sleep(1000);

		commonMethods.verify_Added_Task_In_Week_View("from week view sidebar at particular date",
				taskTitleInputWhileAddFromWeekViewAtParticularDate,
				actualScheduleDateFieldValueWhileAddTaskFromWeekViewAtParticularDate,
				actualDueDateFieldValueWhileAddTaskFromWeekViewAtParticularDate);
	}

	// This method checks added task from week view sidebar at particular date in
	// day view task list
	@Test(dependsOnMethods = "verify_Add_Task_From_Week_View_At_Future_Date", enabled = false)
	public void verify_Added_Task_From_Week_View_At_Future_Date_Check_In_Day_View() throws InterruptedException {
		commonMethods.goToDayView();

		myTasks.clickOnCalendarInDayView();
		Thread.sleep(1000);

		if (actualTaskDueDateValueWhileAddTaskFromMonthViewSidebar.substring(8, 9).equalsIgnoreCase("0")) {
			myTasks.clickOnParticularDateFromCalendarInDayView(
					Integer.parseInt(actualTaskDueDateValueWhileAddTaskFromMonthViewSidebar.substring(9)));
		} else {
			myTasks.clickOnParticularDateFromCalendarInDayView(
					Integer.parseInt(actualTaskDueDateValueWhileAddTaskFromMonthViewSidebar.substring(8)));
		}

		commonMethods.verify_Added_Task_Check_In_Day_View("from week view at particular date",
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
	@Test(dependsOnMethods = "verify_Added_Task_From_Week_View_At_Future_Date_Check_In_Day_View", enabled = false)
	public void verify_Total_Tasks_Count_And_Log_Message_In_Day_View_After_Task_Added_From_Week_View_At_Future_Date()
			throws InterruptedException {
		commonMethods.verify_Total_Tasks_Count_In_Day_View_After_Added_Or_Deleted_Task(totalTasksCount);

		myActivitiesFun.verify_Log_Message(LoginAndForgotPasswordTests.actualEmployeeName, "has added new task",
				taskTitleInputWhileAddFromWeekViewAtParticularDate);
	}

	// This method add new task from week view sidebar with only title, date, and
	// comment
	@Test(priority = 8)
	public void verify_Add_Task_From_Week_View_Sidebar_By_Giving_Only_Title_Date_And_Comment()
			throws InterruptedException {
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
			int fakeNumber = faker.number().numberBetween(1, 5);
			dueDateInput = dueDateInput + fakeNumber;
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

		actualTaskAssigneeNameWhileAddTaskFromWeekViewSidebar = myTasks.checkTaskAssigneeNameWhileAddTaskFromSidebar();
		log.info("Actual task assignee name while add task from week view sidebar is: "
				+ actualTaskAssigneeNameWhileAddTaskFromWeekViewSidebar);
		assertEquals(actualTaskAssigneeNameWhileAddTaskFromWeekViewSidebar,
				LoginAndForgotPasswordTests.actualEmployeeName);

		actualTaskDepartmentWhileAddTaskFromWeekViewSidebar = myTasks.checkTaskDepartmentWhileAddTaskFromSidebar();
		log.info("Actual task department while add task from week view sidebar is: "
				+ actualTaskDepartmentWhileAddTaskFromWeekViewSidebar);

		actualTaskPriorityWhileAddTaskFromWeekViewSidebar = myTasks.checkTaskPriorityWhileAddTaskFromSidebar();
		log.info("Actual task priority while add task from week view sidebar is: "
				+ actualTaskPriorityWhileAddTaskFromWeekViewSidebar);

		myTasks.clickOnStatusDropdownWhileAddTaskFromSidebar();

		myTasks.selectRandomStatusWhileAddTaskFromSidebar();

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

		commonMethods.verify_Toast_Message_After_Task_Add_From_Sidebar(
				actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar);

		totalTasksCount = totalTasksCount + 1;
	}

	// This method checks added task from week view sidebar in
	// week view task list
	@Test(dependsOnMethods = "verify_Add_Task_From_Week_View_Sidebar_By_Giving_Only_Title_Date_And_Comment")
	public void verify_Added_Task_From_Week_View_Sidebar_In_Week_View() throws InterruptedException {
		myTasks.clickOnWeekButton();
		Thread.sleep(1000);

		commonMethods.verify_Added_Task_In_Week_View("from week view sidebar",
				taskTitleInputWhileAddFromWeekViewSidebar, actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar,
				actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar);
	}

	// This method checks added task from week view sidebar in
	// day view task list
	@Test(dependsOnMethods = "verify_Add_Task_From_Week_View_Sidebar_By_Giving_Only_Title_Date_And_Comment")
	public void verify_Added_Task_From_Week_View_Sidebar_Check_In_Day_View() throws InterruptedException {
		commonMethods.goToDayView();

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

			if (Integer.parseInt(actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar.substring(8)) > Integer
					.parseInt(formattedDate.substring(8))
					&& date.substring(0, 2)
							.equalsIgnoreCase(actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar.substring(8))) {
				commonMethods.verify_Added_Task_Check_In_Day_View("from week view sidebar",
						taskTitleInputWhileAddFromWeekViewSidebar, actualTaskStatusWhileAddTaskFromWeekViewSidebar,
						actualTaskPriorityWhileAddTaskFromWeekViewSidebar, "",
						actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar,
						actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar,
						actualTaskDepartmentWhileAddTaskFromWeekViewSidebar,
						actualTaskAssigneeNameWhileAddTaskFromWeekViewSidebar,
						actualTaskCommentWhileAddTaskFromWeekViewSidebar);

				break;
			} else {
				commonMethods.verify_Added_Task_Check_In_Day_View("from week view sidebar",
						taskTitleInputWhileAddFromWeekViewSidebar, actualTaskStatusWhileAddTaskFromWeekViewSidebar,
						actualTaskPriorityWhileAddTaskFromWeekViewSidebar, "",
						actualTaskScheduleDateValueWhileAddTaskFromWeekViewSidebar,
						actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar,
						actualTaskDepartmentWhileAddTaskFromWeekViewSidebar,
						actualTaskAssigneeNameWhileAddTaskFromWeekViewSidebar,
						actualTaskCommentWhileAddTaskFromWeekViewSidebar);

				if (Integer.parseInt(actualTaskDueDateValueWhileAddTaskFromWeekViewSidebar.substring(8)) <= Integer
						.parseInt(formattedDate.substring(8)) && i > 0) {
					String rollOverTaskColor = myTasks.checkRollOverTaskColor();
//					log.info("Roll over task color is: " + rollOverTaskColor);
					assertEquals(rollOverTaskColor, "rgba(255, 15, 0, 1)");
				}

				myTasks.clickOnNextDayIconInDayView();
			}
		}
	}

	// Checks total tasks count after added task from week view sidebar and also
	// checks log message
	@Test(dependsOnMethods = "verify_Added_Task_From_Week_View_Sidebar_Check_In_Day_View")
	public void verify_Total_Tasks_Count_And_Log_Message_In_Day_View_After_Added_Another_Task_From_Week_View_Sidebar()
			throws InterruptedException {
		commonMethods.goToDayView();

		commonMethods.verify_Total_Tasks_Count_In_Day_View_After_Added_Or_Deleted_Task(totalTasksCount);

		myActivitiesFun.verify_Log_Message(LoginAndForgotPasswordTests.actualEmployeeName, "has added new task",
				taskTitleInputWhileAddFromWeekViewSidebar);
	}

}
