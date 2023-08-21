package Hackethon.IdentifyNewBikes;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class NewTest {
	static WebDriver driver;
	@Test
	public void f() {
		try {
			FindNewBikes.findNewBikes(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@BeforeClass
	public void beforeClass() {
		try {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://www.zigwheels.com/");
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		try {
			Thread.sleep(3000);
			driver.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
