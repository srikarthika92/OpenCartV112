package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyaccountPage extends BasePage
{
public MyaccountPage(WebDriver driver)
{
	super(driver);
}
@FindBy(xpath="//a[.='Edit Account']")
WebElement lnkEditAccount;

@FindBy(linkText="Logout")
WebElement lnkLogout;
public boolean lnkEditAccount()
{
	try {
	boolean cnfmsg=lnkEditAccount.isDisplayed();
	return cnfmsg;
	}
	catch(Exception e)
	{
		return false;
	}
}

public void clickLogout()
{
	lnkLogout.click();
}
}
