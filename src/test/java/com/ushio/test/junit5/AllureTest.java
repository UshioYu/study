package com.ushio.test.junit5;

import com.ushio.test.utils.CalculateUtils;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: ushio
 * @description:
 **/
@Epic("计算器项目")
@Feature("冒烟测试用例")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AllureTest {

    @Test
    @Order(1)
    @Description("DescriptionPlusTest")
    @Story("加法测试")
    @DisplayName("加法")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("www.baidu.com")
    @Link(name = "Link 社区贴",type = "mylink",url = "https://ceshiren.com/t/topic/7611")
    void plusTest(){
        assertEquals(6, CalculateUtils.plus(4,2),"is equals??");
    }

    @Test
    @Order(2)
    void minusTest(){
        assertEquals(2,CalculateUtils.minus(4,2),"is equals??");
    }

    @Test
    @Order(3)
    void multiplyTest(){
        assertEquals(8,CalculateUtils.multiply(4,2),"is equals??");
    }

    @Test
    @Order(4)
    void divideTest(){
        assertEquals(2,CalculateUtils.divide(4,2),"is equals??");
    }

    @Test
    @Order(5)
    void plusCountTest() {
        assertEquals(10,CalculateUtils.plus(4, 2, 1, 1, 1, 1),"is equals??");
        //fail();
    }

    @Test
    @Order(6)
    void lamaPlusTest(){
        int result1 = CalculateUtils.plus(4,2);
        int result2 = CalculateUtils.plus(2,2);
        int result3 = CalculateUtils.plus(2,1);
        //这里老师没有细讲getResourceAsStream()方法的使用，可自行扩展学习
//        System.out.println(this.getClass().getResourceAsStream("/1.png"));
//        System.out.println(this.getClass().getClassLoader().getResourceAsStream("1.png"));
        Allure.addAttachment("pic",this.getClass().getResourceAsStream("/1.png"));
        assertAll("批量校验！",
                ()->assertEquals(6,result1,"is equals??"),
                ()->assertEquals(3,result2,"is equals??"),
                ()->assertEquals(4,result3,"is equals??")
        );
    }
}
