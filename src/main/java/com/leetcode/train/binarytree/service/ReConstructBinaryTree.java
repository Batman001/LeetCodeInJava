package com.leetcode.train.binarytree.service;

import com.leetcode.train.binarytree.TreeNode;

/**
 * @author Batman on 2018/12/19.
 * @author Batman
 */
public class ReConstructBinaryTree {

    /**
     * 根据二叉树前序和中序遍历结果,构建二叉树
     * @param preOrder 前序遍历数组
     * @param inOrder 中序遍历数组
     * @return 返回重建后的二叉树的根节点
     */
    private TreeNode reConstructBinaryTree(int[] preOrder, int[] inOrder) {

        /* 前序的第一个数定根*/
        TreeNode root = new TreeNode(preOrder[0]);
        int len = preOrder.length;
        //当只有一个数的时候
        if(len==1){
            root.left = null;
            root.right = null;
            return root;
        }
        //找到中序中的根位置
        int rootVal = root.val;
        int rootIndex;

        for(rootIndex=0; rootIndex<len; rootIndex++){
            if(rootVal == inOrder[rootIndex]){
                break;
            }
        }

        //创建左子树
        if(rootIndex > 0){
            // 左子树的前序遍历结果
            int[] preLeftPart = new int[rootIndex];
            for(int i=0; i<rootIndex; i++){
                preLeftPart[i] = preOrder[i+1];
            }
            // 左子树中序遍历结果
            int[] inLeftPart = new int[rootIndex];
            for(int j=0; j<rootIndex; j++){
                inLeftPart[j] = inOrder[j];
            }
            // 递归调用重建二叉树的函数方法
            root.left=reConstructBinaryTree(preLeftPart, inLeftPart);
        }else{
            root.left=null;
        }
        //创建右子树
        if(len - rootIndex - 1 > 0){
            int[] preRightPart = new int[len-rootIndex-1];
            int[] inRightPart = new int[len-rootIndex-1];
            for(int j=rootIndex+1; j<len; j++){
                inRightPart[j-rootIndex-1] = inOrder[j];
                preRightPart[j-rootIndex-1] = preOrder[j];
            }
            root.right=reConstructBinaryTree(preRightPart, inRightPart);
        }else{
            root.right=null;
        }

        return root;
    }

    public static void main(String[] args) {
        int[] preOrder = new int[]{3,9,20,15,7};
        int[] inOrder = new int[]{9,3,15,20,7};
        TreeNode root = new ReConstructBinaryTree().reConstructBinaryTree(preOrder, inOrder);

        Visit v1 = new PrevVisitInStackImpl();
        Visit v2 = new MiddleVisitImpl();
        Visit v3 = new PostVisitImpl();
        System.out.printf("重建后的树的前序遍历为:\t");
        v1.visitTree(root);
        System.out.println();

        System.out.printf("重建后的树的中序遍历为:\t");
        v2.visitTree(root);
        System.out.println();

        System.out.printf("重建后的树的后序遍历为:\t");
        v3.visitTree(root);

    }

}
