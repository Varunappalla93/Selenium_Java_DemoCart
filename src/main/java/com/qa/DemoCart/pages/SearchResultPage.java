package com.qa.DemoCart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.DemoCart.Utils.ElementUtils;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtils elementutil;

	private By searchResult = By.xpath("//div[@class='product-thumb']");
	private By searchResultItems = By.cssSelector("div[class='caption']  h4 a");

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtils(driver);
	}

	public int getresultscount() {
		return elementutil.getElements(searchResult).size();
	}

	public ProductInfoPage selectProduct(String ProductName) {
		List<WebElement> resultitemsList = elementutil.getElements(searchResultItems);
		System.out.println(
				"Total no of items displayed for :" + ProductName + " and its " + "size are " + resultitemsList.size());

		for (WebElement e : resultitemsList) {
			if (e.getText().equals(ProductName))
			{
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
