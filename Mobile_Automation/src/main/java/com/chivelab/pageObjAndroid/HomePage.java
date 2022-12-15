package com.chivelab.pageObjAndroid;

import javax.management.Notification;

import org.mozilla.javascript.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.utility.Log;
import com.utility.ObjectTypes;
import com.utility.SingletonClass;
import com.utils.ExtentReports.ExtentManager;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends BasePageObject {

	@SuppressWarnings("unused")
	private AndroidDriver<AndroidElement> driver;
	public static AndroidElement element = null;

	public HomePage() {
	}

	public HomePage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Search bar
	@AndroidFindBy(id = "com.saiv.android:id/etSearch")
	private AndroidElement searchBar;

	// Monthly contribution reminder title
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='30 Minutes contribution reminder']")
	private AndroidElement monthlyContributionReminderTxt;

	// Clear Notifications
	@AndroidFindBy(id ="com.android.systemui:id/clear_all_button")
	private AndroidElement clear;
	
	// Group Goal Reach reminder title
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Would you like to continue with the same group or terminate ?']")
	private AndroidElement GroupGoalReachReminderTxt;

	// Plus button - group chat
	@AndroidFindBy(id = "com.saiv.android:id/btnPlus")
	private AndroidElement plusBtnGroupChat;

	// Amount textbox
	@AndroidFindBy(id = "com.saiv.android:id/etAmount")
	private AndroidElement amountTxtbox;

	// Reason textbox
	@AndroidFindBy(id = "com.saiv.android:id/etReason")
	private AndroidElement reasonTxtbox;

	// Send to group button
	@AndroidFindBy(id = "com.saiv.android:id/relSendToGroup")
	private AndroidElement sendToGroup;

	// Swap - Confirm button
	@AndroidFindBy(id = "com.saiv.android:id/layoutSubmitBtn")
	private AndroidElement SwapConfirmButton;

//	// Send to group button
//	@AndroidFindBy(id = "com.saiv.android:id/relSendToMember")
//	private AndroidElement sendToMember;
//	
//	// Send to group button
//	@AndroidFindBy(id = "com.saiv.android:id/relRequestMoney")
//	private AndroidElement requestMoney;
//	
	/**
	 * Search Group
	 *
	 * @param text - name of user
	 * @throws Exception
	 * @author Amila
	 */
	public void searchGroup(String groupName) throws Exception {

//		DIsabled search due to a defect in search Function.class remove this when it fixed
		
//		uiClear(searchBar);
//		uiSendKeys(searchBar, groupName);
//		hideKeyboard();
//		sleep(4);
		uiClickTextViewByText(groupName);
		log("Search group : " + groupName);
	}

	/**
	 * Select Treasurer
	 *
	 * @param text - name of user
	 * @throws Exception
	 * @author Amila
	 */
	public void selectTreasurer(String name) throws Exception {

		uiClick((AndroidElement) SingletonClass.getAndroidDriver().findElement(By.xpath(
				"//android.widget.TextView[@text='" + name + "']/following-sibling::android.widget.TextView[1]")));
		sleep(2);

		log("Select Treasurer: " + name);
	}

	/**
	 * Close Monthly contribution reminder popup if visible
	 *
	 * @param N/A
	 * @throws Exception
	 * @author Amila
	 */
	public void closeMonthlyContributionReminderPopup() throws Exception {

		sleep(1);
		while (isVisibleAndroidElementWithoutLog(monthlyContributionReminderTxt)) {
			clickBackButton();
		}
	}

	/**
	 * Close Monthly contribution reminder popup if visible
	 *
	 * @param N/A
	 * @throws Exception
	 * @author Amila
	 */
	public void closeGroupGoalReachReminderPopup() throws Exception {

		sleep(1);
		while (isVisibleAndroidElementWithoutLog(GroupGoalReachReminderTxt)) {
			clickBackButton();
		}
	}

	/**
	 * Close Monthly contribution reminder popup if visible
	 *
	 * @param N/A
	 * @throws Exception
	 * @author Amila
	 */
	public String getStartDate() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//android.widget.TextView[@resource-id='com.saiv.android:id/tvStartDate']"))
				.getText().trim();

		return gettext;
	}

	/**
	 * Send to group - payments
	 *
	 * @param amount - send amount reason - reason of the payment
	 * @throws Exception
	 * @author Amila
	 */
	public void sendToGroupPayments(String amount, String reason) throws Exception {

		uiClick(plusBtnGroupChat);
		uiClickTextViewByText("Payments");
//		uiClickTextViewByText("Send to group");
		uiClickTextViewByText("Send money to group");
		uiSendKeys(amountTxtbox, amount);
		hideKeyboard();
		uiSendKeys(reasonTxtbox, reason);
		hideKeyboard();
		takeScreenshot("Send money to Group");
		uiClick(sendToGroup);
		waitForLoading(10);
		sleep(3);
		log("Send money to group : amount: " + amount + ", reason: " + reason);
	}

	/**
	 * Send to group - payments
	 *
	 * @param amount - send amount reason - reason of the payment
	 * @throws Exception
	 * @author Amila
	 */
	public void swapPositions(String user) throws Exception {

		uiClick(plusBtnGroupChat);
		uiClickTextViewByText("Swap");
		uiClickTextViewByText(user);
		uiClick(SwapConfirmButton);
		sleep(5);
	}



	/**
	 * send Group Money To Member Payments
	 *
	 * @param amount - send amount reason - reason of the payment
	 * @throws Exception
	 * @author Hasitha
	 */
	public void sendGroupMoneyToMemberPayments(String amount, String reason, String user) throws Exception {

		boolean found = false;

		uiClick(plusBtnGroupChat);
		uiClickTextViewByText("Payments");
		uiClickTextViewByText("Send group money to member");
		uiSendKeys(amountTxtbox, amount);
		hideKeyboard();
		uiSendKeys(reasonTxtbox, reason);
		hideKeyboard();
		if (!(isVisibleTextViewWithoutLog(user))) {
			for (int i = 0; i < 20; i++) {
				swipeUpOrDown(0.75, 0.6, 2000);
				found = isVisibleTextViewWithoutLog(user);
				if (found) {
					break;
				}
			}
		}
		uiClick((AndroidElement) SingletonClass.getAndroidDriver().findElement(By.xpath(
				"//android.widget.TextView[@text=\"" + user + "\"]/following-sibling::android.widget.TextView[1]")));
		sleep(2);
		takeScreenshot("Send group money to member");
		uiClick(sendToGroup);
		sleep(3);

		log("Send group money to member : amount: " + amount + ", reason: " + reason + ", user: " + user);
	}

	/**
	 * Request money - payments
	 *
	 * @param amount - send amount reason - reason of the payment
	 * @throws Exception
	 * @author Amila
	 */
	public void requestMoneyPayments(String amount, String reason, String user) throws Exception {

		boolean found = false;

		uiClick(plusBtnGroupChat);
		uiClickTextViewByText("Payments");
		uiClickTextViewByText("Request money for the group");
		uiSendKeys(amountTxtbox, amount);
		hideKeyboard();
		uiSendKeys(reasonTxtbox, reason);
		hideKeyboard();
		if (!(isVisibleTextViewWithoutLog(user))) {
			for (int i = 0; i < 20; i++) {
				swipeUpOrDown(0.75, 0.6, 2000);
				found = isVisibleTextViewWithoutLog(user);
				if (found) {
					break;
				}
			}
		}
		uiClick((AndroidElement) SingletonClass.getAndroidDriver().findElement(By.xpath(
				"//android.widget.TextView[@text=\"" + user + "\"]/following-sibling::android.widget.TextView[1]")));
		sleep(2);
		takeScreenshot("Request money for the group");
		uiClick(sendToGroup);
		sleep(3);

		log("Request money for the group : amount: " + amount + ", reason: " + reason + ", user: " + user);
	}

	/**
	 * Open Notifications
	 *
	 * @param N/A
	 * @throws Exception
	 * @author Amila
	 */
	public void openNotifications() throws Exception {

		// Driver session keep alive
		driverKeepAlive();

		SingletonClass.getAndroidDriver().openNotifications();
		sleep(3);
	}

	/**
	 * Wait for Notifications
	 *
	 * @param N/A
	 * @throws Exception
	 * @author Hasitha
	 */
	public void waitForNotificationsLeaveGroup(String text, int minutes,String userName_1,String randomNumber) throws Exception {

		//Wai for 10 mins
		sleep(600);
		boolean found = false;
		openNotifications();
		sleep(3);
		found = isVisibleTextViewWithoutLog(text);
		if (found == true) {
			
			takeScreenshot("Found Notification: " + text);
			uiClickTextViewByText(text);
			Log.info("Found Push Notifaication: " + text);
			waitForLoading(20);
			sleep(5);
			// verify popup title
			verifyIsVisibleTextView("Remove participants", ObjectTypes.POPUP_TITLE);
			// verify popup text
			verifyIsVisibleTextView("Do you want to remove " + userName_1 + " from Group_" + randomNumber + " Group?",
					ObjectTypes.POPUP_TEXT);
			// verify confirm button
			verifyIsVisibleAndroidElementById("com.saiv.android:id/imgAcceptBtn", "Confirm", ObjectTypes.BUTTON);
			// verify cancel button
			verifyIsVisibleAndroidElementById("com.saiv.android:id/imgTerminateBtn", "Cancel", ObjectTypes.BUTTON);
			// click cancel button
			clickAndroidElementById("com.saiv.android:id/imgTerminateBtn", "Cancel", ObjectTypes.BUTTON, true);
			sleep(5);
		} else {
//			//Log
//			Log.info("Not Found Push Notifaication: " + text);
			
			
			Log.error("Not Found Push Notifaication: " + text);

			//screenshot
			takeScreenshot("Not Found Notification: " + text);
			//Navigates back
			SingletonClass.getAndroidDriver().navigate().back();
			// start precondition
			clickAndroidElementByText("Group_" + randomNumber, ObjectTypes.CHAT_GROUP_TITLE);
			// verify popup title
			verifyIsVisibleTextView("Remove participants", ObjectTypes.POPUP_TITLE);
			// verify popup text
			verifyIsVisibleTextView("Do you want to remove " + userName_1 + " from Group_" + randomNumber + " Group?",
					ObjectTypes.POPUP_TEXT);
			// verify confirm button
			verifyIsVisibleAndroidElementById("com.saiv.android:id/imgAcceptBtn", "Confirm", ObjectTypes.BUTTON);
			// verify cancel button
			verifyIsVisibleAndroidElementById("com.saiv.android:id/imgTerminateBtn", "Cancel", ObjectTypes.BUTTON);
			// click cancel button
			clickAndroidElementById("com.saiv.android:id/imgTerminateBtn", "Cancel", ObjectTypes.BUTTON, true);
			sleep(5);
		}
		
		
		
	}
	
	/**
	 * Wait for Notifications
	 *
	 * @param N/A
	 * @throws Exception
	 * @author Amila
	 */
	public void waitForNotifications(String text, int minutes) throws Exception {

		boolean found = false;
		int seconds = minutes * 60;
		while (!isVisibleTextViewWithoutLog(text)) {

			openNotifications();
			sleep(3);
			found = isVisibleTextViewWithoutLog(text);
			if (found) {
				takeScreenshot("Found Notification: " + text);
				uiClickTextViewByText(text);
				waitForLoading(20);
				sleep(5);
				break;
			} else {
				SingletonClass.getAndroidDriver().navigate().back();
				Log.info("Wait for Push Notifaication: " + text);
			}
			sleep(7);
			if (seconds > 0) {
				seconds = seconds - 10;
			} else {
				openNotifications();
				verifyAssertTrue(false, "Push Notication not found :" + text + ", wait " + minutes + " minutes");
			}
		}
	}

	/**
	 * Wait for Notifications by Xpath
	 *
	 * @param N/A
	 * @throws Exception
	 * @author Amila
	 */
	public void waitForNotificationsByXpath(String xpath, String text, int minutes) throws Exception {

		boolean found = false;
		int seconds = minutes * 60;
		while (!isVisibleElementByXpathWithoutLog(xpath)) {
//					isVisibleElementByXpathWithoutLog(xpath)
			openNotifications();
			sleep(3);
			found = isVisibleElementByXpathWithoutLog(xpath);
			if (found) {
				takeScreenshot("Found Notification: " + text);
				uiClickByXpath(xpath);
				waitForLoading(20);
				sleep(5);
				break;
			} else {
				SingletonClass.getAndroidDriver().navigate().back();
				Log.info("Wait for Push Notifaication: " + text);
			}
			sleep(7);
			if (seconds > 0) {
				seconds = seconds - 10;
			} else {
				openNotifications();
				verifyAssertTrue(false, "Push Notication not found :" + text + ", wait " + minutes + " minutes");
			}
		}
	}

}
