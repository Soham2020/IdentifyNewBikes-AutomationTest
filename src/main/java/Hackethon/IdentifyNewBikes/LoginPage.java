package Hackethon.IdentifyNewBikes;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public static void loginPage(WebDriver driver) {
		try {
			// navigate to landing page
			driver.navigate().back();
			
			WebElement clickLogin = driver.findElement(By.id("forum_login_title_lg"));
			clickLogin.click();
			
			Thread.sleep(2000);
			
			WebElement clickGoogle = driver.findElement(By.xpath("//div[@class='lgn-sc c-p txt-l pl-30 pr-30 googleSignIn']"));
			clickGoogle.click();
			
			Set<String> windows = driver.getWindowHandles();
			for(String id:windows) {
				driver.switchTo().window(id);
			}
			
			WebElement searchField = driver.findElement(By.xpath("//input[@type='email']"));
			searchField.sendKeys("sd@gm.com");
			
			WebElement clickNext = driver.findElement(By.xpath("//span[text()='Next']"));
			clickNext.click();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
