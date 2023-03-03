package com.qa.DemoCart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.DemoCart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	SoftAssert sa = new SoftAssert();

	@BeforeClass
	public void productInfosetup() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] searchData() {
		return new Object[][] { { "MacBook" }, { "iMac" }, { "iPhone" } };
	}

	// from searchData DataProvider
	@Test(dataProvider = "searchData")
	public void prodcountTest(String prodname) {
		srp = ap.doSearch(prodname);
		int results = srp.getresultscount();
		Assert.assertTrue(results > 0);
	}

	@Test
	public void prodInfoHeaderTest() {
		srp = ap.doSearch("MacBook");
		prdinfo = srp.selectProduct("MacBook Pro");
		Assert.assertEquals(prdinfo.getproductheadertext(), "MacBook Pro");

	}

	@Test
	public void prodInfoImagecountTest() {
		srp = ap.doSearch("MacBook");
		prdinfo = srp.selectProduct("MacBook Pro");
		Assert.assertTrue(prdinfo.getproductimagescount() == 4);
	}

	@Test
	public void getProdInfoTest() {
		srp = ap.doSearch("MacBook");
		prdinfo = srp.selectProduct("MacBook Pro");
		Map<String, String> actualProdmetadata = prdinfo.getproductInfo();
		actualProdmetadata.forEach((k, v) -> System.out.println(k + " : " + v));

		sa.assertEquals(actualProdmetadata.get("name"), "MacBook Pro");
		sa.assertEquals(actualProdmetadata.get("Brand"), "Apple");
		sa.assertEquals(actualProdmetadata.get("price"), "$2,000.00");

		sa.assertAll();
	}

	@Test
	public void selectQuantityTest() {
		srp = ap.doSearch("MacBook");
		prdinfo = srp.selectProduct("MacBook Pro");
		prdinfo.SelectQuantity("2");
	}

	@Test
	public void addtoCartTest() {
		srp = ap.doSearch("MacBook");
		prdinfo = srp.selectProduct("MacBook Pro");
		prdinfo.addtoCartButton();
	}

	@Test
	public void getsuccesstexttest() throws InterruptedException {
		srp = ap.doSearch("MacBook");
		prdinfo = srp.selectProduct("MacBook Pro");
		prdinfo.addtoCartButton();
		Thread.sleep(6000);
		String sucesstext = prdinfo.getsucessmessage();
		Assert.assertTrue(sucesstext.contains("Success: "));

	}

}