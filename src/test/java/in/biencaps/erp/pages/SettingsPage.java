package in.biencaps.erp.pages;

import org.openqa.selenium.*;

import in.biencaps.erp.utilities.*;

public class SettingsPage {
	protected WebElementActions webElementActions = new WebElementActions();

	public By informationBox = By.xpath("//div[contains(text(),'Information')]");
	public By changeLoginPasswordBox = By.xpath("//div[contains(text(),'Change your login Password?')]");
	public By currentPassword = By.xpath("(//div[@class='label-holder'])[1]//input");
	public By eyeIconNearCurrentPasswordTextfield = By.xpath("(//*[name()='svg'])[14]");
	public By errorMessageOfCurrentPasswordTextfield = By
			.xpath("//p[normalize-space()='Enter Correct Current Password']");
	public By newPassword = By.xpath("(//div[@class='label-holder'])[2]//input");
	public By eyeIconNearNewPasswordTextfield = By.xpath("(//*[name()='svg'])[15]");
	public By passwordInfo = By.xpath("//p[@class='pass-info']");
	public By confirmNewPassword = By.xpath("(//div[@class='label-holder'])[3]//input");
	public By eyeIconNearConfirmNewPasswordTextfield = By.xpath("(//*[name()='svg'])[16]");
	public By errorMessageForConfirmNewPassword = By
			.xpath("//p[normalize-space()='Confirm Password not match with New Password']");
	public By submitButtonAtChangePassword = By.xpath("//button[normalize-space()='SUBMIT']");

	public void clickOnInformationBox() {
		webElementActions.clickOnMethod(informationBox);
	}

	public void clickOnChangeYourLoginPasswordBox() {
		webElementActions.clickOnMethod(changeLoginPasswordBox);
	}

	public void enterCurrentPassword(String password) {
		webElementActions.clickOnMethod(currentPassword);
		webElementActions.sendKeysMethod(currentPassword, password);
	}

	public String checkCurrentPassword() {
		return webElementActions.getAtrributeMethod(currentPassword, "value");
	}

	public void clickOnEyeIconNearCurrentPasswordTextfield() {
		webElementActions.clickOnMethod(eyeIconNearCurrentPasswordTextfield);
	}

	public String checkErrorMessageAfterEnteringIncorrectCurrentPassword() {
		return webElementActions.getTextMethod(errorMessageOfCurrentPasswordTextfield);
	}

	public String checkNewPassword() {
		return webElementActions.getAtrributeMethod(newPassword, "value");
	}

	public void enterNewPassword(String password) {
		webElementActions.clickOnMethod(newPassword);
		webElementActions.sendKeysMethod(newPassword, password);
	}

	public void clickOnEyeIconNearNewPasswordTextfield() {
		webElementActions.clickOnMethod(eyeIconNearNewPasswordTextfield);
	}

	public String checkColorOfPasswordInfoAfterEnteringInvalidNewPassword() {
		return webElementActions.getCssValue(passwordInfo, "background-color");
	}

	public void enterConfirmNewPassword(String password) {
		webElementActions.clickOnMethod(confirmNewPassword);
		webElementActions.sendKeysMethod(confirmNewPassword, password);
	}

	public String checkNewConfirmPassword() {
		return webElementActions.getAtrributeMethod(confirmNewPassword, "value");
	}

	public void clickOnEyeIconNearConfirmNewPasswordTextfield() {
		webElementActions.clickOnMethod(eyeIconNearConfirmNewPasswordTextfield);
	}

	public String checkErrorMessageAfterEnteringIncorrectConfirmNewPassword() {
		return webElementActions.getTextMethod(errorMessageForConfirmNewPassword);
	}

	public void clickOnSubmitButtonForChangeLoginPassword() {
		webElementActions.clickOnMethod(submitButtonAtChangePassword);
	}
}
