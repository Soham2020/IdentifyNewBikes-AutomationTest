package Hackethon.IdentifyNewBikes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelData {
	static File f;
	static FileInputStream fi;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static String path = "E:\\eclipse-jee-2023-06-R-win32-x86_64\\eclipse\\IdentifyNewBikes\\test-output\\OutputBikes.xlsx";
	static FileOutputStream fos;

	public static void writeExcelTopCellBikes() throws Exception {

		f = new File(path);
		fi = new FileInputStream(f);

		workbook = new XSSFWorkbook(fi);

		sheet = workbook.getSheet("Bikes");
		row = sheet.createRow(0);
		row.createCell(0).setCellValue("Bike's Name");
		row.createCell(1).setCellValue("Price");
		row.createCell(2).setCellValue("Launched Date");
		fos = new FileOutputStream(f);
		workbook.write(fos);
	}

	public static void writeExcelTopCellCars() throws Exception {

		f = new File(path);
		fi = new FileInputStream(f);

		workbook = new XSSFWorkbook(fi);

		sheet = workbook.createSheet("Cars");
		row = sheet.createRow(0);
		row.createCell(0).setCellValue("Car's Name");
		fos = new FileOutputStream(f);
		workbook.write(fos);
	}

	public static void writeExcelData(String[] s, int rowNum, String sheetName) throws Exception {

		f = new File(path);
		fi = new FileInputStream(f);

		workbook = new XSSFWorkbook(fi);

		sheet = workbook.getSheet(sheetName);

		row = sheet.createRow(rowNum);

		row.createCell(0).setCellValue(s[0]);
		row.createCell(1).setCellValue(s[1]);
		row.createCell(2).setCellValue(s[2]);

		for (int i = 0; i < s.length; i++) {
			row.createCell(i).setCellValue(s[i]);
		}

		fos = new FileOutputStream(f);
		workbook.write(fos);

	}

	public static void writeExcelData(String s, String sheetName) throws Exception {

		f = new File(path);
		fi = new FileInputStream(f);

		workbook = new XSSFWorkbook(fi);

		sheet = workbook.getSheet(sheetName);

		int rowNum = 1;
		String[] arr = s.split("\n");
		for (int i = 0; i < arr.length; i++) {
			row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(arr[i]);
		}

		fos = new FileOutputStream(f);
		workbook.write(fos);

	}

}
