package binarytree.service;

import binarytree.TreeNode;

import java.util.Stack;

/**
 * @author Batman on 2018/12/21.
 * @author Batman
 * 非递归前序遍历二叉树
 * 使用栈数据结构实现
 */
public class PrevisitInStackImpl implements Visit {
    @Override
    public void visitTree(TreeNode root){
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            node.visit();
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
    }

}
