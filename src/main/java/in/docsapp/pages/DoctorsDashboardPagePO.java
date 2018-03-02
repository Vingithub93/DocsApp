package in.docsapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.docsapp.generics.BasePage;

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
		return eleDiagnosisSubmitButton;
	}
	
	@FindAll
	({
		@FindBy(xpath="//div[text()='X']")
	})
	private WebElement eleFormCloseButton;
	
	public WebElement getEleFormCloseButton()
	{
		return eleFormCloseButton;
	}
	
	
}
