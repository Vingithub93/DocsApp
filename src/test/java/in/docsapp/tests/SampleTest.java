package in.docsapp.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import in.docsapp.generics.Auto_Const;
import in.docsapp.generics.ExcelLibrary;
import in.docsapp.generics.GenericMethods;
import in.docsapp.workflow.GenericServices;
import io.github.bonigarcia.wdm.ChromeDriverManager;

public class SampleTest  {

	public static WebDriver driver;
	public static void main(String[] args) throws Exception{
		
		
		String s=ExcelLibrary.getSheetName("maxlife");
		System.out.println(s);
		
		
//		ChromeDriverManager.getInstance().setup();
//		driver= new ChromeDriver();
//		driver.get("https://b2btest.docsapp.in/webviews/telemerdashboardreact/?#!login");
//		Thread.sleep(4000);
//		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//		driver.findElement(By.name("txtemail")).sendKeys("dr.priya@docsapp.in");
//		driver.findElement(By.name("txtpassword")).sendKeys("1@3$5^");
//		driver.findElement(By.className("SubmitButton_8baflu")).click();
//		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[4]/div[1]/div/div/div[3]/div[1]/div[3]/div[1]/div/div[7]/div/div[1]/a[1]")).click();
//		GenericServices services=new GenericServices(driver);
//		
//		services.actionOnQuestion(driver, "maxlife_questions", "case 1");
		
		
	}

}
