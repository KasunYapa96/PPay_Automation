package com.chivelab.pageObjIOS;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.gson.annotations.Until;
import com.relevantcodes.extentreports.LogStatus;
import com.utility.Log;
import com.utility.ObjectTypes;
import com.utility.SingletonClass;
import com.utils.ExtentReports.ExtentTestManager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions; 
import static io.appium.java_client.touch.offset.ElementOption.element;
import static org.testng.Assert.assertTrue;

import io.appium.java_client.TouchAction;



public class BasePageObject {
	
	
	/**
	 * Function Name - take screenShot on failure
	 * 
	 * @param N/A
	 * @throws N/A
	 * @author Kasun
	 * @return N/A
	 * @throws InterruptedException 
	 */
	public static void appClose(double startyFactor, double endyFactor, int waitTime){
		
		((PressesKey) SingletonClass.getIOSDriver()).pressKeyCode(187); //pressKeyCode(AndroidKeyCode.KEYCODE_MENU);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {

			Dimension size = SingletonClass.getIOSDriver().manage().window().getSize();
			Log.info(size.toString());
			int starty = (int) (size.height * startyFactor);
			int endy = (int) (size.height * endyFactor);
			int startx = size.width / 2;
			TouchAction touchAction = new TouchAction(SingletonClass.getIOSDriver());
			touchAction.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(waitTime))).moveTo(PointOption.point(startx, endy)).release().perform();

			Log.info("Swipe up App close");

		} catch (Exception e) {

			ExtentTestManager.getTest().log(LogStatus.FAIL, "Scroll Up fail: App close " + e.getMessage());
			Log.info("Scroll Up fail: App close " + e.getMessage());
		}
	}
	
	/**
	 * Function Name - take screenShot on failure
	 * 
	 * @param N/A
	 * @throws N/A
	 * @author Kasun
	 * @return N/A
	 */
	public void screenShot() {
		Boolean screenShotsOn = Boolean.valueOf(SingletonClass.getFrameWorkProp("screeShots"));

		if (screenShotsOn) {
			String timeStamp;
			File screenShotName;
			File scrFile = ((TakesScreenshot) SingletonClass.getIOSDriver()).getScreenshotAs(OutputType.FILE);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			screenShotName = new File(SingletonClass.getFrameWorkProp("screenShotpath") + timeStamp + ".png");
			try {
				FileUtils.copyFile(scrFile, screenShotName);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			String filePath = screenShotName.toString();
			String path = "<img src=" + filePath + " alt=\"\" height=\"450\" width=\"450\">";
			Reporter.log(path);
		}
	}
	
	/**
	 * Driver session keep alive 
	 * 
	 * @param N/A
	 * @throws N/A
	 * @author Kasun
	 * @return N/A
	 */
	public void driverKeepAlive() {
		ScreenOrientation orientation;
		
		try{
			orientation = SingletonClass.getIOSDriver().getOrientation();
		
		}catch (Exception e) {

		}
		try{
			orientation = SingletonClass.getAndroidDriver_2().getOrientation();
		
		}catch (Exception e) {

			}
		try{
			orientation = SingletonClass.getAndroidDriver_3().getOrientation();
		}catch (Exception e) {

		}
	}

	/**
	 * sleep (pause)
	 *
	 * @param seconds
	 *            - wait time in seconds
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public void sleep(int seconds) throws InterruptedException {
		// SingletonClass.getIOSDriver().manage().timeouts().implicitlyWait(seconds,
		// TimeUnit.SECONDS);
		Thread.sleep(seconds * 1000);
	}
	
	/**
	 * sleep (pause)
	 *
	 * @param seconds
	 *            - wait time in seconds
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public void setFocus() {
		try {
			
			((PressesKey) SingletonClass.getIOSDriver()).pressKeyCode(187);
			sleep(2);
			((PressesKey) SingletonClass.getIOSDriver()).pressKeyCode(187);
			sleep(3);

		} catch (Exception e) {

			ExtentTestManager.getTest().log(LogStatus.FAIL, "Set focus to application fail " + e.getMessage());
			Log.info("Set focus to the application fail " + e.getMessage());
		}
	}
	
	/**
	 * Toggle is enabled
	 *
	 * @param resourceId
	 *            - resourceId of the element
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public boolean toggleIsEnabled(String resourceId) {
		
		boolean enabled = false;
		String value;
		
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[contains(@resource-id,\""+resourceId+"\")]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[contains(@resource-id,\""+resourceId+"\")]")).getText().trim();
			
			if(value.equals("ON")){
				enabled = true;
			}else if(value.equals("OFF")){
				enabled = false;
			}else{
				Assert.assertTrue(false, "Toggle Not found : returned: "+ value);
			}

		} catch (NoSuchElementException e) {
			Assert.assertTrue(false, "Toggle Not found : resoucre-id= "+ resourceId);
		}catch (TimeoutException e){
			Assert.assertTrue(false, "Toggle Not found : resoucre-id= "+ resourceId);
		}

		return enabled;

	}
	
	/**
	 * Toggle enable
	 *
	 * @param resourceId
	 *            - resourceId of the element
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public void toggleEnable(String resourceId) throws InterruptedException {
		
		boolean enabled = true;
		
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[contains(@resource-id,\""+resourceId+"\")]"))));
			enabled = toggleIsEnabled(resourceId);
			
			if(enabled==false){
				uiClickByXpath("//*[contains(@resource-id,\""+resourceId+"\")]");
				
				sleep(3);
				enabled = toggleIsEnabled(resourceId);
				if(enabled==false){
					Assert.assertTrue(false, "Toggle Not Enabled : resoucre-id= "+ resourceId);
				}
		
			}

		} catch (NoSuchElementException e) {
			Assert.assertTrue(false, "Toggle Not found : resoucre-id= "+ resourceId);
		}catch (TimeoutException e){
			Assert.assertTrue(false, "Toggle Not found : resoucre-id= "+ resourceId);
		}

	}
	
	/**
	 * Toggle disable
	 *
	 * @param resourceId
	 *            - resourceId of the element
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public void toggleDisable(String resourceId) throws InterruptedException {
		
		boolean enabled = false;
		
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[contains(@resource-id,\""+resourceId+"\")]"))));
			enabled = toggleIsEnabled(resourceId);
			
			if(enabled==true){
				uiClickByXpath("//*[contains(@resource-id,\""+resourceId+"\")]");
				
				sleep(3);
				enabled = toggleIsEnabled(resourceId);
				if(enabled==true){
					Assert.assertTrue(false, "Toggle Not Disabled : resoucre-id= "+ resourceId);
				}
		
			}

		} catch (NoSuchElementException e) {
			Assert.assertTrue(false, "Toggle Not found : resoucre-id= "+ resourceId);
		}catch (TimeoutException e){
			Assert.assertTrue(false, "Toggle Not found : resoucre-id= "+ resourceId);
		}

	}

	/**
	 * Wait for Loading "animation_view"
	 * 
	 * @param N/A
	 * @throws N/A
	 * @author Kasun
	 * @throws InterruptedException
	 */
	@SuppressWarnings("deprecation")
	public void waitForLoading(int retryCount) throws InterruptedException {
		sleep(2);
		for (int i = 0; i < retryCount; i++) {
			try {
				new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
						.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageView"))));
				if (((AndroidElement) SingletonClass.getIOSDriver()
						.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageView"))).isDisplayed()) {
					Log.info("wait for loading: animation_view");
					sleep(2);

				}
			} catch (NoSuchElementException e) {
				Log.info("skip for loading: animation_view");
				sleep(4);
				break;

			} catch (StaleElementReferenceException e) {
				((PressesKey) SingletonClass.getIOSDriver()).pressKeyCode(187);
				sleep(2);
				((PressesKey) SingletonClass.getIOSDriver()).pressKeyCode(4);
				sleep(3);
			} catch(TimeoutException e){
				sleep(2);
			}
			catch(WebDriverException e){
				((PressesKey) SingletonClass.getIOSDriver()).pressKeyCode(187);
				sleep(2);
				((PressesKey) SingletonClass.getIOSDriver()).pressKeyCode(4);
				sleep(3);
			}
		}

		// WebDriverWait wait = new
		// WebDriverWait(SingletonClass.getIOSDriver(), 30);
		// wait.until(ExpectedConditions.visibilityOf((AndroidElement)
		// SingletonClass.getIOSDriver().findElement(
		// By.xpath("//android.widget.ImageView[@resource-id=\"com.visa.android:id/animation_view\"]"))));
		// sleep(5);
	}


	/**
	 * Wait for Element by ID
	 * 
	 * @param text
	 *            - id of the element retryCount - retry count for element
	 * @throws N/A
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public void waitForElementById(String id, int retryCount) throws InterruptedException {
		for (int i = 0; i < retryCount; i++) {
			try {
				new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
						.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
				if (((AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))).isDisplayed()) {
					Log.info("wait for element by id: " + id);
					break;

				}
			} catch (NoSuchElementException e) {
				Log.info("skip for element by id: " + id);
				sleep(2);

			}
		}
	}

	/**
	 * Wait for Element by Text
	 * 
	 * @param text
	 *            - class name(TextView) of the element retryCount - retry count
	 *            for element
	 * @throws N/A
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public void waitForElementByText(String text, int retryCount) throws InterruptedException {
		for (int i = 0; i < retryCount; i++) {
			try {
				new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
						.findElement(By.xpath("//android.widget.TextView[@text=\'" + text + "\']"))));
				if (((AndroidElement) SingletonClass.getIOSDriver()
						.findElement(By.xpath("//android.widget.TextView[@text=\'" + text + "\']"))).isDisplayed()) {
					Log.info("wait for element by text: " + text);
					break;

				}
			} catch (NoSuchElementException e) {
				Log.info("skip for element by text: " + text);
				sleep(2);

			}
		}
	}
	
	/**
	 * Wait for Element by Xpath
	 * 
	 * @param xpath
	 *            - class name(TextView) of the element retryCount - retry count
	 *            for element
	 * @throws N/A
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public void waitForElementByXpath(String xpath, int retryCount) throws InterruptedException {
		for (int i = 0; i < retryCount; i++) {
			try {
				new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
						.findElement(By.xpath(xpath))));
				if (((AndroidElement) SingletonClass.getIOSDriver()
						.findElement(By.xpath(xpath))).isDisplayed()) {
					Log.info("wait for element by text: " + xpath);
					break;

				}
			} catch (NoSuchElementException e) {
				Log.info("skip for element by text: " + xpath);
				sleep(2);

			}
		}
	}

	/**
	 * Wait for Element by View
	 * 
	 * @param text
	 *            - class name (View) of the element retryCount - retry count
	 *            for element
	 * @throws N/A
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public void waitForElementByView(String text, int retryCount) throws InterruptedException {
		for (int i = 0; i < retryCount; i++) {
			try {
				new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
						.findElement(By.xpath("//android.view.View[@text=\"" + text + "\"]"))));
				if (((AndroidElement) SingletonClass.getIOSDriver()
						.findElement(By.xpath("//android.view.View[@text=\"" + text + "\"]"))).isDisplayed()) {
					Log.info("wait for element by view: " + text);
					break;

				}
			} catch (NoSuchElementException e) {
				Log.info("skip for element by view: " + text);
				sleep(2);

			}
		}
	}

	/**
	 * wait for Element - *********** Need to test
	 *
	 * @param element
	 *            - input element second - wait in seconds
	 * @author Kasun
	 * @throws N/A
	 */
	public void waitForElement(MobileElement element, int seconds) {

		WebDriverWait wait = new WebDriverWait(SingletonClass.getIOSDriver(), seconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * uiSendKeys by ID
	 *
	 * @param text
	 *            - ID of element value - input value takeScreenshot - take a
	 *            screen shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 * @throws N/A
	 */
	public void uiSendKeysById(String id, String value, boolean takeScreenshot) throws Exception {
		waitForLoading(10);
		waitForElementById(id, 10);
		uiSendKeys((AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")), value);
		hideKeyboard();

		if (takeScreenshot) {
			takeScreenshot("Screenshot");
		}
	}

	/**
	 * uiSendKeys by ID
	 *
	 * @param text
	 *            - ID of element value - input value
	 * @author Kasun
	 * @throws Exception
	 * @throws N/A
	 */
	public void uiSendKeysById(String id, String value) throws Exception {
		waitForLoading(10);
		waitForElementById(id, 10);
		uiSendKeys((AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")), value);
		hideKeyboard();
	}
	
	/**
	 * uiSendKeys by Text
	 *
	 * @param text
	 *            - text of element value - input value
	 * @author Hasitha
	 * @throws Exception
	 * @throws N/A
	 */
	public void uiSendKeysByText(String text, String value) throws Exception {
		waitForLoading(10);
		waitForElementByText(text, 10);
		uiSendKeys((AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@text=\"" + text + "\"]")), value);
		hideKeyboard();
	}
	
	/**
	 * uiSendKeys by Xpath
	 *
	 * @param xpath
	 *            - ID of element value - input value
	 * @author Kasun
	 * @throws Exception
	 * @throws N/A
	 */
	public void uiSendKeysByXpath(String xpath, String value) throws Exception {
		waitForLoading(10);
		waitForElementByXpath(xpath, 10);
		uiSendKeys((AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath(xpath)), value);
		hideKeyboard();
	}
	
	/**
	 * uiSendKeys by ID
	 *
	 * @param text
	 *            - ID of element value - input value takeScreenshot - take a
	 *            screen shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 * @throws N/A
	 */
	public void uiSendKeysById(MobileElement element, String value, boolean takeScreenshot) throws Exception {
		waitForLoading(10);
		uiSendKeys(element, value);
		hideKeyboard();

		if (takeScreenshot) {
			takeScreenshot("Screenshot");
		}
	}
	
	/**
	 * uiSendKeys by ID
	 *
	 * @param text
	 *            - ID of element value - input value takeScreenshot - take a
	 *            screen shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 * @throws N/A
	 */
	public void uiSendKeysById(MobileElement element, String value) throws Exception {
		waitForLoading(10);
		uiSendKeys(element, value);
		hideKeyboard();
	}

	/**
	 * uiSendKeys by ID on page (scroll down and search)
	 *
	 * @param id
	 *            - Id of the element objtype - Enum object types input - input
	 *            text scrollCount - scroll down count (max limit)
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void uiSendKeysByIdOnPage(String id, String elementName, String input, ObjectTypes objtype, int scrollCount,
			boolean takeScreenshot) throws Exception {
		boolean value = isVisibleAndroidElementByIdWithoutLog(id);
		AndroidElement element = null;

		if (value == false) {
			for (int i = 0; i < scrollCount + 1; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
					element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
					value = element.isDisplayed();

				} catch (NoSuchElementException e) {
					swipeUpOrDown(0.5, 0.2, 3000);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
				}

				if (value == true) {
					break;
				}
			}
		} else {
			element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
		}

		if (value == false) {
			Log.info("uiSendKeys '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "uiSendKeys '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("uiSendKeys '" + elementName + "' " + objtype.getValue());
		}

		uiSendKeysById(id, input);

	}

	/**
	 * uiSendKeys by ID on page (scroll down and search)
	 *
	 * @param id
	 *            - id of the element objtype - Enum object types input - input
	 *            value scrollCount - scroll down count (max limit)
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void uiSendKeysByIdOnPage(String id, String elementName, String input, ObjectTypes objtype, int scrollCount)
			throws Exception {
		boolean value = isVisibleAndroidElementByIdWithoutLog(id);
		AndroidElement element = null;

		if (value == false) {
			for (int i = 0; i < scrollCount + 1; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
					element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
					value = element.isDisplayed();

				} catch (NoSuchElementException e) {
					swipeUpOrDown(0.5, 0.2, 3000);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
				}

				if (value == true) {
					break;
				}
			}
		} else {
			element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
		}

		if (value == false) {
			Log.info("uiSendKeys '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "uiSendKeys '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		uiSendKeysById(id, input);

	}
	
	/**
	 * uiSendKeys by ID on page (scroll down and search)
	 *
	 * @param id
	 *            - Id of the element objtype - Enum object types input - input
	 *            text scrollCount - scroll down count (max limit)
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void uiSendKeysByIdOnPage(MobileElement element, String elementName, String input, ObjectTypes objtype, int scrollCount,
			boolean takeScreenshot) throws Exception {
		boolean value = isVisibleAndroidElementWithoutLog(element);
//		AndroidElement element = null;

		if (value == false) {
			for (int i = 0; i < scrollCount + 1; i++) {
				
//				element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
				value = isVisibleAndroidElementWithoutLog(element);

				if(value == false) {
					swipeUpOrDown(0.5, 0.2, 3000);

				}else{
					break;
				}
			}
		}
		
		if (value == false) {
			Log.info("uiSendKeys '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "uiSendKeys '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("uiSendKeys '" + elementName + "' " + objtype.getValue());
		}

		uiSendKeysById(element, input);

	}
	
	/**
	 * uiSendKeys by ID on page (scroll down and search)
	 *
	 * @param id
	 *            - Id of the element objtype - Enum object types input - input
	 *            text scrollCount - scroll down count (max limit)
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void uiSendKeysByIdOnPage(MobileElement element, String elementName, String input, ObjectTypes objtype, int scrollCount) throws Exception {
		boolean value = isVisibleAndroidElementWithoutLog(element);
//		AndroidElement element = null;

		if (value == false) {
			for (int i = 0; i < scrollCount + 1; i++) {
				
//				element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
				value = isVisibleAndroidElementWithoutLog(element);

				if(value == false) {
					swipeUpOrDown(0.5, 0.2, 3000);

				}else{
					break;
				}
			}
		}
		
		if (value == false) {
			Log.info("uiSendKeys '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "uiSendKeys '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		uiSendKeysById(element, input);

	}
	
	

	/**
	 * uiGetText by ID
	 *
	 * @param text-
	 *            ID of element value - input value takeScreenshot - take a
	 *            screen shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 * @throws N/A
	 */
	public String uiGetTextById(String id, boolean takeScreenshot) throws Exception {
		waitForLoading(10);
		waitForElementById(id, 10);

		if (takeScreenshot) {
			takeScreenshot("Screenshot");
		}

		return uiGetText((AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")));
	}

	/**
	 * uiGetText by ID
	 *
	 * @param text-
	 *            ID of element value - input value
	 * @author Kasun
	 * @throws InterruptedException
	 * @throws N/A
	 */
	public String uiGetTextById(String id) throws InterruptedException {
		waitForLoading(10);
		waitForElementById(id, 10);
		return uiGetText((AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")));
	}
	
	
	
	public String uiGetTextByXpath(String xpath) throws InterruptedException {
	
		return uiGetText((AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath(xpath)));
	}

	/**
	 * uiClear by ID
	 *
	 * @param text
	 *            - ID of element
	 * @author Kasun
	 * @throws InterruptedException
	 * @throws N/A
	 */
	public void uiClearById(String id) throws InterruptedException {
		waitForLoading(10);
		waitForElementById(id, 10);
		uiClear((AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")));
		waitForLoading(10);
	}

	/**
	 * uiClick by ID
	 *
	 * @param text
	 *            - ID of element
	 * @author Kasun
	 * @throws InterruptedException
	 * @throws N/A
	 */
	public void uiClickById(String id) throws InterruptedException {
		waitForLoading(10);
		waitForElementById(id, 10);
		uiClick((AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")));
		waitForLoading(10);
	}

	/**
	 * uiClick TextView by Text
	 *
	 * @param text
	 *            - text of element
	 * @author Kasun
	 * @throws InterruptedException
	 * @throws N/A
	 */
	public void uiClickTextViewByText(String text) throws InterruptedException {
		waitForLoading(10);
		waitForElementByText(text, 10);
		uiClick((AndroidElement) SingletonClass.getIOSDriver()
				.findElement(By.xpath("//android.widget.TextView[@text=\'" + text + "\']")));
		waitForLoading(10);
	}
	
	/**
	 * uiClick by Xpath
	 *
	 * @param text
	 *            - text of element
	 * @author Kasun
	 * @throws InterruptedException
	 * @throws N/A
	 */
	public void uiClickByXpath(String xpath) throws InterruptedException {
		waitForLoading(10);
		waitForElementByXpath(xpath, 10);
		uiClick((AndroidElement) SingletonClass.getIOSDriver()
				.findElement(By.xpath(xpath)));
		waitForLoading(10);
	}
	
	/**
	 * uiClick  by Text
	 *
	 * @param text
	 *            - text of element
	 * @author Kasun
	 * @throws InterruptedException
	 * @throws N/A
	 */
	public void uiClickByText(String text) throws InterruptedException {
		waitForLoading(10);
		waitForElementByText(text, 10);
		uiClick((AndroidElement) SingletonClass.getIOSDriver()
				.findElement(By.xpath("//*[@text=\"" + text + "\"]")));
		waitForLoading(10);
	}

	/**
	 * Function Name - uiSendKeys Override default selenium 'sendkeys()' method
	 * with wait option.This method is used to wait for element to be displayed
	 * until given duration before failing the action.
	 * 
	 * @param MobileElement
	 *            element & String value
	 * @throws Exception
	 *             - ElementNotFoundException
	 * @author Kasun
	 * @return N/A
	 */
	public void uiSendKeys(MobileElement element, String value) {
		String locator;
		String splitLocator[];
		try {
			element = (MobileElement) (new WebDriverWait(SingletonClass.getIOSDriver(), 20))
					.until(ExpectedConditions.visibilityOf(element));
			locator = element.toString();
			if(locator.contains("->")){
				splitLocator= locator.split("->");
				locator = splitLocator[1];
			}
			element.sendKeys(new CharSequence[] { value });

			Log.info("Enter/Set value '" + value + "' for element : " + locator);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Enter/Set value '" + value + "' for element : " + locator);

		} catch (Exception e) {
			screenShot();
			Log.info("Enter/Set value .. for element : " + e.getMessage());
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Enter/Set value .. for element : " + e.getMessage());
			Assert.assertTrue(false, "Element not found : ");
		}
	}
	
	
	public void sendKeysSp(String id,String value) throws Exception {
		waitForElementById(id, 10);
		//uiSendKeys((AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")), value);
		uiSendKeys((AndroidElement) SingletonClass.getIOSDriver().findElement(By.id(id)), value);
		hideKeyboard();
		
	}
	
	
	

	/**
	 * Function Name - uiClear Override default selenium 'clear()' method with
	 * wait option,clear the field before clearing the field.This method is used
	 * to wait for element to be displayed until given duration before failing
	 * the action.
	 * 
	 * @param MobileElement
	 *            element
	 * @throws Exception
	 *             - ElementNotFoundException
	 * @author Kasun
	 * @return N/A
	 */
	public void uiClear(MobileElement element) {
		String locator;
		String splitLocator[];
		try {
			element = (MobileElement) (new WebDriverWait(SingletonClass.getIOSDriver(), 20))
					.until(ExpectedConditions.visibilityOf(element));
			locator = element.toString();
			if(locator.contains("->")){
				splitLocator= locator.split("->");
				locator = splitLocator[1];
			}
			element.clear();

			Log.info("Clear content of the element : " + locator);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Clear content of the element : " + locator);

		} catch (Exception e) {
			screenShot();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Clear content of the element : " + e.getMessage());
			Assert.assertTrue(false, "Element not found : ");
		}

	}

	/**
	 * Function Name - uiGetText Override default selenium 'getText()' method
	 * with wait option before retrieving the value.This method is used to wait
	 * for element to be displayed until given duration before failing the
	 * action.
	 * 
	 * @param MobileElement
	 *            element
	 * @throws Exception
	 *             - ElementNotFoundException
	 * @author Kasun
	 * @return String - text value of the given element
	 */
	public String uiGetText(MobileElement element) {
		
		// Driver session keep alive
		driverKeepAlive();
		
		String content = "";
		String locator;
		String splitLocator[];
		try {
			element = (MobileElement) (new WebDriverWait(SingletonClass.getIOSDriver(), 20))
					.until(ExpectedConditions.visibilityOf(element));
			locator = element.toString();
			if(locator.contains("->")){
				splitLocator= locator.split("->");
				locator = splitLocator[1];
			}
			content = element.getText();
			ExtentTestManager.getTest().log(LogStatus.INFO,
						"Get text element : " + locator + "----> Value : " + content);

		} catch (Exception e) {
			screenShot();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Get the value of the element :" + e.getMessage());
			Assert.assertTrue(false, "Element not found : ");
		}
		return content;
	}

	/**
	 * Function Name - uiClick Override default selenium 'click()' method with
	 * wait option before clicking the element.This method is used to wait for
	 * clicking an element to be displayed until given duration before failing
	 * the action.
	 * 
	 * @param MobileElement
	 *            element
	 * @throws Exception
	 *             - ElementNotFoundException
	 * @author Kasun
	 * @return N/A
	 */
	public void uiClick(MobileElement element) {
		
		// Driver session keep alive
		driverKeepAlive();
		
		String locator;
		String splitLocator[];
		try {
			element = (MobileElement) (new WebDriverWait(SingletonClass.getIOSDriver(), 20))
					.until(ExpectedConditions.elementToBeClickable(element));
			locator = element.toString();
			if(locator.contains("->")){
				splitLocator= locator.split("->");
				locator = splitLocator[1];
			}
			element.click();
			ExtentTestManager.getTest().log(LogStatus.INFO, "Click element : " + locator);
			
		} catch (Exception e) {
			screenShot();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Click element : " + e.getMessage());
			Assert.assertTrue(false, "Element not found : ");
		}

	}

	/**
	 * Function Name - getTimeStampValue method implemented to get the current
	 * time in "HH_mm_ss" format
	 * 
	 * @param N/A
	 * @throws Exception
	 *             - Exception
	 * @author Kasun
	 * @return String
	 */
	public static String getTimeStampValue() throws IOException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		new Date(timestamp.getTime());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH_mm_ss");
		return simpleDateFormat.format(timestamp);
	}

	/**
	 * Function Name - uiQuit method implemented to kill the driver instance
	 * 
	 * @param N/A
	 * @throws Exception
	 *             - Exception
	 * @author Kasun
	 * @return N/A
	 */
	public void uiQuit() {
		SingletonClass.getIOSDriver().quit();
	}

	/**
	 * Function Name - uiBack method implemented to simulate android back button
	 * action
	 * 
	 * @param N/A
	 * @throws Exception
	 *             - Exception
	 * @author Kasun
	 * @return N/A
	 */
	public void uiBack() {
		try {
			SingletonClass.getIOSDriver().navigate().back();
			ExtentTestManager.getTest().log(LogStatus.INFO, "Simulate android back button action");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception : " + e);
		}

	}

	/**
	 * Function Name - getWindowSize method implemented to get the android
	 * device screen size.
	 * 
	 * @param N/A
	 * @throws Exception
	 *             - Exception
	 * @author Kasun
	 * @return Dimension - dimention
	 */
	public Dimension getWindowSize() throws Exception {
		Dimension dimension = SingletonClass.getIOSDriver().manage().window().getSize();
		return dimension;

	}

	/**
	 * Function Name - takeScreenshot method implemented to capture a screen
	 * shot of the current instance.
	 * 
	 * @param Destination
	 *            - path to save location
	 * @throws Exception
	 *             - Exception
	 * @author Kasun
	 * @return N/A
	 */
	public void takeScreenshot(String Discription) throws Exception {

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		File screenShotName = new File(SingletonClass.getFrameWorkProp("screenShotpathPass") + timeStamp + ".png");

		try {

			File scrFile = ((TakesScreenshot) SingletonClass.getIOSDriver()).getScreenshotAs(OutputType.FILE);

			try {

				FileUtils.copyFile(scrFile, screenShotName);

				Toolkit.getDefaultToolkit().beep();

				ExtentTestManager.getTest().log(LogStatus.INFO, "Screenshot Description : " + Discription);

				// path replaced due to image load in html report.
				String filePath = screenShotName.toString().replace("ExtentReports\\", "");

				ExtentTestManager.getTest().log(LogStatus.INFO, "Screen shot",
						ExtentTestManager.getTest().addScreenCapture(filePath));

				Log.info("Take screen shot success");

			} catch (IOException e) {

				ExtentTestManager.getTest().log(LogStatus.FAIL, "Take screen shot fail " + e.getMessage());

				Log.info("Take screen shot fail");

			}

		} catch (Exception e) {

			// TODO: handle exception

		}
	}

	/**
	 * 
	 * Simulate android scroll action
	 * 
	 * @param startyFactor
	 *            - 0.50 / endyFactor - 0.20
	 * @throws Exception
	 * @author Kasun
	 * 
	 */

	public void swipeUpOrDown(double startyFactor, double endyFactor, int waitTime) {

		try {

			Dimension size = SingletonClass.getIOSDriver().manage().window().getSize();
			Log.info(size.toString());
			int starty = (int) (size.height * startyFactor);
			int endy = (int) (size.height * endyFactor);
			int startx = size.width / 2;
//			SingletonClass.getIOSDriver().swipe(startx, starty, startx, endy, waitTime);
			TouchAction touchAction = new TouchAction(SingletonClass.getIOSDriver());
			touchAction.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(waitTime))).moveTo(PointOption.point(startx, endy)).release().perform();

			Log.info("Simulate android scroll action success");

		} catch (Exception e) {

			ExtentTestManager.getTest().log(LogStatus.FAIL, "Scroll Down fail " + e.getMessage());
			Log.info("Simulate android scroll action fail " + e.getMessage());
		}
	}

	/**
	 * 
	 * Simulate android scroll action up or down from element and scroll until
	 * target element found
	 *
	 * @param startxFactor
	 *            - 0.80 / endxFactor - 0.20 / baseElement - (X coordinates of
	 *            the base element ) / swipeTime - time taken to perform swipe /
	 *            targetElement - scroll until target element found
	 * @throws Exception
	 * @author Kasun
	 * 
	 */
	public void swipeVerticalElementTillFound(AndroidElement baseElement, AndroidElement targetElement, int scrollCount,
			double startyFactor, double endyFactor, int swipeTime)
			throws InterruptedException {
		int count = 0;
		boolean isDisplayed = false;
		int hight = SingletonClass.getIOSDriver().manage().window().getSize().getHeight();
		while (isDisplayed == false && count <= scrollCount) {
			try {
				Point s = targetElement.getLocation();
				int ycord = s.getY();

				if ((hight - 200 > ycord) == true) {
					isDisplayed = true;
				} else {
					isDisplayed = false;
				}
			} catch (Exception e) {
				Log.info(e.getMessage());
			}
			try {
				if (isDisplayed == false) {

					swipeVerticalFromElement(baseElement, startyFactor, endyFactor, swipeTime);
				}
			} catch (Exception e) {
				Log.info(e.getMessage());
			}
			count++;
		}
	}

	
	//Force Swipe Down

	
	
	/**
	 * 
	 * Simulate android scroll action up or down from element
	 * 
	 * @param startxFactor
	 *            - 0.80 / endxFactor - 0.20 / baseElement - (X coordinates of
	 *            the base element ) / swipeTime - time taken to perform swipe
	 * @throws Exception
	 * @author Kasun
	 * 
	 */

	public void swipeVerticalFromElement(AndroidElement baseElement, double startyFactor, double endyFactor,
			int swipeTime) {
		Dimension size;
		size = SingletonClass.getIOSDriver().manage().window().getSize();
		int starty = (int) (size.height * startyFactor);
		int endy = (int) (size.height * endyFactor);
		Point s = baseElement.getLocation();
		int startx = s.getX();
//		SingletonClass.getIOSDriver().swipe(startx, starty, startx, endy, swipeTime);
		TouchAction touchAction = new TouchAction(SingletonClass.getIOSDriver());
		touchAction.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipeTime))).moveTo(PointOption.point(startx, endy)).release().perform();

	}
	/**
	 * 
	 * Simulate android scroll action up or down from element
	 * 
	 * @param startxFactor
	 *            - 0.80 / endxFactor - 0.20 / baseElement - (X coordinates of
	 *            the base element ) / swipeTime - time taken to perform swipe
	 * @throws Exception
	 * @author Kasun
	 * 
	 */

	public void swipeHorizontalFromElement(AndroidElement baseElement, double startyFactor, double endyFactor,
			int swipeTime) {
		System.out.println("passss");
		Dimension size;
		size = SingletonClass.getIOSDriver().manage().window().getSize();
//		int starty = (int) (size.height * startyFactor);
		int startx = (int) (size.width * startyFactor);
//		int endy = (int) (size.height * endyFactor);
		int endx = (int) (size.width * endyFactor);

		Point s = baseElement.getLocation();
//		int startx = s.getX();
		int starty = s.getY();

//		SingletonClass.getIOSDriver().swipe(startx, starty, startx, endy, swipeTime);
		TouchAction touchAction = new TouchAction(SingletonClass.getIOSDriver());
//		touchAction.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipeTime))).moveTo(PointOption.point(startx, endy)).release().perform();
		touchAction.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipeTime))).moveTo(PointOption.point(endx, starty)).release().perform();

	}
	
	
    //method to left and right swipe on the screen based on coordinates
public void swipeAction(AndroidElement baseElement,String direction) {
	//int Xcoordinate, int Ycoordinate, 
    //get device width and height
    Dimension dimension = SingletonClass.getIOSDriver().manage().window().getSize();
    int deviceHeight = dimension.getHeight();
    int deviceWidth = dimension.getWidth();
    System.out.println("Height x Width of device is: " + deviceHeight + " x " + deviceWidth);
//	Point s = baseElement.getLocation();
//	int start_x = s.getX();
//	int start_y = s.getY();
//	Point s = baseElement.getLocation();
	int Xcoordinate = baseElement.getCenter().getX();
	int Ycoordinate = baseElement.getCenter().getY();

//	int Xcoordinate = s.getX();
//	int Ycoordinate = s.getY();
	System.out.println(Xcoordinate);
	System.out.println(Ycoordinate);

    switch (direction) {
        case "Left":
            System.out.println("Swipe Right to Left");
            //define starting and ending X and Y coordinates
//        	Point s = baseElement.getLocation();
//        	int Xcoordinate = s.getX();
//        	int Ycoordinate = s.getY();
//            int startX=deviceWidth - Xcoordinate;
//            int startY=Ycoordinate; // (int) (height * 0.2);
//            int endX=Xcoordinate;
//            int endY=Ycoordinate;
            int startX=deviceWidth - Xcoordinate;
            int startY=Ycoordinate; // (int) (height * 0.2);
            int endX=Xcoordinate;
            int endY=Ycoordinate;
            //perform swipe from right to left
            new TouchAction(SingletonClass.getIOSDriver()).longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();
            break;

        case "Right":
            System.out.println("Swipe Left to Right");
            //define starting X and Y coordinates
//        	Point s = baseElement.getLocation();
//        	int Xcoordinatea = s.getX();
//        	int Ycoordinateb = s.getY();
            startX=Xcoordinate;
            startY=Ycoordinate;
            endX=deviceWidth - Xcoordinate;
            endY=Ycoordinate;
            //perform swipe from left to right
            new TouchAction(SingletonClass.getIOSDriver()).longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();
            break;
    }
}

/**
 * 
 * Simulate android Find element Scroll
 * 
 * @param N/A
 * @throws Exception
 * @author Hasitha
 * 
 */

public void verifyIsVisibleTextViewOnPageSwipeUp(String txt, ObjectTypes objtype, int scrollCount, boolean takeScreenshot,Double start, Double end)
		throws Exception {
	boolean value = isVisibleTextViewWithoutLog(txt);

	if (value == false) {
		for (int i = 0; i < scrollCount; i++) {
			try {
				new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
						.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]"))));
				value = SingletonClass.getIOSDriver()
						.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]")).isDisplayed();

			} catch (NoSuchElementException e) {

				swipeUpOrDown(start, end, 3000);
				value = isVisibleTextViewWithoutLog(txt);

			}catch (TimeoutException e){
				swipeUpOrDown(start, end, 3000);
				value = isVisibleTextViewWithoutLog(txt);
			}

			if (value == true) {
				break;
			}
		}
	}

	if (value == false) {
		Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
	}

	if (takeScreenshot) {
		takeScreenshot("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}
	Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
}

	/**
	 * 
	 * Simulate Scroll
	 * 
	 * @param N/A
	 * @throws Exception
	 * @author Hasitha
	 * 
	 */

public void swipingVertical(double startyFactor, double endyFactor) throws InterruptedException {
	  //Get the size of screen.
	 Dimension size;

	  size = SingletonClass.getIOSDriver().manage().window().getSize();
	  System.out.println(size);
	   
	  //Find swipe start and end point from screen's with and height.
	  //Find starty point which is at bottom side of screen.
	  int starty = (int) (size.height * startyFactor);
	  //Find endy point which is at top side of screen.
	  int endy = (int) (size.height * endyFactor);
	  //Find horizontal point where you wants to swipe. It is in middle of screen width.
	  int startx = size.width / 2;
	  System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

	  //Swipe from Bottom to Top.
      new TouchAction(SingletonClass.getIOSDriver()).longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(startx, endy)).release().perform();

//	  SingletonClass.getIOSDriver().swipe(startx, starty, startx, endy, 3000);
	  Thread.sleep(2000);

}
	public void hideKeyboard() throws Exception {

		try {
			SingletonClass.getIOSDriver().hideKeyboard();

			Log.info("Simulate android hideKeyboard action");
		} catch (Exception e) {

			Log.info("Simulate android hideKeyboard action " + e.getMessage());
		}
	}

	/**
	 * 
	 * Simulate android press enter action 
	 * 
	 * @param N/A
	 * @throws Exception
	 * @author Kasun
	 * 
	 */

	@SuppressWarnings("deprecation")
	public void pressEnterSoftKeyboard() throws Exception {

		try {
			((PressesKey) SingletonClass.getIOSDriver()).pressKeyCode(66);
			sleep(2);
			log("Simulate android press enter on soft keyboard action success");

		} catch (Exception e) {
			log("Simulate android press enter on soft keyboard action fail " + e.getMessage());
		}
	}
	
	/**
	 * 
	 * Simulate android back button action 
	 * 
	 * @param N/A
	 * @throws Exception
	 * @author Kasun
	 * 
	 */

	public void clickBackButton() throws Exception {

		try {
			SingletonClass.getIOSDriver().navigate().back();
			sleep(2);
			log("Simulate android click back button action success");

		} catch (Exception e) {
			log("Simulate android click back button action fail " + e.getMessage());
		}
	}


	/**
	 * 
	 * Wait until element present
	 * 
	 * @param MobileElement
	 *            element, int retryCount
	 * @throws Exception
	 * @author Kasun
	 * 
	 */

	public void waitUntilElementPresent(MobileElement element, int retryCount) throws InterruptedException {

		for (int i = 0; i < retryCount; i++) {
			if (i == 0) {
				sleep(2);
			}

			try {
				new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf(element));
				if (element.isDisplayed()) {
					Log.info("found element");
					break;
				}
			} catch (NoSuchElementException e) {
				Log.info("wait for element");
				sleep(2);
			}
		}
	}
	
	/**
	 * 
	 * Tap UI by X & Y values
	 *
	 * @param X & Y
	 * @throws Exception
	 * @author Kasun
	 * 
	 */

	public void tapLocation(int x, int y) throws Exception {

		try {

			TouchAction touchAction = new TouchAction(SingletonClass.getIOSDriver());
			touchAction.tap(PointOption.point(x, y)).perform();

			log("Tap location X=" + x + " Y=" + y);

		} catch (Exception e) {

			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Tap location X=" + x + " Y=" + y + " fail " + e.getMessage());

			Log.info("Tap location fail " + e.getMessage());

		}

	}

	/**
	 * refresh page
	 *
	 * @param retryCount-
	 *            wait For please wait count takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void refreshPage(int retryCount, boolean takeScreenshot) throws Exception {

		swipeUpOrDown(0.3, 0.8, 3000);
		waitForLoading(retryCount);

		if (takeScreenshot) {
			takeScreenshot("Refreshed page");
		}
		Log.info("Refreshed page");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Refreshed page");

	}

	/**
	 * refresh page
	 *
	 * @param retryCount-
	 *            wait For please wait count
	 * @author Kasun
	 * @throws Exception
	 */
	public void refreshPage(int retryCount) throws Exception {

		swipeUpOrDown(0.3, 0.8, 3000);
		waitForLoading(retryCount);

		Log.info("Refreshed page");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Refreshed page");

	}

	/**
	 * Log start precondition
	 *
	 * @param text-
	 *            description of precondition
	 * @author Kasun
	 * @throws Exception
	 */
	public void logStartPrecondition(String text) throws Exception {

		Log.info("=====| Start Precondition : " + text + " |=====");
		ExtentTestManager.getTest().log(LogStatus.INFO, "=====| Start Precondition : " + text + " |=====");

	}

	/**
	 * Log end precondition
	 *
	 * @param text-
	 *            description of precondition
	 * @author Kasun
	 * @throws Exception
	 */
	public void logEndPrecondition(String text) throws Exception {

		Log.info("=====| End of Precondition : " + text + " |=====");
		ExtentTestManager.getTest().log(LogStatus.INFO, "=====| End of Precondition : " + text + " |=====");

	}

	/**
	 * Log - log file and Extend report
	 *
	 * @param text-
	 *            description log @author Kasun @throws
	 */
	public void log(String text) {

		Log.info(text);
		ExtentTestManager.getTest().log(LogStatus.INFO, text);

	}

	/**
	 * Reset APP - Clear all data of the App
	 *
	 * @param N/A
	 * 			@author Kasun @throws
	 * @throws Exception 
	 */
	public void resetApp() throws Exception {
		// wait for loading
		waitForLoading(20);
		
		// clear app data
		SingletonClass.getIOSDriver().resetApp();
		
		// clear browser data
		clearBrowserData();
		
		// To start app
		sleep(5);
		SingletonClass.getIOSDriver().resetApp();
		
		log("Cleared SAIV App data & Browser data");

		// wait for loading
		waitForLoading(20);
		
		//wait for sync
		sleep(5);
	}
	
	/**
	 * Clear Browser Data - Clear all data of the Browser
	 *
	 * @param N/A
	 * @author Kasun @throws
	 * @throws Exception 
	 */
	public void clearBrowserData() throws Exception {
		waitForElementById("com.android.chrome:id/menu_button", 20);
		sleep(5);
		AndroidElement element = (AndroidElement)SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\"com.android.chrome:id/menu_button\"]"));
		int x = element.getLocation().getX();
		int y = element.getLocation().getY();

		tapLocation(x, y);
		tapLocation(x, y);
		tapLocation(x, y);

		sleep(1);
		
		uiClick((AndroidElement) SingletonClass.getIOSDriver()
				.findElement(By.xpath("//android.widget.ImageButton[@content-desc='View site information']")));
		sleep(1);
		uiClickById("com.android.chrome:id/page_info_site_settings_button");
		sleep(2);
		uiClick((AndroidElement) SingletonClass.getIOSDriver()
				.findElement(By.xpath("//*[@resource-id=\"com.android.chrome:id/button_preference\"]")));
		sleep(2);
		uiClick((AndroidElement) SingletonClass.getIOSDriver()
				.findElement(By.xpath("//*[@resource-id='android:id/button1']")));
		log("Cleared SAIV App data");
		waitForElementById("kc-login", 10);

	}

	/**
	 * Allow App permissions
	 * 
	 * @param N/A
	 * @throws N/A
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public void allowAppPermissions(int retryCount) throws InterruptedException {
		sleep(2);
		for (int i = 0; i < retryCount; i++) {
			try {
				new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
						.findElement(By.xpath("//*[@text=\"ALLOW\" or @text=\"Allow\"]"))));
				if (((AndroidElement) SingletonClass.getIOSDriver()
						.findElement(By.xpath("//*[@text=\"ALLOW\" or @text=\"Allow\"]"))).isDisplayed()) {
					uiClick((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@text=\"ALLOW\" or @text=\"Allow\" or @text=\"Allow all the time\"]")));
					sleep(2);

				}
			} catch (NoSuchElementException e) {
				Log.info("skip for app permission: ALLOW");
				sleep(5);
				break;

			}
		}
		waitForLoading(10);
	}


	
	
	
	
	
	

	// ============================ Commons methods - Scripting ======================================

	/**
	 * Is visible TextView - Without Logs
	 *
	 * @param txt
	 *            - input text
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public boolean isVisibleTextViewWithoutLog(String txt) throws InterruptedException {
		boolean value = false;
		System.out.println(txt);
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[@text='" + txt + "']"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[@text='" + txt + "']")).isDisplayed();

		} catch (NoSuchElementException e) {

		}catch (TimeoutException e){
			
		}

		return value;
	}

	/**
	 * Is visible contains TextView - Without Logs
	 *
	 * @param txt
	 *            - input text
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public boolean isVisibleContainsTextViewWithoutLog(String txt) throws InterruptedException {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[contains(@text,\"" + txt + "\")]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[contains(@text,\"" + txt + "\")]")).isDisplayed();

		} catch (NoSuchElementException e) {

		}catch (TimeoutException e){
			
		}

		return value;
	}

	/**
	 * Is visible element by Xpath - Without Logs
	 *
	 * @param txt
	 *            - input text
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public boolean isVisibleElementByXpathWithoutLog(String xpath) throws InterruptedException {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath(xpath))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath(xpath)).isDisplayed();

		} catch (NoSuchElementException e) {

		}catch (TimeoutException e){
			
		}

		return value;
	}

	/**
	 * Is visible contains view.View - Without Logs
	 *
	 * @param txt
	 *            - input text
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public boolean isVisibleContainsViewWithoutLog(String txt) throws InterruptedException {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.view.View[contains(@text,\"" + txt + "\")]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.view.View[contains(@text,\"" + txt + "\")]")).isDisplayed();

		} catch (NoSuchElementException e) {

		}catch (TimeoutException e){
			
		}

		return value;
	}

	/**
	 * Is visible EditText - Without Logs
	 *
	 * @param txt
	 *            - EditText text
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public boolean isVisibleEditTextWithoutLog(String txt) throws InterruptedException {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.EditText[@text=\"" + txt + "\"]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.EditText[@text=\"" + txt + "\"]")).isDisplayed();

		} catch (NoSuchElementException e) {

		}catch (TimeoutException e){
			
		}

		return value;
	}

	/**
	 * Is visible contains EditText - Without Logs
	 *
	 * @param txt
	 *            - EditText text
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public boolean isVisibleContainsEditTextWithoutLog(String txt) throws InterruptedException {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.EditText[contains(@text,\"" + txt + "\")]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.EditText[contains(@text,\"" + txt + "\")]")).isDisplayed();

		} catch (NoSuchElementException e) {

		}catch (TimeoutException e){
			
		}

		return value;
	}

	/**
	 * Is visible AndroidElement - Without Logs
	 *
	 * @param txt
	 *            - input text
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public boolean isVisibleAndroidElementWithoutLog(MobileElement element) throws InterruptedException {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf(element));
			value = element.isDisplayed();

		} catch (NoSuchElementException e) {

		} catch (TimeoutException e){
			
		}

		return value;
	}

	/**
	 * Is visible AndroidElement by ID - Without Logs
	 *
	 * @param text
	 *            - input ID
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public boolean isVisibleAndroidElementByIdWithoutLog(String id) throws InterruptedException {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
			value = SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")).isDisplayed();

		} catch (NoSuchElementException e) {

		}catch (TimeoutException e){
			
		}
		
		return value;
	}

	/**
	 * Is visible AndroidElement by ID - Without Logs
	 *
	 * @param text
	 *            - input ID
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public boolean isVisibleAndroidElementByIdWithoutLog1(MobileElement id) throws InterruptedException {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
			value = SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")).isDisplayed();

		} catch (NoSuchElementException e) {

		}catch (TimeoutException e){
			
		}
		
		return value;
	}
	
	/**
	 * Is visible AndroidElement by Text - Without Logs
	 *
	 * @param text
	 *            - input text
	 * @author Kasun
	 * @throws InterruptedException
	 */
	public boolean isVisibleAndroidElementByTextWithoutLog(String text) throws InterruptedException {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@text=\"" + text + "\"]"))));
			value = SingletonClass.getIOSDriver().findElement(By.xpath("//*[@text=\"" + text + "\"]"))
					.isDisplayed();

		} catch (NoSuchElementException e) {

		}catch (TimeoutException e){
			
		}
		return value;
	}
	
	/**
	 * TextView is visible on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit)
	 * @author Kasun
	 * @throws Exception
	 */
	public boolean isVisibleTextViewOnPage(String txt, ObjectTypes objtype, int scrollCount, boolean takeScreenshot) throws Exception {
		boolean value = isVisibleTextViewWithoutLog(txt);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]"))));
					value = SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]")).isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);
				}

				if (value == true) {
					break;
				}
			}
		}
		
		if(takeScreenshot){
			takeScreenshot("Is visible " + txt + " : " + value);
		}

		log("Is visible " + txt + " : " + value);
		return value;
	}
	
	/**
	 * TextView is visible on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit)
	 * @author Kasun
	 * @throws Exception
	 */
	public boolean isVisibleTextViewOnPage(String txt, ObjectTypes objtype, int scrollCount) throws Exception {
		boolean value = isVisibleTextViewWithoutLog(txt);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]"))));
					value = SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]")).isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);
				}

				if (value == true) {
					break;
				}
			}
		}

		log("Is visible \"" + txt + "\" : " + value);
		return value;
	}
	
	
	
	
	
	

	// ============================ Asserts
	// ======================================

	/**
	 * verify TextView is visible
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types takeScreenshot - take
	 *            a screen shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleTextView(String txt, ObjectTypes objtype, boolean takeScreenshot) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify contains TextView is visible
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types takeScreenshot - take
	 *            a screen shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleContainsTextView(String txt, ObjectTypes objtype, boolean takeScreenshot)
			throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[contains(@text,\"" + txt + "\")]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[contains(@text,\"" + txt + "\")]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}
		

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify view.View is visible
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types takeScreenshot - take
	 *            a screen shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleView(String txt, ObjectTypes objtype, boolean takeScreenshot) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.view.View[@text=\"" + txt + "\"]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.view.View[@text=\"" + txt + "\"]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify contains view.View is visible
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types takeScreenshot - take
	 *            a screen shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleContainsView(String txt, ObjectTypes objtype, boolean takeScreenshot) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.view.View[contains(@text,\"" + txt + "\")]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.view.View[contains(@text,\"" + txt + "\")]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify TextView is visible
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleTextView(String txt, ObjectTypes objtype) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify contains TextView is visible
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleContainsTextView(String txt, ObjectTypes objtype) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[contains(@text,\"" + txt + "\")]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[contains(@text,\"" + txt + "\")]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify view.View is visible
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types takeScreenshot - take
	 *            a screen shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleView(String txt, ObjectTypes objtype) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.view.View[@text=\"" + txt + "\"]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.view.View[@text=\"" + txt + "\"]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify contains view.View is visible
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleContainsView(String txt, ObjectTypes objtype) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.view.View[contains(@text,\"" + txt + "\")]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.view.View[contains(@text,\"" + txt + "\")]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify EditText is visible
	 *
	 * @param txt
	 *            - EditText text objtype - Enum object types takeScreenshot -
	 *            take a screen shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleEditText(String txt, ObjectTypes objtype, boolean takeScreenshot) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.EditText[@text=\"" + txt + "\"]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.EditText[@text=\"" + txt + "\"]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify contains EditText is visible
	 *
	 * @param txt
	 *            - EditText text objtype - Enum object types takeScreenshot -
	 *            take a screen shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleContainsEditText(String txt, ObjectTypes objtype, boolean takeScreenshot)
			throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.EditText[contains(@text,\"" + txt + "\")]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.EditText[contains(@text,\"" + txt + "\")]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify EditText is visible
	 *
	 * @param txt
	 *            - EditText text objtype - Enum object types
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleEditText(String txt, ObjectTypes objtype) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.EditText[@text=\"" + txt + "\"]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.EditText[@text=\"" + txt + "\"]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify contains EditText is visible
	 *
	 * @param txt
	 *            - EditText text objtype - Enum object types
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleContainsEditText(String txt, ObjectTypes objtype) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.EditText[contains(@text,\"" + txt + "\")]"))));
			value = SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.EditText[contains(@text,\"" + txt + "\")]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify AndroidElement is visible
	 *
	 * @param element
	 *            - Input element elementName - name of the element objtype -
	 *            Enum object types takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElement(AndroidElement element, String elementName, ObjectTypes objtype,
			boolean takeScreenshot) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf(element));
			value = element.isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * verify AndroidElement is visible
	 *
	 * @param element
	 *            - Input element elementName - name of the element objtype -
	 *            Enum object types
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElement(AndroidElement element, String elementName, ObjectTypes objtype)
			throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf(element));
			value = element.isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * verify AndroidElement is visible by ID
	 *
	 * @param element
	 *            - Input element elementName - name of the element objtype -
	 *            Enum object types takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElementById(String id, String elementName, ObjectTypes objtype,
			boolean takeScreenshot) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(),15).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
			value = SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * verify AndroidElement is visible by ID
	 *
	 * @param element
	 *            - Input element elementName - name of the element objtype -
	 *            Enum object types takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElementById(String id, String elementName, ObjectTypes objtype) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
			value = SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")).isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * verify AndroidElement is visible by xpath
	 *
	 * @param element
	 *            - Input element elementName - name of the element objtype -
	 *            Enum object types takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Hasitha
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElementByXpath(String xpathValue, String elementName, ObjectTypes objtype) throws Exception {
		boolean value = false;
		System.out.println(xpathValue);
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//android.widget.TextView[@text=\""+xpathValue+"\"]/preceding-sibling::android.widget.ImageView"))));
			value = SingletonClass.getIOSDriver().findElement(By.xpath("//android.widget.TextView[@text=\""+xpathValue+"\"]/preceding-sibling::android.widget.ImageView")).isDisplayed();

			
		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
	}
	
	/**
	 * verify AndroidElement is visible by Text
	 *
	 * @param element
	 *            - Input element elementName - name of the element objtype -
	 *            Enum object types takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElementByText(String text, ObjectTypes objtype, boolean takeScreenshot)
			throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 10).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@text=\"" + text + "\"]"))));
			value = SingletonClass.getIOSDriver().findElement(By.xpath("//*[@text=\"" + text + "\"]"))
					.isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + text + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + text + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + text + "' " + objtype.getValue());
	}

	/**
	 * verify AndroidElement is visible by Text
	 *
	 * @param element
	 *            - Input element elementName - name of the element objtype -
	 *            Enum object types takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElementByTextContains(String text, ObjectTypes objtype, boolean takeScreenshot)
			throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[contains(@text,\"" + text + "\")]"))));
			value = SingletonClass.getIOSDriver().findElement(By.xpath("//*[contains(@text,\"" + text + "\")]"))
					.isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + text + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + text + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + text + "' " + objtype.getValue());
	}
	
	/**
	 * verify AndroidElement is visible by Text
	 *
	 * @param element
	 *            - Input element elementName - name of the element objtype -
	 *            Enum object types takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElementByText(String text, ObjectTypes objtype) throws Exception {
		boolean value = false;
		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@text=\"" + text + "\"]"))));
			value = SingletonClass.getIOSDriver().findElement(By.xpath("//*[@text=\"" + text + "\"]"))
					.isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
		} catch(TimeoutException e){
			Log.info("Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + text + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + text + "' " + objtype.getValue());
	}

	/**
	 * verify TextView is visible on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit) takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleTextViewOnPage(String txt, ObjectTypes objtype, int scrollCount, boolean takeScreenshot)
			throws Exception {
		boolean value = isVisibleTextViewWithoutLog(txt);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]"))));
					value = SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]")).isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify contains TextView is visible on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit) takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleContainsTextViewOnPage(String txt, ObjectTypes objtype, int scrollCount,
			boolean takeScreenshot) throws Exception {
		boolean value = isVisibleContainsTextViewWithoutLog(txt);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.TextView[contains(@text,\"" + txt + "\")]"))));
					value = SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.TextView[contains(@text,\"" + txt + "\")]"))
							.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleContainsTextViewWithoutLog(txt);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleContainsTextViewWithoutLog(txt);
				}
				

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify contains view.View is visible on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit) takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleContainsViewOnPage(String txt, ObjectTypes objtype, int scrollCount,
			boolean takeScreenshot) throws Exception {
		boolean value = isVisibleContainsViewWithoutLog(txt);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.view.View[contains(@text,\"" + txt + "\")]"))));
					value = SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.view.View[contains(@text,\"" + txt + "\")]"))
							.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleContainsViewWithoutLog(txt);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleContainsViewWithoutLog(txt);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify EditText is visible on page (scroll down and search)
	 *
	 * @param txt
	 *            - EditText text objtype - Enum object types scrollCount -
	 *            scroll down count (max limit) takeScreenshot - take a screen
	 *            shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleEditTextOnPage(String txt, ObjectTypes objtype, int scrollCount, boolean takeScreenshot)
			throws Exception {
		boolean value = isVisibleEditTextWithoutLog(txt);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.EditText[@text=\"" + txt + "\"]"))));
					value = SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.EditText[@text=\"" + txt + "\"]")).isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleEditTextWithoutLog(txt);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleEditTextWithoutLog(txt);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify contains EditText is visible on page (scroll down and search)
	 *
	 * @param txt
	 *            - EditText text objtype - Enum object types scrollCount -
	 *            scroll down count (max limit) takeScreenshot - take a screen
	 *            shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleContainsEditTextOnPage(String txt, ObjectTypes objtype, int scrollCount,
			boolean takeScreenshot) throws Exception {
		boolean value = isVisibleContainsEditTextWithoutLog(txt);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.EditText[contains(@text,\"" + txt + "\")]"))));
					value = SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.EditText[contains(@text,\"" + txt + "\")]"))
							.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleContainsEditTextWithoutLog(txt);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleContainsEditTextWithoutLog(txt);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify TextView is visible on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleTextViewOnPage(String txt, ObjectTypes objtype, int scrollCount) throws Exception {
		boolean value = isVisibleTextViewWithoutLog(txt);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]"))));
					value = SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]")).isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify contains TextView is visible on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleContainsTextViewOnPage(String txt, ObjectTypes objtype, int scrollCount)
			throws Exception {
		boolean value = isVisibleContainsTextViewWithoutLog(txt);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.TextView[contains(@text,\"" + txt + "\")]"))));
					value = SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.TextView[contains(@text,\"" + txt + "\")]"))
							.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleContainsTextViewWithoutLog(txt);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleContainsTextViewWithoutLog(txt);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify contains view.View is visible on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleContainsViewOnPage(String txt, ObjectTypes objtype, int scrollCount) throws Exception {
		boolean value = isVisibleContainsViewWithoutLog(txt);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.view.View[contains(@text,\"" + txt + "\")]"))));
					value = SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.view.View[contains(@text,\"" + txt + "\")]"))
							.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleContainsViewWithoutLog(txt);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleContainsViewWithoutLog(txt);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify EditText is visible on page (scroll down and search)
	 *
	 * @param txt
	 *            - EditText text objtype - Enum object types scrollCount -
	 *            scroll down count (max limit)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleEditTextOnPage(String txt, ObjectTypes objtype, int scrollCount) throws Exception {
		boolean value = isVisibleEditTextWithoutLog(txt);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.EditText[@text=\"" + txt + "\"]"))));
					value = SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.EditText[@text=\"" + txt + "\"]")).isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleEditTextWithoutLog(txt);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleEditTextWithoutLog(txt);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify contains EditText is visible on page (scroll down and search)
	 *
	 * @param txt
	 *            - EditText text objtype - Enum object types scrollCount -
	 *            scroll down count (max limit)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleContainsEditTextOnPage(String txt, ObjectTypes objtype, int scrollCount)
			throws Exception {
		boolean value = isVisibleContainsEditTextWithoutLog(txt);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.EditText[contains(@text,\"" + txt + "\")]"))));
					value = SingletonClass.getIOSDriver()
							.findElement(By.xpath("//android.widget.EditText[contains(@text,\"" + txt + "\")]"))
							.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleContainsEditTextWithoutLog(txt);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleContainsEditTextWithoutLog(txt);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false, "Assert Failed : Verify '" + txt + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + txt + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Verify '" + txt + "' " + objtype.getValue());
	}

	/**
	 * verify AndroidElement is visible on page (scroll down and search)
	 *
	 * @param element
	 *            - Input element elementName - Name of the element objtype -
	 *            Enum object types scrollCount - scroll down count (max limit)
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElementOnPage(AndroidElement element, String elementName, ObjectTypes objtype,
			int scrollCount, boolean takeScreenshot) throws Exception {
		boolean value = isVisibleAndroidElementWithoutLog(element);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf(element));
					value = element.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleAndroidElementWithoutLog(element);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleAndroidElementWithoutLog(element);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * verify AndroidElement is visible on page (scroll down and search)
	 *
	 * @param element
	 *            - Input element elementName - Name of the element objtype -
	 *            Enum object types scrollCount - scroll down count (max limit)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElementOnPage(AndroidElement element, String elementName, ObjectTypes objtype,
			int scrollCount) throws Exception {
		boolean value = isVisibleAndroidElementWithoutLog(element);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf(element));
					value = element.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleAndroidElementWithoutLog(element);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleAndroidElementWithoutLog(element);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * verify AndroidElement is visible by ID on page (scroll down and search)
	 *
	 * @param element
	 *            - Input element elementName - Name of the element objtype -
	 *            Enum object types scrollCount - scroll down count (max limit)
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElementByIdOnPage(String id, String elementName, ObjectTypes objtype,
			int scrollCount, boolean takeScreenshot) throws Exception {
		boolean value = isVisibleAndroidElementByIdWithoutLog(id);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
					value = SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")).isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleAndroidElementByIdWithoutLog(id);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleAndroidElementByIdWithoutLog(id);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * verify AndroidElement is visible by Text on page (scroll down and search)
	 *
	 * @param element
	 *            - Input element elementName - Name of the element objtype -
	 *            Enum object types scrollCount - scroll down count (max limit)
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElementByIdOnPage(String id, String elementName, ObjectTypes objtype,
			int scrollCount) throws Exception {
		boolean value = isVisibleAndroidElementByIdWithoutLog(id);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
					value = SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]")).isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleAndroidElementByIdWithoutLog(id);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleAndroidElementByIdWithoutLog(id);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + elementName + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * verify AndroidElement is visible by Text on page (scroll down and search)
	 *
	 * @param element
	 *            - Input element elementName - Name of the element objtype -
	 *            Enum object types scrollCount - scroll down count (max limit)
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElementByTextOnPage(String text, ObjectTypes objtype,
			int scrollCount, boolean takeScreenshot) throws Exception {
		boolean value = isVisibleAndroidElementByTextWithoutLog(text);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@text=\"" + text + "\"]"))));
					value = SingletonClass.getIOSDriver().findElement(By.xpath("//*[@text=\"" + text + "\"]"))
							.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleAndroidElementByTextWithoutLog(text);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleAndroidElementByTextWithoutLog(text);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Assert Passed : Verify '" + text + "' " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + text + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + text + "' " + objtype.getValue());
	}

	/**
	 * verify AndroidElement is visible by Text on page (scroll down and search)
	 *
	 * @param element
	 *            - Input element elementName - Name of the element objtype -
	 *            Enum object types scrollCount - scroll down count (max limit)
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyIsVisibleAndroidElementByTextOnPage(String text, ObjectTypes objtype,
			int scrollCount) throws Exception {
		boolean value = isVisibleAndroidElementByTextWithoutLog(text);

		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@text=\"" + text + "\"]"))));
					value = SingletonClass.getIOSDriver().findElement(By.xpath("//*[@text=\"" + text + "\"]"))
							.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleAndroidElementByTextWithoutLog(text);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleAndroidElementByTextWithoutLog(text);
				}

				if (value == true) {
					break;
				}
			}
		}

		if (value == false) {
			Log.info("Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + text + "' " + objtype.getValue() + " : Not Found");
		}

		Log.info("Assert Passed : Verify '" + text + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + text + "' " + objtype.getValue());
	}

	/**
	 * Click on AndroidElement
	 *
	 * @param text
	 *            - text of the element objtype - Enum object types
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElement(MobileElement element, String elementName, ObjectTypes objtype,
			boolean takeScreenshot) throws Exception {
		boolean value = false;
		// AndroidElement element=null;

		try {
			// element =
			// (AndroidElement)SingletonClass.getIOSDriver().findElement(By.xpath("//android.widget.TextView[@text=\""+text+"\"]"));
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf(element));
			value = element.isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
		} catch(TimeoutException e){
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
		}

		if (value == false) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Click on '" + elementName + "' " + objtype.getValue());
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * Click on AndroidElement
	 *
	 * @param text
	 *            - text of the element objtype - Enum object types
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElement(MobileElement element, String elementName, ObjectTypes objtype) throws Exception {
		boolean value = false;
		// AndroidElement element=null;

		try {
			// element =
			// (AndroidElement)SingletonClass.getIOSDriver().findElement(By.xpath("//android.widget.TextView[@text=\""+text+"\"]"));
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf(element));
			value = element.isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
		} catch(TimeoutException e){
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
		}

		if (value == false) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * Click on AndroidElement by ID
	 *
	 * @param text
	 *            - text of the element objtype - Enum object types
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElementById(String id, String elementName, ObjectTypes objtype, boolean takeScreenshot)
			throws Exception {
		boolean value = false;
		AndroidElement element = null;

		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
			element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
			value = element.isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
		} catch(TimeoutException e){
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
		}

		if (value == false) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Click on '" + elementName + "' " + objtype.getValue());
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * Click on AndroidElement by ID
	 *
	 * @param text
	 *            - text of the element objtype - Enum object types
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElementById(String id, String elementName, ObjectTypes objtype) throws Exception {
		boolean value = false;
		AndroidElement element = null;

		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
			element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
			value = element.isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
		} catch(TimeoutException e){
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
		}

		if (value == false) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + elementName + "' " + objtype.getValue());
	}


	/**
	 * Long press on AndroidElement by text
	 *
	 * @param text
	 *            - text of the element objtype - Enum object types
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Hasitha
	 * @throws Exception
	 */
	public void longPressAndroidElementByText(String txt, String elementName, ObjectTypes objtype) throws Exception {
		boolean value = false;
		AndroidElement element = null;

		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@text=\"" + txt + "\"]"))));
			element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@text=\"" + txt + "\"]"));
			value = element.isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
		} catch(TimeoutException e){
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
		}

		if (value == false) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

//		TouchActions action = new TouchActions(SingletonClass.getIOSDriver());
//		action.longPress(element);
//		action.perform();
//		action.longPress(element).release().perform();
		
		TouchAction action = new TouchAction(SingletonClass.getIOSDriver()).longPress(longPressOptions().withElement(element(element)).withDuration(Duration.ofMillis(3000))).release().perform();
//        Thread.sleep(5000);
		sleep(10);
//		WebElement webElement = SingletonClass.getIOSDriver().findElement(By.xpath(element));

//        TouchAction Action = new TouchAction(appiumDriver);
//        Action.longPress(webElement).release().perform();
        
//		uiClick(element);

		waitForLoading(10);

		Log.info("Long Press on '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Long Press on '" + elementName + "' " + objtype.getValue());
	}

	
	/**
	 * Click on AndroidElement by contains ID
	 *
	 * @param text
	 *            - text of the element objtype - Enum object types
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElementByContainsId(String id, String elementName, ObjectTypes objtype) throws Exception {
		boolean value = false;
		AndroidElement element = null;

		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[contains(@resource-id,\""+id+"\")]"))));
			element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[contains(@resource-id,\""+id+"\")]"));
			value = element.isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
		} catch(TimeoutException e){
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found : " + e);
		}

		if (value == false) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + elementName + "' " + objtype.getValue());
	}
	
	/**
	 * Click on AndroidElement by Text
	 *
	 * @param text
	 *            - text of the element objtype - Enum object types
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElementByText(String text, ObjectTypes objtype, boolean takeScreenshot) throws Exception {
		boolean value = false;
		AndroidElement element = null;

		try {
//			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
//					.findElement(By.xpath("//*[@text=\"" + text + "\"]"))));
//			element = (AndroidElement) SingletonClass.getIOSDriver()
//					.findElement(By.xpath("//*[@text=\"" + text + "\"]"));
//			value = element.isDisplayed();

			new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@text='" + text + "']"))));
			element = (AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@text='" + text + "']"));
			value = element.isDisplayed();
			
		} catch (NoSuchElementException e) {
			Log.info("Click on '" + text + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + text + "' " + objtype.getValue() + " :  Not Found : " + e);
		} catch(TimeoutException e){
			Log.info("Click on '" + text + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + text + "' " + objtype.getValue() + " :  Not Found : " + e);
		}

		if (value == false) {
			Log.info("Click on '" + text + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + text + "' " + objtype.getValue() + " :  Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Click on '" + text + "' " + objtype.getValue());
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + text + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + text + "' " + objtype.getValue());
	}

	/**
	 * Click on AndroidElement by Text
	 *
	 * @param text
	 *            - text of the element objtype - Enum object types
	 *            takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElementByText(String text, ObjectTypes objtype) throws Exception {
		boolean value = false;
		AndroidElement element = null;

		try {
			new WebDriverWait(SingletonClass.getIOSDriver(), 30).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@text=\"" + text + "\"]"))));
			element = (AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@text=\"" + text + "\"]"));
			value = element.isDisplayed();

		} catch (NoSuchElementException e) {
			Log.info("Click on '" + text + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + text + "' " + objtype.getValue() + " :  Not Found : " + e);
		} catch(TimeoutException e){
			Log.info("Click on '" + text + "' " + objtype.getValue() + " :  Not Found : " + e);
			Assert.assertTrue(false, "Click on '" + text + "' " + objtype.getValue() + " :  Not Found : " + e);
		}

		if (value == false) {
			Log.info("Click on '" + text + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + text + "' " + objtype.getValue() + " :  Not Found");
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + text + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + text + "' " + objtype.getValue());
	}

	/**
	 * Click on AndroidElement on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit) takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElementOnPage(MobileElement element, String elementName, ObjectTypes objtype,
			int scrollCount, boolean takeScreenshot) throws Exception {
		boolean value = isVisibleAndroidElementWithoutLog(element);
		// AndroidElement element=null;

		if (value == false) {
			for (int i = 0; i < scrollCount + 1; i++) {
				try {
					// element =
					// (AndroidElement)SingletonClass.getIOSDriver().findElement(By.xpath("//android.widget.TextView[@text=\""
					// + text + "\"]"));
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf(element));
					value = element.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
				}

				if (value == true) {
					break;
				}
			}
		} else {
			// element =
			// (AndroidElement)SingletonClass.getIOSDriver().findElement(By.xpath("//android.widget.TextView[@text=\""
			// + text + "\"]"));
		}

		if (value == false) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Click on '" + elementName + "' " + objtype.getValue());
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * Click on AndroidElement on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit) takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElementOnPage(MobileElement element, String elementName, ObjectTypes objtype,
			int scrollCount) throws Exception {
		boolean value = isVisibleAndroidElementWithoutLog(element);
		// AndroidElement element=null;

		if (value == false) {
			for (int i = 0; i < scrollCount + 1; i++) {
				try {
					// element =
					// (AndroidElement)SingletonClass.getIOSDriver().findElement(By.xpath("//android.widget.TextView[@text=\""
					// + text + "\"]"));
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf(element));
					value = element.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
				}

				if (value == true) {
					break;
				}
			}
		} else {
			// element =
			// (AndroidElement)SingletonClass.getIOSDriver().findElement(By.xpath("//android.widget.TextView[@text=\""
			// + text + "\"]"));
		}

		if (value == false) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * Click on AndroidElement by ID on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit) takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElementByIdOnPage(String id, String elementName, ObjectTypes objtype, int scrollCount,
			boolean takeScreenshot) throws Exception {
		boolean value = isVisibleAndroidElementByIdWithoutLog(id);
		AndroidElement element = null;

		if (value == false) {
			for (int i = 0; i < scrollCount + 1; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
					element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
					value = element.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
				}

				if (value == true) {
					break;
				}
			}
		} else {
			element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
		}

		if (value == false) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Click on '" + elementName + "' " + objtype.getValue());
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * Click on AndroidElement by ID on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit) takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElementByIdOnPage(String id, String elementName, ObjectTypes objtype, int scrollCount)
			throws Exception {
		boolean value = isVisibleAndroidElementByIdWithoutLog(id);
		AndroidElement element = null;

		if (value == false) {
			for (int i = 0; i < scrollCount + 1; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
					element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
					value = element.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
				}

				if (value == true) {
					break;
				}
			}
		} else {
			element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
		}

		if (value == false) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + elementName + "' " + objtype.getValue());
	}

	/**
	 * Click on AndroidElement by ID on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit) takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElementByIdOnPage2(MobileElement id, String elementName, ObjectTypes objtype, int scrollCount)
			throws Exception {
		boolean value = isVisibleAndroidElementByIdWithoutLog1(id);
		AndroidElement element = null;

		if (value == false) {
			for (int i = 0; i < scrollCount + 1; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@resource-id=\""+id+"\"]"))));
					element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
					value = element.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
				}

				if (value == true) {
					break;
				}
			}
		} else {
			element = (AndroidElement) SingletonClass.getIOSDriver().findElement(By.xpath("//*[@resource-id=\""+id+"\"]"));
		}

		if (value == false) {
			Log.info("Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + elementName + "' " + objtype.getValue() + " :  Not Found");
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + elementName + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + elementName + "' " + objtype.getValue());
	}
	
	/**
	 * Click on AndroidElement by Text on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit) takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElementByTextOnPage(String text, ObjectTypes objtype, int scrollCount,
			boolean takeScreenshot) throws Exception {
		boolean value = isVisibleAndroidElementByTextWithoutLog(text);
		AndroidElement element = null;

		if (value == false) {
			for (int i = 0; i < scrollCount + 1; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 5).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@text=\"" + text + "\"]"))));
					element = (AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@text=\"" + text + "\"]"));
					value = element.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
				}

				if (value == true) {
					break;
				}
			}
		} else {
			element = (AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@text=\"" + text + "\"]"));
		}

		if (value == false) {
			Log.info("Click on '" + text + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + text + "' " + objtype.getValue() + " :  Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Click on '" + text + "' " + objtype.getValue());
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + text + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + text + "' " + objtype.getValue());
	}

	/**
	 * Click on AndroidElement by Text on page (scroll down and search)
	 *
	 * @param txt
	 *            - Input text objtype - Enum object types scrollCount - scroll
	 *            down count (max limit) takeScreenshot - take a screen shot
	 *            (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void clickAndroidElementByTextOnPage(String text, ObjectTypes objtype, int scrollCount)
			throws Exception {
		boolean value = isVisibleAndroidElementByTextWithoutLog(text);
		AndroidElement element = null;

		if (value == false) {
			for (int i = 0; i < scrollCount + 1; i++) {
				try {
					new WebDriverWait(SingletonClass.getIOSDriver(), 10).ignoring(TimeoutException.class).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@text=\"" + text + "\"]"))));
					element = (AndroidElement) SingletonClass.getIOSDriver()
							.findElement(By.xpath("//*[@text=\"" + text + "\"]"));
					value = element.isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);

				}catch (TimeoutException e){
					swipeUpOrDown(0.5, 0.2, 3000);
				}

				if (value == true) {
					break;
				}
			}
		} else {
			element = (AndroidElement) SingletonClass.getIOSDriver()
					.findElement(By.xpath("//*[@text=\"" + text + "\"]"));
		}

		if (value == false) {
			Log.info("Click on '" + text + "' " + objtype.getValue() + " :  Not Found");
			Assert.assertTrue(false, "Click on '" + text + "' " + objtype.getValue() + " :  Not Found");
		}

		uiClick(element);

		waitForLoading(10);

		Log.info("Click on '" + text + "' " + objtype.getValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on '" + text + "' " + objtype.getValue());
	}

	/**
	 * Verify Assert Equals
	 *
	 * @param actual
	 *            - Actual result/runtime expected - Expected msg - Customizable
	 *            message takeScreenshot - take a screen shot (true-enable/
	 *            false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyAssertEquals(Object actual, Object expected, String msg, boolean takeScreenshot)
			throws Exception {
		if (actual.equals(expected)) {
			Assert.assertEquals(actual, expected, "Assert Failed : " + msg + " : " + expected + ", ");
			if (takeScreenshot) {
				takeScreenshot("Assert Passed : " + msg + " : " + expected);
			}
			Log.info("Assert Passed : " + msg + " : " + expected);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : " + msg + " : " + expected);

		} else {

			Log.info("Assert Failed : " + msg + " : " + expected + ", but found " + actual);
			Assert.assertEquals(actual, expected, "Assert Failed : " + msg + " : " + expected + ", ");
		}
	}

	/**
	 * Verify Assert Equals
	 *
	 * @param actual
	 *            - Actual result/runtime expected - Expected msg - Customizable
	 *            message
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyAssertEquals(Object actual, Object expected, String msg) throws Exception {
		if (actual.equals(expected)) {
			Assert.assertEquals(actual, expected, "Assert Failed : " + msg + " : " + expected + ", ");
			Log.info("Assert Passed : " + msg + " : " + expected);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : " + msg + " : " + expected);

		} else {

			Log.info("Assert Failed : " + msg + " : " + expected + ", but found " + actual);
			Assert.assertEquals(actual, expected, "Assert Failed : " + msg + " : " + expected + ", ");
		}
	}

	/**
	 * Verify Assert True
	 *
	 * @param condition
	 *            - true/false msg - Customizable message takeScreenshot - take
	 *            a screen shot (true-enable/ false-disable)
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyAssertTrue(boolean condition, String msg, boolean takeScreenshot) throws Exception {
		if (condition == true) {
			Assert.assertTrue(condition, "Assert Passed : " + msg + ", ");
			if (takeScreenshot) {
				takeScreenshot("Assert Passed : " + msg);
			}
			Log.info("Assert Passed : " + msg);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : " + msg);

		} else {
			Log.info("Assert Failed : " + msg);
			Assert.assertTrue(condition, "Assert Failed : " + msg + ", ");
		}
	}

	/**
	 * Verify Assert True
	 *
	 * @param condition
	 *            - true/false msg - Customizable message
	 * @author Kasun
	 * @throws Exception
	 */
	public void verifyAssertTrue(boolean condition, String msg) throws Exception {
		if (condition == true) {
			Assert.assertTrue(condition, "Assert Failed : " + msg + ", ");
			Log.info("Assert Passed : " + msg);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : " + msg);

		} else {
			Log.info("Assert Failed : " + msg);
			Assert.assertTrue(condition, "Assert Failed : " + msg + ", ");
		}
	}
	
	/**
	 * click on screen according to coordinates
	 *
	 * @param - x, y coordinates of the tap location
	 * 
	 * @author Kasun Yapa
	 */
	public void clickPoint(int x,int y) {
		TouchAction action= new TouchAction(SingletonClass.getIOSDriver());
		action.tap(PointOption.point(x, y)).perform();
		//action.tap(PointOption.point(299, 437)).perform();
	}
	
	
	public void assertTwoTexts(String text,String expectedText,String logMessege) {
		//boolean value3 = false;
		try {
			if(text.equals(expectedText)) {
				assertTrue(true);
				Log.info("Assert Passed for verify "+ logMessege);
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Assert Passed for verify "+logMessege);
			}else {
				assertTrue(false);
				Log.info("Assert Failed for verify "+logMessege);
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Assert Failed for verify "+logMessege);
				
			}
		} catch (Exception e) {
			
		}
		
	}
}
