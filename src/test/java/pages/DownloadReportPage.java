package pages;

import config.Reporter;
import org.openqa.selenium.WebElement;
import pageFactory.PageFactoryInitializer;
import org.openqa.selenium.By;
import utils.Element;
import utils.ExplicitWaiting;
import utils.GenericMethods;
import org.openqa.selenium.support.ui.Select;
public class DownloadReportPage extends PageFactoryInitializer {

    private static By downloadReportPageHeader = By.xpath("//h1[normalize-space()='The Total Economic Impact™ of Entrata']");
    private static By watchReportPageHeader = By.xpath("//h2[normalize-space()='The Total Economic Impact™ of Entrata']");
    private static By emptyFieldError = By.xpath("//div[normalize-space()='This field is required.']");
    private static By scheduleDemoCheckbox = By.xpath("//input[@name='demoRequestCheckbox']");

    /*
    * This method is used to click and enter value in textboxes
     */
    public static void enterTextByPlaceholder(String placeholder, String value) {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        // Create a dynamic XPath using the provided placeholder
        By dynamicInputTextBox = By.xpath("//input[@placeholder='" + placeholder + "']");

        // Click the input text box
        Element.clickUsingBy(dynamicInputTextBox, placeholder);

        // Enter the specified value
        Element.enterText(dynamicInputTextBox, value, placeholder);
    }

    /*
    * verify download report page/tab opens
     */
    public static boolean verifyDownloadReportPage() {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        if (Element.isListVisibleUsingByWithoutFailedMSG(downloadReportPageHeader, "Download Report page Heading")) {
            return true;
        }
        else if (Element.isListVisibleUsingByWithoutFailedMSG(watchReportPageHeader, "Watch Report page Heading")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean verifyDownloadReportTab() {
        GenericMethods.switchToNewlyOpenedWindow();
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

//        ExplicitWaiting.waitForSeconds(5);
        if (Element.isListVisibleUsingByWithoutFailedMSG(downloadReportPageHeader, "Download Report page Heading")) {
            return true;
        }
        else if (Element.isListVisibleUsingByWithoutFailedMSG(watchReportPageHeader, "Watch Report page Heading")) {
            return true;
        } else {
            return false;
        }
    }

    /*
    * verify error for empty/blank fields
     */
    public static boolean verifyErrorForEmptyField() {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());
        if(Element.isListVisibleUsingByWithoutFailedMSG(emptyFieldError, "Empty Field Error")){
            return true;
        }
        else {
            return false;
        }
    }

    /*
    * This method is used to select option from Unit dropdown
     */
    public static void selectDropdownOption(String visibleText) {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        WebElement unitCountDropdown = getWebDriver().findElement(By.id("Unit_Count__c"));
        ExplicitWaiting.waitForSeconds(5);

        // Create a Select object
        Select select = new Select(unitCountDropdown);

        // Select an option by visible text
        select.selectByVisibleText(visibleText);

        // Alternatively, we can select by value or index:
        // select.selectByValue("1 - 10");
        // select.selectByIndex(2); // This would select the third option (index starts at 0)
    }

    /*
    * Accept checkbox of schedule demo for entrata product
     */
    public static void selectCheckbox(){
        ExplicitWaiting.waitForPageLoaded(getWebDriver());

        Element.clickAction(scheduleDemoCheckbox, "Schedule Demo Checkbox");
    }

    /*
    *This method is used to verify which page opened on new tab
     */
    public static String verifyOpenedPage(){
        if (Element.isVisibleWithoutFailedMsg(watchReportPageHeader, "Watch Report page Heading")) {
            return "Watch";
        } else if (Element.isVisibleWithoutFailedMsg(downloadReportPageHeader, "Download Report page Heading")) {
            return "Download";
        } else {
            return "Not Valid";
        }
    }
}
