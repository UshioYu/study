package com.ushio.test.junit5;

import com.ushio.test.junit5.utils.CalculateUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: ushio
 * @description:
 **/
public class BaseTest {

    @Test
    void j1Test(){
        assertEquals(1,2,"is equals??");
    }

    @Test
    void plusTest(){
        System.out.println(CalculateUtils.plus(4,2));
    }

    @Test
    void minusTest(){
        System.out.println(CalculateUtils.minus(4,2));
    }

    @Test
    void multiplyTest(){
        System.out.println(CalculateUtils.multiply(4,2));
    }

    @Test
    void divideTest(){
        System.out.println(CalculateUtils.divide(4,2));
    }

}
