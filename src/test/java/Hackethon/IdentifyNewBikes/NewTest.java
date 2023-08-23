package Hackethon.IdentifyNewBikes;

import org.testng.annotations.Test;

import Hackethon.IdentifyNewBikes.Base.DriverSetup;
import Hackethon.IdentifyNewBikes.Pages.FindNewBikes;
import Hackethon.IdentifyNewBikes.Pages.LoginPage;
import Hackethon.IdentifyNewBikes.Pages.UsedCars;

import org.testng.annotations.BeforeClass;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class NewTest {
	public static WebDriver driver;

	@Test
	public void f() {
		try {
			FindNewBikes.findNewBikes(driver);
			UsedCars.usedCars(driver);
			LoginPage.loginPage(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void beforeClass() {
		try {
			FileReader fr = new FileReader("C:\\Users\\2282058\\Downloads\\eclipse-java-2023-03-R-win32-x86_64 (1)\\eclipse\\IdentifyNewBikes\\src\\main\\java\\Hackethon\\IdentifyNewBikes\\Config\\config.properties");
			Properties p = new Properties();
			p.load(fr);
			String url = p.getProperty("url");
			driver = DriverSetup.driverSetup(driver);
			driver.navigate().to(url);
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		try {
			Thread.sleep(2000);
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
