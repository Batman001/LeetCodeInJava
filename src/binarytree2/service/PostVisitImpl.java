package binarytree2.service;

import binarytree2.MyNode;

/**
 * @author Batman on 2018/12/19.
 */
public class PostVisitImpl implements VisitAll {
    @Override
    public void visitTree(MyNode mynode) {
        if(mynode == null){
            return;
        }
        visitTree(mynode.getLeft());
        visitTree(mynode.getRight());
        mynode.visit();
    }
}