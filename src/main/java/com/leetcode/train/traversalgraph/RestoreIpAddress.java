package com.leetcode.train.traversalgraph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Batman create on 2019-01-18 2:27 PM
 * Leetcode 93
 * given a com.leetcode.train.string containing only digits, restore it by returning all possible
 * valid IP address combinations
 */
public class RestoreIpAddress {
    /**
     * leetcode 93
     * @param s 待处理IP地址字符串
     * @return 包含有效IP组合的列表容器
     */
    private List<String> restoreIpAddress(String s){
        List<String> res = new ArrayList<>();
        if(s.length() < 4 || s.length() > 12){
            return res;
        }
        dfs(s, "", res, 0);
        return res;
    }

    /**
     * 写一个for循环,每一层从1个字符开始取一直到3个字符,再加上一个判断isValid的方法进行验证
     * 如果是合法的字符,进行下一层递归,否则跳过
     * @param s 需要进行递归处理的IP字符串
     * @param temp 当前已经完成的部分组合字符串,count<=3
     * @param res 最终记录合理IP地址的ArrayList容器
     * @param count 目前完成组合IP字符串的次数
     */
    private void dfs(String s, String temp, List<String> res, int count) {
        // 递归终止条件
        if(count == 3 && isValid(s)){
            res.add(temp + s);
        }
        for(int i=1; i<4 && i<s.length(); i++){
            String subString = s.substring(0, i);
            if(isValid(subString)){
                dfs(s.substring(i), temp + subString + ".", res, count+1);
            }
        }

    }

    /**
     * 判断IP字符串的子串是否满足IP成立的条件
     * @param s 带判断字符串
     * @return 布尔类型
     */
    private boolean isValid(String s){
        if(s.charAt(0) == '0'){
            return "0".equals(s);
        }
        int num = Integer.parseInt(s);
        return num > 0 && num <= 255;
    }

    public static void main(String[] args) {
        String ip = "1002010";
        RestoreIpAddress function = new RestoreIpAddress();

        System.out.println(function.restoreIpAddress(ip));
    }


}
