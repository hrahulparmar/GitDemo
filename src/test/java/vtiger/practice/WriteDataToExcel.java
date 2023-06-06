package vtiger.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		//step 1 open the file in java readable format
		FileInputStream fise = new FileInputStream("./src/test/resources/TestData.xlsx");
		
		//step 2 create a workbook factory
		Workbook wb = WorkbookFactory.create(fise);
		
		//step 3 get control of sheet
		Sheet sb = wb.getSheet("Organization");
		
		//step 4 get control of row
		Row rw = sb.createRow(6);
		
		//step 5 get control of cell
		Cell ce = rw.createCell(2);
		
		//step 6 set the cell value
		ce.setCellValue("hello");
		
		//step 7 open the file in java write format
		FileOutputStream fos= new FileOutputStream("./src/test/resources/TestData.xlsx");
		
		//step 8 write the data into the file
		wb.write(fos);
		System.out.println("data written");
		wb.close();
		

	}

}
