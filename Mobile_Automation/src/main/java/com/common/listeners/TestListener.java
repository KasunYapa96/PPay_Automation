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

import com.relevantcodes.extentreports.LogStatus;
import com.utility.Log;
import com.utility.SingletonClass;
import com.utils.ExtentReports.ExtentTestManager;
import com.utils.ExtentReports.ExtentManager;


public class TestListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", SingletonClass.getAndroidDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        //Do tier down operations for extentreports reporting!
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        //Start operation for extentreports.
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(),"");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Started : "+getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //Extentreports log operation for passed tests.
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed : "+getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String timeStamp;
		File screenShotName;
		File scrFile = ((TakesScreenshot) SingletonClass.getAndroidDriver()).getScreenshotAs(OutputType.FILE);
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		screenShotName = new File(SingletonClass.getFrameWorkProp("screenShotpath") + timeStamp + ".png");
		try {
			FileUtils.copyFile(scrFile, screenShotName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String filePath = screenShotName.toString().replace("ExtentReports\\", ""); //path replaced due to image load in html report.
        //Extentreports log and screenshot operations for failed tests.
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Fail : "+getTestMethodName(iTestResult)+" : "+iTestResult.getThrowable().getMessage());
        ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed",
                ExtentTestManager.getTest().addScreenCapture(filePath));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        //Extentreports log operation for skipped tests.
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped : "+getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}
