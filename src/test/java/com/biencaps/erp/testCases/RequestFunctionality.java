package com.biencaps.erp.testCases;

import static org.testng.Assert.*;

import org.apache.logging.log4j.*;

import com.biencaps.erp.pageObjectModels.*;

public class RequestFunctionality extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public Logger log = LogManager.getLogger(RequestFunctionality.class);

	protected DashboardPage dashboard;
	protected RequestPage request;

	/*
	 * This method is responsible to check first request card details and validate
	 * details in request section
	 */
	public void verifyRequestInReceivedRequestCard(String employeeName, String action, String taskTitle)
			throws InterruptedException {
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

		try {
			String actualRequestActionInReceivedRequestCard = request.checkRequestActionReceivedRequestCard();
			log.info("Actual request action in received request card is: " + actualRequestActionInReceivedRequestCard);

			String actualRequestTaskTitleInReceivedRequestCard = request.checkRequestTaskTitleInReceivedRequestCard();
			log.info("Actual request task title in received request card is: "
					+ actualRequestTaskTitleInReceivedRequestCard);

			String requestMessage = actualRequestActionInReceivedRequestCard + " "
					+ actualRequestTaskTitleInReceivedRequestCard;
			log.info("Request action with task title in received card is: " + requestMessage + "\n");
			String actionAndTaskTitleOfRequest = action + " " + taskTitle + "";
			assertTrue(requestMessage.equalsIgnoreCase(actionAndTaskTitleOfRequest));
		} catch (Exception e) {
			log.error("Request action and task title in request card is incorrect" + "\n");
		}
	}

	/*
	 * This method is responsible to check first request card details and validate
	 * details in my request section
	 */
	public void verifyRequestInMyRequestsCard(String employeeName, String requestAction, String taskTitle)
			throws InterruptedException {
		dashboard = new DashboardPage();
		request = new RequestPage();

		dashboard.clickOnRequestSectionLink();
		Thread.sleep(2000);

		request.clickOnMyRequestButton();
		Thread.sleep(2000);

		request.clickOnRefreshIconInRequestSection();
		Thread.sleep(2000);

		try {
			String actualEmployeeNameInMyRequestCard = request.checkEmployeeNameInRequestCard();
			log.info("Actual employee name in my request card is: " + actualEmployeeNameInMyRequestCard);

			if (actualEmployeeNameInMyRequestCard.equals(null) || actualEmployeeNameInMyRequestCard.isBlank()
					|| actualEmployeeNameInMyRequestCard.isEmpty()) {
				log.error("Request sender employee in my request card is incorrect" + "\n");
			} else {
				assertTrue(actualEmployeeNameInMyRequestCard.startsWith(employeeName));
			}
		} catch (Exception e) {
			log.error("Employee name in request card is not found" + "\n");
		}

		String actualRequestSentToEmployeeNameInMyRequestCard = request
				.checkRequestSentToEmployeeNamesInMyRequestCard();
		log.info("Actual request sent to employee name in my request card is: "
				+ actualRequestSentToEmployeeNameInMyRequestCard);

		try {
			String actualRequestActionInMyRequestCard = request.checkRequestActionForInMyRequestCard(requestAction);
			log.info("Actual request action in my request card is: " + actualRequestActionInMyRequestCard);

			String actualRequestTaskTitleInMyRequestCard = request.checkRequestTaskTitleInMyRequestCard(taskTitle);
			log.info("Actual request task title in my request card is: " + actualRequestTaskTitleInMyRequestCard);

			String requestMessage = actualRequestActionInMyRequestCard + " " + actualRequestTaskTitleInMyRequestCard;
			log.info("Request action with task title in my request card is: " + requestMessage + "\n");
			String actionAndTaskTitleOfRequest = requestAction + " " + taskTitle + "";
			assertTrue(requestMessage.equalsIgnoreCase(actionAndTaskTitleOfRequest));
		} catch (Exception e) {
			log.error("Request action and task title in my request card is incorrect" + "\n");
		}
	}

	public void verifyTaskRequestInMyRequestSectionByFilteringRequestCategory(String requestCategory,
			String employeeName, String actionTakenByEmployeeName, String requestAction, String taskTitle)
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

		try {
			String actualEmployeeNameInMyRequestCard = request.checkEmployeeNameInRequestCard();
			log.info("Actual employee name in my request card is: " + actualEmployeeNameInMyRequestCard);

			if (actualEmployeeNameInMyRequestCard.equals(null) || actualEmployeeNameInMyRequestCard.isBlank()
					|| actualEmployeeNameInMyRequestCard.isEmpty()) {
				log.error("Request sender employee in my request card is incorrect" + "\n");
			} else {
				assertTrue(actualEmployeeNameInMyRequestCard.startsWith(employeeName));
			}
		} catch (Exception e) {
			log.error("Employee name in request card is not found" + "\n");
		}

		if (requestCategory.equalsIgnoreCase("Rejected")) {
			String actualRequestRejectedByEmployeeNameInMyRequestCard = request
					.checkRequestSentToEmployeeNamesInMyRequestCard();
			log.info("Actual request rejected by employee name in my request card is: "
					+ actualRequestRejectedByEmployeeNameInMyRequestCard);

			String[] parts = actualRequestRejectedByEmployeeNameInMyRequestCard.split(":");
			// Trim any leading or trailing whitespace from the parts
			String part1 = parts[0].trim();
			String part2 = parts[1].trim();
			assertEquals(part1, "Rejected By");
			assertEquals(part2, actionTakenByEmployeeName);
		} else {
			String actualRequestRejectedByEmployeeNameInMyRequestCard = request
					.checkRequestSentToEmployeeNamesInMyRequestCard();
			log.info("Actual request rejected by employee name in my request card is: "
					+ actualRequestRejectedByEmployeeNameInMyRequestCard);

			String[] parts = actualRequestRejectedByEmployeeNameInMyRequestCard.split(":");
			// Trim any leading or trailing whitespace from the parts
			String part1 = parts[0].trim();
			String part2 = parts[1].trim();
			assertEquals(part1, "Accepted By");
			assertEquals(part2, actionTakenByEmployeeName);
		}

		try {
			String actualRequestActionInMyRequestCard = request.checkRequestActionForInMyRequestCard(requestAction);
			log.info("Actual request action in my request card is: " + actualRequestActionInMyRequestCard);

			String actualRequestTaskTitleInMyRequestCard = request.checkRequestTaskTitleInMyRequestCard(taskTitle);
			log.info("Actual request task title in my request card is: " + actualRequestTaskTitleInMyRequestCard);

			String requestMessage = actualRequestActionInMyRequestCard + " " + actualRequestTaskTitleInMyRequestCard;
			log.info("Request action with task title in my request card is: " + requestMessage + "\n");
			String actionAndTaskTitleOfRequest = requestAction + " " + taskTitle + "";
			assertTrue(requestMessage.equalsIgnoreCase(actionAndTaskTitleOfRequest));
		} catch (Exception e) {
			log.error("Request action and task title in my request card is incorrect" + "\n");
		}
	}
}
