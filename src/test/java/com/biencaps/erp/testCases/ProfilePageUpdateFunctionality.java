package com.biencaps.erp.testCases;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

import com.biencaps.erp.pageObjectModels.DashboardPage;
import com.biencaps.erp.pageObjectModels.ProfilePage;
import com.biencaps.erp.utilities.CommonTestMethods;
import com.biencaps.erp.utilities.Constants;
import com.biencaps.erp.utilities.DataGenerator;
import com.biencaps.erp.utilities.WebElementActions;

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

	@Test(priority = 3)
	public void verifyGmailKeySubmitAtEmployeeSideByEnteringLessThan16DigitGmailKey() throws InterruptedException {
		dashboard.clickOnEmployeeNameAtDashboard();
		Thread.sleep(1000);

		profile.clickOnEditInfoButton();

		profile.clearGmailKeyField();

		String randomLessThan16DigitGmailKeyInput = DataGenerator.generateRandomString(1, 15);

		profile.enterGmailKey(randomLessThan16DigitGmailKeyInput);

		profile.enterPassword(Constants.employeePassword);

		profile.clickOnSendButtonForGmailKeySubmit();

		commonMethods.verifyToastMessage("after not entering gmail key for gmail key submit",
				"Please Fill Correct 16 digit key");

		webElementActions.refreshThePage();
	}

}
