package com.biencaps.erp.pageObjectModels;

import org.openqa.selenium.*;

import com.biencaps.erp.utilities.*;

public class DashboardPage {
	// Here one driver variable declared
	protected WebDriver driver;

	protected WebElementActions webElementActions;

	public By employeeNameAfterLoggedIn = By.xpath("//p[@class='profileName']");

	public By logOutIcon = By.xpath("(//*[name()='svg'][@class='logout-req'])[1]");
	public By logOutButton = By.xpath("//div[@class='logout-confirm']");
	public By cancelLogOutButton = By.xpath("//div[@class='cancel-logout']");

	public By myTasksSectionLink = By.xpath("//p[normalize-space()='My Tasks']");
	public By informationSectionLink = By.xpath("//p[normalize-space()='Information']");
	public By requestSectionLink = By.xpath("//div[@class='requestsNotify']//p");
	public By employeeSectionLink = By.xpath("//p[normalize-space()='Employee']");
	public By projectsSectionLink = By.xpath("//p[normalize-space()='Projects']");
	public By analyticsSectionLink = By.xpath("//p[normalize-space()='Analytics']");
	public By closureReportSectionLink = By.xpath("//p[normalize-space()='Closure Report']");
	public By settingsSectionLink = By.xpath("//p[normalize-space()='Settings']");
	public By createClosureButton = By.xpath("//p[normalize-space()='Create Closure']");
	public By notificationIcon = By
			.xpath("//div[@class='notifaction-icon-container']//*[name()='svg']//*[name()='path']");
	public By firstNotificationMessage = By.xpath("(//div[@class='notification_subject']/h4)[1]");

	public By lastArrowForLevelView = By.xpath("(//button[@class='p-tree-toggler p-link']//*[name()='svg'])[last()]");
	public By rolesForLevelView = By.xpath("//span[@class='p-treenode-label']");
	public By lastRoleForLevelView = By.xpath("(//span[@class='p-treenode-label'])[last()]");

	public By newTaskButton = By.xpath("//button[normalize-space()='New Task']");

	// constructor created for webdriver initialized to this class driver variable
	// Also that webdriver passed as argument to selenium methods class
	// (A class which has common selenium methods used for all classes)
	public DashboardPage(WebDriver driver2) {
		this.driver = driver2;
		webElementActions = new WebElementActions();
	}

	public String checkEmployeeNameAtDashboard() {
		return webElementActions.getTextMethod(employeeNameAfterLoggedIn);
	}

	public void clickOnMyTasksSection() {
		webElementActions.clickOnMethod(myTasksSectionLink);
	}

	public void clickOnInformationSection() {
		webElementActions.clickOnMethod(informationSectionLink);
	}

	public void clickOnRequestSectionLink() {
		webElementActions.clickOnMethod(requestSectionLink);
	}

	public void clickOnEmployeeSectionLink() {
		webElementActions.clickOnMethod(employeeSectionLink);
	}

	public void clickOnProjectsSectionLink() {
		webElementActions.clickOnMethod(projectsSectionLink);
	}

	public void clickOnAnalyticsSectionLink() {
		webElementActions.clickOnMethod(analyticsSectionLink);
	}

	public void clickOnClosureReportSectionLink() {
		webElementActions.clickOnMethod(closureReportSectionLink);
	}

	public void clickOnSettingsSectionLink() {
		webElementActions.clickOnMethod(settingsSectionLink);
	}

	public void clickOnLogOutIcon() {
		webElementActions.clickOnMethod(logOutIcon);
	}

	public void clickOnLogOutButton() {
		webElementActions.clickOnMethod(logOutButton);
	}

	public void clickOnCancelLogOutButton() {
		webElementActions.clickOnMethod(cancelLogOutButton);
	}

	public void clickOnNotificationIcon() {
		webElementActions.clickOnMethod(notificationIcon);
	}

	public String checkFirstNotificationMessageForRequestDenied() {
		return webElementActions.getTextMethod(firstNotificationMessage);
	}

	public void clickOnLastArrowForLevelView() {
		webElementActions.clickOnMethod(lastArrowForLevelView);
	}

	public String checkRoleForLevelView() {
		return webElementActions.getTextMethod(lastRoleForLevelView);
	}

	public void clickOnRandomRoleForLevelView() {
		webElementActions.clickOnMethod(lastRoleForLevelView);
	}

	public void clickOnEmployeeFromLevelView(String employeeName) {
		By employeeNameAtLevelView = By.xpath("//p[normalize-space()='" + employeeName + "']");
		webElementActions.clickOnMethod(employeeNameAtLevelView);
	}

	public void clickOnNewTaskButtonFromLevelView() {
		webElementActions.clickOnMethod(newTaskButton);
	}
}
