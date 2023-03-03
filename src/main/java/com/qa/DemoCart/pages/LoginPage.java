package com.qa.DemoCart.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.DemoCart.Utils.Constants;
import com.qa.DemoCart.Utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtils elementutil;

	// By locators
	private By Username = By.xpath("//input[@id='input-email']");
	private By password = By.xpath("//input[@id='input-password']");
	private By submit = By.xpath("//input[@value='Login']");
	private By forgotpwdlink = By.xpath("(//a[normalize-space()='Forgotten Password'])[1]");
	private By RegisterLink=By.xpath("//a[@class='list-group-item'][normalize-space()='Register']");
	private By loginErrorMessg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	
	/**
	 * constructor to initialize driver.
	 * 
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtils(driver);

	}

	@Step("Getting login page title")
	public String getLoginPageTitle() {
		return elementutil.waitfortitle(5, Constants.Login_Page_Title);
	}

	public String getLoginUrl() {
		return elementutil.getPageurl();
	}

	public boolean isforgotpwdlinkexist() {
		return elementutil.isElementdisplayed(forgotpwdlink);
	}
	
	@Step("Login with username {0} and password {1}")
	public AccountsPage doLogin(String username, String pwd) {
		elementutil.dosendkeys(Username, username);
		elementutil.doactionsendkeys(password, pwd);
		elementutil.clickelement(submit);
		return new AccountsPage(driver);

	}
	
	@Step("Login with username {0} and password {1}")
	public AccountsPage doLoginwithNegative(String username, String pwd) {
		elementutil.dosendkeys(Username, username);
		elementutil.doactionsendkeys(password, pwd);
		elementutil.clickelement(submit);
		return new AccountsPage(driver);

	}
	
	@Step("login with username : {0} and password : {1}")
	public boolean doLoginWrongData(String un, String pwd) {
		elementutil.dosendkeys(Username, un);
		elementutil.dosendkeys(password, pwd);
		elementutil.clickelement(submit);
		return elementutil.isElementdisplayed(loginErrorMessg);
	}
	
	
	public RegistrationPage clickRegisterPage()
	{
		elementutil.clickelement(RegisterLink);
		return new RegistrationPage(driver);
	}

}
