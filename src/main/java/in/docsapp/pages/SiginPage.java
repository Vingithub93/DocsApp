package in.docsapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SiginPage extends BasePage{
	
	public 	SiginPage(WebDriver driver)
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
		return eleSigninButton;
}
  

}
	
	


