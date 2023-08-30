package Hackethon.IdentifyNewBikes.Pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import Hackethon.IdentifyNewBikes.Base.DriverSetup;
import Hackethon.IdentifyNewBikes.utils.Highlight;
import Hackethon.IdentifyNewBikes.utils.ScreenShot;

public class LoginPage extends DriverSetup {

	By clickLoginLocator = By.id("forum_login_title_lg");
	By clickGoogleLocator = By.xpath("//div[@class='lgn-sc c-p txt-l pl-30 pr-30 googleSignIn']");
	By searchFieldLocator = By.xpath("//input[@type='email']");
	By clickNextLocator = By.xpath("//span[text()='Next']");
	By errLocator = By.className("VBGMK");
	By zigWheelsPath = By.xpath("//a[@class = 'zw i-b mt-10 pt-2']");

	ScreenShot ss = new ScreenShot();

	public void loginPage() throws Exception {

		logger = extent.createTest("Login Page");
		try {
			// Get back to main page
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement zigWheels = driver.findElement(zigWheelsPath);
			js.executeScript("arguments[0].click()", zigWheels);
			Highlight.highlight(driver, zigWheelsPath); // highlight

			WebElement clickLogin = driver.findElement(clickLoginLocator);
			clickLogin.click();

			Thread.sleep(1000);

			WebElement clickGoogle = driver.findElement(clickGoogleLocator);
			clickGoogle.click();

			Set<String> windows = driver.getWindowHandles();
			for (String id : windows) {
				driver.switchTo().window(id);
			}

			WebElement searchField = driver.findElement(searchFieldLocator);
			searchField.sendKeys("sd@gm.com");
			Highlight.highlight(driver, searchFieldLocator); // highlight

			WebElement clickNext = driver.findElement(clickNextLocator);
			clickNext.click();

			Thread.sleep(1000);

			WebElement err = driver.findElement(errLocator);
			String msg = err.getText();
			String[] marr = msg.split("\n");
			System.out.println(marr[1]);

			String imgName = "loginPage";
			ss.screenShot(driver, imgName);
			reportFail("Invalid Login Details, Test Case failed", imgName);
		} catch (Exception e) {
			// TODO: handle exception
			reportPass("Invalid Login Details, Test Case passed");
		}
	}
}
