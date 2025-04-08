package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	
	@Test(groups={"sanity","regression","master"})
	public void verify_account_registration()
	{
		try {
			logger.info("******* STARTING TC001_AccountRegistrationTest ********");
			
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		logger.info("Clicked on MyAccount link");
		
		hp.clickRegister();
		logger.info("Clicked on Register link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details");
		
		regpage.setFirstname(randomString().toUpperCase());
		regpage.setLastname(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmpassword(password);
		regpage.clickPrivacypolicy();
		regpage.clickContinue();
		logger.info("Validating Expected Message");
		
		String confmsg=regpage.getConfirmationmsg();
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!"); this won't work. 
		//bcoz once assertion fails remaining statement wont be executed
		if(confmsg.equals("Your Account Has Been Created!")) 
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}}
		catch(Exception e)
		{
			Assert.fail();
			
		}
		
		logger.info("****** FINISHED TC001_AccountRegistrationTest *******");
	}

}

