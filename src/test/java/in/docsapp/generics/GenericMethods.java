package in.docsapp.generics;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class GenericMethods {
	
	//To read the value from PROPERTIES file
		public static String getProperty(String key)
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
}
