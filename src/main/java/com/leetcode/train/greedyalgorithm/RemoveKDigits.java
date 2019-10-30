package com.leetcode.train.greedyalgorithm;

import java.util.Stack;

/**
 * @author Batman create on 2019-07-04 09:38
 * leetcode 402 移掉K位数字
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小
 */
public class RemoveKDigits {

    /**
     * 贪心算法加单调栈实现
     * @param num 待移除数字的字符串数字
     * @param k 移除数字的个数
     * @return 返回移除后数字的字符串表达
     */
    public String removeKDigits(String num, int k) {
        if (k >= num.length() || num.length() == 0) {
            return "0";
        }

        // 保证栈顶始终是最大值
        Stack<Integer> stack = new Stack<>();
        stack.push(num.charAt(0) - '0');
        for (int i = 1; i < num.length(); i++) {
            int current = num.charAt(i) - '0';
             while(!stack.isEmpty() && k > 0 && current < stack.peek()) {
                 stack.pop();
                 k -- ;
             }

            //不等于0可以添加进去,
            //等于0，栈不为空可以填进去，
             if (current != 0  || !stack.isEmpty()) {
                 stack.push(current);
             }
        }

        while( k > 0) {
            k --;
            stack.pop();
        }

        if(stack.isEmpty()) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();

    }

}
