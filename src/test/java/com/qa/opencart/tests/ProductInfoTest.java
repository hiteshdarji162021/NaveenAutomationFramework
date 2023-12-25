package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@DataProvider
	public Object[] getTestData() {
		return new Object[][] { { "MacBook", "MacBook Pro", 4 }, { "Macbook", "MacBook Air", 4 }, { "iMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30\"", 6 }, { "Samsung", "Samsung SyncMaster 941BW", 1 },
				{ "Samsung", "Samsung Galaxy Tab 10.1", 7 }

		};

	}

	@Test(dataProvider = "getTestData")
	public void ProductImageCountTest(String searchKey, String productName, int productImages) {

		searchpage = accpage.performSearch(searchKey);
		productInfoPage = searchpage.selectProduct(productName);
		int actualimageCount = productInfoPage.getProductImageCount();
		assertEquals(actualimageCount, productImages);
	}

	@Test
	public void productInfoTest() {
		searchpage = accpage.performSearch("MacBook");
		productInfoPage = searchpage.selectProduct("MacBook Pro");
		Map<String, String> actualProductInfo = productInfoPage.getProductInfo();
		softAssert.assertEquals(actualProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(actualProductInfo.get("productheader"), "MacBook Pro");
		softAssert.assertEquals(actualProductInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductInfo.get("Reward Points"), "800");
		System.out.println("Validated Successfully");
		softAssert.assertAll();
	}
	
	@DataProvider
	public Object[][] getDataForCart() {
		return new Object[][] {
			{"MacBook","MacBook Pro",1 },		
		};
	}

	@Test(dataProvider = "getDataForCart")
	public void addRecordInCart(String headerName, String ProductName, int qty) {
		searchpage = accpage.performSearch(headerName);
		productInfoPage = searchpage.selectProduct(ProductName);
		productInfoPage.enterQuntity(qty);
		String actualMessage = productInfoPage.addToCart();
		Assert.assertEquals(actualMessage, "Success: You have added "+ProductName+" to your shopping cart!");

	}

}
