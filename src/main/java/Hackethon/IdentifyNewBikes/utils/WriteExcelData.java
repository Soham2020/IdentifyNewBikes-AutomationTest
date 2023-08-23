package Hackethon.IdentifyNewBikes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelData {
	
	public static void writeExcelData(String s, int rowNum, int colNum, String sheetName) throws Exception {
		String path = "C:\\Users\\2282058\\Downloads\\eclipse-java-2023-03-R-win32-x86_64 (1)\\eclipse\\IdentifyNewBikes\\test-output\\OutputData.xlsx";

		File f = new File(path);
		FileInputStream fi = new FileInputStream(f);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fi);
		
		XSSFSheet sheet;
		// check weather sheet is existing or not
		if(workbook.getSheetIndex(sheetName) == -1) {
			sheet = workbook.createSheet(sheetName);
		}else {
			sheet = workbook.getSheet(sheetName);
		}
		
		XSSFRow row;
		// check weather row exists or not
		if(sheet.getRow(rowNum) == null) {
			row = sheet.createRow(rowNum);
		}
		else {
			row = sheet.getRow(rowNum);
		}
		
		
		
		row.createCell(0).setCellValue("Bike Name");
		
	}

}
