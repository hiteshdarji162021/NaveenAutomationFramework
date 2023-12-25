package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtility {

	WebDriver driver;
	JavaScriptExecutorUtility jutil;

	public ElementUtility(WebDriver driver) {
		this.driver = driver;
		jutil = new JavaScriptExecutorUtility(driver);
	}

	public WebElement getElement(By Locator) {

		WebElement element = driver.findElement(Locator);
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			jutil.flash(element);
		}
		return element;
	}

	public WebElement getElement(By Locator, int timeOut) {
		return waitElementvisible(Locator, timeOut);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void sendKeys(By Locator, String name) {
		getElement(Locator).clear();
		getElement(Locator).sendKeys(name);
	}

	public void doClick(By Locator) {
		getElement(Locator).click();
	}

	public String dogetText(By Locator) {
		return getElement(Locator).getText();
	}

	public boolean elementIsDisplayed(By Locator) {
		return getElement(Locator).isDisplayed();
	}

	public String elementAttribute(By locator, String attribute) {
		return getElement(locator).getAttribute(attribute);
	}

	public void getelementsAttribute(By locator, String attributename) {
		List<WebElement> lst = driver.findElements(locator);
		for (WebElement ele : lst) {
			String attname = ele.getAttribute(attributename);
			System.out.println(attname);
		}
	}

	public List<WebElement> getlistElements(By locator) {
		return driver.findElements(locator);
	}

	public List<String> getElementText(By locator) {
		List<String> lsttext = new ArrayList<>();
		List<WebElement> lst = getElements(locator);
		for (WebElement element : lst) {
			String value = element.getText();
			if (value.length() > 0) {
				lsttext.add(value);
			}
		}
		return lsttext;
	}

	public int getElementsSize(By locator) {
		return driver.findElements(locator).size();

	}

	// ************************Select Dropdown utility*********
	public void getdropdownvaluebyindex(By locator, int index) {
		Select sel = new Select(getElement(locator));
		sel.selectByIndex(index);
	}

	public void getdropdownvaluebyValue(By locator, String value) {
		Select sel = new Select(getElement(locator));
		sel.selectByValue("India");
	}

	public void getdropdownvaluebytext(By locator, String text) {
		Select sel = new Select(getElement(locator));
		sel.selectByValue("Iceland");
	}

	public List<WebElement> getdropdownoptionslist(By locator) {
		Select sel = new Select(getElement(locator));
		return sel.getOptions();
	}

	public int dropdownoptionsize(By locator) {
		int size = getdropdownoptionslist(locator).size();
		System.out.println("Total Dropdown value is" + size);
		return size;
	}

	public List<String> getdropdownoptionsinText(By Locator) {
		List<String> lststr = new ArrayList<>();
		Select sel = new Select(getElement(Locator));
		List<WebElement> lstdropdown = sel.getOptions();

		for (WebElement ele : lstdropdown) {
			String textdropdown = ele.getText();
			lststr.add(textdropdown);
		}
		return lststr;
	}

	public void getdropdownoptionsselectValue(By Locator, String expvalue) {

		List<WebElement> element = getdropdownoptionslist(Locator);

		for (WebElement ele : element) {
			String textdropdown = ele.getText();
			System.out.println(textdropdown);
			if (textdropdown.equals(expvalue)) {
				ele.click();
				break;
			}
		}

	}

	public void dosearch(By locator, String searchedtext) {
		List<WebElement> lstlang = driver.findElements(locator);

		System.out.println(lstlang.size());

		for (WebElement ele : lstlang) {
			String text = ele.getText();
			System.out.println(text);
			if (text.equals(searchedtext)) {
				ele.click();
				break;
			}
		}
	}

	public void doActionssendkeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value);
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator));
	}

	// ********************Wait untils*****************************//
	public WebElement witElementPresent(By locator, int unit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(unit));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public WebElement waitElementvisible(By locator, int unit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(unit));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public List<WebElement> waitElementsvisible(By locator, int unit) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(unit));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	public Alert waitForAlertPresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		return waitForAlertPresent(timeOut).getText();
	}

	public void alertAccept(int timeOut) {
		waitForAlertPresent(timeOut).accept();
	}

	public void alertDismiss(int timeOut) {
		waitForAlertPresent(timeOut).dismiss();
	}

	public String getTitlecontaiAndFeath(int TimeOut, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.titleContains(text));
		return driver.getTitle();
	}

	public String getTitlegetTitleAndFeath(int TimeOut, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.titleIs(text));
		return driver.getTitle();
	}

	public String geturlcontaiAndFeath(int TimeOut, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.urlContains(text));
		return driver.getCurrentUrl();
	}

	public String getTitleurlAndFeath(int TimeOut, String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.urlToBe(url));
		return driver.getCurrentUrl();
	}

	// check any one element is available it return list
	public List<WebElement> getPresentOfAllElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

//check all element vision and get hight and width >0
	public List<WebElement> getVisibleOfAllElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	public void getFramebyElement(WebElement ele, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
	}

	public void getFramebyidOrName(String idOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	public void getFramebyIndex(int index, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void getFrameUsingbyLoctor(By loctor, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(loctor));
	}

	public void waitForByLocatorClickble(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement waitForElementClickble(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void doClickwithActionWait(By locator, int timeOut) {
		WebElement ele = waitForElementClickble(locator, timeOut);
		Actions ac = new Actions(driver);
		ac.click(ele).build().perform();

	}

	public WebElement fluetwaitmethod(int timeOut, int pollingTime, By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.pollingEvery(Duration.ofSeconds(pollingTime)).withMessage("Gabbar is back");
		return witElementPresent(locator, timeOut);
	}

}
