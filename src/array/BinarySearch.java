package array;

import org.jetbrains.annotations.Contract;

/**
 * @author Batman create on 2019-04-16 14:13
 * Leetcode 704 https://leetcode-cn.com/problems/binary-search/
 * nums 所有的元素都是非重复的
 */
public class BinarySearch {
    @Contract(pure = true)
    private int search(int[] nums, int target){
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

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int target = 15;
        System.out.println(new BinarySearch().search(nums,target));

    }
}
