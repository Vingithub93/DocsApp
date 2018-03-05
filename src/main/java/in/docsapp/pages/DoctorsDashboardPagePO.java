package in.docsapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.docsapp.generics.BasePage;
import in.docsapp.generics.BaseTest;

public class DoctorsDashboardPagePO extends BasePage {
	
	public DoctorsDashboardPagePO(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver,this);
	}
	@FindAll
	({
		@FindBy(id="add_appId"),
		@FindBy(xpath="//*[@placeholder='Application ID']")
	})
	private WebElement eleDiagnosisButton;
	
	public WebElement getEleDiagnosisButton()
	{
		return eleDiagnosisButton;
	}
	
	@FindAll
	({
		@FindBy(xpath="//button[text()='Submit']")
	})
	private WebElement eleDiagnosisSubmitButton;
	
	public WebElement getEleDiagnosisSubmitButton()
	{
		BaseTest.element = "Submit button in diagnose form";
		return eleDiagnosisSubmitButton;
	}
	
	@FindAll
	({
		@FindBy(xpath="//div[text()='X']")
	})
	private WebElement eleFormCloseButton;
	
	public WebElement getEleFormCloseButton()
	{
		BaseTest.element = "close button in diagnose form";
		return eleFormCloseButton;
	}
	
	
}
