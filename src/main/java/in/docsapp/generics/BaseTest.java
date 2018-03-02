package in.docsapp.generics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	
	public DesiredCapabilities capabilities;
	
	
	@BeforeClass
	public void launchApplication() {
		
		ChromeDriverManager.getInstance().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://b2btest.docsapp.in/webviews/telemerdashboardreact/?#!login");
		
	}
	
	
	@AfterClass
	public void exitApplication() {
//		driver.close();
	}
}
