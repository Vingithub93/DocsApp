package in.docsapp.generics;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class GenericMethods {
	
	//To read the value from PROPERTIES file
		public static String getConfigProperty(String key)
		{
			String value="";
			try {
				Properties properties=new Properties();
				properties.load(new FileInputStream(Auto_Const.CONFIG_PATH));
				value=properties.getProperty(key);
			}
			catch (Exception e) {
				
			}
			return value;
		}
		
		//To read the value from PROPERTIES file
			public static String getProperty(String key, String path)
			{
				String value="";
				try {
					Properties properties=new Properties();
					properties.load(new FileInputStream(path));
					value=properties.getProperty(key);
				}
				catch (Exception e) {
					System.out.println("Unable to fetch data");
				}
				return value;
			}
		
		//Select class methods
		public void selectbyVisibletext(WebElement element, String text)			
		{			
			Select sel = new Select(element);		
			sel.selectByVisibleText(text);
		}
		
		public void selectbyValue(WebElement element, String value)			
		{			
			Select sel = new Select(element);		
			sel.selectByValue(value);
		}
		
		public String TimeStamp() {
	    	Date date = new Date();
		    SimpleDateFormat ft = new SimpleDateFormat ("_dd-MMM-yyyy_HH-mm-ss");
		    return ft.format(date.getTime());
		}
		
		//finding dyanmic elements
		public static void actionOnQuestion(WebDriver driver, String sheetName)
		{
			int rowCount=ExcelLibrary.getLastRowCount(sheetName);
			System.out.println(rowCount);
			for(int i=1; i<=rowCount; i++)
			{
				String question=ExcelLibrary.getCellValue(sheetName, i, 0);
				
				String option=ExcelLibrary.getCellValue(sheetName, i, 1);
				
				String answer=ExcelLibrary.getCellValue(sheetName, i, 2);
				
				
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'"+question+"')]")));
				if(option.equalsIgnoreCase("yes"))
				{
					Wait.waitForElementClickable(driver, driver.findElement(By.xpath("//*[contains(text(),'"+question+"')]/../..//span[contains(text(),'Yes')]")));
					driver.findElement(By.xpath("//*[contains(text(),'"+question+"')]/../..//span[contains(text(),'Yes')]")).click();
					try {
						List<WebElement> ele = driver.findElements(By.xpath("//*[contains(text(),'"+question+"')]/../..//div[last()]//input"));
						for(WebElement we:ele)
						{
							we.clear();
							we.sendKeys(answer);
						}
					}
					catch (Exception e) {
						System.out.println("No input text Field for question "+question);
					}
				}
				else if (option.equalsIgnoreCase("no")) {
					driver.findElement(By.xpath("//*[contains(text(),'"+question+"')]/../..//span[contains(text(),'No')]")).click();
				}
					
				
			}
		}
}
