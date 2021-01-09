package com.ushio.test.junit5;

import com.ushio.test.utils.CalculateLjUtils;
import org.junit.jupiter.api.RepeatedTest;

/**
 * @author: ushio
 * @description:
 **/
public class MixConcurrentTest {

    @RepeatedTest(10)
    void repeatedPlusTest() {
        System.out.println("repeatedPlusTest:" + CalculateLjUtils.plus(4,2));
    }

    @RepeatedTest(10)
    void repeatedMinusTest() {
        System.out.println("repeatedMinusTest:" + CalculateLjUtils.minus(4,2));
    }
}
