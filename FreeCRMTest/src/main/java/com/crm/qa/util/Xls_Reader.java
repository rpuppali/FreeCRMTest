package com.crm.qa.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xls_Reader {
	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public String TestData = System.getProperty("user.dir") + "\\TestData\\TestData.xlsx";

	public Xls_Reader() throws IOException {
		fis = new FileInputStream(TestData);
		workbook = new XSSFWorkbook(fis);
	}

	public String GetTestData(String sheetName, String screenName, String fieldName, String testCaseName) throws Exception {
		sheet = workbook.getSheet(sheetName);
		DataFormatter formatter = new DataFormatter();
		int colnum = getColNum(sheetName, testCaseName);
		if (colnum != -1) {
			for (Row row : sheet) {
				if (row.getCell(0) != null && row.getCell(1) != null && row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING) {
					if (row.getCell(0).getRichStringCellValue().getString().trim().equalsIgnoreCase(screenName.trim()) && row.getCell(1).getRichStringCellValue().getString().trim().equalsIgnoreCase(fieldName.trim())) {
						System.out.println(row.getCell(0).getRichStringCellValue().getString().trim() + " & " + row.getCell(1).getRichStringCellValue().getString().trim() + " - Test data Found in rownum --> " + row.getRowNum());
						String srow = formatter.formatCellValue(row.getCell(colnum)).trim();
						System.out.println("Test data is --> " + srow);
						return srow;
					}
				}
			}
		}
		throw new Exception("Data not found in the data sheet.Sheet:" + sheetName + " search criteria-1:" + screenName + " search criteria-2:" + fieldName + " testcase name:" + testCaseName);
	}

	public int getColNum(String sheetName, String colName) throws IOException {

		sheet = workbook.getSheet(sheetName);
		int colNum = -1;

		row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i) != null && row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName)) {
				colNum = i;
				return colNum;
			}
		}
		return colNum;
	}

}
