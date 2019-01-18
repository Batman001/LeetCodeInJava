package binaryTree2.service;

import binaryTree2.MyNode;

/**
 * Created by Batman on 2018/12/19.
 */
public class InVisitImpl implements VisitAll {
    @Override
    public void visitTree(MyNode mynode) {
        if(mynode == null){
            return;
        }
        visitTree(mynode.getLeft());
        mynode.visit();
        visitTree(mynode.getRight());

    }
}
