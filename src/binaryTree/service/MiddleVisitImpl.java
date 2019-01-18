package binaryTree.service;

import binaryTree.TreeNode;

import java.util.Stack;

/**
 * Created by Batman on 2018/12/19.
 */
public class MiddleVisitImpl implements Visit {
    @Override
    public void visitTree(TreeNode root) {
        if(root == null)
            return;
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

