package vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {

	public static void main(String[] args) throws IOException {
		
		// step1 open the file in readable format
		FileInputStream fise= new FileInputStream("./src/test/resources/TestData.xlsx");
		
		//step2 create a workbook factory
		Workbook wb = WorkbookFactory.create(fise);
		
		//step3 get control of sheet
		Sheet sh = wb.getSheet("Organization");
		
		//step4 get control of row
		Row rw = sh.getRow(0);
		
		//step4 get control of cell
		Cell ce = rw.getCell(0);
		
		//step5 capture the information inside the cell
		String value = ce.getStringCellValue();
		
		System.out.println(value);
		

	}

}
