package in.biencaps.erp.pages;

import org.openqa.selenium.*;

import in.biencaps.erp.utilities.*;

public class EmployeePage {
	public By userIdsOfEmployee = By.xpath("//tbody//tr//td[2]");
	public By employeeNames = By.xpath("//tbody//tr//td[3]");
	public By employeeRoles = By.xpath("//tbody//tr//td[7]");
	public By employeeDiv = By.xpath("//div[@class='dashboard-container']");
	public By loadMoreButton = By.xpath("//div[@class='load-more-text']");

	protected WebElementActions webElementActions = new WebElementActions();

	public void scrollBottomOfEmployeeContainer() {
		webElementActions.scrollBottomOfDiv(employeeDiv);
	}

	public void clickOnLoadMoreButton() {
		webElementActions.scrollUntilElementFound(loadMoreButton);
		webElementActions.clickOnMethod(loadMoreButton);
	}
}
