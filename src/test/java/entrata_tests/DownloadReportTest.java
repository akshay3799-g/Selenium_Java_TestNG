package entrata_tests;

import config.Reporter;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageFactory.PageFactoryInitializer;
import utils.ExplicitWaiting;
import utils.GenericMethods;
import utils.RandomGenerator;

public class DownloadReportTest extends PageFactoryInitializer {
    String firstName = RandomGenerator.GenerateRandomCapitalLetters(5);
    String lastName = RandomGenerator.GenerateRandomCapitalLetters(5);
    String workEmail = RandomGenerator.GenerateRandomEMAILIDs("malinator.com");
    String companyName = RandomGenerator.GenerateRandomCapitalLetters(5);
    String phoneNumber = RandomGenerator.GenerateRandomNumber(10);

    @BeforeClass
    void loginAsBuyer() throws Exception {
        GenericMethods.getURL();
        ExplicitWaiting.waitForPageLoaded(getWebDriver());
        homePage().acceptCookies();
    }

    @Test(priority = 1, description = "Verify the download report functionality")
    public void verifyDownloadReport() throws Exception {

        Reporter.info("Verify user is able to scroll toward download report section");
        homePage().gotoDownloadReportSection(5);

        Reporter.info("Verify user is able to click on download report button");
        Assertions.assertThat(homePage().ClickHomePageButton("downloadReport")).isTrue();

        Reporter.info("Verify user redirects to new tab");
        Assertions.assertThat(reportPage().verifyDownloadReportPage()).isTrue();

        Reporter.info("Fill the details to download report");
        reportPage().enterTextByPlaceholder("First Name", firstName);
        reportPage().enterTextByPlaceholder("Last Name", lastName);
        reportPage().enterTextByPlaceholder("Work Email Address", workEmail);
        reportPage().enterTextByPlaceholder("Company Name", companyName);
        reportPage().enterTextByPlaceholder("Phone Number", phoneNumber);
        reportPage().selectDropdownOption("11 - 100");
        reportPage().selectCheckbox();

        Reporter.info("As it's live site not submitting form by clicking download now button");
        GenericMethods.closeCurrentWindow();

    }

    @Test(priority = 2, description = "Verify the error shown when submit form with empty fields")
    public void verifyDownloadReportInvalidData() throws Exception {

        Reporter.info("Verify user is able to scroll toward download report section");
        Assertions.assertThat(homePage().ClickHomePageButton("home")).isTrue();
        Assertions.assertThat(homePage().verifyHomePageDisplayed()).isTrue();
        homePage().gotoDownloadReportSection(5);

        Reporter.info("Verify user is able to click on download report button");
        Assertions.assertThat(homePage().ClickHomePageButton("downloadReport")).isTrue();

        Reporter.info("Verify user redirects to new tab");
        Assertions.assertThat(reportPage().verifyDownloadReportPage()).isTrue();

        Reporter.info("Click download without Filling the details to download report");
        reportPage().enterTextByPlaceholder("First Name", firstName);
        Assertions.assertThat(homePage().ClickHomePageButton("download")).isTrue();

        Reporter.info("Verify error shown for empty field");
        Assertions.assertThat(reportPage().verifyErrorForEmptyField()).isTrue();
    }
}
