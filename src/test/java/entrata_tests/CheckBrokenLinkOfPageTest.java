package entrata_tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageFactory.PageFactoryInitializer;
import utils.ExplicitWaiting;
import utils.GenericMethods;

public class CheckBrokenLinkOfPageTest extends PageFactoryInitializer {
    @BeforeClass
    void loginAsBuyer() throws Exception {
        GenericMethods.getURL();
        ExplicitWaiting.waitForPageLoaded(getWebDriver());
        homePage().acceptCookies();
    }

    @Test(priority = 1, description = "Verify the broken links from page")
    public void verifyBrokenLinkFromPage() throws Exception {
        ExplicitWaiting.waitForPageLoaded(getWebDriver());
        linksChecker().checkBrokenLinks();
    }

}
