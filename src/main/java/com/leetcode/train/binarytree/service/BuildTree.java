package com.leetcode.train.binarytree.service;

import com.leetcode.train.binarytree.TreeNode;

/**
 * @author Batman on 2018/12/19.
 * @author Batman
 */

public class BuildTree {
    /**
     * 根据二叉树的前序遍历和中序遍历 重建该二叉树
     * @param preorder 前序遍历数组
     * @param inorder  中序遍历数组
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder){
        if(preorder==null || inorder==null || preorder.length!=inorder.length || preorder.length==0){
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int rootIndex;
        int len = preorder.length;

        // find the root of Tree
        for(rootIndex=0;rootIndex<len;rootIndex++){
            if(inorder[rootIndex] == root.val){
                break;
            }
        }

        // 重建左子树
        if(rootIndex>0){
            int[] pre = new int[rootIndex];
            int[] in = new int[rootIndex];
            for(int i=0;i<rootIndex;i++){
                pre[i] = preorder[i+1];
                in[i] = inorder[i];
            }
            root.left = buildTree(pre, in);

        }else{
            root.left = null;
        }

        // 重建右子树
        if(len-rootIndex-1>0){
            int[] pre = new int[len-rootIndex-1];
            int[] in = new int[len-rootIndex-1];
            for(int i=rootIndex+1;i<len;i++){
                pre[i-rootIndex-1] = preorder[i];
                in[i-rootIndex-1] = inorder[i];
            }
            root.right = buildTree(pre, in);

        }else{
            root.right = null;
        }
        return root;

    }

    /**
     * 根据二叉树中序遍历和后序遍历的结果重建二叉树
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder){

        if(inorder==null || postorder==null || inorder.length!=postorder.length || inorder.length==0){
            return null;
        }

        int len = inorder.length;
        //根据后序遍历结果,可以得到二叉树的根 为后序遍历的最后一个元素
        TreeNode root = new TreeNode(postorder[postorder.length -1]);

        // 根据中序遍历结果得到根节点索引
        int rootIndex = 0;
        for(int i=0;i<len;i++){
            if(inorder[i] == root.val) {
                rootIndex = i;
                break;
            }
        }

        // 重建左子树
        if(rootIndex>0){
            // 得到左子树的中序遍历和后序遍历结果
            int[] in = new int[rootIndex];
            int[] post = new int[rootIndex];
            for(int i=0;i<rootIndex;i++){
                in[i] = inorder[i];
                post[i] = postorder[i];
            }
            root.left = buildTree2(in, post);
        }else{
            root.left = null;
        }

        // 重建右子树
        if(rootIndex<len-1){
            // 得到右子树的中序遍历和后序遍历结果
            int[] in = new int[len-1-rootIndex];
            int[] post = new int[len-1-rootIndex];
            for(int i=rootIndex+1;i<len;i++){
                in[i-rootIndex-1] = inorder[i];
                // 判断清楚后序遍历的右子树开始位置
                post[i-rootIndex-1] = postorder[i-1];
            }
            root.right = buildTree(in, post);
        }else{
            root.right = null;
        }

        return root;
    }

}

