package Hackethon.IdentifyNewBikes.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Hackethon.IdentifyNewBikes.Base.DriverSetup;
import Hackethon.IdentifyNewBikes.utils.ExtentReport;
import Hackethon.IdentifyNewBikes.utils.Highlight;
import Hackethon.IdentifyNewBikes.utils.WriteExcelData;

public class UsedCars extends DriverSetup {
	public static ExtentTest logger;
	public static ExtentReports extent = ExtentReport.getInstance();

	static By usedCarLocator = By.linkText("Used Cars");
	static By getChennaiLocator = By.xpath("//span[text()='Chennai']");
	static By scrollModelLocator = By.className("gsc_thin_scroll");
	static By popularCarsLocator = By.xpath("//div[@class='zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10']");

	public static void UsedCarsChennai() {
		try {
			// instantiating actions class
			Actions actions = new Actions(driver);

			WebElement usedCar = driver.findElement(usedCarLocator);
			Thread.sleep(2000);
			actions.moveToElement(usedCar).perform();
			Thread.sleep(2000);
			WebElement getChennai = driver.findElement(getChennaiLocator);
			Highlight.highlight(driver, getChennaiLocator); // highlight
			getChennai.click();

			logger.log(Status.PASS ,"Used Cars in Chennai is selected");
		} catch (Exception e) {
			// TODO: handle exception
			logger.log(Status.FAIL ,e.getMessage());
		}
	}
	
	public static void PopularModels() {
		try {
			// scroll down is working
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement scrollModel = driver.findElement(scrollModelLocator);
			js.executeScript("arguments[0].scrollIntoView(true)", scrollModel);
			Thread.sleep(3000);

			WriteExcelData.writeExcelTopCellCars();

			// get all the popular cars
			List<WebElement> l = driver.findElements(popularCarsLocator);
			for (WebElement w : l) {
				System.out.println(w.getText());
			}
			WriteExcelData.writeExcelData(l, "Cars");
			
			logger.log(Status.PASS ,"Popular Models fetched");
		}catch (Exception e) {
			// TODO: handle exception
			logger.log(Status.FAIL ,e.getMessage());
		}
	}

	
	
	public static void usedCars() {
		try {
			// Get back to main page
			driver.navigate().back();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.navigate().back();

			// create a test case log in extent report
			logger = extent.createTest("Used Cars in Chennai");
			UsedCarsChennai();
			
			// create a test case log in extent report
			logger = extent.createTest("Popular Models in Chennai");
			PopularModels();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}