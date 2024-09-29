package entrata_tests;

import config.Reporter;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageFactory.PageFactoryInitializer;
import utils.ExplicitWaiting;
import utils.GenericMethods;
import utils.RandomGenerator;

public class LoginTest extends PageFactoryInitializer {

    String username = RandomGenerator.GenerateRandomEMAILIDs("malinator.com");
    String password = RandomGenerator.GenerateRandomCapitalLetters(5);

    @BeforeClass
    void loginAsBuyer() throws Exception {
        GenericMethods.getURL();
        ExplicitWaiting.waitForPageLoaded(getWebDriver());
        homePage().acceptCookies();
    }

    @Test(priority = 1, description = "Verify login as Property Manager")
    public void verifyDownloadReport() throws Exception {

        Reporter.info("Verify user is on homepage");
        Assertions.assertThat(homePage().verifyHomePageDisplayed()).isTrue();

        Reporter.info("Verify user is able to click sign In button");
        Assertions.assertThat(homePage().ClickHomePageButton("login")).isTrue();

        Reporter.info("Verify user redirects to Sign In page");
        Assertions.assertThat(loginPage().verifyUserRedirectsToCorrectPage("Login")).isTrue();

        Reporter.info("Verify user redirects to property manager login page");
        loginPage().openLoginPage("Property");
        Assertions.assertThat(loginPage().verifyUserRedirectsToCorrectPage("Property")).isTrue();

        Reporter.info("Verify error shown for Invalid credentials");
        reportPage().enterTextByPlaceholder("Username", username);
        reportPage().enterTextByPlaceholder("Password", password);
        Assertions.assertThat(homePage().ClickHomePageButton("submitlogin")).isTrue();
        Assertions.assertThat(loginPage().verifyErrorForInvalidLogin()).isTrue();

        Reporter.info("As it's live site not submitting form with Valid credentials");
        loginPage().goToWebsite(3);

    }

    @Test(priority = 2, description = "Verify the login as Resident User")
    public void verifyDownloadReportInvalidData() throws Exception {

        Reporter.info("Verify user is on homepage");
        Assertions.assertThat(homePage().ClickHomePageButton("home")).isTrue();
        Assertions.assertThat(homePage().verifyHomePageDisplayed()).isTrue();

        Reporter.info("Verify user is able to click sign In button");
        Assertions.assertThat(homePage().ClickHomePageButton("login")).isTrue();

        Reporter.info("Verify user redirects to Sign In page");
        Assertions.assertThat(loginPage().verifyUserRedirectsToCorrectPage("Login")).isTrue();

        Reporter.info("Verify user redirects to Resident user login page");
        loginPage().openLoginPage("Resident");
        Assertions.assertThat(loginPage().verifyUserRedirectsToCorrectPage("Resident")).isTrue();

        Reporter.info("Verify App and Website Login options shown on page");
        Assertions.assertThat(loginPage().verifyUserRedirectsToCorrectPage("App")).isTrue();
        Assertions.assertThat(loginPage().verifyUserRedirectsToCorrectPage("Website")).isTrue();

    }
}
