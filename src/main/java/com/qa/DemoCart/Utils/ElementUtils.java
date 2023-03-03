package com.qa.DemoCart.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.DemoCart.factory.DriverFactory;

public class ElementUtils {

	private WebDriver driver;
	private JavaScriptUtil jsutil;
	
	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		jsutil=new JavaScriptUtil(driver);
	}

	// get element method and flash according to highlight value from config file.
	public WebElement getelement2(By locator) {
		
		WebElement element = driver.findElement(locator);
		
		if (Boolean.parseBoolean(DriverFactory.highlight_ele))
		{
			jsutil.flash(element);
		
		}
		return element;
//		return driver.findElement(locator);
	}

	
	// send keys
	public void dosendkeys(By locator, String value) {
		WebElement element = getelement2(locator);
		element.clear();
		element.sendKeys(value);
	}

	// click
	public void clickelement(By locator) {
		getelement2(locator).click();
	}

	// get text
	public String dogetText(By locator) {
		String text = getelement2(locator).getText();
		return text;
	}

	// is element displayed
	public boolean isElementdisplayed(By locator) {
		boolean element = getelement2(locator).isDisplayed();
		return element;
	}

	// get elements method
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	// get text of elements using for loop
	public List<String> getElementsTextList(By locator) {
		List<String> eletextlist = new ArrayList<String>(); // create String Arraylist

		List<WebElement> list = getElements(locator);
		for (int i = 0; i < list.size(); i++) {
			String linktext = list.get(i).getText();
			if (!linktext.isEmpty()) {
				eletextlist.add(linktext + " and index is " + i); // add links text into eletextlist String Arraylist
			}

		}
		return eletextlist;
	}

	// get attribute of elements using for loop
	public List<String> getElementAttributeList(By locator, String attrname) {
		List<String> attrtextlist = new ArrayList<String>();

		List<WebElement> elelist = getElements(locator);

		for (WebElement e : elelist) {
			attrtextlist.add(e.getAttribute(attrname));
		}
		return attrtextlist;
	}

	// switchwindowandgetitle method
	public String switchwindowandgetitle(String windowid) {
		driver.switchTo().window(windowid);
		String title = driver.getTitle();
		return title;

	}

	/**************** Select dropdown utils ******************/

	// select from dropdown by index
	public void Selectfromdropdownbyindex(By locator, int index) {
		Select selectoption = new Select(getelement2(locator));
		selectoption.selectByIndex(index);
	}

	// select from dropdown by visible text
	public void Selectfromdropdownbyvisibletext(By locator, String visibletext) {
		Select selectoption = new Select(getelement2(locator));
		selectoption.selectByVisibleText(visibletext);
	}

	// select from dropdown by value
	public void Selectfromdropdownbyvalue(By locator, String value) {
		Select selectoption = new Select(getelement2(locator));
		selectoption.selectByValue(value);
	}

	// select using getOptions method
	public void doSelectusingoptions(By locator, String value) {
		Select sel = new Select(getelement2(locator));
		List<WebElement> dropdownoptions = sel.getOptions();
		System.out.println(dropdownoptions.size());

		for (WebElement e : dropdownoptions) {
			String dropdowntext = e.getText();
			System.out.println(dropdowntext);

			if (dropdowntext.equals(value)) {
				e.click();
				break;
			}

		}
	}

	// select using getdropdownwithoutselect method
	public void getdropdownwithoutselect(By locator, String text) {
		List<WebElement> list = getElements(locator);
		for (WebElement e : list) {
			System.out.println(e.getText());
			if (e.getText().equals(text)) {
				e.click();
				break;
			}
		}
	}

	// click right click options method
	public void SelectRightClickOption(By locator, String text) {
		List<WebElement> contextoptions = getElements(locator);

		for (WebElement e : contextoptions) {
			String optiontext = e.getText();
			if (optiontext.equals(text)) {
				e.click();
				break;
			}

		}

	}

	// handle alert method
	public String handlealert() {
		Alert alertmsg = driver.switchTo().alert();
		String alerttext = alertmsg.getText();
		alertmsg.accept();
		return alerttext;

	}

	// doactionsendkeys method
	public void doactionsendkeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getelement2(locator), value).perform();
	}

	// doactionsclick method
	public void doactionclick(By locator) {
		Actions act = new Actions(driver);
		act.click(getelement2(locator)).perform();
	}

	// doactionsmovetoelement method
	public void doactionsmovetoelement(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getelement2(locator)).perform();

	}

	// movetoelementandclickonelementafterhovering
	public void movetoelementandclickonelementafterhovering(By locator) {
		doactionsmovetoelement(locator);
		getelement2(locator).click();
	}

	// wait for title and get title
	public String waitfortitle(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	// wait for url
	public Boolean waitforpageurl(int timeout, String url) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(url));
	}

	// get page url
	public String getPageurl() {
		return driver.getCurrentUrl();
	}

	/************************* Wait Utils *************************/

	// wait until all the elements are visible on the webpage.
	public List<WebElement> WaitforvisibilityofElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	// wait for presence of element.
	public WebElement waitforElementPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	// wait for visibility of element by locator generic method.
	public WebElement waitforElementVisibleBylocator(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	// wait for visibility of Web Element generic method.
	public WebElement waitforElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOf(getelement2(locator)));

	}

	// wait for all elements visibility
	public List<WebElement> waitForAllElementsVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

/******************************Streams*********************************************/
	
	// print elements text using stream
	public void printElementsusingStream(By locator, int timeout) {
		waitForAllElementsVisibility(locator, timeout).stream().forEach(e -> System.out.println(e.getText()));
	}

	// print elementslist using stream and filter with map
	public List<String> getElementsListwithTextusingStream(By locator, int timeout, String filterval) 
	{
		return waitForAllElementsVisibility(locator, timeout).stream()
		.filter(e ->e.getText().contains(filterval)).map(e->e.getText()).collect(Collectors.toList());
	}


	public void printListElements(List<String> elelist)
	{
		elelist.forEach(ele->System.out.println(ele));
		
	}
	
	// print elementslist using stream and filter without map
	public List<WebElement> getElementsListusingStream(By locator, int timeout, String filterval) 
	{
		return waitForAllElementsVisibility(locator, timeout).stream()
		.filter(e ->e.getText().contains(filterval)).collect(Collectors.toList());
	}
	
	/***************************************************************************/

	// get text of all elements
	public List<String> getElementsTextList(By locator, int timeout) {
		List<String> eleTextlist = new ArrayList<String>();
		List<WebElement> elelist = waitForAllElementsVisibility(locator, 4);

		for (WebElement e : elelist) {
			if (!e.getText().isEmpty()) {
				eleTextlist.add(e.getText());
			}

		}
		return eleTextlist;

	}

	// wait for element to be clickable
	public WebElement WaitForElementtoBeClickable(By loc, int timeout)

	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(loc));

	}

	// click on element when enabled
	public void clickWhenReady(By loc, int timeout) {
		WaitForElementtoBeClickable(loc, timeout).click();
	}

	/**************** Alerts *************************/

	// wait for alert generic method
	public Alert waitforAlertPresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	// get alert text generic method
	public String getAlertText(int timeout) {
		Alert alert_text = waitforAlertPresent(timeout);
		return alert_text.getText();
	}

	// accept alert text generic method
	public void AcceptAlert(int timeout) {
		Alert alert = waitforAlertPresent(timeout);
		alert.accept();
	}

	// dismiss alert text generic method
	public void DismisstAlert(int timeout) {
		Alert alert = waitforAlertPresent(timeout);
		alert.dismiss();
	}

	// wait for title generic method
	public String WaitForTitle(int timeout, String title, int polltime) {
		WebDriverWait wait = new WebDriverWait(driver, timeout, polltime);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	// wait for title contains generic method
	public String WaitForTitleContains(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	// wait for url generic method
	public String WaitForurlcontains(int timeout, String url) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlContains(url));
		return driver.getCurrentUrl();
	}

	/*************************Frames********************/
	
	// wait for frame using By locator.
	public void waitForFrame(int timeout, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	// wait for frame using id or name.
	public void waitForFrame(int timeout, String idorname) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idorname));
	}

	// wait for frame using index
	public void waitForFrame(int timeout, int index) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	// wait for frame using web element
	public void waitForFrame(int timeout, WebElement frameele) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameele));
	}

	/******************** Fluent Wait *********************/

	// waitforelementwithFluentWait generic method
	public WebElement waitforelementwithFluentWait(int timeout, long pollingtime, By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingtime)).ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// waitforAlertwithFluentWait generic method
	public Alert waitforelementwithFluentWait(int timeout, long pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingtime)).ignoring(NoAlertPresentException.class);

		return wait.until(ExpectedConditions.alertIsPresent());
	}

	// waitforFramewithFluentWait generic method
	public WebDriver waitforFramewithFluentWait(int timeout, long pollingtime, By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingtime)).ignoring(NoSuchFrameException.class);

		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

}