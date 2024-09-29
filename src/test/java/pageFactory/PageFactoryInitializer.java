package pageFactory;

import base.WebDriverFactory;
import org.openqa.selenium.support.PageFactory;
import pages.BrokenLinksChecker;
import pages.DownloadReportPage;
import pages.HomePage;
import pages.LoginPage;


public class PageFactoryInitializer extends WebDriverFactory {

    /**
     * @return This method return HomePage
     */
    public HomePage homePage() {
        return PageFactory.initElements(getWebDriver(), HomePage.class);
    }

    /**
     * @return This method return LoginPage
     */
    public LoginPage loginPage() {
        return PageFactory.initElements(getWebDriver(), LoginPage.class);
    }

    /**
     * @return This method return Download Report Page
     */
    public DownloadReportPage reportPage() {
        return PageFactory.initElements(getWebDriver(), DownloadReportPage.class);
    }

    /**
     * @return This method return Broken Links Checker Page
     */
    public BrokenLinksChecker linksChecker() {
        return PageFactory.initElements(getWebDriver(), BrokenLinksChecker.class);
    }

}