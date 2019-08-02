package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {

	public static String getTimeStamp() {
		Date myDate = new Date();
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(myDate);
		return timeStamp;
	}
	
	public static XSSFWorkbook openWorkbook(String fileName) {
		try {
			File file = new File("target/ExcelFiles/" + fileName + ".xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			System.out.println("File opened successfully");
			return workbook;
		} catch (Exception e) {
			System.out.println("Something went wrong while opening the file - "+ fileName);
			e.printStackTrace();
			return null;
		}
	}
	
	public static XSSFSheet getSheetFromBook(String fileName, String sheetName) {
		XSSFWorkbook workbook = openWorkbook(fileName);
		return workbook.getSheet(sheetName);
	}
	
	public static void saveAndCloseWorkbook(String fileName, XSSFWorkbook workbook) {
		try {
			File file = new File("target/ExcelFiles/" + fileName + ".xlsx");
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			System.out.println("File saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createWorkbook(String fileName) {
		try {
			File file = new File("target/ExcelFiles/" + fileName + ".xlsx");
			FileOutputStream fos = new FileOutputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook();
			workbook.write(fos);
			fos.close();
			System.out.println("File created successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
