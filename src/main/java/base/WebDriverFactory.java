package base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import static base.BrowserFactory.createDriver;

public class WebDriverFactory {
	public static ThreadLocal<WebDriver> wd = new ThreadLocal<WebDriver>();
	public static Logger log = null;
	Constants con;

	/**
	 * This method read config file and initialize it
	 */
	@BeforeSuite
	public void setupConfig() {
		con = new Constants();
		con.readConfigFile();
		con.initConfig();
	}

	/**
	 * This is method initialize the webdriver and read the log4j file
	 * 
	 * @throws Exception
	 */
	@Parameters({ "browser" })
	@BeforeClass(alwaysRun = true)
	public void beforeMethod(String browser) throws Exception {
		System.out.println("Browser: " + browser);
		WebDriver driver = createDriver(browser);
		setWebDriver(driver);
		log = Logger.getLogger(getClass());
		String path = System.getProperty("user.dir");
		PropertyConfigurator.configure(path + "/src/main/resources/log4j.properties");
	}

	/**
	 * Sets the given WebDriver as thread-local variable value
	 * 
	 * @param driver
	 */
	public void setWebDriver(WebDriver driver) {
		wd.set(driver);
	}

	/**
	 * Returns the webdriver instance using threadLocal
	 * 
	 * @return WebDriver
	 */
	public static WebDriver getWebDriver() {
		return wd.get();
	}

	/**
	 * This method quit the driver after class
	 * 
	 * @throws Exception
	 */
	@AfterClass(alwaysRun = true)
	public void afterMethod() throws Exception {
		getWebDriver().quit();
	}
}
