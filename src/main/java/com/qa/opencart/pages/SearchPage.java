package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtility;

public class SearchPage {
	private WebDriver driver;
	private ElementUtility eleUtil;
	private By searchResult=By.cssSelector(".product-thumb img");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtility(driver);

	}
	public int getSearchProductCount() {
		int productCount= eleUtil.waitElementsvisible(searchResult, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Product Count is==>"+productCount);
		return productCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.waitElementvisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		System.out.println("Product Successfully selected");
		return new ProductInfoPage(driver);
	}
	
	

}
