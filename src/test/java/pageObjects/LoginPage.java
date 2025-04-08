
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(id="input-email")
	WebElement txtUsername;
	
	@FindBy(id="input-password")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	public
	WebElement btnLogin;
	
	 public void txtUsername(String username)
	 {
		 txtUsername.sendKeys(username);
	 }

	 public void txtPassword(String password)
	 {
		 txtPassword.sendKeys(password);
	 }
	 public void btnLogin()
	 {
		 btnLogin.click();
	 }
}
