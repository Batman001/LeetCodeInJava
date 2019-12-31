package com.leetcode.train.binarysearch;

/**
 * @author Batman create on 2019-06-04 14:02
 * 二分查找法 在旋转数组中查找
 */
public class SearchInRotateArray {

    /**
     * leetcode 153 数组无重复元素 https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转
     * 示例: 输入: [3,4,5,1,2] 输出: 1
     * 示例: 输入: [4,5,6,7,0,1,2] 输出: 0
     * @param nums int类型数组
     * @return 返回数组中最小值
     */
    private static int findMin(int[] nums){

        // 如果数组没有旋转 则返回数组第一个元素
        if(nums.length == 1 || nums[0] < nums[nums.length - 1]){
            return nums[0];
        }

        return binSearch(nums, 0, nums.length - 1);
    }

    /**
     * 二分查找数组 nums[start:end+1] 的最小值
     * @param nums 目标数组
     * @param start 开始位置索引
     * @param end 结束位置索引
     * @return 最小值
     */
    private static int binSearch(int[] nums, int start, int end) {
        int mid = (start + end) / 2;
        if(nums[start] > nums[mid]) {
            return binSearch(nums, start, mid);
        }
        if (nums[mid + 1] > nums[end]) {
            return binSearch(nums, mid+1, end);

        }
        return nums[mid + 1];
    }


    /**
     * 二分查找元素法 leetcode 153 未包含重复元素 https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
     * @param nums 待查找数组
     * @return 数组中最小元素
     */
    private static int binarySearchInRotate(int[] nums){
        int low = 0, high = nums.length - 1;

        // 设置终止条件 low >= high
        while(low < high){
            int mid = (low + high) / 2;

            // 判断 如果中间元素大于最后一个元素 则最小值位于nums[mid+1:high+1]中
            if(nums[mid] > nums[high]){
                // 如果刚好二分的元素下一位为最小值 直接返回即可
                if(nums[mid + 1] < nums[mid]){
                    return nums[mid + 1];
                }
                low = mid + 1;
            }
            // 判断 如果中间元素 小于最后一个元素 则最小值位于nums[left:mid+1]中
            else{
                high = mid;
            }
        }
        return nums[0];

    }


    /**
     * leetcode 154. 寻找旋转排序数组中的最小值 II
     * 示例 输入: [2,2,2,0,1]  输出: 0
     * @param nums 含有重复元素且按照从小到大排序的数组 按照某一点旋转后的 数组
     * @return 数组中的最小值
     */
    public static int binarySearchInRotateDupArr(int[] nums){
        int low = 0, high = nums.length -1 ;

        while(low < high){
            int mid = (low + high) / 2;
            if(nums[mid] > nums[high]){
                low = mid + 1;
            }else if(nums[mid] < nums[high]){
                high = mid;
            }
            // 处理可能出现的重复的元素 如果nums[mid]==nums[high] 则high--
            else{
                high -= 1;
            }
        }
        return nums[low];
    }


    /**
     * 返回数组中出现的第一个元素 (数组中包含重复元素)
     * @param nums 升序排序数组
     * @param target 待查找数字
     * @return first index
     */
    private static int searchFirstTar(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = 0;

        while (left < right) {
            mid = (left + right) / 2;
            if(nums[mid] > target) {
                right = mid - 1;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                if (mid - 1 > 0 && nums[mid-1] == nums[mid]) {
                    mid -= 1;
                }
                return mid;
            }
        }
        return -1;

    }

    /**
     * 二分查找返回数组中出现的最后一个元素 (数组中包含重复元素)
     * @param nums 升序数组
     * @param target 待查找数字
     * @return 最后一个索引
     */
    private static int searchLastTar(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = 0;

        while (left <= right) {
            mid = (left + right) / 2;
            if(nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                if(mid <= nums.length - 1 && nums[mid] == nums[mid + 1]) {
                    mid += 1;
                }
                return mid;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        int[] nums = {4,5,6,7,8,9,10,1,2,3};
        int[] dumpNums = {1,2,3,3,5,6,6,9,10,12,23,34};
        int res = findMin(nums);
        System.out.println(res);

        System.out.println("二分查找重复元素返回第一个索引结果为:" + searchFirstTar(dumpNums, 6));
        System.out.println("二分查找重复元素返回最后一个索引为结果为:" + searchLastTar(dumpNums, 6));
    }

}
