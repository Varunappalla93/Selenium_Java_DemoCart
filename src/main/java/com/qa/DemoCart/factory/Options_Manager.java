package com.qa.DemoCart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Options_Manager {
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	
	public Options_Manager(Properties prop) {
		this.prop = prop;

	}
	
	public ChromeOptions getChromeOptions()
	{
		co=new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			co.addArguments("--incognito");
		}
		return co;
	}
	
	
	public FirefoxOptions getFireFoxOptions()
	{
		fo=new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			fo.addArguments("--incognito");
		}
		return fo;
	}
}
