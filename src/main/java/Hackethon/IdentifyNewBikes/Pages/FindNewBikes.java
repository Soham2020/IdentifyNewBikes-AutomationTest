package Hackethon.IdentifyNewBikes.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Hackethon.IdentifyNewBikes.Base.DriverSetup;
import Hackethon.IdentifyNewBikes.utils.ExtentReport;
import Hackethon.IdentifyNewBikes.utils.Highlight;
import Hackethon.IdentifyNewBikes.utils.WriteExcelData;

public class FindNewBikes extends DriverSetup {
//	public static ExtentReport report = new ExtentReport();
	public static ExtentReports extent = ExtentReport.getInstance();
	public static ExtentTest logger;
	
	static By newBikesLocator = By.linkText("New Bikes");
	static By upcomingBikesLocator = By.xpath("//div[@id='headerNewNavWrap']/nav/div/ul/li[3]/ul/li[5]/span");
	static By selectManufactureLocator = By.className("custom-select");
	static By viewMoreBtnLocator = By.className("zw-cmn-loadMore");
	

	
	public static void findNewBikes() {
		
		try {
			
			// create a test case log in extent report
			logger = extent.createTest("Search for Upcoming Bikes");
			searchUpcomingBikes();

			// create a test case log in extent report
			logger = extent.createTest("Searched for Honda Bikes");
			searchHondaBikes();
			Thread.sleep(2000);
			
			// create a test case log in extent report
			logger = extent.createTest("Bikes under 4 Lakhs");
			searchHondaBikesUnder4Lakh();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void searchUpcomingBikes() {
		try {
			// instantiating actions class
			Actions actions = new Actions(driver);

			// getting web element of new bikes tag
			WebElement newBikes = driver.findElement(newBikesLocator);

			Thread.sleep(2000);
			// using moveToElement method of actions class for mouse hover
			actions.moveToElement(newBikes).perform();

			// clicking on the upcoming bikes
			WebElement upcomingBikes = driver.findElement(upcomingBikesLocator);
			Highlight.highlight(driver, upcomingBikesLocator);		//highlight
			upcomingBikes.click();
			
			logger.log(Status.PASS ,"Search for Upcoming Bikes passed");
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();		
		}
	}
	
	public static void searchHondaBikes() {
		try {
			// selecting Honda brand from the drop-down
			WebElement selectManufacture = driver.findElement(selectManufactureLocator);
			Select select = new Select(selectManufacture);
			Highlight.highlight(driver, selectManufactureLocator);		//highlight
			select.selectByVisibleText("Honda");
			
			logger.log(Status.PASS ,"Search for Honda Bikes passed");
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.log(Status.FAIL , e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void searchHondaBikesUnder4Lakh() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement viewMoreBtn = driver.findElement(viewMoreBtnLocator);
		js.executeScript("arguments[0].scrollIntoView(true)", viewMoreBtn);
		js.executeScript("arguments[0].click()", viewMoreBtn);

		
		try {
			WriteExcelData.writeExcelTopCellBikes();
			int row = 1;
			// get all the Honda bikes
			for (int i = 1; i <= 16; i++) {
				// skip the ad section
				if (i == 7) {
					continue;
				}
				WebElement e = driver.findElement(By.xpath("//ul[@id='modelList']/li[" + i + "]"));
				String s = e.getText();
				String[] sarr = s.split("\n"); // split into array for manipulation
				String temp = sarr[1]; // index1 contains the price value of every bike
				if (temp.contains("Lakh")) {
					temp = temp.replaceAll("\\s", ""); // remove the spaces
					String[] t = temp.split("[.]");
					String c = t[1]; // take only the integer part
					if (Integer.parseInt(c) < 4) {
						System.out.println(s);
						WriteExcelData.writeExcelData(sarr, row, "Bikes");
						row++;
					}
				} else {
					System.out.println(s);
					WriteExcelData.writeExcelData(sarr, row, "Bikes");
					row++;
				}
			}
			logger.log(Status.PASS ,"Honda Bikes fetched successfully");
		}catch (Exception e) {
			// TODO: handle exception
			logger.log(Status.FAIL ,e.getMessage());
		}
	}
}