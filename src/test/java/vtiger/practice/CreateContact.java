package vtiger.practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContact
{

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("http://localhost:8888/");
		WebElement userbox = driver.findElement(By.xpath("//input[@name='user_name']"));
		WebElement passbox= driver.findElement(By.xpath("//input[@name='user_password']"));
		WebElement loginbtn= driver.findElement(By.xpath("//input[@id='submitButton']"));
		userbox.sendKeys("admin");
		passbox.sendKeys("admin");
		loginbtn.click();
		
		WebElement contactlk= driver.findElement(By.xpath("//a[normalize-space()='Contacts']"));
		contactlk.click();
		WebElement addContact= driver.findElement(By.xpath("//img[@title='Create Contact...']"));
		addContact.click();
		WebElement lastname= driver.findElement(By.xpath("//input[@name='lastname']"));
		lastname.sendKeys("parmar");
		WebElement radiobtn= driver.findElement(By.xpath("//input[@value='U']"));
		radiobtn.click();
		WebElement savebtn= driver.findElement(By.xpath("//input[@class='crmbutton small save']"));
		savebtn.click();
		WebElement validateText= driver.findElement(By.xpath("//span[@class='small']"));
		String text = validateText.getText();
		if(text.contains("Updated"))
			System.out.println("pass");
		else
			System.out.println("fail");
		driver.close();
	}

}
