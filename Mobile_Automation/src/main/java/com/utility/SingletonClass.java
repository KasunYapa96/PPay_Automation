package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.chivelab.pageObjAndroid.BasePageObject;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;
import com.microsoft.appcenter.appium.Factory;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.ExtentReports.ExtentTestManager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class SingletonClass {
	@SuppressWarnings("rawtypes")
	
	private static AndroidDriver driver;
	private static IOSDriver driverIOS;
	
	private static EnhancedAndroidDriver<MobileElement> driver_1;
	private static AndroidDriver driver_2;
	private static AndroidDriver driver_3;
	private static AndroidDriver driver_4;
	
	private static EnhancedIOSDriver<MobileElement> driverIOS_1;
	private static IOSDriver driverIOS_2;
	private static IOSDriver driverIOS_3;
	private static IOSDriver driverIOS_4;
	
	private static Properties uiProp;
	private static Properties uiProp2;
	private static Properties testData;
	private static Properties expectedResults;
	private static SingletonClass obj;
	public static WebDriverWait wait;
	public String env;
	private static SingletonClass singletonClass;
	private static int count1 = 0;
	private static Map<Object, Object> cacheMap;

	public String getCounter() {
		count1++;
		String count = Integer.toString(count1);
		return count;
	}

	private SingletonClass() {

	}

	public static final SingletonClass getInstance() {
		if (singletonClass == null) {
			singletonClass = new SingletonClass();
		}
		return singletonClass;
	}
	
	
	
	 static String workingDir = System.getProperty("user.dir");
	 static File path1 = new File(workingDir+"\\Apps\\app-release (21).apk");
	 
	 static File path2 = new File(workingDir+"\\Apps\\app-release (21).apk");
	 
	 

	@SuppressWarnings("rawtypes")
	public static void setAndroidDriver(String environment) throws MalformedURLException {
		if (environment == "device_1") {
			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("udid", SingletonClass.getFrameWorkProp("1_udid"));
				capabilities.setCapability("systemPort",
						Integer.parseInt(SingletonClass.getFrameWorkProp("1_systemPort")));
				capabilities.setCapability("newCommandTimeout",
						Integer.parseInt(SingletonClass.getFrameWorkProp("1_newCommandTimeout")));
				capabilities.setCapability("deviceName", SingletonClass.getFrameWorkProp("1_deviceName"));
				capabilities.setCapability("platformName", SingletonClass.getFrameWorkProp("1_platformName"));
				capabilities.setCapability(CapabilityType.VERSION,
						SingletonClass.getFrameWorkProp("1_capabilityType.VERSION"));
				capabilities.setCapability("noReset", SingletonClass.getFrameWorkProp("1_noReset"));
				capabilities.setCapability("appPackage", SingletonClass.getFrameWorkProp("1_appPackage"));
				capabilities.setCapability("appActivity", SingletonClass.getFrameWorkProp("1_appActivity"));
				capabilities.setCapability("app", path1.getAbsolutePath());
				
				//https://discuss.appium.io/t/why-it-install-app-everytime-if-app-already-there-in-device/3574
				//App path for installation 
				
				driver_1 = Factory.createAndroidDriver(new URL(SingletonClass.getFrameWorkProp("1_URL")), capabilities);
				Log.info("Setup android driver - success ------------>");
			} catch (Exception e) {
				Log.info("Setup android driver - fail ------------>" + e.getMessage());
			}

		} else if (environment == "device_2") {
			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("udid", SingletonClass.getFrameWorkProp("2_udid"));
				capabilities.setCapability("systemPort",
						Integer.parseInt(SingletonClass.getFrameWorkProp("2_systemPort")));
				capabilities.setCapability("newCommandTimeout",
						Integer.parseInt(SingletonClass.getFrameWorkProp("2_newCommandTimeout")));
				capabilities.setCapability("deviceName", SingletonClass.getFrameWorkProp("2_deviceName"));
				capabilities.setCapability("platformName", SingletonClass.getFrameWorkProp("2_platformName"));
				capabilities.setCapability(CapabilityType.VERSION,
						SingletonClass.getFrameWorkProp("2_capabilityType.VERSION"));
				capabilities.setCapability("noReset", SingletonClass.getFrameWorkProp("2_noReset"));
				capabilities.setCapability("appPackage", SingletonClass.getFrameWorkProp("2_appPackage"));
				capabilities.setCapability("appActivity", SingletonClass.getFrameWorkProp("2_appActivity"));
				driver_2 = new AndroidDriver(new URL(SingletonClass.getFrameWorkProp("2_URL")), capabilities);
				Log.info("Setup android driver - success ------------>");
			} catch (Exception e) {
				Log.info("Setup android driver - success ------------>" + e.getMessage());
			}

		} else if (environment == "device_3") {
			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("udid", SingletonClass.getFrameWorkProp("3_udid"));
				capabilities.setCapability("systemPort",
						Integer.parseInt(SingletonClass.getFrameWorkProp("3_systemPort")));
				capabilities.setCapability("newCommandTimeout",
						Integer.parseInt(SingletonClass.getFrameWorkProp("3_newCommandTimeout")));
				capabilities.setCapability("deviceName", SingletonClass.getFrameWorkProp("3_deviceName"));
				capabilities.setCapability("platformName", SingletonClass.getFrameWorkProp("3_platformName"));
				capabilities.setCapability(CapabilityType.VERSION,
						SingletonClass.getFrameWorkProp("3_capabilityType.VERSION"));
				capabilities.setCapability("noReset", SingletonClass.getFrameWorkProp("3_noReset"));
				capabilities.setCapability("appPackage", SingletonClass.getFrameWorkProp("3_appPackage"));
				capabilities.setCapability("appActivity", SingletonClass.getFrameWorkProp("3_appActivity"));
				driver_3 = new AndroidDriver(new URL(SingletonClass.getFrameWorkProp("3_URL")), capabilities);
				Log.info("Setup android driver - success ------------>");
			} catch (Exception e) {
				Log.info("Setup android driver - success ------------>" + e.getMessage());
			}

		} else if (environment == "device_4") {
			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("udid", SingletonClass.getFrameWorkProp("4_udid"));
				capabilities.setCapability("systemPort",
						Integer.parseInt(SingletonClass.getFrameWorkProp("4_systemPort")));
				capabilities.setCapability("newCommandTimeout",
						Integer.parseInt(SingletonClass.getFrameWorkProp("4_newCommandTimeout")));
				capabilities.setCapability("deviceName", SingletonClass.getFrameWorkProp("4_deviceName"));
				capabilities.setCapability("platformName", SingletonClass.getFrameWorkProp("4_platformName"));
				capabilities.setCapability(CapabilityType.VERSION,
						SingletonClass.getFrameWorkProp("4_capabilityType.VERSION"));
				capabilities.setCapability("noReset", SingletonClass.getFrameWorkProp("4_noReset"));
				capabilities.setCapability("appPackage", SingletonClass.getFrameWorkProp("4_appPackage"));
				capabilities.setCapability("appActivity", SingletonClass.getFrameWorkProp("4_appActivity"));
				driver_4 = new AndroidDriver(new URL(SingletonClass.getFrameWorkProp("4_URL")), capabilities);
				Log.info("Setup android driver - success ------------>");
			} catch (Exception e) {
				Log.info("Setup android driver - success ------------>" + e.getMessage());
			}

		}

		driver = null;
	}
	
	
	@SuppressWarnings("rawtypes")
	public static void setIOSDriver(String environment) throws MalformedURLException {
		if (environment == "device_1") {
			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("udid", SingletonClass.getFrameWorkProp2("1_udid"));
				capabilities.setCapability("systemPort",
						Integer.parseInt(SingletonClass.getFrameWorkProp2("1_systemPort")));
				capabilities.setCapability("newCommandTimeout",
						Integer.parseInt(SingletonClass.getFrameWorkProp2("1_newCommandTimeout")));
				capabilities.setCapability("deviceName", SingletonClass.getFrameWorkProp2("1_deviceName"));
				capabilities.setCapability("platformName", SingletonClass.getFrameWorkProp2("1_platformName"));
				capabilities.setCapability(CapabilityType.VERSION,
						SingletonClass.getFrameWorkProp2("1_capabilityType.VERSION"));
				capabilities.setCapability("noReset", SingletonClass.getFrameWorkProp2("1_noReset"));
				capabilities.setCapability("appPackage", SingletonClass.getFrameWorkProp2("1_appPackage"));
				capabilities.setCapability("appActivity", SingletonClass.getFrameWorkProp2("1_appActivity"));
				capabilities.setCapability("app", path2.getAbsolutePath());
				driverIOS_1 = Factory.createIOSDriver(new URL(SingletonClass.getFrameWorkProp("1_URL")), capabilities);
				Log.info("Setup android driver - success ------------>");
			} catch (Exception e) {
				Log.info("Setup android driver - fail ------------>" + e.getMessage());
			}

		} else if (environment == "device_2") {
			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("udid", SingletonClass.getFrameWorkProp("2_udid"));
				capabilities.setCapability("systemPort",
						Integer.parseInt(SingletonClass.getFrameWorkProp("2_systemPort")));
				capabilities.setCapability("newCommandTimeout",
						Integer.parseInt(SingletonClass.getFrameWorkProp("2_newCommandTimeout")));
				capabilities.setCapability("deviceName", SingletonClass.getFrameWorkProp("2_deviceName"));
				capabilities.setCapability("platformName", SingletonClass.getFrameWorkProp("2_platformName"));
				capabilities.setCapability(CapabilityType.VERSION,
						SingletonClass.getFrameWorkProp("2_capabilityType.VERSION"));
				capabilities.setCapability("noReset", SingletonClass.getFrameWorkProp("2_noReset"));
				capabilities.setCapability("appPackage", SingletonClass.getFrameWorkProp("2_appPackage"));
				capabilities.setCapability("appActivity", SingletonClass.getFrameWorkProp("2_appActivity"));
				driver_2 = new AndroidDriver(new URL(SingletonClass.getFrameWorkProp("2_URL")), capabilities);
				Log.info("Setup android driver - success ------------>");
			} catch (Exception e) {
				Log.info("Setup android driver - success ------------>" + e.getMessage());
			}

		} else if (environment == "device_3") {
			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("udid", SingletonClass.getFrameWorkProp("3_udid"));
				capabilities.setCapability("systemPort",
						Integer.parseInt(SingletonClass.getFrameWorkProp("3_systemPort")));
				capabilities.setCapability("newCommandTimeout",
						Integer.parseInt(SingletonClass.getFrameWorkProp("3_newCommandTimeout")));
				capabilities.setCapability("deviceName", SingletonClass.getFrameWorkProp("3_deviceName"));
				capabilities.setCapability("platformName", SingletonClass.getFrameWorkProp("3_platformName"));
				capabilities.setCapability(CapabilityType.VERSION,
						SingletonClass.getFrameWorkProp("3_capabilityType.VERSION"));
				capabilities.setCapability("noReset", SingletonClass.getFrameWorkProp("3_noReset"));
				capabilities.setCapability("appPackage", SingletonClass.getFrameWorkProp("3_appPackage"));
				capabilities.setCapability("appActivity", SingletonClass.getFrameWorkProp("3_appActivity"));
				driver_3 = new AndroidDriver(new URL(SingletonClass.getFrameWorkProp("3_URL")), capabilities);
				Log.info("Setup android driver - success ------------>");
			} catch (Exception e) {
				Log.info("Setup android driver - success ------------>" + e.getMessage());
			}

		} else if (environment == "device_4") {
			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("udid", SingletonClass.getFrameWorkProp("4_udid"));
				capabilities.setCapability("systemPort",
						Integer.parseInt(SingletonClass.getFrameWorkProp("4_systemPort")));
				capabilities.setCapability("newCommandTimeout",
						Integer.parseInt(SingletonClass.getFrameWorkProp("4_newCommandTimeout")));
				capabilities.setCapability("deviceName", SingletonClass.getFrameWorkProp("4_deviceName"));
				capabilities.setCapability("platformName", SingletonClass.getFrameWorkProp("4_platformName"));
				capabilities.setCapability(CapabilityType.VERSION,
						SingletonClass.getFrameWorkProp("4_capabilityType.VERSION"));
				capabilities.setCapability("noReset", SingletonClass.getFrameWorkProp("4_noReset"));
				capabilities.setCapability("appPackage", SingletonClass.getFrameWorkProp("4_appPackage"));
				capabilities.setCapability("appActivity", SingletonClass.getFrameWorkProp("4_appActivity"));
				driver_4 = new AndroidDriver(new URL(SingletonClass.getFrameWorkProp("4_URL")), capabilities);
				Log.info("Setup android driver - success ------------>");
			} catch (Exception e) {
				Log.info("Setup android driver - success ------------>" + e.getMessage());
			}

		}

		driverIOS = null;
	}
	
	
	
	
	

	@SuppressWarnings("rawtypes")
	public static EnhancedAndroidDriver<MobileElement> getAndroidDriver() {

		if (driver == null) {
			driver = driver_1;
		} else {

			if (!(driver_2 == null)) {
				if (!(driver_2.getSessionId() == null) & driver != driver_1) {
					if (driver.getSessionId().equals(driver_2.getSessionId())) {
						driver = getAndroidDriver_2();
					}

				}
			}

			else if (!(driver_3 == null)) {
				if (!(driver_3.getSessionId() == null) & driver != driver_1) {
					if (driver.getSessionId().equals(driver_3.getSessionId())) {
						driver = getAndroidDriver_3();
					}

				}
			}

			else if (!(driver_4 == null)) {
				if (!(driver_4.getSessionId() == null) & driver != driver_1) {
					if (driver.getSessionId().equals(driver_4.getSessionId())) {
						driver = getAndroidDriver_4();
					}
				}
			}
			
			else{
				driver = driver_1;
			}
		}

		return (EnhancedAndroidDriver<MobileElement>) driver;
	}
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	public static EnhancedIOSDriver<MobileElement> getIOSDriver() {

		if (driverIOS == null) {
			driverIOS = driverIOS_1;
		} else {

			if (!(driverIOS_2 == null)) {
				if (!(driverIOS_2.getSessionId() == null) & driverIOS != driverIOS_1) {
					if (driverIOS.getSessionId().equals(driverIOS_2.getSessionId())) {
						driverIOS = getIOSDriver_2();
					}

				}
			}

			else if (!(driverIOS_3 == null)) {
				if (!(driverIOS_3.getSessionId() == null) & driverIOS != driverIOS_1) {
					if (driverIOS.getSessionId().equals(driverIOS_3.getSessionId())) {
						driverIOS = getIOSDriver_3();
					}

				}
			}

			else if (!(driverIOS_4 == null)) {
				if (!(driverIOS_4.getSessionId() == null) & driverIOS != driverIOS_1) {
					if (driverIOS.getSessionId().equals(driverIOS_4.getSessionId())) {
						driverIOS = getIOSDriver_4();
					}
				}
			}
			
			else{
				driverIOS = driverIOS_1;
			}
		}

		return (EnhancedIOSDriver<MobileElement>) driverIOS;
	}
	
	
	
	
	

	@SuppressWarnings("rawtypes")
	public static AndroidDriver getAndroidDriver_2() {
		return driver_2;
	}

	@SuppressWarnings("rawtypes")
	public static AndroidDriver getAndroidDriver_3() {
		return driver_3;
	}

	@SuppressWarnings("rawtypes")
	public static AndroidDriver getAndroidDriver_4() {
		return driver_4;
	}

	@SuppressWarnings("rawtypes")
	public static AndroidDriver switchDevice_1() {
		driver = driver_1;
		Log.info("Switch to device 1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Switch to device 1");
		return driver;
	}

	@SuppressWarnings("rawtypes")
	public static AndroidDriver switchDevice_2() {
		driver = getAndroidDriver_2();
		Log.info("Switch to device 2");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Switch to device 2");
		return driver;
	}

	@SuppressWarnings("rawtypes")
	public static AndroidDriver switchDevice_3() {
		driver = getAndroidDriver_3();
		Log.info("Switch to device 3");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Switch to device 3");
		return driver;
	}

	@SuppressWarnings("rawtypes")
	public static AndroidDriver switchDevice_4() {
		driver = getAndroidDriver_4();
		Log.info("Switch to device 4");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Switch to device 4");
		return driver;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public static IOSDriver getIOSDriver_2() {
		return driverIOS_2;
	}

	@SuppressWarnings("rawtypes")
	public static IOSDriver getIOSDriver_3() {
		return driverIOS_3;
	}
	
	@SuppressWarnings("rawtypes")
	public static IOSDriver getIOSDriver_4() {
		return driverIOS_4;
	}
	
	

	public static String getFrameWorkProp(String key) {
		return uiProp.getProperty(key);
	}

	public static void setFrameWorkProp() {

		uiProp = new Properties();
		try {
			uiProp.load(new FileInputStream("src/test/resources/TestData/arimacFramework.properties"));
			Log.info("Initialize FrameWork Property file - success ------------>");
		} catch (IOException e) {
			Log.info("Initialize FrameWork Property file - fail ------------>" + e.getMessage());
		}

	}
	
	public static String getFrameWorkProp2(String key) {
		return uiProp2.getProperty(key);
	}
	
	public static void setFrameWorkProp2() {

		uiProp2 = new Properties();
		try {
			uiProp2.load(new FileInputStream("src/test/resources/TestData/arimacFramework2.properties"));
			Log.info("Initialize FrameWork Property file - success ------------>");
		} catch (IOException e) {
			Log.info("Initialize FrameWork Property file - fail ------------>" + e.getMessage());
		}

	}
	
	
	

	public static String getTestData(String key) {
		return testData.getProperty(key);
	}

	public static void setTestData() {

		testData = new Properties();
		try {
			testData.load(new FileInputStream("src/test/resources/TestData/testData.properties"));
			Log.info("Initialize TestData Property file - success ------------>");
		} catch (IOException e) {
			Log.info("Initialize TestData Property file - fail ------------>" + e.getMessage());
		}

	}

	public static String getExpectedResults(String key) {
		return expectedResults.getProperty(key);
	}

	public static void setExpectedResults() {

		expectedResults = new Properties();
		try {
			expectedResults.load(new FileInputStream("src/test/resources/TestData/expectedResults.properties"));
			Log.info("Initialize ExpectedResults Property file - success ------------>");
		} catch (IOException e) {
			Log.info("Initialize ExpectedResults Property file - fail ------------>" + e.getMessage());
		}

	}

	public static SingletonClass getObj() {
		return obj;
	}

	public static void setObj(SingletonClass obj) {
		SingletonClass.obj = obj;
	}

	public int Sleep(int waitingTime) {
		return 0;
	}

	public static void setCacheMap() {
		cacheMap = new HashMap<Object, Object>();
	}

	public static void putObjCacheMap(Object key, Object value) {
		cacheMap.put(key, value);
	}

	public static Object getObjCacheMap(Object key) {
		return cacheMap.get(key);
	}

	
	
	public static void AndroidDriverCloseApp() {
		try {
			driver = null;
			if(driver_1 != null){
				driver_1.closeApp();
//				driver_1.quit();
			}
			if(driver_2 != null){
				driver_2.closeApp();
//				driver_2.quit();

			}
			if(driver_3 != null){
				driver_3.closeApp();
//				driver_3.quit();

			}
			if(driver_4 != null){
				driver_4.closeApp();
//				driver_4.quit();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void IOSDriverCloseApp() {
		try {
			driverIOS = null;
			if(driverIOS_1 != null){
				driverIOS_1.closeApp();
//				driver_1.quit();
			}
			if(driverIOS_2 != null){
				driverIOS_2.closeApp();
//				driver_2.quit();

			}
			if(driverIOS_3 != null){
				driverIOS_3.closeApp();
//				driver_3.quit();

			}
			if(driverIOS_4 != null){
				driverIOS_4.closeApp();
//				driver_4.quit();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	public static void appClose() {
		try {
			if (!(driver_1 == null)) {
				driver = driver_1;
				appClose(0.5, 0.1, 1000);

			} 
			if (!(driver_2 == null)) {
				driver = getAndroidDriver_2();
				appClose(0.5, 0.1, 1000);

			} 
			if (!(driver_3 == null)) {
				driver = getAndroidDriver_3();
				appClose(0.5, 0.1, 1000);

			} 
			if (!(driver_4 == null)) {
				driver = getAndroidDriver_4();
				appClose(0.5, 0.1, 1000);
			}
			driver = null;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static void appCloseIOS() {
		try {
			if (!(driverIOS_1 == null)) {
				driverIOS = driverIOS_1;
				appClose(0.5, 0.1, 1000);

			} 
			if (!(driverIOS_2 == null)) {
				driverIOS = getIOSDriver_2();
				appClose(0.5, 0.1, 1000);

			} 
			if (!(driverIOS_3 == null)) {
				driverIOS =  getIOSDriver_3();
				appClose(0.5, 0.1, 1000);

			} 
			if (!(driverIOS_4 == null)) {
				driverIOS = getIOSDriver_4();
				appClose(0.5, 0.1, 1000);
			}
			driverIOS = null;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

	public static void appClose(double startyFactor, double endyFactor, int waitTime) {

		driver.pressKeyCode(187);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {

			Dimension size = driver.manage().window().getSize();
			Log.info(size.toString());
			int starty = (int) (size.height * startyFactor);
			int endy = (int) (size.height * endyFactor);
			int startx = size.width / 2;
			TouchAction touchAction = new TouchAction(driver);
			touchAction.press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(waitTime)))
					.moveTo(PointOption.point(startx, endy)).release().perform();

			Log.info("Swipe up App close");

		} catch (Exception e) {

			ExtentTestManager.getTest().log(LogStatus.FAIL, "Scroll Up fail: App close " + e.getMessage());
			Log.info("Scroll Up fail: App close " + e.getMessage());
		}
		
		driver.navigate().back();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
