package project1.testscripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import project1.pagelibrary.HomePage;
import project1.pagelibrary.Login;
import project1.pagelibrary.SingleSiteProject;
import project1.testbase.TestBase;

public class Test1 extends TestBase {
	
	Boolean verifyTest;
	@BeforeClass
	public void initSetup() throws IOException
	{
		setup();
	}
	
	@Test
	public void verifyCreateProject() throws IOException, InterruptedException
	{
		
		
		implicitWait();
		Login login=new Login(driver);
		login.loginToParcel();
		SingleSiteProject p=new SingleSiteProject(driver);
		p.enterDateDraftDue();
		verifyTest=p.verifyProjectName();
		if(verifyTest)
		{
			Assert.assertTrue(true, "Project created successfully");
		}
		else
		{
			Assert.assertTrue(false, "Project not created ");
		}
		
	}

	@AfterClass
	public void tearDown()
	{
		exitBrowser();
	}
}
