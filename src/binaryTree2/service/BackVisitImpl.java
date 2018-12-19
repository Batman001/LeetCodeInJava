package binaryTree2.service;

import binaryTree2.MyNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by sunchao on 2018/12/19.
 */
public class BackVisitImpl implements VisitAll {
    @Override
    public void visitTree(MyNode mynode) {
        Deque<MyNode> deque = new ArrayDeque<>();
        Deque<MyNode> reverseDeque = new ArrayDeque<>();
        while(mynode != null || deque.size() > 0){
            while(mynode != null){
                deque.push(mynode);
                reverseDeque.push(mynode);
                mynode = mynode.getRight();
            }


            if(deque.size() > 0){
                mynode = deque.pop();
                mynode = mynode.getLeft();
            }
        }

        while(reverseDeque.size()>0){
            mynode = reverseDeque.pop();
            mynode.visit();
        }


    }
}

