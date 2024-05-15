package in.biencaps.erp.pages;

import org.openqa.selenium.*;
import java.util.*;
import in.biencaps.erp.utilities.*;

public class DashboardPage {
	protected WebElementActions webElementActions = new WebElementActions();

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
	public By notificationMessages = By.xpath("//div[@class='notification_subject']/h4");

	public By lastArrowForLevelView = By.xpath("(//button[@class='p-tree-toggler p-link']//*[name()='svg'])[last()]");
	public By rolesForLevelView = By.xpath("//span[@class='p-treenode-label']");
	public By lastRoleForLevelView = By.xpath("(//span[@class='p-treenode-label'])[last()]");

	public By newTaskButton = By.xpath("//button[normalize-space()='New Task']");

	public String checkEmployeeNameAtDashboard() {
		return webElementActions.getTextMethod(employeeNameAfterLoggedIn);
	}

	public void clickOnEmployeeNameAtDashboard() {
		webElementActions.clickOnMethod(employeeNameAfterLoggedIn);
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

	public List<String> checkNotificationMessages() {
		return webElementActions.getValuesFromListOfWebElements(notificationMessages);
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
