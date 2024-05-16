package in.biencaps.erp.pages;

import org.openqa.selenium.*;

import in.biencaps.erp.utilities.*;

public class LoginPage {
	protected WebElementActions web_element_actions = new WebElementActions();

	public By letsConnectButton = By.xpath("//div[@id='connectBtn']");
	public By userIdTextfield = By.xpath("//input[@type='text']");
	public By userIdTextfieldErrorMessage = By.xpath("//p[normalize-space()='Enter Valid User ID']");
	public By passwordTextfield = By.xpath("//input[@type='password']");
	public By eyeIconNearPasswordTextfield = By.xpath("(//*[name()='svg'])[2]");
	public By passwordTextfieldErrorMessage = By.xpath("//p[normalize-space()='Enter Valid Password']");
	public By loginButton = By.xpath("//button[normalize-space()='LOGIN']");
	public By forgotPasswordLink = By.xpath("//p[normalize-space()='Forgot Password?']");
	public By errorToastMessageForForgotPassword = By.xpath("//div[contains(text(),'Enter valid Employee Id')]");
	public By sendPasswordButton = By.xpath("//div[@class='Forgot-submit-btn']");
	public By suceessMessageForForgotPassword = By.xpath("//div[@class='loader-success-message']");
	public By loggedInToastMessage = By.xpath("//div[contains(text(),'Login Successfully')]");
	public By closeIconOfToastMessage = By.xpath("(//*[name()='svg'][@aria-hidden='true'])[1]");

	public void clickOnLetsConnectButton() {
		web_element_actions.clickOnMethod(letsConnectButton);
	}

	public void clickOnUserIdTextfield() {
		web_element_actions.clickOnMethod(userIdTextfield);
	}

	public void clearUserIdTextfield() {
		web_element_actions.clearMethod(userIdTextfield);
	}

	public String enterAndCheckUserId(String userId) {
		web_element_actions.sendKeysMethod(userIdTextfield, userId);
		return web_element_actions.getAtrributeMethod(userIdTextfield, "value");
	}

	public String checkUserIdTextfieldErrorMessage() {
		return web_element_actions.getTextMethod(userIdTextfieldErrorMessage);
	}

	public void clickOnPasswordTextfield() {
		web_element_actions.clickOnMethod(passwordTextfield);
	}

	public void clearPasswordTextfield() {
		web_element_actions.clearMethod(passwordTextfield);
	}

	public String enterAndCheckPassword(String password) {
		web_element_actions.sendKeysMethod(passwordTextfield, password);
		return web_element_actions.getAtrributeMethod(passwordTextfield, "value");
	}

	public void clickOnEyeIconNearPasswordTextfield() {
		web_element_actions.clickOnMethod(eyeIconNearPasswordTextfield);
	}

	public String checkPasswordTextfieldErrorMessage() {
		return web_element_actions.getTextMethod(passwordTextfieldErrorMessage);
	}

	public void clickOnLoginButton() {
		web_element_actions.clickOnMethod(loginButton);
	}

	public void clickOnForgotPasswordLink() {
		web_element_actions.clickOnMethod(forgotPasswordLink);
	}

	public String checkErrorToastMessageForForgotPassword() {
		return web_element_actions.getTextMethod(errorToastMessageForForgotPassword);
	}

	public void clickOnSendPasswordButton() {
		web_element_actions.clickOnMethod(sendPasswordButton);
	}

	public String checkForgotPasswordSentSuccessfullyMessage() {
		return web_element_actions.getTextMethod(suceessMessageForForgotPassword);
	}

	public String checkSuccessfullLoggedInToastMessage(String userId) {
		return web_element_actions.getTextMethod(loggedInToastMessage);
	}

	public void clickOnCloseIconOfToastMessage() {
		web_element_actions.clickOnMethod(closeIconOfToastMessage);
	}
}