package com.qa.DemoCart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.DemoCart.Utils.Constants;
import com.qa.DemoCart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 500: Design Login Page")
@Story("US 3424: develop a feature with all login page scenarios")
public class LoginPageTest extends BaseTest {

	@Description("Login Page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void LoginPageTitleTest() {
		String title = lp.getLoginPageTitle();
		System.out.println("Title is: " + title);
		Assert.assertEquals(title, Constants.Login_Page_Title);
	}

	@Test(priority = 2, enabled = false)
	public void LoginPageURLTest() {
		String url = lp.getLoginUrl();
		System.out.println("URL is: " + url);
		Assert.assertTrue(url.contains(Constants.Login_Page_url));
	}

	@Test(priority = 3)
	public void forgotpwdlinktest() {
		boolean forgotpwdlink = lp.isforgotpwdlinkexist();
		Assert.assertTrue(forgotpwdlink);
	}

	@Test(priority = 4)
	public void dologintest() {
		lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	
	// negative testing
	@DataProvider
	public Object[][] loginNegativeData()
	{
		return new Object[][] {{"test@gmail.com","test@123"},{" ","test@123"}};
	}
	
	
	
	@Test(priority = 0 ,dataProvider = "loginNegativeData")
	public void dologintestwithNegativeScenario(String un,String pwd) {
		lp.doLoginWrongData(un,pwd);

	}
}
