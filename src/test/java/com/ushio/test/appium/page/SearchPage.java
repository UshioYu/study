package com.ushio.test.appium.page;

import com.ushio.test.appium.BaseSnowBallTest;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * @author: ushio
 * @description:
 **/
public class SearchPage extends BaseSnowBallTest {

    @ParameterizedTest
    @MethodSource("getPriceByName")
    void searchByName(String name, String code, double price) {
        driver.findElement(MobileBy.id("com.xueqiu.android:id/tv_search")).click();
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys(name);
        driver.findElement(By.xpath("//*[@text='" + code + "']")).click();
        String currentPrice = driver.findElement(By.xpath("//*[@text='" + code + "']/../../..//*[@resource-id='com.xueqiu.android:id/current_price']")).getText();
        driver.findElement(By.id("com.xueqiu.android:id/action_close")).click();
        assertThat("compare price：", Double.parseDouble(currentPrice), greaterThanOrEqualTo(price));
    }

     static Stream<Arguments> getPriceByName(){
        return Stream.of(Arguments.of("阿里巴巴","BABA",200d),
                Arguments.of("网易","NTES",300d),
                Arguments.of("百度","BIDU",180d));
    }
}
