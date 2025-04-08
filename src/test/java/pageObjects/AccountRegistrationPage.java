package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
public AccountRegistrationPage(WebDriver driver)
{
	super(driver);
}

@FindBy(id="input-firstname") 
WebElement txtFirstname;

@FindBy(id="input-lastname")
WebElement txtLastname;

@FindBy(id="input-email")
WebElement txtEmail;

@FindBy(id="input-telephone")
WebElement txtTelephone;

@FindBy(id="input-password")
WebElement txtPassword;

@FindBy(id="input-confirm")
WebElement txtConfirmpassword;

@FindBy(xpath="//input[@name='agree']")
WebElement chkPrivacypolicy;

@FindBy(xpath="//input[@value='Continue']")
WebElement btnContinue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;

public void setFirstname(String firstname)
{
txtFirstname.sendKeys(firstname);	
}
public void setLastname(String lastname)
{
	txtLastname.sendKeys(lastname);
}
public void setEmail(String email)
{
	txtEmail.sendKeys(email);
}
public void setTelephone(String telephone)
{
	txtTelephone.sendKeys(telephone);
}
public void setPassword(String password)
{
	txtPassword.sendKeys(password);
}
public void setConfirmpassword(String password)
{
	txtConfirmpassword.sendKeys(password);
}
public void clickPrivacypolicy()
{
	chkPrivacypolicy.click();
}
public void clickContinue()
{
	btnContinue.click();
}

public String getConfirmationmsg()
{
	try
	{
		return(msgConfirmation.getText());
	}
	catch(Exception e)
	{
		return (e.getMessage());
	}
}
}
