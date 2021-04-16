package com.ushio.test.testng;

import org.testng.annotations.Test;

/**
 * @author: ushio
 * @description:
 **/
public class MmmoTest {

    @Test(groups = {"g1"})
    void testA() {
        System.out.println("A");
    }

    @Test(groups = {"g2"})
    void testB() {
        System.out.println("B");
    }

    @Test(groups = {"g1","g2"})
    void testC() {
        System.out.println("C");
    }

    @Test(groups = {"g2","g3"})
    void testD() {
        System.out.println("D");
    }

    @Test(groups = {"g3"})
    void testE() {
        System.out.println("E");
    }

}
