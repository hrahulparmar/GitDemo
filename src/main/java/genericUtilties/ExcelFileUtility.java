package genericUtilties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/*
 * This class contains generic methods related to excel file
*/
public class ExcelFileUtility implements ICosnstantsUtility {

	/*
	 * This method will read data from excel and return a String
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * return value
	 * throw IOException
	 * 
	*/
	public String readDataFromExcel(String sheetName, int rowNo, int cellNo) throws IOException
	{
		FileInputStream fis = new FileInputStream(ICosnstantsUtility.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		String value = wb.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();
		return value;
	}
	
	/*
	 * This method will write data to excel
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @param value
	 * throw IOException
	*/
	public void writeDataIntoExcel(String sheetName, int rowNo, int cellNo, String value) throws IOException
	{
		FileInputStream fis = new FileInputStream(ICosnstantsUtility.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		wb.createSheet(sheetName).createRow(rowNo).createCell(cellNo).setCellValue(value);
		
		FileOutputStream fos= new FileOutputStream(ICosnstantsUtility.excelFilePath);
		wb.write(fos);
		wb.close();
	}
}
