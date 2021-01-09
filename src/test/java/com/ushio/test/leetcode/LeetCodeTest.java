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

    @Test
    void romanToInt(){
        System.out.println(romanToInt("XXIV"));

    }


    public int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            System.out.println("preNum:" + preNum + ",num:" + num);
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
            System.out.println("sum:" + sum + ",preNum:" + preNum);
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    public int romanToInt1(String s) {
        s = s.replace("IV","a");
        s = s.replace("IX","b");
        s = s.replace("XL","c");
        s = s.replace("XC","d");
        s = s.replace("CD","e");
        s = s.replace("CM","f");

        int result = 0;
        for (int i=0; i<s.length(); i++) {
            result += which(s.charAt(i));
        }
        return result;
    }

    public int which(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            case 'a': return 4;
            case 'b': return 9;
            case 'c': return 40;
            case 'd': return 90;
            case 'e': return 400;
            case 'f': return 900;
        }
        return 0;
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
