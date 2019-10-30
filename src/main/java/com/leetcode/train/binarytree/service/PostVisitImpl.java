package com.leetcode.train.binarytree.service;

import com.leetcode.train.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Batman on 2018/12/19.
 */
public class PostVisitImpl implements Visit {
    @Override
    public void visitTree(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        Deque<TreeNode> reverseDeque = new ArrayDeque<>();
        while(root!=null || deque.size() > 0){
            while(root!=null){
                deque.push(root);
                reverseDeque.push(root);
                root = root.getRight();
            }
            if(deque.size() > 0){
                root = deque.pop();
                root = root.getLeft();
            }
        }

        while(reverseDeque.size() > 0){
            root = reverseDeque.pop();
            root.visit();
        }
    }

}
