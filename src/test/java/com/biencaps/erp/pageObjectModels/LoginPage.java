package com.biencaps.erp.pageObjectModels;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import com.biencaps.erp.utilities.*;

public class LoginPage {
	protected WebDriver driver;

	protected WebElementActions web_element_actions;

	@FindBy(xpath = "//div[@id='connectBtn']")
	public WebElement lets_connect_button;

	@FindBy(xpath = "//input[@type='text']")
	public WebElement user_id_textfield;

	@FindBy(xpath = "//p[normalize-space()='Enter Valid User ID']")
	public WebElement user_id_textfield_error_message;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement password_textfield;

	@FindBy(xpath = "//p[normalize-space()='Enter Valid Password']")
	public WebElement password_textfield_error_message;

	@FindBy(xpath = "(//*[name()='svg'])[2]")
	public WebElement eye_icon_near_password_textfield;

	@FindBy(xpath = "//button[normalize-space()='LOGIN']")
	public WebElement login_button;

	@FindBy(xpath = "//p[normalize-space()='Forgot Password?']")
	public WebElement forgot_password_link;

//	public By letsConnectButton = By.xpath("//div[@id='connectBtn']");
//	public By userIdTextfield = By.xpath("//input[@type='text']");
//	public By userIdTextfieldErrorMessage = By.xpath("//p[normalize-space()='Enter Valid User ID']");
//	public By passwordTextfield = By.xpath("//input[@type='password']");
//	public By eyeIconNearPasswordTextfield = By.xpath("(//*[name()='svg'])[2]");
//	public By passwordTextfieldErrorMessage = By.xpath("//p[normalize-space()='Enter Valid Password']");
//	public By loginButton = By.xpath("//button[normalize-space()='LOGIN']");
//	public By forgotPasswordLink = By.xpath("//p[normalize-space()='Forgot Password?']");
	public By errorToastMessageForForgotPassword = By.xpath("//div[contains(text(),'Enter valid Employee Id')]");
	public By sendPasswordButton = By.xpath("//div[@class='Forgot-submit-btn']");
	public By suceessMessageForForgotPassword = By.xpath("//div[@class='loader-success-message']");
	public By loggedInToastMessage = By.xpath("//div[contains(text(),'Login Successfully')]");
	public By closeIconOfToastMessage = By.xpath("(//*[name()='svg'][@aria-hidden='true'])[1]");

	// constructor created for webdriver initialized to this class driver variable
	// Also that webdriver passed as argument to selenium methods class
	// (A class which has common selenium methods used for all classes)
	public LoginPage(WebDriver driver2) {
		PageFactory.initElements(driver2, this);
		web_element_actions = new WebElementActions();
	}

	public void clickOnLetsConnectButton() {
		web_element_actions.clickOnMethod(lets_connect_button);
	}

	public void clickOnUserIdTextfield() {
		web_element_actions.clickOnMethod(user_id_textfield);
	}

	public void clearUserIdTextfield() {
		web_element_actions.clearMethod(user_id_textfield);
	}

	public String enterAndCheckUserId(String userId) {
		web_element_actions.sendKeysMethod(user_id_textfield, userId);
		return web_element_actions.getAtrributeMethod(user_id_textfield);
	}

	public String checkUserIdTextfieldErrorMessage() {
		return web_element_actions.getTextMethod(user_id_textfield_error_message);
	}

	public void clickOnPasswordTextfield() {
		web_element_actions.clickOnMethod(password_textfield);
	}

	public void clearPasswordTextfield() {
		web_element_actions.clearMethod(password_textfield);
	}

	public String enterAndCheckPassword(String password) {
		web_element_actions.sendKeysMethod(password_textfield, password);
		return web_element_actions.getAtrributeMethod(password_textfield);
	}

	public void clickOnEyeIconNearPasswordTextfield() {
		web_element_actions.clickOnMethod(eye_icon_near_password_textfield);
	}

	public String checkPasswordTextfieldErrorMessage() {
		return web_element_actions.getTextMethod(password_textfield_error_message);
	}

	public void clickOnLoginButton() {
		web_element_actions.clickOnMethod(login_button);
	}

	public void clickOnForgotPasswordLink() {
		web_element_actions.clickOnMethod(forgot_password_link);
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
