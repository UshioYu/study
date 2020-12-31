package com.ushio.test.appium;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author: ushio
 * @description:
 **/
public class SnowBallTest {

    private static AndroidDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp(){
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "android");
            desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
            desiredCapabilities.setCapability("udid", "127.0.0.1:7555");
            desiredCapabilities.setCapability("noReset", "true");
            desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
            desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");

            URL remoteUrl = new URL("http://localhost:4723/wd/hub");

            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void fun(){
        wait = new WebDriverWait(driver,10,1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("com.android.settings:/id/title")));
    }

    @Test
    void helloWorldTest(){
        //定位首页搜索框
        driver.findElement(MobileBy.id("com.xueqiu.android:id/tv_search")).click();
        //driver.findElement(By.id("com.xueqiu.android:id/tv_search")).click();
        //定位搜索页搜索框并输入
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys("阿里巴巴");
        driver.findElement(By.xpath("//*[@text='BABA']")).click();
        System.out.println(driver.findElement(By.id("current_price")).getText());
    }

    @AfterAll
    public static void tearDown() {
        //driver.quit();
    }

}
