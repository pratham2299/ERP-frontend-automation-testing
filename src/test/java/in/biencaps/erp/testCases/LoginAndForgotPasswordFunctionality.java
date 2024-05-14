package in.biencaps.erp.testCases;

import static org.testng.Assert.*;
import java.util.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import in.biencaps.erp.pages.*;
import in.biencaps.erp.utilities.*;

/* This class is extended BaseTest class.
 *  BaseTest has  so you can inherite  from BaseTest to this class
 */
public class LoginAndForgotPasswordFunctionality extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static Logger log = LogManager.getLogger(LoginAndForgotPasswordFunctionality.class);
	public static String actualEmployeeName;

	public static HashMap<String, String> employeeUserIdsAndNames;
	public static HashMap<String, String> employeeUserIdsAndRoles;

	protected LoginPage login;
	protected WebElementActions webElementActions;
	protected MyTasksPage myTasks;
	protected DashboardPage dashboard;
	protected CommonTestMethods commonMethods;
	protected EmployeePage employee;
	protected LogoutFunctionality logOutFun;

	// Login employee and get all employee user id, full name, role
	@Test(priority = 1)
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
		Thread.sleep(1500);

		dashboard.clickOnEmployeeSectionLink();

		employee.scrollBottomOfEmployeeContainer();

		try {
			employee.clickOnLoadMoreButton();

			employeeUserIdsAndNames = DataGenerator.employeeUserIdsAndNames();
			for (Map.Entry<String, String> entry : employeeUserIdsAndNames.entrySet()) {
				log.info(entry.getKey() + "=> " + entry.getValue());
			}

			employeeUserIdsAndRoles = DataGenerator.employeeUserIdsAndRoles();
			for (Map.Entry<String, String> entry : employeeUserIdsAndRoles.entrySet()) {
				log.info(entry.getKey() + "=> " + entry.getValue());
			}

			logOutFun.verifyLogOutEmployee();
		} catch (Exception e) {
			employeeUserIdsAndNames = DataGenerator.employeeUserIdsAndNames();
			for (Map.Entry<String, String> entry : employeeUserIdsAndNames.entrySet()) {
				log.info(entry.getKey() + "=> " + entry.getValue());
			}

			employeeUserIdsAndRoles = DataGenerator.employeeUserIdsAndRoles();
			for (Map.Entry<String, String> entry : employeeUserIdsAndRoles.entrySet()) {
				log.info(entry.getKey() + "=> " + entry.getValue());
			}

			logOutFun.verifyLogOutEmployee();
		}
	}

	// Checks forgot password functionality without giving user Id in employee Id
	// field
	@Test(priority = 2)
	public void verifyForgotPasswordWithoutGivingUserId() throws InterruptedException {
		login = new LoginPage();
		webElementActions = new WebElementActions();

		login.clickOnForgotPasswordLink();

		String actualErrorMessageForForgotPassword = login.checkErrorToastMessageForForgotPassword();
		log.error("\nActual error toast message for forgot password functionality when not entering employee Id is: "
				+ actualErrorMessageForForgotPassword + "\n");
		assertEquals(actualErrorMessageForForgotPassword, "Enter valid Employee Id");

		login.clickOnCloseIconOfToastMessage();
	}

	// Checks forgot password functionality by giving user Id in employee Id
	// field
	@Test(priority = 3, enabled = false)
	public void verifyForgotPasswordByGivingValidUserId() throws InterruptedException {
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
	@Test(priority = 4, dataProvider = "TestDataForLoginEmployee", dataProviderClass = DataProviders.class)
	public void verifyLoginEmployee(String userId, String password) throws InterruptedException {
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
	@Test(priority = 8)
	public void checkEmployeeNameAfterLoggedIn() {
		dashboard = new DashboardPage();

		actualEmployeeName = dashboard.checkEmployeeNameAtDashboard();
		log.info("Actual employee name at dashboard page is: " + actualEmployeeName + "\n");

		String correspondingEmployeeNameFromHashmap = employeeUserIdsAndNames
				.get(Constants.employeeUserId);
		assertEquals(actualEmployeeName, correspondingEmployeeNameFromHashmap);
	}
}
