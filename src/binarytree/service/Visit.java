package binarytree.service;

import binarytree.TreeNode;

/**
 * @author Batman on 2018/12/19.
 */
public interface Visit {
    /**
     * 二叉树遍历接口
     * @param root 二叉树根节点
     */
    void visitTree(TreeNode root);
}
