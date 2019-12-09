package com.leetcode.train.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Batman on 2018/12/19.
 */
public class LeetCodeTest {

    /**
     * leetcode13 罗马数字转整数
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
     * leetcode14  最长公共前缀
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


    /**
     * leetcode 17 电话号码字母组合
     * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     * @param digits 输入数字
     * @return 返回数字可以输出的字符串
     *
     * input: "23"
     * output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
     *
     */
    public static List<String> letterCombinations(String digits){
        String[] table = new String[]{"","","abc","def","ghi","jkl",",mmo","pqrs","tuv","exyz"};
        List<String> list = new ArrayList<>();

        // 从index为0开始,即digits的第一个数字开始
        letterCombinationsFunction(list, digits, "", 0, table);
        return list;
    }

    /**
     * phone字符组装的函数
     * @param list  保存结果的容器
     * @param digits  待处理的传入的数字
     * @param curr   当前的curr字符串
     * @param index  当前索引
     * @param table  数字对应字符
     */
    private static void letterCombinationsFunction(List<String> list, String digits, String curr, int index, String[] table) {
        // 判断递归结束的条件
        if(index == digits.length()){
            if(curr.length() != 0) {
                list.add(curr);
            }
            return;
        }
        // 找到数字对应的字符串
        String temp = table[digits.charAt(index) - '0'];
        for(int i=0;i<temp.length(); i++){
            // 每次循环把不同的字符串添加到当前curr之后
            String next = curr + temp.charAt(i);
            // 进入下一层
            letterCombinationsFunction(list, digits, next, index + 1, table);
        }
    }


    /**
     * 最长公共子序列 leetcode 1143
     * https://leetcode-cn.com/problems/longest-common-subsequence/
     * @param text1 文本1
     * @param text2 文本2
     * @return 返回最长公共子序列的长度
     *
     * input: text1="abcde" text2="ace"
     * output: 3
     *
     * solution: 动态规划
     * if text1[i] == text2[j]:
     *      dp[i][j]=dp[i-1][j-1]+1
     * else:
     *      dp[i][j]=max(dp[i-1][j], dp[i][j-1])   保留之前的最长序列值
     * return dp[-1][-1]
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // 初始化 动态规划转移矩阵
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                dp[i][j] = 0;
            }
        }

        for(int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m][n];


    }


    /**
     * 最长公共子串
     * @param text1 文本1
     * @param text2 文本2
     * @return 返回最长公共子串的长度
     *
     * input: text1="asdfas" text2="werasdfaswer"
     * output: 6
     *
     * if text1[i] == text2[j]:
     *      dp[i][j] = dp[i-1][j-1] + 1
     * else:
     *      dp[i][j] = 0
     */

    public static int longeCommonSubstring(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m+1][n+1];

        for (int i=0; i<=m; i++) {
            for (int j=0; j<=n; j++) {
                dp[i][j] = 0;
            }
        }

        // 遍历text1与text2 的全部字符
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }


        // 遍历dp二维数组 返回最大值
        int max = 0;
        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                if(dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }

        return max;

    }




    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
        String[] test = new String[]{"flower","flow","flove"};
        String result = longestCommonPrefix(test);
        System.out.println(result);

        System.out.println(letterCombinations("223"));
    }
}

