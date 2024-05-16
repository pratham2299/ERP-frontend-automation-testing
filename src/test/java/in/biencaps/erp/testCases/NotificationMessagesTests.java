package in.biencaps.erp.testCases;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.logging.log4j.*;

import in.biencaps.erp.pages.*;
import in.biencaps.erp.utilities.*;

public class NotificationMessagesTests {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public Logger log = LogManager.getLogger(NotificationMessagesTests.class);

	protected WebElementActions webElementActions = new WebElementActions();
	protected DashboardPage dashboard = new DashboardPage();

	// Check notification message
	public void verifyNotificationMessageForSelfTaskSubmit(String message, String notificationMessage, String taskTitle)
			throws InterruptedException {
//		dashboard = new DashboardPage();

		dashboard.clickOnNotificationIcon();

		List<String> actualNotificationMessages = dashboard.checkNotificationMessages();

		String receivedNotificationMessage = "" + notificationMessage + " \"" + taskTitle + "\"";
		try {
			assertTrue(actualNotificationMessages.contains(receivedNotificationMessage));

			webElementActions.refreshThePage();
			Thread.sleep(3000);
		} catch (Exception e) {
			log.error("Notification message " + message + " is incorrect" + "\n");
			webElementActions.refreshThePage();
		}
	}

	// Check notification message
	public void verifyNotificationMessageAfterTaskAssign(String employeeName, String notificationMessage,
			String taskTitle) throws InterruptedException {
//		dashboard = new DashboardPage();

		dashboard.clickOnNotificationIcon();

		List<String> actualNotificationMessages = dashboard.checkNotificationMessages();

		String receivedNotificationMessage = "\"" + employeeName + "\" " + notificationMessage + " \"" + taskTitle
				+ "\"";
		try {
			assertTrue(actualNotificationMessages.contains(receivedNotificationMessage));

			webElementActions.refreshThePage();
			Thread.sleep(3000);
		} catch (Exception e) {
			log.error("Notification message after added task from level view to lower level employee is incorrect"
					+ "\n");
			webElementActions.refreshThePage();
		}
	}

	// Check notification message
	public void verifyNotificationMessageForFieldChange(String employeeName, String message, String notificationMessage,
			String oldTaskTitle, String newTaskTitle) throws InterruptedException {
//		dashboard = new DashboardPage();

		dashboard.clickOnNotificationIcon();

		List<String> actualNotificationMessages = dashboard.checkNotificationMessages();

		String receivedNotificationMessage = "\"" + employeeName + "\" " + notificationMessage + " \"" + oldTaskTitle
				+ "\"";
		try {
			assertTrue(actualNotificationMessages.contains(receivedNotificationMessage));

			webElementActions.refreshThePage();
			Thread.sleep(3000);
		} catch (Exception e) {
			log.error("Notification message " + message + " is incorrect" + "\n");
			webElementActions.refreshThePage();
		}
	}
}
