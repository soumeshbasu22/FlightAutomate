package com.automatedemo.objectrepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ObjectRepository1 {
	WebDriver driver;
	public static By flights=By.xpath("//*[text()='Flights']");
	public static By rtrip=By.cssSelector("input[name='tripType'][value='roundtrip']");
	public static By oway=By.cssSelector("input[name='tripType'][value='oneway']");
	public static By psngr=By.xpath("//*[@name='passCount']");
	public static By deptfrom=By.xpath("//*[@name='fromPort']");
	public static By frommonth=By.xpath("//*[@name='fromMonth']");
	public static By fromday=By.xpath("//*[@name='fromDay']");
	public static By arrive=By.xpath("//*[@name='toPort']");
	public static By Return=By.xpath("//*[@name='toMonth']");
	public static By today=By.xpath("//*[@name='toDay']");
	public static By prefeconomy=By.cssSelector("input[name='servClass'][value='Coach']");
	public static By prefbusiness=By.cssSelector("input[name='servClass'][value='Business']");
	public static By preffirst=By.cssSelector("input[name='servClass'][value='First']");
	public static By airline=By.cssSelector("input[name='airline']");
	public static By continueb=By.xpath("//*[@name='findFlights']");
	
	public ObjectRepository1(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	public void flightbook(String no_of_pass,int arrival_on_mnth,String arrival_on_day, int return_on_mnth,String return_on_day) throws InterruptedException {
		driver.findElement(flights).click();
		driver.navigate().refresh();
		driver.findElement(flights).click();
		
		WebDriverWait wait=new WebDriverWait(driver, 40000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(rtrip)));
		driver.findElement(rtrip).click();
		WebElement sel1=driver.findElement(psngr);
		Select s1=new Select(sel1);
		s1.selectByValue(no_of_pass);
		Thread.sleep(50000);
		WebElement sel2=driver.findElement(frommonth);
		Select s2=new Select(sel2);
		s2.selectByIndex(arrival_on_mnth);
		WebElement sel3=driver.findElement(fromday);
		Select s3=new Select(sel3);
		s3.selectByValue(arrival_on_day);
		WebElement sel4=driver.findElement(Return);
		Select s4=new Select(sel4);
		s4.selectByIndex(return_on_mnth);
		WebElement sel5=driver.findElement(today);
		Select s5=new Select(sel5);
		s5.selectByValue(return_on_day);
		driver.findElement(continueb).click();
		WebElement table=driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table"));
		List<WebElement>rows=table.findElements(By.tagName("tr"));
		for(int k=0;k<=rows.size();k++) {
			List<WebElement>cols=driver.findElements(By.tagName("td"));
			for(int m=0;m<cols.size();m++) {
				if(cols.get(m).getText().contains("No Seats")) {
				
				System.out.println("No Seats Available to book");
			}}
		}
		List<WebElement>links=driver.findElements(By.xpath("//*[@href='index.php']"));
		
		links.get(2).click();
	}
}
