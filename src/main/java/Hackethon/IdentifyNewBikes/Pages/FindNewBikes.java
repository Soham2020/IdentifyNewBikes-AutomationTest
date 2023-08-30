package Hackethon.IdentifyNewBikes.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import Hackethon.IdentifyNewBikes.Base.DriverSetup;
import Hackethon.IdentifyNewBikes.utils.Highlight;
import Hackethon.IdentifyNewBikes.utils.ScreenShot;
import Hackethon.IdentifyNewBikes.utils.WriteExcelData;

public class FindNewBikes extends DriverSetup {

	By newBikesLocator = By.linkText("New Bikes");
	By upcomingBikesLocator = By.xpath("//div[@id='headerNewNavWrap']/nav/div/ul/li[3]/ul/li[5]/span");
	By selectManufactureLocator = By.className("custom-select");
	By viewMoreBtnLocator = By.className("zw-cmn-loadMore");

	By modelNamePath = By.xpath("//strong[@class='lnk-hvr block of-hid h-height']");
	By priceTextPath = By.xpath("//div[@class='b fnt-15']");
	By launchDatePath = By.xpath("//div[@class='clr-try fnt-14']");

	ScreenShot ss = new ScreenShot();

	public void findNewBikes() {

		try {

			// create a test case log in extent report
			logger = extent.createTest("Search for Upcoming Bikes");
			searchUpcomingBikes();

			// create a test case log in extent report
			logger = extent.createTest("Searched for Honda Bikes");
			searchHondaBikes();
			Thread.sleep(1000);

			// select the <a> tag link of bike's price under 5 lakhs
			/*
			 * WebElement upcomingBikeUnderFive =
			 * driver.findElement(By.xpath("//ul[@class='lnk-lst']/li[5]/a")); String url =
			 * upcomingBikeUnderFive.getAttribute("href"); driver.navigate().to(url);
			 */

			// create a test case log in extent report
			logger = extent.createTest("Bikes under 4 Lakhs");
			searchHondaBikesUnder4Lakh();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void searchUpcomingBikes() throws Exception {
		try {
			// instantiating actions class
			Actions actions = new Actions(driver);

			// getting web element of new bikes tag
			WebElement newBikes = driver.findElement(newBikesLocator);

			Thread.sleep(1000);
			// using moveToElement method of actions class for mouse hover
			actions.moveToElement(newBikes).perform();

			// clicking on the upcoming bikes
			WebElement upcomingBikes = driver.findElement(upcomingBikesLocator);
			Highlight.highlight(driver, upcomingBikesLocator); // highlight
			upcomingBikes.click();

			reportPass("Search for Upcoming Bikes passed");
		} catch (Exception e) {
			// TODO: handle exception
			String imgName = "upcomingBikes";
			ss.screenShot(driver, imgName);
			reportFail("Search for Upcoming Bikes failed", imgName);
		}
	}

	public void searchHondaBikes() throws Exception {
		try {
			// selecting Honda brand from the drop-down
			WebElement selectManufacture = driver.findElement(selectManufactureLocator);
			Select select = new Select(selectManufacture);
			Highlight.highlight(driver, selectManufactureLocator); // highlight
			select.selectByVisibleText("Honda");

			reportPass("Search for Honda Bikes passed");
		} catch (Exception e) {
			// TODO: handle exception
			String imgName = "SearchBikes";
			ss.screenShot(driver, imgName);
			reportFail("Search for Honda Bikes failed", imgName);
		}
	}

	public void searchHondaBikesUnder4Lakh() throws Exception {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement viewMoreBtn = driver.findElement(viewMoreBtnLocator);
		js.executeScript("arguments[0].scrollIntoView(true)", viewMoreBtn);
		js.executeScript("arguments[0].click()", viewMoreBtn);

		try {
			WriteExcelData.writeExcelTopCellBikes();
//			int row = 1;
//			// get all the Honda bikes
//			for (int i = 1; i <= 16; i++) {
//				// skip the ad section
//				if (i == 7) {
//					continue;
//				}
//				WebElement e = driver.findElement(By.xpath("//ul[@id='modelList']/li[" + i + "]"));
//				String s = e.getText();
//				String[] sarr = s.split("\n"); // split into array for manipulation
//				String temp = sarr[1]; // index1 contains the price value of every bike
//				if (temp.contains("Lakh")) {
//					temp = temp.replaceAll("\\s", ""); // remove the spaces
//					String[] t = temp.split("[.]");
//					String c = t[1]; // take only the integer part
//					if (Integer.parseInt(c) < 4) {
//						System.out.println(s);
//						WriteExcelData.writeExcelData(sarr, row, "Bikes");
//						row++;
//					}
//				} else {
//					System.out.println(s);
//					WriteExcelData.writeExcelData(sarr, row, "Bikes");
//					row++;
//				}
//			}
			List<WebElement> modelName = driver.findElements(modelNamePath);
			List<WebElement> priceText = driver.findElements(priceTextPath);
			List<WebElement> launchDate = driver.findElements(launchDatePath);

			List<Float> under4 = new ArrayList<Float>();
			int row = 1;

			for (int i = 0; i < priceText.size(); i++) {
				float cost = Float.parseFloat(
						priceText.get(i).getText().replaceAll("Rs. ", "").replaceAll(" Lakh", "").replaceAll(",", ""));
				String model = modelName.get(i).getText();
				String launch = launchDate.get(i).getText();
				if (cost == 79000) {
					cost = (79000.0f / 100000.0f);
				}
				if (cost < 4) {
					under4.add(cost);
					System.out.println("[Model: " + model + " Cost: " + cost + " Launch: " + launch + "]");
					String[] arr = new String[3];
					arr[0] = model;
					arr[1] = priceText.get(i).getText();
					arr[2] = launch;
					WriteExcelData.writeExcelData(arr, row++, "Bikes");
				}
			}
			reportPass("Honda Bikes fetched successfully");
		} catch (Exception e) {
			// TODO: handle exception
			String imgName = "BikesFailed";
			ss.screenShot(driver, imgName);
			reportFail("Honda Bikes fetched failed", imgName);
		}
	}
}