package binaryTree2.service;

import binaryTree2.MyNode;

/**
 * Created by sunchao on 2018/12/19.
 */
public class PreVisitImpl implements VisitAll {
    @Override
    public void visitTree(MyNode mynode) {
        if (mynode == null){
            return;
        }
        mynode.visit();
        visitTree(mynode.getLeft());
        visitTree((mynode.getRight()));
    }
}
