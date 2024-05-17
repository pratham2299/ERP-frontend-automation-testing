package in.biencaps.erp.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;

import in.biencaps.erp.testCases.BaseTest;

public class WebElementActions {
	public WebDriver driver;
	private WebDriverWait wait;

	public WebElementActions() {
		this.driver = BaseTest.driver;
		wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(6));
	}

	public WebElement waitForElementVisibility(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitForAllElementsVisibility(By locator) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public WebElement waitForElementToBeClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void refreshThePage() {
		driver.navigate().refresh();
	}

	public String currentWindowUrl() {
		return driver.getCurrentUrl();
	}

	public int sizeOfListOfWebElement(By locator) {
		return waitForAllElementsVisibility(locator).size();
	}

	public WebElement lastWebELement(By locator) {
		int indexOfLastElement = sizeOfListOfWebElement(locator) - 1;
		WebElement lastElement = driver.findElements(locator).get(indexOfLastElement);
		return lastElement;
	}

	public void scrollUntilElementFound(By locator) {
		WebElement element = waitForElementVisibility(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scrolling down the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollUntilElementFound(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scrolling down the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollUntilElementFound(By locator, int index) {
		WebElement element = waitForAllElementsVisibility(locator).get(index);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scrolling down the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void mouseHoverOnElement(By locator) {
		WebElement element = waitForElementVisibility(locator);
		// Creating object of an Actions class
		Actions action = new Actions(driver);

		// Performing the mouse hover action on the target element.
		action.moveToElement(element).perform();
	}

	public void scrollHorizantally(int xValue, By locator) {
		WebElement element = waitForElementVisibility(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollLeft += arguments[1];", element, xValue);
	}

	public void clickOnMethod(By locator) {
		WebElement element = waitForElementVisibility(locator);
		waitForElementToBeClickable(element).click();
	}

	public void clickOnMethod(WebElement element) {
		waitForElementToBeClickable(element).click();
	}

	public void clickOnMethod(By locator, int index) {
		WebElement element = waitForAllElementsVisibility(locator).get(index);
		waitForElementToBeClickable(element).click();
	}

	public void clearMethod(By locator) {
		WebElement element = waitForElementVisibility(locator);
		// Click on the element to focus it (optional)
		element.click();

		// Send Control + A to select all text in the element
		element.sendKeys(Keys.CONTROL + "a");

		// Send Backspace to delete the selected text
		element.sendKeys(Keys.BACK_SPACE);
	}

	public void clearMethod(By locator, int index) {
		WebElement element = waitForAllElementsVisibility(locator).get(index);
		// Click on the element to focus it (optional)
		element.click();

		// Send Control + A to select all text in the element
		element.sendKeys(Keys.CONTROL + "a");

		// Send Backspace to delete the selected text
		element.sendKeys(Keys.BACK_SPACE);
	}

	public void sendKeysMethod(By locator, String input) {
		waitForElementVisibility(locator).sendKeys(input);
	}

	public void sendKeysMethod(By locator, int index, String input) {
		WebElement element = waitForAllElementsVisibility(locator).get(index);
		element.sendKeys(input);
	}

	public void sendKeysMethod(By locator, Keys key) {
		driver.findElement(locator).sendKeys(key);
	}

	public void sendKeysMethod(By locator, int index, Keys key) {
		WebElement element = waitForAllElementsVisibility(locator).get(index);
		element.sendKeys(key);
	}

	public String getCssValue(By locator, String value) {
		return driver.findElement(locator).getCssValue(value);
	}

	public String getCssValue(By locator, int index, String value) {
		WebElement element = waitForAllElementsVisibility(locator).get(index);
		return element.getCssValue(value);
	}

	public String getAtrributeMethod(By locator, String value) {
		return driver.findElement(locator).getAttribute(value);
	}

	public String getAtrributeMethod(By locator, int index, String value) {
		WebElement element = waitForAllElementsVisibility(locator).get(index);
		return element.getAttribute(value);
	}

	public String getTextMethod(By locator) {
		return waitForElementVisibility(locator).getText();
	}

	public String getTextMethod(By locator, int index) {
		WebElement element = waitForAllElementsVisibility(locator).get(index);
		return element.getText();
	}

	public boolean isDisplayedMethod(By locator) {
		return waitForElementVisibility(locator).isDisplayed();
	}

	public void selectByIndexMethod(By locator, int index) {
		Select select = new Select(waitForElementVisibility(locator));
		select.selectByIndex(index);
	}

	public void selectByVisibleTextMethod(By locator, String text) {
		Select select = new Select(waitForElementVisibility(locator));
		select.selectByVisibleText(text);
	}

	public List<String> getValuesFromListOfWebElements(By locator) {
		List<WebElement> webElements = waitForAllElementsVisibility(locator);
		int sizeOfWebElements = webElements.size();
		List<String> values = new ArrayList<String>();

		for (int i = 0; i < sizeOfWebElements; i++) {
			values.add(webElements.get(i).getText());
		}
		return values;
	}

	public List<String> getValuesOfWebelements(By locator) {
		List<WebElement> webElements = waitForAllElementsVisibility(locator);
		int sizeOfWebElements = webElements.size();
		List<String> values = new ArrayList<String>();

		for (int i = 0; i < sizeOfWebElements; i++) {
			values.add(webElements.get(i).getAttribute("value"));
		}
		return values;
	}
}
