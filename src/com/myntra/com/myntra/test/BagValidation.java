package com.myntra.com.myntra.test;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BagValidation {

	WebDriver driver = null;

	@Test(priority=0, expectedExceptions={Exception.class})
	public void addItem() {
		driver.findElement(By.xpath("//input[@class='desktop-searchBar']"))
				.sendKeys("shoes");
		try {
			Thread.sleep(5000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[@class='desktop-submit']/span"))
				.click();
		driver.findElement(By.xpath(".//*[text()='Roadster']/ancestor::a/img")).click();
		
		driver.findElement(By.xpath(".//*[@id='sizeButtonsContainer']/div[2]/button[3]/p[text()=8]")).click();
		
		 try {
				Thread.sleep(2000);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
	        
        driver.findElement(By.xpath("//*[text()='ADD TO CART']")).click();
        
        
        try {
			Thread.sleep(20000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
        
        
        WebDriverWait wait = new WebDriverWait(driver, 30);
        
        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='desktop-cart']/span[2]")));
        
        element.click();
        
        FluentWait wait1 = new FluentWait(driver).withTimeout(30, TimeUnit.SECONDS).ignoring(Exception.class).pollingEvery(5, TimeUnit.SECONDS);
        WebElement element2 =  (WebElement) wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='desktop-cart']/span[2]")));
        System.out.println(element2.getText());
     
      /* if( driver.findElement(By.xpath("//a[@class='desktop-cart']/span[2]")).isDisplayed())
       {
    	   driver.findElement(By.xpath("//a[@class='desktop-cart']/span[2]")).click();
       }*/
        /*if(count.equals(" ")){
        System.out.println("The Bag count is " +count);
        }*/
        /*  driver.findElement(By.xpath("//*[text()='GO TO CART']")).click();*/
	}

	

	@BeforeTest
	public void initializeBrowser() {
         this.driver = new FirefoxDriver();
         driver.get("https://www.myntra.com/");

	}

	@AfterTest
	public void tearDown() {
		
    driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}
}
