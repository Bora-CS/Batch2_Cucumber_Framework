package Excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javafaker.Faker;

import groovy.time.BaseDuration.From;

public class Excel {

	public static void main(String[] args) {

		String fileName = "Name";

		try {
			File file = new File("target/ExcelFiles/" + fileName + ".xlsx");
			FileOutputStream fos = new FileOutputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("SheetN1");

			Faker faker = new Faker();
			int id = 0;

			for (int i = 0; i < 21; i++) {
				XSSFRow row = sheet.createRow(i);
				String firstName = faker.name().firstName();
				String lastName = faker.name().lastName();
				String phoneNumber = faker.phoneNumber().cellPhone();
				String email = firstName + lastName + "@gmail.com";
				for (int j = 0; j < 5; j++) {
					XSSFCell cell = row.createCell(j);
					if (i == 0 && j == 0) {
						cell.setCellValue("ID");
					} else if (i == 0 && j == 1) {
						cell.setCellValue("FIRST NAME");
					} else if (i == 0 && j == 2) {
						cell.setCellValue("LAST NAME");
					} else if (i == 0 && j == 3) {
						cell.setCellValue("EMAIL ADDRESS");
					} else if (i == 0 && j == 4) {
						cell.setCellValue("PHONE NUMBER");
					} else if ((!(i == 0)) && j == 0) {
						cell.setCellValue(id);
					} else if ((!(i == 0)) && j == 1) {
						cell.setCellValue(firstName);
					} else if ((!(i == 0)) && j == 2) {
						cell.setCellValue(lastName);
					} else if ((!(i == 0)) && j == 3) {
						cell.setCellValue(firstName + lastName + "@gmail.com");
					} else if ((!(i == 0)) && j == 4) {
						cell.setCellValue(phoneNumber);
					}

				}
				id++;
			}
			workbook.write(fos);
			fos.close();
			System.out.println("File created successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
