package utils;

import config.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

import static base.WebDriverFactory.getWebDriver;
import static java.time.Duration.ofSeconds;

public class ExplicitWaiting {

	public ExplicitWaiting() {

	}

	/* Select using WebElements */

	/**
	 * To Wait Until Element to be Clickable
	 */
	public static void explicitWaitElementToBeClickable(WebElement element, int Seconds) {
		WebDriverWait clickableWait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(Seconds));
		clickableWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * To Wait Until Element to be Selectable
	 */
	public static void explicitWaitElementToBeSelected(WebElement element, int Seconds) {
		WebDriverWait selectableWait = new WebDriverWait(getWebDriver(), ofSeconds(Seconds));
		selectableWait.until(ExpectedConditions.elementToBeSelected(element));
	}

	/**
	 * To Wait Until Element has the text
	 */
	public static void explicitWaitTextToBePresentInElement(WebElement element, int Seconds, String text) {
		WebDriverWait textToBePresent = new WebDriverWait(getWebDriver(), ofSeconds(Seconds));
		textToBePresent.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	/**
	 * To Wait Until Element to be Visible
	 */
	public static void explicitWaitVisibilityOfElement(WebElement element, int Seconds) {
		WebDriverWait elementToBeVisible = new WebDriverWait(getWebDriver(), ofSeconds(Seconds));
		elementToBeVisible.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * To Wait Until Element is Selected
	 */
	public static void explicitWaitSelectionStateToBe(WebElement element, int Seconds, boolean selected) {
		WebDriverWait elementIsSelected = new WebDriverWait(getWebDriver(), ofSeconds(Seconds));
		elementIsSelected.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
	}

	/**
	 * To Wait Until Elements to be Visible
	 * 
	 * @return
	 */
	public static boolean explicitWaitVisibilityOfElements(List<WebElement> element, int Seconds) {
		WebDriverWait elementsToBeVisible = new WebDriverWait(getWebDriver(), ofSeconds(Seconds));
		elementsToBeVisible.until(ExpectedConditions.visibilityOfAllElements(element));
		return true;
	}

	/**
	 * Wait for page load completely
	 */
	public static void waitForPageLoaded(WebDriver driver) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(10000);
			@SuppressWarnings("deprecation")
			WebDriverWait wait = new WebDriverWait(driver, ofSeconds(30));
			wait.until(expectation);
		} catch (Throwable error) {
			Reporter.fail("Timeout waiting for Page Load Request to complete.");
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	/**
	 * Wait for seconds
	 */
	public static void waitForSeconds(int timeoutInSeconds) {
		try {
			Thread.sleep(timeoutInSeconds * 1000);
		} catch (InterruptedException e) {

		}
	}

	/* Select using By Method */

	/**
	 * To Wait Until Element to be Clickable
	 */
	public static void explicitWaitElementToBeClickable(By element, int Seconds) {
		WebDriverWait clickableWait = new WebDriverWait(getWebDriver(), ofSeconds(Seconds));
		clickableWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * To Wait Until Element to be Selectable
	 */
	public static void explicitWaitElementToBeSelected(By element, int Seconds) {
		WebDriverWait selectableWait = new WebDriverWait(getWebDriver(), ofSeconds(Seconds));
		selectableWait.until(ExpectedConditions.elementToBeSelected(element));
	}

	/**
	 * To Wait Until Title contains the text
	 */
	public static void explicitWaitTitleContains(By element, int Seconds, String title) {
		WebDriverWait titleContains = new WebDriverWait(getWebDriver(), ofSeconds(Seconds));
		titleContains.until(ExpectedConditions.titleContains(title));
	}

	/**
	 * To Wait Until Title is
	 */
	public static void explicitWaitTitleIs(By element, int Seconds, String title) {
		WebDriverWait titleIs = new WebDriverWait(getWebDriver(), ofSeconds(Seconds));
		titleIs.until(ExpectedConditions.titleIs(title));
	}

	/**
	 * To Wait Until Element to be Visible
	 */
	public static void explicitWaitVisibilityOfElement(By element, int Seconds) {
		WebDriverWait elementToBeVisible = new WebDriverWait(getWebDriver(), ofSeconds(Seconds));
		elementToBeVisible.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	/**
	 * To Wait Until Element is Selected
	 */
	public static void explicitWaitSelectionStateToBe(By element, int Seconds, boolean selected) {
		WebDriverWait elementToBeVisible = new WebDriverWait(getWebDriver(), ofSeconds(Seconds));
		elementToBeVisible.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
	}

	/**
	 * To Wait for the Alert
	 */
	public static void explicitWaitForAlert(int Seconds) {
		WebDriverWait waitForAlert = new WebDriverWait(getWebDriver(), ofSeconds(Seconds));
		waitForAlert.until(ExpectedConditions.alertIsPresent());
	}
}