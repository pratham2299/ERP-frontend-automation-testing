package in.biencaps.erp.testCases;

import static org.testng.Assert.*;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import in.biencaps.erp.pages.*;
import in.biencaps.erp.utilities.*;

/* This class is extended BaseTest class.
 *  BaseTest has  so you can inherite  from BaseTest to this class
 */
public class LoginAndForgotPasswordTests extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(LoginAndForgotPasswordTests.class);
	public static String actualEmployeeName;

	protected LoginPage login;
	protected WebElementActions webElementActions;
	protected CommonTestMethods commonMethods;

	// Checks forgot password functionality without giving user Id in employee Id
	// field
	@Test(priority = 1)
	public void verify_Forgot_Password_Without_Giving_UserId() throws InterruptedException {
		login = new LoginPage();
		webElementActions = new WebElementActions();
		commonMethods = new CommonTestMethods();

		login.clickOnForgotPasswordLink();

		String actualErrorMessageForForgotPassword = login.checkErrorToastMessageForForgotPassword();
		log.error("\nActual error toast message for forgot password functionality when not entering employee Id is: "
				+ actualErrorMessageForForgotPassword + "\n");
		assertEquals(actualErrorMessageForForgotPassword, "Enter valid Employee Id");

		login.clickOnCloseIconOfToastMessage();
	}

	// Checks forgot password functionality by giving user Id in employee Id
	// field
	@Test(priority = 2, enabled = false)
	public void verify_Forgot_Password_By_Giving_Valid_UserId() throws InterruptedException {
		String actualEnteredUserId = login.enterAndCheckUserId(Constants.employeeUserId);
		log.info("Actual entered user Id is: " + actualEnteredUserId + "\n");
		assertEquals(actualEnteredUserId, Constants.employeeUserId);

		login.clickOnForgotPasswordLink();

		login.clickOnSendPasswordButton();

		String actualSuccessMessageAfterForgotPasswordSent = login.checkForgotPasswordSentSuccessfullyMessage();
		log.info("Actual success message after forgot password sent to employee email is: "
				+ actualSuccessMessageAfterForgotPasswordSent + "\n");

		webElementActions.refreshThePage();
	}

	// Login employee
	@Test(priority = 3, dataProvider = "TestDataForLoginEmployee", dataProviderClass = DataProviders.class)
	public void verify_Login_Employee_With_Valid_And_Invalid_Credentails(String userId, String password)
			throws InterruptedException, FileNotFoundException {
		login.clickOnUserIdTextfield();

		String actualEnteredUserId = login.enterAndCheckUserId(userId);
		log.info("Actual entered user Id is: " + actualEnteredUserId);

		login.clickOnPasswordTextfield();

		String actualEnteredPassword = login.enterAndCheckPassword(password);
		log.info("Actual entered password is: " + actualEnteredPassword);

		login.clickOnEyeIconNearPasswordTextfield();
		Thread.sleep(500);

		login.clickOnEyeIconNearPasswordTextfield();

		login.clickOnLoginButton();

		if (!userId.equalsIgnoreCase(Constants.employeeUserId)) {
			String actualUserIdTextfieldErrorMessage = login.checkUserIdTextfieldErrorMessage();
			log.info("Actual userId textfield error message is: " + actualUserIdTextfieldErrorMessage + "\n");
			assertEquals(actualUserIdTextfieldErrorMessage, "Enter Valid User ID");
		} else if (!password.equalsIgnoreCase(Constants.employeePassword)) {
			String actualPasswordTextfieldErrorMessage = login.checkPasswordTextfieldErrorMessage();
			log.info("Actual password textfield error message is: " + actualPasswordTextfieldErrorMessage);
			assertEquals(actualPasswordTextfieldErrorMessage, "Enter Valid Password");
		} else {
			String actualToastMessageAfterSuccessfulLoggedIn = login.checkSuccessfullLoggedInToastMessage(userId);
			log.info("Actual toast message after successful logged In is: " + actualToastMessageAfterSuccessfulLoggedIn
					+ "\n");
			assertEquals(actualToastMessageAfterSuccessfulLoggedIn, "Login Successfully");

			login.clickOnCloseIconOfToastMessage();
		}

		webElementActions.refreshThePage();
	}

	// This variable is printed actual logged in employee name
	// And also validate employee name is correct or not
	// By checking in hashmap value created in different class
	@Test(priority = 4)
	public void check_Employee_Name_After_Logged_In() throws Exception {
		actualEmployeeName = commonMethods.verify_Employee_Name_After_Logged_In(Constants.employeeUserId);
	}
}
