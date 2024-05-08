package com.biencaps.erp.testCases;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

import com.biencaps.erp.pageObjectModels.*;
import com.biencaps.erp.utilities.*;

public class ProfilePageUpdateFunctionality {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(ProfilePageUpdateFunctionality.class);

	protected WebElementActions webElementActions;
	protected DashboardPage dashboard;
	protected ProfilePage profile;
	protected CommonTestMethods commonMethods;

	@Test(priority = 1)
	public void verifyGmailKeySubmitAtEmployeeSideWithoutEnteringGmailKey() throws InterruptedException {
		webElementActions = new WebElementActions();
		dashboard = new DashboardPage();
		profile = new ProfilePage();
		commonMethods = new CommonTestMethods();

		dashboard.clickOnEmployeeNameAtDashboard();
		Thread.sleep(1000);

		profile.clickOnEditInfoButton();

		profile.clearGmailKeyField();

		profile.clickOnSendButtonForGmailKeySubmit();

		commonMethods.verifyToastMessage("after not entering gmail key for gmail key submit",
				"Please Fill Correct 16 digit key");

		webElementActions.refreshThePage();
	}

	@Test(priority = 2)
	public void verifyGmailKeySubmitAtEmployeeSideWithoutEnteringPassword() throws InterruptedException {
		dashboard.clickOnEmployeeNameAtDashboard();
		Thread.sleep(1000);

		profile.clickOnEditInfoButton();

		profile.clickOnSendButtonForGmailKeySubmit();

		commonMethods.verifyToastMessage("after not entering password for gmail key submit",
				"Please Fill Correct password");
	}

	@Test(priority = 3, dataProvider = "TestDataForSubmitGmailKey", dataProviderClass = DataProviders.class)
	public void verifyGmailKeySubmitAtEmployeeSide(String gmailKey, String password) throws InterruptedException {
		webElementActions.refreshThePage();

		dashboard.clickOnEmployeeNameAtDashboard();
		Thread.sleep(1000);

		profile.clickOnEditInfoButton();

		profile.clearGmailKeyField();

		profile.enterGmailKey(gmailKey);

		profile.enterPassword(password);

		profile.clickOnSendButtonForGmailKeySubmit();

		if (gmailKey.length() < 16 || gmailKey.length() > 16) {
			commonMethods.verifyToastMessage("after entering invalid gmail key for gmail key submit",
					"Please Fill Correct 16 digit key");
		} else if (password.equalsIgnoreCase(Constants.employeePassword)) {
			commonMethods.verifyToastMessage("after entering invalid password for gmail key submit",
					"Enter valid password");
		} else {
			commonMethods.verifyToastMessage("after entering valid gmail key and valid password for gmail key submit",
					"Updated Successfully");
		}
	}

}
