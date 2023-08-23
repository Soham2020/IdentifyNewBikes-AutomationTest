package Hackethon.IdentifyNewBikes;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	public static WebDriver driverSetup(WebDriver driver) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter the correct option:: ");
		try {
			int options = sc.nextInt();
			if(options == 1) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();			}
			else if(options == 2) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			sc.close();
			return driver;
		}catch(Exception e) {
			System.out.println("Please enter the correct option");
			return null;
		}
	}
}
