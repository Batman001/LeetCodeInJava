package binarytree.service;

import binarytree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Batman on 2018/12/19.
 */
public class PrintPathRootToNode {

    public static ArrayList path;
    public Boolean printPath(TreeNode root, TreeNode dest){
        if(root==null) return false;
        if(root==dest||printPath(root.left,dest)||printPath(root.right,dest)){
            //System.out.print("  " + root.data);
            path.add(root.val);
            return true;
        }
        return false;
    }
    public static void main (String[] args) throws java.lang.Exception
    {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode (2);
        root.right = new TreeNode (3);
        TreeNode n1 = new TreeNode (4);
        root.left.left = n1;
        root.left.right = new TreeNode (5);
        TreeNode n2 = new TreeNode (8);
        root.left.right.right = n2;
        root.left.right.left = new TreeNode (7);

        PrintPathRootToNode i = new PrintPathRootToNode();
        path = new ArrayList();
        i.printPath(root,n2);
        Collections.reverse(path);
        System.out.println(" Path " + path);
    }
}

