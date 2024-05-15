package in.biencaps.erp.utilities;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.concurrent.*;

import in.biencaps.erp.pages.*;

public class DataGenerator {
	static WebElementActions webElementActions = new WebElementActions();
	static EmployeePage employee = new EmployeePage();

	static Random random = new Random();

	// This method stores employee user Ids and names
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

	// This method stores employee user Ids and roles
	public static HashMap<String, String> employeeUserIdsAndRoles() {
		List<String> keysList = webElementActions.getValuesFromListOfWebElements(employee.userIdsOfEmployee);
		List<String> valuesList = webElementActions.getValuesFromListOfWebElements(employee.employeeRoles);
		HashMap<String, String> credentials = new HashMap<String, String>();
		for (int i = 0; i < keysList.size() && i < valuesList.size(); i++) {
			String key = keysList.get(i);
			String value = valuesList.get(i);

			credentials.put(key, value);
		}

		return credentials;
	}
	
	// This method stores employee user Ids and departments
	public static HashMap<String, String> employeeUserIdsAndDepartments() {
		List<String> keysList = webElementActions.getValuesFromListOfWebElements(employee.userIdsOfEmployee);
		List<String> valuesList = webElementActions.getValuesFromListOfWebElements(employee.employeeDepartments);
		HashMap<String, String> credentials = new HashMap<String, String>();
		for (int i = 0; i < keysList.size() && i < valuesList.size(); i++) {
			String key = keysList.get(i);
			String value = valuesList.get(i);

			credentials.put(key, value);
		}

		return credentials;
	}

	// This method stores employee user Id and names
	// So that we can use it while log in on production
	public static HashMap<String, String> employeeUserIdsAndPasswordsOnTestingEnvironment() {
		HashMap<String, String> employees = new HashMap<String, String>();
		employees.put("INC012", "Pass@123");
		employees.put("INC004", "Pass@123");
		employees.put("INC017", "Pass@123");

		return employees;
	}

	// This method stores employee user Id and names
	public static HashMap<String, String> employeeUserIdsAndPasswordsOnStagingEnvironment() {
		HashMap<String, String> employees = new HashMap<String, String>();
		employees.put("INC012", "Pratham@2299");
		employees.put("INC018", "TeamLead@123");
		employees.put("INC017", "Admin@123");

		return employees;
	}

	// This method stores employee user Id and names
	public static HashMap<String, String> employeeUserIdsAndNamesOnTestServer() {
		HashMap<String, String> employees = new HashMap<String, String>();
		employees.put("INC012", "Prathamesh Dhasade");
		employees.put("INC004", "Sachin Patil");
		employees.put("INC017", "Prathamesh T Dhasade");

		return employees;
	}

	// This method stores employee user Id and roles
	// So that we can use it while log in on production
	public static HashMap<String, String> employeeUserIdsAndRolesOnTestServer() {
		HashMap<String, String> employeeRoles = new HashMap<String, String>();
		employeeRoles.put("INC012", "Lead");
		employeeRoles.put("INC004", "Admin");
		employeeRoles.put("INC017", "Team Lead");

		return employeeRoles;
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

	public static String generateRandomString(int stringMinLength, int stringMaxLength) {
		int minLength = stringMinLength;
		int maxLength = stringMaxLength; // You can adjust this as per your requirement
		int length = minLength + new Random().nextInt(maxLength - minLength);
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int index = new Random().nextInt(chars.length());
			sb.append(chars.charAt(index));
		}
		return sb.toString();
	}

	public static String generateRandomLessThan10DigitMobileNumber(int stringMinLength, int stringMaxLength) {
		int minLength = stringMinLength;
		int maxLength = stringMaxLength; // You can adjust this as per your requirement
		int length = minLength + new Random().nextInt(maxLength - minLength);
		String chars = "0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int index = new Random().nextInt(chars.length());
			sb.append(chars.charAt(index));
		}
		return sb.toString();
	}

	public static String generateInvalidPassword() {
		StringBuilder invalidPassword = new StringBuilder();
		int length = random.nextInt(20) + 1; // Generate random length between 1 and 20 characters

		// Generate random characters
		for (int j = 0; j < length; j++) {
			int type = random.nextInt(4); // 0: digit, 1: lowercase, 2: uppercase, 3: special symbol
			char ch;
			switch (type) {
			case 0:
				ch = (char) ('0' + random.nextInt(10)); // Digit
				break;
			case 1:
				ch = (char) ('a' + random.nextInt(26)); // Lowercase letter
				break;
			case 2:
				ch = (char) ('A' + random.nextInt(26)); // Uppercase letter
				break;
			case 3:
				ch = "!@#$&*".charAt(random.nextInt(6)); // Special symbol
				break;
			default:
				ch = ' '; // Whitespace
			}
			invalidPassword.append(ch);
		}

		return invalidPassword.toString();
	}
}
