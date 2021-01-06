package com.leetcode.train.binarytree;

import com.leetcode.train.binarytree.service.FunctionTreeNode;
import java.util.List;

/**
 * @author Batman
 * Date 2019/12/2 10:20
 * Description
 */
public class Test {


    public static void main(String[] args) {
        FunctionTreeNode functionTreeNode = new FunctionTreeNode();
        String treeArrays = "[1,2,3,4,null,10,9,null,7,8,12]";
        TreeNode root = functionTreeNode.stringToTreeNode(treeArrays);
        List<List<Integer>> levelRes = functionTreeNode.levelOrder(root);

        System.out.println("使用字符串重建二叉树的层次遍历结果为: ");
        System.out.println(levelRes);


        System.out.println(functionTreeNode.findPath(root, 2));



    }

}
