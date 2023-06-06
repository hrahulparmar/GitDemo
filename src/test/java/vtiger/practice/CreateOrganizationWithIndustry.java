package vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import genericUtilties.ExcelFileUtility;
import genericUtilties.JavaUtility;
import genericUtilties.PropertyFileUtility;
import genericUtilties.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationWithIndustry {

	static WebDriver driver;

	public static void main(String[] args) throws IOException {

		//Random ran = new Random();
		//int random = ran.nextInt();

		// step 1 read all data from resource
		//FileInputStream fisp = new FileInputStream("./src/test/resources/CommonData.properties");
		//Properties pobj = new Properties();
		//pobj.load(fisp);
		
		//create objects for all utilities
		ExcelFileUtility eUtil= new ExcelFileUtility();
		JavaUtility jUtil= new JavaUtility();
		PropertyFileUtility pUtil= new PropertyFileUtility();
		WebDriverUtility wUtil= new WebDriverUtility();
		
		String USERNAME = pUtil.readDataFromPropertyFile("username");   //pobj.getProperty("username");
		String PASSWORD =   pUtil.readDataFromPropertyFile("password");                                            // obj.getProperty("password");
		String URL =        pUtil.readDataFromPropertyFile("url");                                             //obj.getProperty("url");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");

		//FileInputStream fise = new FileInputStream("./src/test/resources/TestData.xlsx");
		//Workbook wb = WorkbookFactory.create(fise);
		//Sheet sh = wb.getSheet("Organization");
		//Row rw = sh.getRow(4);
		//Cell ce = rw.getCell(2);
		
		String ORGNAME = eUtil.readDataFromExcel("Organization", 4, 2);
		String INDUSTRY = eUtil.readDataFromExcel("Organization", 4, 3);

		// step 2 launch browser -runtime polymorphism
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println("Invalid Name");
		}

		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wUtil.maximizeWindow(driver);
        wUtil.waitForPageLoad(driver);
		driver.get(URL);
		WebElement userbox = driver.findElement(By.xpath("//input[@name='user_name']"));
		WebElement passbox = driver.findElement(By.xpath("//input[@name='user_password']"));
		WebElement loginbtn = driver.findElement(By.xpath("//input[@id='submitButton']"));

		userbox.sendKeys(USERNAME);
		passbox.sendKeys(PASSWORD);
		loginbtn.click();

		WebElement orgbtn = driver.findElement(By.linkText("Organizations"));
		orgbtn.click();
		WebElement addorg = driver.findElement(By.xpath("//img[@title='Create Organization...']"));
		addorg.click();
		WebElement orgname = driver.findElement(By.xpath("//input[@name='accountname']"));
		orgname.sendKeys(ORGNAME);
		WebElement rdobtn = driver.findElement(By.xpath("//input[@value='T']"));
		rdobtn.click();
		WebElement indDrop = driver.findElement(By.xpath("//select[@name='industry']"));

		//Select sc = new Select(indDrop);
		//sc.selectByVisibleText(INDUSTRY);
		wUtil.handleDropDown(indDrop, INDUSTRY);
		
		WebElement savebtn = driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]"));
		savebtn.click();
		WebElement validate = driver.findElement(By.xpath("//span[@class='small']"));
		String text = validate.getText();

		if (text.contains("Updated"))
			System.out.println("pass");
		else
			System.out.println("fail");
		driver.close();

	}

}
