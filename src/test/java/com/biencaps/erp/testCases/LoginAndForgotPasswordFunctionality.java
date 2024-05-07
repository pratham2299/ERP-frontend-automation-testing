package com.biencaps.erp.testCases;

import static org.testng.Assert.*;
import java.util.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;
import com.biencaps.erp.pageObjectModels.*;
import com.biencaps.erp.utilities.*;

/* This class is extended BaseTest class.
 *  BaseTest has  so you can inherite  from BaseTest to this class
 */
public class LoginAndForgotPasswordFunctionality extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(LoginAndForgotPasswordFunctionality.class);
	public static String validUserId;
	public static String actualEmployeeName;

//	protected LoginPage login;
	protected LoginPage login = new LoginPage();
	protected WebElementActions webElementActions;
	protected MyTasksPage myTasks;
	protected DashboardPage dashboard;
	protected CommonTestMethods commonMethods;
	protected EmployeePage employee;
	protected LogoutFunctionality logOutFun;

	@Test(priority = 1, enabled = false)
	public void goToLoginPage() {
		login = new LoginPage();
		webElementActions = new WebElementActions();
		myTasks = new MyTasksPage(driver);
		dashboard = new DashboardPage();
		commonMethods = new CommonTestMethods();
		employee = new EmployeePage();
		logOutFun = new LogoutFunctionality();

		login.clickOnLetsConnectButton();

		assertEquals(webElementActions.currentWindowUrl(), Constants.websiteUrl + "login");
	}

	// Login employee and get all employee user id, full name, role
	@Test(priority = 1, enabled = false)
	public void getAllEmployeeDetails() throws InterruptedException {
		login = new LoginPage();
		webElementActions = new WebElementActions();
		myTasks = new MyTasksPage(driver);
		dashboard = new DashboardPage();
		commonMethods = new CommonTestMethods();
		employee = new EmployeePage();
		logOutFun = new LogoutFunctionality();

		commonMethods.verifyLoginEmployeeByGivingValidUserIdAndValidPassword(Constants.adminLevelTesterEmployeeUserId,
				Constants.adminLevelTesterEmployeePassword);

		dashboard.clickOnEmployeeSectionLink();

		employee.scrollBottomOfEmployeeContainer();

		if (webElementActions.isDisplayedMethod(employee.loadMoreButton)) {
			employee.clickOnLoadMoreButton();

			DataGenerator.employeeUserIdsAndNames();

			logOutFun.verifyLogOutEmployee();
		} else {
			List<String> employeeIds = webElementActions.getValuesFromListOfWebElements(employee.userIdsOfEmployee);
			log.info("All employee ids are: " + employeeIds);

			List<String> employeeNames = webElementActions.getValuesFromListOfWebElements(employee.employeeNames);
			log.info("All employee names are: " + employeeNames);

			DataGenerator.employeeUserIdsAndNames();

			logOutFun.verifyLogOutEmployee();
		}
	}

	// Login employee with valid user Id and invalid password and checks error
	// messages
	@Test(priority = 2)
	public void verifyLoginEmployeeByGivingValidUserIdAndInvalidPassword() throws InterruptedException {
		login = new LoginPage();
		webElementActions = new WebElementActions();
//		myTasks = new MyTasksPage();
//		dashboard = new DashboardPage();
//		commonMethods = new CommonTestMethods();
//		employee = new EmployeePage();
//		logOutFun = new LogoutFunctionality();

		login.clickOnUserIdTextfield();

		String actualEnteredUserId = login.enterAndCheckUserId(Constants.employeeUserId);
		log.info("Actual entered user Id is: " + actualEnteredUserId);

		login.clickOnPasswordTextfield();

		String invalidPassword = faker.internet().password();

		String actualEnteredPassword = login.enterAndCheckPassword(invalidPassword);
		log.info("Actual entered password is: " + actualEnteredPassword);

		login.clickOnEyeIconNearPasswordTextfield();
		Thread.sleep(1000);

		login.clickOnEyeIconNearPasswordTextfield();

		login.clickOnLoginButton();

		String actualPasswordTextfieldErrorMessage = login.checkPasswordTextfieldErrorMessage();
		log.info("Actual password textfield error message is: " + actualPasswordTextfieldErrorMessage);
		assertEquals(actualPasswordTextfieldErrorMessage, "Enter Valid Password");

		webElementActions.refreshThePage();
	}

	// Login employee with invalid user Id and valid password and checks error
	// messages
	@Test(priority = 3)
	public void verifyLoginEmployeeByGivingInvalidUserIdAndValidPassword() throws InterruptedException {
		login.clickOnUserIdTextfield();

//		login.clearUserIdTextfield();

		String invalidUserId = faker.name().firstName();

		String actualEnteredUserId = login.enterAndCheckUserId(invalidUserId);
		log.info("Actual entered user Id is: " + actualEnteredUserId);

		login.clickOnPasswordTextfield();

//		login.clearPasswordTextfield();

		String actualEnteredPassword = login.enterAndCheckPassword(Constants.employeePassword);
		log.info("Actual entered password is: " + actualEnteredPassword);

		login.clickOnEyeIconNearPasswordTextfield();
		Thread.sleep(1000);

		login.clickOnEyeIconNearPasswordTextfield();

		login.clickOnLoginButton();

		String actualUserIdTextfieldErrorMessage = login.checkUserIdTextfieldErrorMessage();
		log.info("Actual userId textfield error message is: " + actualUserIdTextfieldErrorMessage + "\n");
		assertEquals(actualUserIdTextfieldErrorMessage, "Enter Valid User ID");

		webElementActions.refreshThePage();
	}

	// Login employee with invalid user Id and invalid password and checks error
	// messages
	@Test(priority = 4)
	public void verifyLoginEmployeeByGivingInvalidUserIdAndInvalidPassword() throws InterruptedException {
		login.clickOnUserIdTextfield();

//		login.clearUserIdTextfield();

		String invalidUserId = faker.name().firstName();

		String actualEnteredUserId = login.enterAndCheckUserId(invalidUserId);
		log.info("Actual entered user Id is: " + actualEnteredUserId);

		login.clickOnPasswordTextfield();

//		login.clearPasswordTextfield();

		String invalidPassword = faker.internet().password();

		String actualEnteredPassword = login.enterAndCheckPassword(invalidPassword);
		log.info("Actual entered password is: " + actualEnteredPassword);

		login.clickOnEyeIconNearPasswordTextfield();
		Thread.sleep(1000);

		login.clickOnEyeIconNearPasswordTextfield();

		login.clickOnLoginButton();

		String actualUserIdTextfieldErrorMessage = login.checkUserIdTextfieldErrorMessage();
		log.info("Actual userId textfield error message is: " + actualUserIdTextfieldErrorMessage + "\n");
		assertEquals(actualUserIdTextfieldErrorMessage, "Enter Valid User ID");

		webElementActions.refreshThePage();
	}

	// Checks forgot password functionality without giving user Id in employee Id
	// field
	@Test(priority = 5)
	public void verifyForgotPasswordWithoutGivingUserId() throws InterruptedException {
		login.clickOnForgotPasswordLink();

		String actualErrorMessageForForgotPassword = login.checkErrorToastMessageForForgotPassword();
		log.error("Actual error toast message for forgot password functionality when not entering employee Id is: "
				+ actualErrorMessageForForgotPassword + "\n");
		assertEquals(actualErrorMessageForForgotPassword, "Enter valid Employee Id");
		Thread.sleep(500);

		login.clickOnCloseIconOfToastMessage();
	}

	// Checks forgot password functionality by giving user Id in employee Id
	// field
	@Test(priority = 6)
	public void verifyForgotPasswordByGivingUserId() throws InterruptedException {
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

	// Login employee with valid user Id and valid password and checks error
	// messages
	@Test(priority = 7)
	public void verifyLoginEmployeeByGivingValidUserIdAndValidPassword() throws InterruptedException {
		login = new LoginPage();

		validUserId = Constants.employeeUserId;

		login.clickOnUserIdTextfield();

//		login.clearUserIdTextfield();

		String actualEnteredUserId = login.enterAndCheckUserId(validUserId);
		log.info("Actual entered user Id is: " + actualEnteredUserId);

		String validPassword = Constants.employeePassword;

		login.clickOnPasswordTextfield();

//		login.clearPasswordTextfield();

		String actualEnteredPassword = login.enterAndCheckPassword(validPassword);
		log.info("Actual entered password is: " + actualEnteredPassword);

		login.clickOnEyeIconNearPasswordTextfield();
		Thread.sleep(1000);

		login.clickOnEyeIconNearPasswordTextfield();

		login.clickOnLoginButton();

		String actualToastMessageAfterSuccessfulLoggedIn = login.checkSuccessfullLoggedInToastMessage(validUserId);
		log.info("Actual toast message after successful logged In is: " + actualToastMessageAfterSuccessfulLoggedIn
				+ "\n");
		assertEquals(actualToastMessageAfterSuccessfulLoggedIn, "Login Successfully");
		Thread.sleep(500);

		login.clickOnCloseIconOfToastMessage();
	}

	// This variable is printed actual logged in employee name
	// And also validate employee name is correct or not
	// By checking in hashmap value created in different class
	@Test(priority = 8)
	public void checkEmployeeNameAfterLoggedIn() {
		dashboard = new DashboardPage();
		actualEmployeeName = dashboard.checkEmployeeNameAtDashboard();
		log.info("Actual employee name at dashboard page is: " + actualEmployeeName + "\n");

		String correspondingEmployeeNameFromHashmap = DataGenerator.employeeUserIdsAndNamesOnProduction()
				.get(validUserId);
		assertEquals(actualEmployeeName, correspondingEmployeeNameFromHashmap);
	}
}
