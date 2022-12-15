package com.chivelab.pageObjAndroid;

import org.apache.velocity.runtime.directive.Parse;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;
import com.utility.Log;
import com.utility.ObjectTypes;
import com.utility.SingletonClass;
import com.utils.ExtentReports.ExtentTestManager;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GroupCreationPage extends BasePageObject {

	// class variable
	final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

	final java.util.Random rand = new java.util.Random();

	// consider using a Map<String,Boolean> to say whether the identifier is being
	// used or not
	final Set<String> identifiers = new HashSet<String>();

	@SuppressWarnings("unused")
	private AndroidDriver<AndroidElement> driver;
	public static AndroidElement element = null;

	public GroupCreationPage() {
	}

	public GroupCreationPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// group type drop down
	@AndroidFindBy(id = "com.saiv.android:id/tvSavingsType")
	private AndroidElement groupTypeDropdown;

	// group type drop down
	@AndroidFindBy(id = "com.saiv.android:id/tvSelectPayment")
	private AndroidElement paymentFrequencyDropdown;

//	// Start Date field
//	@AndroidFindBy(id = "com.saiv.android:id/container_4")
//	private AndroidElement startDateField;

	// Start Date field
	@AndroidFindBy(id = "com.saiv.android:id/selectStartDateContainer")
	private AndroidElement startDateField;

	// Plus button
	@AndroidFindBy(id = "com.saiv.android:id/tvCreateChatGroup")
	private AndroidElement plusBtn;

	// Search bar
	@AndroidFindBy(id = "com.saiv.android:id/etSearch")
	private AndroidElement searchBar;

	// Next Arrow - create chat group
	@AndroidFindBy(id = "com.saiv.android:id/tvCreateChatGroup")
	private AndroidElement nextArrowBtn;

	// Group name
	@AndroidFindBy(id = "com.saiv.android:id/etGroupName")
	private AndroidElement groupNameTxt;

	// Right button
	@AndroidFindBy(id = "com.saiv.android:id/tvRightBtn")
	private AndroidElement rightBtn;

	// Group goal textbox
	@AndroidFindBy(id = "com.saiv.android:id/etTarget")
	private AndroidElement groupgoalTxtbox;

	// Next Arrow - Select group type page
	@AndroidFindBy(id = "com.saiv.android:id/tvNext")
	private AndroidElement nextArrow2Btn;

	// First name textbox
	@AndroidFindBy(id = "com.saiv.android:id/etCardFirstName")
	private AndroidElement firstNameTxtbox;

	// Last name textbox
	@AndroidFindBy(id = "com.saiv.android:id/etCardLastName")
	private AndroidElement lastNameTxtbox;

	// Nick name textbox
	@AndroidFindBy(id = "com.saiv.android:id/etCardNickName")
	private AndroidElement nickNameTxtbox;

	// Ok button
	@AndroidFindBy(id = "com.saiv.android:id/tvOK")
	private AndroidElement okBtn;

	// Confirm button
	@AndroidFindBy(id = "com.saiv.android:id/relConfirmContainer")
	private AndroidElement confirmBtn;

	// first contact picture icon
	@AndroidFindBy(xpath = "(//android.widget.ImageView)[2]")
	private AndroidElement firstContactIcon;

	// second contact picture icon
	@AndroidFindBy(xpath = "(//android.widget.ImageView)[2]")
	private AndroidElement secondtContactIcon;

	// first contact name
	@AndroidFindBy(xpath = "(//android.widget.ImageView/preceding-sibling::android.widget.LinearLayout)[1]/android.widget.TextView")
	private AndroidElement firstContactName;

	// first contact radio button
	@AndroidFindBy(xpath = "(//android.widget.ImageView/preceding-sibling::android.widget.LinearLayout)[1]/following-sibling::android.widget.ImageView")
	private AndroidElement firstContactrbn;

	// second contact radio button
	@AndroidFindBy(xpath = "(//android.widget.ImageView/preceding-sibling::android.widget.LinearLayout)[2]/following-sibling::android.widget.ImageView")
	private AndroidElement secondContactrbn;

	// Get Participants count
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Participants :']/following-sibling::android.widget.TextView")
	private AndroidElement ParticipantsCount;

	// Get Participants count 2
	@AndroidFindBy(xpath = "//*[@resource-id='com.saiv.android:id/tvMemberCount']")
	private AndroidElement ParticipantsCountRule;
	
	// Get Participants 1st user's name
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Participants :']/ancestor::android.widget.LinearLayout[1]/following-sibling::androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.ImageView/ancestor::android.widget.RelativeLayout[1]/following-sibling::android.widget.TextView")
	private AndroidElement Participantsuser_01;

	// Get Participants 2nd user's name
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Participants :']/ancestor::android.widget.LinearLayout[1]/following-sibling::androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.ImageView/ancestor::android.widget.RelativeLayout[1]/following-sibling::android.widget.TextView")
	private AndroidElement Participantsuser_02;

	// Get total
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ZAR']/following-sibling::android.widget.TextView")
	private AndroidElement TotalZar;
	
	// Get total
	@AndroidFindBy(xpath = "//*[@resource-id='com.saiv.android:id/tvEquityAmount']")
	private AndroidElement TotalZarTransaction;

	// Contribution user profile pic one
	@AndroidFindBy(xpath = "(//android.widget.ImageView[1])[1]")
	private AndroidElement profilepicone;
	

	// Contribution user profile pic two
	@AndroidFindBy(xpath = "(//android.widget.ImageView[1])[2]")
	private AndroidElement profilepictwo;

	// Contribution user profile pic three
	@AndroidFindBy(xpath = "(//android.widget.ImageView[1])[3]")
	private AndroidElement profilepicthree;

	// 1st Contribution name
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Total']/ancestor::android.widget.RelativeLayout[2]/following-sibling::android.widget.RelativeLayout[1]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.TextView")
	private AndroidElement firstcontributorName;

	// 2nd Contribution name
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Total']/ancestor::android.widget.RelativeLayout[2]/following-sibling::android.widget.RelativeLayout[1]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.TextView")
	private AndroidElement SecondContributorName;

	// admin name
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Total']/ancestor::android.widget.RelativeLayout[2]/following-sibling::android.widget.RelativeLayout[1]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[3]/android.widget.TextView")
	private AndroidElement adminName;

	// member one amount
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Total']/ancestor::android.widget.RelativeLayout[2]/following-sibling::android.widget.RelativeLayout[1]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.TextView[@text='ZAR']/following-sibling::android.widget.EditText")
	private AndroidElement MemberOneAmount;

	// member two amount
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Total']/ancestor::android.widget.RelativeLayout[2]/following-sibling::android.widget.RelativeLayout[1]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.TextView[@text='ZAR']/following-sibling::android.widget.EditText")
	private AndroidElement MemberTwoAmount;

	// Admin amount
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Total']/ancestor::android.widget.RelativeLayout[2]/following-sibling::android.widget.RelativeLayout[1]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='ZAR']/following-sibling::android.widget.EditText")
	private AndroidElement AdminAmount;

	// get group name
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Summary']/following-sibling::android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.TextView")
	private AndroidElement groupName;

	// enter group name
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@text,'Group name')]")
	private AndroidElement enterGruopName;

	// enter Amount
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@text,'Amount')]")
	private AndroidElement enterAmounte;

	// get Frequency goal
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contribution frequency']/ancestor::android.widget.RelativeLayout[1]/following-sibling::android.widget.TextView")
	private AndroidElement getFrequencygoal;

	// get Frequency goal
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='30 Minutes take home amount per member']/ancestor::android.widget.RelativeLayout[1]/following-sibling::android.widget.TextView")
	private AndroidElement getTakeHomeAmount;

	// Active user
	@AndroidFindBy(xpath = "(//android.widget.TextView)[4]")
	private AndroidElement ActiveUser;

	// First Contact name New Chat
	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.LinearLayout")
	private AndroidElement getFirstContactnameNewChat;

	// Second Contact name New Chat
	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[4]/android.widget.LinearLayout")
	private AndroidElement getSecondContactnameNewChat;

	/**
	 * Select Group type
	 *
	 * @param text - name of group type
	 * @throws Exception
	 * @author Amila
	 */
	public void selectGroupType(String text) throws Exception {
		clickAndroidElement(groupTypeDropdown, "Group Type", ObjectTypes.DROPDOWN);
		sleep(3);
		uiClickByText(text);
		log("Select Group Type: " + text);
	}

	/**
	 * Select Payment frequency
	 *
	 * @param text - name of group type
	 * @throws Exception
	 * @author Amila
	 */
	public void selectPaymentFrequency(String text) throws Exception {
		clickAndroidElement(paymentFrequencyDropdown, "Payment Frequency", ObjectTypes.DROPDOWN);
		sleep(3);
		uiClickByText(text);
		log("Select Payment Frequency: " + text);
	}

	/**
	 * Set start date
	 *
	 * @param text - name of group type
	 * @throws Exception
	 * @author Amila
	 */
	public void setStartDate() throws Exception {
		clickAndroidElement(startDateField, "Start Date", ObjectTypes.DATE_PICKER);
		sleep(3);
		clickAndroidElementByText("OK", ObjectTypes.BUTTON);
		log("Set Start date");
	}

	/**
	 * Select User
	 *
	 * @param text - name of user
	 * @throws Exception
	 * @author Amila
	 */
	public void selectUsers(String[] users) throws Exception {
		for (String user : users) {
			uiSendKeys(searchBar, user);
			hideKeyboard();
			sleep(4);
			uiClick((AndroidElement) SingletonClass.getAndroidDriver()
					.findElement(By.xpath("//android.widget.TextView[@text=\"" + user
							+ "\"]/ancestor::android.widget.LinearLayout[1]/following-sibling::android.widget.ImageView[1]")));
			uiClear(searchBar);

			log("Select User: " + user);
		}

	}

	/**
	 * Select a User
	 *
	 * @param text - name of user
	 * @throws Exception
	 * @author Hasitha
	 */
	public void selectSingleUser(String user) throws Exception {

		uiSendKeys(searchBar, user);
		hideKeyboard();
		sleep(2);
		uiClick((AndroidElement) SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//android.widget.TextView[@text=\"" + user
						+ "\"]/ancestor::android.widget.LinearLayout[1]/following-sibling::android.widget.ImageView[1]")));
		uiClear(searchBar);

		log("Select User: " + user);

	}

	/**
	 * Create a group
	 * 
	 * @param N/A
	 * @throws N/A
	 * @author Amila
	 * @throws Exception
	 */
	public void createGroup(String[] users, String groupName, String groupType, String groupGoal,
			String paymentFrequency, String firstName, String lastName, String nickName, String randomNumber,String cardnumber,String CardYear,String CardMonth,String CardCVV) throws Exception {

		String key = groupType;
		switch (key) {
		case "Save as you go":
			


			
////////////////////////New Flow
			
			
	    //Click View my groups
		clickAndroidElementByText("View my groups", ObjectTypes.TEXT);
			
		//click on plus icon
		clickAndroidElementById("com.saiv.android:id/tvCreateChatGroup", "Plus Button", ObjectTypes.BUTTON);

		// click on New Group
		clickAndroidElementByText("New group", ObjectTypes.ICON, true);

		//select users
		selectUsers(users);
		
		hideKeyboard();

		takeScreenshot("Added Users");

		// click on next button
		clickAndroidElementById("com.saiv.android:id/tvCreateChatGroup", "Next", ObjectTypes.ICON, true);

//		// verify new group title
//		verifyIsVisibleAndroidElementByText("NEW GROUP", ObjectTypes.TITLE, true);

//		// click on camera icon
//		clickAndroidElementById("com.saiv.android:id/ivGroupPic", "Camera", ObjectTypes.ICON);
//
//		// click on take photo
//		clickAndroidElementByText("Take Photo", ObjectTypes.POPUP_BUTTON);

		// allow app permissions
		allowAppPermissions(5);

//		// click camera shutter button
//		clickAndroidElementByContainsId("com.android.camera2:id/shutter_button", "Camera shutter", ObjectTypes.BUTTON);
//
//		// click camera done button
//		clickAndroidElementByContainsId("com.android.camera2:id/done_button", "Camera done", ObjectTypes.BUTTON);

		// take screen shot
		takeScreenshot("Captured photo");

		// Get Participants User
		getParticipantsUsers();
		
//		// Enter Group name
        enterGroupName("Group_" + randomNumber);

		// set group name
		uiSendKeys(groupNameTxt, groupName);
		hideKeyboard();
		
		// click on next button
		clickAndroidElementById("com.saiv.android:id/tvRightBtn", "Next", ObjectTypes.ICON, true);
		
		// Drop-down option Select group type
		clickAndroidElementById("com.saiv.android:id/weWantArrow", "Select group type drop down", ObjectTypes.NAVIGATION_LINK);
		
		// click on plus icon
		clickAndroidElementByText(groupType, ObjectTypes.MENU_OPTION, true);
		
		// Enter Ultimate goal amount
		enterEnterUltimateGoal(groupGoal);

		// Drop-down option Select payment frequency
		clickAndroidElementById("com.saiv.android:id/tvIcon2", "Select payment frequency drop down", ObjectTypes.NAVIGATION_LINK);

		// click Custom of dropdown
		clickAndroidElementByText(paymentFrequency, ObjectTypes.MENU_OPTION, true);

		// click Start Date picker
		clickAndroidElementByIdOnPage("com.saiv.android:id/tvIcon4", "Start Date picker", ObjectTypes.ICON,10);

		// select Start Date
		clickAndroidElementById("android:id/button1", "Start Date picker", ObjectTypes.ICON, true);

		// click Next button
		clickAndroidElementByIdOnPage("com.saiv.android:id/tvNext", "Next button", ObjectTypes.ICON,10);

		// wait for loading
		waitForLoading(20);
		
		// click on Next button
		clickAndroidElementById("com.saiv.android:id/tvNext", "Next Button", ObjectTypes.ICON, true);

		// click on Add new card button
		clickAndroidElementById("com.saiv.android:id/rlNewCard", "Add new card", ObjectTypes.ICON);
		
		// click on New Group
		clickAndroidElementByText("Confirm", ObjectTypes.BUTTON);
		
		// wait for loading
		waitForLoading(20);
		
		// enter first name
		uiSendKeysById("paymentform-first_name", "first_name");
						
		// enter last name
		uiSendKeysById("paymentform-last_name", "last_name");

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
		
		// enter cvv number
		uiSendKeysByIdOnPage("paymentform-cvv", "CVV", CardCVV, ObjectTypes.BUTTON, 5);
		
		// Click on Submit button
		clickAndroidElementByTextOnPage("Submit", ObjectTypes.BUTTON, 5, true);
		
		//wait
		waitForLoading(5);
		
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
		
		// wait for loading
		waitForLoading(20);

		// click on Confirm button
		clickAndroidElementById("com.saiv.android:id/relConfirmContainer", "Confirm", ObjectTypes.BUTTON);

		sleep(3);

		takeScreenshot("Group creation animation");

		sleep(1);

		takeScreenshot("Group creation animation");

		sleep(1);

		takeScreenshot("Group creation animation");
		
		sleep(10);
		
		break;
			
		case "Merry - go - round":


		    //Click View my groups
			clickAndroidElementByText("View my groups", ObjectTypes.TEXT);
				
			//click on plus icon
			clickAndroidElementById("com.saiv.android:id/tvCreateChatGroup", "Plus Button", ObjectTypes.BUTTON);

			// click on New Group
			clickAndroidElementByText("New group", ObjectTypes.ICON, true);

			//select users
			selectUsers(users);
			
			hideKeyboard();

			takeScreenshot("Added Users");

			// click on next button
			clickAndroidElementById("com.saiv.android:id/tvCreateChatGroup", "Next", ObjectTypes.ICON, true);

//			// verify new group title
//			verifyIsVisibleAndroidElementByText("NEW GROUP", ObjectTypes.TITLE, true);

//			// click on camera icon
//			clickAndroidElementById("com.saiv.android:id/ivGroupPic", "Camera", ObjectTypes.ICON);
	//
//			// click on take photo
//			clickAndroidElementByText("Take Photo", ObjectTypes.POPUP_BUTTON);

			// allow app permissions
			allowAppPermissions(5);

//			// click camera shutter button
//			clickAndroidElementByContainsId("com.android.camera2:id/shutter_button", "Camera shutter", ObjectTypes.BUTTON);
	//
//			// click camera done button
//			clickAndroidElementByContainsId("com.android.camera2:id/done_button", "Camera done", ObjectTypes.BUTTON);

			// take screen shot
			takeScreenshot("Captured photo");

			// Get Participants User
			getParticipantsUsers();
			
//			// Enter Group name
	        enterGroupName("Group_" + randomNumber);

			// set group name
			uiSendKeys(groupNameTxt, groupName);
			hideKeyboard();
			
			// click on next button
			clickAndroidElementById("com.saiv.android:id/tvRightBtn", "Next", ObjectTypes.ICON, true);
			
			// Drop-down option Select group type
			clickAndroidElementById("com.saiv.android:id/weWantArrow", "Select group type drop down", ObjectTypes.NAVIGATION_LINK);
			
			// click on plus icon
			clickAndroidElementByText(groupType, ObjectTypes.MENU_OPTION, true);
			
			// Enter Ultimate goal amount
			enterEnterUltimateGoal(groupGoal);

			// Drop-down option Select payment frequency
			clickAndroidElementById("com.saiv.android:id/tvIcon2", "Select payment frequency drop down", ObjectTypes.NAVIGATION_LINK);

			// click Custom of dropdown
			clickAndroidElementByText(paymentFrequency, ObjectTypes.MENU_OPTION, true);

			// click Start Date picker
			clickAndroidElementByIdOnPage("com.saiv.android:id/tvIcon4", "Start Date picker", ObjectTypes.ICON,10);

			// select Start Date
			clickAndroidElementById("android:id/button1", "Start Date picker", ObjectTypes.ICON, true);

			// click Next button
			clickAndroidElementByIdOnPage("com.saiv.android:id/tvNext", "Next button", ObjectTypes.ICON,10);

			// wait for loading
			waitForLoading(20);
			
			// click on Next button
			clickAndroidElementById("com.saiv.android:id/tvNext", "Next Button", ObjectTypes.ICON, true);

			// click on Add new card button
			clickAndroidElementById("com.saiv.android:id/rlNewCard", "Add new card", ObjectTypes.ICON);
			
			// click on New Group
			clickAndroidElementByText("Confirm", ObjectTypes.BUTTON);
			
			// wait for loading
			waitForLoading(20);
			
			// enter first name
			uiSendKeysById("paymentform-first_name", "first_name");
							
			// enter last name
			uiSendKeysById("paymentform-last_name", "last_name");

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
			
			// enter cvv number
			uiSendKeysByIdOnPage("paymentform-cvv", "CVV", CardCVV, ObjectTypes.BUTTON, 5);
			
			// Click on Submit button
			clickAndroidElementByTextOnPage("Submit", ObjectTypes.BUTTON, 5, true);
			
			//wait
			waitForLoading(5);
			
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
			
			// wait for loading
			waitForLoading(20);

			// click on Confirm button
			clickAndroidElementById("com.saiv.android:id/relConfirmContainer", "Confirm", ObjectTypes.BUTTON);

			sleep(3);

			takeScreenshot("Group creation animation");

			sleep(1);

			takeScreenshot("Group creation animation");

			sleep(1);

			takeScreenshot("Group creation animation");
			
			sleep(10);
			
			break;
			
		default:

			System.out.println("Failed");
			break;
		}

	}

	public void createGroupNS( String userName_2,String userName_3,String password,String groupType, String ultimateGoal, String paymentFrequency,String randomNumber) throws Exception {
		
		String[] users = { userName_2, userName_3 };
		
		waitForLoading(50);
		
		//Click View my groups
		clickAndroidElementByText("View my groups", ObjectTypes.TEXT);

//		// click on plus icon
//		uiClickByXpath("//*[contains(@resource-id,'tvCreateChatGroup')]");
//
//		// click on plus icon
//		clickAndroidElementByText("New group", ObjectTypes.ICON, true);
		
		//click on plus icon
		clickAndroidElementById("com.saiv.android:id/tvCreateChatGroup", "Plus Button", ObjectTypes.BUTTON);

		// click on New Group
		clickAndroidElementByText("New group", ObjectTypes.ICON, true);
		
		waitForLoading(50);

	//select users
	selectUsers(users);
	
	waitForLoading(50);
	
	hideKeyboard();

	takeScreenshot("Added Users");
	
	waitForLoading(50);
	
//	// Select numbers
//	groupCreationPage.clickRadiobutton();

	// click on next button
	clickAndroidElementById("com.saiv.android:id/tvCreateChatGroup", "Next", ObjectTypes.ICON, true);

	// verify new group title
	verifyIsVisibleAndroidElementByText("New group", ObjectTypes.TITLE, true);

//	// click on camera icon
//	clickAndroidElementById("com.saiv.android:id/ivGroupPic", "Camera", ObjectTypes.ICON);
//
//	// click on take photo
//	clickAndroidElementByText("Take photo", ObjectTypes.POPUP_BUTTON);
//
//	// allow app permissions
//	allowAppPermissions(5);
//
//	// click camera shutter button
//	clickAndroidElementByContainsId("com.android.camera2:id/shutter_button", "Camera shutter", ObjectTypes.BUTTON);
//
//	// click camera done button
//	clickAndroidElementByContainsId("com.android.camera2:id/done_button", "Camera done", ObjectTypes.BUTTON);
//
//	// take screen shot
//	takeScreenshot("Captured photo");

	// Get Participants User
	getParticipantsUsers();

	// Enter Group name
	enterGroupName("Group_" + randomNumber);
	
	waitForLoading(50);

	// click on next button
	//clickAndroidElementById("com.saiv.android:id/tvRightBtn", "Next", ObjectTypes.ICON, true);
	clickAndroidElementById("com.saiv.android:id/tvRightBtn", "Next", ObjectTypes.ICON, true);

	// wait for loading
	waitForLoading(50);

	// verify Lets get started
//	verifyIsVisibleAndroidElementByText("Group type", ObjectTypes.TEXT, true);
//	verifyIsVisibleAndroidElementByTextContains("Lets get started", ObjectTypes.TEXT, true);
	verifyIsVisibleAndroidElementById("com.saiv.android:id/tvLetGetsStat", "Title", ObjectTypes.TITLE);
	
	//---------------------------------------------------------------------------------------------------------------------
	
	// Drop-down option Select group type
			clickAndroidElementById("com.saiv.android:id/weWantArrow", "Select group type drop down", ObjectTypes.NAVIGATION_LINK);
			
			// click on plus icon
			clickAndroidElementByText(groupType, ObjectTypes.MENU_OPTION, true);
			
			// wait for loading
			waitForLoading(20);

			// verify ultimateGoal text 1
			verifyIsVisibleAndroidElementByText("Ultimate savings goal", ObjectTypes.TEXT, true);
			
			// verify ultimateGoal text 1
			verifyIsVisibleAndroidElementByText("(ZAR)", ObjectTypes.TEXT, true);
			
			// verify ultimateGoal tool tip
//			verifyIsVisibleAndroidElementById("com.saiv.android:id/tvInfo2", "Ultimate goal tool tip", ObjectTypes.ICON);
			
			// Enter Ultimate goal amount
			enterEnterUltimateGoal(ultimateGoal);

			// Drop-down option Select payment frequency
			clickAndroidElementById("com.saiv.android:id/tvIcon2", "Select payment frequency drop down", ObjectTypes.NAVIGATION_LINK);

			// click Custom of dropdown
			clickAndroidElementByText(paymentFrequency, ObjectTypes.MENU_OPTION, true);

			// click Start Date picker
			clickAndroidElementByIdOnPage("com.saiv.android:id/tvIcon4", "Start Date picker", ObjectTypes.ICON,10);

			// select Start Date
			clickAndroidElementById("android:id/button1", "Start Date picker", ObjectTypes.ICON, true);

//			// click End Date picker
//			clickAndroidElementByIdOnPage("com.saiv.android:id/tvIcon5", "Start Date picker", ObjectTypes.ICON,10);
	//
//			// select End Date
//			clickAndroidElementById("android:id/button1", "Start Date picker", ObjectTypes.ICON, true);

			// click on next
			verifyIsVisibleAndroidElementByIdOnPage("com.saiv.android:id/tvNext", "Next button", ObjectTypes.LINK, 10, true);

			// click Next button
			clickAndroidElementByIdOnPage("com.saiv.android:id/tvNext", "Next button", ObjectTypes.ICON,10);

			// wait for loading
			waitForLoading(20);
			
			// verify Contribution title
			verifyIsVisibleAndroidElementByText("Contribution", ObjectTypes.TITLE, true);
			
			//--------------------------------------------------------------------------------------------------------------------------
			
			// verify Total text
			verifyIsVisibleAndroidElementByText("Total", ObjectTypes.TEXT);

			// Get Total
			geTotal();

			// Verify Contribution profile pictures
			verifyContributionProfile();

			// Contributor name and amount
			verifyNameandAmount();

			// verify Dropdown
			verifyIsVisibleAndroidElementByText("Savings Club", ObjectTypes.LABEL);

			// verify Icon
			verifyIsVisibleAndroidElementById("com.saiv.android:id/tvIcon2", "Icon", ObjectTypes.ICON);

			// click on Next button
			clickAndroidElementById("com.saiv.android:id/tvNext", "Next Button", ObjectTypes.ICON, true);

			// wait for loading
			waitForLoading(20);

			// verify page title
			verifyIsVisibleTextView("Add your group card", ObjectTypes.PAGE_TITLE);

//			// verify page text
//			verifyIsVisibleTextView(
//					"At the moment we only support saiv cards issued from Discovery bank, FNB, Investec, Tyme bank and African bank.",
//					ObjectTypes.TEXT);
	//
			// click on Add new card button
			//clickAndroidElementById("com.saiv.android:id/rlNewCard", "Add new card", ObjectTypes.ICON);
			
			// verify confirm button
			verifyIsVisibleTextView("Confirm", ObjectTypes.BUTTON);
			
			// click confirm button
			clickAndroidElementByText("Confirm", ObjectTypes.BUTTON);
			
			//Add existing card and popup check
			verifyIsVisibleTextView("Your card is added successfully", ObjectTypes.BUTTON);
			
			// click ok button
			clickAndroidElementByText("OK", ObjectTypes.BUTTON);
				
			
			// wait for loading
			waitForLoading(20);

			// Get Group Name
			getGroupName();

			// Get Start day
			getStartDate();

			// Get End day
			getEndDate();

			// Get Frequency
			getFrequency();

			// Get Frequency goal
			//groupCreationPage.getFrequencygoal();

			// verify Confirm Button
			verifyIsVisibleAndroidElementByText("Confirm", ObjectTypes.BUTTON);

			// verify user 1
			verifyIsVisibleTextViewOnPage(userName_2, ObjectTypes.TEXT, 5);

			// verify user 2
			verifyIsVisibleTextViewOnPage(userName_3, ObjectTypes.TEXT, 5);

			// verify user 3
			verifyIsVisibleTextViewOnPage("You", ObjectTypes.TEXT, 5);

			// click on Confirm button
			clickAndroidElementById("com.saiv.android:id/relConfirmContainer", "Confirm", ObjectTypes.BUTTON);

			sleep(3);

			takeScreenshot("Group creation animation");

			sleep(1);

			takeScreenshot("Group creation animation");

			sleep(1);

			takeScreenshot("Group creation animation");
			
			sleep(10);
			
			
		
	}
	
	/**
	 * Verify first contact picture icon
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void verifyContactPictureIcon() throws Exception {

		boolean value = firstContactIcon.isDisplayed();
		if (value == true) {

			log("Contact picture should be visible");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Contact picture icon not visible");
		}

	}

	/**
	 * Verify Second contact picture icon
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void verifySecondContactPictureIcon() throws Exception {

		boolean value = secondtContactIcon.isDisplayed();
		if (value == true) {

			log("Second user Contact picture should be visible");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Second user Contact picture not visible");
		}

	}

	/**
	 * Get first contact name
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getFirstContactName() throws Exception {

		boolean getname = firstContactName.isDisplayed();
		String name = firstContactName.getText().trim();
		verifyAssertTrue(getname, "Contact name should be visible");
		log("First contact Name : " + name);

	}

	/**
	 * Get first contact name in New chat page
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getFirstContactNameNewChat() throws Exception {

		boolean getname = getFirstContactnameNewChat.isDisplayed();
		String name = getFirstContactnameNewChat.getText().trim();
		verifyAssertTrue(getname, "Contact name should be visible" + name);

	}

	/**
	 * Get Second contact name in New chat page
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getSecondContactNameNewChat() throws Exception {

		boolean getname = getSecondContactnameNewChat.isDisplayed();

		String name = firstContactName.getText().trim();
		verifyAssertTrue(!name.isEmpty(), "Second user contact name should be visible" + name);

	}

	/**
	 * Verify first Contact Radio Button
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void verifyContactRadioButton() throws Exception {

		boolean value = firstContactrbn.isDisplayed();
		if (value == true) {

			log("First contact user radio button should be visible");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "First contact user radio button not visible");
		}

	}

	/**
	 * Click Contact Radio Button
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void clickRadiobutton() throws Exception {

		uiClick(firstContactrbn);
		log("click first contact radio button");

		uiClick(secondContactrbn);
		log("Click second contact radio button");

		takeScreenshot("");

	}

	/**
	 * Get Participants Count
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getParticipantsCount() throws Exception {

		String getcount = ParticipantsCount.getText().trim();
		boolean count = ParticipantsCount.isDisplayed();
		verifyAssertTrue(count, "Participants Count should be visible");
		log("Participants" + getcount);

	}

	/**
	 * Get Participants user's Picture icon and name
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getParticipantsUsers() throws Exception {

		String getuser_01 = Participantsuser_01.getText().trim();
		boolean getuser01 = Participantsuser_01.isDisplayed();
		verifyAssertTrue(getuser01, "Participants user one, should be visible");
		log("Participants user" + getuser_01);

		String getuser_02 = Participantsuser_02.getText().trim();
		boolean getuser02 = Participantsuser_02.isDisplayed();
		verifyAssertTrue(getuser02, "Participants user two, should be visible");
		log("Participants user" + getuser_02);

		takeScreenshot("");
	}

	/**
	 * Randome name
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */

	public String randomIdentifier() {

		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(5) + 5;
			for (int i = 0; i < length; i++) {
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}

		return builder.toString();

	}

	/**
	 * Enter Group name
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void enterGroupName(String txt) throws Exception {

		String getname = randomIdentifier();

		uiClick(enterGruopName);
		// String Groupname = getname+"Test";
		uiSendKeysById(enterGruopName, txt);
		log("Assert Passed : Enter Group Name :  " + txt);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Assert Passed : Enter Group Name :  " + txt);
		takeScreenshot("");

	}

	/**
	 * Enter Ultimate goal
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void enterEnterUltimateGoal(String amount) throws Exception {

		uiClick(enterAmounte);
		uiSendKeysById(enterAmounte, amount);
		log("Enter Ultimate goal : " + amount);
		takeScreenshot("");

	}

	/**
	 * Get Total
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void geTotal() throws Exception {

		String getotal = TotalZar.getText().trim();
		log("Total ZAR : " + getotal);

	}
	
	/**
	 * Get Total
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void geTotalTransactions() throws Exception {

		String getotal = TotalZarTransaction.getText().trim();
		boolean name = TotalZarTransaction.isDisplayed();
		verifyAssertTrue(name, "Group Total, should be visible");
		log("Group Total " + getotal);

	}

	/**
	 * Verify Contribution profile pic
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void verifyContributionProfile() throws Exception {

		boolean p1 = profilepicone.isDisplayed();
		boolean p2 = profilepictwo.isDisplayed();
		boolean p3 = profilepicthree.isDisplayed();
		verifyAssertTrue(p1, "Contribution user  profile  picture, should be visible");
		verifyAssertTrue(p2, "Contribution user  profile  picture, should be visible");
		verifyAssertTrue(p3, "Contribution user  profile  picture, should be visible");
		log("Verify Contribution profile pictures");

	}

	
	/**
	 * Verify check box
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void verifyCheckBoxLoans(String user) throws Exception {
		
		boolean p1 = SingletonClass.getAndroidDriver().findElement(By.xpath("//*[@text='"+user+"']/../following-sibling::android.widget.ImageView")).isDisplayed();

		verifyAssertTrue(p1,  user + " check box, should be visible");

		log("Check box found: " + user);
		
	}
	
	/**
	 * Verify user profile
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void verifyUserProfileLoans(String user) throws Exception {
		
		boolean p1 = SingletonClass.getAndroidDriver().findElement(By.xpath("//*[@text='"+user+"']/../preceding-sibling::android.widget.RelativeLayout")).isDisplayed();

		verifyAssertTrue(p1, "Member user  profile  picture, should be visible");

		log("Profile Image found: " + user);
		
	}
	
	
	
	/**
	 * Verify Name and member amount
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void verifyNameandAmount() throws Exception {

		String nameOne = firstcontributorName.getText().trim();
		String memberoneAmount = MemberOneAmount.getText().trim();

		log("'" + nameOne + " :'" + memberoneAmount);

		String nameTwo = SecondContributorName.getText().trim();
		String membertwoAmount = MemberTwoAmount.getText().trim();
		log("'" + nameTwo + " :'" + membertwoAmount);

		String admin = adminName.getText().trim();
		String adminAmount = AdminAmount.getText().trim();
		log("'" + admin + ": '" + adminAmount);

	}

	/**
	 * Get Total
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getGroupName() throws Exception {

		String getname = groupName.getText().trim();
		boolean name = groupName.isDisplayed();
		verifyAssertTrue(name, "Group name, should be visible");
		log("Group Name : " + getname);

	}

	
	/**
	 * Get Frequency goal
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getUltimateGoalAmountRules() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvAmount']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Ultimate Target, should be visible");
		log("Ultimate Target : " + gettext);
		
	
	}
	
	/**
	 * Get Frequency goal
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getMGRGoalAmountRules() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvContributionAmount']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Contribution Amount, should be visible");
		log("Contribution Amount : " + gettext);
		
	
	}
	
	/**
	 * Get Start Date
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getStartDateRules() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvStartDate']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Start date, should be visible");
		log("Start date : " + gettext);

	}
	
	/**
	 * Get End Date
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getEndDateRules() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvEndDate']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "End date, should be visible");
		log("End date : " + gettext);

	}
	
	/**
	 * Get Participants Count
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getParticipantsCountRules() throws Exception {

		String getcount = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvMemberCount']")).getText()
				.trim();
		
		
		boolean count = ParticipantsCountRule.isDisplayed();
		verifyAssertTrue(count, "Participants Count should be visible");
		log("Participants" + getcount);

	}
	
	/**
	 * Get Frequency
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getFrequencyRUles() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvFrequency']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Frequency, should be visible");
		log("Frequency : " + gettext);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Get Start Date
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getStartDate() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvStartDate']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Start date, should be visible");
		log("Start date : " + gettext);

	}

	/**
	 * Get End Date
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getEndDate() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()   
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvEndDate']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "End date, should be visible");
		log("End date : " + gettext);

	}

	/**
	 * Get Frequency
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getFrequency() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvFrequency']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Frequency, should be visible");
		log("Frequency : " + gettext);

	}
	
	/**
	 * Validate xpath get val with expected value
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void VerifyTextWithXpath(String path, String text) throws Exception {

		String gettext = SingletonClass.getAndroidDriver().findElement(By.xpath(path))
				.getText().trim();
//		verifyAssertTrue(!gettext.isEmpty(), "Frequency, should be visible");

		verifyAssertTrue(!gettext.isEmpty(), text +", should be visible");
//		assertEquals(gettext, expected);

		log( text +": " + gettext);

	}
	
	/**
	 * Validate xpath get val with expected value
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void VerifyTextWithXpath(String path, String expected, String text) throws Exception {

		String gettext = SingletonClass.getAndroidDriver().findElement(By.xpath(path)).getText().trim();
//		verifyAssertTrue(!gettext.isEmpty(), "Frequency, should be visible");

		verifyAssertTrue(!gettext.isEmpty(), text +", should be visible");
		assertEquals(gettext, expected);

		log( text +": " + gettext);

	}

	/**
	 * Get Frequency
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public String getFrequencyofGroup() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@text='Frequency']/following-sibling::android.widget.TextView[1]")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Frequency, should be visible");
//		log("Frequency : " +gettext);

		return gettext;
	}

	/**
	 * Get Draw Date -admin
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getFirstUserDrawDate(String user) throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(
						By.xpath("//android.widget.TextView[@text='You']/following-sibling::android.widget.TextView"))
				.getText().trim();
		verifyAssertTrue(!gettext.isEmpty(), user + " draw date, should be visible");
		log(user + " Draw date : " + gettext);

	}

	/**
	 * Get Draw Date - second user
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getSecondUserDrawDate(String user) throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath(
						"//android.widget.TextView[@text=\"" + user + "\"]/following-sibling::android.widget.TextView"))
				.getText().trim();
		verifyAssertTrue(!gettext.isEmpty(), user + " draw date, should be visible");
		log(user + " Draw date : " + gettext);

	}

	/**
	 * Get Draw Date - third user
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getThirdUserDrawDate(String user) throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath(
						"//android.widget.TextView[@text=\"" + user + "\"]/following-sibling::android.widget.TextView"))
				.getText().trim();
		verifyAssertTrue(!gettext.isEmpty(), user + " draw date, should be visible");
		log(user + " Draw date : " + gettext);

	}

	/**
	 * Get Frequency goal
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getFrequencygoal() throws Exception {

		String gettext = getFrequencygoal.getText().trim();
		log("Frequency goal : " + gettext);

	}

	/**
	 * Get Take Home Amount Per member
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getTakeHomeAmountPermember() throws Exception {

		String gettext = getTakeHomeAmount.getText().trim();
		log(getFrequencyofGroup() + " Take home amount per member : " + gettext);

	}

	/**
	 * Add Decimal Points to a number
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public String ConvertToUsDecimal(String number) throws Exception {

		int inum = Integer.parseInt(number);

		NumberFormat anotherFormat = NumberFormat.getNumberInstance(Locale.US);
		DecimalFormat anotherDFormat = (DecimalFormat) anotherFormat;
		anotherDFormat.applyPattern("#.00");
		anotherDFormat.setGroupingUsed(true);
		anotherDFormat.setGroupingSize(3);

		return anotherDFormat.format(inum);

	}

	/**
	 * Get Frequency goal
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getContributionamount() throws Exception {

//		setFocus();
		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvContributionAmount']"))
				.getText().trim();
		verifyAssertTrue(!gettext.isEmpty(), "Contribution amount, should be visible");
		log("Contribution amount : " + gettext);

	}
	
	/**
	 * Get Frequency goal
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getContributionamountns() throws Exception {

//		setFocus();
		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvAmount']"))
				.getText().trim();
		verifyAssertTrue(!gettext.isEmpty(), "Contribution amount, should be visible");
		log("Contribution amount : " + gettext);

	}
	
	/**
	 * Get Frequency goal
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getUltimateGoalamount() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvGoal']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Ultimate Goal, should be visible");
		log("Ultimate Goal : " + gettext);
		
	
	}
	
	/**
	 * Get Frequency goal
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getContributionamountmgr() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvGoal']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Custom Goal, should be visible");
		log("Custom Goal : " + gettext);
		
	
	}
	
	
	/**
	 * Get Amount to transfer
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getAmounttotransfer() throws Exception {

//		setFocus();
		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvSubTotalAmount']"))
				.getText().trim();
		verifyAssertTrue(!gettext.isEmpty(), "Amount to transfer, should be visible");
		log("Amount to transfer : " + gettext);

	}
	
	/**
	 * Get Service Charge
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getServiceCharge() throws Exception {

//		setFocus();
		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvServiceFeeCurrency']"))
				.getText().trim();
		verifyAssertTrue(!gettext.isEmpty(), "Service Charge, should be visible");
		log("Service Charge : " + gettext);

	}
	
	/**
	 * Get Service Charge
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getTimesStamp() throws Exception {

//		setFocus();
		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvDate']"))
				.getText().trim();
		verifyAssertTrue(!gettext.isEmpty(), "Transaction Date/Time, should be visible");
		log("Transaction Date/Time : " + gettext);

	}

	
	/**
	 * Get Service Charge
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getTotalAmount() throws Exception {

//		setFocus();
		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvTotalAmount']"))
				.getText().trim();
		verifyAssertTrue(!gettext.isEmpty(), "Total Amount, should be visible");
		log("Total Amount : " + gettext);

	}

	
	/**
	 * Click User
	 *
	 * @param text -
	 * @throws Exception
	 * @author Bathiya
	 */
	public void clickActiveUser() throws Exception {

		uiClick(ActiveUser);
		waitForLoading(20);
		log("Click User");

	}

	/**
	 * Get Remove user status
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getRemovedUser(String txt, int scrollCount, ObjectTypes objtype, boolean takeScreenshot)
			throws Exception {

		boolean value = isVisibleTextViewWithoutLog(txt);
		String gettext;
		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getAndroidDriver(), 5).ignoring(TimeoutException.class)
							.ignoring(WebDriverException.class)
							.until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getAndroidDriver()
									.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt
											+ "\"]/following-sibling::android.widget.TextView[1]"))));
					value = SingletonClass.getAndroidDriver()
							.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt + "\"]")).isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);

				} catch (TimeoutException e) {
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);
				}

				if (value == true) {

					gettext = SingletonClass.getAndroidDriver()
							.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt
									+ "\"]/following-sibling::android.widget.TextView[1]"))
							.getText().trim();
					verifyAssertTrue(gettext.equals("Removed"), txt + " removed from the group");
					log(txt + " Removed successfully ");
//					takeScreenshot("Assert Passed : Verify '" + gettext + "' " + objtype.getValue());

					break;
				}
			}
		}
		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + "' removed from the group " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + txt + "' removed from the group " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Verify '" + txt + "' removed from the group " + objtype.getValue());
		}

		Log.info("Assert Passed : Verify '" + txt + "' removed from the group " + objtype.getValue() + " : Found");
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + txt + "' removed from the group " + objtype.getValue() + " : Found");
	}

	/**
	 * Get To be added user status
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getToBeAddedUser(String txt, int scrollCount, ObjectTypes objtype, boolean takeScreenshot)
			throws Exception {

		boolean value = isVisibleTextViewWithoutLog(txt);
		String gettext;
		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getAndroidDriver(), 5).ignoring(TimeoutException.class)
							.ignoring(WebDriverException.class)
							.until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getAndroidDriver()
									.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt
											+ "\"]/following-sibling::android.widget.TextView[1]"))));
					value = SingletonClass.getAndroidDriver().findElement(By.xpath("//android.widget.TextView[@text=\""
							+ txt + "\"]/following-sibling::android.widget.TextView[1]")).isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);

				} catch (TimeoutException e) {
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);
				}

				if (value == true) {

					gettext = SingletonClass.getAndroidDriver()
							.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt
									+ "\"]/following-sibling::android.widget.TextView[1]"))
							.getText().trim();
					verifyAssertTrue(gettext.equals("To be added"),
							txt + " To be added " + objtype.getValue() + " : Found");
					log(txt + " To be added " + objtype.getValue() + ": Found");
//					takeScreenshot(
//							"Assert Passed : Verify '" + txt + "' To be added " + objtype.getValue() + " : Found");

					break;
				}
			}
		}
		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + " To be added " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + txt + " To be added " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Verify '" + txt + "' To be added " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' To be added " + objtype.getValue() + " : Found");
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + txt + "' To be added " + objtype.getValue() + " : Found");
	}

	/**
	 * Get To pending user status
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getPendingUser(String txt, int scrollCount, ObjectTypes objtype, boolean takeScreenshot)
			throws Exception {

		boolean value = isVisibleTextViewWithoutLog(txt);
		String gettext;
		if (value == false) {
			for (int i = 0; i < scrollCount; i++) {
				try {
					new WebDriverWait(SingletonClass.getAndroidDriver(), 5).ignoring(TimeoutException.class)
							.ignoring(WebDriverException.class)
							.until(ExpectedConditions.visibilityOf((AndroidElement) SingletonClass.getAndroidDriver()
									.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt
											+ "\"]/following-sibling::android.widget.TextView[1]"))));
					value = SingletonClass.getAndroidDriver().findElement(By.xpath("//android.widget.TextView[@text=\""
							+ txt + "\"]/following-sibling::android.widget.TextView[1]")).isDisplayed();

				} catch (NoSuchElementException e) {

					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);

				} catch (TimeoutException e) {
					swipeUpOrDown(0.5, 0.2, 3000);
					value = isVisibleTextViewWithoutLog(txt);
				}

				if (value == true) {

					gettext = SingletonClass.getAndroidDriver()
							.findElement(By.xpath("//android.widget.TextView[@text=\"" + txt
									+ "\"]/following-sibling::android.widget.TextView[1]"))
							.getText().trim();
					verifyAssertTrue(gettext.equals("Pending"),
							txt + " Pending " + objtype.getValue() + " : Found");
					log(txt + " Pending " + objtype.getValue() + ": Found");
//					takeScreenshot(
//							"Assert Passed : Verify '" + txt + "' To be added " + objtype.getValue() + " : Found");

					break;
				}
			}
		}
		if (value == false) {
			Log.info("Assert Failed : Verify '" + txt + " Pending " + objtype.getValue() + " : Not Found");
			Assert.assertTrue(false,
					"Assert Failed : Verify '" + txt + "  Pending " + objtype.getValue() + " : Not Found");
		}

		if (takeScreenshot) {
			takeScreenshot("Verify '" + txt + "' Pending " + objtype.getValue());
		}
		Log.info("Assert Passed : Verify '" + txt + "' Pending " + objtype.getValue() + " : Found");
		ExtentTestManager.getTest().log(LogStatus.PASS,
				"Assert Passed : Verify '" + txt + "' Pending " + objtype.getValue() + " : Found");
	}
	
}
