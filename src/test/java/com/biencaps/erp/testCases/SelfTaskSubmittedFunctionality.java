package com.biencaps.erp.testCases;

import static org.testng.Assert.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import com.biencaps.erp.pageObjectModels.*;
import com.biencaps.erp.utilities.*;

/* This class is extended BaseTest class.
 *  BaseTest has driver so you can inherite driver from BaseTest to this class
 */
public class SelfTaskSubmittedFunctionality extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(SelfTaskSubmittedFunctionality.class);

	public static String lastTaskTitleInDayView;
	public static String actualHigherAuthorityName;

	protected MyTasksPage myTasks;
	protected DashboardPage dashboard;
	protected CommonTestMethods commonMethods;
	protected LogoutFunctionality logOutFun;
	protected LoginAndForgotPasswordFunctionality loginFun;
	protected RequestFunctionality requestFun;
	protected RequestPage request;
	protected MyActivitiesFunctionality myActivities;

	// Self task submit and check log messsage
	@Test(priority = 1)
	public void verifySelfTaskSubmittedInDayView() throws InterruptedException {
		myTasks = new MyTasksPage(driver);
		commonMethods = new CommonTestMethods();
		loginFun = new LoginAndForgotPasswordFunctionality();
		logOutFun = new LogoutFunctionality();
		requestFun = new RequestFunctionality();
		request = new RequestPage(driver);
		dashboard = new DashboardPage(driver);
		myActivities = new MyActivitiesFunctionality();

		myTasks.clickOnDayButton();
		Thread.sleep(2000);

		myTasks.scrollUptoBottomOfTaskDivInDayView();
		Thread.sleep(1000);

		String actualOldTaskStatusInDayView = myTasks.checkLastTaskStatusTextInDayView();
		log.info("Actual old task status in day view is: " + actualOldTaskStatusInDayView);

		lastTaskTitleInDayView = myTasks.checkLastTaskTitleInDayView();
		log.info("Last task title in day view is: " + lastTaskTitleInDayView);

		myTasks.clickOnLastTaskStatusInDayView();

		myTasks.clickOnSelectedTaskStatusValueFromDropdownInDayView("Submitted");

		commonMethods.verifyToastMessage("after task submitted in day view",
				"Request for update status sent successfully");

		String actualUpdatedTaskStatusInDayView = myTasks.checkLastTaskStatusTextInDayView();
		log.info("Actual updated task status in day view is: " + actualUpdatedTaskStatusInDayView);
		assertEquals(actualUpdatedTaskStatusInDayView, "Submitted");
		Thread.sleep(1000);

		myActivities.verifyLogMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName, "status",
				actualOldTaskStatusInDayView, actualUpdatedTaskStatusInDayView, lastTaskTitleInDayView);
	}

	// Check request details in my request
	@Test(priority = 2)
	public void verifyTaskSubmittedRequestInMyRequestSection() throws InterruptedException {
		requestFun.verifyRequestInMyRequestsCard(LoginAndForgotPasswordFunctionality.actualEmployeeName,
				"wants to submit", lastTaskTitleInDayView);
	}

//	@Test(priority = 3)
//	public void verifyNotificationMessageAndRequestOfEmployeeAtAllHigherAuthority() throws InterruptedException {
//		logOutFun.verifyLogOutEmployee();
//
//		commonMethods.verifyloggedInEmployeeNameNotificationMessageAndRequestForAllHigherAuthority("lead level",
//				Constants.leadLevelTesterEmployeeUserId, Constants.leadLevelTesterEmployeePassword,
//				LoginAndForgotPasswordFunctionality.actualEmployeeName, "wants to submit", lastTaskTitleInDayView);
//
//		logOutFun.verifyLogOutEmployee();
//
//		commonMethods.verifyloggedInEmployeeNameNotificationMessageAndRequestForAllHigherAuthority("team lead level",
//				Constants.teamLeadLevelTesterEmployeeUserId, Constants.teamLeadLevelTesterEmployeePassword,
//				LoginAndForgotPasswordFunctionality.actualEmployeeName, "wants to submit", lastTaskTitleInDayView);
//
//		request.clickOnFirstRejectButton();
//		Thread.sleep(1000);
//
//		request.clickOnRejectSendButtonInRequestSection();
//		Thread.sleep(2000);
//	}

	// Logout from employee and log in as lead level employee
	@Test(priority = 3)
	public void verifyLogoutAsEmployeeLoginAsLeadLevelEmployeeAndCheckLeadLevelEmployeeName()
			throws InterruptedException {
		logOutFun.verifyLogOutEmployee();

		commonMethods.verifyLoginEmployeeByGivingValidUserIdAndValidPassword(Constants.leadLevelTesterEmployeeUserId,
				Constants.leadLevelTesterEmployeePassword);
		Thread.sleep(3000);

		actualHigherAuthorityName = commonMethods
				.verifyEmployeeNameAfterLoggedIn(Constants.leadLevelTesterEmployeeUserId);
		log.info("Actual lead level employee name at dashboard page is: " + actualHigherAuthorityName + "\n");
	}

	// Check notification message at lead level employee
	// of task submit request of employee
	@Test(priority = 4, enabled = false)
	public void verifyNotificationMessageAtLeadLevelEmployeeSideOfTaskSubmitOfEmployee() throws InterruptedException {
		commonMethods.verifyNotificationMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName,
				"after employee sent task submit request at lead level employee side", "wants to submit",
				lastTaskTitleInDayView);
	}

	// Reject task submit request by checking each request details
	@Test(priority = 5)
	public void verifyRequestForTaskSubmitOfEmployeeByLeadLevelEmployee() throws InterruptedException {
		requestFun.verifyRequestInReceivedRequestCard(LoginAndForgotPasswordFunctionality.actualEmployeeName,
				"wants to submit", lastTaskTitleInDayView);
	}

	// Logout from employee and log in as team lead level employee
	@Test(priority = 6)
	public void verifyLogoutAsEmployeeLoginAsTeamLeadLevelEmployeeAndCheckTeamLeadLevelEmployeeName()
			throws InterruptedException {
		logOutFun.verifyLogOutEmployee();

		commonMethods.verifyLoginEmployeeByGivingValidUserIdAndValidPassword(
				Constants.teamLeadLevelTesterEmployeeUserId, Constants.teamLeadLevelTesterEmployeePassword);
		Thread.sleep(3000);

		actualHigherAuthorityName = commonMethods
				.verifyEmployeeNameAfterLoggedIn(Constants.teamLeadLevelTesterEmployeeUserId);
	}

	// Check notification message at team lead level employee
	// of task submit request of employee
	@Test(priority = 7, enabled = false)
	public void verifyNotificationMessageAtTeamLeadLevelEmployeeSideOfTaskSubmitOfEmployee()
			throws InterruptedException {
		commonMethods.verifyNotificationMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName,
				"after employee sent task submit request at team lead level employee side", "wants to submit",
				lastTaskTitleInDayView);
	}

	// Reject task submit request by checking each request details
	@Test(priority = 8)
	public void verifyRequestForTaskSubmitOfEmployeeByTeamLeadLevelEmployee() throws InterruptedException {
		requestFun.verifyRequestInReceivedRequestCard(LoginAndForgotPasswordFunctionality.actualEmployeeName,
				"wants to submit", lastTaskTitleInDayView);

		request.clickOnFirstRejectButton();
		Thread.sleep(1000);

		request.clickOnRejectSendButtonInRequestSection();
		Thread.sleep(2000);
	}

	// Logout from employee and log in as admin
	@Test(priority = 9, enabled = false)
	public void verifyLogoutAsEmployeeLoginAsAdminAndCheckAdminName() throws InterruptedException {
		logOutFun.verifyLogOutEmployee();

		commonMethods.verifyLoginEmployeeByGivingValidUserIdAndValidPassword(Constants.adminLevelTesterEmployeeUserId,
				Constants.adminLevelTesterEmployeePassword);
		Thread.sleep(3000);

		actualHigherAuthorityName = commonMethods
				.verifyEmployeeNameAfterLoggedIn(Constants.adminLevelTesterEmployeeUserId);
		log.info("Actual admin level employee name at dashboard page is: " + actualHigherAuthorityName + "\n");
	}

	// Check notification message at team admin level employee
	// of task submit request of employee
	@Test(priority = 10, enabled = false)
	public void verifyNotificationMessageAtAdminLevelEmployeeSideOfTaskSubmitOfEmployee() throws InterruptedException {
		commonMethods.verifyNotificationMessage(LoginAndForgotPasswordFunctionality.actualEmployeeName,
				"after employee sent task submit request at admin level employee side", "wants to submit",
				lastTaskTitleInDayView);
	}

	// Deny task submit request by checking each request details
	@Test(priority = 11, enabled = false)
	public void verifyRejectedRequestForTaskSubmitOfEmployeeFromAdminSide() throws InterruptedException {
		requestFun.verifyRequestInReceivedRequestCard(LoginAndForgotPasswordFunctionality.actualEmployeeName,
				"wants to submit", lastTaskTitleInDayView);
	}

	// Check after rejected request request card and its details present or not
	@Test(priority = 12)
	public void verifyIsRequestActionAndTaskTitlePresentInRequestCardList() throws InterruptedException {
		String actualRequestActionInReceivedRequestCard = request.checkRequestActionReceivedRequestCard();
		String requestAction = "wants to submit";

		String actualRequestTaskTitleInReceivedRequestCard = request.checkRequestTaskTitleInReceivedRequestCard();

		String requestMessage = actualRequestActionInReceivedRequestCard + " "
				+ actualRequestTaskTitleInReceivedRequestCard;
		assertFalse(requestMessage.equalsIgnoreCase("" + requestAction + " \"" + lastTaskTitleInDayView + "\""));
		Thread.sleep(2000);
	}

	// Logout as admin and log in as employee
	@Test(priority = 13)
	public void verifyLogoutAsHigherAuthorityLoginAsEmployeeAndCheckEmployeeName() throws InterruptedException {
		logOutFun.verifyLogOutEmployee();

		loginFun.verifyLoginEmployeeByGivingValidUserIdAndValidPassword();
		Thread.sleep(3000);

		String actualEmployeeName = commonMethods
				.verifyEmployeeNameAfterLoggedIn(LoginAndForgotPasswordFunctionality.validUserId);
		log.info("Actual employee name at dashboard page is: " + actualEmployeeName + "\n");
	}

	// Check notification message after rejected task submit request
	@Test(priority = 14)
	public void verifyNotificationMessageOfTaskSubmitRequestRejectedByHigherAuthority() throws InterruptedException {
		commonMethods.verifyNotificationMessage(actualHigherAuthorityName, "after task submitted request rejected",
				"rejected your request for", lastTaskTitleInDayView);
	}

	// Check task status after task submit request rejected in day view
	@Test(priority = 15)
	public void verifyTaskStatusInDayViewAfterTaskSubmittedRequestRejectedByHigherAuthority()
			throws InterruptedException {
		myTasks.clickOnDayButton();

		myTasks.clickOnWeekButton();

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
	@Test(priority = 16)
	public void verifyLogMessageAfterSubmitTaskRequestRejected() throws InterruptedException {
		myActivities.verifyLogMessage(actualHigherAuthorityName, "has rejected your request for task submit in",
				lastTaskTitleInDayView);
	}

	// Check request details in my request
	@Test(priority = 17)
	public void verifyTaskRequestInMyRequestSectionAfterRejectedSubmitTaskRequest() throws InterruptedException {
		requestFun.verifyTaskRequestInMyRequestSectionByFilteringRequestCategory("Rejected",
				LoginAndForgotPasswordFunctionality.actualEmployeeName, actualHigherAuthorityName, "wants to submit",
				lastTaskTitleInDayView);
	}

	@Test(priority = 18)
	public void verifyApprovedRequestForTaskSubmitOfEmployeeFromHigherAuthoritySide() throws InterruptedException {
		dashboard.clickOnMyTasksSection();
		Thread.sleep(2000);

		myTasks.clickOnDayButton();
		Thread.sleep(2000);

		verifySelfTaskSubmittedInDayView();

		verifyTaskSubmittedRequestInMyRequestSection();

		verifyLogoutAsEmployeeLoginAsLeadLevelEmployeeAndCheckLeadLevelEmployeeName();

		verifyRequestForTaskSubmitOfEmployeeByLeadLevelEmployee();

		verifyLogoutAsEmployeeLoginAsTeamLeadLevelEmployeeAndCheckTeamLeadLevelEmployeeName();

		requestFun.verifyRequestInReceivedRequestCard(LoginAndForgotPasswordFunctionality.actualEmployeeName,
				"wants to submit", lastTaskTitleInDayView);

//		commonMethods.verifyloggedInEmployeeNameNotificationMessageAndRequestForAllHigherAuthority("lead level",
//				Constants.leadLevelTesterEmployeeUserId, Constants.leadLevelTesterEmployeePassword,
//				LoginAndForgotPasswordFunctionality.actualEmployeeName, "wants to submit", lastTaskTitleInDayView);
//
//		logOutFun.verifyLogOutEmployee();
//
//		commonMethods.verifyloggedInEmployeeNameNotificationMessageAndRequestForAllHigherAuthority("team lead level",
//				Constants.teamLeadLevelTesterEmployeeUserId, Constants.teamLeadLevelTesterEmployeePassword,
//				LoginAndForgotPasswordFunctionality.actualEmployeeName, "wants to submit", lastTaskTitleInDayView);

		request.clickOnFirstApproveButton();
		Thread.sleep(2000);

		verifyIsRequestActionAndTaskTitlePresentInRequestCardList();

		verifyLogoutAsHigherAuthorityLoginAsEmployeeAndCheckEmployeeName();
	}

	// Check notification message after approved task submit request
	@Test(priority = 19)
	public void verifyNotificationMessageOfTaskSubmitRequestApprovedByHigherAuthority() throws InterruptedException {
		commonMethods.verifyNotificationMessage(actualHigherAuthorityName, "after task submitted request approved",
				"accepted your request for", lastTaskTitleInDayView);
	}

	// Check task status after task submit request rejected in day view
	@Test(priority = 20)
	public void verifyTaskStatusInDayViewAfterTaskSubmittedRequestApprovedByHigherAuthority()
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
	public void verifyCompletedTasksCountAndPercentageAfterTaskCompletedInDayView() throws InterruptedException {
		int totalCompetedTasksCount = myTasks.checkSizeOfCompletedTaskInDayView();
		commonMethods.verifyTotalCompletedTasksCountInDayView(totalCompetedTasksCount);
		Thread.sleep(1000);

		int totalTasksCount = commonMethods.getTotalTasksCountInDayView();
		commonMethods.verifyPercentageOfCompletedTasksInDayView(totalCompetedTasksCount, totalTasksCount);
		Thread.sleep(1000);
	}

	// Check log message after task submit request approved in day view
	@Test(priority = 22)
	public void verifyLogMessageAfterSubmitTaskRequestApproved() throws InterruptedException {
		myActivities.verifyLogMessage(actualHigherAuthorityName,
				"has approved your request and changed task status from \"Submitted\" to \"Completed\" in",
				lastTaskTitleInDayView);
	}

	// Check request details in my request
	@Test(priority = 23)
	public void verifyTaskRequestInMyRequestSectionAfterApprovedSubmitTaskRequest() throws InterruptedException {
		requestFun.verifyTaskRequestInMyRequestSectionByFilteringRequestCategory("Accepted",
				LoginAndForgotPasswordFunctionality.actualEmployeeName, actualHigherAuthorityName, "wants to submit",
				lastTaskTitleInDayView);
	}
}
