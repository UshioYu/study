package com.ushio.test.utils;

/**
 * @author: ushio
 * @description:测试靶场
 **/
public class CalculateLjUtils {

    public static int result = 0;

    public synchronized static int plus(int x) {
        try {
            Thread.sleep(300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result += x;
    }

    public static int plus(int x, int y) {
        return result = x + y;
    }

    public static int minus(int x, int y) {
        return result = x - y;
    }

    public static int multiply(int x, int y) {
        return result = x * y;
    }

    public static int divide(int x, int y) {
        return result = x / y;
    }


}
