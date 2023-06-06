package vtiger.practice;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtilties.ExcelFileUtility;
import genericUtilties.JavaUtility;
import genericUtilties.PropertyFileUtility;
import genericUtilties.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EndToEnd {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		// WebDriverManager.chromedriver().setup();
		// WebDriver driver = new ChromeDriver();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// driver.get("http://localhost:8888/");
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

		String USERNAME = pUtil.readDataFromPropertyFile("username"); // pobj.getProperty("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password"); // obj.getProperty("password");
		String URL = pUtil.readDataFromPropertyFile("url"); // obj.getProperty("url");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String ORGNAME = eUtil.readDataFromExcel("Organization", 4, 2);
		String INDUSTRY = eUtil.readDataFromExcel("Organization", 4, 3);

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

		WebElement contactlk = driver.findElement(By.xpath("//a[normalize-space()='Contacts']"));
		contactlk.click();
		WebElement addContact = driver.findElement(By.xpath("//img[@title='Create Contact...']"));
		addContact.click();
		WebElement lastname = driver.findElement(By.xpath("//input[@name='lastname']"));
		lastname.sendKeys("rahul");
		WebElement addOrg = driver.findElement(By.xpath("(//img[@title='Select'])[1]"));
		String parentWin = driver.getWindowHandle();
		addOrg.click();
		Set<String> wins = driver.getWindowHandles();

		for (String win : wins) {
			driver.switchTo().window(win);
			String CURL = driver.getCurrentUrl();
			if (CURL.contains("http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype")) {
				WebElement org = driver.findElement(By.xpath("(//a[.='RP'])[1]"));
				org.click();
				break;
			}
		}
		driver.switchTo().window(parentWin);

		WebElement radiobtn = driver.findElement(By.xpath("//input[@value='U']"));
		radiobtn.click();
		WebElement savebtn = driver.findElement(By.xpath("//input[@class='crmbutton small save']"));
		savebtn.click();
		WebElement validateText = driver.findElement(By.xpath("//span[@class='small']"));
		String text = validateText.getText();
		if (text.contains("Updated")) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
		WebElement logImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(logImg).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(500);
		driver.close();
	}

}
