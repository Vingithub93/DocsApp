package in.docsapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.docsapp.generics.ExcelLibrary;
import io.github.bonigarcia.wdm.ChromeDriverManager;

public class SamplePage {

	public  static WebDriver driver;
	public String uname;
	@FindAll
	({
		@FindBy(id=""),
		@FindBy(name="txtmail"),
		@FindBy(xpath="//tagname[text()='']")
	})
	private WebElement eleSigninUsername;
	
	public WebElement getEleSigninUsername()
	{
		uname = "";
		return eleSigninUsername;
	}
	
	public  SamplePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public static void main(String[] args) throws Exception{
		ChromeDriverManager.getInstance().setup();
		driver=new ChromeDriver();
		driver.get("https://b2btest.docsapp.in/webviews/telemerdashboardreact/?#!login");
		SamplePage sam=new SamplePage(driver);
		Thread.sleep(4000);
	}

}
