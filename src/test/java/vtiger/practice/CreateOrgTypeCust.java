package vtiger.practice;

import java.io.IOException;
import java.time.Duration;

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

public class CreateOrgTypeCust {

	static WebDriver driver;

	public static void main(String[] args) throws IOException {
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

		String USERNAME = pUtil.readDataFromPropertyFile("username"); // pobj.getProperty("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password"); // obj.getProperty("password");
		String URL = pUtil.readDataFromPropertyFile("url"); // obj.getProperty("url");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		// WebDriverManager.chromedriver().setup();
		// WebDriver driver = new ChromeDriver();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// driver.get(URL);
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println("Invalid Name");
		}
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
		orgname.sendKeys("Energy organisation");
		WebElement rdobtn = driver.findElement(By.xpath("//input[@value='T']"));
		rdobtn.click();
		WebElement indDrop = driver.findElement(By.xpath("//select[@name='industry']"));
		WebElement typeDrop = driver.findElement(By.xpath("//select[@name='accounttype']"));

		Select sc = new Select(indDrop);
		sc.selectByVisibleText("Energy");

		Select sc1 = new Select(typeDrop);
		sc1.selectByVisibleText("Customer");

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
