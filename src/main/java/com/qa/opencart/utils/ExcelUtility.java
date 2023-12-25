package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	private static final String TEST_DATA_SHEET_NAME = "./src/test/resources/testdata/OpencartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {

		System.out.println("Sheet Name is==>" + sheetName);
		Object data[][] = null;

		FileInputStream fis;
		try {
			fis = new FileInputStream(TEST_DATA_SHEET_NAME);

			book = WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
}
