package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtility;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtility eleUtil;
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By metaInfo = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By priceInfo = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By qty = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By message = By.xpath("//*[@class='alert alert-success alert-dismissible']");

	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtility(driver);
	}

	public String getProductHeaderValue() {
		String productHeaderValue = eleUtil.dogetText(productHeader);
		System.out.println(productHeaderValue);
		return productHeaderValue;
	}

	public int getProductImageCount() {
		int imagesCount = eleUtil.waitElementsvisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("product images count==>" + imagesCount);
		return imagesCount;
	}

	public Map<String, String> getProductInfo() {
		productInfoMap = new HashMap<>();
		String productHeader = getProductHeaderValue();
		productInfoMap.put("productheader", productHeader);
		getPrice();
		getTax();
		System.out.println(productInfoMap);
		return productInfoMap;
	}

	private void getPrice() {
		List<WebElement> metaList = eleUtil.getElements(metaInfo);
		for (WebElement e : metaList) {
			String meta = e.getText();
			String metaInfo[] = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
		}
	}

	private void getTax() {
		List<WebElement> taxInfo = eleUtil.getElements(priceInfo);
		String price = taxInfo.get(0).getText();
		String tax = taxInfo.get(1).getText();
		productInfoMap.put("productprice", price);
		String extaxValue = tax.split(":")[1].trim();
		productInfoMap.put("taxinfo", extaxValue);
	}

	public void enterQuntity(int Qty) {
		System.out.println("Quantity is==>" + Qty);
		eleUtil.sendKeys(qty, String.valueOf(Qty));
	}
	public String addToCart() {
		eleUtil.doClick(addToCart);	
		String successfulMsg= eleUtil.waitElementvisible(message, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		successfulMsg = successfulMsg.substring(0,successfulMsg.length()-1).replace("\n", "");
		System.out.println(successfulMsg);
		return successfulMsg;
	}

}