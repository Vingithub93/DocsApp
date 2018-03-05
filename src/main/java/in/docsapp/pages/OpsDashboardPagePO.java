package in.docsapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.docsapp.generics.BasePage;
import in.docsapp.generics.BaseTest;

public class OpsDashboardPagePO extends BasePage {
	
	public OpsDashboardPagePO(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@value='+ Add']")
	private WebElement eleAddButton;
	
	public WebElement getEleAddButton()
	{
		BaseTest.element = "Add new case button in ops dashboard";
		return eleAddButton;
	}
	
	@FindBy(xpath="(//input[@type='search' or @placeholder='Search...'])[1]")
	private WebElement eleAppIDSearchTextField;
	
	public WebElement getEleAppIDSearchTextField()
	{
		BaseTest.element = "Application ID search text field";
		return eleAppIDSearchTextField;
	}
	
	public WebElement getEleDoctorsName(String doctorName)
	{
		BaseTest.element = doctorName+" button in assign doctor";
		return driver.findElement(By.xpath("//*[contains(text(),'"+doctorName+"')]"));
	}
	
	@FindBy(xpath="(//button[text()='Verify'])")
	private WebElement eleFormVerifyButton;
	
	public WebElement getEleFormVerifyButton()
	{
		BaseTest.element = "Verify button in ops verification form";
		return eleFormVerifyButton;
	}
}
