package genericUtilties;

import java.util.Date;
import java.util.Random;

/*
 * This class consists of generic methods related to java
 * @author rahulP
*/
public class JavaUtility {

	/*
	 * this method return random number
	 * @return
	 * 
	*/
	public int getRandomNumber()
	{
		Random r= new Random();
		return r.nextInt(1000);
	}
	
	/*
	 * This method will return system date
	 * @return
	*/
	public String getSystemDate()
	{
		Date d= new Date();
		return d.toString();
	}

	/*
	 * this method will return date in a custom format
	 * @return
	*/
	public String getDateFormat()
	{
		Date d= new Date();
		String[] dArr = d.toString().split(" ");
		String date= dArr[2];
		String month= dArr[1];
		String year= dArr[5];
		String time= dArr[3].replace(':', '-');
		return date+" "+month+" "+year+" "+time;
	}
}
