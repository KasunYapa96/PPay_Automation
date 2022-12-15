package com.chivelab.pageObjAndroid;

import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;
import com.utility.ObjectTypes;
import com.utility.SingletonClass;
import com.utils.ExtentReports.ExtentTestManager;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class P2PLoanPage extends BasePageObject {
	
	// class variable
	final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

	final java.util.Random rand = new java.util.Random();

	// consider using a Map<String,Boolean> to say whether the identifier is being used or not 
	final Set<String> identifiers = new HashSet<String>();

	@SuppressWarnings("unused")
	private AndroidDriver<AndroidElement> driver;
	public static AndroidElement element = null;

	public P2PLoanPage() {
	}	

	public P2PLoanPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Ann']/ancestor::android.widget.LinearLayout[1]/following-sibling::android.widget.ImageView")
	private AndroidElement Barrower;
	

	
	//  barrower checkbox
	private AndroidElement Barrower(String text)
	{
		return driver.findElement(By.xpath("//android.widget.TextView[@text='"+text+"']/ancestor::android.widget.LinearLayout[1]/following-sibling::android.widget.ImageView"));
	}
	
	// Get Loan offer detailes
//	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Loan Offer']/following-sibling::android.widget.RelativeLayout[1]/android.widget.TextView")
	@AndroidFindBy(xpath = "//*[@id='com.saiv.android:id/tvHeader']")
	private AndroidElement Getloanoffer;
	

	
	/**
	 * Click first barrower checkbox
	 *
	 * @param text - 
	 * @throws Exception
	 * @author Bathiya
	 */
	public void clickUserFirstbarrower(String user) throws Exception{
		
		uiClick(Barrower(user));
		waitForLoading(20);
		log("Click barrower");
		
	
	}
	
	/**
	 * Click first barrower checkbox
	 *
	 * @param text - 
	 * @throws Exception
	 * @author Bathiya
	 */
	public void clickUserFirstbarrower1(String user) throws Exception{
		
//		swipeVerticalFromElement(Barrower(user), 0.80, 0.30, 5);
		

		uiClick(Barrower(user));
//		clickAndroidElementByIdOnPage2(Barrower(user), user, ObjectTypes.BUTTON, 5);
		waitForLoading(20);
		log("Click barrower");
		
	
	}
	
	/**
	 * Get Due date
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getDueDate() throws Exception {

//		String gettext = SingletonClass.getAndroidDriver()
//				.findElement(By.xpath("//*[@text='Due date']/following-sibling::android.widget.TextView[1]")).getText()
//				.trim();
		
		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.id("com.saiv.android:id/tvPaybackPeriod")).getText()
				.trim();
		
		
		verifyAssertTrue(!gettext.isEmpty(), "Due date, should be visible");
		log("Due date : " + gettext);

	}
	
	/**
	 * Get Due date
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getGratuity() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@text='Gratuity']/following-sibling::android.widget.TextView[1]")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Gratuity, should be visible");
		log("Gratuity : " + gettext);

	}
	
	/**
	 * Get Due date
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getReason() throws Exception {

		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvReason']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Reason, should be visible");
		log("Reason : " + gettext);

	}
	
	
	/**
	 * Get personal loan count
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getPersonalLoanCount(String expected) throws Exception {

//		String gettext = SingletonClass.getAndroidDriver()
//				.findElement(By.xpath("//*[@resource-id='com.visa.android:id/tvPersonalLoanCount']")).getText()
//				.trim();
//		assertEquals(gettext, expected,"Personal loan count is different. Expected "+expected+" but found "+gettext);
//	
//		log("Personal loan count : " + gettext);
		
		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvPersonalLoanCount']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Personal loan count, should be visible");
		log("Personal loan count : " + gettext);

	}
	
	/**
	 * Get Group loan count
	 *
	 * @param text -
	 * @throws Exception
	 * @author Hasitha
	 */
	public void getGroupLoanCount(String expected) throws Exception {

//		String gettext = SingletonClass.getAndroidDriver()
//				.findElement(By.xpath("//*[@resource-id='com.visa.android:id/tvGroupLoanCount']")).getText()
//				.trim();
//		assertEquals(gettext, expected,"Group loan count is different. Expected "+expected+" but found "+gettext);
//	
//		log("Group loan count : " + gettext);
		
		String gettext = SingletonClass.getAndroidDriver()
				.findElement(By.xpath("//*[@resource-id='com.saiv.android:id/tvGroupLoanCount']")).getText()
				.trim();
		verifyAssertTrue(!gettext.isEmpty(), "Group loan count, should be visible");
		log("Group loan count : " + gettext);

	}
	
	
	/**
	 * Get Loan offer detailes
	 *
	 * @param text - 
	 * @throws Exception
	 * @author Bathiya
	 */
	public void getLoanOfferDetailes() throws Exception{
		
		String getText = Getloanoffer.getText().trim();
		
		String splitgettxt[] = getText.split(". ");
		String getnumber = splitgettxt[0];
		String getLoan = splitgettxt[4];
		String getgratuity = splitgettxt[9].replace("-", "");
		String getdueDate = splitgettxt[13].replace("Tota", "");
		String getTotalAmount = splitgettxt[17].replace(".", "");
		
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Loan Offering number :  "+getnumber);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Loan Amount ZAR :  "+getLoan);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Gratuity :  "+getgratuity);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Due Date :  "+getdueDate);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Total Amount ZAR :  "+getTotalAmount);
		
	
	}
	
	public void DownNotification(){
		
		swipeUpOrDown(0.01, 0.5, 3000);
		
	}
	
	
	

	
}
