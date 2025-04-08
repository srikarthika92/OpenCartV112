package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass

{
	public static WebDriver driver;
	public Logger logger;
	public Properties prop;
	
	@BeforeClass(groups={"sanity","master"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws IOException
	{
	logger=LogManager.getLogger(this.getClass());
	
    String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties";
    FileInputStream fis=new FileInputStream(filePath);
    prop=new Properties();
    prop.load(fis);
    
    if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
    {
    	DesiredCapabilities cap=new DesiredCapabilities();
    	
    	//os
    	if(os.equalsIgnoreCase("windows"))
    	{
    		cap.setPlatform(Platform.WIN10);
    	}
    	else if(os.equalsIgnoreCase("linux"))
    	{
    		cap.setPlatform(Platform.LINUX);
    	}
    	else
    	{
    		System.out.println("No matching os found");
    		return;
    	}
    	//browser // we can use switch also
    	switch(br.toLowerCase())
    	{
    	case "chrome": cap.setBrowserName("chrome"); break;
    	
    	case "edge": cap.setBrowserName("edge"); break;
    	
    	case "firefox": cap.setBrowserName("firefox"); break;
    	
    	default: System.out.println("no matching browser found"); return;
    	}
    	
    	//driver=new RemoteWebDriver(URI.create("http://192.168.1.5:4444/wd/hub").toURL(),cap);
    	
    	driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), cap);
    	
    }
    		
    if(prop.getProperty("execution_env").equalsIgnoreCase("local"))		
    {		
	switch(br.toLowerCase())
	{
	case "chrome": driver=new ChromeDriver(); break;
	case "firefox": driver=new FirefoxDriver(); break;
	case "edge": driver=new EdgeDriver(); break;
	case "ie": driver=new InternetExplorerDriver(); break;
	default: System.out.println("Invalid browser name"); 
	return; //return will come out of the switch case stop the execution if browser name is invalid
		
	}}
	
	driver.manage().deleteAllCookies();  
    driver.navigate().refresh();         // Refresh to avoid stale elements  
	driver.manage().window().maximize();
	driver.get(prop.getProperty("url"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	@AfterClass(groups={"sanity","master"})
	public void tearDown()
	{
		driver.quit();
	}
	public String randomString()
	{
		RandomStringGenerator generator=new RandomStringGenerator.Builder().withinRange('a','z').build();
		
		String generatedString= generator.generate(10);
		return generatedString;
	}

	public String randomNumber()
	{
		RandomStringGenerator generator=new RandomStringGenerator.Builder().withinRange('0','9').build();
		String generatedNumber= generator.generate(10);
		return generatedNumber;
		
	}
	public String randomAlphaNumeric()
	{
	RandomStringGenerator generatorstr=new RandomStringGenerator.Builder().withinRange('a','z').build();
		
		String generatedString= generatorstr.generate(10);
		
		RandomStringGenerator generatorno=new RandomStringGenerator.Builder().withinRange('0','9').build();
		String generatedNumber= generatorno.generate(10);
		
		
		return generatedString+"@"+generatedNumber;
		
	}

  public String captureScreen(String tname)
  {
	  String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	  
	  TakesScreenshot takescreenshot=(TakesScreenshot) driver;
	  File sourcefile=takescreenshot.getScreenshotAs(OutputType.FILE);
	  
	  String targetfilepath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"-"+timestamp+".png";
	  File targetfile=new File(targetfilepath);
	  
	  sourcefile.renameTo(targetfile);
	  
	  return targetfilepath;
  }
}
