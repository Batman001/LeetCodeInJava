package com.leetcode.train.greedyalgorithm; /**
 * Created by batman on 2018/12/27.
 * @author Batman
 */

/**
 * @author Batman
 * 贪心算法规划leetcode题目总结
 */
public class LeetcodeTest {


    /**
     * leetcode 55 https://leetcode-cn.com/problems/jump-game/
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个位置。
     *
     * 使用贪心算法求解
     * @param nums 你可以跳跃的最大距离
     * @return 如果能跳到最后 返回true 否则返回false
     */
    public boolean canJump(int[] nums){
        if(nums.length == 1){

            return true;
        }

        // loc记录当前指针的位置
        int loc;

        // reach记录已经能到达的最大索引处
        int reach = 0;

        boolean res = false;
        for(int i=0;i<nums.length-1; i++){
            // 如果当前能到达的地点小于i break
            if(reach < i){
                break;
            }
            loc = i + nums[i];
            reach = Math.max(reach, loc);
        }

        if(reach >= nums.length-1){
            res = true;
        }
        return false;

    }

}
