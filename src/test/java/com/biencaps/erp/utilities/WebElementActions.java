package com.biencaps.erp.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;

import com.biencaps.erp.testCases.BaseTest;

public class WebElementActions {
	private WebDriver driver;
	private WebDriverWait wait;

	public WebElementActions() {
		this.driver = BaseTest.driver;
		wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(7));
	}

	public void refreshThePage() {
		driver.navigate().refresh();
	}

	public String currentWindowUrl() {
		return driver.getCurrentUrl();
	}

	public int sizeOfListOfWebElement(By locator) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)).size();
	}

	public void scrollUntilElementFound(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scrolling down the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollUntilElementFound(By locator, int index) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)).get(index);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scrolling down the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void mouseHoverOnElement(WebElement element) {
		// Creating object of an Actions class
		Actions action = new Actions(driver);

		// Performing the mouse hover action on the target element.
		action.moveToElement(element).perform();
	}

	public void scrollHorizantally(int xValue, By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollLeft += arguments[1];", element, xValue);
	}

	public void scrollBottomOfDiv(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		// Scroll to the bottom of the div element using JavaScript
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", element);
	}

	public void clickOnMethod(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void clickOnMethod(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void clickOnMethod(By locator, int index) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)).get(index);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void clearMethod(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
	}

	public void clearMethod(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
	}

	public void clearMethod(By locator, int index) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)).get(index);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)).get(index).clear();
	}

	public void sendKeysMethod(By locator, String input) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(input);
	}

	public void sendKeysMethod(By locator, int index, String input) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)).get(index);
		element.sendKeys(input);
	}

	public void sendKeysMethod(WebElement element, String input) {
		element.sendKeys(input);
	}

	public void sendKeysMethod(By locator, Keys key) {
		driver.findElement(locator).sendKeys(key);
	}

	public void sendKeysMethod(By locator, int index, Keys key) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)).get(index);
		element.sendKeys(key);
	}

	public String getCssValue(By locator, String value) {
		return driver.findElement(locator).getCssValue(value);
	}

	public String getAtrributeMethod(By locator) {
		return driver.findElement(locator).getAttribute("value");
	}

	public String getAtrributeMethod(WebElement element) {
		return element.getAttribute("value");
	}

	public String getAtrributeMethod(By locator, int index) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)).get(index);
		return element.getAttribute("value");
	}

	public String getTextMethod(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
	}

	public String getTextMethod(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element)).getText();
	}

	public String getTextMethod(By locator, int index) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)).get(index);
		return element.getText();
	}

	public boolean isDisplayedMethod(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
	}

	public void selectByIndexMethod(By locator, int index) {
		Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
		select.selectByIndex(index);
	}

	public void selectByVisibleTextMethod(By locator, String text) {
		Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
		select.selectByVisibleText(text);
	}

	public List<String> getValuesFromListOfWebElements(By locator) {
		List<WebElement> webElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		int sizeOfWebElements = webElements.size();
		List<String> values = new ArrayList<String>();

		for (int i = 0; i < sizeOfWebElements; i++) {
			values.add(webElements.get(i).getText());
		}
		return values;
	}
}
