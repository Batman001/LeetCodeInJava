import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sunchao on 2018/12/19.
 */
public class CombinationSum {
    static List<List<Integer>> res;
    public static List<List<Integer>> combinationsSum(int[] candidates, int target){
        res = new LinkedList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();

        // 先对数组进行排序
        Arrays.sort(candidates);
        helper(candidates, target, 0, tmp);
        return res;
    }

    /**
     *
     * @param nums
     * @param target
     * @param index
     * @param tmp
     */
    private static void helper(int[] nums, int target, int index, List<Integer> tmp) {
        // 如果当前和已经大于目标,说明该路径错误
        if(target < 0){
            return;
        }
        // 如果当前和等于该目标,说明是一条正确的路径,记录该路径
        else if(target == 0){
            List<Integer> oneComb = new LinkedList<>(tmp);
            res.add(oneComb);
        }
        // 否则 对剩余所有可能性进行深度优先搜索
        else{
            for(int i=index;i<nums.length;i++){
                // 典型的先加入元素,再进行搜索,递归回来再移出元素的DFS解法
                tmp.add(nums[i]);
                helper(nums, target-nums[i],i,tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {

        int[] nums = new int[]{2,3,6,7};
        int target = 7;
        System.out.println(combinationsSum(nums, target));



    }

}
