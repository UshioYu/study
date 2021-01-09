package com.ushio.test.junit5;

import com.ushio.test.utils.CalculateLjUtils;
import org.junit.jupiter.api.RepeatedTest;

/**
 * @author: ushio
 * @description:
 **/
public class ConcurrentTest {

    @RepeatedTest(10)
    void repeatedTest() {
        //junit.jupiter.execution.parallel.config.fixed.parallelism的值得小于RepeatedTest
        //的次数，不然加锁synchronized好像也不准
        System.out.println("repeatedTest:" + CalculateLjUtils.plus(1));
    }
}
