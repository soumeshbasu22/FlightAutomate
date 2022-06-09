package com.automatedemo.objectrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ObjectRepository {
	WebDriver driver;
	public static By RegLink=By.xpath("//*[@href=\'register.php\'][text()=\'REGISTER\']");
	public static By Fname=By.xpath("//*[@name=\'firstName\']");
	public static By Lname=By.xpath("//*[@name=\'lastName\']");
	public static By Phone=By.xpath("//*[@name=\'phone\']");
	public static By Mail=By.xpath("//*[@name=\'userName\']");
	public static By address=By.xpath("//*[@name=\'address1\']");
	public static By city=By.xpath("//*[@name=\'city\']");
	public static By state=By.xpath("//*[@name=\'state\']");
	public static By postalcode=By.xpath("//*[@name=\'postalCode\']");
	public static By country=By.xpath("//*[@name=\'country\']");
	public static By Uname=By.cssSelector("input[id='email']");
	public static By Pwd=By.cssSelector("input[name='password']");
	public static By CPwd=By.cssSelector("input[name='confirmPassword']");
	public static By submit=By.cssSelector("input[name='submit']");
	public static By user=By.cssSelector("input[name='userName']");
	public static By passwrd=By.cssSelector("input[name='password']");
	public static By sub=By.cssSelector("input[name='submit']");
	public static By signon=By.xpath("//a[text()='SIGN-ON']");
	public static By signoff=By.xpath("//a[text()='SIGN-OFF']");
	
	public ObjectRepository(WebDriver driver){
		this.driver=driver;
	}
	public void registration(String fname,String lname,String phone,String mail) throws InterruptedException {
		driver.findElement(RegLink).click();
		Thread.sleep(6000);
		driver.findElement(Fname).sendKeys(fname);
		Thread.sleep(3000);
		driver.findElement(Lname).sendKeys(lname);
		Thread.sleep(3000);
		driver.findElement(Phone).sendKeys(phone);
		Thread.sleep(3000);
		driver.findElement(Mail).sendKeys(mail);
	}
	public void registration(String Address,String City,String State,String Postalcode,String Country) {
		driver.findElement(address).sendKeys(Address);
		driver.findElement(city).sendKeys(City);
		driver.findElement(state).sendKeys(State);
		driver.findElement(postalcode).sendKeys(Postalcode);
		Select s=new Select(driver.findElement(country));
		s.selectByValue("INDIA");;
	}
	public void registration(String username,String pwd,String cpwd) {
		driver.findElement(Uname).sendKeys(username);
		driver.findElement(Pwd).sendKeys(pwd);
		driver.findElement(CPwd).sendKeys(cpwd);
		driver.findElement(submit).click();
		
	}
	public void logintest(String username,String password) {
		driver.findElement(signon).click();
		driver.navigate().refresh();
		driver.findElement(signon).click();
		driver.findElement(user).sendKeys(username);
		driver.findElement(passwrd).sendKeys(password);
		driver.findElement(sub).click();
		driver.findElement(signoff).click();
	}
}
