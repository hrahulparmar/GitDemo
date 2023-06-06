package genericUtilties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * This class consists of generic methods related to property file
 * @author rahulP
*/
public class PropertyFileUtility {

	/*
	 * This method will read data from property  file and return value to caller
	 * @param key
	 * @return value
	 * @throws IOException
	 * 
	*/
	public String readDataFromPropertyFile(String key) throws IOException
	{	
		FileInputStream fis= new FileInputStream(ICosnstantsUtility.propertyFilePath);
		Properties pObj= new Properties();
		pObj.load(fis);
		String value = pObj.getProperty(key);
		return value;
	}
	
}
