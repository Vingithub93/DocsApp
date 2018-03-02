package in.docsapp.workflow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import in.docsapp.generics.ExcelLibrary;
import in.docsapp.generics.GenericMethods;
import in.docsapp.generics.Wait;
import in.docsapp.pages.AddNewCase;
import in.docsapp.pages.OpsDashboard;
/**
 * 
 * @author-Vinayak
 * 
 * 
 *
 */
public class GenericServices extends GenericMethods{
	
	/**
	 * 
	 * @param driver
	 * @param caseName
	 * 
	 * @ This method performs actions on questions under diagnose option as doctor
	 * 
	 * 
	 */
	//finding dyanmic elements to answer doctor questions
	public void actionOnQuestion(WebDriver driver, String caseName)
	{
		String sheetName="";
		int rowNum=ExcelLibrary.findRowNum(caseName, GenericMethods.getConfigProperty("sheet5"));
		
		String vendor=ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Vendor");
		
		String[] ch = vendor.toLowerCase().split(" ");
		vendor="";
		for(String s:ch)
		{
			vendor=vendor+s;
		}
		System.out.println(vendor);
		sheetName=ExcelLibrary.getSheetName(vendor);
		System.out.println(sheetName);
		
		int rowCount=ExcelLibrary.getLastRowCount(sheetName);
		System.out.println(rowCount);
		for(int i=1; i<=rowCount; i++)
		{
			String question=ExcelLibrary.getCellValue(sheetName, i, 0);
			
			String option=ExcelLibrary.getCellValue(sheetName, i, ExcelLibrary.findCellNum(caseName, sheetName));
			
			String answer=ExcelLibrary.getCellValue(sheetName, i, ExcelLibrary.findCellNum(caseName, sheetName)+1);
			
			
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'"+question+"')]")));
			if(option.equalsIgnoreCase("yes"))
			{
				Wait.waitForElementClickable(driver, driver.findElement(By.xpath("//*[contains(text(),'"+question+"')]/../..//span[contains(text(),'Yes')]")));
				driver.findElement(By.xpath("//*[contains(text(),'"+question+"')]/../..//span[contains(text(),'Yes')]")).click();
				try {
					List<WebElement> ele = driver.findElements(By.xpath("//*[contains(text(),'"+question+"')]/../..//div[last()]//input"));
					for(WebElement we:ele)
					{
						clearTextField(driver, we);
						type(driver, we, answer);
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
	public void createCaseOps(WebDriver driver,String caseName)
	{
		AddNewCase addCase = new AddNewCase(driver);
		int rowNum=ExcelLibrary.findRowNum(caseName, GenericMethods.getConfigProperty("sheet5"));
		
		type(driver, addCase.getEleApplicationIDTextField(), ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Application_ID"));

		type(driver, addCase.getEleNameTextField(), ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Name"));
		
		type(driver, addCase.getElePhoneTextField(), ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Phone_Number"));
		
		type(driver, addCase.getEleAltPhoneTextField(), ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Alt_Phone_Number"));
		
		String value=ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Date_of_Birth(DD-MM-YYYY)");
		String[] DOB = value.split("-");
		
		type(driver, addCase.getEleDayDOBTextField(), DOB[0]);
		
		type(driver, addCase.getEleMonthDOBTextField(), DOB[1]);
		
		type(driver, addCase.getEleYearDOBTextField(), DOB[2]);
		
		selectbyVisibletext(addCase.getEleGenderDropdown(), ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Gender"));
		
		type(driver, addCase.getEleAddressTextField(), ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Address"));
		
		selectbyVisibletext(addCase.getEleVendorDropdown(), ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Vendor"));
		
		type(driver, addCase.getElePANTextField(), ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "PAN"));
		
		type(driver, addCase.getElePlanTextField(), ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Plan_Details"));
		
		type(driver, addCase.getEleNomineeNameTextField(), ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Nominee"));
		
		type(driver, addCase.getEleNomineeDOBTextField(), ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Nominee_DOB"));
	}
	
	public void searchAppID(WebDriver driver, String caseName)
	{
		OpsDashboard ops=new OpsDashboard(driver);
		int rowNum=ExcelLibrary.findRowNum(caseName, GenericMethods.getConfigProperty("sheet5"));
		String data=ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Application_ID");
		type(driver, ops.getEleAppIDSearchTextField(), data);
	}
	
	public void assignDoctor(WebDriver driver, String caseName)
	{
		OpsDashboard ops=new OpsDashboard(driver);
		
		searchAppID(driver, caseName);
		int rowNum=ExcelLibrary.findRowNum(caseName, GenericMethods.getConfigProperty("sheet5"));
		String appID=ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Application_ID");
		String doctorName=ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Doctor");
		click(driver, ops.getEleApplicationIDAssign(appID));
		click(driver, ops.getEleDoctorsName(doctorName));
		driver.switchTo().alert().accept();
	}
}
