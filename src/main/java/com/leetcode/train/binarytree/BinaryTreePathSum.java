package com.leetcode.train.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Batman create on 2019-04-17 17:34
 * leetcode 113 路径总和 II https://leetcode-cn.com/problems/path-sum-ii/
 */
public class BinaryTreePathSum {
    /** 满足路径之和为sum的路径存储 */
    private List<List<Integer>> sumPaths = new ArrayList<>();

    /** 一条路径信息存储 */
    private List<Integer> onePath = new ArrayList<>();

    private List<List<Integer>> pathSum(TreeNode root, int sum){
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
        // 比如树的结构是[1,2,3] 此时onePath存储信息为[1,2],为了获取[1,3]的路径信息 需要删除2节点
        onePath.remove(onePath.size() -1);
        return sumPaths;
    }

    public static void main(String[] args) {
        TreeNode root = InitTree.init();
        System.out.println(new BinaryTreePathSum().pathSum(root, 36));
    }


}
