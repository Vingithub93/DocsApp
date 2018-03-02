package in.docsapp.workflow;

import org.openqa.selenium.WebDriver;

import in.docsapp.generics.BasePage;
import in.docsapp.generics.ExcelLibrary;
import in.docsapp.generics.GenericMethods;
import in.docsapp.generics.Wait;
import in.docsapp.pages.SigninPage;

/**
 * 
 * @author Automation
 *
 */
public class ServiceLib {

	
	/**
	 * <p>
	 *  <b>Note:</b>This method login to the application based on the usename and password
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
		SigninPage signin=new SigninPage(driver);
		GenericMethods methods=new GenericMethods();
		
		methods.type(driver, signin.getEleSigninUsername(), userName);
		methods.type(driver, signin.getEleSigninPassword(), password);
		methods.click(driver, signin.getEleSigninButton());
		}
		catch (Throwable e) {
			System.out.println("Unable to login to the application using "+userName+" "+ password);
		}
		
	}
	
	/**
	 * <p>
	 * <b>Note:</b> This method login to application using type of user(ops, doctor, vendor) and case name or user name 
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
		
		SigninPage signin=new SigninPage(driver);
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
		methods.click(driver, signin.getEleSigninButton());
		}
		catch (Throwable e) {
			System.out.println("Unable to login based on "+ caseOrUsername);
		}
		
	}
	
	public void exit(WebDriver driver)
	{
		BasePage base=new BasePage(driver);
		GenericMethods methods=new GenericMethods();
		
		methods.click(driver, base.getElelogoutButton());
	}
}
