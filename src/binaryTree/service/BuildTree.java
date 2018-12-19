package binaryTree.service;

import binaryTree.TreeNode;

/**
 * Created by sunchao on 2018/12/19.
 * @author Batman
 */
public class BuildTree {
    public TreeNode BuildTree(int[] preorder, int[] inorder){
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
            root.left = BuildTree(pre, in);

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
            root.right = BuildTree(pre, in);

        }else{
            root.right = null;
        }

        return root;

    }

}

