package binaryTree2.service;

/**
 * Created by sunchao on 2018/12/19.
 */

import binaryTree2.MyNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by sunchao on 2017/11/7.
 * 中序遍历非递归实现
 */

public class MiddleVisitImpl implements VisitAll {
    @Override
    public void visitTree(MyNode mynode) {
        Deque<MyNode> deque = new ArrayDeque<>();
        while(mynode != null || deque.size()>0){
            while(mynode != null){
                deque.push(mynode);
                mynode = mynode.getLeft();
            }
            if(deque.size() > 0){
                mynode = deque.pop();
                mynode.visit();
                mynode = mynode.getRight();
            }
        }

    }
}

