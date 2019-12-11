package com.leetcode.train.string.kmp;

/**
 * @author Batman on 2019/1/8.
 * @author Batman
 * 暴力破解字符串匹配算法
 */
public class BroteForce {
    /**
     * bf是暴力破解字符串匹配算法的实现
     * @param ts  主字符串
     * @param ps  模式字符串
     * @return 如果匹配成功,返回主字符串的开始匹配的位置,否则返回-1
     */
    private int bf(String ts, String ps){

        char[] t = ts.toCharArray();

        char[] p = ps.toCharArray();

        // 主串的位置
        int i = 0;

        // 模式串的位置
        int j = 0;

        while(i < t.length && j < p.length){
            // 如果两个字符串相同,则比较下一个
            if(t[i] == p[j]){
                i++;
                j++;
            }
            // 一旦不匹配
            else{
                // 主字符串 i后退 j归零
                i = i - j + 1;
                j = 0;
            }
        }

        if(j == p.length){
            return i - j;
        }else{
            return -1;
        }
    }

    public static void main(String[] args) {
        String ts = "abbaabbaaba";
        String ps = "abbaaba";
        System.out.println(new BroteForce().bf(ts, ps));
    }
}
