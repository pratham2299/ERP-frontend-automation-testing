package in.biencaps.erp.testCases;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import in.biencaps.erp.pages.*;
import in.biencaps.erp.utilities.*;

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
	public void verify_Last_Task_Details_In_Day_View() throws InterruptedException {
		webElementActions = new WebElementActions();
		myTasks = new MyTasksPage(driver);
		dashboard = new DashboardPage();
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
	public void verify_Delete_Last_Task_From_Day_View() throws InterruptedException {
		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.clickOnLastCheckBoxInDayView();
//		Thread.sleep(1000);

		myTasks.clickOnDeleteButtonInDayView();
//		Thread.sleep(1000);

		myTasks.clickOnDeleteButtonForTaskDeleteInDayView();
//		Thread.sleep(2000);

		commonMethods.verify_Toast_Message("after deleted task in day view", "task deleted successfully");

		myTasks.clickOnRefreshButtonInDayView();
//		Thread.sleep(2000);
	}

	// Check log message after single task delete
	@Test(priority = 2)
	public void verify_Log_Message_After_Task_Delete_From_Day_View() throws InterruptedException {
		myActivitiesFun.verify_Log_Message(LoginAndForgotPasswordFunctionality.actualEmployeeName, "has deleted",
				actualLastTaskTitleInDayView);
	}

	// Check total tasks count in day view after deleted single task
	@Test(priority = 3)
	public void verify_Total_Tasks_Count_In_Day_View_After_Deleting_Single_Task() throws InterruptedException {
		int totalCheckboxesInDayView = commonMethods.get_Total_Tasks_Count_In_Day_View();

		if (totalCheckboxesInDayView == 1) {
			log.info("Task deleted successfully from day view and no tasks present" + "\n");

			AddSelfTaskAndCheckInDayViewFunctionality.totalTasksCount = 0;

			commonMethods.verify_Total_Tasks_Count_In_Day_View_After_Added_Or_Deleted_Task(
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

			commonMethods.verify_Total_Tasks_Count_In_Day_View_After_Added_Or_Deleted_Task(
					AddSelfTaskAndCheckInDayViewFunctionality.totalTasksCount);
		}
		Thread.sleep(2000);
	}

	// Multiple task delete
	@Test(priority = 4)
	public void verify_Multiple_Tasks_Delete_In_Day_View() throws InterruptedException {
		myTasks.clickOnMultipleCheckBoxesInDayView();
//		Thread.sleep(1000);

		myTasks.clickOnDeleteButtonInDayView();
//		Thread.sleep(1000);

		myTasks.clickOnDeleteButtonForTaskDeleteInDayView();
//		Thread.sleep(2000);

		commonMethods.verify_Toast_Message("after deleted task in day view", "task deleted successfully");

		myTasks.clickOnRefreshButtonInDayView();
//		Thread.sleep(2000);
	}

	// Check total tasks count in day view after deleted multiple task
	@Test(priority = 5, enabled = false)
	public void verify_Total_Tasks_Count_In_Day_View_After_Deleting_All_Tasks() throws InterruptedException {
		log.info("Task deleted successfully from day view and no tasks present" + "\n");

		AddSelfTaskAndCheckInDayViewFunctionality.totalTasksCount = 1;

		commonMethods.verify_Total_Tasks_Count_In_Day_View_After_Added_Or_Deleted_Task(
				AddSelfTaskAndCheckInDayViewFunctionality.totalTasksCount);
		Thread.sleep(2000);
	}
}
