package com.ushio.test.testng;

import com.ushio.test.testng.util.DataUtil;
import com.ushio.test.utils.CalculateUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

/**
 * @author: ushio
 * @description:
 **/
public class TestNgTest {

    @BeforeClass
    void beforeClass() {
        System.out.println("beforeClass");
    }

    @AfterClass
    void afterClass() {
        System.out.println("afterClass");
    }

    @BeforeMethod
    void beforeMethod() {
        System.out.println("beforeMethod");
    }

    @BeforeSuite
    void beforeSuite() {
        System.out.println("beforeSuite");
    }

    @BeforeTest
    void beforeTest() {
        System.out.println("beforeTest");
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("afterMethod");
    }

    @AfterSuite
    void afterSuite() {
        System.out.println("afterSuite");
    }

    @AfterTest
    void afterTest() {
        System.out.println("afterTest");
    }

    @Test(priority = 1)
    void plusTest(){
        Assert.assertEquals(5,CalculateUtils.plus(4,2));
    }

    @Test(priority = 2)
    void minusTest() {
        //testng的软断言,更灵活
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1, CalculateUtils.minus(4, 2));
        softAssert.assertEquals(2, CalculateUtils.minus(4, 1));
        softAssert.assertEquals(1, CalculateUtils.minus(4, 3));
        softAssert.assertEquals(5, CalculateUtils.minus(4, 3));
        softAssert.assertAll();
    }

    @Test(priority = 3)
    void multiplyTest(){
        System.out.println(CalculateUtils.multiply(4,2));
    }

    @Test(priority = 4)
    void divideTest(){
        System.out.println(CalculateUtils.divide(4,2));
    }

    @Test(threadPoolSize = 3,invocationCount = 10,timeOut = 10000)
    void repeatedPlusTest(){
        //testng多线程示例
        System.out.println(CalculateUtils.divide(4,2));
    }

    @Test(enabled = false)
    void enableTest(){
        System.out.println("暂时不执行，相当于注解");
    }

    @Test(timeOut = 4000)
    void timeoutTest(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("timeoutTest()");
    }

    @Test(threadPoolSize = 1,invocationCount = 1)
    @Parameters({"x","y","z"})
    void paramaterTest(int x, int y, int z) {
        SoftAssert softAssert = new SoftAssert();
        int a = CalculateUtils.plus(x, y);
        System.out.println(a);
        softAssert.assertEquals(a, z);
        Assert.assertEquals(a,z);
    }

    @DataProvider(name = "getData")
    public Object[][] getDataFromCsv(){
        return DataUtil.getTestData("/src/main/resources/data/plus.csv");
    }

    @Test(threadPoolSize = 1,invocationCount = 1,dataProvider = "getData")
    void dataCsvTest(String x,String y){
        try {
            int a = CalculateUtils.plus(Integer.parseInt(x),Integer.parseInt(y));
            System.out.println(a);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test1(){
        System.out.println("test1");
    }

    @Test(dependsOnMethods = "test1")
    void test2(){
        System.out.println("test2");
    }

    @Test(dependsOnMethods = {"test1","test2"})
    void test3(){
        System.out.println("test3");
    }

    @Test(groups = {"test11"})
    void test11(){
        System.out.println("test11");
    }

    @Test(groups = {"test11","test12"})
    void test12(){
        System.out.println("test12");
    }

    @Test(dependsOnGroups = {"test11","test12"})
    void test13(){
        System.out.println("test13");
    }

}
