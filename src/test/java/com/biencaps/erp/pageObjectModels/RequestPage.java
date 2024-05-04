package com.biencaps.erp.pageObjectModels;

import org.openqa.selenium.*;

import com.biencaps.erp.utilities.*;

public class RequestPage {
	protected WebDriver driver;

	protected WebElementActions webElementActions;

	public By requestSectionLink = By.xpath("//p[normalize-space()='Request']");
	public By refreshIcon = By.xpath("//div[@class='retTitle']//*[name()='svg']");
	public By receivedRequestButton = By.xpath("//button[normalize-space()='Requests']");
	public By myRequestsButton = By.xpath("//button[normalize-space()='My Requests']");
	public By requestCategoryDropdown = By.xpath("//select[@class='filtersForRequests']");
	public By requestCardInMyRequest = By.xpath("//div[@class='request-card']//p");
	public By requestSentToEmployees = By.xpath("(//p[@class='sendTo'])[1]");
	public By requestCard = By.xpath("//div[@class='request-main-container']//p");

	public By approveButtons = By.xpath("//button[@id='req-approved-btn']");
	public By rejectButtons = By.xpath("//button[@id='req-deny-btn']");
	public By rejectSendButton = By.xpath("(//button[normalize-space()='Send'])[1]");

	// constructor created for webdriver initialized to this class driver variable
	// Also that webdriver passed as argument to selenium methods class
	// (A class which has common selenium methods used for all classes)
	public RequestPage(WebDriver driver2) {
		this.driver = driver2;
		webElementActions = new WebElementActions();
	}

	public void clickOnRefreshIconInRequestSection() {
		webElementActions.clickOnMethod(refreshIcon);
	}

	public void clickOnMyRequestButton() {
		webElementActions.clickOnMethod(myRequestsButton);
	}

	public void clickOnRejectSendButtonInRequestSection() {
		webElementActions.clickOnMethod(rejectSendButton);
	}

	public void clickOnFirstApproveButton() {
		webElementActions.clickOnMethod(approveButtons, 0);
	}

	public void clickOnFirstRejectButton() {
		webElementActions.clickOnMethod(rejectButtons, 0);
	}

	public String checkEmployeeNameInRequestCard() {
		return webElementActions.getTextMethod(requestCard, 0);
	}

	public String checkRequestActionReceivedRequestCard() {
		return webElementActions.getTextMethod(requestCard, 1);
	}

	public String checkRequestTaskTitleInReceivedRequestCard() {
		return webElementActions.getTextMethod(requestCard, 2);
	}

	public String checkRequestSentToEmployeeNamesInMyRequestCard() {
		return webElementActions.getTextMethod(requestSentToEmployees);
	}

	public String checkRequestActionForInMyRequestCard(String action) {
		By requestAction = By.xpath("(//p[contains(text(),'" + action + "')])[1]");
		return webElementActions.getTextMethod(requestAction);
	}

	public String checkRequestTaskTitleInMyRequestCard(String taskTitle) {
		By requestTaskTitle = By.xpath("(//p[normalize-space()='" + taskTitle + "'])[1]");
		return webElementActions.getTextMethod(requestTaskTitle);
	}

	public void clickOnRequestCategoryDropdown() {
		webElementActions.clickOnMethod(requestCategoryDropdown);
	}

	public void selectRejectedValueForFilterRequest() {
		webElementActions.selectByVisibleTextMethod(requestCategoryDropdown, "Rejected");
	}

	public void selectAcceptedValueForFilterRequest() {
		webElementActions.selectByVisibleTextMethod(requestCategoryDropdown, "Accepted");
	}
}
