package com.ushio.test.leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: ushio
 * @description:LeetCode
 **/
public class LeetCodeTest {

    @Test
    void testIsHappy(){
        System.out.println(isHappy(19));
    }

    public boolean isHappy(int n) {
        Set<Integer> integerSet = new HashSet<>();
        while (n != 1 && !integerSet.contains(n)) {
            integerSet.add(n);
            n = getNextInt(n);
        }
        return n == 1;
    }

    public int getNextInt(int n){
        int nextInt = 0;
        while (n > 0) {
            int d = n % 10;//取余数
            n = n / 10;
            nextInt += d * d;
        }
        return nextInt;
    }
}
