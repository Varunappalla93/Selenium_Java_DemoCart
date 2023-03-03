package com.qa.DemoCart.tests;

import java.util.Collections;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.DemoCart.Utils.Constants;
import com.qa.DemoCart.Utils.ErrorClass;
import com.qa.DemoCart.base.BaseTest;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accpagesetup() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void AccountPageTitleTest() {
		System.out.println(ap.getaccountpagetitle());
		Assert.assertEquals(ap.getaccountpagetitle(), Constants.Account_Page_Title, ErrorClass.Acc_Page_title_error);
	}

	@Test
	public void AccountPageHeadertest() {
		System.out.println(ap.getAccountUrl());
		Assert.assertEquals(ap.getAccountUrl(), Constants.Account_Page_url);
	}

	@Test
	public void AccountpageLogo() {
		System.out.println(ap.getAccountPageLogo());
		Assert.assertTrue(ap.getAccountPageLogo());
	}

	@Test
	public void AccountSectionsListTest() {
		List<String> secList = ap.getAccountSectionslist();
		secList.stream().forEach(e -> System.out.println(e));
		Collections.sort(Constants.expected_Account_List);
		Assert.assertEquals(secList, Constants.expected_Account_List);

	}

	@Test
	public void isLogoutlinkTest() {
		Assert.assertTrue(ap.islogoutexist());

	}

}
