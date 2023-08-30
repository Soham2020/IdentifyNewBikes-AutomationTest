package Hackethon.IdentifyNewBikes.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	public void screenShot(WebDriver driver,String imgName) {
		String path = "C:\\Users\\2282058\\Downloads\\eclipse-java-2023-03-R-win32-x86_64 (1)\\eclipse\\IdentifyNewBikes\\screenshots\\"+imgName+".png";
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(path);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
