package Hackethon.IdentifyNewBikes.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
	public static ExtentReports extent;
	
	
	public static ExtentReports getInstance() {
		String fileName = "./test-output/Reports/ExtentReport.html";
		if(extent == null) {
	        // Create an HTML reporter to generate the Extent report.
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName); 

	        // Create a new instance of ExtentReports and attach the HTML reporter to it.
	        extent = new ExtentReports();

	        extent.attachReporter(htmlReporter);

	        return extent;
		}
		return extent;
	}
	
	public ExtentReports createInstance(String fileName) {

        // Create an HTML reporter to generate the Extent report.

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName); 

        // Create a new instance of ExtentReports and attach the HTML reporter to it.
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);

        return extent;

    }

//	public void TestPassed(String msg) {
//		logger.log(Status.PASS, msg);
//	}
//	public void TestFail(String msg) {
//		logger.log(Status.FAIL, msg);
//	}
	
	public static void Flush() {
		extent.flush();
	}
}
