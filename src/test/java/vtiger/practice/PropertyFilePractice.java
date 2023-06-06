package vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFilePractice {

	public static void main(String[] args) throws IOException {
		
		//step 1 open the file in java readable format
		FileInputStream fisp= new FileInputStream("./src/test/resources/CommonData.properties");
		
		//step 2 create object of properties class from java.util
		Properties pobj= new Properties();
		
		//step 3 load the file into properties
		pobj.load(fisp);
		
		//step 4 give the key and read the value
		String browser = pobj.getProperty("browser");

		System.out.println(browser);
	}

}
