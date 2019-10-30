package com.leetcode.train.binarytree.service;

import com.leetcode.train.binarytree.InitTree;
import com.leetcode.train.binarytree.TreeNode;

import java.util.ArrayList;

/**
 * @author Batman create on 2019-04-16 15:11
 * 得到二叉树从根节点到所有叶子节点的路径信息
 */
public class FindAllPath {

    /** 用来记录全部的路径信息 */
    private ArrayList<ArrayList<Integer>> allPaths = new ArrayList<>();

    /** 用来记录一条路径的信息 */
    private ArrayList<Integer> onePath = new ArrayList<>();

    /** 返回所有的路径的信息 */
    private ArrayList<ArrayList<Integer>> findAllPath(TreeNode root){
        if(root == null){
            return allPaths;
        }

        // 把当前节点加入到路径信息中
        onePath.add(root.val);

        // 如果是叶子节点,则把onePath加入到allPaths中去
        if(root.left == null && root.right == null){
            allPaths.add(new ArrayList<>(onePath));
        }

        // 递归遍历根节点的左右子树
        findAllPath(root.left);
        findAllPath(root.right);

        onePath.remove(onePath.size() -1 );
        return allPaths;

    }

    public static void main(String[] args) {
        TreeNode root = InitTree.init();
        ArrayList<ArrayList<Integer>> result = new FindAllPath().findAllPath(root);
        System.out.println(result);
    }

}
