package com.ushio.test.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author: ushio
 * @description:
 **/
public class AshinTest {

    private static WebDriver webDriver;

    @BeforeAll
    public static void initWebDriver(){
        try {
            webDriver = new ChromeDriver();
            //隐式等待，selenium server服务端等待，每隔
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            //显示等待
//            wait = new WebDriverWait(webDriver,5000,1);
//            wait.until();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSearch(){
        webDriver.get("https://www.ceshiren.com");
        webDriver.findElement(By.id("search-button")).click();
        webDriver.findElement(By.id("search-term")).sendKeys("selenium");

    }

    @AfterAll
    public static void exit(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.quit();
    }
}
