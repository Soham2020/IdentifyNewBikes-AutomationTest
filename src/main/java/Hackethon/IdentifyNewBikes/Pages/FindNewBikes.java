package Hackethon.IdentifyNewBikes.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FindNewBikes {
	public static void findNewBikes(WebDriver driver) {

		By newBikesLocator = By.linkText("New Bikes");
		By upcomingBikesLocator = By.xpath("//div[@id='headerNewNavWrap']/nav/div/ul/li[3]/ul/li[5]/span");
		By selectManufactureLocator = By.className("custom-select");
		By viewMoreBtnLocator = By.className("zw-cmn-loadMore");

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// instantiating actions class
			Actions actions = new Actions(driver);

			// getting web element of new bikes tag
			WebElement newBikes = driver.findElement(newBikesLocator);

			Thread.sleep(2000);
			// using moveToElement method of actions class for mouse hover
			actions.moveToElement(newBikes).perform();

			// clicking on the upcoming bikes
			WebElement upcomingBikes = driver.findElement(upcomingBikesLocator);
			upcomingBikes.click();

			// selecting Honda brand from the drop-down
			WebElement selectManufacture = driver.findElement(selectManufactureLocator);
			Select select = new Select(selectManufacture);
			select.selectByVisibleText("Honda");

			Thread.sleep(2000);

			// select the <a> tag link of bike's price under 5 lakhs
			/*
			 * WebElement upcomingBikeUnderFive =
			 * driver.findElement(By.xpath("//ul[@class='lnk-lst']/li[5]/a")); String url =
			 * upcomingBikeUnderFive.getAttribute("href"); driver.navigate().to(url);
			 */

			WebElement viewMoreBtn = driver.findElement(viewMoreBtnLocator);
			js.executeScript("arguments[0].scrollIntoView(true)", viewMoreBtn);
			js.executeScript("arguments[0].click()", viewMoreBtn);
			Thread.sleep(3000);

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
					}
				} else {
					System.out.println(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}