package in.biencaps.erp.testCases;

import static org.testng.Assert.*;
import org.apache.logging.log4j.*;

import in.biencaps.erp.pages.*;

public class RequestTests extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public Logger log = LogManager.getLogger(RequestTests.class);

	protected DashboardPage dashboard;
	protected RequestPage request;

	/*
	 * This method is responsible to check first request card details and validate
	 * details in request section
	 */
	public void verify_Request_In_Received_Request_Card(String employeeName, String taskOwnerNameWithTaskDate,
			String action, String taskTitle) throws InterruptedException {
		dashboard = new DashboardPage();
		request = new RequestPage();

		dashboard.clickOnRequestSectionLink();
		Thread.sleep(2000);

		request.clickOnRefreshIconInRequestSection();
		Thread.sleep(2000);

		String actualRequestSenderEmployeeNameInRequestCard = request.checkEmployeeNameInRequestCard();
		log.info("Actual request sender employee name in request card is: "
				+ actualRequestSenderEmployeeNameInRequestCard);

		if (actualRequestSenderEmployeeNameInRequestCard.equals(null)
				|| actualRequestSenderEmployeeNameInRequestCard.isBlank()
				|| actualRequestSenderEmployeeNameInRequestCard.isEmpty()) {
			log.error("Request sender employee in request card is incorrect" + "\n");
		} else {
			assertTrue(actualRequestSenderEmployeeNameInRequestCard.startsWith(employeeName));
		}

		String actualTaskAssignedByInMyRequestCard = request.checkRequestAssignedByInMyRequestCard();
		log.info("Actual task assigned by employee name in my request card is: " + actualTaskAssignedByInMyRequestCard);

		String[] parts = actualTaskAssignedByInMyRequestCard.split(":");
		// Trim any leading or trailing whitespace from the parts
		String part1 = parts[0].trim();
		String part2 = parts[1].trim();
		assertEquals(part1, "Assigned By");
		assertEquals(part2, taskOwnerNameWithTaskDate);

		String actualRequestActionInReceivedRequestCard = request.checkRequestActionReceivedRequestCard();
		log.info("Actual request action in received request card is: " + actualRequestActionInReceivedRequestCard);

		String actualRequestTaskTitleInReceivedRequestCard = request.checkRequestTaskTitleInReceivedRequestCard();
		log.info("Actual request task title in received request card is: "
				+ actualRequestTaskTitleInReceivedRequestCard);

		String requestMessage = actualRequestActionInReceivedRequestCard + " "
				+ actualRequestTaskTitleInReceivedRequestCard;
		log.info("Request action with task title in received card is: " + requestMessage + "\n");
		String actionAndTaskTitleOfRequest = action + " " + taskTitle + "";
		assertEquals(requestMessage, actionAndTaskTitleOfRequest);
	}

	/*
	 * This method is responsible to check first request card details and validate
	 * details in my request section
	 */
	public void verify_Request_In_My_Requests_Card(String employeeName, String taskOwnerNameWithTaskDate,
			String requestAction, String taskTitle) throws InterruptedException {
		dashboard = new DashboardPage();
		request = new RequestPage();

		dashboard.clickOnRequestSectionLink();
		Thread.sleep(2000);

		request.clickOnMyRequestButton();
		Thread.sleep(2000);

		request.clickOnRefreshIconInRequestSection();
		Thread.sleep(2000);

		String actualEmployeeNameInMyRequestCard = request.checkEmployeeNameInRequestCard();
		log.info("Actual employee name in my request card is: " + actualEmployeeNameInMyRequestCard);

		if (actualEmployeeNameInMyRequestCard.equals(null) || actualEmployeeNameInMyRequestCard.isBlank()
				|| actualEmployeeNameInMyRequestCard.isEmpty()) {
			log.error("Request sender employee in my request card is incorrect" + "\n");
		} else {
			assertTrue(actualEmployeeNameInMyRequestCard.startsWith(employeeName));
		}

		String actualTaskAssignedByInMyRequestCard = request.checkRequestAssignedByInMyRequestCard();
		log.info("Actual task assigned by employee name in my request card is: " + actualTaskAssignedByInMyRequestCard);

		String[] parts = actualTaskAssignedByInMyRequestCard.split(":");
		// Trim any leading or trailing whitespace from the parts
		String part1 = parts[0].trim();
		String part2 = parts[1].trim();
		assertEquals(part1, "Assigned By");
		assertEquals(part2, taskOwnerNameWithTaskDate);

		String actualRequestSentToEmployeeNameInMyRequestCard = request
				.checkRequestSentToEmployeeNamesInMyRequestCard();
		log.info("Actual request sent to employee name in my request card is: "
				+ actualRequestSentToEmployeeNameInMyRequestCard);

		String actualRequestActionInMyRequestCard = request.checkRequestActionForInMyRequestCard(requestAction);
		log.info("Actual request action in my request card is: " + actualRequestActionInMyRequestCard);

		String actualRequestTaskTitleInMyRequestCard = request.checkRequestTaskTitleInMyRequestCard(taskTitle);
		log.info("Actual request task title in my request card is: " + actualRequestTaskTitleInMyRequestCard);

		String requestMessage = actualRequestActionInMyRequestCard + " " + actualRequestTaskTitleInMyRequestCard;
		log.info("Request action with task title in my request card is: " + requestMessage + "\n");
		String actionAndTaskTitleOfRequest = requestAction + " " + taskTitle + "";
		assertEquals(requestMessage, actionAndTaskTitleOfRequest);
	}

	public void verify_Task_Request_In_My_Request_Section_By_Filtering_Request_Category(String requestCategory,
			String taskOwnerNameWithTaskDate, String actionTakenByEmployeeName, String requestAction, String taskTitle)
			throws InterruptedException {
		dashboard = new DashboardPage();
		request = new RequestPage();

		dashboard.clickOnRequestSectionLink();
		Thread.sleep(2000);

		request.clickOnMyRequestButton();
		Thread.sleep(2000);

		request.clickOnRequestCategoryDropdown();
		Thread.sleep(1000);

		request.clickOnRefreshIconInRequestSection();
		Thread.sleep(2000);

		if (requestCategory.equalsIgnoreCase("Rejected")) {
			request.selectRejectedValueForFilterRequest();
		} else {
			request.selectAcceptedValueForFilterRequest();
		}
		Thread.sleep(2000);

		String actualEmployeeNameInMyRequestCard = request.checkEmployeeNameInRequestCard();
		log.info("Actual employee name in my request card is: " + actualEmployeeNameInMyRequestCard);

		if (actualEmployeeNameInMyRequestCard.equals(null) || actualEmployeeNameInMyRequestCard.isBlank()
				|| actualEmployeeNameInMyRequestCard.isEmpty()) {
			log.error("Request sender employee in my request card is incorrect" + "\n");
		} else {
			assertTrue(actualEmployeeNameInMyRequestCard.startsWith(LoginAndForgotPasswordTests.actualEmployeeName));
		}

		if (requestCategory.equalsIgnoreCase("Rejected")) {
			String actualTaskAssignedByInMyRequestCard = request.checkRequestAssignedByInMyRequestCard();
			log.info("Actual task assigned by employee name in my request card is: "
					+ actualTaskAssignedByInMyRequestCard);

			String[] partsOfAssignedBy = actualTaskAssignedByInMyRequestCard.split(":");
			// Trim any leading or trailing whitespace from the parts
			String part1OfAssignedBy = partsOfAssignedBy[0].trim();
			String part2OfAssignedBy = partsOfAssignedBy[1].trim();
			assertEquals(part1OfAssignedBy, "Assigned By");
			assertEquals(part2OfAssignedBy, taskOwnerNameWithTaskDate);

			String actualRequestRejectedByEmployeeNameInMyRequestCard = request
					.checkRequestSentToEmployeeNamesInMyRequestCard();
			log.info("Actual request rejected by employee name in my request card is: "
					+ actualRequestRejectedByEmployeeNameInMyRequestCard);

			String[] partsOfAcceptedBy = actualRequestRejectedByEmployeeNameInMyRequestCard.split(":");
			// Trim any leading or trailing whitespace from the parts
			String part1OfAcceptedBy = partsOfAcceptedBy[0].trim();
			String part2OfAcceptedBy = partsOfAcceptedBy[1].trim();
			assertEquals(part1OfAcceptedBy, "Rejected By");
			assertEquals(part2OfAcceptedBy, actionTakenByEmployeeName);
		} else {
			String actualTaskAssignedByInMyRequestCard = request.checkRequestAssignedByInMyRequestCard();
			log.info("Actual task assigned by employee name in my request card is: "
					+ actualTaskAssignedByInMyRequestCard);

			String[] partsOfAssignedBy = actualTaskAssignedByInMyRequestCard.split(":");
			// Trim any leading or trailing whitespace from the parts
			String part1OfAssignedBy = partsOfAssignedBy[0].trim();
			String part2OfAssignedBy = partsOfAssignedBy[1].trim();
			assertEquals(part1OfAssignedBy, "Assigned By");
			assertEquals(part2OfAssignedBy, taskOwnerNameWithTaskDate);

			String actualRequestAcceptedByEmployeeNameInMyRequestCard = request
					.checkRequestSentToEmployeeNamesInMyRequestCard();
			log.info("Actual request accepted by employee name in my request card is: "
					+ actualRequestAcceptedByEmployeeNameInMyRequestCard);

			String[] partsOfAcceptedBy = actualRequestAcceptedByEmployeeNameInMyRequestCard.split(":");
			// Trim any leading or trailing whitespace from the parts
			String part1OfAcceptedBy = partsOfAcceptedBy[0].trim();
			String part2OfAcceptedBy = partsOfAcceptedBy[1].trim();
			assertEquals(part1OfAcceptedBy, "Accepted By");
			assertEquals(part2OfAcceptedBy, actionTakenByEmployeeName);
		}

		String actualRequestActionInMyRequestCard = request.checkRequestActionForInMyRequestCard(requestAction);
		log.info("Actual request action in my request card is: " + actualRequestActionInMyRequestCard);

		String actualRequestTaskTitleInMyRequestCard = request.checkRequestTaskTitleInMyRequestCard(taskTitle);
		log.info("Actual request task title in my request card is: " + actualRequestTaskTitleInMyRequestCard);

		String requestMessage = actualRequestActionInMyRequestCard + " " + actualRequestTaskTitleInMyRequestCard;
		log.info("Request action with task title in my request card is: " + requestMessage + "\n");
		String actionAndTaskTitleOfRequest = requestAction + " " + taskTitle + "";
		assertEquals(requestMessage, actionAndTaskTitleOfRequest);
	}
}
