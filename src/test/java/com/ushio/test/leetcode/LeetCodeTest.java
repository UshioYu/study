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

    @Test
    void testString(){
        String s = "123456";
        s.replaceAll("12","34");
        String s1 = s;
        System.out.println(s.replaceAll("12","34"));
        System.out.println(s1);
    }

    @Test
    void testConvertToTitle(){
        System.out.println(convertToTitle(28));
    }

    @Test
    void test11(){
        long usableSpace = 3525517312L;
        long totalSpace = 3579346944L;
        double b = usableSpace/(double)totalSpace;
        System.out.println(b);
        boolean a = b>0.2;
        System.out.println(a);
    }

    public String convertToTitle(int n) {
        if (n <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.append((char) (n % 26 + 'A'));
            n =n / 26;
        }
        return sb.reverse().toString();
    }

    @Test
    void test1(){
        String url = "http://ip:port/icntv/p_777777770000000000000025297221";
        String cid = url.substring(url.lastIndexOf("icntv/") + 6);
        System.out.println(cid);
    }

    @Test
    void testIsToeplitzMatrix(){
        int[][] matrix = new int[][]{{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        isToeplitzMatrix(matrix);
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        int colLength = matrix.length;
        int rowLength = matrix[0].length;
        System.out.println("colLength:"+colLength+",rowLength:"+rowLength);
        for (int i = 1; i < colLength; i++) {
            for (int j = 1; j < rowLength; j++) {
                if(matrix[i][j]!=matrix[i-1][j-1]){
                    return false;
                }
            }
        }
        return true;
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
