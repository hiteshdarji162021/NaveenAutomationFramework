package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtility;

public class MyAccountPage {

	private WebDriver driver;
	private ElementUtility eleUtil;
	private By logout = By.linkText("Logout");
	private By accsHeader = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By SearchIcon = By.cssSelector("#search button");

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtility(driver);
	}

	public String getAccountTitle() {
		String title = eleUtil.getTitlegetTitleAndFeath(AppConstants.DEFAULT_SHOT_TIME_OUT,
				AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
		System.out.println("Account page Title is==>" + title);
		return title;
	}

	public String getAccountURL() {
		String url = eleUtil.geturlcontaiAndFeath(AppConstants.DEFAULT_SHOT_TIME_OUT,
				AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE);
		System.out.println("Account page Title is==>" + url);
		return url;
	}

	public boolean islogOutExist() {
		return eleUtil.waitElementvisible(logout, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();

	}

	public boolean issearchExist() {
		return eleUtil.waitElementvisible(search, AppConstants.DEFAULT_SHOT_TIME_OUT).isDisplayed();
	}

	public List<String> getAccountPageHeader() {

		List<WebElement> accHeaderList = eleUtil.waitElementsvisible(accsHeader, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> accHeaderValueList = new ArrayList<>();
		for (WebElement ele : accHeaderList) {
			String text = ele.getText();
			accHeaderValueList.add(text);
		}
		return accHeaderValueList;

	}

	public SearchPage performSearch(String searchKey) {
		if (issearchExist()) {
			eleUtil.sendKeys(search, searchKey);;
			eleUtil.doClick(SearchIcon);
			System.out.println("Search result shows correctly");
			return new SearchPage(driver);
		} else {
			System.out.println("Search field is not present in page");
			return null;
		}
	}

}
