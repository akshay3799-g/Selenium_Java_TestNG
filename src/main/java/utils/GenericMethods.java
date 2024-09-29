package utils;

import java.util.Set;
import base.Constants;
import org.openqa.selenium.support.PageFactory;

import static base.WebDriverFactory.getWebDriver;

public class GenericMethods {
	
	private GenericMethods() {

	}

	/**
	 * used to switch to newly opened window
	 */
	public static void switchToNewlyOpenedWindow() {
		String parentWindow = getWebDriver().getWindowHandle();
		Set<String> allwindow = getWebDriver().getWindowHandles();
		for (String window : allwindow) {
			if (!window.equalsIgnoreCase(parentWindow)) {
				getWebDriver().switchTo().window(window);
				break; // Exit the loop once we switch to the new window
			}
		}
	}

	/**
	 * This method is closing the current window and switch to parent window
	 */
	public static void closeCurrentWindow() {
		String parentWindow = getWebDriver().getWindowHandle();
		Set<String> allwindow = getWebDriver().getWindowHandles();
		for (String window : allwindow) {
			if (!window.equalsIgnoreCase(parentWindow)) {
				getWebDriver().close();
				getWebDriver().switchTo().window(window);
				break;
			}
		}
	}

	/**
	 * This method closing the parent window
	 */
	public static void closeParentWindow() {
		String parentWindow = getWebDriver().getWindowHandle();
		Set<String> allwindow = getWebDriver().getWindowHandles();
		for (String window : allwindow) {
			if (!window.equalsIgnoreCase(parentWindow)) {
				getWebDriver().switchTo().window(window);
				getWebDriver().close();
				getWebDriver().switchTo().window(parentWindow);
				break;
			}
		}
	}

	/**
	 * This method is for getting url of the website
	 */
	public static void getURL() throws Exception {
		getWebDriver().get(Constants.get("url"));
		ExplicitWaiting.waitForPageLoaded(getWebDriver());
		ExplicitWaiting.waitForSeconds(7);
	}

	/**
	 * Refresh the Active Window
	 */
	public static void refresh() {
		getWebDriver().navigate().refresh();
		ExplicitWaiting.waitForPageLoaded(getWebDriver());
		ExplicitWaiting.waitForSeconds(7);
	}

	/**
	 * Navigate Backwards
	 */
	public static void back() {
		getWebDriver().navigate().back();
		ExplicitWaiting.waitForPageLoaded(getWebDriver());
		ExplicitWaiting.waitForSeconds(7);
	}

	/**
	 * Navigate forwards
	 */
	public static void forward() {
		getWebDriver().navigate().forward();
		ExplicitWaiting.waitForPageLoaded(getWebDriver());
		ExplicitWaiting.waitForSeconds(5);
	}

	/**
	 * Navigates to given URL
	 *
	 * @param url
	 * @throws Exception
	 */
	public static void getURL(String url) throws Exception {
		getWebDriver().get(url);
		ExplicitWaiting.waitForPageLoaded(getWebDriver());
		ExplicitWaiting.waitForSeconds(7);
	}

}