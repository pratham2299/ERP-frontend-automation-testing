package in.biencaps.erp.testCases;

import static org.testng.Assert.*;

import java.util.*;

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
	public void verifyNotificationMessage(String employeeName, String notificationMessage) throws InterruptedException {
//		dashboard = new DashboardPage();

		String notificationCountOfLoggedInEmployee = dashboard.checkNotificationCount();
		int notificationCount = Integer.parseInt(notificationCountOfLoggedInEmployee);
		log.info("Notification count before marked as read is: " + notificationCount);

		dashboard.clickOnNotificationIcon();
		Thread.sleep(1000);

		List<String> actualNotificationMessages = dashboard.checkNotificationMessages();
		List<String> actualNotificationSenderEmployeeNames = dashboard.checkNotificationSenderEmployeeNames();

		if (actualNotificationMessages.get(0).equalsIgnoreCase(notificationMessage)) {
			assertEquals(actualNotificationMessages.get(0), notificationMessage);
			assertEquals(actualNotificationSenderEmployeeNames.get(0), employeeName);
			Thread.sleep(500);
			webElementActions.clickOnMethod(dashboard.notificationMessages, 0);
			Thread.sleep(1000);
			notificationCount = notificationCount - 1;
		} else {
			assertEquals(actualNotificationMessages.get(1), notificationMessage);
			assertEquals(actualNotificationSenderEmployeeNames.get(1), employeeName);
			Thread.sleep(500);
			webElementActions.clickOnMethod(dashboard.notificationMessages, 1);
			Thread.sleep(1000);
			notificationCount = notificationCount - 1;
		}

		webElementActions.refreshThePage();
		Thread.sleep(3000);
		log.info("Notification count after marked as read is: " + notificationCount);

		notificationCountOfLoggedInEmployee = dashboard.checkNotificationCount();
		assertEquals(notificationCountOfLoggedInEmployee, String.valueOf(notificationCount));
	}
}
