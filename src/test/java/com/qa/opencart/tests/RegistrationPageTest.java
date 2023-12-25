package com.qa.opencart.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtility;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void regPageSetup() {
		registartionPage= loginpage.navigateToRegister();
	}
	
	@DataProvider
	public Object[][] getData() {
		Object regData[][]= ExcelUtility.getTestData(AppConstants.REG_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider ="getData")
	public void userRegTest(String firstname, String lastname, String telephone, String password,String subscribe) {	
		assertTrue(registartionPage.registerUser(firstname, lastname, telephone, password, subscribe)); 
	}

}
