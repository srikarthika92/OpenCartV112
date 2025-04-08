package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	
	public ExtentSparkReporter sparkreporter;
	
	public ExtentReports extent;
	
	public ExtentTest test;
	
	String reportName;
	
	      public void onStart(ITestContext testContext)
	      {
		   /* SimpleDateFormat df=new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
		    Date dt=new Date();
		    String timestamp=df.format(dt); */
	    	  
	      String timestamp= new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
	      
	      reportName="test-report"+ timestamp+".html";
	      
	      sparkreporter= new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\"+reportName);
	      
	      sparkreporter.config().setDocumentTitle("OpenCart Automation Report");
	      
	      sparkreporter.config().setReportName("OpenCart Functional Testing");
	      
	      sparkreporter.config().setTheme(Theme.DARK);
	      
	      extent=new ExtentReports();
	      
	      extent.attachReporter(sparkreporter);
	      
	      extent.setSystemInfo("Application", "opencart");
	      
	      extent.setSystemInfo("Module", "Admin");
	      
	      extent.setSystemInfo("SubModule","Customers");
	      
	      extent.setSystemInfo("Username", System.getProperty("user.name"));
	      
	      extent.setSystemInfo("Environment", "QA");
	      
	      String os=testContext.getCurrentXmlTest().getParameter("os");
	      extent.setSystemInfo("Operating System", os);
	      
	      String browser=testContext.getCurrentXmlTest().getParameter("browser");
	      extent.setSystemInfo("Browser", browser);
	      
	      List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
	      if(!includedGroups.isEmpty())
	      {
	    	  extent.setSystemInfo("Groups", includedGroups.toString());
	      }
	      
		  }

	      public void onTestStart(ITestResult result) 
	      {
	    	 

		   
		  }

		  public void onTestSuccess(ITestResult result) 
		  {
			  
			  test=extent.createTest(result.getTestClass().getName());
			  test.assignCategory(result.getMethod().getGroups());//to assign groups in report
			  test.log(Status.PASS, result.getName()+"got successfully executed");
		   
		  }

		  public void onTestFailure(ITestResult result) 
		  {
			  test=extent.createTest(result.getTestClass().getName());
			  test.assignCategory(result.getMethod().getGroups());
			  test.log(Status.FAIL, result.getName()+"got failed");
			  test.log(Status.INFO, result.getThrowable().getMessage());
			  
			  try 
			  {
				  //make webdriver static in baseclass
				  String imgpath=new BaseClass().captureScreen(result.getName());
				  test.addScreenCaptureFromPath(imgpath);
			  }
			  catch(Exception e)
			  {
				  e.getStackTrace();
			  }
		    
		  }

		  public void onTestSkipped(ITestResult result) 
		  {
		    test=extent.createTest(result.getTestClass().getName());
		    test.assignCategory(result.getMethod().getGroups());
		    
		    test.log(Status.SKIP, result.getName()+"got skipped");
		    test.log(Status.INFO, result.getThrowable().getMessage());
		  }

		  public void onTestFailedButWithinSuccessPercentage(ITestResult result)
		  {
		    
		  }

		  public void onTestFailedWithTimeout(ITestResult result)
		  {
		    onTestFailure(result);
		  }

		  public void onFinish(ITestContext context) 
		  {
		   extent.flush();
		   
		   //to open report automatically once execution completed
		   
		   String pathofExtentReport=System.getProperty("user.dir")+"\\reports\\"+reportName;
		   File extentReport=new File(pathofExtentReport);
		   
		   try
		   {
			   Desktop.getDesktop().browse(extentReport.toURI());
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		   
		   /*
		    *  To mail a report automatically (add java email depandency in pom.xml)
		    * 
		   try {
			   URL url =new 
				   URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+reportName);
		  //Create the email message
		  ImageHtmlEmail email=new ImageHtmlEmail();
		  
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		  email.setHostName("smtp.googleemail.com");
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("srikarthika.t@gamil.com","password"));
		  email.setSSLOnConnect(true);
		  email.setFrom("srikarthika.t@gamil.com"); //sender
		  email.setSubject("Test Results");
		  email.setMsg("Please find attached report");
		  email.addTo("karthingm92@gamil.com"); //receiver
		  email.attach(url,"extent report","please check the report...");
		  email.send(); //send the email)
		   }
		   catch(Exception e)
		   {
			e.printStackTrace();   
		   }
		   
		   
		    */
		  }
		}



