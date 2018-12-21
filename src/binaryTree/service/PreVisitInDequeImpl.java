package binaryTree.service;

import binaryTree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by sunchao on 2018/12/19.
 */
public class PreVisitInDequeImpl implements Visit {
    @Override
    public void visitTree(TreeNode root) {
        if(root == null)
            return;
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

