package entrata_tests;

import config.Reporter;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageFactory.PageFactoryInitializer;
import utils.ExplicitWaiting;
import utils.GenericMethods;

public class HomePageTest extends PageFactoryInitializer {

    @BeforeClass
    void loginAsBuyer() throws Exception {
        GenericMethods.getURL();
        ExplicitWaiting.waitForPageLoaded(getWebDriver());
        homePage().acceptCookies();
    }

    @Test(priority = 1, description = "Verify the pages navigation")
    public void verifyHomePageNavigations() throws Exception {

        Reporter.info("Verify user is on home page");
        Assertions.assertThat(homePage().verifyHomePageDisplayed()).isTrue();

        Reporter.info("Verify user redirects to schedule demo page");
        Assertions.assertThat(homePage().ClickHomePageButton("scheduledemo")).isTrue();
        Assertions.assertThat(loginPage().verifyUserRedirectsToCorrectPage("scheduledemo")).isTrue();

        Reporter.info("Navigate back to home page");
        Assertions.assertThat(homePage().ClickHomePageButton("home")).isTrue();

        Reporter.info("Verify user redirects page under products");
        homePage().navigateAndVerify("Products", "Rewards");

        Reporter.info("Navigate back to home page");
        Assertions.assertThat(homePage().ClickHomePageButton("home")).isTrue();

        Reporter.info("Verify user redirects page under Solutions");
        homePage().navigateAndVerify("Solutions", "Student");

        Reporter.info("Navigate back to home page");
        Assertions.assertThat(homePage().ClickHomePageButton("home")).isTrue();

        Reporter.info("Verify user redirects page under Resources");
        Assertions.assertThat(homePage().verifyResourcesPage()).isTrue();
    }
}
