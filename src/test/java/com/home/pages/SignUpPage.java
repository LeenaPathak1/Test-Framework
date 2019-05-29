package com.home.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.home.basemethods.CommonMethods;

public class SignUpPage extends CommonMethods {
	
	WebDriver driver;
	
	String signUpHeader ="//h1[contains(text(),'Sign Up to QaClickAcademy')]";
		
	@FindBy (xpath="//input[@id='user_name']")
	WebElement fullName ;
	
	@FindBy (xpath="//input[@id='user_email']")
	WebElement email_address;
	
	@FindBy (xpath="//input[@id='user_password']")
	WebElement password;
	
	@FindBy (xpath="//input[@id='user_password_confirmation']")
	WebElement confirmPwd;
	
	@FindBy (xpath="//input[@id='user_agreed_to_terms']")
	WebElement agreeToTerms;
	
	@FindBy (xpath="//input[@value='Sign Up']")
	WebElement SignUp;
	
	public SignUpPage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
	
	public SignUpPage(){
		
	}
	
	public void enterFullName(){
	
		type(fullName, CommonMethods.dynamicText()+" test");
	}	
	
	
	
	public void verifySignUpPage(WebDriver driver){
		
		verifyElement(driver,signUpHeader);
		
	}
	
	public void enterPwd(String text){
		
		type(password, text);
		
	}
	
	public String enterEMailAddress(String appendEmail){
		
		String uniqueEmail = dynamicText();
		
		type(email_address,uniqueEmail+appendEmail );
		
		return uniqueEmail+appendEmail;
		
	}
	
	public void enterConfirmPwd(String confirmPwdText){
		
		type(confirmPwd, confirmPwdText);
		
	}
	
	public void clickAgreeToTerms(){
		
		click(agreeToTerms);
		
	}
	
	public void clickSignUp(){
		
		click(SignUp);
		
	}
	

}
