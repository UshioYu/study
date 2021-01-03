package com.ushio.test.appium;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author: ushio
 * @description:
 **/
public class SwiperUnlockTest {

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
            desiredCapabilities.setCapability("appPackage", "cn.kmob.screenfingermovelock");
            desiredCapabilities.setCapability("appActivity", "com.samsung.ui.FlashActivity");

            URL remoteUrl = new URL("http://localhost:4723/wd/hub");

            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void swiperUnlockTest(){
        try {
            TouchAction touchAction = new TouchAction(driver);

            Duration duration = Duration.ofSeconds(5);

            driver.findElement(By.id("cn.kmob.screenfingermovelock:id/setPatternLayout")).click();
            Thread.sleep(7000);

            touchAction.press(PointOption.point(248,389)).waitAction(WaitOptions.waitOptions(duration))
                    .moveTo(PointOption.point(770,381)).waitAction(WaitOptions.waitOptions(duration))
                    .moveTo(PointOption.point(1286,400)).waitAction(WaitOptions.waitOptions(duration))
                    .moveTo(PointOption.point(1289,894)).waitAction(WaitOptions.waitOptions(duration))
                    .moveTo(PointOption.point(1288,1409)).waitAction(WaitOptions.waitOptions(duration))
                    .release().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterAll
    public static void tearDown() {
        //driver.quit();
    }

}
