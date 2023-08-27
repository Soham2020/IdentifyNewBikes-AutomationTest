package Hackethon.IdentifyNewBikes;

import org.testng.annotations.Test;

import Hackethon.IdentifyNewBikes.Base.DriverSetup;
import Hackethon.IdentifyNewBikes.Pages.FindNewBikes;
import Hackethon.IdentifyNewBikes.Pages.LoginPage;
import Hackethon.IdentifyNewBikes.Pages.UsedCars;
import Hackethon.IdentifyNewBikes.utils.ExtentReport;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class NewTest {
	public static WebDriver driver;
	DriverSetup setup = new DriverSetup();

	@Test
	public void FirstTestCase() {
		try {
			FindNewBikes.findNewBikes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods = {"FirstTestCase"} )
	public void SecondTestCase() {
		try {
			UsedCars.usedCars();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods = {"SecondTestCase"} )
	public void ThirdTestCase() {
		try {
			LoginPage.loginPage();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@BeforeClass
	public void beforeClass() {
		try {
//			FileReader fr = new FileReader("./Resource/config.properties");
//			Properties p = new Properties();
//			p.load(fr);
//			String url = p.getProperty("url");
//			driver = DriverSetup.driverSetup(driver);
//			driver.navigate().to(url);
//			driver.manage().window().maximize();
//			driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
			setup.driverSetup("chrome");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		try {
			ExtentReport.Flush();
			setup.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
