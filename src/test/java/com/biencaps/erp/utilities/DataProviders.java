package com.biencaps.erp.utilities;

import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;

public class DataProviders {
	Faker faker = new Faker();

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
}
