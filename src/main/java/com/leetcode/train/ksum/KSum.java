package com.leetcode.train.ksum;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetode 15 three sum
 * https://leetcode-cn.com/problems/3sum/
 * @author Batman on 2018/11/16.
 * (1)首先第一步进行排序
 * (2)固定一个数字 按照TwoSum的方法进行查找和为 -firstNum的组合
 * (3)在指针移动的过程中跳过出现的数字(进行去重操作)
 */
public class KSum {


    public static List<List<Integer>> twoSum(int[] nums, int target){
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if(len < 2){
            return result;
        }
        Arrays.sort(nums);
        int leftIndex = 0 ;
        int rightIndex = len - 1;
        while(leftIndex < rightIndex){
            int twoSum = nums[leftIndex] + nums[rightIndex];
            if(twoSum == target){
                result.add(Arrays.asList(nums[leftIndex], nums[rightIndex]));

                // skip the duplicated nums[leftIndex]
                while(leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex + 1]){
                    leftIndex ++;

                }

                // skip the duplicated nums[rightIndex]
                while(leftIndex < rightIndex && nums[rightIndex] == nums[rightIndex-1]){
                    rightIndex --;
                }

                leftIndex ++;
                rightIndex --;
            }
            else if(twoSum > target){
                rightIndex --;
            }else{
                leftIndex ++;
            }

        }

        return result;


    }

    /**
     * 3Sum
     * @param nums 数组
     * @return 返回符合条件的全部组合
     */
    public static List<List<Integer>> threeSum(int[] nums, int target){
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if(len<3){
            return result;
        }
        Arrays.sort(nums);


        // for all numbers can be the 1st number of triplet
        for(int i=0; i<len-2; i++){
            int firstNumber = nums[i];

            // skip all duplicated first number
            if(i==0 || firstNumber != nums[i-1]){
                int leftIndex = i+1;
                int rightIndex = len -1;
                int twoSumTarget = target - firstNumber;

                // try to find two numbers that sum up to firstNumber
                while(leftIndex < rightIndex){
                    int twoSum = nums[leftIndex] + nums[rightIndex];
                    if(twoSum == twoSumTarget){
                        result.add(Arrays.asList(firstNumber, nums[leftIndex], nums[rightIndex]));

                        // skip the duplicated nums[leftIndex]
                        while(leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex+1]){
                            leftIndex ++;
                        }
                        // skip the duplicated nums[rightIndex]
                        while(leftIndex < rightIndex && nums[rightIndex-1] == nums[rightIndex]){
                            rightIndex --;
                        }
                        leftIndex ++;
                        rightIndex --;
                    }
                    else if(twoSum < twoSumTarget){
                        leftIndex ++;
                    }else{
                        rightIndex--;
                    }
                }
            }
        }

        return result;

    }

    public static List<List<Integer>> fourSum(int[] nums, int target){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length<4){
            return result;
        }
        Arrays.sort(nums);
        int len = nums.length;


        for(int i=0;i<len-3;i++){
            if(i==0 || nums[i] > nums[i-1]){
                for(int j=i+1;j<len-2;j++){
                    if(j==i+1 || nums[j] > nums[j-1]){
                        int left = j+1;
                        int right = len-1;
                        while(left<right){
                            if(nums[i] + nums[j] + nums[left] + nums[right] == target){
                                result.add(Arrays.asList(nums[i], nums[j],nums[left], nums[right]));
                                while(left<right && nums[left] == nums[left+1]){
                                    left += 1;
                                }
                                while(left<right && nums[right] == nums[right-1]){
                                    right -= 1;
                                }
                                left +=1;
                                right -=1;
                            }
                            else if(nums[i] + nums[j] + nums[left] + nums[right] > target){
                                right -= 1;
                            }
                            else{
                                left += 1;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] test = {-5,-2,-4,-2,-5,-4,0,0};
        int[] test1 = {1,0,-1,0,-2,2};
        System.out.println(fourSum(test1, 0));
        System.out.println(threeSum(test1, 0));
        System.out.println(twoSum(test1, 0));
    }
}

