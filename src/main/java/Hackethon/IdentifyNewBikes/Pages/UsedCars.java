package Hackethon.IdentifyNewBikes.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Hackethon.IdentifyNewBikes.Base.DriverSetup;
import Hackethon.IdentifyNewBikes.utils.Highlight;
import Hackethon.IdentifyNewBikes.utils.ScreenShot;
import Hackethon.IdentifyNewBikes.utils.WriteExcelData;

public class UsedCars extends DriverSetup {

	By usedCarLocator = By.linkText("Used Cars");
	By getChennaiLocator = By.xpath("//span[text()='Chennai']");
//	By scrollModelLocator = By.xpath("//div[@class='zm-cmn-colorBlack ml-30 mob-nonclick div-h3 mt-20 mb-10']");
	By popularCarsLocator = By.xpath("//div[@class='zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10']");
	By modelListLocator = By.tagName("li");
	By zigWheelsPath = By.xpath("//a[@class = 'zw i-b mt-10 pt-2']");

	ScreenShot ss = new ScreenShot();

	public void UsedCarsChennai() throws Exception {
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

			reportPass("Used Cars in Chennai is selected");
		} catch (Exception e) {
			// TODO: handle exception
			String imgName = "UsedCarsChennai";
			ss.screenShot(driver, imgName);
			reportFail("Used Cars in Chennai is not selected", imgName);
		}
	}

	public void PopularModels() throws Exception {
		try {
			// scroll down is working
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement scrollModel = driver.findElement(popularCarsLocator);
//			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView(true)", scrollModel);

			WriteExcelData.writeExcelTopCellCars();

			// get all the popular cars
			List<WebElement> l = driver.findElements(modelListLocator);
			List<String> model = new ArrayList<String>();
			for(int i=0;i<l.size();i++) {
				System.out.println(l.get(i).getText());
				model.add(l.get(i).getText());
			}
			WriteExcelData.writeExcelData(l, "Cars");

			reportPass("Popular Models fetched");
		} catch (Exception e) {
			// TODO: handle exception
			String imgName = "PopularModels";
			ss.screenShot(driver, imgName);
			reportFail("Popular Models not fetched", imgName);
		}
	}

	public void usedCars() throws Exception {
		try {
			// Get back to main page
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement zigWheels = driver.findElement(zigWheelsPath);
			js.executeScript("arguments[0].click()", zigWheels);
			Highlight.highlight(driver, zigWheelsPath); // highlight

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