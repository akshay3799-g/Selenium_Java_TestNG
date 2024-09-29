
package utils;

import base.Constants;
import base.WebDriverFactory;
import config.Reporter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Element extends WebDriverFactory {

	static int waitInSeconds = 15;

	private Element(){

	}

	/**
	 * This method is for click the specific element
	 * 
	 * @param element
	 * @param elementName
	 */
	public static void clickUsingBy(By element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitElementToBeClickable(element, waitInSeconds);
			getWebDriver().findElement(element).click();
		} catch (Exception ex) {
			log.info(elementName + " is not Clickable");
		}
	}

	/**
	 * This method is for click the specific element
	 * 
	 * @param element
	 * @param elementName
	 */
	public static void clickUsingWebElement(WebElement element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitElementToBeClickable(element, waitInSeconds);
			element.click();
		} catch (Exception ex) {
			log.info(elementName + " is not Clickable");
			ex.printStackTrace();
		}
	}

	/**
	 * This method is for click the element from list using index number
	 * 
	 * @param element
	 * @param elementName
	 * @param index
	 */
	public static void clickFromList(By element, String elementName, int index) {
		try {
			ExplicitWaiting.explicitWaitElementToBeClickable(element, waitInSeconds);
			getWebDriver().findElements(element).get(index).click();
		} catch (Exception ex) {
			log.info(elementName + " is not Clickable");
			ex.printStackTrace();;
		}
	}

	/**
	 * This method is for click the element using action class
	 * 
	 * @param element
	 * @param elementName
	 */
	public static void clickAction(By element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			ExplicitWaiting.explicitWaitElementToBeClickable(element, waitInSeconds);
			Actions builder = new Actions(getWebDriver());
			builder.moveToElement(getElement(element)).click(getElement(element));
			builder.perform();
		} catch (Exception ex) {
			log.info(elementName + " is not Clickable");
			ex.printStackTrace();;
		}
	}
	
	/**
	 * This method is for click the element using action class
	 * 
	 * @param element
	 * @param elementName
	 */
	public static void doubleClickAction(By element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			ExplicitWaiting.explicitWaitElementToBeClickable(element, waitInSeconds);
			Actions builder = new Actions(getWebDriver());
			builder.moveToElement(getWebDriver().findElement(element)).doubleClick().build().perform();
		} catch (Exception ex) {
			log.info(elementName + " is not Clickable");
			ex.printStackTrace();;
		}
	}

	/**
	 * This method is for click the element using javascript executor
	 * 
	 * @param element
	 * @param elementName
	 */
	public static void clickJavaScript(By element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			ExplicitWaiting.explicitWaitElementToBeClickable(element, waitInSeconds);
			((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click();", getElement(element));
		} catch (Exception ex) {
			log.info(elementName + " is not Clickable");
			ex.printStackTrace();;
		}
	}

	/**
	 * This method is for checking the visibility of the element and return true
	 * 
	 * @param element
	 * @param elementName
	 * @return Boolean
	 */
	public static boolean isVisibleUsingBy(By element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			if (getWebDriver().findElement(element).isDisplayed()) {
				return true;
			}
		} catch (Exception ex) {
			Reporter.fail(elementName + " is not Displayed");
			ex.printStackTrace();;
			return false;
		}
		return false;
	}

	/**
	 * This method is for checking the visibility of the element and return true
	 * 
	 * @param element
	 * @param elementName
	 * @return Boolean
	 */
	public static boolean isVisibleUsingWebElement(WebElement element, String elementName) {
		try {

			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			if (element.isDisplayed()) {
				return true;
			}
		} catch (Exception ex) {
			Reporter.fail(elementName + " is not Displayed");
			ex.printStackTrace();;
			return false;
		}
		return false;
	}

	/**
	 * This method is for checking the visibility of the element and return true
	 * 
	 * @param element
	 * @param elementName
	 * @return Boolean
	 */
	public static boolean isVisibleWithoutFailedMsg(By element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			if (getWebDriver().findElement(element).isDisplayed()) {
				return true;
			}
		} catch (Exception ex) {
			log.info(elementName + " is not Displayed");
			ex.printStackTrace();;
			return false;
		}
		return false;
	}

	/**
	 * This method is for checking the visibility of the element and return true
	 * 
	 * @param element
	 * @param elementName
	 * @return Boolean
	 */
	public static boolean isNotVisible(By element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, 3);
			if ((getWebDriver().findElement(element).isDisplayed())) {
				log.info(elementName + " is Displayed");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();;
			return true;
		}
		return true;
	}

	/**
	 * This method is for checking the visibility of the list of elements and return
	 * true
	 * 
	 * @param e
	 * @param elementName
	 * @return Boolean
	 */
	public static boolean isListVisibleUsingBy(By e, String elementName) {
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(waitInSeconds));
			wait.until(ExpectedConditions.visibilityOfAllElements(getElementList(e)));
			if (ExplicitWaiting.explicitWaitVisibilityOfElements(getElementList(e), waitInSeconds)) {
				return true;
			} else {
				Reporter.fail(elementName + " is not Displayed");
				return false;
			}
		} catch (Exception ex) {
			Reporter.fail(elementName + " is not Displayed");
			ex.printStackTrace();;
			return false;
		}
	}

	/**
	 * This method is for checking the visibility of the list of elements and return
	 * true
	 * @param elementName
	 * @return Boolean
	 */
	public static boolean isListVisibleUsingByWithoutFailedMSG(By element, String elementName) {
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(waitInSeconds));
			wait.until(ExpectedConditions.visibilityOfAllElements(getElementList(element)));
			if (ExplicitWaiting.explicitWaitVisibilityOfElements(getElementList(element), waitInSeconds)) {
				return true;
			} else {
				log.info(elementName + " is not Displayed");
				return false;
			}
		} catch (Exception ex) {
			log.info(elementName + " is not Displayed");
			ex.printStackTrace();;
			return false;
		}
	}

	/**
	 * This method is for checking the visibility of the list of elements and return
	 * true
	 * 
	 * @param elementName
	 * @return Boolean
	 */
	public static boolean isListVisibleUsingWebelement(List<WebElement> element, String elementName) {
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(waitInSeconds));
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
			if (ExplicitWaiting.explicitWaitVisibilityOfElements(element, waitInSeconds)) {
				return true;
			} else {
				Reporter.fail(elementName + " is not Displayed");
				return false;
			}
		} catch (Exception ex) {
			Reporter.fail(elementName + " is not Displayed");
			ex.printStackTrace();;
			return false;
		}
	}

	/**
	 * This method is for clear the previous text and enter new text
	 * 
	 * @param element
	 * @param text
	 * @param elementName
	 */
	public static void clearEnterText(By element, String text, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			if (getWebDriver().findElement(element).isEnabled()) {
				clearTextKeys(element, elementName);
				getElement(element).sendKeys(text.trim());
			}
		} catch (Exception ex) {
			log.info(elementName + " is not Enabled");
			ex.printStackTrace();;
		}
	}

	/**
	 * This method is for clear the previous text and enter new text
	 *
	 * @param element
	 * @param text
	 * @param elementName
	 */
	public static void clearEnterTextWebelement(WebElement element, String text, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			if (element.isEnabled()) {
				element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				element.sendKeys(Keys.BACK_SPACE);
				element.sendKeys(text);
			}
		} catch (Exception ex) {
			log.info(elementName + " is not Enabled");
			ex.printStackTrace();;
		}
	}

	/**
	 * This method is clear the entire text using keys operation
	 * 
	 * @param element
	 * @param elementName
	 */
	public static void clearTextKeys(By element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			if (getElement(element).isEnabled()) {
				getElement(element).sendKeys(Keys.chord(Keys.CONTROL, "a"));
				getElement(element).sendKeys(Keys.BACK_SPACE);
			}
		} catch (Exception ex) {
			log.info(elementName + " is not Enabled");
			ex.printStackTrace();;
		}
	}

	/**
	 * This method is for enter new text
	 * 
	 * @param element
	 * @param text
	 * @param elementName
	 */
	public static void enterText(By element, String text, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(getElement(element), waitInSeconds);
			getElement(element).sendKeys(text);

		} catch (Exception ex) {
			log.info(elementName + " is not Enabled");
			ex.printStackTrace();;
		}
	}

	/**
	 * This method is for entering the keys
	 * 
	 * @param element
	 * @param key
	 * @param elementName
	 */
	public static void enterText(By element, Keys key, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			if (getElement(element).isEnabled()) {
				getElement(element).sendKeys(key);
			}
		} catch (Exception ex) {
			log.info(elementName + " is not Enabled");
			ex.printStackTrace();;
		}
	}

	/**
	 * This method is used to both send one KEY (same as method above) but also
	 * sends combination of keys
	 * 
	 * @param element
	 * @param keysToSend  (for sending multiple keys)
	 * @param elementName This method is for entering the keys
	 */
	public static void enterText(By element, String elementName, CharSequence... keysToSend) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, 2);
			if (getElement(element).isEnabled()) {
				getElement(element).sendKeys(keysToSend);
			}
		} catch (Exception ex) {
			ex.printStackTrace();;
			log.info(elementName + " is not Enabled");
		}
	}

	/**
	 * This method checks element is enabled or not with adjustable Wait for quicker
	 * execution.
	 * 
	 * @param element
	 * @param elementName
	 * @param wait        -> used wait here to reduce wait for quick executions
	 * @return boolean
	 */
	public static boolean isEnable(By element, String elementName, int wait) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, wait);
			if (getElement(element).isEnabled()) {
				return true;
			}
		} catch (Exception ex) {
			log.info(elementName + " is not Enabled");
			return false;
		}
		return false;
	}

	/**
	 * This method checks element is enabled nor not
	 * 
	 * @param element
	 * @param elementName
	 * @return boolean
	 */
	public static boolean isEnable(By element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			if (getElement(element).isEnabled()) {
				return true;
			}
		} catch (Exception ex) {
			log.info(elementName + " is not Enabled");
			ex.printStackTrace();;
			return false;
		}
		return false;
	}

	/**
	 * This method checks element is enabled nor not using WebElement
	 * 
	 * @param element
	 * @param elementName
	 * @return boolean
	 */
	public static boolean isEnable(WebElement element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			if (element.isEnabled()) {
				return true;
			}
		} catch (Exception ex) {
			log.info(elementName + " is not Enabled");
			ex.printStackTrace();;
			return false;
		}
		return false;
	}

	/**
	 * This method return name of the screenshot
	 * 
	 * @param methodName
	 * @return String
	 */
	public static String getScreenshotName(String methodName) {
		Date d = new Date();
		String fileName = methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
		return fileName;
	}

	/**
	 * This method is used to take screenshots
	 * 
	 * @param methodName
	 * @return String
	 * @throws Exception
	 */
	public static String takeScreenshot(String methodName) throws Exception {
		String fileName = getScreenshotName(methodName);
//		String directory = System.getProperty("user.dir") + "/screenshots/";
//		new File(directory).mkdirs();
		String path = Constants.screenshotsDir + fileName;
		try {
			File screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
		} catch (Exception e) {
		}

		return path;
	}

	/**
	 * This method return web element
	 * 
	 * @param element
	 * @return WebElement
	 */
	public static WebElement getElement(By element) {
		WebElement webElement = null;
		try {
			webElement = getWebDriver().findElement(element);
			return webElement;
		} catch (Exception e) {
			Reporter.info("Xpath is not found");
		}
		return webElement;
	}

	/**
	 * This method return list of web elements
	 * 
	 * @param element
	 * @return List<WebElement> - WebElement
	 */
	public static List<WebElement> getElementList(By element) {
		List<WebElement> elementList = null;
		try {
			elementList = getWebDriver().findElements(element);
			return elementList;
		} catch (Exception e) {
			Reporter.info("Xpath is not found");
			e.printStackTrace();
		}
		return elementList;
	}

	/**
	 * This method return text of web element
	 * 
	 * @param element
	 * @return String
	 */
	public static String getText(By element) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			return getWebDriver().findElement(element).getText().trim();
		} catch (Exception e) {
			Reporter.info("Xpath is not found");
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * This method return text of web element
	 * 
	 * @param element
	 * @return String
	 */
	public static String getText(WebElement element) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			return element.getText().trim();
		} catch (Exception e) {
			Reporter.info("Xpath is not found");
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * This method return value using getAttribute function of web element
	 * 
	 * @param element
	 * @return String
	 */
	public static String getAttributeValue(By element) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			return getWebDriver().findElement(element).getAttribute("value").trim();
		} catch (Exception e) {
			Reporter.info("Xpath is not found");
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * This method return value using getAttribute function of web element
	 *
	 * @param element
	 * @return String
	 */
	public static String getAttributeType(By element) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			return getWebDriver().findElement(element).getAttribute("type").trim();
		} catch (Exception e) {
			Reporter.info("Xpath is not found");
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * This method return given attribute from given By element
	 * 
	 * @param element
	 * @param attribute
	 * @return String
	 */
	public static String getAttribute(By element, String attribute) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			return getWebDriver().findElement(element).getAttribute(attribute).trim();
		} catch (Exception e) {
			Reporter.info("Xpath is not found");
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * This method clicks on given WebElement and handles browser level alerts
	 * 
	 * @param element
	 * @param elementName
	 */
	public static void alertHandle(WebElement element, String elementName) {
		try {
			isVisibleUsingWebElement(element, elementName);
			ExplicitWaiting.explicitWaitElementToBeClickable(element, waitInSeconds);
			element.click();
			Alert simpleAlert = getWebDriver().switchTo().alert();
			simpleAlert.accept();
		} catch (Exception ex) {
			log.info("FAIL:- " + elementName + " is not Clickable");
			ex.printStackTrace();;
		}
	}

	/**
	 * This method handle browser level alert without passing parameter
	 */
	public static void browserLevelAlertHandle() {
		try {
			Alert simpleAlert = getWebDriver().switchTo().alert();
			simpleAlert.accept();
		} catch (Exception ex) {
			ex.printStackTrace();;
		}
	}

	/**
	 * This method clicks on given By Element and handles browser level alerts
	 * 
	 * @param element
	 * @param elementName
	 * @return
	 */
	public static void alertHandle(By element, String elementName) {
		try {
			isVisibleUsingBy(element, elementName);
			ExplicitWaiting.explicitWaitElementToBeClickable(element, waitInSeconds);
			clickUsingBy(element, elementName);
			Alert simpleAlert = getWebDriver().switchTo().alert();
			simpleAlert.accept();
		} catch (Exception ex) {
			Reporter.info("FAIL:- " + elementName + " is not Clickable");
			ex.printStackTrace();;
		}
	}

	/**
	 * Checks if Specified String is present in Page Source
	 * 
	 * @param search
	 * @return boolean
	 */
	public static boolean isStringPresentInPageSource(String search) {
		ExplicitWaiting.waitForPageLoaded(getWebDriver());
		ExplicitWaiting.waitForSeconds(5);
		if (getWebDriver().getPageSource().contains(search)) {
			return true;
		}
		return false;
	}

	/**
	 * This method is for checking the visibility of the element and return true
	 * 
	 * @param element
	 * @param elementName
	 * @return Boolean
	 */
	public static boolean checkIfElementVisibleInstant(By element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, 2);
			if (getWebDriver().findElement(element).isDisplayed()) {
				return true;
			}
		} catch (Exception ex) {
			log.info(elementName + " is not Displayed");
			ex.printStackTrace();;
			return false;
		}
		return false;
	}

	/**
	 * This method is for click the specific element
	 * 
	 * @param element
	 * @param elementName
	 */
	public static void clickUsingByInstant(By element, String elementName, int wait) {
		try {
			ExplicitWaiting.explicitWaitElementToBeClickable(element, wait);
			getWebDriver().findElement(element).click();
		} catch (Exception ex) {
			log.info(elementName + " is not Clickable");
		}
	}

	/**
	 * Checks if a field is editable or not then sets the previous value
	 *
	 * @param element
	 * @param elementName
	 * @param textToEnter
	 * @return boolean
	 */
	public static boolean isTextFieldEditable(By element, String elementName, String textToEnter) {
		boolean isEditable = false;
		try {
			if (Element.isVisibleUsingBy(element, elementName)) {
				String previousValue = getText(element);
				if (StringUtils.isBlank(previousValue)) {
					previousValue = getAttributeValue(element);
				}
				getElement(element).sendKeys(Keys.CONTROL, "a");
				ExplicitWaiting.waitForSeconds(3);
				enterText(element, textToEnter, elementName);
				if (getAttributeValue(element).equals(textToEnter) || getText(element).equals(textToEnter)) {
					isEditable = true;
				}
				getElement(element).sendKeys(Keys.CONTROL, "a");
				enterText(element, previousValue, elementName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return isEditable;
	}

	/**
	 * Returns clickable element from given By Elements
	 * 
	 * @param element
	 * @return WebElement
	 */
	public static WebElement getClickableElement(By element) {
		for (WebElement webElement : getElementList(element)) {
			try {
				WebDriverWait clickableWait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(2));
				clickableWait.until(ExpectedConditions.elementToBeClickable(webElement));
				return webElement;
			} catch(Exception e) {

			}
		}
		Reporter.info("None of the element is Clickable");
		return null;
	}

	/**
	 * This method is used to hover the mouse for given time
	 * @param element
	 * @param wait
	 */
	public static void MouseHoverAndPause(By element, int wait) {
		Actions actObj = new Actions(getWebDriver());
		actObj.moveToElement(getElement(element)).pause(wait).perform();
	}

	/**
	 * This method is used to hover the mouse for given time
	 * @param element
	 * @param wait
	 */
	public static void MouseHoverAndPause(WebElement element, int wait) {
		Actions actObj = new Actions(getWebDriver());
		actObj.moveToElement(element).pause(wait).perform();
	}
}
