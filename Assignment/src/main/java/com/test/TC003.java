package com.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC003 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver;
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		 SoftAssert s_assert = new SoftAssert();
		// Setting implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		driver.get("https://react-shopping-cart-67954.firebaseapp.com/");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		

		if(driver.findElement(By.linkText("Star")).isDisplayed())
		{
			System.out.println("User should see the GitHub star button");
		}
		driver.findElement(By.linkText("Star")).click();		
		String star_counter=driver.findElement(By.xpath("//span[@id='repo-stars-counter-star']")).getAttribute("title");
		
		System.out.println("No of stars"+star_counter);
		
		driver.findElement(By.partialLinkText("Sign in")).click();
		driver.findElement(By.name("login")).sendKeys("gunjankatyal123");
		driver.findElement(By.id("password")).sendKeys("Gunjan*1994");
		driver.findElement(By.name("commit")).click();
		
		if(driver.findElement(By.xpath("//*[@aria-label='Star this repository'][1]")).isDisplayed())
		{	driver.findElement(By.xpath("//*[@aria-label='Star this repository'][1]")).click();}
		String star_counter2=driver.findElement(By.xpath("//span[@id='repo-stars-counter-star']")).getAttribute("title");
		System.out.println("No of stars"+star_counter2);
		
		driver.navigate().to("https://react-shopping-cart-67954.firebaseapp.com/");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
			String ab=	driver.findElement(By.xpath("//*[@class='sc-ebmerl-4 iliWeY']/p")).getText();
		
		int a=	Integer.parseInt(ab.split("P")[0].trim());
		int aa=	driver.findElements(By.xpath("//*[text()='Add to cart']")).size();
			if(a==aa)
		{
			System.out.println("No of products are same as displayed!");
		}
		
driver.findElement(By.xpath("//span[text()='S']")).click();
Thread.sleep(5000);


 a=	Integer.parseInt(driver.findElement(By.xpath("//*[@class='sc-ebmerl-4 iliWeY']/p")).getText().split("P")[0].trim());

 aa=	driver.findElements(By.xpath("//*[text()='Add to cart']")).size();
if(a==aa)
{
System.out.println("No of products are same again as displayed !");
}

driver.findElement(By.xpath("//*[text()='Add to cart']")).click();
driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();

 driver.switchTo().alert().accept();
		
		driver.close();
		System.out.print("All steps executed !");
	
	}
}
