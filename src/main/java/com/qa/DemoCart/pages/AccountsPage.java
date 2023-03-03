package com.qa.DemoCart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.DemoCart.Utils.Constants;
import com.qa.DemoCart.Utils.ElementUtils;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtils elementutil;

	private By accountSections = By.cssSelector("div#content h2");
	private By logo = By.cssSelector("div#logo img");
	private By logoutlink = By.linkText("Logout");
	private By searchfield = By.name("search");
	private By searchbutton = By.cssSelector(".input-group-btn");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtils(driver);
	}

	public String getaccountpagetitle() {
		return elementutil.waitfortitle(5, Constants.Account_Page_Title);

	}

	public String getAccountUrl() {
		return elementutil.getPageurl();
	}

	public boolean getAccountPageLogo() {
		return elementutil.isElementdisplayed(logo);

	}

	public List<String> getAccountSectionslist() {

		List<String> accsecvalList = new ArrayList<String>();
		List<WebElement> accseclist = elementutil.WaitforvisibilityofElements(accountSections, 5);

		for (WebElement e : accseclist) {
			accsecvalList.add(e.getText());
		}
		Collections.sort(accsecvalList);
		return accsecvalList;

	}

	public boolean islogoutexist() {
		return elementutil.isElementdisplayed(logoutlink);
	}

	// search method
	public SearchResultPage doSearch(String productname) {
		System.out.println("Searching the product "+productname);
		elementutil.dosendkeys(searchfield, productname);
		elementutil.clickelement(searchbutton);
		return new SearchResultPage(driver);
	}

}
