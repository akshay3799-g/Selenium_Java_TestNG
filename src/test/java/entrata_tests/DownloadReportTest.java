package entrata_tests;

import config.Reporter;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageFactory.PageFactoryInitializer;
import pages.DownloadReportPage;
import utils.ExplicitWaiting;
import utils.GenericMethods;
import utils.RandomGenerator;

public class DownloadReportTest extends PageFactoryInitializer {
    String firstName = RandomGenerator.GenerateRandomCapitalLetters(5);
    String lastName = RandomGenerator.GenerateRandomCapitalLetters(5);
    String workEmail = RandomGenerator.GenerateRandomEMAILIDs("malinator.com");
    String companyName = RandomGenerator.GenerateRandomCapitalLetters(5);
    String phoneNumber = RandomGenerator.GenerateRandomNumber(10);

    @BeforeMethod
    void loginAsBuyer() throws Exception {
        GenericMethods.getURL();
        ExplicitWaiting.waitForPageLoaded(getWebDriver());
        homePage().acceptCookies();
    }

    @Test(priority = 1, description = "Verify the download report functionality")
    public void verifyDownloadReport() throws Exception {

        Reporter.info("Verify user is able to scroll toward download report section");
        Assertions.assertThat(homePage().verifyHomePageDisplayed()).isTrue();
        homePage().gotoDownloadReportSection(5);

        Reporter.info("Verify user is able to click on download report button");
        Assertions.assertThat(homePage().ClickHomePageButton("downloadReport")).isTrue();

        Reporter.info("Verify user redirects to download report page");
        Assertions.assertThat(DownloadReportPage.verifyDownloadReportPage()).isTrue();

        Reporter.info("Click download now button");
        Assertions.assertThat(homePage().ClickHomePageButton("download")).isTrue();

        Reporter.info("Verify user redirects to new tab");
        Assertions.assertThat(DownloadReportPage.verifyDownloadReportTab()).isTrue();

        Reporter.info("Fill the details to download report");
        if(DownloadReportPage.verifyOpenedPage().equalsIgnoreCase("Download")){
            DownloadReportPage.enterTextByPlaceholder("First Name", firstName);
            DownloadReportPage.enterTextByPlaceholder("Last Name", lastName);
            DownloadReportPage.enterTextByPlaceholder("Work Email Address", workEmail);
            DownloadReportPage.enterTextByPlaceholder("Company Name", companyName);
            DownloadReportPage.enterTextByPlaceholder("Phone Number", phoneNumber);
            DownloadReportPage.selectDropdownOption("11 - 100");
            DownloadReportPage.selectCheckbox();
        } else if (DownloadReportPage.verifyOpenedPage().equalsIgnoreCase("Watch")) {
            Reporter.info("Watch how download report page opened");
        }
        else {
            Reporter.info("Invalid page opened on new tab when tries to download report, opened page is:"+getWebDriver().getTitle());
            Assert.fail("Invalid page opened on new tab when tries to download report");
        }


        Reporter.info("As it's live site not submitting form by clicking download now button");
        GenericMethods.closeCurrentWindow();

    }

    @Test(priority = 2, description = "Verify the error shown when submit form with empty fields")
    public void verifyDownloadReportInvalidData() throws Exception {

        Reporter.info("Verify user is able to scroll toward download report section");
        Assertions.assertThat(homePage().verifyHomePageDisplayed()).isTrue();
        homePage().gotoDownloadReportSection(5);

        Reporter.info("Verify user is able to click on download report button");
        Assertions.assertThat(homePage().ClickHomePageButton("downloadReport")).isTrue();

        Reporter.info("Verify user redirects to download report page");
        Assertions.assertThat(DownloadReportPage.verifyDownloadReportPage()).isTrue();

        Reporter.info("Click download now button");
        Assertions.assertThat(homePage().ClickHomePageButton("download")).isTrue();

        Reporter.info("Verify user redirects to new tab");
        Assertions.assertThat(DownloadReportPage.verifyDownloadReportTab()).isTrue();

        Reporter.info("Click download without Filling all details to download report and verify error");
        if(DownloadReportPage.verifyOpenedPage().equalsIgnoreCase("Download")){
            DownloadReportPage.enterTextByPlaceholder("First Name", firstName);
            DownloadReportPage.selectCheckbox();
            Assertions.assertThat(homePage().ClickHomePageButton("download")).isTrue();
            Assertions.assertThat(DownloadReportPage.verifyErrorForEmptyField()).isTrue();
        } else if (DownloadReportPage.verifyOpenedPage().equalsIgnoreCase("Watch")) {
            Reporter.info("Watch how download report page opened");
        }
        else {
            Reporter.info("Invalid page opened on new tab when tries to download report, opened page is:"+getWebDriver().getTitle());
            Assert.fail("Invalid page opened on new tab when tries to download report");
        }
    }
}
