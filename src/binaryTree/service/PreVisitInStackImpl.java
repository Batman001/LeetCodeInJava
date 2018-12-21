package binaryTree.service;

import binaryTree.TreeNode;

import java.util.Stack;

/**
 * Created by sunchao on 2018/12/21.
 * 借助stack数据结构实现前序遍历的函数
 */
public class PreVisitInStackImpl implements Visit {
    @Override
    public void visitTree(TreeNode root){
        if(root == null){
            return ;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            node.visit();
            if(root.right != null){
                stack.push(root.right);
            }
            if(root.left != null){
                stack.push(root.left);
            }
        }

    }
}
