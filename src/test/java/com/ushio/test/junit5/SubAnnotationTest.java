package com.ushio.test.junit5;

import org.junit.jupiter.api.*;

/**
 * @author: ushio
 * @description:each方法不加static，all方法加static
 **/
public class SubAnnotationTest extends AnnotationTest{

    @BeforeAll
    public static void beforeSubAll(){
        System.out.println("beforeSubAll");
    }

    @AfterAll
    public static void AfterSubAll(){
        System.out.println("afterSubAll");
    }

    @BeforeEach
    public void beforeSubEach(){
        System.out.println("beforeSubEach");
    }

    @AfterEach
    public void afterSubEach(){
        System.out.println("afterSubEach");
    }

    @Test
    void testSub1(){
        System.out.println("testSub1");
    }

    @Test
    void testSub2(){
        System.out.println("testSub2");
    }

}
