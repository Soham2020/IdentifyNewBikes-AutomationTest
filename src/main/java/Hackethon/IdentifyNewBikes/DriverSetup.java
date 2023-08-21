package Hackethon.IdentifyNewBikes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	public static void driverSetup() {
		try {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.zigwheels.com/");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
