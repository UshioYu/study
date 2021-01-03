package com.ushio.test.appium;

import com.ushio.wechat.helper.LogHelper;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * @author: ushio
 * @description:
 **/
public class SnowBallSnowBallTest extends BaseSnowBallTest {

    @Test
    void fun(){
        //显示等待
        wait = new WebDriverWait(driver,10,1000);
        driver.findElement(MobileBy.id("com.xueqiu.android:id/tv_search")).click();
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys("阿里巴巴");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='BABA']"))).click();
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

    @Test
    void searchTest(){
        //定位首页搜索框
        WebElement homeSearchTv = driver.findElement(MobileBy.id("com.xueqiu.android:id/tv_search"));
        String enableStr = homeSearchTv.getAttribute("enabled");
        LogHelper.info("enableStr:" + enableStr);
        LogHelper.info("isEnabled:" + homeSearchTv.isEnabled());
        if (homeSearchTv.isEnabled() || "true".equals(enableStr)) {
            Point point = homeSearchTv.getLocation();
            LogHelper.info("point:" + point);
            LogHelper.info("point x:" + point.x + ",y:" + point.y);
            LogHelper.info("bounds:" + homeSearchTv.getAttribute("bounds"));
            homeSearchTv.click();

            //定位搜索页搜索框并输入
            WebElement searchInputTv = driver.findElement(By.id("com.xueqiu.android:id/search_input_text"));
            String displayStr = searchInputTv.getAttribute("displayed");
            LogHelper.info("displayStr:"+displayStr);
            LogHelper.info("isDisplayed:"+searchInputTv.isDisplayed());
            if (searchInputTv.isDisplayed() || "true".equals(displayStr)) {
                searchInputTv.sendKeys("阿里巴巴");
                LogHelper.info("search success");
            } else {
                LogHelper.info("search failure");
            }
        }

    }

    @Test
    void swiperTest(){
        try {
            int width = driver.manage().window().getSize().getWidth();
            int height = driver.manage().window().getSize().getHeight();
            Thread.sleep(7000);//强制等待
            TouchAction touchAction = new TouchAction(driver);
            touchAction.press(PointOption.point((int) width / 2, (int) height * 8 / 10)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                    .moveTo(PointOption.point((int) width / 2, (int) height * 2 / 10)).release().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void priceTest(){
        driver.findElement(MobileBy.id("com.xueqiu.android:id/tv_search")).click();
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys("阿里巴巴");
        driver.findElement(By.xpath("//*[@text='BABA']")).click();
        driver.findElement(By.xpath("//*[@text='股票']")).click();
        String currentPrice = driver.findElement(By.xpath("//*[@text='09988']/../../..//*[@resource-id='com.xueqiu.android:id/current_price']")).getText();
        //assertThat("compare price：", Double.parseDouble(currentPrice), greaterThan(200d));
        //assertThat("compare price：", Double.parseDouble(currentPrice), equalTo(200d));
        assertThat("compare price：", Double.parseDouble(currentPrice), greaterThanOrEqualTo(200d));
    }

    @Test
    void uiautomatorTest(){
        AndroidDriver<MobileElement> androidDriver = (AndroidDriver<MobileElement>)driver;
        //androidDriver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.xueqiu.android:id/tab_name\").text(\"交易\")").click();
        System.out.println(androidDriver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"行情\")").getText());
        System.out.println(androidDriver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.xueqiu.android:id/scroll_view\").childSelector(text(\"热门\"))").getText());
        System.out.println(androidDriver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.xueqiu.android:id/tab_name\").fromParent(text(\"行情\"))").getText());

    }

    @Test
    void priceAssertTest(){
        driver.findElement(MobileBy.id("com.xueqiu.android:id/tv_search")).click();
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys("阿里巴巴");
        driver.findElement(By.xpath("//*[@text='BABA']")).click();
    }

}
