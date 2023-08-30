package Hackethon.IdentifyNewBikes.Base;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Hackethon.IdentifyNewBikes.utils.ExtentReport;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	public ExtentReports extent = ExtentReport.getInstance();
	public ExtentTest logger;
//	ScreenShot ss = new ScreenShot();

	public static WebDriver driver;

	public void driverSetup(String browser) {

		try {
			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}

			FileReader fr = new FileReader("./Resource/config.properties");
			Properties p = new Properties();
			p.load(fr);
			String url = p.getProperty("url");
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void close() {
		driver.quit();
	}

	public void reportPass(String message) {
		logger.log(Status.PASS, message);
	}

	public void reportFail(String message, String imgName) throws Exception {
		logger.log(Status.FAIL, message);
		logger.addScreenCaptureFromPath(
				"C:\\Users\\2282058\\Downloads\\eclipse-java-2023-03-R-win32-x86_64 (1)\\eclipse\\IdentifyNewBikes\\screenshots\\"
						+ imgName + ".png");
	}

}
