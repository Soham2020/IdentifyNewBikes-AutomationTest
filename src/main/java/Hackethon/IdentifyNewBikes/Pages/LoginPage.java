package Hackethon.IdentifyNewBikes.Pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public static void loginPage(WebDriver driver) {
		By clickLoginLocator = By.id("forum_login_title_lg");
		By clickGoogleLocator = By.xpath("//div[@class='lgn-sc c-p txt-l pl-30 pr-30 googleSignIn']");
		By searchFieldLocator = By.xpath("//input[@type='email']");
		By clickNextLocator = By.xpath("//span[text()='Next']");
		By errLocator = By.className("VBGMK");
		
		try {
			// navigate to landing page
			driver.navigate().back();
			
			WebElement clickLogin = driver.findElement(clickLoginLocator);
			clickLogin.click();
			
			Thread.sleep(2000);
			
			WebElement clickGoogle = driver.findElement(clickGoogleLocator);
			clickGoogle.click();
			
			Set<String> windows = driver.getWindowHandles();
			for(String id:windows) {
				driver.switchTo().window(id);
			}
			
			WebElement searchField = driver.findElement(searchFieldLocator);
			searchField.sendKeys("sd@gm.com");
			
			WebElement clickNext = driver.findElement(clickNextLocator);
			clickNext.click();
			
			Thread.sleep(2000);
			
			WebElement err = driver.findElement(errLocator);
			String msg = err.getText();
			String[] marr = msg.split("\n");
			System.out.println(marr[1]);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
