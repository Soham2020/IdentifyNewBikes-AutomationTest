package Hackethon.IdentifyNewBikes.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Highlight {
	public static void highlight(WebDriver driver, By locator) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		try {
			WebElement element = driver.findElement(locator);
			js.executeScript("arguments[0].style.border='2px solid red', arguments[0].style.background='yellow'", element);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
