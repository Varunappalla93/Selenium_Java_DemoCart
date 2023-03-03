package com.qa.DemoCart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.qa.DemoCart.Listeners.TestAllureListener;
import com.qa.DemoCart.factory.DriverFactory;
import com.qa.DemoCart.pages.AccountsPage;
import com.qa.DemoCart.pages.LoginPage;
import com.qa.DemoCart.pages.ProductInfoPage;
import com.qa.DemoCart.pages.RegistrationPage;
import com.qa.DemoCart.pages.SearchResultPage;

@Listeners(TestAllureListener.class)
public class BaseTest {
	DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public LoginPage lp;
	public AccountsPage ap;
	public SearchResultPage srp;
	public ProductInfoPage prdinfo;
	public RegistrationPage rp;

	// to execute tests on which browser from testng.xml file.

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browsername) {
		df = new DriverFactory();
		prop = df.init_prop();
		prop.setProperty("browser", browsername);
		driver = df.init_driver(prop);
		lp = new LoginPage(driver);
	}

	/*
	 * // to run tests from tests individually.
	 * 
	 * @BeforeTest public void setup() { df = new DriverFactory(); prop =
	 * df.init_prop(); // prop.setProperty("browser", browsername); driver =
	 * df.init_driver(prop); lp = new LoginPage(driver); }
	 */

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
