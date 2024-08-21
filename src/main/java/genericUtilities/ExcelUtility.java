package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	private Workbook wb;
	Sheet sheet;
	
	public void excelInit(String excelPath, String sheetname) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		
		sheet = wb.getSheet(sheetname);
	}
	
	public Map<String, String> readFromExcel(String expectedTest) {
		DataFormatter df = new DataFormatter();
		Map<String, String> map = new HashMap<>();
		int requiredRow = 0;
		for(int i = 0; i <= sheet.getLastRowNum(); i++) {
			requiredRow = i;
			if(sheet.getRow(i).getCell(1).getStringCellValue().equalsIgnoreCase(expectedTest)) 
				break;
		}
		
		for(int i = requiredRow; i <= sheet.getLastRowNum(); i++) {
			String key = df.formatCellValue(sheet.getRow(i).getCell(2));
			String value = df.formatCellValue(sheet.getRow(i).getCell(3));
			if(key.equals("####"))
				break;
			map.put(key, value);
		}
		return map;
	}
	
	public void updateStatus(String expectedTest, String status) {
		for(int i = 0; i <= sheet.getLastRowNum(); i++) {
			if(sheet.getRow(i).getCell(1).getStringCellValue().equalsIgnoreCase(expectedTest)) {
				sheet.getRow(i).createCell(4).setCellValue(status);
				break;
			}
		}
	}
	
	public void saveExcel(String excelPath) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
