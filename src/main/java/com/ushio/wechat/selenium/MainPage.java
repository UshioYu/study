package com.ushio.wechat.selenium;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ushio.wechat.util.ThreadUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.ushio.wechat.util.Constant.BASEURL;

/**
 * @author: ushio
 * @description:
 **/
public class MainPage extends BasePage {

    public MainPage() {
        initWebDriver();
        jumpMainPage();
    }

    private void initWebDriver() {
        if (webDriver == null) {
            if ("chrome".equalsIgnoreCase(System.getenv("browser"))) {
                //System.setProperty("webdriver.chrome.driver", "E:/hogwarts/soft/chromedriver_win32/chromedriver.exe");
                webDriver = new ChromeDriver();
            } else if ("firefox".equalsIgnoreCase(System.getenv("browser"))) {
                webDriver = new FirefoxDriver();
            } else {
                //设个默认值
                webDriver = new ChromeDriver();
            }
        }
        //time也可提出参数，在调用的地方使用
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 10,1000);
        webDriver.get(BASEURL);
        webDriver.manage().window().maximize();
    }

    private void jumpMainPage(){
        File file = new File("cookies.yaml");
        if (file.exists()) {
            //读取yaml的cookies，塞给driver，自动登录
            List<HashMap<String,Object>> cookies = new ArrayList<>();
            try {
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                TypeReference typeReference = new TypeReference<ArrayList<HashMap<String,Object>>>() {};
                cookies = (ArrayList<HashMap<String,Object>>)mapper.readValue(file,typeReference);
            } catch (Exception e) {
                e.printStackTrace();
            }

            cookies.forEach(cookieMap->{
                Cookie cookie = new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString());
                webDriver.manage().addCookie(cookie);
            });

            webDriver.navigate().refresh();
        } else {
            //等待手机扫码，扫码后保存cookies
            ThreadUtil.sleep(15000);
            Set<Cookie> cookies = webDriver.manage().getCookies();
            try {
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                mapper.writeValue(new File("cookies.yaml"), cookies);
            } catch (IOException e) {
                e.printStackTrace();
            }
            webDriver.navigate().refresh();
        }
    }

    public ContactPage goToContact() {
        click(By.id("menu_contacts"));
        return new ContactPage(webDriver, webDriverWait);
    }
}
