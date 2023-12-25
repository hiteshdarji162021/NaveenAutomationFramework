package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtility;
import com.qa.opencart.utils.ExcelUtility;

import io.qameta.allure.Issue;
import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtility eleUtil;
	private ExcelUtility excelUtil;

	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPassword = By.linkText("Forgotten Password");
	private By submit = By.xpath("//input[@value='Login']");
	private By register = By.linkText("Register");
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtility(driver);
		excelUtil = new ExcelUtility();
	}
	
	@Step("Getting login page title")
	public String getPageTitle() {
		String title=eleUtil.getTitlegetTitleAndFeath(AppConstants.DEFAULT_SHOT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println(title);
		return title;
	}
	@Step("Getting login page url")
	public String getPageUrl() {
		String url = eleUtil.geturlcontaiAndFeath(AppConstants.DEFAULT_SHOT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login Page url"+ url);
		return url;
	}
	@Step("checking forgot password link")
	public boolean isForgotPasswordExist() {
		//return driver.findElement(forgotPassword).isDisplayed();
		return eleUtil.waitElementvisible(forgotPassword, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	@Step("login with username: {0} and password: {1}")	
	public MyAccountPage doLogin(String user, String pass) {
		
		System.out.println("User Name is"+user +"Password is" +pass);
		eleUtil.waitElementvisible(username, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(user);
		eleUtil.sendKeys(password, pass);
		eleUtil.doClick(submit);
		return new MyAccountPage(driver);
	}
	
	@Step("navigate to Registartion page")
	public RegistartionPage navigateToRegister() {
		eleUtil.doClick(register);
		return new RegistartionPage(driver);
	}
	
	

}
