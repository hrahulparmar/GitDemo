package vtiger.practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgType {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("http://localhost:8888/");
		WebElement userbox = driver.findElement(By.xpath("//input[@name='user_name']"));
		WebElement passbox = driver.findElement(By.xpath("//input[@name='user_password']"));
		WebElement loginbtn = driver.findElement(By.xpath("//input[@id='submitButton']"));
		userbox.sendKeys("admin");
		passbox.sendKeys("admin");
		loginbtn.click();
		
		WebElement orgbtn = driver.findElement(By.linkText("Organizations"));
		orgbtn.click();
		WebElement addorg = driver.findElement(By.xpath("//img[@title='Create Organization...']"));
		addorg.click();
		WebElement orgname = driver.findElement(By.xpath("//input[@name='accountname']"));
		orgname.sendKeys("PR");
		WebElement rdobtn = driver.findElement(By.xpath("//input[@value='T']"));
		rdobtn.click();
		WebElement indDrop = driver.findElement(By.xpath("//select[@name='industry']"));
		
		Select sc = new Select(indDrop);
		sc.selectByVisibleText("Chemicals");
		
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
