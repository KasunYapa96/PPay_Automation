package com.chivelab.pageObjAndroid;

import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.utility.SingletonClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ManualPaymentsSendtoMemberPage extends BasePageObject {
	
	// class variable
	final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

	final java.util.Random rand = new java.util.Random();

	// consider using a Map<String,Boolean> to say whether the identifier is being used or not 
	final Set<String> identifiers = new HashSet<String>();

	@SuppressWarnings("unused")
	private AndroidDriver<AndroidElement> driver;
	public static AndroidElement element = null;

	public ManualPaymentsSendtoMemberPage() {
	}	

	public ManualPaymentsSendtoMemberPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	// Amount textbox
	@AndroidFindBy(id = "com.visa.android:id/etAmount")
	private AndroidElement amountTxtbox;
	
	
	// Reason textbox
	@AndroidFindBy(id = "com.visa.android:id/etReason")
	private AndroidElement reasonTxtbox;
	
	// Send to group button
	@AndroidFindBy(id = "com.visa.android:id/relSendToGroup")
	private AndroidElement sendToGroup;
	
	
	//---Get Start / End and Frequency
	private AndroidElement getTotal(String text)
	{
		return driver.findElement(By.xpath("//android.widget.TextView[@text='"+text+"']/following-sibling::android.widget.TextView"));
	}
	
	
	/**
	 * Request money - payments
	 *
	 * @param amount - send amount
	 * 			reason - reason of the payment
	 * @throws Exception
	 * @author Amila
	 */
	public void requestMoneyPayments(String amount, String reason, String user) throws Exception{

		uiSendKeys(amountTxtbox, amount);
		hideKeyboard();
		uiSendKeys(reasonTxtbox, reason);
		hideKeyboard();
		uiClick((AndroidElement) SingletonClass.getAndroidDriver().findElement(By.xpath("//android.widget.TextView[@text=\""+user+"\"]/following-sibling::android.widget.TextView[1]")));
		sleep(2);
		takeScreenshot("Send to member");
		uiClick(sendToGroup);	
		sleep(3);
		
		log("Request money : amount: "+amount+ ", reason: "+reason+", user: "+user);
	}
	
	/**
	 * Request money - payments
	 *
	 * @param amount - send amount
	 * 			reason - reason of the payment
	 * @throws Exception
	 * @author Amila
	 */
	public void sendGroupMoneyToMemberPayments(String amount, String reason, String user) throws Exception{

		uiSendKeys(amountTxtbox, amount);
		hideKeyboard();
		uiSendKeys(reasonTxtbox, reason);
		hideKeyboard();
		swipingVertical(0.70,0.40);
		uiClick((AndroidElement) SingletonClass.getAndroidDriver().findElement(By.xpath("//android.widget.TextView[@text=\""+user+"\"]/following-sibling::android.widget.TextView[1]")));
		sleep(2);
		takeScreenshot("Send group money to member");
		uiClick(sendToGroup);	
		sleep(3);
		
		log("Send Group Money To Member : amount: "+amount+ ", reason: "+reason+", user: "+user);
	}
	
	
	/**
	 * Get Total
	 *
	 * @param text - 
	 * @throws Exception
	 * @author Bathiya
	 */
	public void geTotal() throws Exception{
		
		
		String gettext = getTotal("Total").getText().trim();		
		//verifyAssertTrue(gettext, "Total Should be : "+gettext);
		log("Total : "+gettext);
	
	}
}
