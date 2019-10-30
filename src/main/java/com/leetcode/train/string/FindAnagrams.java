package com.leetcode.train.string;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Batman on 2019/1/2.
 * @author Batman
 * add a code from branch feature and master
 * FindAnagrams 找出字符串中所有的变位词 LeetCode 438
 *
 * 题目描述：
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 *
 * 示例：
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * 滑动窗口思想
 */
public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p){
        List<Integer> result = new ArrayList<>();
        if(s == null || p == null) {
            return result;
        }
        int left = 0, right=0, count=p.length();
        int[] map = new int[256];
        char[] sc = s.toCharArray();
        for(char c:p.toCharArray()){
            map[c] ++;
        }

        while(right<s.length()){
            if(map[sc[right++]] -- >= 1){
                count --;
            }
            if(count == 0){
                result.add(left);
            }
            if(right-left == p.length() && map[sc[left++]] ++ >=0 ){
                count ++;
            }
        }
        return result;

    }


    public List<Integer> findAnagrams2(String s, String p){
        HashMap<Character, Integer> map = new HashMap<>();
        // input invalid as p size > s size
        if(p.length()>s.length()){
            return new ArrayList<>();
        }
        /*
         * set all strings of s in map and initialize to 0
         */
        for (int k=0;k<s.length();k++){
            map.put(s.charAt(k), 0);
        }

        /*
         * set all strings of p in map and for each occur of character in p increment
         * the value in the map
         */

        for(int k=0;k<p.length();k++){
            if(map.containsKey(p.charAt(k))){
                map.put(p.charAt(k), map.get(p.charAt(k)) + 1);
            }
            else{
                return new ArrayList<>();
            }
        }


        int left=0,right=0,count = p.length();
        List<Integer> list = new ArrayList<>();
        while(right<s.length()){
            /*
             * Decrement count in map only for each occur of character s in p
             */
            if(map.get(s.charAt(right))>0){
                count --;
            }
            map.put(s.charAt(right), map.get(s.charAt(right)) - 1);

            while(count==0){
                if(right-left+1 == p.length()){
                    /* record start index left of anagram */
                    list.add(left);
                }

                if(map.get(s.charAt(left)) >=0){
                    count ++;
                }

                /* record the occur of character at index i in map */
                map.put(s.charAt(left), map.get(s.charAt(left)) + 1);
                left ++;
            }
            right ++;
        }
        return list;
    }
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> result = new FindAnagrams().findAnagrams(s, p);
//        List<Integer> result = new FindAnagrams().findAnagrams2(s, p);
        System.out.println(result);
    }
}
