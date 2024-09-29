package config;

import base.Constants;
import base.WebDriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.compress.utils.CharsetNames;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import utils.Element;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporter extends WebDriverFactory {

	public static ExtentReports extent;
	public static ExtentHtmlReporter reporter;
	public static String trade;
	public static String ReportFileName;

	/**
	 * This constructor sets all the configuration for extent report
	 *
	 * @throws Exception
	 */
	public Reporter() throws Exception {
		reporter = new ExtentHtmlReporter(getReportName());
		reporter.config().setEncoding("utf-8");
		reporter.config().setDocumentTitle("AutomationReport");
		reporter.config().setReportName(getReportName());

		extent = new ExtentReports();
		extent.setSystemInfo("Environment", "DEV");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		reporter.config().setTheme(Theme.STANDARD);
		extent.attachReporter(reporter);
	}

	/**
	 * This method is for printing the pass statement and log
	 *
	 * @param description
	 * @return String
	 */
	public static String pass(String description) {
		Listeners.getExtentTest().get().pass(description);
		log.info(description);
		return description;
	}

	/**
	 * This method is for printing the info statement and log
	 *
	 * @param description
	 * @return String
	 */
	public static String info(String description) {
		Listeners.getExtentTest().get().info(description);
		log.info(description);
		return description;
	}

	/**
	 * This method is for printing the error statement and log
	 *
	 * @param description
	 * @return String
	 */
	public static String error(String description) {
		Listeners.getExtentTest().get().error(description);
		log.info(description);
		return description;
	}

	/**
	 * This method is for printing the fail statement and log
	 *
	 * @param description
	 * @return String
	 */
	public static String fail(String description) {
		Listeners.getExtentTest().get().fail(description);
		log.info(description);
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		String methodName = stacktrace[3].getMethodName();
		String path = "";
		try {
			path = Element.takeScreenshot(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Listeners.getExtentTest().get().fail("<b><font color=red> Screenshot of failure</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (Exception ex) {
			Listeners.getExtentTest().get().fail("Test Failed, unable to attach screenshot.");
		}
		return description;
	}

	/**
	 * This method is for printing the skip statement and log
	 *
	 * @param description
	 * @return String
	 */
	public static String skip(String description) {
		Listeners.getExtentTest().get().skip(description);
		log.info(description);
		return description;
	}

	/**
	 * This method is for printing the Ribbon logo on html report
	 */
	public void export() {
		try {
			extent.flush();
			File testReport = new File(System.getProperty("user.dir") + Constants.REPORTPATH + Constants.ReportFileName);
			Document doc = Jsoup.parse(testReport, CharsetNames.UTF_8);
			doc.body()
					.getElementsByAttributeValue("src",
							"https://cdn.rawgit.com/extent-framework/extent-github-cdn/d74480e/commons/img/logo.png")
					.remove();
			doc.body().getElementsByAttributeValue("class", "brand-logo black").append(
					"<img src='https://cdn.prod.website-files.com/66b25cad9398f590ab703ccf/66b25cad9398f590ab703cfc_logo.svg' type='text/javascript'>");
			PrintWriter writer = new PrintWriter(testReport, "UTF-8");
			writer.write(doc.html());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This methods return the name of the report using date
	 *
	 * @return
	 * @throws Exception
	 */
	public String getReportName() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		Constants.ReportFileName = "Test_Report" + "_" + dateFormat.format(date) + ".html";
		return System.getProperty("user.dir") + Constants.REPORTPATH + Constants.ReportFileName;
	}

	/**
	 * This method is for printing the fail statement and log
	 *
	 * @param description
	 * @return String
	 */
	public static void reportAssertFail(String description) {
		Listeners.getExtentTest().get().fail(description);
		log.info(description);
		Assert.fail(description);
	}
}
