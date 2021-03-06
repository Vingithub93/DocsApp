package in.docsapp.generics;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.browserstack.local.Local;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.OperaDriverManager;

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
		}
	}
	
	private Local l;
	@BeforeClass
	public void launchApplication() {
		
		String browserName=System.getProperty("browser_name");
		System.out.println("Browser Name : "+browserName);
		
		
		try
		{
			extent=new ExtentReports(extent_path,false);
			extent.assignProject("ProjectName");
		}
		catch(Exception e) 
		{			
		}
		try {
			
			String browserStackBrowser = "";
			if(browserName.contains("browserstack")) {
				String[] temp;
				temp = browserName.split("_");
				browserName = temp[0];
				browserStackBrowser = temp[1];
				
			}

			System.out.println("Browser Name: "+ browserName);
			
			if(browserName.equalsIgnoreCase("chrome"))
			{
			ChromeDriverManager.getInstance().setup();
			driver=new ChromeDriver();
			}
			else if(browserName.equalsIgnoreCase("firefox"))
			{
				FirefoxDriverManager.getInstance().setup();
				driver=new FirefoxDriver();
				
			}
			else if(browserName.equalsIgnoreCase("ie"))
			{
				InternetExplorerDriverManager.getInstance().setup();
				driver=new InternetExplorerDriver();
			}
			else if(browserName.equalsIgnoreCase("opera"))
			{
				OperaDriverManager.getInstance().setup();
				driver=new OperaDriver();
			}
			
			else if (browserName.equalsIgnoreCase("browserstack")) {
				String config_file = "./src/main/java/in/docsapp/generics/browserstack.config.json";
				System.out.println(config_file);
		        JSONParser parser = new JSONParser();
		        JSONObject config = (JSONObject) parser.parse(new FileReader(config_file));
		        JSONObject envs = (JSONObject) config.get("environments");

		        DesiredCapabilities capabilities = new DesiredCapabilities();
		        

		        Map<String, String> envCapabilities = (Map<String, String>) envs.get(browserStackBrowser);
		        Iterator it = envCapabilities.entrySet().iterator();
		        while (it.hasNext()) {
		            Map.Entry pair = (Map.Entry)it.next();
		            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
		        }
		        
		        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
		        it = commonCapabilities.entrySet().iterator();
		        
		        while (it.hasNext()) {
		            Map.Entry pair = (Map.Entry)it.next();
		            if(capabilities.getCapability(pair.getKey().toString()) == null){
		                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
		            }
		        }

		        String username = System.getenv("BROWSERSTACK_USERNAME");
		        if(username == null) {
		            username = (String) config.get("browserstack_user");
		        }

		        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
		        if(accessKey == null) {
		            accessKey = (String) config.get("browserstack_key");
		        }

		        if(capabilities.getCapability("browserstack.local") != null && capabilities.getCapability("browserstack.local") == "true"){
		            l = new Local();
		            Map<String, String> options = new HashMap<String, String>();
		            options.put("key", accessKey);
		            l.start(options);
		        }

		        driver = new RemoteWebDriver(new URL("http://"+username+":"+accessKey+"@"+config.get("server")+"/wd/hub"), capabilities);
			
			}
			else {
				logger.log(LogStatus.WARNING, " Browser is not configured to launch");
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			
			EventFiringWebDriver e_driver = new EventFiringWebDriver(driver);
			MyListners eventListener = new MyListners();
			e_driver.register(eventListener);
			driver = e_driver;
			
			driver.get("https://b2btest.docsapp.in/webviews/telemerdashboardreact/?#!login");
		
	}
		catch (Exception e) {
			
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
//		  extent.flush();
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
