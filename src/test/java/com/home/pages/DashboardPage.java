package com.home.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.home.basemethods.CommonMethods;

	public class DashboardPage extends CommonMethods {

	
	@FindBy (xpath="//a[contains(@class,'profile-dropdown')]")
	WebElement imageDropDown;
	
	@FindBy (xpath="//a[contains(@href,'sign_out') and contains(text(),'Log Out')]")
	WebElement logOutOption;
	
	String logOutObj = "//a[contains(@href,'sign_out') and contains(text(),'Log Out')]";
	
	@FindBy (xpath="//a[contains(text(),'My Courses')]")
	WebElement myCoursesLink;
	
	String signInObj = "//a[contains(@href,'sign_in')]";
	
	public DashboardPage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void clickImageDropDown(){
		
		click(imageDropDown);
		
	}
	
	public void clickLogOutOption(){
		
		verifyElement(driver, logOutObj);
		click(logOutOption);
		
	}
	
	public void verifyMyCoursesLink(){
		
		verifyElementDisplayed(myCoursesLink);
		
	}
}
