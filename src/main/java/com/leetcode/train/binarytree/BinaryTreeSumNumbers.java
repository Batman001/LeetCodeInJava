package com.leetcode.train.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Batman create on 2019-04-16 14:48
 * leetcode 129. 求根到叶子节点数字之和 https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 */
public class BinaryTreeSumNumbers {
    /**
     * 首先组合得到从根节点到叶子节点的数字
     * 然后进行相加得到的和进行返回
     * @param root 二叉树根节点
     * @return 从根节点到叶子节点的数字之和
     */
    private int sumNumbers(TreeNode root){
        List<List<Integer>> resultPaths = findAllPath(root);
        // 首先用String类型将二叉树val拼接 并用sumStr存储
        ArrayList<String> sumStr = new ArrayList<>();
        for (List<Integer> each:resultPaths){
            StringBuilder currentSumStr = new StringBuilder();
            for(Integer item : each){
                currentSumStr.append(item);
            }
            sumStr.add(currentSumStr.toString());

        }
        int sumNum=0;
        for(String item:sumStr){
            sumNum += Integer.parseInt(item);
        }

        return sumNum;

    }

    /** 全部路径信息存储 */
    private List<List<Integer>> allPaths = new ArrayList<>();

    /** 一条路径信息存储 */
    private List<Integer> onePath = new ArrayList<>();

    private List<List<Integer>> findAllPath(TreeNode root){
        if(null == root){
            return allPaths;
        }
        onePath.add(root.val);
        System.out.println(onePath);
        if(root.left == null && root.right == null){
            allPaths.add(new ArrayList<>(onePath));
        }
        // 递归遍历根节点的左右子树
        findAllPath(root.left);
        findAllPath(root.right);
        onePath.remove(onePath.size() -1);
        return allPaths;
    }


    public static void main(String[] args) {
        TreeNode root = InitTree.init();
        System.out.println(new BinaryTreeSumNumbers().sumNumbers(root));
    }
}
