package project1.pagelibrary;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import project1.testbase.TestBase;

public class SingleSiteProject extends TestBase 
{
	
	By projectName=By.xpath(".//*[@id='project_name']");
	By DateDraftDue=By.xpath(".//*[@id='draft_dateButton']");
	By DateFinalDue=By.xpath(".//*[@id='final_dateButton']");
	By propertyName=By.xpath(".//*[@id='divProperty']/table/tbody/tr[2]/td[2]/input[1]");
	By address=By.xpath(".//*[@id='prop_address1']");
	By city=By.xpath(".//*[@id='prop_city']");
	By state=By.xpath(".//*[@id='prop_state']");
	By createProject=By.xpath(".//*[@id='divButtons']/input[1]");
	By singleSiteProjectLink=By.xpath(".//*[@id='contentstart-content']/ul/li[1]/a");
	By draftCalenderDropdown=By.xpath("html/body/div[3]/div/div[1]/select");
	By finalCalenderDropdown=By.xpath("html/body/div[4]/div/div[1]/select");
	By projectNameLink=By.xpath("html/body/div[2]/div/div/div/div/div[1]/div[1]/div[1]/p[1]/small/a");
	
	String actualProjectName;
	
	int rowCount;
	WebDriver driver;
	 int RptType;
	 List<WebElement> projectNumberE;
	 WebElement projectNumber;
	 String projectNum;
	 String dat;
		String day;
	
	
	
	
	
	public SingleSiteProject(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickSingleSite()
	{
		driver.findElement(singleSiteProjectLink).click();
		
		
	}
	
    public void getreports (String templateId) throws InterruptedException
    {
    	
    	//((JavascriptExecutor) driver).executeScript("scroll(0,250);");
	
	 List<WebElement> tableRows=driver.findElements(By.xpath(".//*[@id='divReports']/table/tbody/tr"));
	int rowcount=tableRows.size();	
		
		driver.findElement(By.xpath(".//*[@id='express']")).click();
		String xpath1=".//*[@id='divReports']/table/tbody/tr[";
		String xpath2="]/td[1]";
		String xpath3="/parent::tr/following-sibling::tr[1]/child::td[1]/input";
		String xpath4="/parent::td/following-sibling::td[1]/div/input[2]";
		outer:
		for(int row=2;row<=rowcount;row++)
		{
			
			List<WebElement> colElements=driver.findElements(By.xpath(".//*[@id='divReports']/table/tbody/tr["+row+"]/td"));
			int colsize=colElements.size();
			((JavascriptExecutor) driver).executeScript("scroll(0,50);");
			for(int col=2;col<=colsize;col=col+2)
			{
				
				String combineReportId;
				try {
					combineReportId = driver.findElement(By.xpath(".//*[@id='divReports']/table/tbody/tr["+row+"]/td["+col+"]/div")).getAttribute("id");
				} catch (Exception e) {
					continue outer;
				}
				
				String finalReportId= combineReportId.replaceAll("[^0-9]", "");
				if(finalReportId.equals(templateId))
				{
					driver.findElement(By.xpath(".//*[@id='divReports']/table/tbody/tr["+row+"]/td["+col+"]/preceding-sibling::td[1]")).click();
					driver.findElement(By.xpath(".//*[@id='divReports']/table/tbody/tr["+row+"]/td["+col+"]/preceding-sibling::td[1]//following-sibling::td[1]/div/input[2]")).sendKeys("30");
					break outer;
				}
				
			}
			
			
			}
	
								
	  	}
    
	public void selectDate(String date0,By dateDraftDue2) throws InterruptedException
	{
		dat=date0;
		System.out.println(dat);
		
		Date d=new Date(dat);
		SimpleDateFormat df=new SimpleDateFormat("MMMM/dd/YYYY");
		String date1=df.format(d);
		System.out.println(date1);
		String MonthSplit[]=date1.split("/");
		String month=MonthSplit[0];
		String daysplit[]=dat.split("/");
		day=daysplit[1];
		
		
		
		if(dateDraftDue2.toString().equalsIgnoreCase(DateDraftDue.toString()))
		{
			driver.findElement(dateDraftDue2).click();
			Select monthSelect=new Select(driver.findElement(draftCalenderDropdown));
			monthSelect.selectByVisibleText(month);
			
			String xpath1="html/body/div[3]/table/tbody/tr[";
			String xpath2="]/td[";
			
			String xpath3="]";
				
			driver.findElement(dateDraftDue2).click();
			
		//	Thread.sleep(3000);
			alt:
			for(int row=1;row<=5;row++)
			{
				for(int col=1;col<=7;col++)
				{
									
					if(driver.findElement(By.xpath(xpath1+row+xpath2+col+xpath3)).getText().equalsIgnoreCase(day))
					{
						driver.findElement(By.xpath(xpath1+row+xpath2+col+xpath3)).click();
						break alt;
					}
				}
			}
		}
		
		else
		{
			driver.findElement(dateDraftDue2).click();
			Select monthSelect=new Select(driver.findElement(finalCalenderDropdown));
			monthSelect.selectByVisibleText(month);

			String xpath1="html/body/div[4]/table/tbody/tr[";
			String xpath2="]/td[";
			
			String xpath3="]";
				
			driver.findElement(dateDraftDue2).click();
		
			//Thread.sleep(3000);
			alt:
			for(int row=1;row<=5;row++)
			{
				for(int col=1;col<=7;col++)
				{
									
					if(driver.findElement(By.xpath(xpath1+row+xpath2+col+xpath3)).getText().equalsIgnoreCase(day))
					{
						driver.findElement(By.xpath(xpath1+row+xpath2+col+xpath3)).click();
						break alt;
					}
				}
			}
		}
				
		
		
       	
	}
	
	public void enterProjectname(String pname) throws FileNotFoundException, IOException
	{
		propertiesloader();
		int r=randomNumber();
		driver.findElement(projectName).sendKeys(pname+r);
		actualProjectName=pname+r;
		prop.setProperty("ProjectName", pname+r);
		prop.store(new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\java\\project1\\utils\\config.properties"),null);
	}
    
	public void enterAddress(String address1)
	{
		int r=randomNumber();
		driver.findElement(address).sendKeys(address1+r);
	}
    
	public void enterCity(String city1)
	{
		int r=randomNumber();
		driver.findElement(city).sendKeys(city1+r);
		
	}
	public void selectState(String state1)
	{
		Select states=new Select(driver.findElement(state));
		states.selectByVisibleText(state1);
		
	}
	
	public void enterPropertyName(String propname)
	{
		int r=randomNumber();
		driver.findElement(propertyName).sendKeys(propname+r);
	}
	
	public void clickCreateProjectbutton()
	{
		driver.findElement(createProject).click();
	}
	
	public boolean verifyProjectName()
	{
		if(driver.findElement(projectNameLink).getText().equalsIgnoreCase(actualProjectName))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	   public void enterDateDraftDue() throws InterruptedException, FileNotFoundException, IOException
		{
			   
		    clickSingleSite();
		    enterProjectname("NBL");
		    selectDate("4/4/2018",DateDraftDue);
		    selectDate("12/5/2018",DateFinalDue);
		    enterPropertyName("TestProperty");
			enterAddress("Add1");
		    enterCity("City");
		    selectState("California");
		    getreports("1585");
		    clickCreateProjectbutton();    
		}
	   
	   
}