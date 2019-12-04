package com.leetcode.train.binarytree;

import com.leetcode.train.binarytree.service.FunctionTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Batman on 2018/12/19.
 */
public class TreeNodeTest{
    public static void main(String[] args){
        TreeNode root = new TreeNode();
        TreeNode a = new TreeNode();
        TreeNode b = new TreeNode();
        TreeNode c = new TreeNode();
        TreeNode d = new TreeNode();
        TreeNode e = new TreeNode();

        root.val = 3;
        root.left = a;
        root.right = c;

        a.val = 4;
        a.left = b;
        a.right = d;

        b.val = 2;
        b.left = e;

        c.val = 8;
        d.val = 10;
        e.val = 6;


        ArrayList<Integer> result = root.postOrder(root);
        for(int data:result){
            System.out.println(data);
        }

        System.out.println("the max depth of com.leetcode.train.binarytree is "+ root.getMaxDepth(root));

        System.out.println("the min depth of com.leetcode.train.binarytree is "+ root.getMin(root));


        FunctionTreeNode fc = new FunctionTreeNode();
        TreeNode rebuildRoot = fc.stringToTreeNode("[3,5,1,6,2,0,8,null,null,7,4]");
        List<List<Integer>> res = fc.levelOrder(rebuildRoot);
        System.out.println("重建后二叉树的层次遍历结果为: " + res);

        System.out.println(fc.getLastCommonParent(rebuildRoot, rebuildRoot.left, rebuildRoot.left.right.right));



    }
}
