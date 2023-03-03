package com.qa.DemoCart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.DemoCart.Utils.ElementUtils;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtils elementutil;

	private By productHeader = By.cssSelector("div[class='col-sm-4'] h1");
	private By productImages = By.cssSelector("ul[class='thumbnails'] li a img");
	private By productmetadata = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productpricedata = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By productQuantity = By.xpath("//input[@id='input-quantity']");
	private By AddtoCartbutton = By.xpath("//button[@id='button-cart']");
	private By successtext = By.cssSelector(".alert.alert-success.alert-dismissible");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtils(driver);
	}

	public String getproductheadertext() {
		String headertext = elementutil.dogetText(productHeader);
		return headertext;
	}

	public int getproductimagescount() {
		return elementutil.getElements(productImages).size();
	}

	/**
	 * This method will collect product info in form of key, value pairs as HashMap.
	 * 
	 * @return
	 */
	public Map<String, String> getproductInfo() {
		Map<String, String> prodinfoMap = new HashMap<String, String>();
		
		// prod header
		prodinfoMap.put("name", getproductheadertext());

		List<WebElement> metaDataList = elementutil.getElements(productmetadata);
		System.out.println("Total product metaData " + metaDataList.size());

		// metaData
		for (WebElement e : metaDataList) {
			String[] meta = e.getText().split(":");
			String metakey = meta[0].trim();
			String metavalue = meta[1].trim();

			prodinfoMap.put(metakey, metavalue);
		}

		// price
		List<WebElement> pricelist = elementutil.getElements(productpricedata);
		String price = pricelist.get(0).getText();
		String exprice = pricelist.get(1).getText();
		prodinfoMap.put("price", price);
		prodinfoMap.put("exTaxprice", exprice);

		return prodinfoMap;

	}

	public void SelectQuantity(String qty) {
		elementutil.dosendkeys(productQuantity, qty);
	}

	public void addtoCartButton() {
		elementutil.clickelement(AddtoCartbutton);
	}

	public String getsucessmessage() {
		return elementutil.dogetText(successtext);
	}

	
}
