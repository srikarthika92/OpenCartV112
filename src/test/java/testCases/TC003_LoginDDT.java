package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyaccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass
{
	
	@Test(dataProvider="loginData",dataProviderClass=DataProviders.class)
	public void LoginTest(String username,String password,String exp)
	{
		try {
		
		HomePage hp=new HomePage(driver);
	    hp.clickMyaccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		System.out.println("Testing with: " + username + " | " + password);
		lp.txtUsername(username);
		lp.txtPassword(password);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(lp.btnLogin));  // No parentheses, as btnLogin is a WebElement!

		lp.btnLogin();  // Now calling the click method
       Thread.sleep(1000);
		
		/*
		 * Data Valid - Login Success - Test Pass- Logout
		 *            - Login Failed  - Test Failed
		 *            
		 * Data Invalid - Login Success - Test Fail - Logout
		 *                Login Failed - Test Pass -
		 */
		
		MyaccountPage macc=new MyaccountPage(driver);
		
		boolean targetpage=macc.lnkEditAccount();
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetpage==true)
			{
				
				macc.clickLogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetpage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
			
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}

}
