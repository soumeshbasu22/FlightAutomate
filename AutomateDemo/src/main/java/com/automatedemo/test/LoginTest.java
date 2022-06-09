package com.automatedemo.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automatedemo.browser.BrowserFactory;
import com.automatedemo.objectrepo.ObjectRepository;
import com.automatedemo.objectrepo.ObjectRepository1;

public class LoginTest {
	WebDriver driver;
	@BeforeTest
	public void SetUp() {
		driver=BrowserFactory.StartApp(driver, "chrome", "https://demo.guru99.com/test/newtours/");
	}
	@Test(priority=1)
	public void regtest() throws Exception {
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
			}
			}
			catch(Exception e) {
				System.out.println("continue");
				String text=driver.findElement(By.xpath("//b[contains(text(),'SBM')]")).getText();
				System.out.println(text);
				//or.logintest(uname, pwd);
				driver.findElement(or.RegLink).click();
			}
			
		}wb1.close();
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
			
			
		}wb.close();
	}
	
	public static void main(String[] args) throws Exception {
		LoginTest lt=new LoginTest();
		lt.SetUp();
		//lt.regtest();
		//lt.logtest();
		lt.flydetail();
		}
}
