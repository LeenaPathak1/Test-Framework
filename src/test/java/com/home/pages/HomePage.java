package com.home.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.home.basemethods.CommonMethods;

	public class HomePage extends CommonMethods {
	
	WebDriver driver;
	
	@FindBy (xpath="//span[text()='Register']")
	WebElement registerLink;
	
	@FindBy (xpath="//a[contains(@href,'sign_in')]")
	WebElement signInLink;
	
	@FindBy (xpath="//a[contains(@class,'profile-dropdown')]")
	WebElement imageDropDown;
	
	@FindBy (xpath="//a[contains(@href,'sign_out') and contains(text(),'Log Out')]")
	WebElement logOutOption;
	
	String signInObj = "//a[contains(@href,'sign_in')]";
	
	public HomePage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void clickRegister(){
		
		click(registerLink);
		
	}
	
	public void clickSignIn(){
		
		click(signInLink);
		
	}
	
	public void  waitForSignIn(WebDriver driver){
		
		verifyElement(driver,signInObj);
		
	}
	
	

}
