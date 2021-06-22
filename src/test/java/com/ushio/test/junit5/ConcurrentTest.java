package com.ushio.test.junit5;

import com.ushio.test.utils.CalculateLjUtils;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author: ushio
 * @description:
 **/
public class ConcurrentTest {

    @RepeatedTest(10)
    @Disabled
    void repeatedTest() {
        //junit.jupiter.execution.parallel.config.fixed.parallelism的值得小于RepeatedTest
        //的次数，不然加锁synchronized好像也不准
        System.out.println("repeatedTest:" + CalculateLjUtils.plus(1));
    }

    @RepeatedTest(10)
    void repeated1Test() {
        assertEquals(1,1);
        System.out.println("aaa");
    }

    @RepeatedTest(10)
    void repeated2Test() {
        assertEquals(2,2);
        System.out.println("bbb");
    }

}
