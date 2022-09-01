package com.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
//import org.testng.asserts.SoftAssert;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC05 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver;
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		 SoftAssert s_assert = new SoftAssert();
		// Setting implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		System.out.println(driver.getTitle());
		s_assert.assertEquals("Swag Labs", driver.getTitle());

		String loginattr = driver.findElement(By.xpath("//*[@id='login-button']")).getAttribute("value");
		s_assert.assertEquals("Login", loginattr);

		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

		;
		s_assert.assertEquals("Name (A to Z)",
				driver.findElement(By.xpath("//*[@class='product_sort_container']/option[1]")).getText());

		driver.findElement(By.xpath("//*[text()='Add to cart'][1]")).click();

		s_assert.assertEquals("1", driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText());

		String a = driver.findElement(By.xpath("(//div[@class='inventory_item_name'])[6]")).getText();
		driver.findElement(By.xpath("(//*[text()='Add to cart'])[5]")).click();

		s_assert.assertEquals("2", driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText());

		driver.findElement(By.xpath("//*[text()='Remove'][1]")).click();
		s_assert.assertEquals("1", driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText());

		driver.findElement(By.className("shopping_cart_link")).click();

		driver.findElement(By.xpath("//*[@class='inventory_item_name']")).getText();
		s_assert.assertEquals(a, driver.findElement(By.xpath("//*[@class='inventory_item_name']")).getText());

		driver.findElement(By.id("continue-shopping")).click();

		Select aa = new Select(driver.findElement(By.className("product_sort_container")));
		aa.selectByValue("lohi");

		Double[] num = new Double[6];
		List<WebElement> list = driver.findElements(By.className("inventory_item_price"));
		int i = -1;
		for (WebElement abc : list) {
			Double mno = Double.parseDouble(abc.getText().replace("$", ""));
			i++;
			num[i] = mno;

		}

		s_assert.assertTrue(isOrdered(num));

		System.out.print("All steps executed !");

	}

	static boolean isOrdered(Double... input) {
		int[] counts = new int[3];
		for (int i = 1; i < input.length; i++)
			++counts[1 + Integer.signum(Double.compare(input[i - 1], input[i]))];
		return (counts[0] == 0 || counts[2] == 0);
	}
}
