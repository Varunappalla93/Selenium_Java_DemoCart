package com.qa.DemoCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.DemoCart.Utils.Constants;
import com.qa.DemoCart.Utils.ElementUtils;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtils elementutil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");

	private By agreeCheckBox = By.xpath("//input[@name='agree']");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By sucessMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtils(driver);
	}

	public boolean accountRegistration(String firstName, String lastName, String email, String telephone,
			String password, String subsribe) {
		elementutil.dosendkeys(this.firstName, firstName);
		elementutil.dosendkeys(this.lastName, lastName);
		elementutil.dosendkeys(this.email, email);
		elementutil.dosendkeys(this.telephone, telephone);
		elementutil.dosendkeys(this.password, password);
		elementutil.dosendkeys(this.confirmpassword, password);
		if (subsribe.equals("yes")) {
			elementutil.clickelement(subscribeYes);
		} else {
			elementutil.clickelement(subscribeNo);
		}

		elementutil.clickelement(agreeCheckBox);
		elementutil.clickelement(continueButton);
		String mesg = elementutil.waitforElementVisible(sucessMessg, 5).getText();
		System.out.println("account creation : " + mesg);
		if (mesg.contains(Constants.REGISTER_SUCCESS_MESSG)) {
			elementutil.clickelement(logoutLink);
			elementutil.clickelement(registerLink);
			return true;
		}
		return false;
	}

}
