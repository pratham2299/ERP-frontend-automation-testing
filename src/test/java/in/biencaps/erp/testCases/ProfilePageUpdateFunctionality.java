package in.biencaps.erp.testCases;

import static org.testng.Assert.*;

import java.util.regex.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import in.biencaps.erp.pages.*;
import in.biencaps.erp.utilities.*;

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

		profile.clearPasswordField();

		profile.enterPassword(password);

		profile.clickOnSendButtonForGmailKeySubmit();

		if (!gmailKey.equalsIgnoreCase(Constants.gmailKey)) {
			commonMethods.verifyToastMessage("after entering invalid gmail key for gmail key submit",
					"Please Fill Correct 16 digit key");
		} else if (!password.equalsIgnoreCase(Constants.employeePassword)) {
			commonMethods.verifyToastMessage("after entering invalid password for gmail key submit",
					"Enter valid password");
		} else {
			commonMethods.verifyToastMessage("after entering valid gmail key and valid password for gmail key submit",
					"Updated Successfully");
		}
	}

	@Test(priority = 4)
	public void verifyPrimaryMobileNumberTextfieldByLeavingFieldEmpty() throws InterruptedException {
		profile.scrollUntilSubmitButtonFound();

		profile.clearPrimaryMobileNumberTextfield();

		profile.clickOnSubmitButton();

		commonMethods.verifyToastMessage("after leaving primary mobile number textfield empty",
				"Enter Valid Primary Mobile Number");

		String actualBorderColorOfPrimaryMobileNumberTextfieldAfterLeavingFieldEmpty = profile
				.checkBorderColorOfPrimaryMobileNumberTextfield();
		log.info("Actual border color of primary mobile number textfield after leaving field empty is: "
				+ actualBorderColorOfPrimaryMobileNumberTextfieldAfterLeavingFieldEmpty);
		assertEquals(actualBorderColorOfPrimaryMobileNumberTextfieldAfterLeavingFieldEmpty, "rgb(255, 0, 0)");
	}

	@Test(priority = 5, dataProvider = "TestDataForUpdateProfile", dataProviderClass = DataProviders.class)
	public void verifyProfileUpdateFromEmployeeSide(String primaryMobileNumber, String secondaryMobileNumber,
			String personalEmail) throws InterruptedException {
//		webElementActions.refreshThePage();
//
//		dashboard.clickOnEmployeeNameAtDashboard();
//		Thread.sleep(1000);
//
//		profile.clickOnEditInfoButton();

		profile.scrollUntilSubmitButtonFound();

		profile.clearPrimaryMobileNumberTextfield();

		profile.enterPrimaryMobileNumber(primaryMobileNumber);

		profile.clearSecondaryMobileNumberTextfield();

		profile.enterSecondaryMobileNumber(secondaryMobileNumber);

		profile.clearPersonalEmailTextfield();

		profile.enterPersonalEmail(personalEmail);

		profile.clickOnSubmitButton();

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(personalEmail);

		if (primaryMobileNumber.length() < 10) {
			commonMethods.verifyToastMessage("after entering less than 10 digit primary mobile number",
					"Enter Valid Primary Mobile Number");

			String actualBorderColorOfPrimaryMobileNumberTextfieldAfterLeavingFieldEmpty = profile
					.checkBorderColorOfPrimaryMobileNumberTextfield();
			log.info(
					"Actual border color of primary mobile number textfield entering less than 10 digit mobile number is: "
							+ actualBorderColorOfPrimaryMobileNumberTextfieldAfterLeavingFieldEmpty);
			assertEquals(actualBorderColorOfPrimaryMobileNumberTextfieldAfterLeavingFieldEmpty, "rgb(255, 0, 0)");
		} else if (secondaryMobileNumber.length() < 10) {
			commonMethods.verifyToastMessage("after entering less than 10 digit secondary mobile number",
					"Enter Valid Secondary Mobile Number");

			String actualBorderColorOfSecondaryMobileNumberTextfieldAfterLeavingFieldEmpty = profile
					.checkBorderColorOfSecondaryMobileNumberTextfield();
			log.info(
					"Actual border color of secondary mobile number textfield entering less than 10 digit mobile number is: "
							+ actualBorderColorOfSecondaryMobileNumberTextfieldAfterLeavingFieldEmpty);
			assertEquals(actualBorderColorOfSecondaryMobileNumberTextfieldAfterLeavingFieldEmpty, "rgb(255, 0, 0)");
		} else if (matcher.matches() == false) {
			commonMethods.verifyToastMessage("after entering invalid personal email for profile update",
					"Enter Valid personal email");
		} else {
			commonMethods.verifyToastMessage("after entering valid data for profile update",
					"Employee details updated successfully");
		}
	}

	@Test(priority = 6)
	public void verifyProfileUpdateByEnteringOptionalFields() throws InterruptedException {
		webElementActions.refreshThePage();

		dashboard.clickOnEmployeeNameAtDashboard();
		Thread.sleep(1000);

		profile.clickOnEditInfoButton();

		profile.scrollUntilSubmitButtonFound();

		profile.clearBloodGroupTextfield();

		profile.enterBloodGroup("B+");
		Thread.sleep(1000);

		profile.enterDateOfBirth("22Aug1999");
		Thread.sleep(1000);

		profile.clearAddressTextfield();

		profile.enterAddress("Bibwewadi, Pune");
		Thread.sleep(1000);

		profile.clickOnSubmitButton();

		commonMethods.verifyToastMessage("after entering valid data for profile update",
				"Employee details updated successfully");

	}
}
