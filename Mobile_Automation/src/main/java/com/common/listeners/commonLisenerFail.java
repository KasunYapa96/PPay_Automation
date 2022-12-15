package com.common.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.chivelab.pageObjAndroid.BasePageObject;
import com.utility.Log;
import com.utility.SingletonClass;

public class commonLisenerFail implements ITestListener {
	@SuppressWarnings("unused")
	private String suiteName;
	private String testClassName;
	private String testMethodName;

	@Override
	public void onFinish(ITestContext arg0) {
		Log.info("\n++++++++++++++ Finished running Test ++++++++++++++");

	}

	@Override
	public void onStart(ITestContext arg0) {
		suiteName = arg0.getSuite().getName();
		Log.info("\n++++++++++++++ Started running Test ++++++++++++++");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	@Override
	public void onTestFailure(ITestResult arg0) {

		Boolean screenShotsOn = Boolean.valueOf(SingletonClass.getFrameWorkProp("screeShots"));

		if (screenShotsOn) {
			String timeStamp;
			File screenShotName;
			File scrFile = ((TakesScreenshot) SingletonClass.getAndroidDriver()).getScreenshotAs(OutputType.FILE);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			screenShotName = new File(SingletonClass.getFrameWorkProp("screenShotpath") + timeStamp + ".png");
			try {
				FileUtils.copyFile(scrFile, screenShotName);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String filePath = screenShotName.toString();
			String path = "<img src=" + filePath + " alt=\"\" height=\"450\" width=\"450\">";
			Reporter.log(path);

		} else {

		}

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		testClassName = arg0.getTestClass().getName();
		testMethodName = arg0.getMethod().getMethodName();

		Log.info("\n++++ Started running:" + testClassName + ":" + testMethodName + " ++++");

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		try {
			Log.info("\n\t\t\t Test Passed : " + arg0.getInstanceName() + " at " + BasePageObject.getTimeStampValue()
					+ " \n");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
