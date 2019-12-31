package com.leetcode.train.binarysearch;

/**
 * @author Batman create on 2019-09-16 08:59
 * leetcode 162 寻找峰值 https://leetcode-cn.com/problems/find-peak-element/
 * 峰值元素是指其值大于左右相邻值的元素。
 *
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 *
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 *
 */
public class FindPeakElement {

    /**
     * 寻找数组中的峰值元素 要求时间复杂度 o(lgn)
     * @param nums 待查找目标数组
     * @return 峰值元素的索引位置
     */
    private static int findPeakElement(int[] nums) {

        // 二分搜索
        // 如果nums[i]<nums[i+1] 则[i+1,n]一定有peak，即使完全递增，n[n]也是peak
        // 如果nums[i]>nums[i+1] 则[0,i]一定有peak，同理

        if(nums.length == 1) {
            return 0;
        }
        int left = 0, right = nums.length-1;
        int mid = 0;


        while (left < right) {
            mid = (left + right) >> 1;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        return left;


    }

    public static void main(String[] args) {
        int[] nums = {1,2,1,3,5,6,4};
        System.out.println(findPeakElement(nums));
    }
}
