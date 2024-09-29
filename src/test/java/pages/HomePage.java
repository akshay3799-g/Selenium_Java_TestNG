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


public class HomePage extends PageFactoryInitializer {

    private By logo = By.xpath("//a[@class='brand w-nav-brand']");
    private By homepageHeader = By.xpath("//div[normalize-space()='Property management software']");
    private By downloadReportSectionHeading = By.xpath("//h3[normalize-space()='Worried about making the switch?']");
    private By downloadReportButton = By.xpath("//div[normalize-space()='Download the Report']");
    private By downloadButton = By.xpath("//div[normalize-space()='Download Now']");
    private By scheduleDemoButton = By.xpath("//div[normalize-space()='Schedule Your Demo']");
    private By signInButton = By.xpath("(//a[normalize-space()='Sign In'])[1]");
    private By signInButtonOfLogin = By.xpath("//button[normalize-space()='Sign In']");
    By resourcesOptionInNavigation = By.xpath("//div[normalize-space()='Resources']//div[1]");
    private By resourcesPageHeading = By.xpath("//h2[normalize-space()='Resource Center']");
    private By acceptCookiesButton = By.xpath("//div[@class='cookie-button-row']//a");

    /*
     * This method is used to click accept cookie button
     */
    public void acceptCookies(){
        ExplicitWaiting.waitForSeconds(10);
        Element.clickUsingBy(acceptCookiesButton, "Accept cookies button");
    }

    /*
     * This method is used to check button visibility and click it
     */
    public boolean ClickHomePageButton(String type) {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());
        ExplicitWaiting.waitForSeconds(10);
        switch (type.toLowerCase()) {
            case "login":
                if(Element.isVisibleUsingBy(signInButton, "Sign In button")){
                    ExplicitWaiting.waitForSeconds(2);
                    Element.clickUsingBy(signInButton, "Sign In button");
                    return true;
                }
                else {
                    return false;
                }

            case "downloadreport":
                if(Element.isVisibleUsingBy(downloadReportButton, "Download report button")){
                    ExplicitWaiting.waitForSeconds(2);
                    Element.clickUsingBy(downloadReportButton, "Download report button");
                    return true;
                }
                else {
                    return false;
                }

            case "download":
                if(Element.isVisibleUsingBy(downloadButton, "Download now button")){
                    ExplicitWaiting.waitForSeconds(2);
                    Element.clickUsingBy(downloadButton, "Download now button");
                    return true;
                }
                else {
                    return false;
                }

            case "scheduledemo":
                if(Element.isVisibleUsingBy(scheduleDemoButton, "Schedule Demo button")){
                    ExplicitWaiting.waitForSeconds(2);
                    Element.clickUsingBy(scheduleDemoButton, "Schedule Demo button");
                    return true;
                }
                else {
                    return false;
                }

            case "submitlogin":
                if(Element.isVisibleUsingBy(signInButtonOfLogin, "Sign In button of Login form")){
                    ExplicitWaiting.waitForSeconds(2);
                    Element.clickUsingBy(signInButtonOfLogin, "Sign In button of Login form");
                    return true;
                }
                else {
                    return false;
                }

            case "home":
                if(Element.isVisibleUsingBy(logo, "Logo")){
                    ExplicitWaiting.waitForSeconds(2);
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
        ExplicitWaiting.waitForSeconds(10);
        if(Element.isVisibleUsingBy(homepageHeader, "Home page header")){
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
        // Base case: limit the number of attempts to avoid infinite recursion
        if (attempts <= 0) {
            System.out.println("Max attempts reached. Download report section not found.");
            return;
        }

        ExplicitWaiting.waitForSeconds(10);
        CommonMethod.scrollDown(10000);
        ExplicitWaiting.waitForSeconds(5);

        if (Element.isVisibleUsingBy(downloadReportSectionHeading, "Download report section")) {
            CommonMethod.scrollUp(100);
        } else {
            ExplicitWaiting.waitForSeconds(10);
            gotoDownloadReportSection(attempts - 1); // Recursive call with decremented attempts
        }
    }

    /*
    *This method is used to navigate to page from navigation bar and check if correct page opens
     */
    public boolean navigateAndVerify(String optionText, String pageTitleText) {
        ExplicitWaiting.waitForSeconds(10);
        // Construct dynamic XPath using the parameters
        By optionInNavigation = By.xpath("//div[normalize-space()='" + optionText + "']//div[1]");

        // Click on the navigation option
        if(Element.isVisibleUsingBy(optionInNavigation, optionText)){
            ExplicitWaiting.waitForSeconds(10);
            Element.clickUsingBy(optionInNavigation, optionText);

            By pageOfNavigation = By.xpath("//a[normalize-space()='" + pageTitleText + "']");

            // Click on the page of selected navigation option
            if(Element.isVisibleUsingBy(pageOfNavigation, pageTitleText)){
                ExplicitWaiting.waitForSeconds(10);
                Element.clickUsingBy(pageOfNavigation, pageTitleText);
                ExplicitWaiting.waitForSeconds(10);

                // Get the title of the opened page
                String pageTitle = getWebDriver().getTitle();

                // Check if the title contains the expected text
                return pageTitle.contains(pageTitleText);
            }
            else {
                Reporter.info(pageTitleText + "is not available"+ optionText + " Please provide valid page option.");
                return false;
            }
        }
        else {
            Reporter.info(optionText + "is not available in Navigation bar, Please provide valid option.");
            return false;
        }
    }

    /*
    *This method is used to verify resources page opens or not
     */
    public boolean verifyResourcesPage() {
        ExplicitWaiting.waitForSeconds(10);
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
