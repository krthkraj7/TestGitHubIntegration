package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	//Made public to use the variable globally(Global Variable)
	public static WebDriver driver;
	public static Properties prop;
	public static WebEventListener eventListener;
	public static EventFiringWebDriver e_driver;
	
	
	
	public TestBase() {
		
		
		try {
				prop = new Properties();
				FileInputStream fis = new FileInputStream("C:\\Users\\Karthik Rajendran\\Desktop\\"+
				"Educational\\Framework Practice\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\util\\config.properties");
				prop.load(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
		}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) 
		{
//			System.setProperties("webdriver.chrome.driver", "C:\\Users\\Karthik Rajendran\\Desktop\\Educational\\Selenium\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();				
		} 
		else if(browserName.equals("ff")) 
		{
//			System.setProperties("webdriver.gecko.driver", "C:\\Users\\Karthik Rajendran\\Desktop\\Educational\\Selenium\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();				
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICITELY_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
		
	}
