package com.ushio.test.testng;

import com.ushio.test.utils.CalculateUtils;
import org.testng.annotations.Test;

/**
 * @author: ushio
 * @description:
 **/
public class MixTestNgTest {

    @Test(threadPoolSize = 1,invocationCount = 5,timeOut = 10000)
    void repeatedPlusTest(){
        //testng多线程示例
        System.out.println(CalculateUtils.plus(4,2));
    }

    @Test(threadPoolSize = 1,invocationCount = 5,timeOut = 10000)
    void repeatedMinusTest(){
        //testng多线程示例
        System.out.println(CalculateUtils.minus(4,2));
    }
}
