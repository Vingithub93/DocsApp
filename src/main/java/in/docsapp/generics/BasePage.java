package in.docsapp.generics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

@Listeners(MyListners.class)
public class BasePage {

	public static WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Logout']")
	private WebElement elelogoutButton;
	
	public WebElement getElelogoutButton()
	{
		BaseTest.element = "Logout link";
		return elelogoutButton;
	}
	
	public WebElement getEleforParticularCase(String appID, String text)
	{
		BaseTest.element = text+" button";
		return driver.findElement(By.xpath("//div[text()='"+appID+"']/../../../..//*[contains(text(),'"+text+"')]"));
	}
	
	@FindBy(xpath="//div[@style='right: -100px;']")
	private WebElement eleLoader;
	
	public WebElement getEleLoader()
	{
		return eleLoader;
	}
	
	@FindBy(xpath="//div[@class='LogoContainer_h5zlxv']//img")
	private WebElement eleDocsAppLogo;
	
	public WebElement getDocsAppLogo()
	{
		return eleLoader;
	}
}
