package binarytree2.service;

import binarytree2.MyNode;

/**
 * @author Batman on 2018/12/19.
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
