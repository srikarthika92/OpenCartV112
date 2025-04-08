package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyaccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups={"sanity","master"})
	public void verify_UserLogin()
	{
		logger.info("****** STARTING TC002_LoginTest *******");
		try
		{
	
	HomePage hp=new HomePage(driver);
	
	hp.clickMyaccount();
	hp.clickLogin();
	
	LoginPage lp=new LoginPage(driver);
	
	lp.txtUsername(prop.getProperty("username"));
	lp.txtPassword(prop.getProperty("password"));
	lp.btnLogin();
	
	MyaccountPage macc=new MyaccountPage(driver);
	
	boolean targetpage=macc.lnkEditAccount();
	
	Assert.assertTrue(targetpage);
	}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** FINISHED TC002_LoginTest ******");
		}
	
}
