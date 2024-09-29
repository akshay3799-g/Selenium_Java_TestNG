package base;

import com.google.gson.Gson;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
	private static String chromeVersion = "121";

	/**
	 * This method sets up configurations for running browser
	 * 
	 * @param browser
	 * @return WebDriver
	 * @throws Exception
	 */
	static WebDriver createDriver(String browser) throws Exception {
		WebDriver driver;
		String downloadDirectory = System.getProperty("user.dir") + File.separator + "Import_Export" + File.separator
				+ "downloads";
		System.setProperty("webdriver.chrome.whitelistedIps", "");
		switch (browser.toLowerCase()) {
		case "chrome":
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			ChromeOptions chromeOptions = new ChromeOptions();
			Map<String, Object> settings = new HashMap<>();
			Map<String, Object> appState = new HashMap<>();

			appState.put("recentDestinations", new Object[] { new HashMap<String, Object>() {
				{
					put("id", "Save as PDF");
					put("origin", "local");
				}
			} });
			appState.put("selectedDestinationId", "Save as PDF");
			appState.put("version", 2);
			settings.put("appState", appState);
			String jsonSettings = new Gson().toJson(settings);

			chromePrefs.put("printing.print_preview_sticky_settings", jsonSettings);
			chromeOptions.setBrowserVersion(chromeVersion);
			chromePrefs.put("download.default_directory", downloadDirectory);
			chromePrefs.put("download.prompt_for_download", false);
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			chromeOptions.addArguments("--window-size=1600,900");
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("--kiosk-printing");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
			break;
		case "chrome_headless":
			HashMap<String, Object> headlessChromePrefs = new HashMap<String, Object>();
			ChromeOptions headlessChromeOption = new ChromeOptions();
			headlessChromeOption.setBrowserVersion(chromeVersion);
//			System.out.println("BrowserPath "
//					+ SeleniumManager.getInstance().getDriverPath(headlessChromeOption, false).getBrowserPath());
//			System.out.println("DriverPath "
//					+ SeleniumManager.getInstance().getDriverPath(headlessChromeOption, false).getDriverPath());
			DesiredCapabilities capabilities = new DesiredCapabilities();
			headlessChromeOption.addArguments("--headless=new");
			headlessChromeOption.addArguments("--disable-gpu");
			headlessChromeOption.addArguments("--disable-dev-shm-usage");
			headlessChromeOption.addArguments("--no-sandbox");
			headlessChromeOption.addArguments("--disable-infobars");
			headlessChromeOption.addArguments("disable-popup-blocking");
			headlessChromeOption.addArguments("--remote-allow-origins=*");
			headlessChromeOption.addArguments("--kiosk-printing");
			headlessChromeOption.addArguments("--window-size=1600,900");
			headlessChromePrefs.put("download.default_directory", downloadDirectory);
			headlessChromePrefs.put("download.prompt_for_download", false);
			headlessChromeOption.setExperimentalOption("prefs", headlessChromePrefs);
			capabilities.setCapability(ChromeOptions.CAPABILITY, headlessChromeOption);
			headlessChromeOption.merge(capabilities);
			driver = new ChromeDriver(headlessChromeOption);
			break;
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.download.dir", downloadDirectory);
			firefoxProfile.setPreference("browser.download.folderList", 2);
			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/csv,application/java-archive, application/x-msexcel,application/excel,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/vnd.microsoft.portable-executable");
			firefoxProfile.setPreference("dom.disable_open_during_load", false);
			firefoxOptions.setProfile(firefoxProfile);
			driver = new FirefoxDriver(firefoxOptions);
			driver.manage().window().maximize();
			break;
		case "firefox_headless":
			FirefoxOptions headlessFirefoxOptions = new FirefoxOptions();
			headlessFirefoxOptions.addArguments("--headless");
			driver = new FirefoxDriver(headlessFirefoxOptions);
			break;
		case "edge":
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;
		default:
			throw new NotFoundException("Browser Not Found. Please Provide a Valid Browser");
		}
		return driver;
	}

}
