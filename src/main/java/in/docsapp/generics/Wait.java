package in.docsapp.generics;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * 
 * @author Deepak
 * 
 * contains wait generic methods
 *
 */
public class Wait {
	
	public static WebDriverWait wait;
	
	//Wait for Tilte
	public static void waitForTitle(WebDriver driver, String title)
	{
		wait=new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains(title));	
	}
	
	//Wait for URL
	public static void waitForURL(WebDriver driver, String url)
	{
		wait=new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.urlToBe(url));	
	}
		
	//Wait for Element to be clickable
	public static void waitForElementClickable(WebDriver driver, WebElement element)
	{
		wait=new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(element));	
	}
	
	//Wait for Visibility of Element
	public static void waitForElementVisibility(WebDriver driver, WebElement element)
	{
		wait=new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(element));	
	}
	
	//Wait for Text to be present in element
	public static void waitForElementText(WebDriver driver, WebElement element, String text)
	{
		wait=new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));	
	}
}
