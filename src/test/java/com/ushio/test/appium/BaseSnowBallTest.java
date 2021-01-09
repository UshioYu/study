package com.ushio.test.appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author: ushio
 * @description:
 **/
public class BaseSnowBallTest {

    public static AndroidDriver driver;
    public static WebDriverWait wait;

    /**
     * 打开雪球
     */
    @BeforeAll
    public static void setUp(){
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "127.0.0.1:7555");
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "127.0.0.1:7555");
            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
            desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
            desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");

            URL remoteUrl = new URL("http://localhost:4723/wd/hub");

            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
            //隐式等待
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void tearDown() {
        //driver.quit();
    }

}
