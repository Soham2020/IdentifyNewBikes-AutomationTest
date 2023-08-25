package Hackethon.IdentifyNewBikes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class WriteExcelData {
	static File f;
	static FileInputStream fi;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static String path = "./test-output/OutputData.xlsx";
	static FileOutputStream fos;

	public static void writeExcelTopCellBikes() throws Exception {

		f = new File(path);
		if(!f.exists()) {
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(path);
			workbook.write(fos);
		}
		
		fi = new FileInputStream(path);

		workbook = new XSSFWorkbook(fi);
		if(workbook.getSheetIndex("Bikes") == -1) {
			workbook.createSheet("Bikes");
		}
		
		sheet = workbook.getSheet("Bikes");
		if(sheet.getRow(0) == null)
			sheet.createRow(0);
		row = sheet.getRow(0);
		
		row.createCell(0).setCellValue("Bike's Name");
		row.createCell(1).setCellValue("Price");
		row.createCell(2).setCellValue("Launched Date");
		row.createCell(3).setCellValue("Remarks");
		fos = new FileOutputStream(f);
		workbook.write(fos);
	}

	public static void writeExcelTopCellCars() throws Exception {

		f = new File(path);
		
		
		if(!f.exists()) {
			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(path);
			workbook.write(fos);
		}
		
		fi = new FileInputStream(path);

		workbook = new XSSFWorkbook(fi);
		if(workbook.getSheetIndex("Cars") == -1) {
			workbook.createSheet("Cars");
		}
		
		sheet = workbook.getSheet("Cars");
		if(sheet.getRow(0) == null)
			sheet.createRow(0);
		row = sheet.getRow(0);
		
		
//		fi = new FileInputStream(f);
//
//		workbook = new XSSFWorkbook(fi);
//
//		sheet = workbook.createSheet("Cars");
//		row = sheet.createRow(0);
		row.createCell(0).setCellValue("Car's Name");
		fos = new FileOutputStream(f);
		workbook.write(fos);
	}

	public static void writeExcelData(String[] s, int rowNum, String sheetName) throws Exception {

		f = new File(path);
//		if(!f.exists()) {
//			workbook = new XSSFWorkbook();
//			fos = new FileOutputStream(path);
//			workbook.write(fos);
//		}
//		
//		fi = new FileInputStream(path);
//
//		workbook = new XSSFWorkbook(fi);
//		if(workbook.getSheetIndex(sheetName) == -1) {
//			sheet = workbook.createSheet(sheetName);
//		}
//		
//		sheet = workbook.getSheet(sheetName);
//		if(sheet.getRow(0) == null)
//			row = sheet.createRow(rowNum);
//		else
//			row = sheet.getRow(rowNum);
		
		fi = new FileInputStream(f);

		workbook = new XSSFWorkbook(fi);

		sheet = workbook.getSheet(sheetName);
		Flush();

		row = sheet.createRow(rowNum);

		row.createCell(0).setCellValue(s[0]);
		row.createCell(1).setCellValue(s[1]);
		row.createCell(2).setCellValue(s[2]);

		for (int i = 0; i < s.length; i++) {
			row.createCell(i).setCellValue(s[i]);
		}

		fos = new FileOutputStream(f);
		workbook.write(fos);
		fos.close();

	}

	public static void writeExcelData(List<WebElement> arr, String sheetName) throws Exception {

		f = new File(path);
//		if(!f.exists()) {
//			workbook = new XSSFWorkbook();
//			fos = new FileOutputStream(path);
//			workbook.write(fos);
//		}
//		
//		fi = new FileInputStream(path);
//
//		workbook = new XSSFWorkbook(fi);
//		if(workbook.getSheetIndex(sheetName) == -1) {
//			workbook.createSheet(sheetName);
//		}
//		
//		sheet = workbook.getSheet(sheetName);
//		if(sheet.getRow(0) == null)
//			sheet.createRow(0);
//		row = sheet.getRow(0);
		
		fi = new FileInputStream(f);

		workbook = new XSSFWorkbook(fi);

		sheet = workbook.getSheet(sheetName);
		Flush();

		int rowNum = 1;
		for (int i = 0; i < arr.size(); i++) {
			row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(arr.get(i).getText());
		}

		fos = new FileOutputStream(f);
		workbook.write(fos);
		fos.close();

	}
	
	public static void Flush() throws Exception {
		fos = new FileOutputStream(path);
		fos.flush();
	}

}