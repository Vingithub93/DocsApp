package in.docsapp.generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;

/**
 * 
 * @author Vinayak
 *
 */


public class GenericMethods{
	
	
	
	public String globalTimeStamp = TimeStamp();
	
	public String extent_path="./reports/CurrentRunResults/"+"Extentreport_"+globalTimeStamp+"/";
	
	public static ExtentReportUtils extentUtils=new ExtentReportUtils();
	
	//creates path
	public String createPath(String path){
		File file=new File(path);
		try
		{
			System.out.println("Creating path "+path);
			file.mkdir();
		}
		catch(Exception e)
		{	
			System.out.println("Failed to create path "+path);
		}
		return path;
	}
	
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
			extentUtils.logInfo("Selected the " + text + " from the dropdown");
			System.out.println("Selected the " + text + " from the dropdown");
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
		public boolean waitUntilElementISVisible(WebDriver driver, WebElement element) {
			int count=0;
			boolean flag=false;
			while(count<5) 
			{
				try {
					element.isDisplayed();
					flag =true;
					break;
				}
				catch (Throwable e) 
				{
					try 
					{
						Thread.sleep(1000);
						count++;
					} 
					catch (InterruptedException e1) 
					{
						System.out.println("Element is not Displayed = "+element.toString());
					}
				}
			}
			return flag;
		}
		
		public boolean waitUntilElementInvisible(WebDriver driver, WebElement element) {
			int count=0;
			boolean flag=false;
			while(count<5) 
			{
				try {
					element.isDisplayed();
					Thread.sleep(1000);
					count++;
				}
				catch (Throwable e) 
				{
					flag=true;
					break;
				}
			}
			return flag;
		}
		
		public void type(WebDriver driver,WebElement textElement, String data) {
			waitUntilElementISVisible(driver, textElement);
			System.out.println(data);
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
		
		public void customDelay(int timeSeconds)
		{
			try {
				Thread.sleep(timeSeconds*1000);
			}
			catch (Exception e) {
			}
		}
		
		public void moveOldReports(){
			try 
			{
				System.out.println("Moving old reports");
				File destFolder = new File(".\\reports\\Oldreports\\MovedOn"+globalTimeStamp);
				FileUtils.moveToDirectory(new File(".\\reports\\CurrentRunResults\\"),destFolder,true);
				//FileUtils.moveToDirectory(new File(".\\src\\test\\CurrentRunResults\\ScreenShots\\"),destFolder,true);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		public void takeScreenShot(WebDriver driver)
		{
					TakesScreenshot scrShot =((TakesScreenshot)driver);
	                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	                GenericMethods gen =new GenericMethods();
	                File DestFile=new File("./screenshots/"+gen.globalTimeStamp+".jpg");

	                try 
	                {
						FileUtils.copyFile(SrcFile, DestFile);
					} 
	                catch (IOException e) {
						e.printStackTrace();
					}
		}

}
