package com.chivelab.configs;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.utility.Log;
import com.utility.SingletonClass;

import java.net.MalformedURLException;
import java.util.Map;

public class ConfigTestSAIV {

	// Declare A Map Object To Hold TestNG Results
	public static Map<Integer, Object[]> TestNGResults;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuiteMethod() throws MalformedURLException,Exception {
		try {
			DOMConfigurator.configure("log4j.xml");
			SingletonClass.setFrameWorkProp();
			SingletonClass.setTestData();
			SingletonClass.setExpectedResults();
			SingletonClass.setCacheMap();
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			Log.info("Setup test configuration success------------>");
			
		} catch (Exception e) {
			Log.info("Setup test configuration fail------------>"+e.getMessage());

		}

	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws InterruptedException,Exception {
		try {
			SingletonClass.getAndroidDriver().quit();
			Log.info("Kill android driver success------------>");
		} catch (Exception e) {
			Log.info("Kill android driver fail------------>"+e.getMessage());
		}

	}

}
