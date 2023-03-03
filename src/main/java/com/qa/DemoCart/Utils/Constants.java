package com.qa.DemoCart.Utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String Login_Page_Title = "Account Login";
	public static final String Login_Page_url = "account/login";

	public static final String Account_Page_Title = "My Account";

	public static final String Account_Page_url = "https://naveenautomationlabs.com/opencart/index.php?route=account/account";

	public static final List<String> expected_Account_List = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");

	public static final String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";

	/***************************** Sheet Names ********************************/

	public static final String RegisterSheetName = "Register";

}
