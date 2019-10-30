package com.leetcode.train.binarytree2.service;

import com.leetcode.train.binarytree2.MyNode;

/**
 * @author Batman on 2018/12/19.
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
