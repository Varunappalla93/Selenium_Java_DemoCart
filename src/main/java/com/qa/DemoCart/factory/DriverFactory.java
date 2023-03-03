package com.qa.DemoCart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.mongodb.MapReduceCommand.OutputType;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Driver Factory class for initializing driver and returns the driver
 * reference.
 * 
 * @author VARUN
 *
 */

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	public static String highlight_ele = null;
	private Options_Manager om;

	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * 
	 * @param browser
	 * @return this method will return webdriver reference.
	 */

	public WebDriver init_driver(Properties prop) {

		// jsutil flash
		highlight_ele = prop.getProperty("highlight");

		// OptionsManager
		om = new Options_Manager(prop);

		String browser = prop.getProperty("browser").trim();
		System.out.println("Browser name is " + browser);

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver(om.getChromeOptions());

			// thread local
			tldriver.set(new ChromeDriver(om.getChromeOptions()));

		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();

//			driver = new FirefoxDriver(om.getFireFoxOptions());

			// thread local
			tldriver.set(new FirefoxDriver(om.getFireFoxOptions()));

		} else if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Please pass the correct browser name " + browser);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}

	// get WebDriver
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}

	/**
	 * 
	 * @return prop which loads fis from config.properties file and returns prop.
	 */

	public Properties init_prop() {
		FileInputStream fis = null;
		prop = new Properties();

		String env = System.getProperty("env");

		if (env == null) {
			System.out.println("Running on prod env ");
			try {
				fis = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("Running on env " + env);
			try {

				switch (env) {
				case "qa":
					fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;

				case "staging":
					fis = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}
	
	/*
	 * Take screenshot
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
}