package project1.testscripts;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import project1.pagelibrary.HomePage;
import project1.pagelibrary.Login;
import project1.testbase.TestBase;

public class Test2 extends TestBase {
	
	Boolean verifyTest;
	@BeforeClass
	public void initSetup() throws IOException
	{
		setup();
	}
	
	@Test
	public void verifyProjectSearchAndOpen() throws FileNotFoundException, IOException, InterruptedException
	{
		implicitWait();
		Login login=new Login(driver);
		login.loginToParcel();
		HomePage hpobj=new HomePage(driver);
		
		hpobj.clickOnHome();
		Thread.sleep(3000);
		hpobj.searchProject();
		Thread.sleep(3000);
		verifyTest=hpobj.verifyProjectName();
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
