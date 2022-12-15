package com.utility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.appium.java_client.android.AndroidDriver;

public class ExtentReporter {

	static ExtentHtmlReporter htmlReporter = null;
	static ExtentReports extent = null;
	static ExtentTest test = null;
	public static String reportPath = null;

	// Create new reports for each execution and creates the screenshots and
	// downloads folders
	public static void createReport() {

		// reportPath =
		// System.getProperty("user.dir")+"\\reports\\ExecutionReport_"+getCurrentDateTime().replace("/",
		// "_").replace(":", "_").replace("[", "").replace("] ", "").replace("
		// ", "_");
		reportPath = "C:\\test" + "\\reports\\ExecutionReport_" + getCurrentDateTime().replace("/", "_")
				.replace(":", "_").replace("[", "").replace("] ", "").replace(" ", "_");
		new File(reportPath).mkdir();
		new File(reportPath + "\\screenshots").mkdir();
		new File(reportPath + "\\downloads").mkdir();
		htmlReporter = new ExtentHtmlReporter(reportPath + "\\extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}

	// Identify each and every test case
	public static void generateReports(String string) {
		test = extent.createTest(string.toString());
		// test.log(Status.INFO, "This step shows usage of log(status,
		// details)");
		// extent.flush();
	}

	// write to report at any given place using this method
	public static void writeToReport(Status state, String text) {
		test.log(state, text);
	}

	// Identify the end result of the test case and log the status of the test
	// case at each and every test case end
	public static void endOfTest(ITestResult result, EventFiringWebDriver driver) {
		if (result.getStatus() == ITestResult.FAILURE) {
			Log.info(getCurrentDateTime() + "Test Failed: " + result.getThrowable());
			try {
				test.fail("Test Failed: " + result.getThrowable(), MediaEntityBuilder
						.createScreenCaptureFromPath(takeScreenshot(SingletonClass.getAndroidDriver())).build());

			} catch (IOException e) {
				test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
				e.printStackTrace();
			}
		}

		else if (result.getStatus() == ITestResult.SKIP) {
			Log.info(getCurrentDateTime() + "Test Skipped: " + result.getThrowable());
			test.log(Status.SKIP, "Test Case skipped");
		}

		else {
			Log.info(getCurrentDateTime() + "Test Passed");
			test.log(Status.PASS, "Test Case Passed");
		}

		extent.flush();
	}

	// capture the screenshot and save the screenshot inside the report folder
	// and return the screenshot name
	private static String takeScreenshot(AndroidDriver androidDriver) {
		String screenShotName = getCurrentDateTime().replace("/", "_").replace(":", "_").replace("[", "")
				.replace("] ", "").replace(" ", "_") + UUID.randomUUID().toString();
		String path = reportPath + "\\screenshots\\" + screenShotName + ".jpeg";
		String retPath = ".\\screenshots\\" + screenShotName + ".jpeg";
		try {
			// File screenshotFile =
			// ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// FileUtils.copyFile(screenshotFile, new File(path));
			WebDriver wd = new Augmenter().augment(androidDriver);
			File f = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(f, new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			test.log(Status.SKIP, "Can not take screenshot: " + e);
		}
		return retPath;
	}

	// write to the report given status and if the screenshot is needed it also
	// captured to the report
	public static void logReport(Status status, String message, boolean takeScreenshot) {

		if (takeScreenshot) {
			try {
				test.log(status, message, MediaEntityBuilder
						.createScreenCaptureFromPath(takeScreenshot(SingletonClass.getAndroidDriver())).build());
			} catch (IOException e) {
				test.log(status, "Unable to capture screenshot. Logging Provided information: " + message);
			}
		} else {
			test.log(status, message);
		}
		message = message.replace(
				"<span onclick=\"$(this).next().toggle();\">Show/Hide Stacktrace</span><p style=\"display: none;\">",
				"");
		Log.info(getCurrentDateTime() + message);
	}

	// return the current date and time
	public static String getCurrentDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return "[" + (dtf.format(now)) + "] ";
	}

}
