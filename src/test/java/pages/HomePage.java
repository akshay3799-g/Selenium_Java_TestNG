package pages;

import config.CommonMethod;
import config.Reporter;
import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.WebElement;
import pageFactory.PageFactoryInitializer;
import org.openqa.selenium.By;
import utils.Element;
import utils.ExplicitWaiting;
import utils.GenericMethods;

import java.util.List;


public class HomePage extends PageFactoryInitializer {

    private By logo = By.xpath("//a[@class='brand w-nav-brand']");
    private By homepageHeader = By.xpath("//div[normalize-space()='Property management software']");
    private By downloadReportSectionHeading = By.xpath("//h3[normalize-space()='Worried about making the switch?']");
    private By downloadReportButton = By.xpath("//div[normalize-space()='Download the Report']");
    private By seeHowItWorkButton = By.xpath("//div[normalize-space()='See How it Works']");
    private By downloadButton = By.xpath("//div[normalize-space()='Download Now']");
    private By watchNowButton = By.xpath("//div[normalize-space()='Watch Now']");
    private By scheduleDemoButton = By.xpath("//div[normalize-space()='Schedule Your Demo']");
    private By signInButton = By.xpath("(//a[normalize-space()='Sign In'])[1]");
    private By signInButtonOfLogin = By.xpath("//button[normalize-space()='Sign In']");
    By resourcesOptionInNavigation = By.xpath("//div[normalize-space()='Resources']//div[1]");
    private By resourcesPageHeading = By.xpath("//h2[normalize-space()='Resource Center']");
    private By acceptCookiesButton = By.xpath("//div[@class='cookie-button-row']//a");
    private By resourcesPageeading = By.xpath("//h2[normalize-space()='Resource Center']");

    /*
     * This method is used to click accept cookie button
     */
    public void acceptCookies(){
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        if(Element.isVisibleWithoutFailedMsg(acceptCookiesButton, "Accept cookies button")) {
            Element.clickUsingBy(acceptCookiesButton, "Accept cookies button");
        }
    }

    /*
     * This method is used to check button visibility and click it
     */
    public boolean ClickHomePageButton(String type) {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        switch (type.toLowerCase()) {
            case "login":
                ExplicitWaiting.waitForPageLoaded(getWebDriver());
                ExplicitWaiting.waitForSeconds(2);
                if(Element.isVisibleWithoutFailedMsg(signInButton, "Sign In button")){
                    Element.clickAction(signInButton, "Sign In button");
                    return true;
                }
                else {
                    return false;
                }

            case "downloadreport":
                ExplicitWaiting.waitForPageLoaded(getWebDriver());
                CommonMethod.scrollDown(200);

                if (Element.isVisibleWithoutFailedMsg(downloadReportButton, "Download report button")) {
                    Element.clickUsingBy(downloadReportButton, "Download report button");
                    return true;
                }
                // Check for the "See How It Works" button if the first button is not visible
                else if (Element.isVisibleWithoutFailedMsg(seeHowItWorkButton, "See How It Work button")) {
                    Element.clickUsingBy(seeHowItWorkButton, "See How It Work button");
                    return true;
                }
                // If neither button is visible
                return false;

            case "download":
                ExplicitWaiting.waitForPageLoaded(getWebDriver());
                CommonMethod.scrollDown(500);
                if (Element.isVisibleWithoutFailedMsg(downloadButton, "Download now button")) {
                    Element.clickUsingBy(downloadButton, "Download now button");
                    return true;
                }
                else if (Element.isVisibleWithoutFailedMsg(watchNowButton, "Watch now button")) {
                    Element.clickUsingBy(watchNowButton, "Watch now button");
                    return true;
                } else {
                    return false;
                }

            case "scheduledemo":
                ExplicitWaiting.waitForPageLoaded(getWebDriver());
                CommonMethod.scrollDown(200);
                if(Element.isVisibleWithoutFailedMsg(scheduleDemoButton, "Schedule Demo button")){
                    Element.clickUsingBy(scheduleDemoButton, "Schedule Demo button");
                    return true;
                }
                else {
                    return false;
                }

            case "submitlogin":
                ExplicitWaiting.waitForPageLoaded(getWebDriver());
                if(Element.isVisibleWithoutFailedMsg(signInButtonOfLogin, "Sign In button of Login form")){
                    Element.clickUsingBy(signInButtonOfLogin, "Sign In button of Login form");
                    return true;
                }
                else {
                    return false;
                }

            case "home":
                ExplicitWaiting.waitForPageLoaded(getWebDriver());
                if(Element.isVisibleWithoutFailedMsg(logo, "Logo")){
                    Element.clickUsingBy(logo, "Logo");
                    return true;
                }
                else {
                    return false;
                }

            default:
                Reporter.info("Please provide a valid button name");
                return false;
        }
    }

    /*
    * This method is used to verify user is on Homepage
     */
    public boolean verifyHomePageDisplayed(){
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        if(Element.isVisibleWithoutFailedMsg(homepageHeader, "Home page header")){
            return true;
        }
        else {
            return false;
        }
    }

    /*
    * This method is used to redirect to download report page
     */
    public void gotoDownloadReportSection(int attempts) {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        // Base case: limit the number of attempts to avoid infinite recursion
        if (attempts <= 0) {
            System.out.println("Max attempts reached. Download report section not found.");
            return;
        }

        // Scroll down to check for elements
        CommonMethod.scrollDown(2000);

        // Check if the download report section is visible
        if (Element.isVisibleUsingBy(downloadReportSectionHeading, "Download report section")) {
            System.out.println("Download report section found.");
            CommonMethod.scrollUp(100); // Optional: Scroll bit for download report button
        }
        else {
            System.out.println("Download report section not found. Attempting again...");
            gotoDownloadReportSection(attempts - 1); // Recursive call with decremented attempts
        }
    }

    /*
    *This method is used to navigate to page from navigation bar and check if correct page opens
     */
    public boolean navigateAndVerify(String optionText, String pageTitleText) {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        // Construct dynamic XPath using the parameters
        By optionInNavigation = By.xpath("//div[normalize-space()='" + optionText + "']//div[1]");
        ExplicitWaiting.waitForSeconds(1);

        // Click on the navigation option with hover
        if (Element.isVisibleUsingBy(optionInNavigation, optionText)) {
            ExplicitWaiting.waitForSeconds(1);
            // Hover over the navigation option to keep the dropdown open
            Element.MouseHoverAndPause(optionInNavigation, 1);

            // Refined XPath for the page navigation
            By pageOfNavigation = By.xpath("//a[normalize-space()='" + pageTitleText + "']");
            ExplicitWaiting.waitForSeconds(1);

            // Check all elements that match the pageOfNavigation XPath
            List<WebElement> elements = getWebDriver().findElements(pageOfNavigation);
            boolean foundVisibleElement = false;

            for (WebElement element : elements) {
                ExplicitWaiting.waitForSeconds(1);
                if (element.isDisplayed()) {
                    ExplicitWaiting.waitForSeconds(1);
                    System.out.println("Attempting to click on: " + element.getText());
                    element.click();
                    ExplicitWaiting.waitForPageLoaded(getWebDriver());
                    ExplicitWaiting.waitForSeconds(1);

                    // Get the title of the opened page
                    String pageTitle = getWebDriver().getTitle();
                    ExplicitWaiting.waitForSeconds(1);
                    if(optionText.equalsIgnoreCase("Resources")){
                        ExplicitWaiting.waitForSeconds(1);
                        return Element.isVisibleUsingBy(resourcesPageHeading, "Resources page Heading");
                    }
                    else {
                        ExplicitWaiting.waitForSeconds(1);
                        // Check if the title contains the expected text
                        return pageTitle.contains(pageTitleText);
                    }
                }
            }

            // If no visible elements were found
            if (!foundVisibleElement) {
                Reporter.info(pageTitleText + " is not available. " + optionText + " Please provide a valid page option.");
                return false;
            }

        } else {
            Reporter.info(optionText + " is not available in the Navigation bar. Please provide a valid option.");
            return false;
        }

        return false; // Fallback return in case nothing matches
    }

    /*
    *This method is used to verify resources page opens or not
     */
    public boolean verifyResourcesPage() {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        Element.clickUsingBy(resourcesOptionInNavigation, "Resources Option");
        if(Element.isVisibleUsingBy(resourcesPageHeading, "Resources page heading"))
        {
            return true;
        }
        else{
            return false;
        }
    }
}
