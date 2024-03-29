package com.automatedemo.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automatedemo.browser.BrowserFactory;
import com.automatedemo.objectrepo.ObjectRepository;
import com.automatedemo.objectrepo.ObjectRepository1;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class LoginTest {
	WebDriver driver;
	ExtentTest test;
	ExtentReports report;
	
	@BeforeTest
	public void SetUp() {
		driver=BrowserFactory.StartApp(driver, "chrome", "https://demo.guru99.com/test/newtours/");
		String filepath="C:\\Users\\soumesh\\eclipse-workspace\\AutomateDemo\\Reports\\flightreport.html";
		ExtentHtmlReporter html=new ExtentHtmlReporter(filepath);
		report=new ExtentReports();
		report.attachReporter(html);
	}
	@Test(priority=1)
	public void regtest() throws Exception {
		test=report.createTest("Registration");
		ObjectRepository or=new ObjectRepository(driver);
		String filepath="C:\\Users\\soumesh\\eclipse-workspace\\AutomateDemo\\RegSheet.xlsx";
		FileInputStream fi=new FileInputStream(filepath);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sh=wb.getSheet("Sheet1");
		String Filepath1="C:\\Users\\soumesh\\Documents\\Reg_Pwd.xlsx";
		FileInputStream fi1=new FileInputStream(Filepath1);
		XSSFWorkbook wb1=new XSSFWorkbook(fi1);
		XSSFSheet sh1=wb1.getSheet("Sheet1");
		
		int rowcount=sh.getLastRowNum()-sh.getFirstRowNum();
		for(int i=1;i<=rowcount;i++) {
			String fname=sh.getRow(i).getCell(0).getStringCellValue();
			String lname=sh.getRow(i).getCell(1).getStringCellValue();
			String phone=sh.getRow(i).getCell(2).getStringCellValue();
			String email=sh.getRow(i).getCell(3).getStringCellValue();
			String address=sh.getRow(i).getCell(4).getStringCellValue();
			String city=sh.getRow(i).getCell(5).getStringCellValue();
			String state=sh.getRow(i).getCell(6).getStringCellValue();
			String pin=sh.getRow(i).getCell(7).getStringCellValue();
			String country=sh.getRow(i).getCell(8).getStringCellValue();
			
			or.registration(fname, lname, phone, email);
			or.registration(address, city, state, pin, country);
		    wb.close();
			String uname=sh1.getRow(i).getCell(0).getStringCellValue();
			String pwd=sh1.getRow(i).getCell(1).getStringCellValue();
			String cpwd=sh1.getRow(i).getCell(2).getStringCellValue();
			or.registration(uname, pwd, cpwd);
			try{
			if(driver.findElement(By.xpath("//span[text()='PAssword and con.password does not match']")).isDisplayed()) {
				System.out.println("passwords dont match");
				test.log(Status.FAIL, "Passwords dont match");
			}
			}
			catch(NoSuchElementException e) {
				
				System.out.println("continue");
				String text=driver.findElement(By.xpath("//b[contains(text(),'SBM')]")).getText();
				System.out.println(text);
				test.log(Status.PASS, "Registratoion success");
				
				//or.logintest(uname, pwd);
				driver.findElement(or.RegLink).click();
				
			}
		Thread.sleep(3000);	
		}wb1.close();
		report.flush();
		Thread.sleep(3000);
	}
	@Test(priority=2)
	public void logtest() throws Exception{
		ObjectRepository or= new ObjectRepository(driver);
		String Filepath1="C:\\Users\\soumesh\\Documents\\Reg_Pwd.xlsx";
		FileInputStream fi1=new FileInputStream(Filepath1);
		XSSFWorkbook wb1=new XSSFWorkbook(fi1);
		XSSFSheet sh1=wb1.getSheet("Sheet1");
		int rowcount1=sh1.getLastRowNum()-sh1.getFirstRowNum();
		for(int j=1;j<=rowcount1;j++) {
			String uname=sh1.getRow(j).getCell(0).getStringCellValue();
			String pwd=sh1.getRow(j).getCell(1).getStringCellValue();

			or.logintest(uname, pwd);
		
	}wb1.close();
	}
	@Test(priority=3)
	public void flydetail() throws Exception {
		test=report.createTest("Flight_Booking");
		ObjectRepository1 or=new ObjectRepository1(driver);
		String filepath="C:\\Users\\soumesh\\eclipse-workspace\\AutomateDemo\\FlightDetails.xlsx";
		FileInputStream fi=new FileInputStream(filepath);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sh=wb.getSheet("Sheet1");
		int rownum=sh.getLastRowNum()-sh.getFirstRowNum();
		for(int i=1;i<=rownum;i++) {
			String passenger=sh.getRow(i).getCell(0).getStringCellValue();
			int arrivalmth=(int) sh.getRow(i).getCell(1).getNumericCellValue();
			String arrivaldt=sh.getRow(i).getCell(2).getStringCellValue();
			int retmnth=(int) sh.getRow(i).getCell(3).getNumericCellValue();
			String retdt=sh.getRow(i).getCell(4).getStringCellValue();
			
			or.flightbook(passenger, arrivalmth, arrivaldt, retmnth, retdt);
			
			WebElement table=driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table"));
			List<WebElement>rows=table.findElements(By.tagName("tr"));
			for(int k=0;k<rows.size();k++) {
				//List<WebElement>cols=driver.findElements(By.tagName("td"));
				//for(int m=0;m<cols.size();m++) {
					if(rows.get(k).getText().contains("No Seats")) {
					
					System.out.println("No Seats Available to book");
					//flag=1;
					test.log(Status.FAIL, "No seats available");
				}}
			//}
			
			List<WebElement>links=driver.findElements(By.xpath("//*[@href='index.php']"));
			
			links.get(2).click();

		}wb.close();
		report.flush();
	}
	
	public static void main(String[] args) throws Exception {
		LoginTest lt=new LoginTest();
		lt.SetUp();
		lt.regtest();
		lt.logtest();
		lt.flydetail();
		}
}
