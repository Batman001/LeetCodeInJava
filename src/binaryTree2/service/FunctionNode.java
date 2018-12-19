package binaryTree2.service;

import binaryTree2.MyNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by sunchao on 2018/12/19.
 */
public class FunctionNode {

    /**
     * 计算第K层节点的数量
     * @param mynode
     * @param k
     * @return
     */
    public int numOfKLevelNode(MyNode mynode, int k) {
        if (mynode == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int numLeft = numOfKLevelNode(mynode.getLeft(), k - 1);
        int numRight = numOfKLevelNode(mynode.getRight(), k - 1);
        return numLeft + numRight;

    }




    /**
     * 计算二叉树的最深度
     * @param mynode
     * @return
     */
    public int getMaxDepth(MyNode mynode) {
        if (mynode == null) {
            return 0;
        }
        int leftDepth = getMaxDepth(mynode.getLeft());
        int rightDepth = getMaxDepth(mynode.getRight());
        return Math.max(leftDepth, rightDepth) + 1;

    }



    /**
     * 计算二叉树的最浅深度
     * @param root
     * @return
     */
    public int getMin(MyNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        }
        return Math.min(getMin(root.getLeft()), getMin(root.getRight())) + 1;
    }



    /**
     * 二叉树节点数量
     * @param root
     * @return
     */
    public int numOfNode(MyNode root) {
        if (root == null) {
            return 0;
        }
        int left = numOfNode(root.getLeft());
        int right = numOfNode(root.getRight());
        return left + right + 1;
    }



    /**
     * 前序递归遍历二叉树
     * @param root
     */
    public void PreVisit(MyNode root) {
        if (root == null) {
            return;
        }
        root.visit();
        PreVisit(root.getLeft());
        PreVisit(root.getRight());

    }


    /**
     * 中序递归遍历二叉树
     * @param root
     */
    public void MiddleVisit(MyNode root) {
        if (root == null) {
            return;
        }
        MiddleVisit(root.getLeft());
        root.visit();
        MiddleVisit(root.getRight());
    }


    /**
     * 后序递归遍历二叉树
     * @param root
     */
    public void PostVisit(MyNode root) {
        if (root == null) {
            return;
        }
        PostVisit(root.getLeft());
        PostVisit(root.getRight());
        root.visit();

    }


    /**
     * 层次递归遍历二叉树
     * @param root
     */
    public void LevelVisit(MyNode root) {
        if (root == null) {
            return;
        }
        int depth = getMaxDepth(root);
        for (int i = 1; i <= depth; i++) {
            VisitLevel(root, i);
        }
    }

    private void VisitLevel(MyNode root, int level) {
        if (root == null || level < 1) {
            return;
        }
        if (level == 1) {
            root.visit();
            return;
        }

        VisitLevel(root.getLeft(), level - 1);
        VisitLevel(root.getRight(), level - 1);
    }



    /**
     * 非递归前序遍历二叉树
     * @param root
     */
    public void PreVisit_(MyNode root) {
        Deque<MyNode> deque = new ArrayDeque<>();
        while (root != null || deque.size() > 0) {
            while (root != null) {
                root.visit();
                deque.push(root);
                root = root.getLeft();
            }
            if (deque.size() > 0) {
                root = deque.pop();
                root = root.getRight();
            }
        }

    }


    /**
     * 非递归中序遍历二叉树
     * @param root
     */
    public void MiddleVisit_(MyNode root) {
        Deque<MyNode> deque = new ArrayDeque<>();
        while (root != null || deque.size() > 0) {
            while (root != null) {
                deque.push(root);
                root = root.getLeft();
            }

            if (deque.size() > 0) {
                root = deque.pop();
                root.visit();
                root = root.getRight();
            }
        }
    }

    /**
     * 非递归后序遍历二叉树
     * @param root
     */
    public void PostVisit_(MyNode root) {
        Deque<MyNode> deque = new ArrayDeque<>();
        Deque<MyNode> reverseDeque = new ArrayDeque<>();
        while (root != null || deque.size() > 0) {
            while (root != null) {
                deque.push(root);
                reverseDeque.push(root);
                root = root.getRight();
            }
            if (deque.size() > 0) {
                root = deque.pop();
                root = root.getLeft();
            }
        }

        while (reverseDeque.size() > 0) {
            root = reverseDeque.pop();
            root.visit();
        }
    }


}

