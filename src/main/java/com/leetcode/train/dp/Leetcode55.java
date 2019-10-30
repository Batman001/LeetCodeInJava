package com.leetcode.train.dp; /**
 * Created by batman on 2018/12/27.
 * @author Batman
 */

/**
 * @author Batman
 * Leetcode 55题目 Jump Game
 */
public class Leetcode55 {
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
