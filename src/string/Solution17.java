package string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Batman on 2018/12/19.
 */
public class Solution17 {
    public static List<String> letterCombinations(String digits){
        String[] table = new String[]{"","","abc","def","ghi","jkl",",mmo","pqrs","tuv","exyz"};
        List<String> list = new ArrayList<>();

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
            if(curr.length()!=0){
                list.add(curr);
            }
            // 找到数字对应的字符串
            String temp = table[digits.charAt(index) - '0'];
            for(int i=0;i<temp.length(); i++){
                String next = curr + temp.charAt(i);
                letterCombinationsFunction(list, digits, next, index + 1, table);
            }
        }
    }

}
