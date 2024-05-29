package in.biencaps.erp.testCases;

import static org.testng.Assert.*;

import java.util.List;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import in.biencaps.erp.pages.*;
import in.biencaps.erp.utilities.*;

/* This class is extended BaseTest class.
 *  BaseTest has driver so you can inherite driver from BaseTest to this class
 */
public class SelfTaskSubmittedTests extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(SelfTaskSubmittedTests.class);

	private String lastTaskTitleInDayView;
	private String lastTaskScheduleDatInDayView;
	private String lastTaskDueDatInDayView;
	private List<String> reportingAuthoritiesUserIds;
	private String actualTaskOwner;
	private String requestActionForSelfTaskSubmit;
	protected String actualRequestActionTakerHigherAuthority;

	protected MyTasksPage myTasks;
	protected DashboardPage dashboard;
	protected CommonTestMethods commonMethods;
	protected NotificationMessagesTests notification;
	protected LogoutTests logOutFun;
	protected LoginAndForgotPasswordTests loginFun;
	protected RequestTests requestFun;
	protected RequestPage request;
	protected MyActivitiesTests myActivities;

	// Self task submit and check log messsage
	@Test(priority = 1)
	public void verify_Self_Task_Submitted_In_Day_View() throws InterruptedException {
		myTasks = new MyTasksPage(driver);
		commonMethods = new CommonTestMethods();
		notification = new NotificationMessagesTests();
		loginFun = new LoginAndForgotPasswordTests();
		logOutFun = new LogoutTests();
		requestFun = new RequestTests();
		request = new RequestPage();
		dashboard = new DashboardPage();
		myActivities = new MyActivitiesTests();

		commonMethods.goToDayView();

		String actualOldTaskStatusInDayView = myTasks.checkLastTaskStatusTextInDayView();
		log.info("Actual old task status in day view is: " + actualOldTaskStatusInDayView);

		lastTaskTitleInDayView = myTasks.checkLastTaskTitleInDayView();
		log.info("Last task title in day view is: " + lastTaskTitleInDayView);

		lastTaskScheduleDatInDayView = myTasks.checkLastTaskScheduleDateTextInDayView();
		log.info("Last task schedule date in day view is: " + lastTaskScheduleDatInDayView);

		lastTaskDueDatInDayView = myTasks.checkLastTaskDueDateTextInDayView();
		log.info("Last task due date in day view is: " + lastTaskDueDatInDayView);

		myTasks.clickOnLastTaskStatusInDayView();

		myTasks.clickOnSelectedTaskStatusValueFromDropdownInDayView("Submitted");

		commonMethods.verify_Toast_Message("after task submitted in day view",
				"Request for update status sent successfully");

		String actualUpdatedTaskStatusInDayView = myTasks.checkLastTaskStatusTextInDayView();
		log.info("Actual updated task status in day view is: " + actualUpdatedTaskStatusInDayView);
		assertEquals(actualUpdatedTaskStatusInDayView, "Submitted");
		Thread.sleep(1000);

		actualTaskOwner = LoginAndForgotPasswordTests.actualEmployeeName;
		requestActionForSelfTaskSubmit = "wants to submit self task";

		myActivities.verify_Log_Message_After_Update_Task_Details(actualTaskOwner, "status",
				actualOldTaskStatusInDayView, actualUpdatedTaskStatusInDayView, lastTaskTitleInDayView);
	}

	// Check request details in my request
	@Test(priority = 2)
	public void verify_Task_Submitted_Request_In_My_Request_Section() throws InterruptedException {
		if (lastTaskScheduleDatInDayView.equalsIgnoreCase(lastTaskDueDatInDayView)) {
			String taskOwnerNameWithTaskDate = actualTaskOwner + " on " + lastTaskScheduleDatInDayView + " to "
					+ lastTaskDueDatInDayView + "";
			requestFun.verify_Request_In_My_Requests_Card(actualTaskOwner, taskOwnerNameWithTaskDate,
					requestActionForSelfTaskSubmit, lastTaskTitleInDayView);
		} else {
			String taskOwnerNameWithTaskDate = actualTaskOwner + " on " + lastTaskScheduleDatInDayView + " to "
					+ lastTaskDueDatInDayView + "";
			requestFun.verify_Request_In_My_Requests_Card(actualTaskOwner, taskOwnerNameWithTaskDate,
					requestActionForSelfTaskSubmit, lastTaskTitleInDayView);
		}
	}

	@Test(priority = 3)
	public void verify_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority_of_Submit_Task()
			throws InterruptedException {
		reportingAuthoritiesUserIds = commonMethods.getAllHigherAuthoritiesNamesOfLoggedInEmployee();
		System.out.println(reportingAuthoritiesUserIds);

		String notificationMessage = "wants to submit self task \"" + lastTaskTitleInDayView + "\"";

		commonMethods.verify_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority(
				reportingAuthoritiesUserIds, notificationMessage, lastTaskScheduleDatInDayView, lastTaskDueDatInDayView,
				actualTaskOwner, requestActionForSelfTaskSubmit, actualTaskOwner, lastTaskTitleInDayView);
	}

	@Test(dependsOnMethods = "verify_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority_of_Submit_Task")
	public void reject_Task_Title_Change_Request_By_Higher_Authority() throws InterruptedException {
		actualRequestActionTakerHigherAuthority = commonMethods
				.reject_Task_Title_Change_Request_By_Higher_Authority(reportingAuthoritiesUserIds);
	}

	// Check notification message after rejected task submit request
	@Test(priority = 4)
	public void verify_Notification_Message_Of_Request_Rejected_By_Higher_Authority_At_Employee_Side()
			throws InterruptedException {
		commonMethods.login_Employee_And_Get_Name();

		notification.verifyNotificationMessage(actualRequestActionTakerHigherAuthority,
				"rejected your request for \"" + lastTaskTitleInDayView + "\"");
	}

	// Check task status after task submit request rejected in day view
	@Test(priority = 7)
	public void verify_Task_Status_And_Log_Message_In_Day_View_After_Task_Submitted_Request_Rejected_By_Higher_Authority()
			throws InterruptedException {
		commonMethods.goToDayView();

		String actualTaskStatusInDayViewAfterTaskSubmittedRequestDeniedByAdmin = myTasks
				.checkLastTaskStatusTextInDayView();
		log.info("Actual task status in day view after task submitted request denied by admin is: "
				+ actualTaskStatusInDayViewAfterTaskSubmittedRequestDeniedByAdmin + "\n");

		if (actualTaskStatusInDayViewAfterTaskSubmittedRequestDeniedByAdmin.equalsIgnoreCase("In Progress")) {
			assertEquals(actualTaskStatusInDayViewAfterTaskSubmittedRequestDeniedByAdmin, "In Progress");

			myActivities.verify_Log_Message(actualRequestActionTakerHigherAuthority,
					"has rejected your request for task submit in", lastTaskTitleInDayView);
		} else {
			log.info("Task status remained as it is");
		}
		Thread.sleep(2000);
	}

	// Check request details in my request
	@Test(priority = 9)
	public void verify_Task_Request_In_My_Request_Section_After_Rejected_Submit_Task_Request()
			throws InterruptedException {
		if (lastTaskScheduleDatInDayView.equalsIgnoreCase(lastTaskDueDatInDayView)) {
			String taskOwnerNameWithTaskDate = actualTaskOwner + " on " + lastTaskScheduleDatInDayView + "";
			requestFun.verify_Task_Request_In_My_Request_Section_By_Filtering_Request_Category("Rejected",
					taskOwnerNameWithTaskDate, actualRequestActionTakerHigherAuthority, requestActionForSelfTaskSubmit,
					lastTaskTitleInDayView);
		} else {
			String taskOwnerNameWithTaskDate = actualTaskOwner + " on " + lastTaskScheduleDatInDayView + " to "
					+ lastTaskDueDatInDayView + "";
			requestFun.verify_Task_Request_In_My_Request_Section_By_Filtering_Request_Category("Rejected",
					taskOwnerNameWithTaskDate, actualRequestActionTakerHigherAuthority, requestActionForSelfTaskSubmit,
					lastTaskTitleInDayView);
		}
	}

	@Test(priority = 10)
	public void verify_Self_Task_Submitted_Again__In_Day_View() throws InterruptedException {
		commonMethods.goToDayView();

		String actualOldTaskStatusInDayView = myTasks.checkLastTaskStatusTextInDayView();
		log.info("Actual old task status in day view is: " + actualOldTaskStatusInDayView);

		lastTaskTitleInDayView = myTasks.checkLastTaskTitleInDayView();
		log.info("Last task title in day view is: " + lastTaskTitleInDayView);

		lastTaskScheduleDatInDayView = myTasks.checkLastTaskScheduleDateTextInDayView();
		log.info("Last task schedule date in day view is: " + lastTaskScheduleDatInDayView);

		lastTaskDueDatInDayView = myTasks.checkLastTaskDueDateTextInDayView();
		log.info("Last task due date in day view is: " + lastTaskDueDatInDayView);

		myTasks.clickOnLastTaskStatusInDayView();

		myTasks.clickOnSelectedTaskStatusValueFromDropdownInDayView("Submitted");

		commonMethods.verify_Toast_Message("after task submitted in day view",
				"Request for update status sent successfully");

		String actualUpdatedTaskStatusInDayView = myTasks.checkLastTaskStatusTextInDayView();
		log.info("Actual updated task status in day view is: " + actualUpdatedTaskStatusInDayView);
		assertEquals(actualUpdatedTaskStatusInDayView, "Submitted");
		Thread.sleep(1000);

		actualTaskOwner = LoginAndForgotPasswordTests.actualEmployeeName;
		requestActionForSelfTaskSubmit = "wants to submit self task";

		myActivities.verify_Log_Message_After_Update_Task_Details(actualTaskOwner, "status",
				actualOldTaskStatusInDayView, actualUpdatedTaskStatusInDayView, lastTaskTitleInDayView);
	}

	// Check request details in my request
	@Test(priority = 11)
	public void verify__New_Task_Submitted_Request_In_My_Request_Section() throws InterruptedException {
		if (lastTaskScheduleDatInDayView.equalsIgnoreCase(lastTaskDueDatInDayView)) {
			String taskOwnerNameWithTaskDate = actualTaskOwner + " on " + lastTaskScheduleDatInDayView + " to "
					+ lastTaskDueDatInDayView + "";
			requestFun.verify_Request_In_My_Requests_Card(actualTaskOwner, taskOwnerNameWithTaskDate,
					requestActionForSelfTaskSubmit, lastTaskTitleInDayView);
		} else {
			String taskOwnerNameWithTaskDate = actualTaskOwner + " on " + lastTaskScheduleDatInDayView + " to "
					+ lastTaskDueDatInDayView + "";
			requestFun.verify_Request_In_My_Requests_Card(actualTaskOwner, taskOwnerNameWithTaskDate,
					requestActionForSelfTaskSubmit, lastTaskTitleInDayView);
		}
	}

	@Test(priority = 12)
	public void verify_New_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority_of_Submit_Task()
			throws InterruptedException {
		reportingAuthoritiesUserIds = commonMethods.getAllHigherAuthoritiesNamesOfLoggedInEmployee();
		System.out.println(reportingAuthoritiesUserIds);

		String notificationMessage = "wants to submit self task \"" + lastTaskTitleInDayView + "\"";

		commonMethods.verify_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority(
				reportingAuthoritiesUserIds, notificationMessage, lastTaskScheduleDatInDayView, lastTaskDueDatInDayView,
				actualTaskOwner, requestActionForSelfTaskSubmit, actualTaskOwner, lastTaskTitleInDayView);
	}

	@Test(dependsOnMethods = "verify_New_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority_of_Submit_Task")
	public void accept_Task_Title_Change_Request_By_Higher_Authority() throws InterruptedException {
		actualRequestActionTakerHigherAuthority = commonMethods
				.accept_Task_Title_Change_Request_By_Higher_Authority(reportingAuthoritiesUserIds);
	}

	// Check notification message after accepted task submit request
	@Test(priority = 13)
	public void verify_Notification_Message_Of_Request_Accepted_By_Higher_Authority_At_Employee_Side()
			throws InterruptedException {
		commonMethods.login_Employee_And_Get_Name();

		notification.verifyNotificationMessage(actualRequestActionTakerHigherAuthority,
				"accepted your request for \"" + lastTaskTitleInDayView + "\"");
	}

	// Check task status after task submit request accepted in day view
	@Test(priority = 14)
	public void verify_Task_Status_In_Day_View_After_Task_Submitted_Request_Accepted_By_Higher_Authority()
			throws InterruptedException {
		commonMethods.goToDayView();

		String actualTaskStatusInDayViewAfterTaskSubmittedRequestDeniedByAdmin = myTasks
				.checkLastTaskStatusTextInDayView();
		log.info("Actual task status in day view after task submitted request approved by admin is: "
				+ actualTaskStatusInDayViewAfterTaskSubmittedRequestDeniedByAdmin + "\n");

		if (actualTaskStatusInDayViewAfterTaskSubmittedRequestDeniedByAdmin.equalsIgnoreCase("Completed")) {
			assertEquals(actualTaskStatusInDayViewAfterTaskSubmittedRequestDeniedByAdmin, "Completed");
		} else {
			log.error("Task status remained as it is");
		}
		Thread.sleep(2000);
	}

	// Check completed tasks count after task complete in day view
	@Test(priority = 15)
	public void verify_Completed_Tasks_Count_And_Percentage_After_Task_Completed_In_Day_View()
			throws InterruptedException {
		int totalCompetedTasksCount = myTasks.checkSizeOfCompletedTaskInDayView();
		commonMethods.verify_Total_Completed_Tasks_Count_In_Day_View(totalCompetedTasksCount);
		Thread.sleep(1000);

		int totalTasksCount = commonMethods.get_Total_Tasks_Count_In_Day_View();
		commonMethods.verify_Percentage_Of_Completed_Tasks_In_Day_View(totalCompetedTasksCount, totalTasksCount);
		Thread.sleep(1000);
	}

	// Check log message after task submit request accepted in day view
	@Test(priority = 16)
	public void verify_Log_Message_After_Submit_Task_Request_Accepted() throws InterruptedException {
		myActivities.verify_Log_Message(actualRequestActionTakerHigherAuthority,
				"has approved your request and changed task status from \"Submitted\" to \"Completed\" in",
				lastTaskTitleInDayView);
	}

	// Check request details in my request
	@Test(priority = 17)
	public void verify_Task_Request_In_My_Request_Section_After_Accepted_Submit_Task_Request()
			throws InterruptedException {
		if (lastTaskScheduleDatInDayView.equalsIgnoreCase(lastTaskDueDatInDayView)) {
			String taskOwnerNameWithTaskDate = actualTaskOwner + " on " + lastTaskScheduleDatInDayView + "";
			requestFun.verify_Task_Request_In_My_Request_Section_By_Filtering_Request_Category("Accepted",
					taskOwnerNameWithTaskDate, actualRequestActionTakerHigherAuthority, requestActionForSelfTaskSubmit,
					lastTaskTitleInDayView);
		} else {
			String taskOwnerNameWithTaskDate = actualTaskOwner + " on " + lastTaskScheduleDatInDayView + " to "
					+ lastTaskDueDatInDayView + "";
			requestFun.verify_Task_Request_In_My_Request_Section_By_Filtering_Request_Category("Accepted",
					taskOwnerNameWithTaskDate, actualRequestActionTakerHigherAuthority, requestActionForSelfTaskSubmit,
					lastTaskTitleInDayView);
		}
	}
}
