package in.docsapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.docsapp.generics.BasePage;
import in.docsapp.generics.BaseTest;

public class SigninPagePagePO extends BasePage{
	
	public 	SigninPagePagePO(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindAll
	({
		@FindBy(name="txtemail"),
		@FindBy(xpath="//*[@type='email' or @placeholder='Username']")
	})
	private WebElement eleSigninUsername;
	
	public WebElement getEleSigninUsername()
	{
		BaseTest.element = "Username in Signin page";
		return eleSigninUsername;
	}
	
	@FindAll
	({
	@FindBy(name="txtpassword"),
	@FindBy(xpath="//*[@type='password' or @placeholder='Password']")
	})
	private WebElement eleSigninPassword;

	public WebElement getEleSigninPassword()
	{
		BaseTest.element = "Password in Signin page";
		return eleSigninPassword;
	}
	@FindAll
	({
	@FindBy(className="SubmitButton_8baflu"),
	@FindBy(xpath="//*[@type='submit' or @value='Sign In']")
	})
	private WebElement eleSigninButton;

	public WebElement getEleSigninButton()
	{
		BaseTest.element = "Signin Button";
		return eleSigninButton;
	}
  

}
	
	


