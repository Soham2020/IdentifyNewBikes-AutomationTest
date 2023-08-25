package Hackethon.IdentifyNewBikes.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	public static void screenShot(WebDriver driver) {
		String path = "./test-output/screenshot.png";
		try {
			TakesScreenshot screenshot = (TakesScreenshot)driver;
			File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
			File destFile = new File(path);
			FileUtils.copyFile(srcFile, destFile);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
