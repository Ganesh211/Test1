package project1.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	
	public  Properties prop;
	public File f;
	public FileInputStream fis;
	public WebDriver driver;
	public int rNumber;
	String date;
	
	public void setup() throws IOException
	{
		propertiesloader();
		selectBrowser(prop.getProperty("driver"));
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	public void propertiesloader() throws IOException,FileNotFoundException{
		prop=new Properties();
		f=new File(System.getProperty("user.dir")+"\\src\\main\\java\\project1\\utils\\config.properties");
		fis= new FileInputStream(f);
		prop.load(fis);

	}
	public int randomNumber()
	{
		Random r=new Random();
		rNumber= r.nextInt(1000);
		
		return rNumber;
		
	}
	
	public String timeStamp()
	{
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return timeStamp;
	}
	
	public void implicitWait()
	{
	  driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	
	public void explicitWaitVisible(By element,WebDriver driver)
	{
		 new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(element) );
	}
	
		
	public WebDriver selectBrowser(String browser){
		
		
		if(browser.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			return driver;
		}
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "E:/chromedriver_win32/chromedriver.exe");
			driver=new ChromeDriver();
			return driver;
		}
		return null;
		
		
		
	}
	
	public void exitBrowser()
	{
		driver.close();
	}
	
	
}
