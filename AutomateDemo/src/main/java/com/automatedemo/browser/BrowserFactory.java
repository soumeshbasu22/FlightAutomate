package com.automatedemo.browser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
	
	public static WebDriver StartApp(WebDriver driver,String browser,String url) {
		if(browser=="chrome") {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\soumesh\\eclipse-workspace\\AutomateDemo\\chromedriver.exe");
		driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		
	return driver;	
	}

}
