package com.ushio.test.utils;

/**
 * @author: ushio
 * @description:测试靶场
 **/
public class CalculateUtils {

    public static int plus(int... x) {
        int tmp = 0;
        for (int a : x)
            tmp += a;
        return tmp;
    }

    public static int minus(int x, int y) {
        return x - y;
    }

    public static int multiply(int x, int y) {
        return x * y;
    }

    public static int divide(int x, int y) {
        return x / y;
    }


}
