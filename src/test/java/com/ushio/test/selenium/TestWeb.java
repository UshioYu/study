package com.ushio.test.selenium;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: ushio
 * @description:
 **/
public class TestWeb {

    private static WebDriver webDriver;
    private static WebDriverWait wait;
    private Set<Cookie> cookies;

    @BeforeAll
    public static void initWebDriver(){
        try {
            webDriver = new ChromeDriver();
            //隐式等待，selenium server服务端等待，每隔
            webDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
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
        webDriver.findElement(By.cssSelector(".search-dropdown .d-icon-search")).click();
        //webDriver.findElement(By.cssSelector("#search-button")).click();
        webDriver.findElement(By.cssSelector("#search-term")).sendKeys("selenium");
    }

    @Test
    void testLogin() {
        webDriver.manage().window().maximize();
        webDriver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome_baidu");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cookies = webDriver.manage().getCookies();
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.writeValue(new File("cookies.yaml"), cookies);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    @Test
    void testLogined() {
        webDriver.manage().window().maximize();
        webDriver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome_baidu");
        List<HashMap<String,Object>> cookies = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference = new TypeReference<ArrayList<HashMap<String,Object>>>() {};
            cookies = (ArrayList<HashMap<String,Object>>)mapper.readValue(new File("cookies.yaml"),typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }

        cookies.forEach(cookieMap->{
            Cookie cookie = new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString());
            webDriver.manage().addCookie(cookie);
        });

        webDriver.navigate().refresh();

    }

    @AfterAll
    public static void exit(){

    }

}
