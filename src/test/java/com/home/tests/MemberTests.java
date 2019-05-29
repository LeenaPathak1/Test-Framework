package com.home.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.home.basemethods.CommonMethods;
import com.home.pages.DashboardPage;
import com.home.pages.HomePage;
import com.home.pages.SignInPage;
import com.home.pages.SignUpPage;

public class MemberTests extends CommonMethods {
	
	@BeforeMethod
	public void launchUrl() throws IOException{
		
		launchBrowser(url);
	}
	
	@Test (enabled=false)
	public void signUpHappyPath() throws IOException{
		
		HomePage hp = new HomePage(driver);
		hp.clickRegister();
		SignUpPage signUp = new SignUpPage(driver);
		signUp.verifySignUpPage(driver);
		signUp.enterFullName();
		String memberEmail = signUp.enterEMailAddress("@yopmail.com");
		signUp.enterPwd(password);
		signUp.enterConfirmPwd(password);
		signUp.clickAgreeToTerms();
		signUp.clickSignUp();
		writeMemberEmail(memberEmail, password);
	
	}
	
	@Test (dataProvider="data", enabled=true)
	public void validCredMemberLogin(String email, String password) throws IOException{
		
		HomePage hp = new HomePage(driver);
		hp.clickSignIn();
		
		SignInPage signIn = new SignInPage(driver);
		signIn.verifyLogInPage();
		signIn.enterEmail(email);
		signIn.enterPwd(password);
		signIn.clickLogIn();
		
		DashboardPage dp = new DashboardPage(driver);
		dp.clickImageDropDown();
		dp.clickLogOutOption();
		dp.verifyMyCoursesLink();
	
	}
	
	
	public void writeMemberEmail(String memberEmail, String Password) throws IOException{
		FileInputStream input = new FileInputStream("src//test//resources//"+testdataFile);
		
		XSSFWorkbook wb = new XSSFWorkbook(input);
		XSSFSheet ws = wb.getSheet("Members");
		int rowToAddData = ws.getLastRowNum() +1;
		XSSFRow row = ws.createRow(rowToAddData);
		XSSFCell cf = row.createCell(0);
		cf.setCellValue(memberEmail);
		cf = row.createCell(1);
		cf.setCellValue(Password);
		input.close();
		
		FileOutputStream fileOut = new FileOutputStream("src//test//resources//"+testdataFile);
		wb.write(fileOut);
		fileOut.close();
	}
	
	@DataProvider(name="data")
	public static String[][] readData() throws IOException{
		int i=0;
		int j=0;
		
		FileInputStream input = new FileInputStream("src//test//resources//"+testdataFile);
		XSSFWorkbook wb = new XSSFWorkbook(input);
		XSSFSheet sheet = wb.getSheet("Members");
		
		XSSFRow rows = sheet.getRow(0);
		int noOfRows = sheet.getLastRowNum();
		System.out.println("No of rows is: "+noOfRows);
		int noOfCols = rows.getLastCellNum();
		System.out.println("No of cols is: "+noOfCols);
		String[][] returnValues = new String[noOfRows][noOfCols];
		
		Iterator<Row> rowIterator = sheet.rowIterator();
		Row row = rowIterator.next();
		while(rowIterator.hasNext()){
			row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				String cellValue = cell.getStringCellValue();
				returnValues[i][j]=cellValue;
				System.out.println("Cell value: "+returnValues[i][j]);
				j=j+1;
			}
			i++;
			j=0;
		}
		input.close();
		return returnValues;
		
	}
}
