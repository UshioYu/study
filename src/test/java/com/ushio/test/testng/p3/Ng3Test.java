package com.ushio.test.testng.p3;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

/**
 * @author: ushio
 * @description:
 **/
public class Ng3Test {

    @Test(groups = {"g31"})
    @Description("Ng3Test->ng31Test()")
    void ng31Test(){
        System.out.println("Ng3Test->ng31Test()");
    }

    @Test(groups = {"g31","g32"})
    @Description("Ng3Test->ng32Test()")
    void ng32Test(){
        System.out.println("Ng3Test->ng32Test()");
    }

    @Test(groups = {"g32","g33"})
    @Description("Ng3Test->ng33Test()")
    void ng33Test(){
        System.out.println("Ng3Test->ng33Test()");
    }
}
