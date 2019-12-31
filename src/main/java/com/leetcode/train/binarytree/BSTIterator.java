package com.leetcode.train.binarytree;

import java.util.Stack;

/**
 * @author Batman
 * create 2019-01-14 2:34 PM
 */
public class BSTIterator {
    /** 本质上其实是中序遍历 */
    private Stack<TreeNode> stack;
    /** constructor function */
    public BSTIterator(TreeNode root){
        stack = new Stack<>();

        // 找到第一个节点,并把路径入栈
        while(root != null){
            stack.push(root);
            root = root.left;
        }

    }

    /** @return the next smallest number */
    public int next(){
        TreeNode curr = stack.pop();
        int res = curr.val;
        // 如果元素是右节点,把它的右节点及右节点的所有左节点都压入栈中
        if(curr.right != null){
            curr = curr.right;
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
        }
        return res;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNest(){
        return !stack.isEmpty();
    }



}
