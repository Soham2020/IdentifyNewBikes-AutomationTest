package Hackethon.IdentifyNewBikes.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	
	public static ExtentReports getInstance() {
		if(extent == null) {
			extent = createInstance("./test-output/Reports/ExtentReport.html");
		}
		return extent;
	}
	
	public static ExtentReports createInstance(String fileName) {

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
