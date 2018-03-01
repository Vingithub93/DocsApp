package in.docsapp.workflow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import in.docsapp.generics.ExcelLibrary;
import in.docsapp.generics.Wait;
import in.docsapp.pages.AddNewCase;
import in.docsapp.pages.BasePage;

public class GenericServices extends BasePage{


	public GenericServices(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	//finding dyanmic elements to answer doctor questions
	public void actionOnQuestion(WebDriver driver, String sheetName, String caseName)
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
	
	//Create a New Case as OPS
	public void createCaseOps(String caseName)
	{
		AddNewCase addCase = new AddNewCase(driver);
		
	}
}
