package binaryTree2;

import binaryTree2.service.BackVisitImpl;
import binaryTree2.service.FunctionNode;
import binaryTree2.service.VisitAll;
import binaryTree2.service.VisitFunction;

/**
 * Created by Batman on 2018/12/19.
 */
public class BinTree {

    public static void main(String[] args) {
        MyNode rootFact = InitTree.init();
        VisitAll va = new BackVisitImpl();
        FunctionNode fc = new FunctionNode();


        VisitFunction vf = new VisitFunction();
//        int numbers = fc.numOfKLevelNode(rootFact, 4);
//        System.out.println("第2层一共有节点数量为 "+ numbers);
//
//        int depth = fc.getMaxDepth(rootFact);
//        System.out.println("二叉树最深为 "+depth);
//
//
//
//        System.out.println("二叉树最浅为 "+fc.getMaxDepth(rootFact));
//
//        System.out.println("二叉树节点数量为 "+fc.numOfNode(rootFact));
//
//        System.out.print("前序遍历为: ");
//        fc.PreVisit(rootFact);
//        System.out.print("\n");
//
//
//        System.out.print("中序遍历为: ");
//        fc.MiddleVisit(rootFact);
//        System.out.print("\n");
//
//
//        System.out.print("后序遍历为: ");
//        fc.PostVisit(rootFact);
//        System.out.print("\n");
//
//
//        System.out.print("层次遍历为: ");
//        fc.LevelVisit(rootFact);
//        System.out.print("\n");
//
//
//        System.out.print("前序非递归遍历为: ");
//        fc.PreVisit_(rootFact);
//        System.out.print("\n");
//
//
//        System.out.print("中序非递归遍历为: ");
//        fc.MiddleVisit_(rootFact);
//        System.out.print("\n");
//
//
//        System.out.print("后序非递归遍历为: ");
//        fc.PostVisit_(rootFact);
//        System.out.print("\n");

        System.out.printf("使用接口的后序遍历结果为: ");
        va.visitTree(rootFact);
        System.out.println();

        System.out.print("递归前序遍历为: ");
        vf.preVisit(rootFact);
        System.out.print("\n");

        System.out.print("递归中序遍历为: ");
        vf.middleVisit(rootFact);
        System.out.print("\n");


        System.out.print("递归后序遍历为: ");
        vf.postVisit(rootFact);
        System.out.print("\n");


        System.out.print("递归层次遍历为: ");
        vf.levelVisit(rootFact);
        System.out.print("\n");

        System.out.println("MaxDepth is "+vf.getMaxDepth(rootFact));

        System.out.print("非递归前序遍历为: ");
        vf.preVisit_(rootFact);
        System.out.print("\n");

        System.out.print("非递归中序遍历为: ");
        vf.middleVisit_(rootFact);
        System.out.print("\n");


        System.out.print("非递归后序遍历为: ");
        vf.postVisit_(rootFact);
        System.out.print("\n");



    }

}
