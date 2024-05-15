package in.biencaps.erp.testCases;

import static org.testng.Assert.*;

import java.util.regex.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import in.biencaps.erp.pages.*;
import in.biencaps.erp.utilities.*;

public class PasswordChangeFunctionality {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(PasswordChangeFunctionality.class);

	protected DashboardPage dashboard;
	protected SettingsPage settings;
	protected CommonTestMethods commonMethods;
	protected WebElementActions webElementActions;

	@BeforeClass
	public void go_To_Change_Login_Password_Div() throws InterruptedException {
		dashboard = new DashboardPage();
		settings = new SettingsPage();
		commonMethods = new CommonTestMethods();
		webElementActions = new WebElementActions();

		dashboard.clickOnSettingsSectionLink();
	}

	@Test(priority = 1, dataProvider = "TestDataForChangeLoginPassword", dataProviderClass = DataProviders.class)
	public void verify_Change_Login_Password_With_Valid_And_Invalid_Password(String currentPassword, String newPassword,
			String confirmNewPassword) throws InterruptedException {
		webElementActions.refreshThePage();

		settings.clickOnChangeYourLoginPasswordBox();
		Thread.sleep(500);

		settings.enterCurrentPassword(currentPassword);
		Thread.sleep(500);

		String actualEnteredCurrentPassword = settings.checkCurrentPassword();
		log.info("Actual entered current password is: " + actualEnteredCurrentPassword);

		settings.clickOnEyeIconNearCurrentPasswordTextfield();
		Thread.sleep(1000);

		settings.enterNewPassword(newPassword);

		String actualEnteredNewPassword = settings.checkNewPassword();
		log.info("Actual entered new password is: " + actualEnteredNewPassword);

		settings.clickOnEyeIconNearNewPasswordTextfield();
		Thread.sleep(1000);

		settings.enterConfirmNewPassword(confirmNewPassword);

		String actualEnteredConfirmNewPassword = settings.checkNewConfirmPassword();
		log.info("Actual entered confirm new password is: " + actualEnteredConfirmNewPassword);

		settings.clickOnEyeIconNearConfirmNewPasswordTextfield();
		Thread.sleep(1000);

		settings.clickOnSubmitButtonForChangeLoginPassword();
//		Thread.sleep(1000);

		String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=\\S+$).{8,20}$";
		Pattern pattern = Pattern.compile(passwordRegex);
		Matcher matcher = pattern.matcher(newPassword);

		if (!currentPassword.equalsIgnoreCase(Constants.employeePassword)) {
			String actualErrorMessageAfterEnteringIncorrectCurrentPassword = settings
					.checkErrorMessageAfterEnteringIncorrectCurrentPassword();
			assertEquals(actualErrorMessageAfterEnteringIncorrectCurrentPassword, "Enter Correct Current Password");

			commonMethods.verify_Toast_Message("after entering invalid current password",
					"Enter Correct Current Password");
		} else if (matcher.matches() == false) {
			commonMethods.verify_Toast_Message(
					"after entering invalid password which is not matched with password info",
					"Enter Valid New Password, follow the password rules");
		} else if (matcher.matches() == true && !newPassword.equalsIgnoreCase(confirmNewPassword)) {
			String actualErrorMessageWhenNewPasswordAndConfirmNewPasswordDoesNotMatch = settings
					.checkErrorMessageAfterEnteringIncorrectConfirmNewPassword();
			assertEquals(actualErrorMessageWhenNewPasswordAndConfirmNewPasswordDoesNotMatch,
					"Confirm Password not match with New Password");
		} else {
			commonMethods.verify_Toast_Message("after entering all valid passwords", "Password Updated Successfully");
		}
	}
}
