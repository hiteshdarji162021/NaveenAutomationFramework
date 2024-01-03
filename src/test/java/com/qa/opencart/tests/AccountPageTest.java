package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());

	}

	@Test
	public void getAccPageTitleTest() {
		String title = accpage.getAccountTitle();
		Assert.assertEquals(title, AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
	}

	@Test
	public void getAccPageURLTest() {
		String url = accpage.getAccountURL();
		Assert.assertTrue(url.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE));
	}

	@Test
	public void accPageHeadersTest() {
		List<String> accPageHeaderList = accpage.getAccountPageHeader();
		System.out.println(accPageHeaderList);
		Assert.assertEquals(accPageHeaderList.size(), AppConstants.ACCOUNT_PAGE_HEADER_COUNT);
	}

	@Test
	public void accPageHeadersValueTest() {
		List<String> accPageHeaderList = accpage.getAccountPageHeader();
		System.out.println("Actual Page Header List==>" + accPageHeaderList);
		System.out.println("Expected Page Header List==>" + AppConstants.ACCOUNT_PAGE_HEADER_VALUE);
		Assert.assertEquals(accPageHeaderList, AppConstants.ACCOUNT_PAGE_HEADER_VALUE);
	}
	
	@DataProvider
	public Object getProductData() {
		
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"},
			
		};
		
	}

	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchpage = accpage.performSearch(searchKey);
		assertTrue(searchpage.getSearchProductCount() > 0);
	}

	@DataProvider
	public Object getProductTestData() {
		
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"}
			
		};
		
	}
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String SearchKey, String productName) {
		searchpage = accpage.performSearch(SearchKey);
		if (searchpage.getSearchProductCount() > 0) {
			productInfoPage =	searchpage.selectProduct(productName); 
			String actProductHeader= productInfoPage.getProductHeaderValue();
			assertEquals(actProductHeader, productName);
		}
	}

}
