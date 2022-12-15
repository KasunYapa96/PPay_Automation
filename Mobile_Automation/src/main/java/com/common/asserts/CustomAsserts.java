package com.common.asserts;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.utils.ExtentReports.ExtentTestManager;

public class CustomAsserts extends SoftAssert {
	
    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
    	ExtentTestManager.getTest().log(LogStatus.INFO, "Assert Failed -----> Value : "+ex);
    }
    @Override
    public void onAssertSuccess(IAssert<?> assertCommand) {
    	ExtentTestManager.getTest().log(LogStatus.INFO, "Assert Passed -----> Value : "+assertCommand.getActual());
    }
    
}
