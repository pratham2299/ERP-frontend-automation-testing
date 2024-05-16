package in.biencaps.erp.testCases;

import static org.testng.Assert.*;

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

	public static String lastTaskTitleInDayView;
	public static String lastTaskScheduleDatInDayView;
	public static String lastTaskDueDatInDayView;
	public static String actualHigherAuthorityName;

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

		myTasks.clickOnDayButton();
		Thread.sleep(2000);

		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

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

		myActivities.verify_Log_Message_After_Update_Task_Details(LoginAndForgotPasswordTests.actualEmployeeName,
				"status", actualOldTaskStatusInDayView, actualUpdatedTaskStatusInDayView, lastTaskTitleInDayView);
	}

	// Check request details in my request
	@Test(priority = 2)
	public void verify_Task_Submitted_Request_In_My_Request_Section() throws InterruptedException {
		if (lastTaskScheduleDatInDayView.equalsIgnoreCase(lastTaskDueDatInDayView)) {
			requestFun.verify_Request_In_My_Requests_Card(LoginAndForgotPasswordTests.actualEmployeeName,
					LoginAndForgotPasswordTests.actualEmployeeName + " on " + lastTaskScheduleDatInDayView + "",
					"wants to submit", lastTaskTitleInDayView);
		} else {
			requestFun.verify_Request_In_My_Requests_Card(
					LoginAndForgotPasswordTests.actualEmployeeName, LoginAndForgotPasswordTests.actualEmployeeName
							+ " on " + lastTaskScheduleDatInDayView + " to " + lastTaskDueDatInDayView + "",
					"wants to submit", lastTaskTitleInDayView);
		}
	}

	@Test(priority = 3)
	public void verify_Notification_Message_And_Request_Of_Employee_At_All_Higher_Authority()
			throws InterruptedException {
		if (DataGenerator.employeeUserIdsAndRolesOnTestEnvironment().get(Constants.employeeUserId)
				.equalsIgnoreCase("Developer")) {
			logOutFun.verify_LogOut_Employee();

			commonMethods.verify_logged_In_Employee_Name_Notification_Message_And_Request_For_All_Higher_Authority("lead level",
					Constants.leadLevelTesterEmployeeUserId, Constants.leadLevelTesterEmployeePassword,
					LoginAndForgotPasswordTests.actualEmployeeName, "wants to submit", lastTaskTitleInDayView);

			logOutFun.verify_LogOut_Employee();

			commonMethods.verify_logged_In_Employee_Name_Notification_Message_And_Request_For_All_Higher_Authority(
					"team lead level", Constants.teamLeadLevelTesterEmployeeUserId,
					Constants.teamLeadLevelTesterEmployeePassword, LoginAndForgotPasswordTests.actualEmployeeName,
					"wants to submit", lastTaskTitleInDayView);

			request.clickOnFirstRejectButton();
			Thread.sleep(1000);

			request.clickOnRejectSendButtonInRequestSection();
			Thread.sleep(2000);
		} else if (DataGenerator.employeeUserIdsAndRolesOnTestEnvironment().get(Constants.employeeUserId)
				.equalsIgnoreCase("Lead")) {
			logOutFun.verify_LogOut_Employee();

			commonMethods.verify_logged_In_Employee_Name_Notification_Message_And_Request_For_All_Higher_Authority(
					"team lead level", Constants.teamLeadLevelTesterEmployeeUserId,
					Constants.teamLeadLevelTesterEmployeePassword, LoginAndForgotPasswordTests.actualEmployeeName,
					"wants to submit", lastTaskTitleInDayView);

			request.clickOnFirstRejectButton();
			Thread.sleep(1000);

			request.clickOnRejectSendButtonInRequestSection();
			Thread.sleep(2000);
		}
	}

	// Check after rejected request request card and its details present or not
	@Test(priority = 4)
	public void verify_Is_Request_Action_And_Task_Title_Present_In_Request_Card_List() throws InterruptedException {
		String actualRequestActionInReceivedRequestCard = request.checkRequestActionReceivedRequestCard();
		String requestAction = "wants to submit";

		String actualRequestTaskTitleInReceivedRequestCard = request.checkRequestTaskTitleInReceivedRequestCard();

		String requestMessage = actualRequestActionInReceivedRequestCard + " "
				+ actualRequestTaskTitleInReceivedRequestCard;
		assertFalse(requestMessage.equalsIgnoreCase("" + requestAction + " \"" + lastTaskTitleInDayView + "\""));
		Thread.sleep(2000);
	}

	// Logout as admin and log in as employee
	@Test(priority = 5)
	public void verify_Logout_As_Higher_Authority_Login_As_Employee_And_Check_Employee_Name()
			throws InterruptedException {
		logOutFun.verify_LogOut_Employee();

		commonMethods.verify_Login_Employee_By_Giving_Valid_User_Id_And_Valid_Password(Constants.employeeUserId,
				Constants.employeePassword);
		Thread.sleep(3000);

		String actualEmployeeName = commonMethods.verify_Employee_Name_After_Logged_In(Constants.employeeUserId);
		log.info("Actual employee name at dashboard page is: " + actualEmployeeName + "\n");
	}

	// Check notification message after rejected task submit request
	@Test(priority = 6)
	public void verify_Notification_Message_Of_Task_Submit_Request_Rejected_By_Higher_Authority()
			throws InterruptedException {
		notification.verifyNotificationMessageForSelfTaskSubmit("after task submitted request rejected",
				"rejected your request for", lastTaskTitleInDayView);
	}

	// Check task status after task submit request rejected in day view
	@Test(priority = 7)
	public void verify_Task_Status_In_Day_View_After_Task_Submitted_Request_Rejected_By_Higher_Authority()
			throws InterruptedException {
		myTasks.clickOnDayButton();
		Thread.sleep(2000);

		myTasks.scrollUptoBottomOfTaskDivInDayView();

		Thread.sleep(1000);

		myTasks.clickOnRefreshButtonInDayView();
		Thread.sleep(2000);

		String actualTaskStatusInDayViewAfterTaskSubmittedRequestDeniedByAdmin = myTasks
				.checkLastTaskStatusTextInDayView();
		log.info("Actual task status in day view after task submitted request denied by admin is: "
				+ actualTaskStatusInDayViewAfterTaskSubmittedRequestDeniedByAdmin + "\n");

		if (actualTaskStatusInDayViewAfterTaskSubmittedRequestDeniedByAdmin.equalsIgnoreCase("In Progress")) {
			assertEquals(actualTaskStatusInDayViewAfterTaskSubmittedRequestDeniedByAdmin, "In Progress");
		} else {
			log.info("Task status remained as it is");
		}
		Thread.sleep(2000);
	}

	// Check log message after task submit request rejected in day view
	@Test(priority = 8)
	public void verify_Log_Message_After_Submit_Task_Request_Rejected() throws InterruptedException {
		myActivities.verify_Log_Message(actualHigherAuthorityName, "has rejected your request for task submit in",
				lastTaskTitleInDayView);
	}

	// Check request details in my request
	@Test(priority = 9)
	public void verify_Task_Request_In_My_Request_Section_After_Rejected_Submit_Task_Request()
			throws InterruptedException {
		requestFun.verify_Task_Request_In_My_Request_Section_By_Filtering_Request_Category("Rejected",
				LoginAndForgotPasswordTests.actualEmployeeName, actualHigherAuthorityName, "wants to submit",
				lastTaskTitleInDayView);
	}

	@Test(priority = 18)
	public void verify_Accepted_Request_For_Task_Submit_Of_Employee_From_Higher_Authority_Side()
			throws InterruptedException {
		verify_Self_Task_Submitted_In_Day_View();

		verify_Task_Submitted_Request_In_My_Request_Section();

		if (DataGenerator.employeeUserIdsAndRolesOnTestEnvironment().get(Constants.employeeUserId)
				.equalsIgnoreCase("Developer")) {
			logOutFun.verify_LogOut_Employee();

			commonMethods.verify_logged_In_Employee_Name_Notification_Message_And_Request_For_All_Higher_Authority("lead level",
					Constants.leadLevelTesterEmployeeUserId, Constants.leadLevelTesterEmployeePassword,
					LoginAndForgotPasswordTests.actualEmployeeName, "wants to submit", lastTaskTitleInDayView);

			logOutFun.verify_LogOut_Employee();

			commonMethods.verify_logged_In_Employee_Name_Notification_Message_And_Request_For_All_Higher_Authority(
					"team lead level", Constants.teamLeadLevelTesterEmployeeUserId,
					Constants.teamLeadLevelTesterEmployeePassword, LoginAndForgotPasswordTests.actualEmployeeName,
					"wants to submit", lastTaskTitleInDayView);

			request.clickOnFirstApproveButton();
			Thread.sleep(2000);
		} else if (DataGenerator.employeeUserIdsAndRolesOnTestEnvironment().get(Constants.employeeUserId)
				.equalsIgnoreCase("Lead")) {
			logOutFun.verify_LogOut_Employee();

			commonMethods
					.verify_logged_In_Employee_Name_Notification_Message_And_Request_For_All_Higher_Authority(
					"team lead level", Constants.teamLeadLevelTesterEmployeeUserId,
					Constants.teamLeadLevelTesterEmployeePassword, LoginAndForgotPasswordTests.actualEmployeeName,
					"wants to submit", lastTaskTitleInDayView);

			request.clickOnFirstApproveButton();
			Thread.sleep(2000);
		}

		verify_Is_Request_Action_And_Task_Title_Present_In_Request_Card_List();

		verify_Logout_As_Higher_Authority_Login_As_Employee_And_Check_Employee_Name();
	}

	// Check notification message after approved task submit request
	@Test(priority = 19)
	public void verify_Notification_Message_Of_Task_Submit_Request_Accepted_By_Higher_Authority()
			throws InterruptedException {
		notification.verifyNotificationMessageForSelfTaskSubmit("after task submitted request approved",
				"accepted your request for", lastTaskTitleInDayView);
	}

	// Check task status after task submit request rejected in day view
	@Test(priority = 20)
	public void verify_Task_Status_In_Day_View_After_Task_Submitted_Request_Accepted_By_Higher_Authority()
			throws InterruptedException {
		myTasks.clickOnDayButton();
		Thread.sleep(2000);

		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		myTasks.clickOnRefreshButtonInDayView();
		Thread.sleep(2000);

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
	@Test(priority = 21)
	public void verify_Completed_Tasks_Count_And_Percentage_After_Task_Completed_In_Day_View()
			throws InterruptedException {
		int totalCompetedTasksCount = myTasks.checkSizeOfCompletedTaskInDayView();
		commonMethods.verify_Total_Completed_Tasks_Count_In_Day_View(totalCompetedTasksCount);
		Thread.sleep(1000);

		int totalTasksCount = commonMethods.get_Total_Tasks_Count_In_Day_View();
		commonMethods.verify_Percentage_Of_Completed_Tasks_In_Day_View(totalCompetedTasksCount, totalTasksCount);
		Thread.sleep(1000);
	}

	// Check log message after task submit request approved in day view
	@Test(priority = 22)
	public void verify_Log_Message_After_Submit_Task_Request_Accepted() throws InterruptedException {
		myActivities.verify_Log_Message(actualHigherAuthorityName,
				"has approved your request and changed task status from \"Submitted\" to \"Completed\" in",
				lastTaskTitleInDayView);
	}

	// Check request details in my request
	@Test(priority = 23)
	public void verify_Task_Request_In_My_Request_Section_After_Accepted_Submit_Task_Request()
			throws InterruptedException {
		requestFun.verify_Task_Request_In_My_Request_Section_By_Filtering_Request_Category("Accepted",
				LoginAndForgotPasswordTests.actualEmployeeName, actualHigherAuthorityName, "wants to submit",
				lastTaskTitleInDayView);
	}
}
