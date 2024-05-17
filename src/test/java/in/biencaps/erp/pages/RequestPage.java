package in.biencaps.erp.pages;

import org.openqa.selenium.*;

import in.biencaps.erp.utilities.*;

public class RequestPage {
	protected WebDriver driver;

	protected WebElementActions webElementActions = new WebElementActions();

	public By requestSectionLink = By.xpath("//p[normalize-space()='Request']");
	public By refreshIcon = By.xpath("//div[@class='req-header-container']//*[name()='svg']");
	public By receivedRequestButton = By.xpath("//button[normalize-space()='Requests']");
	public By myRequestsButton = By.xpath("//button[normalize-space()='My Requests']");
	public By requestCategoryDropdown = By.xpath("//select[@class='filtersForRequests']");
	public By requestCard = By.xpath("//div[@class='request-card']//p");
	public By requestOfTaskAssignedByEmployee = By.xpath("(//p[@class='sendTo'])[1]");
	public By requestSentToEmployees = By.xpath("(//p[@class='sendTo'])[2]");

	public By approveButtons = By.xpath("//button[@id='req-approved-btn']");
	public By rejectButtons = By.xpath("//button[@id='req-deny-btn']");
	public By rejectSendButton = By.xpath("(//button[normalize-space()='Send'])[1]");

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
		return webElementActions.getTextMethod(requestCard, 2);
	}

	public String checkRequestTaskTitleInReceivedRequestCard() {
		return webElementActions.getTextMethod(requestCard, 3);
	}

	public String checkRequestAssignedByInMyRequestCard() {
		return webElementActions.getTextMethod(requestOfTaskAssignedByEmployee);
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