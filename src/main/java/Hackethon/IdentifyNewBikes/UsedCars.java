package Hackethon.IdentifyNewBikes;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class UsedCars {
	public static void usedCars(WebDriver driver) {
		try {
			// Get back to main page
			driver.navigate().back();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.navigate().back();
			
			// instantiating actions class
			Actions actions = new Actions(driver);
			
			WebElement usedCar = driver.findElement(By.linkText("Used Cars"));
			Thread.sleep(2000);
			actions.moveToElement(usedCar).perform();
			
			WebElement getChennai = driver.findElement(By.xpath("//span[text()='Chennai']"));
			getChennai.click();
			
			// scroll down is working
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement scrollModel = driver.findElement(By.className("gsc_thin_scroll"));
			js.executeScript("arguments[0].scrollIntoView(true)", scrollModel);
			Thread.sleep(5000);
			
			// get all the popular cars
			List<WebElement> l = driver.findElements(By.xpath("//div[@class='gsc_thin_scroll']/ul[1]"));
			for(WebElement w:l) {
				System.out.println(w.getText());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
