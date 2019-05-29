package com.home.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.home.basemethods.CommonMethods;

public class SignInPage extends CommonMethods {

	@FindBy(id="user_email")
	 WebElement userEmail;
	
	@FindBy(id="user_password")
	 WebElement userPassword;
	
	@FindBy(xpath="//input[@value='Log In']")
	 WebElement logInButton;
	
	@FindBy(xpath="//a[contains(text(),'Forgot Password?')]")
	 WebElement forgotPwdLink;
	
	String logInPageTitle = "//h1[contains(text(),'Log In to QaClickAcademy')]";
	
	@FindBy(xpath="//a[text()='Create an Account']")
	WebElement createAccountLink;
	
	@FindBy(xpath="//div[contains(@class,'alert-danger')]")
	WebElement loginErrorMsg;
	
	public SignInPage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
	
	public SignInPage(){
		
	}
	
	
	public void enterEmail(String email){
		
		type(userEmail,email);
	}
	
	public void enterPwd(String text){
		
		type(userPassword,text);
		
	}
	
	public void clickLogIn(){
		
		logInButton.click();
		
	}
	
	public void verifyLogInPage(){
		
		verifyElement(driver,logInPageTitle);
		
	}
	
	public void verifyForgotPwdLink(){
		
		verifyElementDisplayed(forgotPwdLink);
		
	}
	
	public void verifyCreateAccount(){
		
		verifyElementDisplayed(createAccountLink);
		
	}
	
	public void compareErrorMessages(String text2){
		String text1 = loginErrorMsg.getText();
		compareStrings(text1,text2);
			
	}
	
	 
}
