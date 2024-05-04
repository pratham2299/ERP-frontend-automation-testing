package com.biencaps.erp.utilities;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.concurrent.*;

import com.biencaps.erp.pageObjectModels.*;
import com.biencaps.erp.testCases.*;

public class DataGenerator {
	static WebElementActions webElementActions = new WebElementActions();
	static EmployeePage employee = new EmployeePage(BaseTest.driver);

	// This method stores employee user Id and password
	// So that we can use it while log in on production
	public static HashMap<String, String> employeeUserIdsAndNames() {
		List<String> keysList = webElementActions.getValuesFromListOfWebElements(employee.userIdsOfEmployee);
		List<String> valuesList = webElementActions.getValuesFromListOfWebElements(employee.employeeNames);
		HashMap<String, String> credentials = new HashMap<String, String>();
		for (int i = 0; i < keysList.size() && i < valuesList.size(); i++) {
			String key = keysList.get(i);
			String value = valuesList.get(i);

			credentials.put(key, value);
		}

		return credentials;
	}

	// This method stores employee user Id and password
	// So that we can use it while log in on production
	public static HashMap<String, String> employeeUserIdsAndNamesOnProduction() {
		HashMap<String, String> credentials = new HashMap<String, String>();
		credentials.put("INC012", "Prathamesh Dhasade");
		credentials.put("INC027", "Test Team Lead");
		credentials.put("INC028", "Developer Level Tester");
		credentials.put("INC008", "Vishal Lohbande");

		return credentials;
	}

	// This method generate current date in provided format
	public static String generateCurrentDate(String format) {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		String formattedDate = currentDate.format(formatter);

		return formattedDate;
	}

	// This method generate random time for task time field
	public static String generateRandomTime() {
		LocalTime startTime = LocalTime.of(9, 0);
		LocalTime endTime = LocalTime.of(15, 0);

		long startSeconds = startTime.toSecondOfDay();
		long endSeconds = endTime.toSecondOfDay();

		// Generate a random time within the range in seconds
		long randomTimeSeconds = ThreadLocalRandom.current().nextLong(startSeconds, endSeconds);

		// Convert the random time back to LocalTime
		// Generate a random time within the specified range
		LocalTime randomTime = LocalTime.ofSecondOfDay(randomTimeSeconds);

		// Define a formatter to display the time in 12-hour format with AM/PM
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");

		String randomTaskStartTime = formatter.format(randomTime);
		return randomTaskStartTime;
	}
}
