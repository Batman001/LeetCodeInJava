package array;

/**
 * @author Batman create on 2019-03-18 11:25
 * @Leetcode 27 Remove Element
 */
public class RemoveElement {
    public static int removeElement(int[] nums, int val){
        int count = 0;
        for (int i=0; i<nums.length; i++){
            if(val == nums[i]){
                count ++;
            }else{
                nums[i-count] = nums[i];
            }
        }
        return nums.length - count;

    }



    public static void main(String[] args) {
        int[] nums = {3,2,2,3,2,5,6,8,9,0,-3,3};
        int val = 3;
        System.out.println(removeElement(nums, val));
    }

}
