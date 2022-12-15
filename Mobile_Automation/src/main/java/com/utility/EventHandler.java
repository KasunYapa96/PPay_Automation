package com.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.aventstack.extentreports.Status;

public class EventHandler implements WebDriverEventListener {

	public static boolean encriptText = false;

	///////////////////////////////
	// Send Keys to Report before send keys
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		// Log.info("Before enter ["+arg0.getAttribute("value")+"] value on
		// element :"+arg0.toString());
		// ExtentReporter.logReport(Status.PASS, "Before enter
		// ["+arg0.getAttribute("value")+"] value on element
		// :"+arg0.toString());
	}

	// Send Keys to Report after send keys
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		// Log.info("Successfully entered ["+arg0.getAttribute("value")+"] value
		// on element :"+arg0.toString());
		String value = null;
		try {
			value = arg0.getAttribute("value");
		} catch (Exception e) {
			value = "";
		}
		if (encriptText && value != null) {
			value = value.replaceAll("(?s).", "*");
		}
		ExtentReporter.logReport(Status.PASS,
				"Successfully entered [" + value + "] value on element :" + arg0.toString(), false);
		encriptText = false;
	}

	///////////////////////////////
	///////////////////////////////
	// Click command write to Report
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		// Log.info("Before click on element "+arg0.toString());
		ExtentReporter.logReport(Status.PASS, "Before click on element :" + arg0.toString(), false);
		ExtentReporter.extent.flush();
	}

	// Click command write to Report before click
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		// Log.info("Successfully clicked on element "+arg0.toString());
		// ExtentReporter.logReport(Status.PASS, "Successfully clicked on
		// element :"+arg0.toString());
	}
	///////////////////////////////

	///////////////////////////////
	// Find By to Report
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// Log.info("Before finding element :" + arg0.toString());
		// ExtentReporter.logReport(Status.PASS, "Before finding element :" +
		// arg0.toString());
	}

	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {

		// Log.info("Successfully found element :" + arg0.toString());
		ExtentReporter.logReport(Status.PASS, "Successfully found element :" + arg0.toString(), false);
		ExtentReporter.extent.flush();

	}

	/////////////////////////////
	/////////////////////////////
	// Navigate URL to Report
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// Log.info("Before Navigate to URL: "+arg0.toString());
		// ExtentReporter.logReport(Status.PASS, "Before Navigate to URL:
		// "+arg0.toString());
	}

	public void afterNavigateTo(String arg0, WebDriver arg1) {
		// Log.info("Successfully Navigated to URL: "+arg0.toString());
		ExtentReporter.logReport(Status.PASS, "Successfully Navigated to URL: " + arg0.toString(), false);
		ExtentReporter.extent.flush();
	}

	/////////////////////////////
	public void onException(Throwable arg0, WebDriver arg1) {

		// Log.info("Exception occured at " + arg0.getMessage());
		String before = "<span onclick=\"$(this).next().toggle();\">Show/Hide Stacktrace</span><p style=\"display: none;\">";
		String exceptionText = before + arg0.getMessage() + "</p>";
		// ExtentReporter.logReport(Status.INFO, "Exception occured at " +
		// arg0.getMessage(),false);
		ExtentReporter.logReport(Status.INFO, exceptionText, true);
		ExtentReporter.extent.flush();
		// Log.info("<***********>arg0.getStackTrace().toString()"+org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(arg0));
		//
		// Log.info("<***********>arg0.getMessage()"+arg0.getMessage());
		// Log.info("<***********>arg0.getLocalizedMessage()"+arg0.getLocalizedMessage());

	}

	/////////////////////////////
	// Navigate back to report
	public void afterNavigateBack(WebDriver arg0) {
		// Log.info("Inside the after navigateback to " + arg0.getCurrentUrl());
	}

	public void beforeNavigateBack(WebDriver arg0) {
		// Log.info("Just before beforeNavigateBack " + arg0.getCurrentUrl());
	}

	/////////////////////////////
	/////////////////////////////
	// Navigate forward to report
	public void afterNavigateForward(WebDriver arg0) {
		// Log.info("Inside the afterNavigateForward to " +
		// arg0.getCurrentUrl());
	}

	public void beforeNavigateForward(WebDriver arg0) {
		// Log.info("Just before beforeNavigateForward " +
		// arg0.getCurrentUrl());
	}

	/////////////////////////////
	/////////////////////////////
	// After script to report
	public void afterScript(String arg0, WebDriver arg1) {
		// Log.info("Inside the afterScript to, Script is " + arg0);
	}

	public void beforeScript(String arg0, WebDriver arg1) {
		// Log.info("Just before beforeScript " + arg0);
	}
	/////////////////////////////

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeGetText(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	// public void afterChangeValueOf(WebElement element, WebDriver driver,
	// java.lang.CharSequence[] keysToSend)
	// {
	// Log.info("+++++++++inside method afterChangeValueOf on " +
	// element.toString()+ "Value: "+keysToSend);
	// }

}
