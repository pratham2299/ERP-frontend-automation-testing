package com.biencaps.erp.pageObjectModels;

import org.openqa.selenium.By;

import com.biencaps.erp.utilities.WebElementActions;

public class ProfilePage {
	protected WebElementActions webElementActions = new WebElementActions();

	public By editInfoButton = By.xpath("//div[@class='emp-edit']");
	public By gmailKeyTextfield = By.xpath("(//input[@type='text'])[1]");
	public By passwordTextfield = By.xpath("//input[@type='password']");
	public By sendButtonForGmailKeySubmit = By.xpath("//div[@class='key-submit-button']");

	public void clickOnEditInfoButton() {
		webElementActions.clickOnMethod(editInfoButton);
	}

	public void clearGmailKeyField() {
		webElementActions.clearMethod(gmailKeyTextfield);
	}

	public void enterGmailKey(String gmailKey) {
		webElementActions.sendKeysMethod(gmailKeyTextfield, gmailKey);
	}

	public void enterPassword(String password) {
		webElementActions.sendKeysMethod(passwordTextfield, password);
	}

	public void clickOnSendButtonForGmailKeySubmit() {
		webElementActions.clickOnMethod(sendButtonForGmailKeySubmit);
	}
}
