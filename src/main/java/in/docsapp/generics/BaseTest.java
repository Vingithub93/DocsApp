package in.docsapp.generics;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.ChromeDriverManager;

@Listeners(MyListners.class)
public class BaseTest extends MyListners {
	
	public WebDriver driver;
	
	public DesiredCapabilities capabilities;
	
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	public String extent_path;
	
	GenericMethods gen=new GenericMethods();
	
	public static String element;
	
	@BeforeSuite(alwaysRun = true)
	public void CreateExtentReport() 
	{
		gen.moveOldReports();
		extent_path = gen.createPath(gen.extent_path)+"Report_"+gen.TimeStamp()+".html";
		System.out.println("Before Suite " + extent_path);
		File file=new File(extent_path);
		try
		{
			
			file.createNewFile();	
			
			System.out.println("Extent File created");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to create Extent file");
		}
	}
	
	@BeforeClass
	public void launchApplication() {
		
		try
		{
			extent=new ExtentReports(extent_path,false);
			extent.assignProject("ProjectName");
		}
		catch(Exception e) 
		{			
		}
		try {

			ChromeDriverManager.getInstance().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.get("https://b2btest.docsapp.in/webviews/telemerdashboardreact/?#!login");
		}
		catch (Exception e) {
			logger.log(LogStatus.WARNING, " Browser is not configured to launch");
		}
		
	}
	
String testClassName;
	
	String testName;

	@BeforeMethod(alwaysRun=true)
	public void getTestDetails(Method method)
	{
		String field = null;
		testName = null;
		testClassName=null;
		try
		{
			testClassName = this.getClass().getSimpleName();
			System.out.println("test class name : " + testClassName);
			testName=method.getName();
			System.out.println("Test name : " + testName);
			//field=testName.getDeclaredField("testDesc").get(this).toString();
			//field=method.getName().valueOf("testDesc");
			field = this.getClass().getDeclaredField("testDesc").get(this).toString();
			System.out.println("Field : " + field);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		logger=extent.startTest(testClassName+"_"+testName,field);
		String packageName = this.getClass().getPackage().toString();
		System.out.println("Package Name : " + packageName);
		logger.assignCategory(packageName);
	}
	
	@AfterMethod(alwaysRun=true)
	public void endtest(ITestResult iRes){
		  extent.endTest(logger);
		  extent.flush();
	}
	@AfterClass
	public void exitApplication() {
		extent.flush();
		driver.close();
	}
	
	@AfterSuite
	public void closeExtentReport() {
		extent.close();
	}
}
