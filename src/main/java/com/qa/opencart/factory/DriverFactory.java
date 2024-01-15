package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionManager optionManager;
	public static String highlight;
	FileInputStream fi;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * this method is initialize the driver on the basis of browser name
	 * 
	 * @param browserName
	 * @return this return drivers.
	 */
	public WebDriver initDriver(Properties prop) {
		optionManager = new OptionManager(prop);
		highlight = prop.getProperty("highlight");
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println(browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
			} else {
				tldriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			}
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {				
				//run remote grid
				init_remoteDriver("firefox");
			} else {
				//run on local
				tldriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
			}

		} else if (browserName.equalsIgnoreCase("edge")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("edge");
			} else {
				tldriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
			}

		} else {
			System.out.println("please enter valid browser name..." + browserName);
			throw new FrameworkException("INVALID BROWSER NAME");
		}

		getdriver().manage().deleteAllCookies();
		getdriver().manage().window().maximize();
		getdriver().get(prop.getProperty("url"));
		return getdriver();
	}

	private void init_remoteDriver(String bowser) {
		
		System.out.println("RUN TESTCASE ON REMOTE SIDE");
		try {

			switch (bowser.toLowerCase()) {
			case "chrome":
				tldriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("remoteurl")), optionManager.getChromeOptions()));
				break;
			case "firefox":
				tldriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("remoteurl")), optionManager.getFirefoxOptions()));
				break;
			case "edge":
				tldriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("remoteurl")), optionManager.getEdgeOptions()));
				break;

			default:
				System.out.println("please enter valid browser name" + bowser);
				throw new FrameworkException("NO REMOTE BROWSER EXCEPITON");

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * @return local thread driver
	 */
	public synchronized static WebDriver getdriver() {
		return tldriver.get();
	}

	/**
	 * properties file load
	 * 
	 * @return
	 */
	public Properties initprop() {

		String envName = System.getProperty("env");
		System.out.println("Running Test Case of Env...." + envName);
		prop = new Properties();
		try {
			if (envName == null) {
				System.out.println("No environment Passed so By Default its taken QA environment QA Environment");
				fi = new FileInputStream("./src/test/resources/config/qa.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "dev":
					fi = new FileInputStream("./src/test/resources/config/dev.properties");
					break;
				case "qa":
					fi = new FileInputStream("./src/test/resources/config/qa.properties");
					break;
				case "Prod":
					fi = new FileInputStream("./src/test/resources/config/prod.properties");
					break;

				default:
					System.out.println("Please Enter Valid Environment Name");
					throw new FrameworkException("WRONG ENV PASSED");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(fi);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public static String getScreenshot() {

		File srcFile = ((TakesScreenshot) getdriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis();
		File DestFile = new File(path);
		try {
			FileUtil.copyFile(srcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

}
