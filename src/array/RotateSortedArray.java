package array;

/**
 * @author Batman create on 2019-02-20 9:07 AM
 *
 */
public class RotateSortedArray {
    private int search(int [] nums,int target){
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

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 6;
        System.out.println(new RotateSortedArray().search(nums, target));
    }
}
