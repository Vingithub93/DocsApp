package in.docsapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.docsapp.generics.BasePage;

public class VendorsDashboardPagePO extends BasePage{
	
	public VendorsDashboardPagePO(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//input[@value='Upload']")
	private WebElement eleVendorsDashboardButton;
	
	public WebElement getEleVendorsDashboardButton()
	{
		return eleVendorsDashboardButton;
	}



}
