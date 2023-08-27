package Hackethon.IdentifyNewBikes.Base;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//public class DriverSetup {
//	public static WebDriver driverSetup(WebDriver driver) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Please Enter the correct option:: ");
//		try {
//			int options = sc.nextInt();
//			if(options == 1) {
//				WebDriverManager.chromedriver().setup();
//				driver = new ChromeDriver();			}
//			else if(options == 2) {
//				WebDriverManager.edgedriver().setup();
//				driver = new EdgeDriver();
//			}
//			sc.close();
//			return driver;
//		}catch(Exception e) {
//			System.out.println("Please enter the correct option");
//			return null;
//		}
//	}
//}



public class DriverSetup {
	public static WebDriver driver;
	
	public void driverSetup(String browser) {
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			
			FileReader fr = new FileReader("./Resource/config.properties");
			Properties p = new Properties();
			p.load(fr);
			String url = p.getProperty("url");
			driver.navigate().to(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
			
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}
	public void close() {
		driver.quit();
	}
}