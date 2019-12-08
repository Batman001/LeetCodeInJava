package com.leetcode.train.string;

import java.util.Arrays;
import java.util.List;

/**
 * @author Batman on 2018/12/19.
 */
public class LeetCodeTest {

    /**
     * leetcode 罗马数字转整数
     * https://leetcode-cn.com/problems/roman-to-integer/
     * @param s 罗马字符串
     * @return 返回int数字
     */
    public static int romanToInt(String s){

        String[] romanItem = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        List<String> romans = Arrays.asList(romanItem);


        int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        char[] strs = s.toCharArray();


        int res = 0;
        int i=0;

        while(i < strs.length - 1){
            if(romans.contains(String.valueOf(strs[i]))){
                if(romans.contains(String.valueOf(strs[i]) + String.valueOf(strs[i+1]))){
                    res += numbers[romans.indexOf(String.valueOf(strs[i]) + String.valueOf(strs[i+1]))];
                    i+=2;

                }
                else{
                    res += numbers[romans.indexOf(String.valueOf(strs[i]))];
                    i+=1;
                }

            }

        }
        if(i == strs.length - 1){
            res += numbers[romans.indexOf(String.valueOf(strs[i]))];
        }
        return res;

    }

    public static String getType(Object o){
        return o.getClass().toString();
    }


    /**
     * leetcode  最长公共前缀
     * https://leetcode-cn.com/problems/longest-common-prefix/
     * @param strs 包含公共前缀字符的字符串数组
     * @return 返回最长公共前缀字符串
     */
    public static String longestCommonPrefix(String[] strs){
        if(strs == null || strs.length ==0){
            return "";
        }
        if(strs.length == 1){
            return strs[0];
        }
        String result = strs[0];
        for(int i=0;i<strs.length;i++){
            for(int j=result.length();j>=0;j--){
                if(strs[i].startsWith(result.substring(0,j))){
                    break;
                }
                else{
                    result = result.substring(0,j-1);
                }
                if("".equals(result)){
                    return "";
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
//        System.out.println(romanToInt("MCMXCIV"));
        String[] test = new String[]{"flower","flow","flove"};
        String result = longestCommonPrefix(test);
        System.out.println(result);
    }
}

