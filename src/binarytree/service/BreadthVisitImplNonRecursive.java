package binarytree.service;

import binarytree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Batman create on 2019-07-24 14:53
 * 二叉树广度优先遍历（实质上为层次遍历））
 */
public class BreadthVisitImplNonRecursive implements Visit {
    /**
     * 二叉树遍历接口 使用队列数据结构实现层次遍历结果
     *
     * @param root 二叉树根节点
     */
    @Override
    public void visitTree(TreeNode root) {
        if(root == null) {
            return ;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(! queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            curNode.visit();
            if(curNode.left != null) {
                queue.add(curNode.left);
            }
            if(curNode.right != null) {
                queue.add(curNode.right);
            }
        }

    }


}
