package com.hogwarts.testcase;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FirstCase {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    static void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        //设置隐式等待时间
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
    }

/*    @Test
    void login() {
        driver.get("https://ceshiren.com/");

        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();

        driver.findElement(By.id("login-account-name")).clear();
        driver.findElement(By.id("login-account-name")).sendKeys("tengjingbao@126.com");

        driver.findElement(By.id("login-account-password")).clear();
        driver.findElement(By.id("login-account-password")).sendKeys("a19850925");

        driver.findElement(By.id("login-button")).click();
    }*/

    @Test
    void waitTest() {
        driver.get("https://ceshiren.com/");

/*        WebElement loginButton = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//span[contains(text(),'登录')]"));
            }
        });

        loginButton.click();*/

//        System.out.println(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'登录')]")).toString());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'登录')]"))).click();

    }

    @AfterAll
    static void tearDown() {
//        driver.quit();
    }

}
