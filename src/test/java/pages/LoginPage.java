package pages;

import config.CommonMethod;
import config.Reporter;
import org.openqa.selenium.By;
import pageFactory.PageFactoryInitializer;
import utils.Element;
import utils.ExplicitWaiting;
import utils.GenericMethods;

public class LoginPage extends PageFactoryInitializer {

    private By propertyLoginOption = By.xpath("//a[normalize-space()='Property Manager Login']");
    private By residentLoginOption = By.xpath("//a[normalize-space()='Resident Login']");
    private By loginErrorText = By.xpath("//p[@class='alert error slim']");
    private By propertySignInPageHeader = By.xpath("//h2[@class='fsm-header']");
    private By viewAppButton = By.xpath("//div[normalize-space()='View the App']");
    private By viewInWebsiteButton = By.xpath("//div[normalize-space()='View the App']");
    private By scheduleDemoPageHeader = By.xpath("//h1[normalize-space()='Property Management the Way It Should Be']");


    /*
    * This method is used to verify user redirects to correct page or not
    * @param type
     */
    public boolean verifyUserRedirectsToCorrectPage(String type) {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        String expectedSignInTitle = "Entrata Sign In";
        String expectedResidentTitle = "Welcome to the Resident Portal App";
        String actualTitle = getWebDriver().getTitle();
        System.out.println("Found Title:" + actualTitle);

        switch (type.toLowerCase()) {
            case "login":
                ExplicitWaiting.waitForPageLoaded(getWebDriver());

                if (expectedSignInTitle.equalsIgnoreCase(actualTitle)) {
                    Reporter.info("User is on Login Page");
                    return true;
                } else {
                    Reporter.info("User redirects to " + actualTitle + " instead of Login page");
                    return false;
                }

            case "resident":
                ExplicitWaiting.waitForPageLoaded(getWebDriver());

                if (expectedResidentTitle.equalsIgnoreCase(actualTitle)) {
                    Reporter.info("User is on Resident Login Page");
                    return true;
                } else {
                    Reporter.info("User redirects to " + actualTitle + " instead of Resident Login page");
                    return false;
                }

            case "property":
                ExplicitWaiting.waitForPageLoaded(getWebDriver());

                if (Element.isVisibleWithoutFailedMsg(propertySignInPageHeader, "Sign In Header Of Property")) {
                    Reporter.info("User is on Property Login Page");
                    return true;
                } else {
                    Reporter.info("User redirects to " + actualTitle + " instead of Property Login page");
                    return false;
                }

            case "scheduledemo":
                ExplicitWaiting.waitForPageLoaded(getWebDriver());

                try {
                    // Switch to the newly opened window
                    GenericMethods.switchToNewlyOpenedWindow();
                    ExplicitWaiting.waitForPageLoaded(getWebDriver());

                    // Check if the user is on the Schedule Demo page
                    if (Element.isVisibleWithoutFailedMsg(scheduleDemoPageHeader, "Schedule Demo Header")) {
                        Reporter.info("User is on Schedule Demo Page");
                        return true;
                    } else {
                        Reporter.info("User redirects to " + actualTitle + " instead of Schedule Demo page");
                        return false;
                    }
                } catch (Exception e) {
                    // If an exception occurs, check the same visibility
                    ExplicitWaiting.waitForPageLoaded(getWebDriver());

                    if (Element.isVisibleWithoutFailedMsg(scheduleDemoPageHeader, "Schedule Demo Header")) {
                        Reporter.info("User is on Schedule Demo Page");
                        return true;
                    } else {
                        Reporter.info("User redirects to " + actualTitle + " instead of Schedule Demo page");
                        return false;
                    }
                }

            default:
                Reporter.info("Please provide a valid page name");
                return false;
        }
    }


    /*
     * This method is used to login page based on user type
     * @param type
     */public void openLoginPage(String type){
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        CommonMethod.scrollDown(200);
        if(type.equalsIgnoreCase("property")){
            Element.clickUsingBy(propertyLoginOption, "Property Manager Login Button");
        } else if (type.equalsIgnoreCase("resident")) {
            Element.clickUsingBy(residentLoginOption, "Resident User Login Button");
        }
        else {
            Reporter.info("Please provide valid login user type");
        }
    }

    /*
     * This method is used to check options on resident login page
     * @param type
     */
    public boolean verifyOptionsOfResidentLogin(String type){
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        if(type.equalsIgnoreCase("app")){
            return Element.isVisibleUsingBy(viewAppButton, "View App Button");
        } else if (type.equalsIgnoreCase("website")) {
            return Element.isVisibleWithoutFailedMsg(viewInWebsiteButton, "View In Website Button");
        }
        else {
            Reporter.info("Please provide valid type to check on resident login");
            return false;
        }
    }

    /*
     * This method is used to verify error for Invalid login attempt
     */
    public boolean verifyErrorForInvalidLogin() {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        if(Element.isVisibleWithoutFailedMsg(loginErrorText, "Login error message")){
            return true;
        }
        else {
            return false;
        }
    }

    /*
    *This method is created for go back from property login page
     */
    public void goToWebsite(int attempts) {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());
        // Base case: limit the number of attempts to avoid infinite recursion
        if (attempts <= 0) {
            System.out.println("Max attempts reached. Unable to reach login page.");
            return;
        }

        ExplicitWaiting.waitForPageLoaded(getWebDriver());
        GenericMethods.back();

        if (Element.isVisibleUsingBy(propertyLoginOption, "Resident login option")) {
            Reporter.info("Reached login page");
        } else {
            ExplicitWaiting.waitForSeconds(5); // Optional wait before next attempt
            goToWebsite(attempts - 1); // Recursive call with decremented attempts
        }
    }
}
