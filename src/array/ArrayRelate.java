package array;

import java.util.Arrays;

/**
 * @author Batman create on 2019-04-19 16:33
 */
public class ArrayRelate {
    /**
     * @author Batman create on 2019-04-16 14:13
     * Leetcode 704 https://leetcode-cn.com/problems/binary-search/
     * nums 所有的元素都是非重复的
     */
    private int binarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int binaryIndex = (left+right)/2;
            if(target == nums[binaryIndex]){
                return binaryIndex;
            }else if(target > nums[binaryIndex]){
                left = binaryIndex + 1;
            }else{
                right = binaryIndex - 1;
            }
        }
        return -1;

    }

    /**
     * @author Batman create on 2019-03-18 11:25
     * Leetcode 27 Remove Element
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     */
    private int removeElement(int[] nums, int val){
        int count = 0;
        for (int i=0; i<nums.length; i++){
            if(val == nums[i]){
                count ++;
            }else{
                nums[i-count] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return nums.length - count;

    }

    /**
     * Leetcode 33  搜索旋转排序数组
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1
     * @param nums int[]
     * @param target int
     * @return int
     */
    private int rotateSortedArray(int [] nums,int target){
        if(nums==null||nums.length==0) {
            return -1;
        }

        int low = 0;
        int high = nums.length-1;

        while(low <= high){
            int mid = (low + high)/2;
            if(target < nums[mid]){
                //right side is sorted
                if(nums[mid]<nums[high]) {
                    //target must in left side
                    high = mid - 1;
                }
                else {
                    //target<nums[mid]&&target<nums[low]==>means,target cannot be in [low,mid] since this side is sorted
                    if (target < nums[low]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            }else if(target > nums[mid]){
                //left side is sorted
                if(nums[low]<nums[mid]) {
                    //target must in right side
                    low = mid + 1;
                }
                else {
                    //right side is sorted. If target>nums[high] means target is not in this side
                    if (target > nums[high]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
            }else {
                return mid;
            }
        }

        return -1;
    }

    /**
     * 最短无序连续子数组 LeetCode 581
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序
     * 你找到的子数组应是最短的，请输出它的长度
     * solution https://www.cnblogs.com/jimmycheng/p/7673733.html
     * @param nums int[]
     * @return int
     */
    private int findUnsortedSubarray(int[] nums){
        int n = nums.length;
        int beg = -1;
        // end is -2 is because it works if the array is already in ascending order
        int end = -2;
        // from right to left
        int min = nums[n-1];
        // from left to right
        int max = nums[0];

        for(int i=0; i<n; i++)
        {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[n-1-i]);

            if(nums[i] < max){
                end = i;
            }
            if(nums[n-1-i] > min) {
                beg = n - 1 - i;
            }
        }
        // if array is already in ascending order, -2 - (-1) + 1 = 0
        return end - beg + 1;
    }


    /**
     * LeetCode 674 最长连续递增序列
     * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
     * 使用动态规划进行求解
     * @param nums int[]
     * @return int
     */
    private int findLengthOfLCIS(int[] nums){
        if (nums.length == 0){
            return 0;
        }
        int maxValue = 1, currentLen = 1;
        for (int i=1; i<nums.length; i++){
            if(nums[i] > nums[i-1]){
                currentLen ++;
            }else{
                maxValue = Math.max(maxValue, currentLen);
                currentLen = 1;
            }
        }
        return Math.max(currentLen, maxValue);


    }



    public static void main(String[] args) {
        ArrayRelate ar = new ArrayRelate();
        int[] nums = {-1,0,3,5,9,12};
        int target = 15;
        System.out.println(ar.binarySearch(nums,target));

        int[] num = {3,2,2,3,2,5,6,8,9,0,-3,3};
        int val = 3;
        System.out.println(ar.removeElement(num, val));

        System.out.println(ar.rotateSortedArray(nums, val));

        int[] testNums = {2,2,2,2,2};
        System.out.println(ar.findLengthOfLCIS(testNums));

    }


}
