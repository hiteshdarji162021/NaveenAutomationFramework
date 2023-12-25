package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.MyAccountPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistartionPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginpage;
	protected MyAccountPage accpage;
	protected SearchPage searchpage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	protected RegistartionPage registartionPage;
	
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initprop();
		driver = df.initDriver(prop);
		loginpage = new  LoginPage(driver);
		softAssert = new SoftAssert();
		

	}

	@AfterTest
	public void testDown() {
		driver.quit();
	}

}
