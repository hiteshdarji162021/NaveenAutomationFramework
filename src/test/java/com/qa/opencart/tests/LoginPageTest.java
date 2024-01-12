package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-100 Design login for open cart app")
@Story("US- login 101: design login feature")
public class LoginPageTest extends BaseTest {
	
	@Severity(SeverityLevel.CRITICAL)
	@Description(".....Getting title of the page.....")

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginpage.getPageTitle();
		Assert.assertEquals(title,AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}

	@Severity(SeverityLevel.BLOCKER)
	@Description(".....Getting URL of the page........")
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actualURL = loginpage.getPageUrl();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}

	@Severity(SeverityLevel.MINOR)
	@Description(".....checking forgotpassword link.....")
	@Test(priority = 3)
	public void forgotpwdLinkExistTest() {
		Assert.assertTrue(loginpage.isForgotPasswordExist());	
	}

	@Severity(SeverityLevel.NORMAL)
	@Description(".....user is able to login to app with correct username and password....")
	@Test(priority = 4)
	public void loginTest() {
		accpage= loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accpage.islogOutExist());	
			
	}

}
