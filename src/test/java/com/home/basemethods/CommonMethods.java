package com.home.basemethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

public class CommonMethods {
	
//public static WebDriver driver=null;
	
	public static String browser;
	public static String env;
	public static String url;
	public static String memberName1;
	public static String password;
	public static String testdataFile;
	public static WebDriver driver;
	
	@BeforeSuite
	public static void readProperties() throws IOException{
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream("src//test//resources//Common.properties");
		prop.load(input);
		input.close();
		browser=prop.getProperty("browser");
		env = prop.getProperty("Environment");
		
		Properties prop1 = new Properties();
		FileInputStream input1 = new FileInputStream("src//test//resources//Configuration_"+env+".properties");
		prop1.load(input1);
		url = prop1.getProperty("url");
		System.out.println("url is "+url);
		memberName1 = prop1.getProperty("qaclick.member1");
		password = prop1.getProperty("qaclick.password1");
		testdataFile=prop1.getProperty("testdata_file");
		input1.close();
		
		
		
	}
	
	public static WebDriver launchBrowser(String url) throws IOException{
		
		if(browser.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} 
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		return driver;
	
	}
	
	public static void click(WebElement element){
		element.click();
	}	
	
	public static void type(WebElement element, String text){
		element.sendKeys(text);
	}
	
	public static boolean compareStrings(String text1, String text2){
		if(text1.contains(text2)){
			return true;
		}
		return false;
	}

	public static void closeBrowser(WebDriver driver){
		
		driver.close();
		
	}
	

	public static void verifyElement(WebDriver driver, String xpath){
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		
	}
	

	
	public static void verifyElementDisplayed(WebElement element){
		
		element.isDisplayed();
		
	}
	
	public static String dynamicText(){
		SimpleDateFormat s = new SimpleDateFormat("EMMddyyhhmmss");
		Calendar cal = Calendar.getInstance();
		String dynamicText= String.valueOf(s.format(cal.getTime()));
		return dynamicText;
	}
	
	public static void takeScreenShot(WebDriver driver, String screenshotname) throws IOException{
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		SimpleDateFormat sf = new SimpleDateFormat("MMddyy_HHmmss");
		String filename = "target/screenshots/"+screenshotname+"_"+sf.format(Calendar.getInstance().getTime())+".jpg";
		File destination = new File(filename);
		FileUtils.copyFile(src, destination);
	}


}
