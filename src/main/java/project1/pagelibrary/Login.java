package project1.pagelibrary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import project1.testbase.TestBase;

public class Login extends TestBase {
	public WebDriver driver;
	
	By username=By.xpath(".//input[@id='uEmail']");
	By password=By.xpath(".//*[@id='pass']");
	By loginButton=By.xpath(".//*[@id='login-box']/div[3]/div/button");
	
	public Login(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void loginToParcel() throws FileNotFoundException, IOException
	
	{
		propertiesloader();
		driver.findElement(username).sendKeys(prop.getProperty("username"));
		driver.findElement(password).sendKeys(prop.getProperty("password"));
		driver.findElement(loginButton).click();
	}
	
}
