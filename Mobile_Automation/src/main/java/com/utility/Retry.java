package com.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
 
/**
 * 
 */
public class Retry implements IRetryAnalyzer {
 
    private int count = 0;
    private static int maxTry = 0;
 
    public boolean retry(ITestResult iTestResult) {
    	Log.info("Retry enabled for testcase" + iTestResult.getTestName());
        if (!iTestResult.isSuccess()) {                      //Check if test not succeed
            if (count < maxTry) {                            //Check if maxtry count is reached
                count++;                                     //Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
                Log.info("Retry " + iTestResult.getTestName()+" count : "+count);  
                return true;                                 //Tells TestNG to re-run the test
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }
 
}