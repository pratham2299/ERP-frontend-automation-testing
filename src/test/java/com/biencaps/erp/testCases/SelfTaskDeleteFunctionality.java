package com.biencaps.erp.testCases;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import com.biencaps.erp.pageObjectModels.*;
import com.biencaps.erp.utilities.*;

/* This class is extended BaseTest class.
 *  BaseTest has driver so you can inherite driver from BaseTest to this class
 */
public class SelfTaskDeleteFunctionality extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(SelfTaskDeleteFunctionality.class);

	public static String actualTaskTitleAfterClearAndUpdateFromDayView;
	public static String actualLastTaskTitleInDayView;

	protected WebElementActions webElementActions;
	protected MyTasksPage myTasks;
	protected DashboardPage dashboard;
	protected CommonTestMethods commonMethods;
	protected MyActivitiesFunctionality myActivitiesFun;

	@BeforeClass
	public void verifyLastTaskDetailsInDayView() throws InterruptedException {
		webElementActions = new WebElementActions();
		myTasks = new MyTasksPage(driver);
		dashboard = new DashboardPage(driver);
		commonMethods = new CommonTestMethods();
		myActivitiesFun = new MyActivitiesFunctionality();

		dashboard.clickOnMyTasksSection();
//		Thread.sleep(2000);

		myTasks.clickOnDayButton();
//		Thread.sleep(2000);

		myTasks.scrollUptoBottomOfTaskDivInDayView();
//		Thread.sleep(2000);

		actualLastTaskTitleInDayView = myTasks.checkLastTaskTitleInDayView();
		log.info("Actual last task title in day view is: " + actualLastTaskTitleInDayView + "\n");
	}

	// Single task delete
	@Test(priority = 1)
	public void verifyDeleteLastTaskFromDayView() throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.clickOnLastCheckBoxInDayView();
//		Thread.sleep(1000);

		myTasks.clickOnDeleteButtonInDayView();
//		Thread.sleep(1000);

		myTasks.clickOnDeleteButtonForTaskDeleteInDayView();
//		Thread.sleep(2000);

		commonMethods.verifyToastMessage("after deleted task in day view", "task deleted successfully");

		myTasks.clickOnRefreshButtonInDayView();
//		Thread.sleep(2000);
	}

	// Check log message after single task delete
	@Test(priority = 2)
	public void verifyLogMessageAfterNewTaskDeleteFromDayView() throws InterruptedException {
		myActivitiesFun.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "has deleted",
				actualLastTaskTitleInDayView);
	}

	// Check total tasks count in day view after deleted single task
	@Test(priority = 3)
	public void verifyTotalTasksCountInDayViewAfterDeletingSingleTask() throws InterruptedException {
		int totalCheckboxesInDayView = commonMethods.getTotalTasksCountInDayView();

		if (totalCheckboxesInDayView == 1) {
			log.info("Task deleted successfully from day view and no tasks present" + "\n");

			AddSelfTaskAndCheckInDayViewFunctionality.totalTasksCount = 0;

			commonMethods.verifyTotalTasksCountInDayViewAfterAddedOrDeletedTask(
					AddSelfTaskAndCheckInDayViewFunctionality.totalTasksCount);
		} else {
			boolean isTaskTitlePresentInDayViewAfterDeleted = myTasks
					.checkTaskNamePresentAfterDeleteInDayView(actualLastTaskTitleInDayView);
			if (isTaskTitlePresentInDayViewAfterDeleted == false) {
				log.info("Task title deleted from day view" + "\n");
			} else {
				log.info("Task title still present in day view" + "\n");
			}

			AddSelfTaskAndCheckInDayViewFunctionality.totalTasksCount = AddSelfTaskAndCheckInDayViewFunctionality.totalTasksCount
					- 1;

			commonMethods.verifyTotalTasksCountInDayViewAfterAddedOrDeletedTask(
					AddSelfTaskAndCheckInDayViewFunctionality.totalTasksCount);
		}
		Thread.sleep(2000);
	}

	// Multiple task delete
	@Test(priority = 4)
	public void verifyMultipleTasksDeleteInDayView() throws InterruptedException {
		myTasks.clickOnMultipleCheckBoxesInDayView();
//		Thread.sleep(1000);

		myTasks.clickOnDeleteButtonInDayView();
//		Thread.sleep(1000);

		myTasks.clickOnDeleteButtonForTaskDeleteInDayView();
//		Thread.sleep(2000);

		commonMethods.verifyToastMessage("after deleted task in day view", "task deleted successfully");

		myTasks.clickOnRefreshButtonInDayView();
//		Thread.sleep(2000);
	}

	// Check total tasks count in day view after deleted multiple task
	@Test(priority = 5, enabled = false)
	public void verifyTotalTasksCountInDayViewAfterDeletingAllTasks() throws InterruptedException {
		log.info("Task deleted successfully from day view and no tasks present" + "\n");

		AddSelfTaskAndCheckInDayViewFunctionality.totalTasksCount = 1;

		commonMethods.verifyTotalTasksCountInDayViewAfterAddedOrDeletedTask(
				AddSelfTaskAndCheckInDayViewFunctionality.totalTasksCount);
		Thread.sleep(2000);
	}
}
