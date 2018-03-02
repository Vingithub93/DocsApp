package in.docsapp.workflow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import in.docsapp.generics.BasePage;
import in.docsapp.generics.ExcelLibrary;
import in.docsapp.generics.GenericMethods;
import in.docsapp.generics.Wait;
import in.docsapp.pages.AddNewCasePagePO;
import in.docsapp.pages.DoctorsDashboardPagePO;
import in.docsapp.pages.OpsDashboardPagePO;
import in.docsapp.pages.SigninPagePagePO;

/**
 * 
 * @author Vinayak
 *
 */
public class ServiceLib extends GenericMethods{

	
	/**
	 * <p>
	 *  <b>Note:</b>This method helps login to the application based on the usename and password
	 *  </p>
	 * @param driver
	 * @param userName
	 * @param password
	 * 
	 *
	 * 
	 * 
	 */
	public void initService(String userName, String password, WebDriver driver)
	{
		try {
		SigninPagePagePO signin=new SigninPagePagePO(driver);
		GenericMethods methods=new GenericMethods();
		
		methods.type(driver, signin.getEleSigninUsername(), userName);
		methods.type(driver, signin.getEleSigninPassword(), password);
		methods.customDelay(5);
		methods.click(driver, signin.getEleSigninButton());
		}
		catch (Throwable e) {
			System.out.println("Unable to login to the application using "+userName+" "+ password);
		}
		
	}
	
	/**
	 * <p>
	 * <b>Note:</b> This method helps login to application using type of user(ops, doctor, vendor) and case name or user name 
	 * </p>
	 * 
	 * @param driver
	 * @param typeOfUser
	 * @param caseName
	 * 
	 * 
	 */
	public void initService(WebDriver driver, String typeOfUser, String caseOrUsername)
	{
		try {
		
		SigninPagePagePO signin=new SigninPagePagePO(driver);
		GenericMethods methods=new GenericMethods();
		
		String user=typeOfUser.toLowerCase();
		System.out.println(user);
		
		String userName="";
		String password="";
		String sheetName="";
		
		if(caseOrUsername.toLowerCase().contains("case"))
		{
			System.out.println("Entered Case Loop");
			int rowNum=ExcelLibrary.findRowNum(caseOrUsername, GenericMethods.getConfigProperty("sheet5"));
			System.out.println("Row count of "+caseOrUsername+" = "+rowNum);
			
			if(user.contains("ops"))
			{
				caseOrUsername="ops";
				System.out.println("Name = "+caseOrUsername);
			}
			else if(user.contains("doctor"))
			{
				caseOrUsername=ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Doctor");
				System.out.println("Name = "+caseOrUsername);
			}
			
			else if (user.contains("vendor")) {
				caseOrUsername=ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Vendor");
				System.out.println("Name = "+caseOrUsername);
			}
			
			else {
				System.out.println(typeOfUser+" user does not match");
			}
		}
		
		if(user.contains("ops"))
		{
			sheetName=GenericServices.getConfigProperty("sheet2");
		}
		else if (user.contains("doctor")) {
			sheetName=GenericServices.getConfigProperty("sheet3");
		}
		else if (user.contains("vendor")) {
			sheetName=GenericServices.getConfigProperty("sheet4");
		}
		else {
			System.out.println(typeOfUser+" user does not match");
		}
		
		System.out.println("Sheet name of login "+sheetName);
		int rowCount =ExcelLibrary.getLastRowCount(sheetName);
		
		first:
		for(int i=1;i<=rowCount;i++)
		{
			if(ExcelLibrary.getCellValue(sheetName, i, 0).contains(caseOrUsername))
			{
				userName=ExcelLibrary.getCellValue(sheetName, i, 1);
				password=ExcelLibrary.getCellValue(sheetName, i, 2);
				System.out.println(userName);
				System.out.println(password);
				break first;
			}
		}
		
		methods.type(driver, signin.getEleSigninUsername(), userName);
		methods.type(driver, signin.getEleSigninPassword(), password);
		methods.customDelay(5);
		methods.click(driver, signin.getEleSigninButton());
		}
		catch (Throwable e) {
			System.out.println("Unable to login based on "+ caseOrUsername);
		}
		
	}
	
	/**
	 * <p>
	 * <b>Note:</b> This method helps to logout from the application
	 * 
	 * </p>
	 * @param driver
	 */
	public void exit(WebDriver driver)
	{
		BasePage base=new BasePage(driver);
		GenericMethods methods=new GenericMethods();
		methods.customDelay(3);
		methods.click(driver, base.getElelogoutButton());
	}
	
	/**
	 * 
	 * @param driver
	 * @param caseName
	 * 
	 * <p>
	 *<b>Note:</b>This method is used to diagnose a case as doctor and verify it as a ops for max life 
	 * </p>
	 * 
	 */
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

				click(driver, driver.findElement(By.xpath("//*[contains(text(),'"+question+"')]/../..//span[contains(text(),'Yes')]")));
				
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
				click(driver, driver.findElement(By.xpath("//*[contains(text(),'"+question+"')]/../..//span[contains(text(),'No')]")));
			}
			else if (option.equalsIgnoreCase("NA")) {
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
				
			
		}
		
	}
	
	
	/**
	 * 
	 * @param driver
	 * @param caseName
	 * 
	 * <p>
	 * <b>Note: </b> This method is used to create a new case as ops
	 * 
	 * </p>
	 */
	public void createCaseOps(WebDriver driver,String caseName)
	{
		AddNewCasePagePO addCase = new AddNewCasePagePO(driver);
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
		
		click(driver, addCase.getEleSubmitButton());
	}
	
	/**
	 * 
	 * @param driver
	 * @param caseName
	 * 
	 * <p>
	 * <b>Note: </b>This method is searches for the case based on the Application ID
	 * 
	 * 
	 * </p>
	 * 
	 */
	public void searchAppID(WebDriver driver, String caseName)
	{
		OpsDashboardPagePO ops=new OpsDashboardPagePO(driver);
		int rowNum=ExcelLibrary.findRowNum(caseName, GenericMethods.getConfigProperty("sheet5"));
		String data=ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Application_ID");
		type(driver, ops.getEleAppIDSearchTextField(), data);
	}
	
	
	/**
	 * 
	 * @param driver
	 * @param caseName
	 * 
	 * <p>
	 * <b>Note:</b>This method assigns a doctor to a case based on the case name
	 * 
	 * </p>
	 */
	public void assignDoctor(WebDriver driver, String caseName)
	{
		OpsDashboardPagePO ops=new OpsDashboardPagePO(driver);
		
		waitForCasesToLoad(driver);
		searchAppID(driver, caseName);
		int rowNum=ExcelLibrary.findRowNum(caseName, GenericMethods.getConfigProperty("sheet5"));
		String appID=ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Application_ID");
		String doctorName=ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Doctor");
		click(driver, ops.getEleApplicationIDAssign(appID));
		click(driver, ops.getEleDoctorsName(doctorName));
		try {
		driver.switchTo().alert().accept();
		}
		catch (Throwable e) {
		}
	}
	
	/**
	 * 
	 * @param driver
	 * @param caseName
	 * <p>
	 * <b>Note:</b>This method clicks on view button for based on Application ID for a given case number
	 * 
	 * </p>
	 * 
	 */
	public void viewCaseAsOps(WebDriver driver, String caseName) {
		OpsDashboardPagePO ops=new OpsDashboardPagePO(driver);
		BasePage base=new BasePage(driver);
		
		searchAppID(driver, caseName);
		int rowNum=ExcelLibrary.findRowNum(caseName, GenericMethods.getConfigProperty("sheet5"));
		String appID=ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Application_ID");
		click(driver, base.getEleforParticularCase(appID, "View"));
		
	}
	
	/**
	 * 
	 * @param driver
	 * 
	 * <p>
	 * <b>Note:</b>This method wait for the cases to load
	 * 
	 * </p>
	 */
	public void waitForCasesToLoad(WebDriver driver)
	{
		BasePage basePage=new BasePage(driver);
		GenericMethods genericMethods=new GenericMethods();
		
		genericMethods.customDelay(8);
		genericMethods.waitUntilElementISVisible(driver, basePage.getEleLoader());
	}
	
	/**
	 * 
	 * @param driver
	 * @param caseName
	 * 
	 * <p>
	 * <b>Note:</b>This method has complete actions to add case from ops dashboard
	 * 
	 * </p>
	 */
	public void addCaseFlow(WebDriver driver, String caseName)
	{
		OpsDashboardPagePO ops=new OpsDashboardPagePO(driver);
		
		waitForCasesToLoad(driver);
		click(driver, ops.getEleAddButton());
		createCaseOps(driver, caseName);
		
	}
	
	/**
	 * 
	 * @param driver
	 * @param caseName
	 * 
	 * <p>
	 * <b>Note:</b>This method has complete actions to diagnose case based on the Application ID for a Case name from doctor dashboard
	 * 
	 * </p>
	 */
	public void diagnoseCaseFlow(WebDriver driver, String caseName)
	{
		OpsDashboardPagePO ops=new OpsDashboardPagePO(driver);
		BasePage basePage =new BasePage(driver);
		DoctorsDashboardPagePO doctor=new DoctorsDashboardPagePO(driver);
		
		
		waitForCasesToLoad(driver);
		searchAppID(driver, caseName);
		int rowNum=ExcelLibrary.findRowNum(caseName, GenericMethods.getConfigProperty("sheet5"));
		String appID=ExcelLibrary.getSingleCell(GenericMethods.getConfigProperty("sheet5"), rowNum, "Application_ID");
		click(driver, basePage.getEleforParticularCase(appID, "Diagnosis"));
		
		actionOnQuestion(driver, caseName);
		
		click(driver, doctor.getEleDiagnosisSubmitButton());
		
		
		
	}
}
