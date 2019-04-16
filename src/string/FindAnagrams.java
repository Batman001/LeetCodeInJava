package string;

/**
 * @author Batman on 2019/1/2.
 * @author Batman
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * FindAnagrams 找出字符串中所有的变位词 LeetCode 438
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
        /**
         * set all strings of s in map and initialize to 0
         */
        for (int k=0;k<s.length();k++){
            map.put(s.charAt(k), 0);
        }

        /**
         * set all strings of p in map and for each occurence of character in p increment
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
            /**
             * Decrement count in map only for each occurence of character s in p
             */
            if(map.get(s.charAt(right))>0){
                count --;
            }
            map.put(s.charAt(right), map.get(s.charAt(right)) - 1);

            while(count==0){
                if(right-left+1 == p.length()){
                    /** record start index left of anagram */
                    list.add(left);
                }

                if(map.get(s.charAt(left)) >=0){
                    count ++;
                }

                /** record the occurence of character at index i in map */
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
//        List<Integer> result = new FindAnagrams().findAnagrams(s, p);
        List<Integer> result = new FindAnagrams().findAnagrams2(s, p);
        System.out.println(result);
    }
}
