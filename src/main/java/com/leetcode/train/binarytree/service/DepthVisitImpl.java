package com.leetcode.train.binarytree.service;

import com.leetcode.train.binarytree.TreeNode;

import java.util.Stack;

/**
 * @author Batman create on 2019-07-24 14:43
 * 二叉树的深度优先遍历 就是前序遍历
 */
public class DepthVisitImpl implements Visit {
    /**
     * 二叉树遍历接口
     *
     * @param root 二叉树根节点
     */
    @Override
    public void visitTree(TreeNode root) {
        if(root == null) {
            return ;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode curNode = stack.pop();
            curNode.visit();

            if(curNode.right != null) {
                stack.push(curNode.right);
            }

            if(curNode.left != null) {
                stack.push(curNode.left);
            }

        }


    }
}
