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

public class TC004 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver;
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		 SoftAssert s_assert = new SoftAssert();
		// Setting implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		driver.get("https://computer-database.gatling.io/computers");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		System.out.println(driver.getTitle());
		s_assert.assertEquals("Computers database", driver.getTitle());

		if(driver.findElement(By.id("searchbox")).isDisplayed())
		{
			System.out.println("User is able to  see the filter by computer name text box");
		}

		if(driver.findElement(By.linkText("Add a new computer")).isDisplayed())
		{
			System.out.println("User able to see add a new computer button");
		}
		

		if(driver.findElement(By.id("searchsubmit")).isDisplayed())
		{
			System.out.println("User able to see the filter by name button");
		}
		
		
		if(driver.findElement(By.xpath("//*[@class='computers zebra-striped']")).isDisplayed()) {
			
			if(driver.findElement(By.linkText("Computer name")).isDisplayed() && driver.findElement(By.linkText("Introduced")).isDisplayed()
					&& driver.findElement(By.linkText("Discontinued")).isDisplayed() &&driver.findElement(By.linkText("Company")).isDisplayed())
			{System.out.println(" User able to see the table and the headers ");
				
			}
			else {System.out.println(" User is not able to see the table and the headers ");
				
			}
			if(driver.findElement(By.xpath("//*[@class='pagination']")).isDisplayed())
			{
				System.out.println("User able to see pagination");
				
				
			}
			
			
		}
		driver.findElement(By.linkText("Add a new computer")).click();
		
		driver.findElement(By.xpath("//*[@value='Create this computer']")).click();
		if(driver.findElement(By.xpath("//*[@class='help-inline']")).isDisplayed())
		{
			System.out.println("User is able to see the red background on the computer name field");
			
			
		}
		driver.findElement(By.id("name")).sendKeys("Gunjan's Computer");
		Select s= new Select(driver.findElement(By.id("company")));
		s.selectByIndex(1);
		driver.findElement(By.xpath("//*[@value='Create this computer']")).click();
		
		s_assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert-message warning']")).getText(),"Done ! Computer Gunjan's Computer has been created");
		
		driver.findElement(By.id("searchbox")).sendKeys("Gunjan's Computer");
		driver.findElement(By.id("searchsubmit")).click();
		
		int si=driver.findElements(By.xpath("//*[@id=\"main\"]/table/tbody/tr[1]")).size();
		s_assert.assertEquals(1,si );
		
		driver.close();
		System.out.print("All steps executed !");
		s_assert.assertAll();

	}
}
