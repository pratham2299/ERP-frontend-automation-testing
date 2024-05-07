package com.biencaps.erp.pageObjectModels;

import org.openqa.selenium.*;

import com.biencaps.erp.utilities.*;

public class EmployeePage {
	public By userIdsOfEmployee = By.xpath("//tbody//tr//td[starts-with(text(),'INC')]");
	public By employeeNames = By.xpath("//tbody//tr//td[@class='empName cell']");
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
