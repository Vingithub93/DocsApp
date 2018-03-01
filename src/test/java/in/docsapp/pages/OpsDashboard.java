package in.docsapp.pages;

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
	@FindBy(xpath="//input[@value='Assign ']")
	private WebElement eleOpsDashboardAssignButton;
	
	public WebElement getOpsDashboardAssignButton()
	{
		return eleOpsDashboardAssignButton;
	}


}
