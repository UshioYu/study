package com.ushio.test.testng;

import com.ushio.test.utils.CalculateUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @author: ushio
 * @description:
 **/
@Epic("计算器项目")
@Feature("冒烟测试用例")
public class NgAllureTest {

    @Test(priority = 1)
    @Description("DescriptionPlusTest")
    @Story("加法测试")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("www.baidu.com")
    @Link(name = "Link 社区贴",type = "mylink",url = "https://ceshiren.com/t/topic/7611")
    void plusTest(){
        Assert.assertEquals(CalculateUtils.plus(4, 2), 6, "is equals??");
    }

    @Test(priority = 2)
    void minusTest(){
        Assert.assertEquals(CalculateUtils.minus(4,2),2,"is equals??");
    }

    @Test(priority = 3)
    void multiplyTest(){
        Assert.assertEquals(CalculateUtils.multiply(4,2),8,"is equals??");
    }

    @Test(priority = 4)
    void divideTest(){
        Assert.assertEquals(CalculateUtils.divide(4,2),2,"is equals??");
    }

    @Test(priority = 5)
    void plusCountTest() {
        Assert.assertEquals(CalculateUtils.plus(4, 2, 1, 1, 1, 1),10,"is equals??");
        //fail();
    }

    @Test(priority = 6)
    void lamaPlusTest(){
        int result1 = CalculateUtils.plus(4,2);
        int result2 = CalculateUtils.plus(2,2);
        int result3 = CalculateUtils.plus(2,1);
        Allure.addAttachment("pic",this.getClass().getResourceAsStream("/1.png"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(result1, 6, "is equals??");
        softAssert.assertEquals(result2, 3, "is equals??");
        softAssert.assertEquals(result3, 4, "is equals??");
        softAssert.assertAll();
    }
}
