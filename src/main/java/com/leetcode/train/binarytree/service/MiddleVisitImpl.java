package com.leetcode.train.binarytree.service;

import com.leetcode.train.binarytree.TreeNode;

import java.util.Stack;

/**
 * @author Batman on 2018/12/19.
 */
public class MiddleVisitImpl implements Visit {
    @Override
    public void visitTree(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();

        while(root!=null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.getLeft();
            }
            if(!stack.isEmpty()){
                root = stack.pop();
                root.visit();
                root = root.getRight();
            }

        }
    }
}

