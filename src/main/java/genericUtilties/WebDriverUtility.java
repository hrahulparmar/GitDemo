package genericUtilties;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class WebDriverUtility {

	/*
	 * this method will maximize page
	 * @param WebDriver
	*/
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	/*
	 * this method will minimize page
	 * @param WebDriver
	*/
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();;
	}
	/*
	 * this method will start implicit wait
	 * @param WebDriver
	*/
	
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	/*
	 * this method will start explicit wait
	 * @param WebDriver
	 * @param element
	*/
	public void waitForElement(WebDriver driver, WebElement element)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/*
	 * this method will handle drop down by index
	 * @param WebDriver
	 * @param element
	*/
	public void handleDropDown(WebElement element, int index)
	{
		Select sel= new Select(element);
		sel.selectByIndex(index);
	}
	/*
	 * this method will handle drop down by value
	 * @param WebDriver
	 * @param element
	*/
	public void handleDropDown(WebElement element, String value)
	{
		Select sel= new Select(element);
		sel.selectByValue(value);
	}
	/*
	 * this method will handle drop down by visible text
	 * @param WebDriver
	 * @param element
	*/
	public void handleDropDown(String visibleText, WebElement element)
	{
		Select sel= new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	/*
	 * this method will perform mouse hover action
	 * @param WebDriver
	 * @param element
	*/
	
	public void mouseHoverAction(WebDriver driver, WebElement element)
	{
		Actions act= new Actions(driver);
		act.moveToElement(element).perform();
	}
	/*
	 * this method will perform mouse right click
	 * @param WebDriver
	*/
	public void rightClickAction(WebDriver driver)
	{
		Actions act= new Actions(driver);
		act.contextClick();
	}
	/*
	 * this method will perform right click on a element
	 * @param WebDriver
	 * @param element
	*/
	public void rightClickAction(WebDriver driver, WebElement element)
	{
		Actions act= new Actions(driver);
		act.contextClick(element).perform();
	}

	/*
	 * this method will drag and drop from src element to target element offsets
	 * @param driver
	 * @param srcElement
	 * @param trgElement
	*/
	public void dragAndDropAction(WebDriver driver, WebElement srcElement, WebElement trgElement)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(srcElement, trgElement);
	}

	/*
	 * This method will drag and drop to specific axis
	 * @param driver
	 * @param src
	 * @param x
	 * @param y
	*/
	public void dragAndDropAction(WebDriver driver, WebElement src, int x, int y)
	{
		Actions act = new Actions(driver);
		act.dragAndDropBy(src, x, y).perform();
	}

	/*
	 * This method will handle frame by index
	 * @param driver
	 * @param index
	*/
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	/*
	 * This method will handle frame by name or id
	 * @param driver
	 * @param nameOrId
	*/
	public void switchToFrame(WebDriver driver, String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	/*
	 * This method will handle frame by web element
	 * @param driver
	 * @param element
	*/
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}

	/*
	 * This method will switch control from child frame to immediate parent
	 * @param driver
	*/
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	/*
	 * This method will switch control from child frame to default frame
	 * @param driver
	*/
	public void switchToDefaultFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}

	/*
	 * This method will accept the alert pop up
	 * @param driver
	*/
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/*
	 * This method will accept the dismiss pop up
	 * @param driver
	*/
	public void dismissAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	/*
	 * This method will get text from pop up
	 * @param driver
	*/
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}

	/*
	 * This method will take screen shot and return the absolute path
	 * @param driver
	 * @param screenshotname
	 * @return
	 * @throws IOException
	 * 
	*/
	public String takeScreenShot(WebDriver driver, String name) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot) driver;
		File src= ts.getScreenshotAs(OutputType.FILE);
		File trg= new File("./screenshots"+name+".png");
                                          //screenshot.png		
		Files.copy(src, trg);
		
		return trg.getAbsolutePath();//-- used for Extent reports
		//D://AutomationFramework.RP/screenshots/screenshotname.png
	}

	/*
	 * this method will scroll the screen
	 * @param driver
	*/
	public void scrollAction(WebDriver driver)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("windows.scrollBy(0,500);", null);
	}

	/*
	 * this method will switch the window based on title
	 * @param driver
	 * @param partialWindowTitle
	*/
	public void switchToWindow(WebDriver driver,String partialWindowTitle)
	{
		//step1 capture all the window ids
		Set<String> winIds = driver.getWindowHandles();
	    //step2 navigate to each window
		for(String winId:winIds)
		{
			//step3 capture current window title
			String crtTitle= driver.switchTo().window(winId).getTitle();
			
			//step4 compare the title
			if(crtTitle.contains(partialWindowTitle))
			{
				break;
			}
		}
	}
}
