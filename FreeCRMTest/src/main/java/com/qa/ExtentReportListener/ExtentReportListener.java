package com.qa.ExtentReportListener;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportListener implements IReporter{
	private ExtentReports extent;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports(outputDirectory + File.separator + "Extent.html", true);
		
		
		for(ISuite suite: suites) {
			Map<String, ISuiteResult> result = suite.getResults();
			
			
			for(ISuiteResult r: result.values()) {
				ITestContext context = r.getTestContext();
				
				buildTestNotes(context.getPassedTests(), LogStatus.PASS);
				buildTestNotes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNotes(context.getSkippedTests(), LogStatus.PASS);
			}
		}
		
		extent.flush();
		extent.close();
		
		
	}

	private void buildTestNotes(IResultMap test, LogStatus pass) {
		
		
	}



}
