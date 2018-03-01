package in.docsapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewCase extends BasePage{
	
	public AddNewCase(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver,this);
	}
	@FindAll
	({
		@FindBy(id="add_appId"),
		@FindBy(xpath="//*[@placeholder='Application ID']")
	})
	private WebElement eleApplicationIDTextField;
	
	public WebElement getEleApplicationIDTextField()
	{
		return eleApplicationIDTextField;
	}
	@FindAll
	({
		@FindBy(id="add_name"),
		@FindBy(xpath="//*[@placeholder='Name']")
	})
	private WebElement eleNameTextField;
	
	public WebElement getEleNameTextField()
	{
		return eleNameTextField;
	}
	@FindAll
	({
		@FindBy(id="add_phone"),
		@FindBy(xpath="//*[@placeholder='Primary Contact Number']")
	})
	private WebElement elePhoneTextField;
	
	public WebElement getElePhoneTextField()
	{
		return elePhoneTextField;
	}
	@FindAll
	({
		@FindBy(id="add_altphone"),
		@FindBy(xpath="//*[@placeholder='Secondary Contact Number']")
	})
	private WebElement eleAltPhoneTextField;
	
	public WebElement getEleAltPhoneTextField()
	{
		return eleAltPhoneTextField;
	}
	@FindAll
	({
		@FindBy(id="add_dobdd"),
		@FindBy(xpath="//input[@placeholder='DD']")
	})
	private WebElement eleDayDOBTextField;
	
	public WebElement getEleDayDOBTextField()
	{
		return eleDayDOBTextField;
	}
	@FindAll
	({
		@FindBy(id="add_dobmm"),
		@FindBy(xpath="//input[@placeholder='MM']")
	})
	private WebElement eleMonthDOBTextField;
	
	public WebElement getEleMonthDOBTextField()
	{
		return eleMonthDOBTextField;
	}
	@FindAll
	({
		@FindBy(id="add_dobyy"),
		@FindBy(xpath="//input[@placeholder='YYYY']")
	})
	private WebElement eleYearDOBTextField;
	
	public WebElement getEleYearDOBTextField()
	{
		return eleYearDOBTextField;
	}
	
		@FindBy(id="add_gender")
		private WebElement eleGenderDropdown;
		
		public WebElement getEleGenderDropdown()
		{
		return eleGenderDropdown;
		}
		
	@FindAll
	({
		@FindBy(id="add_address"),
		@FindBy(xpath="//input[@placeholder='Full Address']")
	})
	private WebElement eleAddressTextField;
	
	public WebElement getEleAddressTextField()
	{
		return eleAddressTextField;
	}
	
	@FindBy(id="add_vendor")
	private WebElement eleVendorDropdown;
	
	public WebElement getEleVendorDropdown()
	{
		return eleVendorDropdown;
	}
	
	
	@FindAll
	({
		@FindBy(id="add_pan"),
		@FindBy(xpath="//input[@placeholder='Enter PAN']")
	})
	private WebElement elePANTextField;
	
	public WebElement getElePANTextField()
	{
		return elePANTextField;
	}
	
	
	@FindAll
	({
		@FindBy(id="add_plan"),
		@FindBy(xpath="//input[@placeholder='Plan Details']")
	})
	private WebElement elePlanTextField;
	
	public WebElement getElePlanTextField()
	{
		return elePlanTextField;
	}
	@FindAll
	({
		@FindBy(id="add_nomineeName"),
		@FindBy(xpath="//input[@placeholder='Name of Nominee']")
	})
	private WebElement eleNomineeNameTextField;
	
	public WebElement getEleNomineeNameTextField()
	{
		return eleNomineeNameTextField;
	}
	
	@FindAll
	({
		@FindBy(id="add_nomineedob"),
		@FindBy(xpath="//input[@placeholder='YYYY-MM-DD']")
	})
	private WebElement eleNomineeDOBTextField;
	
	public WebElement getEleNomineeDOBTextField()
	{
		return eleNomineeDOBTextField;
	}
	
	
	@FindAll
	({
		@FindBy(id="submit_btn"),
		@FindBy(xpath="//btn[.='Submit']")
	})
	private WebElement eleSubmitButton;
	
	public WebElement getEleSubmitButton()
	{
		return eleSubmitButton;
	}
	@FindAll
	({
		@FindBy(id="submit_add_btn"),
		@FindBy(xpath="//btn[text()='Submit & Add Another']")
	})
	private WebElement eleSubmitAddAnotherButton;
	
	public WebElement getEleSubmitAddAnotherButton()
	{
		return eleSubmitAddAnotherButton;
	}
	


	
	
	
}
