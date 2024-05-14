package in.biencaps.erp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import in.biencaps.erp.utilities.WebElementActions;

public class ProfilePage {
	protected WebElementActions webElementActions = new WebElementActions();

	public By editInfoButton = By.xpath("//div[@class='emp-edit']");
	public By gmailKeyTextfield = By.xpath("(//input[@type='text'])[1]");
	public By passwordTextfield = By.xpath("//input[@type='password']");
	public By sendButtonForGmailKeySubmit = By.xpath("//div[@class='key-submit-button']");
	public By primaryMobileNumber = By.xpath("(//input[@type='number'])[1]");
	public By secondaryMobileNumber = By.xpath("(//input[@type='number'])[2]");
	public By personalEmailTextfield = By.xpath("//input[@id='persnolEmail']");
	public By bloodGroupTextfield = By.xpath("(//input[@placeholder='-'])[2]");
	public By dateOfBirthCalendar = By.xpath("(//input[@type='date'])[2]");
	public By addressTextfield = By.xpath("(//input[@type='text'])[10]");

	public By submitButton = By.xpath("//div[@class='AddEmpButtonEmpty']");

	public void clickOnEditInfoButton() {
		webElementActions.clickOnMethod(editInfoButton);
	}

	public void clearGmailKeyField() {
		webElementActions.clearMethod(gmailKeyTextfield);
	}

	public void enterGmailKey(String gmailKey) {
		webElementActions.sendKeysMethod(gmailKeyTextfield, gmailKey);
	}

	public void clearPasswordField() {
		webElementActions.clearMethod(passwordTextfield);
	}

	public void enterPassword(String password) {
		webElementActions.sendKeysMethod(passwordTextfield, password);
	}

	public void clickOnSendButtonForGmailKeySubmit() {
		webElementActions.clickOnMethod(sendButtonForGmailKeySubmit);
	}

	public void clearPrimaryMobileNumberTextfield() {
		webElementActions.clearMethod(primaryMobileNumber);
	}

	public void enterPrimaryMobileNumber(String mobNo) {
		webElementActions.sendKeysMethod(primaryMobileNumber, mobNo);
	}

	public String checkBorderColorOfPrimaryMobileNumberTextfield() {
		return webElementActions.getCssValue(primaryMobileNumber, "border-color");
	}

	public void clearSecondaryMobileNumberTextfield() {
		webElementActions.clearMethod(secondaryMobileNumber);
	}

	public void enterSecondaryMobileNumber(String mobNo) {
		webElementActions.sendKeysMethod(secondaryMobileNumber, mobNo);
	}

	public String checkBorderColorOfSecondaryMobileNumberTextfield() {
		return webElementActions.getCssValue(secondaryMobileNumber, "border-color");
	}

	public void scrollUntilSubmitButtonFound() {
		webElementActions.scrollUntilElementFound(submitButton);
	}

	public void clearPersonalEmailTextfield() {
		webElementActions.clearMethod(personalEmailTextfield);
	}

	public void enterPersonalEmail(String email) {
		webElementActions.sendKeysMethod(personalEmailTextfield, email);
	}

	public String checkBorderColorOfPersonalEmailTextfield() {
		return webElementActions.getCssValue(personalEmailTextfield, "border-color");
	}

	public void clearBloodGroupTextfield() {
		webElementActions.clearMethod(bloodGroupTextfield);
	}

	public void enterBloodGroup(String bloodGroup) {
		webElementActions.sendKeysMethod(bloodGroupTextfield, bloodGroup);
	}

	public void enterDateOfBirth(String dateOfBirth) throws InterruptedException {
		webElementActions.sendKeysMethod(dateOfBirthCalendar, dateOfBirth.substring(0, 5));
		webElementActions.sendKeysMethod(dateOfBirthCalendar, Keys.TAB);
		Thread.sleep(1000);
		webElementActions.sendKeysMethod(dateOfBirthCalendar, dateOfBirth.substring(5));
	}

	public void clearAddressTextfield() {
		webElementActions.clearMethod(addressTextfield);
	}

	public void enterAddress(String address) {
		webElementActions.sendKeysMethod(addressTextfield, address);
	}

	public void clickOnSubmitButton() {
		webElementActions.clickOnMethod(submitButton);
	}
}
