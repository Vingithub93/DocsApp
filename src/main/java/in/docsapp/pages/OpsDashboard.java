package in.docsapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpsDashboard extends BasePage {
	
	public OpsDashboard (WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@value='+ Add']")
	private WebElement eleAddButton;
	
	public WebElement getEleAddButton()
	{
		return eleAddButton;
	}
	
	public WebElement getEleApplicationIDAssign(String appID)
	{
		return driver.findElement(By.xpath("//div[text()='"+appID+"']/../../../..//div[contains(text(),'Assign')]"));
	}
	
	@FindBy(xpath="(//input[@type='search' or @placeholder='Search...'])[1]")
	private WebElement eleAppIDSearchTextField;
	
	public WebElement getEleAppIDSearchTextField()
	{
		return eleAppIDSearchTextField;
	}
	
	public WebElement getEleDoctorsName(String doctorName)
	{
		return driver.findElement(By.xpath("//*[contains(text(),'"+doctorName+"')]"));
	}
	
}
