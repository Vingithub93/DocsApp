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

/**
 * 
 * @author Vinayak
 *
 */
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
		public static void selectbyVisibletext(WebElement element, String text)			
		{			
			Select sel = new Select(element);		
			sel.selectByVisibleText(text);
		}
		
		public static void selectbyValue(WebElement element, String value)			
		{			
			Select sel = new Select(element);		
			sel.selectByValue(value);
		}
		
		public String TimeStamp() {
	    	Date date = new Date();
		    SimpleDateFormat ft = new SimpleDateFormat ("_dd-MMM-yyyy_HH-mm-ss");
		    return ft.format(date.getTime());
		}
		
		public void waitforElement() {
			
		}
		
		/**
		 * 
		 * @param driver
		 * @param element
		 * 
		 * wait for the expected element to appear in GUI , wait maximunm 40 sec

		 */
		public void waitUntilElementISVisible(WebDriver driver, WebElement element) {
			int count=0;
			while(count<40) {
				try {
					element.isDisplayed();
					break;
				}catch (Throwable e) {
					try {
						Thread.sleep(1000);
						count++;
					} catch (InterruptedException e1) {
						System.out.println("Element is not Displayed = "+element.toString());
					}
				}
			}
		}
		
		public void type(WebDriver driver,WebElement textElement, String data) {
			waitUntilElementISVisible(driver, textElement);
			textElement.sendKeys(data);
		}
		
		public void click(WebDriver driver,WebElement clickElement) {
			waitUntilElementISVisible(driver, clickElement);
			clickElement.click();
		}
			
		public void clearTextField(WebDriver driver,WebElement clickElement) {
			waitUntilElementISVisible(driver, clickElement);
			clickElement.clear();
		}
		
}
