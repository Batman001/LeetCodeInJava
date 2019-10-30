package com.leetcode.train.slidewindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Batman create on 2019-05-23 14:12
 * (待调试成功)
 */
public class Solution {
    public String minWindow(String s, String t){
        int left = 0;
        int right = -1;
        char[] totalArray = s.toCharArray();
        ArrayList<Character> totalList = new ArrayList<>();
        for(char c:totalArray){
            totalList.add(c);
        }
        HashMap<Character, Integer> curMap = new HashMap<>();
        HashMap<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()){
            targetMap.put(c, targetMap.containsKey(c) ? targetMap.get(c) + 1 : 1);
        }


        String foundSubStr = "";
        char[] foundSubStrArray = new char[s.length()];
        while (left < s.length()){

            if (right + 1 < s.length() && ! isCover(curMap, targetMap)){
                char currentChar = totalList.get(right + 1);
                curMap.put(currentChar, curMap.containsKey(currentChar) ? curMap.get(currentChar) + 1: 0);
                right += 1;

            }
            else{
                char currentLeftChar = totalList.get(left);
                curMap.put(currentLeftChar, curMap.get(currentLeftChar)-1);
                if (curMap.get(currentLeftChar) == 0) {
                    curMap.remove(currentLeftChar);
                }
                left += 1;
            }

            // 判断当前s[left:right+1]中是否覆盖t

            if (isCover(curMap, targetMap)) {
                if ("".equals(foundSubStr) || right - left + 1 < foundSubStr.length()) {
                    System.arraycopy(totalArray, left, foundSubStrArray, 0, right-left+1);
                    foundSubStr = foundSubStrArray.toString();
                }
            }

        }
        return foundSubStr;
    }


    /**
     * 判断curMap中全部元素是否包含全部target全部的value信息
     * @param curMap 父map
     * @param targetMap 子map
     * @return 如果curMap全部覆盖targetMap 返回true 否则返回false
     */
    private boolean isCover(HashMap<Character, Integer> curMap, HashMap<Character, Integer> targetMap) {

        Iterator targetIter = targetMap.entrySet().iterator();
        while(targetIter.hasNext()){
            Map.Entry entry = (Map.Entry) targetIter.next();
            Character key = (Character) entry.getKey();
            Integer value = (Integer) entry.getValue();
            if(!curMap.containsKey(key)){
                return false;
            }
            else if(curMap.get(key) < value){
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "ADOBECODEBANC";
        String subStr = "ABC";
        System.out.println(s.minWindow(str, subStr));
    }

}
