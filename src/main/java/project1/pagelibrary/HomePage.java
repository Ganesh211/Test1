package project1.pagelibrary;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import project1.testbase.TestBase;

public class HomePage extends TestBase{
	
	WebDriver driver;
	
	By projectSearchbox=By.xpath(".//*[@id='gs_pName']");
	By enterProjectLink=By.xpath(".//*[@id='gridfindreports']/tbody/tr[2]/td[1]");
	By homeLink=By.xpath(".//*[@id='header-nav']/li[3]/a");
	By projectNameLink=By.xpath("html/body/div[2]/div/div/div/div/div[1]/div[1]/div[1]/p[1]/small/a");
	
 public HomePage(WebDriver driver)
 {
	this.driver=driver;
 }


public void enterProjectSearchbox() throws FileNotFoundException, IOException
{
	propertiesloader();
	driver.findElement(projectSearchbox).sendKeys(prop.getProperty("ProjectName"));
	driver.findElement(projectSearchbox).sendKeys(Keys.ENTER);
}

public void clickEnterProjectLink()
{
	driver.findElement(enterProjectLink).click();
	
}

public void clickOnHome()
{
	driver.findElement(homeLink).click();
}

public boolean verifyProjectName() throws FileNotFoundException, IOException
{
	propertiesloader();
	if(driver.findElement(projectNameLink).getText().equalsIgnoreCase(prop.getProperty("ProjectName")))
	{
		return true;
	}
	else
	{
		return false;
	}
}


public void searchProject() throws FileNotFoundException, IOException
{
	enterProjectSearchbox();
	explicitWaitVisible(enterProjectLink,driver);
	clickEnterProjectLink();
}



}
