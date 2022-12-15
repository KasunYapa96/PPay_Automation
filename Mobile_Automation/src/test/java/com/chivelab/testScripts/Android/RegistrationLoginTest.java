package com.chivelab.testScripts.Android;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.chivelab.pageObjAndroid.BasePageObject;
import com.chivelab.pageObjAndroid.HomePage;
import com.chivelab.pageObjAndroid.RegistrationLoginPage;
import com.common.listeners.TestListener;
import com.relevantcodes.extentreports.LogStatus;
import com.utility.Log;
import com.utility.ObjectTypes;
import com.utility.Retry;
import com.utility.SingletonClass;
import com.utils.ExtentReports.ExtentTestManager;

import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;


@Listeners(TestListener.class)
public class RegistrationLoginTest extends BasePageObject{
	
    @Rule
    public TestWatcher watcher = Factory.createWatcher();
	
	RegistrationLoginPage registrationLoginPage;
	
	@SuppressWarnings("unchecked")
	@BeforeMethod
	public void setUpEnv() throws MalformedURLException {
		SingletonClass.setAndroidDriver("device_1");
		
		//registrationLoginPage = new RegistrationLoginPage(SingletonClass.getAndroidDriver());
	}
	
	
	@AfterTest
	public void forceDown() {
		SingletonClass.AndroidDriverCloseApp();

	}
	

	@AfterMethod
	public void tearDown() {
		SingletonClass.appClose();
	}
	
	public int getRandomNumber() {
        
        int generatedPassword = (int) (Math.random() * (9999 - 1000)) + 1000;

       return generatedPassword;
    }
	

	final String randomNumber = String.valueOf(getRandomNumber());
	
	List<String> excludedCode=new ArrayList<String>(); 
	
	
	@Parameters({})
	@Test(retryAnalyzer = Retry.class, priority = 1, testName = "RegLogin_001- Navigate to registration page and register as a new user", enabled = true, singleThreaded = true)
	public void RegLogin_001() throws Exception {
		
		ExtentTestManager.getTest().setDescription("RegLogin_001- Navigate to registration page and register as a new user");


		sleep(2);
		
		waitForElementByText("Search here...", 5);
			
		// verify view profile text of landing page
		verifyIsVisibleAndroidElementByText("Search here...", ObjectTypes.TEXT_FIELD_VALUE,true);
		
		// verify background image
		takeScreenshot("Background image as per the given UI");
		
		uiClickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ImageView[2]");
		// verify view profile text of landing page
		//verifyIsVisibleAndroidElementByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup", "Theme Changer", ObjectTypes.TOGGLE);
		

		
		// verify profile section by exsisting text
			verifyIsVisibleAndroidElementByText("Get on board with", ObjectTypes.TEXT,true);
			
			
			waitForElementByText("Sign in / Sign up", 5);
			
			sleep(3);
			
			//verify login button element by id 
			verifyIsVisibleAndroidElementByText("Sign in / Sign up",ObjectTypes.BUTTON, true);
			
			//click sign in button
			clickAndroidElementByText("Sign in / Sign up", ObjectTypes.BUTTON);
			
			
			
			takeScreenshot("Background image as per the given UI");
			
			
			waitForElementByText("Welcome back!", 5);
			
			
		String text =	uiGetTextByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[1]");
		System.out.println(text);
		
		String expectedText = "Welcome back!";
		
		sleep(2);
		
		assertTwoTexts(text, expectedText, "Sign in / Sign up page welcome text");
		
		sleep(3);
	
//		waitForElementByText("Sign Up", 5);
//		
//		//click signUp button
//		clickAndroidElementByText("Sign Up", ObjectTypes.BUTTON);
		
		
		waitForElementByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[5]/android.widget.TextView", 3);
		
		uiClickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[5]/android.widget.TextView");
		
		
		//Welcome text signin / signUp page
		waitForElementByText("Welcome back!", 5);
		
		
		String text2 =	uiGetTextByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[1]");
		System.out.println(text2);
		
		String expectedText2 ="Welcome onboard";
		
		assertTwoTexts(text2, expectedText2, "Sign Up page welcome Onboard text");
		
		
		
		
		//verify fullname input element by id 
		verifyIsVisibleAndroidElementById("signup.name", "text box", ObjectTypes.TEXT_BOX, true);
				
		uiSendKeysById("signup.name", "marc"+randomNumber+" Perera");
		
		
		//verify email input element by id 
		verifyIsVisibleAndroidElementById("signup.email", "Email box", ObjectTypes.TEXT_BOX, true);
		
		uiSendKeysById("signup.email", "marc"+randomNumber+"@gmail.com");
		
		
		//verify email input element by id 
		verifyIsVisibleAndroidElementById("signup.password", "PW box", ObjectTypes.TEXT_BOX, true);
		
		
		uiSendKeysById("signup.password", "Pass@"+randomNumber);
	
		
		
		takeScreenshot("Background image as per the given UI");
		
		
		//click sign in button
		clickAndroidElementByText("Sign Up", ObjectTypes.BUTTON);
		
		sleep(2);
		
		waitForElementByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[4]/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.TextView", 3);
		
		takeScreenshot("Background image as per the given UI");
		
		
		//verify profile button 
		verifyIsVisibleAndroidElementByText("My profile",ObjectTypes.NAVIGATION_LINK, true);

		//click my profile button
		clickAndroidElementByText("My profile", ObjectTypes.NAVIGATION_LINK);
		
		takeScreenshot("Background image as per the given UI");
		
	
		String text3 =	uiGetTextByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[1]");
		System.out.println(text3);
		
		String expectedText3 ="marc"+randomNumber+" Perera";
		
		assertTwoTexts(text3,expectedText3,"assert the name of new account");
		
		
		
	}
	
	
	
	
	@Parameters({})
	@Test(retryAnalyzer = Retry.class, priority = 2, testName = "RegLogin_002- Navigate to logIn page and login to created account", enabled = true, singleThreaded = true)
	public void RegLogin_002() throws Exception {
		
		ExtentTestManager.getTest().setDescription("RegLogin_002- Navigate to logIn page and login to created account");
		
sleep(2);
		
		waitForElementByText("Search here...", 5);
			
		// verify view profile text of landing page
		verifyIsVisibleAndroidElementByText("Search here...", ObjectTypes.TEXT_FIELD_VALUE,true);
		
		// verify background image
		takeScreenshot("Background image as per the given UI");
		
		uiClickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ImageView[2]");
		// verify view profile text of landing page
		//verifyIsVisibleAndroidElementByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup", "Theme Changer", ObjectTypes.TOGGLE);
		
//		String a="test";
//		String b="testt";
//		boolean value = false;
//		
//		if(a.equals(b)) {
//			assertTrue(true);
//			Log.info("Assert Passed ");
//			ExtentTestManager.getTest().log(LogStatus.PASS,
//					"Assert Passed ");
//		}else {
//			assertTrue(false);
//			Log.info("Assert Failed ");
//			ExtentTestManager.getTest().log(LogStatus.FAIL,
//					"Assert Failed ");
//			
//		}
		
		
		// verify profile section by exsisting text
			verifyIsVisibleAndroidElementByText("Get on board with", ObjectTypes.TEXT,true);
			
			
			waitForElementByText("Sign in / Sign up", 5);
			
			sleep(3);
			
			//verify login button element by id 
			verifyIsVisibleAndroidElementByText("Sign in / Sign up",ObjectTypes.BUTTON, true);
			
			//click sign in button
			clickAndroidElementByText("Sign in / Sign up", ObjectTypes.BUTTON);
			
			
			
			takeScreenshot("Background image as per the given UI");
			
			
			//verify email input element by id 
			verifyIsVisibleAndroidElementById("signin.email", "Email box", ObjectTypes.TEXT_BOX, true);
			
			uiSendKeysById("signin.email",  "marc"+randomNumber+"@gmail.com");
			
			
			//verify email input element by id 
			verifyIsVisibleAndroidElementById("signin.password", "PW box", ObjectTypes.TEXT_BOX, true);
			
			
			uiSendKeysById("signin.password","Pass@"+randomNumber);
		
			
			
			takeScreenshot("Background image as per the given UI");
			
			
			//click sign in button
			clickAndroidElementByText("Sign In", ObjectTypes.BUTTON);
			
			takeScreenshot("Background image as per the given UI");
			
			waitForElementByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup", 3);
			
			
			
			// verify view profile text of landing page
			verifyIsVisibleAndroidElementByText("Search here...", ObjectTypes.TEXT_FIELD_VALUE,true);
			
			// verify background image
			takeScreenshot("Background image as per the given UI");
			
			uiClickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup");
			waitForElementByText("My profile", 3);
			
			//verify profile button 
			verifyIsVisibleAndroidElementByText("My profile",ObjectTypes.NAVIGATION_LINK, true);

			//click my profile button
			clickAndroidElementByText("My profile", ObjectTypes.NAVIGATION_LINK);
			
		
			String text4 =	uiGetTextByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[1]");
			System.out.println(text4);
			
			String expectedText4 ="marc"+randomNumber+" Perera";
			
			assertTwoTexts(text4, expectedText4, "name of logged account");
			

	
	}
	
	

	@Parameters({})
	@Test(retryAnalyzer = Retry.class, priority = 3, testName = "RegLogin_003 - Navigates to LogIN page and enter wrong credentials", enabled = true, singleThreaded = true)
	public void RegLogin_003() throws Exception {
		
		ExtentTestManager.getTest().setDescription("RegLogin_003 - Navigates to LogIN page and enter wrong credentials");
		
		sleep(2);
		
		waitForElementByText("Search here...", 5);
			
		// verify view profile text of landing page
		verifyIsVisibleAndroidElementByText("Search here...", ObjectTypes.TEXT_FIELD_VALUE,true);
		
		// verify background image
		takeScreenshot("Background image as per the given UI");
		
		uiClickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ImageView[2]");
		// verify view profile text of landing page
		//verifyIsVisibleAndroidElementByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup", "Theme Changer", ObjectTypes.TOGGLE);
		

		
		
		// verify profile section by exsisting text
			verifyIsVisibleAndroidElementByText("Get on board with", ObjectTypes.TEXT,true);
			
			
			waitForElementByText("Sign in / Sign up", 5);
			
			sleep(3);
			
			//verify login button element by id 
			verifyIsVisibleAndroidElementByText("Sign in / Sign up",ObjectTypes.BUTTON, true);
			
			//click sign in button
			clickAndroidElementByText("Sign in / Sign up", ObjectTypes.BUTTON);
			
			
			
			takeScreenshot("Background image as per the given UI");
			
			
			//verify email input element by id 
			verifyIsVisibleAndroidElementById("signin.email", "Email box", ObjectTypes.TEXT_BOX, true);
			
			uiSendKeysById("signin.email", "test@gmail.com");
			
			
			//verify email input element by id 
			verifyIsVisibleAndroidElementById("signin.password", "PW box", ObjectTypes.TEXT_BOX, true);
			
			
			uiSendKeysById("signin.password", "pass@1234");
		
			
			
			takeScreenshot("Background image as per the given UI");
			
			
			//click sign in button
			clickAndroidElementByText("Sign In", ObjectTypes.BUTTON);
			
			takeScreenshot("Background image as per the given UI");
			

		
	}
	

	
	
}
