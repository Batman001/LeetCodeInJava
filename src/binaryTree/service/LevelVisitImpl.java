package binaryTree.service;

import binaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sunchao on 2018/12/19.
 * 二叉树层次遍历 非递归实现方法
 */
public class LevelVisitImpl implements Visit {
    @Override
    public void visitTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode visitedNode = queue.poll();
            visitedNode.visit();
            if(visitedNode.getLeft() != null)
                queue.add(visitedNode.getLeft());
            if(visitedNode.getRight() != null)
                queue.add(visitedNode.getRight());
        }
    }
}

