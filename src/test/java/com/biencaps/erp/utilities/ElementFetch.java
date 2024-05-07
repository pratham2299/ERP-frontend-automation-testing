package com.biencaps.erp.utilities;

import java.util.List;

import org.openqa.selenium.*;

import com.biencaps.erp.testCases.BaseTest;

public class ElementFetch {
	public WebElement getWebElement(String identyfierType, String identifierValue) {
		switch (identyfierType) {
		case "id":
			return BaseTest.driver.findElement(By.id(identifierValue));

		case "name":
			return BaseTest.driver.findElement(By.name(identifierValue));

		case "tagName":
			return BaseTest.driver.findElement(By.tagName(identifierValue));

		case "cssSelector":
			return BaseTest.driver.findElement(By.cssSelector(identifierValue));

		case "xpath":
			return BaseTest.driver.findElement(By.xpath(identifierValue));

		default:
			return null;
		}
	}

	public List<WebElement> getWebElements(String identyfierType, String identifierValue) {
		switch (identyfierType) {
		case "id":
			return BaseTest.driver.findElements(By.id(identifierValue));

		case "name":
			return BaseTest.driver.findElements(By.name(identifierValue));

		case "tagName":
			return BaseTest.driver.findElements(By.tagName(identifierValue));

		case "cssSelector":
			return BaseTest.driver.findElements(By.cssSelector(identifierValue));

		case "xpath":
			return BaseTest.driver.findElements(By.xpath(identifierValue));

		default:
			return null;
		}
	}
}
