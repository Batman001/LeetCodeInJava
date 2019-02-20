package array;

/**
 * @author Batman create on 2019-02-20 9:07 AM
 *
 */
public class RotateSortedArray {
    public int search(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while(low < high){
            int mid = (low + high)/2;
            if(target < nums[mid]){
                // right side is sorted
                if(nums[mid] < nums[high]){
                    // target must be in left side
                    high = mid - 1;
                }
                else if(target < nums[low]){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }else if(target > nums[mid]){
                if(nums[low] < nums[mid]){
                    low = mid + 1;
                }else if(target > nums[high]){
                    high = mid -1;
                }else{
                    low = mid + 1;

                }
            }else{
                return mid;
            }

        }
        return -1;

    }
}
