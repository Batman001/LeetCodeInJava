package com.leetcode.train.binarytree.service;

import com.leetcode.train.binarytree.InitTree;
import com.leetcode.train.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Batman on 2018/12/19.
 */
public class PrintPathRootToNode {

    private static ArrayList path;
    private Boolean printPath(TreeNode root, TreeNode dest){
        if(root==null) return false;
        if(root==dest||printPath(root.left,dest)||printPath(root.right,dest)){
            path.add(root.val);
            return true;
        }
        return false;
    }
    public static void main (String[] args)
    {
        TreeNode root = InitTree.init();
        PrintPathRootToNode i = new PrintPathRootToNode();
        path = new ArrayList();
        i.printPath(root,root.left.right);
        Collections.reverse(path);
        System.out.println(" Path " + path);



    }
}

