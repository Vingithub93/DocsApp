package in.docsapp.generics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	
	public DesiredCapabilities capabilities;
	
	
	@BeforeTest
	public void launchApplication() {
		
		ChromeDriverManager.getInstance().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://b2btest.docsapp.in/webviews/telemerdashboardreact/?#!login");
		
	}
	
	
	@AfterTest
	public void exitApplication() {
		driver.close();
	}
}
