package com.ushio.test.junit5;

import com.ushio.test.junit5.utils.CalculateUtils;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: ushio
 * @description:
 **/
public class AssertTest {

    @Test
    void plusTest(){
        assertEquals(6,CalculateUtils.plus(4,2),"is equals??");
    }

    @Test
    void minusTest(){
        assertEquals(2,CalculateUtils.minus(4,2),"is equals??");
    }

    @Test
    void multiplyTest(){
        assertEquals(8,CalculateUtils.multiply(4,2),"is equals??");
    }

    @Test
    void divideTest(){
        assertEquals(2,CalculateUtils.divide(4,2),"is equals??");
    }

    @Test
    void plusCountTest() {
        assertEquals(10,CalculateUtils.plus(4, 2, 1, 1, 1, 1),"is equals??");
        //fail();
    }

    @Test
    void lamaPlusTest(){
        int result1 = CalculateUtils.plus(4,2);
        int result2 = CalculateUtils.plus(2,2);
        int result3 = CalculateUtils.plus(2,1);
        assertAll("批量校验！",
                ()->assertEquals(6,result1,"is equals??"),
                ()->assertEquals(3,result2,"is equals??"),
                ()->assertEquals(4,result3,"is equals??")
                );
    }

}
