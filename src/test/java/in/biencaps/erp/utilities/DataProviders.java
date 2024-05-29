package in.biencaps.erp.utilities;

import org.testng.annotations.DataProvider;
import java.util.*;
import com.github.javafaker.Faker;

public class DataProviders {
	Faker faker = new Faker();
	Random random = new Random();

	@DataProvider(name = "TestDataForLoginEmployee")
	public Object[][] testDataForLoginEmployee() {
		String invalidUserId = faker.country().capital();
		String invalidPassword = faker.internet().password();
		String validUserId = Constants.employeeUserId;
		String validPassword = Constants.employeePassword;

		return new Object[][] { { invalidUserId, validPassword }, { validUserId, invalidPassword },
				{ invalidUserId, invalidPassword }, { validUserId, validPassword } };
	}

	@DataProvider(name = "TestDataForSubmitGmailKey")
	public Object[][] testDataForSubmitGmailKey() {
		String invalidGmailKey1 = DataGenerator.generateRandomString(1, 15);
		String invalidGmailKey2 = DataGenerator.generateRandomString(17, 25);
		String invalidPassword = faker.internet().password();
		String validGmailKey = Constants.gmailKey;
		String validPassword = Constants.employeePassword;

		return new Object[][] { { invalidGmailKey1, validPassword }, { invalidGmailKey2, validPassword },
				{ validGmailKey, invalidPassword }, { invalidGmailKey1, invalidPassword },
				{ invalidGmailKey2, invalidPassword }, { validGmailKey, validPassword } };
	}

	@DataProvider(name = "TestDataForUpdateProfile")
	public Object[][] testDataForUpdateProfile() {
		String validPrimaryMobileNumber = "9834530434";
		String invalidPrimaryMobileNumber = DataGenerator.generateRandomInvalidDigitMobileNumber(1, 9);
		String validSecondaryMobileNumber = "9850708611";
		String invalidSecondaryMobileNumber = DataGenerator.generateRandomInvalidDigitMobileNumber(1, 9);
		List<String> invalidPersonalEmails = Arrays.asList("prathmeshdhasade", "prathmeshdhasade99",
				"prathmeshdhasade99@@gmail.com", "prathmeshdhasade@gmail");
		int randomIndex = random.nextInt(invalidPersonalEmails.size());
		String randomInvalidPersonalEmail = invalidPersonalEmails.get(randomIndex);
		String validPersonalEmail = "prathmeshdhasade99@gmail.com";

		return new Object[][] { { invalidPrimaryMobileNumber, validSecondaryMobileNumber, validPersonalEmail },
				{ validPrimaryMobileNumber, invalidSecondaryMobileNumber, validPersonalEmail },
				{ validPrimaryMobileNumber, validSecondaryMobileNumber, randomInvalidPersonalEmail },
				{ validPrimaryMobileNumber, validSecondaryMobileNumber, validPersonalEmail } };
	}

	@DataProvider(name = "TestDataForChangeLoginPassword")
	public Object[][] testDataForChangeLoginPassword() {
		String invalidCurrentPassword = faker.internet().password();
		String validCurrentPassword = Constants.employeePassword;
		String invalidNewPassword = DataGenerator.generateInvalidPassword();
		String validNewPassword = Constants.employeePassword;
		String invalidConfirmNewPassword = faker.internet().password();
		String validConfirmNewPassword = validNewPassword;

		return new Object[][] { { invalidCurrentPassword, validNewPassword, validConfirmNewPassword },
				{ validCurrentPassword, invalidNewPassword, validConfirmNewPassword },
				{ validCurrentPassword, validNewPassword, invalidConfirmNewPassword },
				{ validCurrentPassword, validNewPassword, validConfirmNewPassword } };
	}
}