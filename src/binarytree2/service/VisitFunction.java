package binarytree2.service;

import binarytree2.MyNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Batman on 2018/12/19.
 */
public class VisitFunction {

    /**
     * 递归遍历二叉树
     */
    /**
     * 递归前序遍历
     * @param root
     */
    public void preVisit(MyNode root){
        if (root == null){
            return ;
        }
        root.visit();
        preVisit(root.getLeft());
        preVisit(root.getRight());
    }


    /**
     * 递归中序遍历
     * @param root
     */
    public void middleVisit(MyNode root){
        if(root == null){
            return;
        }
        middleVisit(root.getLeft());
        root.visit();
        middleVisit(root.getRight());
    }


    /**
     * 递归后序遍历
     * @param root
     */
    public void postVisit(MyNode root){
        if(root == null){
            return;
        }
        postVisit(root.getLeft());
        postVisit(root.getRight());
        root.visit();
    }


    /**
     * 递归层次遍历
     * @param root
     */
    public void levelVisit(MyNode root){
        if (root == null){
            return;
        }
        int TreeDepth = getMaxDepth(root);
        for(int i=1; i<=TreeDepth; i++){
            visitLevel(root, i);
        }
    }

    private void visitLevel(MyNode root, int level) {
        if(root == null || level < 1){
            return;
        }
        if(level == 1){
            root.visit();
        }
        visitLevel(root.getLeft(), level - 1);
        visitLevel(root.getRight(), level - 1);
    }

    public int getMaxDepth(MyNode root) {
        if(root == null){
            return 0;
        }
        int left = getMaxDepth(root.getLeft());
        int right = getMaxDepth(root.getRight());
        return Math.max(left , right) + 1;
    }

    /**
     * 非递归实现二叉树先序遍历
     * @param root
     */

    public void preVisit_(MyNode root){
        Deque<MyNode> deque = new ArrayDeque<>();
        while(root!=null || deque.size() > 0){
            while(root != null){
                root.visit();
                deque.push(root);
                root = root.getLeft();
            }
            if(deque.size() > 0){
                root = deque.pop();
                root = root.getRight();
            }
        }

    }


    /**
     * 非递归实现二叉树中序遍历
     * @param root
     */

    public void middleVisit_(MyNode root){
        Deque<MyNode> deque = new ArrayDeque<>();
        while(root!=null || deque.size() > 0){
            while(root!=null){
                deque.push(root);
                root = root.getLeft();
            }

            if(deque.size() > 0){
                root = deque.pop();
                root.visit();
                root = root.getRight();
            }
        }
    }

    /**
     * 非递归实现二叉树后序遍历
     * @param root
     */
    public void postVisit_(MyNode root){
        Deque<MyNode> deque = new ArrayDeque<>();
        Deque<MyNode> deque_ = new ArrayDeque<>();
        /*
        deque首先遍历右子树,然后左子树
        deque_存放每次push进栈的节点
        然后让deque_全部弹栈即为后序遍历过程
         */


        while(root != null || deque.size() > 0){
            while(root != null){
                deque.push(root);
                deque_.push(root);
                root = root.getRight();
            }

            if(deque.size() > 0){
                root = deque.pop();
                root = root.getLeft();
            }
        }


        /*
        将deque_全部元素进行弹栈操作
         */
        while(deque_.size() > 0){
            root = deque_.pop();
            root.visit();
        }
    }

}


