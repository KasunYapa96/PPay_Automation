package com.chivelab.pageObjAndroid;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.Log;
import com.utility.ObjectTypes;
import com.utility.SingletonClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RegistrationLoginPage  extends BasePageObject {

	@SuppressWarnings("unused")
	private AndroidDriver<AndroidElement> driver;
	public static AndroidElement element = null;

	public RegistrationLoginPage() {
	}	

	/*
	public RegistrationLoginPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
	}
	*/
	
	// login button
	@AndroidFindBy(id = "kc-login")
	private AndroidElement loginBtn;
	
	// card number textbox
	@AndroidFindBy(id = "com.visa.android:id/et_add_source_card_number_ml")
	private AndroidElement cardNumberTxt;
	
	// card expire date textbox
	@AndroidFindBy(id = "com.visa.android:id/et_add_source_expiry_ml")
	private AndroidElement cardExpDateTxt;
	
	// card cvc textbox
	@AndroidFindBy(id = "com.visa.android:id/et_add_source_cvc_ml")
	private AndroidElement cardCvcTxt;
	
	// Add card title
	@AndroidFindBy(xpath = "//*[@text='Add your personal card']")
	private AndroidElement AddCardTitle;

	
	/**
	 * User Registration
	 *
	 * @param username - value of username
	 * 		password - value of password
	 * @throws Exception
	 * @author Amila
	 */
	
	
	public void registration(String randomNumber,String password,String otp,String mobileNumber,String cardnumber,String CardYear,String CardMonth,String CardCVV) throws Exception{
		
		
		
		// start precondtion
		logStartPrecondition("Clear SAIV App data");
				
		// clear app data
		resetApp();
			
		// end of precondition
		logEndPrecondition("Clear SAIV App data");
		
		// wait for loading
		waitForLoading(20);

		// click on sing up link		
		clickAndroidElementByText("New user? Sign up", ObjectTypes.NAVIGATION_LINK);
		
		// enter user name
				uiSendKeysById("com.saiv.android:id/etUserName", "user_"+randomNumber);
				
				// enter password
				uiSendKeysById("com.saiv.android:id/etPassword", password);
				
				// enter password
				uiSendKeysById("com.saiv.android:id/etConfirmPassword", password);
				
//				// press enter
//				pressEnterSoftKeyboard();
				
				// enter email
				uiSendKeysByIdOnPage("com.saiv.android:id/etEmail", "Email", "user_"+randomNumber+"@arimac.com", ObjectTypes.TEXT_BOX, 5);
				
				// enter fname	
				uiSendKeysByIdOnPage("com.saiv.android:id/etFirstName", "fName", "name_"+randomNumber, ObjectTypes.TEXT_BOX, 5);
				
				// enter lname	
				uiSendKeysByIdOnPage("com.saiv.android:id/etLastName", "lName", "name2_"+randomNumber, ObjectTypes.TEXT_BOX, 5);
				
				
				// Click Country Dropdown
				clickAndroidElementById("com.saiv.android:id/rlClickConsumer", "Country Dropdown", ObjectTypes.BUTTON);
				
				// Click America
				clickAndroidElementByText("Sri Lanka (LK)", ObjectTypes.TEXT, true);
				
				// enter mobile number
				uiSendKeysByIdOnPage("com.saiv.android:id/phoneNumberET", "Phone number", mobileNumber+randomNumber, ObjectTypes.TEXT_BOX, 10);
				
				// enter referrel code
				uiSendKeysByIdOnPage("com.saiv.android:id/etReferralCode", "referrel", "1c877382-4f84-41e8-ac28-f724376db876", ObjectTypes.TEXT_BOX, 10);
							
				// verify privercy
				clickAndroidElementByIdOnPage("com.saiv.android:id/cbPrivacyAndTerms", "Privacy", ObjectTypes.CHECKBOX, 10);
			
				// verify confirm button
				clickAndroidElementByIdOnPage("com.saiv.android:id/rlConfirm", "Next", ObjectTypes.BUTTON, 5, true);
		
		// allow app permissions
		allowAppPermissions(5);
		
//		// click ok
//		clickAndroidElementById("com.visa.android:id/tvOK", "OK", ObjectTypes.BUTTON,true);
		
		// wait for loading
		waitForLoading(20);

		// enter OTP
		uiSendKeysById("com.saiv.android:id/pinViewOtp", otp);
		
//		// Click submit
//		clickAndroidElementById("com.visa.android:id/rlConfirm", "Continue", ObjectTypes.BUTTON);
		
		// wait for loading
		waitForLoading(20);
		
		// verify continue
		verifyIsVisibleAndroidElementByText("Continue", ObjectTypes.TEXT);
		
		//Click continue
		clickAndroidElementByText("Continue", ObjectTypes.TEXT, true);
		
		// wait for loading
		waitForLoading(20);
		
//		// verify success message
//		verifyIsVisibleAndroidElementByText("You have successfully registered", ObjectTypes.TEXT);
//		
//		// Click submit
//		clickAndroidElementById("com.saiv.android:id/tvOK", "OK", ObjectTypes.BUTTON);

	
		
////		//login
//		registrationLoginPage.login("user_"+randomNumber, password);
		
		//Enter un
		uiSendKeysById("username", "user_"+randomNumber);
		
		//Enter pw
		uiSendKeysById("password", password);
		
		//login
		clickAndroidElementById("kc-login", "Log In", ObjectTypes.BUTTON);
		
		// allow app permissions
		allowAppPermissions(5);
		
		//wait
		waitForLoading(20);
		
		//Proceed
		clickAndroidElementById("com.saiv.android:id/relBtnConfirm", "Proceed", ObjectTypes.BUTTON);	
		
		
		// enter first name
		uiSendKeysById("paymentform-first_name", "first_name");
						
		// enter last name
		uiSendKeysById("paymentform-last_name", "last_name");
								
//		// enter nick name
//		uiSendKeysById("com.visa.android:id/etCardNickName", "nick_name");

		// enter card number
		uiSendKeysById("paymentform-pan", cardnumber);
		
		// click month
		clickAndroidElementById("expiry-month-select", "Month", ObjectTypes.BUTTON);
		
		//select month
		clickAndroidElementByText(CardMonth, ObjectTypes.RADIO_BUTTON);
		
		// click yer
		clickAndroidElementById("expiry-year-select", "Year", ObjectTypes.BUTTON);
		
		//select yer
		clickAndroidElementByText(CardYear, ObjectTypes.RADIO_BUTTON);
		
		waitForLoading(10);
		
		// enter cvv number
		uiSendKeysByIdOnPage("paymentform-cvv", "CVV", CardCVV, ObjectTypes.BUTTON, 5);
		
		waitForLoading(30);
		
		// Click on Submit button
//		clickAndroidElementByTextOnPage("Submit", ObjectTypes.BUTTON, 20);
		clickAndroidElementById("direct-pay-form-submit", "Submit", ObjectTypes.BUTTON);
		
		//wait
		waitForLoading(20);
		
		// enter F name
		uiSendKeysById("com.saiv.android:id/etFirstName", "FirstName_"+randomNumber);
		
		// enter L name
		uiSendKeysById("com.saiv.android:id/etCardLastName", "LasttName_"+randomNumber);
		
		// enter nickname
		uiSendKeysById("com.saiv.android:id/etCardNickName", "SavingsCardOf_"+randomNumber);
		
		// click on date
		clickAndroidElementById("com.saiv.android:id/tvDateOfBirth", "Date of birth", ObjectTypes.BUTTON);
		
		//select date
		clickAndroidElementByText("3", ObjectTypes.RADIO_BUTTON);
		
		//select OK
		clickAndroidElementByText("OK", ObjectTypes.RADIO_BUTTON);
		
//		// Confirm date
//		clickAndroidElementById("android:id/button1", "Date of birth", ObjectTypes.BUTTON);
//		
//		// Confirm date
//		clickAndroidElementById("android:id/button1", "Date of birth", ObjectTypes.BUTTON);
		
		// enter address
		uiSendKeysById("com.saiv.android:id/etAddressLineOne", "1st street");

		// enter address
		uiSendKeysById("com.saiv.android:id/etCity", "Colombo");
		
		// country dorpdown
		clickAndroidElementById("com.saiv.android:id/rlClickConsumer", "Select country", ObjectTypes.DROPDOWN);
		
		//Select country
		clickAndroidElementByText("Sri Lanka (LK)", ObjectTypes.TEXT, true);
		
		//Select country
		clickAndroidElementByText("State / Province", ObjectTypes.DROPDOWN);
		
		//Select country
		clickAndroidElementByText("Central Province", ObjectTypes.DROPDOWN, true);
		
		
		// enter address
		uiSendKeysByIdOnPage("com.saiv.android:id/etPostalCode", "PostalCode", "82000", ObjectTypes.TEXT_BOX, 10);
		
		// Next
		clickAndroidElementByIdOnPage("com.saiv.android:id/tvNext", "Next", ObjectTypes.BUTTON,10);
		
		
		// wait for loading
		waitForLoading(20);
		
		// verify success message popup
		verifyIsVisibleAndroidElementByTextOnPage("Your card is added successfully", ObjectTypes.TEXT,10);
		
		// confirm
		clickAndroidElementByIdOnPage("com.saiv.android:id/tvOK", "OK", ObjectTypes.BUTTON,10);
		

	}
   	
	
	
	/**
	 * User Login
	 *
	 * @param username - value of username
	 * 		password - value of password
	 * @throws Exception
	 * @author Amila
	 */
	public void login(String username, String password) throws Exception{
		// start precondtion
		logStartPrecondition("Clear SAIV App data");
				
		// clear app data
		resetApp();
		
		System.out.println("Reset app done");
			
		// end of precondition
		logEndPrecondition("Clear SAIV App data");
		
		// wait for loading
		waitForLoading(20);
//		waitForLoading(20);	
		uiSendKeysById("username", username);
		uiSendKeysById("password", password);
		clickAndroidElementById("kc-login", "Log In", ObjectTypes.BUTTON);
		waitForLoading(10);
		
		// allow app permissions
		allowAppPermissions(10);
		
//		clickAndroidElementByText("Proceed", ObjectTypes.BUTTON);
//		addCardM();
		
		
//		allowAppPermissions(5);
//		waitForLoading(10);
		
		
		
		// verify text
//		verifyIsVisibleAndroidElementById("com.visa.android:id/tvHome", "Home", ObjectTypes.PAGE_TITLE, true);
		log("Logged in user : username: "+username+", password: "+password);
		waitForLoading(30);	
	}
	
	/**
	 * User Login
	 *
	 * @param username - value of username
	 * 		password - value of password
	 * @throws Exception
	 * @author Amila
	 */
	public void login2(String username, String password) throws Exception{
		
		boolean found = false;
		boolean found1 = false;
		int seconds = 120;
		while (!isVisibleAndroidElementByIdWithoutLog("kc-login")) {

			sleep(3);
			found = isVisibleAndroidElementByIdWithoutLog("kc-login");
			if (found) {
				waitForLoading(20);	
				uiSendKeysById("username", username);
				uiSendKeysById("password", password);
				clickAndroidElementById("kc-login", "Log In", ObjectTypes.BUTTON);
				waitForLoading(10);
				allowAppPermissions(5);
//				waitForLoading(10);
				addCardM();
				
				// verify text
//				verifyIsVisibleAndroidElementById("com.visa.android:id/tvHome", "Home", ObjectTypes.PAGE_TITLE, true);
				log("Logged in user : username: "+username+", password: "+password);
				sleep(10);
				break;
			} else {
				sleep(3);
				 found1 = isVisibleAndroidElementByIdWithoutLog("com.visa.android:id/rlConfirm");
				if (found) {
					
					// verify confirm button
					clickAndroidElementByIdOnPage("com.visa.android:id/rlConfirm", "Confirm", ObjectTypes.BUTTON, 5, true);
							
					// allow app permissions
					allowAppPermissions(5);
					
					// click ok
					clickAndroidElementById("com.visa.android:id/tvOK", "OK", ObjectTypes.BUTTON,true);
					
					//sleep
					sleep(5);
					
					
				}
				else {
//				SingletonClass.getAndroidDriver().navigate().back();
				refreshPage(10);
				Log.info("Waiting for Login Page: ");
				}
			}
			sleep(7);
			if (seconds > 0) {
				seconds = seconds - 10;
			} else {
				verifyAssertTrue(false, "Login Page Not found");
			}
		}
		
	}
	
	public void addCardM() throws Exception{

		sleep(1);
		while(isVisibleAndroidElementWithoutLog(AddCardTitle)){
			
//			// verify page text
//			verifyIsVisibleAndroidElementByText("At the moment we only support VISA cards issued from Discovery bank, FNB, Investec, Tyme bank and African bank.", ObjectTypes.TEXT);
					
			// enter first name
			uiSendKeysById("com.visa.android:id/etCardFirstName", "first_name");
							
			// enter last name
			uiSendKeysById("com.visa.android:id/etCardLastName", "last_name");
									
//			// enter nick name
//			uiSendKeysById("com.visa.android:id/etCardNickName", "nick_name");

			// enter card number
			uiSendKeysById("com.visa.android:id/et_add_source_card_number_ml", "4038-2280-0055-2013");
			
			// click month
			clickAndroidElementById("com.visa.android:id/et_add_source_expiry_ml", "Month", ObjectTypes.BUTTON);
			
			// click month
			clickAndroidElementById("com.visa.android:id/et_add_source_expiry_ml", "Date", ObjectTypes.BUTTON);
			
			// enter cvv number
			uiSendKeysById("com.visa.android:id/et_add_source_cvc_ml", "436");
			
			// verify Submit button
			clickAndroidElementByTextOnPage("Submit", ObjectTypes.BUTTON, 5, true);
			
////			// enter last name
////			uiSendKeysById("com.visa.android:id/et_add_source_card_number_ml", "4038-2280-0055-2013");
//			
//			// verify card number
//			verifyAssertTrue(!uiGetTextById("com.visa.android:id/et_add_source_card_number_ml").replaceAll(", Card number", "").trim().isEmpty(), "Card number should auto be populate");
//					
//			// verify expiration date
//			verifyAssertTrue(!uiGetTextById("com.visa.android:id/et_add_source_expiry_ml").replaceAll(", Expiration date", "").trim().isEmpty(), "Card expiration date should auto be populate");
//				
//			// verify CVC
//			verifyAssertTrue(!uiGetTextById("com.visa.android:id/et_add_source_cvc_ml").isEmpty(), "CVC should auto be populate");
//					
//			// verify confirm button
//			clickAndroidElementByTextOnPage("CONFIRM", ObjectTypes.BUTTON, 5, true);
//					
//			// wait for loading
//			waitForLoading(20);
//				
//			// verify popup text
//			verifyIsVisibleAndroidElementByText("Your 'XXXXXXXXXXXX5556' added successfully", ObjectTypes.POPUP_TEXT);
					
			// click ok
			clickAndroidElementById("com.visa.android:id/tvOK", "OK", ObjectTypes.BUTTON);
					
			// wait for loading
			waitForLoading(5);
			
			
		}
	}
	
	/**
	 * User Login - Precondition
	 * 
	 * @param N/A
	 * @throws N/A
	 * @author Amila
	 * @throws Exception
	 */
	public void loginPrecondition(String username, String password) throws Exception {
		sleep(2);
		try {
			if (loginBtn.isDisplayed()) {
				
				login(username, password);

			}
		} catch (NoSuchElementException e) {
			log("skip for login");
		}
	}
	
	/**
	 * Get card number
	 *
	 * @param N/A
	 * @throws Exception
	 * @author Amila
	 */
	public String getCardNumber() throws Exception{

		String text = uiGetText(cardNumberTxt).replaceAll(", Card number", "").trim();

		log("Get Card Number : "+text);
		
		return text;
	}
	
	/**
	 * Get Expire date - card
	 *
	 * @param N/A
	 * @throws Exception
	 * @author Amila
	 */
	public String getExpireDate() throws Exception{

		String text = uiGetText(cardExpDateTxt).replaceAll(", Expiration date", "").trim();

		log("Get Card Expire Date : "+text);
		
		return text;
	}
	
	/**
	 * Get Card CVC number
	 *
	 * @param N/A
	 * @throws Exception
	 * @author Amila
	 */
	public String getCvcNumber() throws Exception{
		
		String text = uiGetText(cardCvcTxt).trim();

		log("Card CVC Number : "+text);
		
		return text;
	}
	
}
