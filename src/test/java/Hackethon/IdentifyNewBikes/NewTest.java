package Hackethon.IdentifyNewBikes;

import org.testng.annotations.Test;

import Hackethon.IdentifyNewBikes.Base.DriverSetup;
import Hackethon.IdentifyNewBikes.Pages.FindNewBikes;
import Hackethon.IdentifyNewBikes.Pages.LoginPage;
import Hackethon.IdentifyNewBikes.Pages.UsedCars;
import Hackethon.IdentifyNewBikes.utils.ExtentReport;

import org.testng.annotations.BeforeClass;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class NewTest {
	public static WebDriver driver;

	DriverSetup setup = new DriverSetup();
	FindNewBikes findNewBikes = new FindNewBikes();
	LoginPage loginPage = new LoginPage();
	UsedCars usedCars = new UsedCars();

	@Test(priority = 1, groups = "Smoke")
	public void FirstTestCase() {
		try {
			findNewBikes.findNewBikes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2, groups = "Regression")
	public void SecondTestCase() {
		try {
			usedCars.usedCars();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test(priority = 3, groups = "Smoke")
	public void ThirdTestCase() {
		try {
			loginPage.loginPage();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

//	@Test(groups = "Regression")
//	public void FirstTestCase1() {
//		try {
//			FindNewBikes.findNewBikes();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test(dependsOnMethods = {"FirstTestCase1"}, groups = "Regression" )
//	public void SecondTestCase1() {
//		try {
//			UsedCars.usedCars();
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	
//	@Test(dependsOnMethods = {"SecondTestCase1"}, groups = "Regression" )
//	public void ThirdTestCase1() {
//		try {
//			LoginPage.loginPage();
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		try {
			setup.driverSetup("chrome");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass(alwaysRun = true)
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
