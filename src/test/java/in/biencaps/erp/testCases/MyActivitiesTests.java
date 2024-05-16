package in.biencaps.erp.testCases;

import static org.testng.Assert.*;

import org.apache.logging.log4j.*;

import in.biencaps.erp.pages.*;
import in.biencaps.erp.utilities.*;

public class MyActivitiesTests extends BaseTest {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public Logger log = LogManager.getLogger(MyActivitiesTests.class);

	protected WebElementActions webElementActions;
	protected MyTasksPage myTasks;

	// This method is responsible for checking log message, log employee, log time.
	// Also it is validating means comparing values of employee, action, time.
	// If falied it print catch statement
	public void verify_Log_Message(String employeeName, String message, String taskTitle) throws InterruptedException {
		webElementActions = new WebElementActions();
		myTasks = new MyTasksPage(driver);

		myTasks.clickOnGetMyActivitiesButtonInDayView();
		Thread.sleep(4500);

		String actualEmployeeNameAtMyActivitiesInDayView = "";

		try {
			actualEmployeeNameAtMyActivitiesInDayView = myTasks.checkEmployeeNameAtMyActivitiesInDayView(employeeName);
			log.info("Actual employee name at my activities in day view is: "
					+ actualEmployeeNameAtMyActivitiesInDayView);
		} catch (Exception e) {
			log.error("Employee name at my activities is not found" + "\n");
			webElementActions.refreshThePage();
			Thread.sleep(4000);
		}

		if (actualEmployeeNameAtMyActivitiesInDayView.equals(null)
				|| actualEmployeeNameAtMyActivitiesInDayView.isBlank()
				|| actualEmployeeNameAtMyActivitiesInDayView.isEmpty()) {
			log.error("Employee name at my activities is null or empty");
		} else {
			assertEquals(actualEmployeeNameAtMyActivitiesInDayView, employeeName);
		}

		try {
			String actualLogMessageAtMyActivitiesInDayView = myTasks.checkActionAtMyActivitiesInDayView();
			log.info("Actual log message at my activities in day view is: " + actualLogMessageAtMyActivitiesInDayView);

			String[] seperateLogMessage = actualLogMessageAtMyActivitiesInDayView.split("\n");

			assertEquals(seperateLogMessage[0].trim(), message);
			assertEquals(seperateLogMessage[1].trim(), taskTitle);
		} catch (Exception e) {
			log.error("Log message at my activities is null" + "\n");
			webElementActions.refreshThePage();
			Thread.sleep(4000);
		}
		webElementActions.refreshThePage();
		Thread.sleep(4000);
	}

	// This method is responsible for checking log message, log employee, log time.
	// Also it is validating means comparing values of employee, action, time.
	// If falied it print catch statement
	public void verify_Log_Message_After_Update_Task_Details(String employeeName, String field, String oldValueOfField, String updatedValueOfField,
			String taskTitle) throws InterruptedException {
		webElementActions = new WebElementActions();
		myTasks = new MyTasksPage(driver);

		myTasks.clickOnGetMyActivitiesButtonInDayView();
		Thread.sleep(4500);

		String actualEmployeeNameAtMyActivitiesInDayView = "";

		try {
			actualEmployeeNameAtMyActivitiesInDayView = myTasks.checkEmployeeNameAtMyActivitiesInDayView(employeeName);
			log.info("Actual employee name at my activities in day view is: "
					+ actualEmployeeNameAtMyActivitiesInDayView);
		} catch (Exception e) {
			log.error("Employee name at my activities is not found" + "\n");
			webElementActions.refreshThePage();
			Thread.sleep(4000);
		}

		if (actualEmployeeNameAtMyActivitiesInDayView.equals(null)
				|| actualEmployeeNameAtMyActivitiesInDayView.isBlank()
				|| actualEmployeeNameAtMyActivitiesInDayView.isEmpty()) {
			log.error("Employee name at my activities is null or empty");
		} else {
			assertEquals(actualEmployeeNameAtMyActivitiesInDayView, employeeName);
		}

		try {
			String actualLogMessageAfterTaskSingleFieldUpdatedInDayView = myTasks.checkActionAtMyActivitiesInDayView();
			log.info("Actual log message after task " + field + " updated at my activities in day view is: "
					+ actualLogMessageAfterTaskSingleFieldUpdatedInDayView);

			String[] seperateLogMessageAfterTaskStatusUpdated = actualLogMessageAfterTaskSingleFieldUpdatedInDayView
					.split("\n");

			assertEquals(seperateLogMessageAfterTaskStatusUpdated[0].trim(),
					"has changed " + field + " from \"" + oldValueOfField + "\" to \"" + updatedValueOfField + "\" in");
			assertEquals(seperateLogMessageAfterTaskStatusUpdated[1].trim(), taskTitle);
		} catch (Exception e) {
			log.error("Log message at my activities is null" + "\n");
			webElementActions.refreshThePage();
			Thread.sleep(4000);
		}
		webElementActions.refreshThePage();
		Thread.sleep(4000);
	}
}
