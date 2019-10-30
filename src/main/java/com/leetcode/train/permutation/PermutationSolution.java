package com.leetcode.train.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Batman on 2018/12/19.
 */
public class PermutationSolution {
    /**
     * 最终返回的结果集
     */
    List<List<Integer>> res = new ArrayList<List<Integer>>();

    public List<List<Integer>> permute(int[] nums){


        int len = nums.length;
        if(len == 0 || nums == null){
            return res;
        }

        // 采用前后元素交换的方法,dfs解题
        exchange(nums, 0, len);
        return res;
    }

    public void exchange(int[] nums, int i, int len){
        // 将当前数组添加到结果集中
        if(i == len-1){
            List<Integer> list = new ArrayList<>();
            for(int j=0;j<len;j++){
                list.add(nums[i]);
            }
            res.add(list);
            return;
        }
        // 将当前位置的数跟后面的数进行交换,并搜索解
        for(int j=i;j<len;j++){
            swap(nums, i, j);
            exchange(nums, i+1, len);
            swap(nums,i,j);
        }
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        PermutationSolution solution = new PermutationSolution();
        List<List<Integer>> res = solution.permute(nums);
        for(List<Integer> item : res){
            System.out.println(Arrays.toString(item.toArray()));
        }

    }

}

