package in.biencaps.erp.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

public class ExcelReader {
	public static String getUserDataForUserId(String userId, String header, FileInputStream filePath) {
		try {
			FileInputStream fis = filePath;
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet
			int rowCount = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < rowCount; i++) { // Start from index 1 to skip header row
				Row row = sheet.getRow(i);
				Cell userIdCell = row.getCell(0); // Assuming user ID is in the first column
				if (header.equalsIgnoreCase("password")) {
					Cell passwordCell = row.getCell(1); // Assuming password is in the second column
					if (userIdCell != null && userIdCell.getStringCellValue().equals(userId)) {
						return passwordCell.getStringCellValue();
					}
				} else if (header.equalsIgnoreCase("name")) {
					Cell passwordCell = row.getCell(2); // Assuming password is in the second column
					if (userIdCell != null && userIdCell.getStringCellValue().equals(userId)) {
						return passwordCell.getStringCellValue();
					}
				} else if (header.equalsIgnoreCase("department")) {
					Cell passwordCell = row.getCell(3); // Assuming password is in the second column
					if (userIdCell != null && userIdCell.getStringCellValue().equals(userId)) {
						return passwordCell.getStringCellValue();
					}
				} else {
					Cell passwordCell = row.getCell(4); // Assuming password is in the second column
					if (userIdCell != null && userIdCell.getStringCellValue().equals(userId)) {
						return passwordCell.getStringCellValue();
					}
				}
			}
			return null; // User ID not found
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
