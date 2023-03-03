package com.qa.DemoCart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.DemoCart.Utils.Constants;
import com.qa.DemoCart.Utils.ExcelUtil;
import com.qa.DemoCart.base.BaseTest;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void setupRegister() {
		rp = lp.clickRegisterPage();
	}

	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.RegisterSheetName);
		return regData;
	}

	public String getRandomNumber() {
		Random randomGenerator = new Random();
		String email = "testautomation" + randomGenerator.nextInt(1000) + "@gmail.com";
		return email;
	}

	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName,
									String telephone, 
									String password, String subsribe) {
		
		Assert.assertTrue
						(rp.accountRegistration(firstName,  lastName, 
								getRandomNumber(),  telephone, 
				 				password,  subsribe));
	}

}
