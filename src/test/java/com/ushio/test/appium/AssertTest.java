package com.ushio.test.appium;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author: ushio
 * @description:
 **/
public class AssertTest {

    @Test
    void assertTest(){
        Integer a = new Integer(1);
        Integer b = new Integer(2);
        assertEquals(a, b);
        assertNotNull(a);
        assertTrue(true);
        assertFalse(false);
        assertNull(b);
        assertSame(a, b);
        assertNotSame(a, b);
        assertArrayEquals(new int[]{1, 2}, new int[]{3, 4});
    }

}
