package entrata_tests;

import config.Reporter;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageFactory.PageFactoryInitializer;
import utils.ExplicitWaiting;
import utils.GenericMethods;

public class HomePageTest extends PageFactoryInitializer {

    @BeforeMethod
    void loginAsBuyer() throws Exception {
        GenericMethods.getURL();
        ExplicitWaiting.waitForPageLoaded(getWebDriver());
        homePage().acceptCookies();
    }

    @Test(priority = 1, description = "Verify the pages navigation")
    public void verifyHomePageNavigations() throws Exception {

        Reporter.info("Verify user is on home page");
        Assertions.assertThat(homePage().verifyHomePageDisplayed()).isTrue();

        Reporter.info("Verify user redirects page under products");
        Assertions.assertThat(homePage().navigateAndVerify("Products", "Reporting")).isTrue();

        Reporter.info("Navigate back to home page");
        Assertions.assertThat(homePage().ClickHomePageButton("home")).isTrue();

        Reporter.info("Verify user redirects page under Solutions");
        Assertions.assertThat(homePage().navigateAndVerify("Solutions", "Student")).isTrue();

        Reporter.info("Navigate back to home page");
        Assertions.assertThat(homePage().ClickHomePageButton("home")).isTrue();

        Reporter.info("Verify user redirects page under Resources");
        Assertions.assertThat(homePage().navigateAndVerify("Resources", "Guides")).isTrue();

        Reporter.info("Navigate back to home page");
        Assertions.assertThat(homePage().ClickHomePageButton("home")).isTrue();
        Assertions.assertThat(homePage().verifyHomePageDisplayed()).isTrue();
    }
}
