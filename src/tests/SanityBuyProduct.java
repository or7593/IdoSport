package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.Main;
import pages.Product;
import utilites.GetDriver;
import utilites.Utilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;


public class SanityBuyProduct {

	// Global variables 
	// Add extent reports
	private ExtentReports extent;
	private ExtentTest myTest;
	private static String reportPaht = System.getProperty("user.dir") + "\\test-output\\flower.html";

	private WebDriver driver;
	private String baseUrl;
	
	
	//pages
	private Main main;
	private Product product;
	
	

	@BeforeClass
	public void beforeClass() {
		extent = new ExtentReports(reportPaht);
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\resources\\flower-extent-config.xml"));
		baseUrl = "https://www.flowers-chen.co.il/";
		driver = GetDriver.getDriver("chrome", baseUrl);
		
		main = new Main(driver);
		product = new Product(driver);

	}

	
	
	@BeforeMethod
	public void beforeMethod(Method method) throws IOException {
		myTest = extent.startTest(method.getName());
		myTest.log(LogStatus.INFO, "Starting test", "Start test");
	}
	
	
	


	
	/*  Prerequisite: getting into https://www.flowers-chen.co.il/
	 * 		Given: Client is in site 
	 * 		When: selecting product
	 *  	Then: Getting order with default date
	 */
	
	@Test(priority = 1, enabled = true, description = "verify date")
	public void verifyDateInOrder() throws InterruptedException, IOException {	
		main.selectFirstProduct();
		product.selectDate();
		Assert.assertTrue(product.goToCart());
	}
	
	
	@Test(priority = 1, enabled = true, description = "change to english")
	public void convertToEn() throws InterruptedException, IOException {	
		Assert.assertTrue(main.changeEN());
		main.changeHE();
		
	}
	
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			myTest.log(LogStatus.FAIL, "Test failed: " + result.getName());
			myTest.log(LogStatus.FAIL, "Test failed reason: " + result.getThrowable());
			myTest.log(LogStatus.FAIL, myTest.addScreenCapture(Utilities.takeScreenShot(driver)));
		}
		else {
			myTest.log(LogStatus.PASS, result.getName(), "Verify successful ");
			myTest.log(LogStatus.PASS, myTest.addScreenCapture(Utilities.takeScreenShot(driver)));

		}

		myTest.log(LogStatus.INFO, "Finish test", "Finish test ");
		extent.endTest(myTest);
	
		//return to base URL 
		//driver.get(baseUrl);
	}

	@AfterClass
	public void afterClass() {
		extent.flush();
		extent.close();
		driver.quit();

	}

	
}
