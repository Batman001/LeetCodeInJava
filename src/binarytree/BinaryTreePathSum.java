package binarytree;

import java.util.ArrayList;

/**
 * @author Batman create on 2019-04-17 17:34
 * leetcode 113 路径总和 II https://leetcode-cn.com/problems/path-sum-ii/
 */
public class BinaryTreePathSum {
    /** 满足路径之和为sum的路径存储 */
    private ArrayList<ArrayList<Integer>> sumPaths = new ArrayList<>();

    /** 一条路径信息存储 */
    private ArrayList<Integer> onePath = new ArrayList<>();

    private ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum){
        if(null == root){
            return sumPaths;
        }
        onePath.add(root.val);
        sum -= root.val;
        if(root.left == null && root.right == null && sum == 0){
            sumPaths.add(new ArrayList<>(onePath));
        }
        // 递归遍历根节点的左右子树
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        onePath.remove(onePath.size() -1);
        return sumPaths;
    }

    public static void main(String[] args) {
        TreeNode root = InitTree.init();
        System.out.println(new BinaryTreePathSum().pathSum(root, 36));
    }


}
