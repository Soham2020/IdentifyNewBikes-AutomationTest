package Hackethon.IdentifyNewBikes;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindNewBikes {
	public static void findNewBikes(WebDriver driver) {
		try {
			// instantiating actions class
			Actions actions = new Actions(driver);

			// getting web element of new bikes tag
			WebElement newBikes = driver.findElement(By.linkText("New Bikes"));

			Thread.sleep(2000);
			// using moveToElement method of actions class for mouse hover
			actions.moveToElement(newBikes).perform();
			
			//clicking on the upcoming bikes
			WebElement upcomingBikes = driver.findElement(By.xpath("//div[@id='headerNewNavWrap']/nav/div/ul/li[3]/ul/li[5]/span"));
			upcomingBikes.click();
			
			// selecting Honda brand from the drop-down
			WebElement selectManufacture = driver.findElement(By.className("custom-select"));
			Select select = new Select(selectManufacture);
			select.selectByVisibleText("Honda");
			
			Thread.sleep(2000);
			
			// select the <a> tag link of bike's price under 5 lakhs
			/* WebElement upcomingBikeUnderFive = driver.findElement(By.xpath("//ul[@class='lnk-lst']/li[5]/a"));
			String url = upcomingBikeUnderFive.getAttribute("href");
			driver.navigate().to(url);
			*/
			
			// get all the Honda bikes
			List<WebElement> hondaBikes1 = driver.findElements(By.id("modelList"));
			for(WebElement w:hondaBikes1) {
				System.out.println(w.getText());
			}
			
//			 Click on view more button but not working, giving element is not clickable
			WebElement viewMoreBtn = driver.findElement(By.xpath("//span[@class='zw-cmn-loadMore']"));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true)", viewMoreBtn);
			js.executeScript("arguments[0].click()", viewMoreBtn);
			
			// get all the Honda bikes
			List<WebElement> hondaBikes1 = driver.findElements(By.id("modelList"));
			for (WebElement w : hondaBikes1) {
				System.out.println(w.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
