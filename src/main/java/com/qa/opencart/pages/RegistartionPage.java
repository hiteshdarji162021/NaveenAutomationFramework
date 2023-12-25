package com.qa.opencart.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtility;
import com.qa.opencart.utils.JavaScriptExecutorUtility;

public class RegistartionPage {

	private WebDriver driver;
	private ElementUtility eleUtil;
	private JavaScriptExecutorUtility jUtil;
	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By email = By.id("input-email");
	By telephone = By.id("input-telephone");
	By password = By.id("input-password");
	By confirmPassword = By.id("input-confirm");
	By SubscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	By SubscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");
	By agree = By.name("agree");
	By continueButton = By.xpath("//input[@value='Continue']");
	By logout = By.linkText("Logout");
	By register = By.linkText("Register");
	By successMsg = By.cssSelector("div#common-success h1");

	public RegistartionPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtility(driver);
		jUtil = new JavaScriptExecutorUtility(driver);
	}


	public String getRandomEmail() {
		Random random = new Random();
		//String email = "automation" + random.nextInt(1000) + "@gmail.com";
		
		String email = "automation" + System.currentTimeMillis() + "@gmail.com";
		//automation12121212121@gmail.com
		//automation232323232323@gmail.com
		
		return email;
	}

	public boolean registerUser(String firstname, String lastname, String telephone, String password,String subscribe) {

		eleUtil.waitElementvisible(this.firstName, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstname);
		eleUtil.sendKeys(this.lastName, lastname);
		eleUtil.sendKeys(this.email, getRandomEmail());
		eleUtil.sendKeys(this.telephone, telephone);
		eleUtil.sendKeys(this.password, password);
		eleUtil.sendKeys(this.confirmPassword, password);

		if (subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doActionsClick(SubscribeYes);
		} else {
			eleUtil.doActionsClick(SubscribeNo);
		}
		jUtil.clickElementByJS(eleUtil.getElement(agree));	
		jUtil.clickElementByJS(eleUtil.getElement(continueButton));
	

		String succssMsg = eleUtil.waitElementvisible(successMsg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();

		if (succssMsg.contains(AppConstants.REG_SUCCESS_MSG)) {
			eleUtil.doClick(logout);
			eleUtil.doClick(register);
			return true;
		}
		return false;
	}

}
