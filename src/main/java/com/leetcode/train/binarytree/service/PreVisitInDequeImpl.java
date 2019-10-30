package com.leetcode.train.binarytree.service;

import com.leetcode.train.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Batman on 2018/12/19.
 * @author Batman
 * 非递归前序遍历二叉树 使用队列实现
 */
public class PreVisitInDequeImpl implements Visit {
    @Override
    public void visitTree(TreeNode root) {
        if(root == null){
            return;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        while(root != null || deque.size() > 0){
            while (root != null){
                deque.push(root);
                root.visit();
                root = root.getLeft();
            }
            if(deque.size() > 0){
                root = deque.pop();
                root = root.getRight();
            }
        }

    }


}

