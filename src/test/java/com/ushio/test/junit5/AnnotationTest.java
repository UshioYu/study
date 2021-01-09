package com.ushio.test.junit5;

import org.junit.jupiter.api.*;

/**
 * @author: ushio
 * @description:each方法不加static，all方法加static
 **/
public class AnnotationTest {

    @BeforeAll
    public static void beforeAll(){
        System.out.println("beforeAll");
    }

    @AfterAll
    public static void AfterAll(){
        System.out.println("afterAll");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("beforeEach");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("afterEach");
    }

    @Test
    void test1(){
        System.out.println("test1");
    }

    @Test
    void test2(){
        System.out.println("test2");
    }

}
