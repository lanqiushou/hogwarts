package com.hogwarts.selenium;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDemoTest {

    @Test
    void startSelenium() {
        WebDriver webDriver = new ChromeDriver();
//        System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
        webDriver.get("https://home.testing-studio.com");
        webDriver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
    }

}
