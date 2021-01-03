package com.ushio.test.appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author: ushio
 * @description:
 **/
public class DeviceTest {

    public static AndroidDriver driver;

    @BeforeAll
    static void init(){
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            //set avd拉起模拟器(只能是sdk自带的非7.0模拟器)
            desiredCapabilities.setCapability("avd","emulator-5554");
            URL remoteUrl = new URL("http://localhost:4723/wd/hub");

            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
            //隐式等待
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void emulatorTest(){
        System.out.println("111");
    }

    @Test
    void smsTest() throws Exception{
        //打电话
        driver.makeGsmCall("18112387886", GsmCallActions.CALL);
        //发短信
        driver.sendSMS("18112387886","hahaha1");
        //切到飞行模式
        driver.toggleAirplaneMode();
        //切到wifi模式
        driver.toggleWifi();
        //切到数据模式
        driver.toggleData();
        //切换横屏
        driver.rotate(ScreenOrientation.LANDSCAPE);
        //切换竖屏
        driver.rotate(ScreenOrientation.PORTRAIT);
        //切换锁屏
        driver.lockDevice();
        //截屏
        File screentShotAs = driver.getScreenshotAs(OutputType.FILE);
        //下面路径如果是windows，use.dir路径要变
        File file = new File(System.getProperty("user.dir") + "src/main/resources/demo.png");
        FileUtils.copyFile(screentShotAs,file);

        //开始录制，只能录3分钟，8.0以上才支持，华为不支持
        driver.startRecordingScreen();
        driver.stopRecordingScreen();
        //设置位置
        //driver.setLocation(new Location());
        //模拟键盘操作
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

}
