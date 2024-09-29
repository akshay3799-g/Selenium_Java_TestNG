package pages;

import org.openqa.selenium.WebElement;
import pageFactory.PageFactoryInitializer;
import org.openqa.selenium.By;
import utils.Element;
import utils.ExplicitWaiting;
import utils.GenericMethods;
import org.openqa.selenium.support.ui.Select;
public class DownloadReportPage extends PageFactoryInitializer {

    private By downloadReportPageHeader = By.xpath("//h1[normalize-space()='The Total Economic Impact™ of Entrata']");
    private By emptyFieldError = By.xpath("//div[normalize-space()='This field is required.']");
    private WebElement unitCountDropdown = getWebDriver().findElement(By.id("Unit_Count__c"));
    private By scheduleDemoCheckbox = By.xpath("//input[@name='demoRequestCheckbox']");

    /*
    * This method is used to click and enter value in textboxes
     */
    public void enterTextByPlaceholder(String placeholder, String value) {
        ExplicitWaiting.waitForSeconds(10);
        // Create a dynamic XPath using the provided placeholder
        By dynamicInputTextBox = By.xpath("//input[@placeholder='" + placeholder + "']");

        // Click the input text box
        Element.clickUsingBy(dynamicInputTextBox, placeholder);
        ExplicitWaiting.waitForSeconds(10);

        // Enter the specified value
        Element.enterText(dynamicInputTextBox, value, placeholder);
    }

    /*
    * verify download report page/tab opens
     */
    public boolean verifyDownloadReportPage() {
        GenericMethods.switchToNewlyOpenedWindow();
        ExplicitWaiting.waitForSeconds(10);
        if(Element.isVisibleUsingBy(downloadReportPageHeader, "Download Report page Heading")){
            return true;
        }
        else {
            return false;
        }
    }

    /*
    * verify error for empty/blank fields
     */
    public boolean verifyErrorForEmptyField() {
        ExplicitWaiting.waitForSeconds(10);
        if(Element.isVisibleUsingBy(emptyFieldError, "Empty Field Error")){
            return true;
        }
        else {
            return false;
        }
    }

    /*
    * This method is used to select option from Unit dropdown
     */
    public void selectDropdownOption(String visibleText) {
        ExplicitWaiting.waitForSeconds(10);
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
    public void selectCheckbox(){
        ExplicitWaiting.waitForSeconds(10);
        Element.clickAction(scheduleDemoCheckbox, "Schedule Demo Checkbox");
    }
}
