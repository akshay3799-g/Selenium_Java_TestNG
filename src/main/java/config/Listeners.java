package config;

import base.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import utils.Element;
import utils.GenericMethods;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

public class Listeners implements ITestListener, IAnnotationTransformer, IRetryAnalyzer {

	ExtentReports extent;
	ExtentTest test;
	Reporter report;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	int counter = 0;
	int retryLimit = 0;


	@Override
	public void onStart(ITestContext context) {
		Constants.testName = context.getName();
		System.out.println("onStart");
		try {
			report = new Reporter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		extent = Reporter.extent;
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent
				.createTest(result.getInstance().getClass().getSimpleName() + "_" + result.getMethod().getMethodName());
		getExtentTest().set(test);
		System.out.println("OnTestStart - " + result.getInstance().getClass().getSimpleName() + "_"
				+ result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess - " + result.getInstance().getClass().getSimpleName() + "_"
				+ result.getMethod().getMethodName());
		Reporter.pass(result.getMethod().getMethodName() + " is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure - " + result.getInstance().getClass().getSimpleName() + "_"
				+ result.getMethod().getMethodName());
		String methodName = result.getMethod().getMethodName();
		String path = "";
		try {
			path = Element.takeScreenshot(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			getExtentTest().get().fail("<b><font color=red> Screenshot of failure</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (Exception ex) {
			getExtentTest().get().fail("Test Failed!");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("cause of skip " +result.getSkipCausedBy());
		System.out.println("onTestSkipped");
		Reporter.skip(result.getMethod().getMethodName() + " is Skipped");
		// extent.removeTest(test);
	}

	@Override
	public void onFinish(ITestContext context) {
		// extent.flush();
		Set<ITestResult> passedTestCasesCount = context.getPassedTests().getAllResults();
		Constants.passedTestCasesCount = String.valueOf(passedTestCasesCount.size());

		Set<ITestResult> failedTestCasesCount = context.getFailedTests().getAllResults();
		Constants.failedTestCasesCount = String.valueOf(failedTestCasesCount.size());

		Set<ITestResult> skippedTests = context.getSkippedTests().getAllResults();
		Constants.skippedTestCasesCount = String.valueOf(skippedTests.size());

		Constants.totalTestCasesCount = String
				.valueOf(passedTestCasesCount.size() + failedTestCasesCount.size() + skippedTests.size());

		report.export();
	}

	public static ThreadLocal<ExtentTest> getExtentTest() {
		return extentTest;
	}

	public static void setExtentTest(ThreadLocal<ExtentTest> extentTest) {
		Listeners.extentTest = extentTest;
	}

	@Override
	public boolean retry(ITestResult result) {
		// Reporter.info(result.getMethod().getMethodName() + " is retrying");
		if (counter < retryLimit) {
			counter++;
			return true;
		}
		return false;
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(Listeners.class);
	}
}
