package in.docsapp.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import in.docsapp.generics.Auto_Const;
import in.docsapp.generics.BaseTest;
import in.docsapp.generics.ExcelLibrary;
import in.docsapp.generics.GenericMethods;
import in.docsapp.pages.OpsDashboard;
import in.docsapp.workflow.GenericServices;
import in.docsapp.workflow.ServiceLib;
import io.github.bonigarcia.wdm.ChromeDriverManager;

/**
 * 
 * @author Vinyak
 *
 */
public class SampleTest extends BaseTest {
	
	@Test
	public void main() throws Exception{
		
		System.out.println(ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), 1, "Application_ID"));
		/*
		driver.findElement(By.name("txtemail")).sendKeys("ops@docsapp.in");
		driver.findElement(By.name("txtpassword")).sendKeys("1@3$5^");
		driver.findElement(By.className("SubmitButton_8baflu")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[4]/div[1]/div/div/div[3]/div[1]/div[3]/div[1]/div/div[7]/div/div[1]/a[1]")).click();
		GenericServices services=new GenericServices();
		
		OpsDashboard ops=new OpsDashboard(driver);
		Thread.sleep(6000);
		services.click(driver, ops.getEleAddButton());
		services.createCaseOps(driver, "case 1");
		services.searchAppID(driver, "case 1");
		services.assignDoctor(driver, "case 1");
		ServiceLib.initService(driver, "");*/
		
	}
	 

}
