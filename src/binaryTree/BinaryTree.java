package binaryTree;

import binaryTree.service.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunchao on 2018/12/19.
 */
public class BinaryTree {
    public static void main(String[] args){
        TreeNode root = InitTree.init();

        FunctionTreeNode fc = new FunctionTreeNode();

        // 插入3个节点到二叉排序树

        fc.BSTInsert(root,new TreeNode(13, null, null));
        fc.BSTInsert(root, new TreeNode(2,null,null));
        fc.BSTInsert(root, new TreeNode(89, null,null));

        // 使用preCollect 按照前序遍历方法收集二叉树的节点
        ArrayList<TreeNode> preresult = new ArrayList<TreeNode>();
        fc.preCollect(root,preresult);

        System.out.println("前序存储结果为:");
        for(int i=0;i<preresult.size();i++) {
            System.out.print(preresult.get(i).getVal()+" ");
        }
        System.out.println();

        System.out.print("中序遍历结果为按照升序排序结果,其排序结果为:");
        Visit v1 = new MiddleVisitImpl();
        v1.visitTree(root);
        System.out.println();

        System.out.println("后序遍历结果为:");
        Visit v2 = new PostVisitImpl();
        v2.visitTree(root);
        System.out.println();


        ArrayList<TreeNode> path = new ArrayList<>();
        if (fc.hasNode(root,root.getLeft())){
            fc.getPath(root,root.left,path);
        }
        path.add(root.left);
        System.out.printf("从根节点 %d 到某一节点 %d 路径为: ", root.val, root.left.val);
        for(TreeNode node:path){
            System.out.print(node.getVal()+" ");
        }



        System.out.print("\n");
        System.out.print("层次遍历非递归为：");
        Visit v = new LevelVisitImpl();
        v.visitTree(root);
        System.out.print("\n");


        System.out.print("层次遍历的递归为：");
        fc.levelVisit(root);
        System.out.print("\n");

        System.out.println("层次遍历的非递归为：");
        List<List<Integer>> result = fc.levelOrder(root);
        for(List<Integer> item:result){
            System.out.println(item);
        }

        System.out.println("得到两个节点的最近的公共祖先节点方法1:");
        TreeNode ancestor = fc.lowestCommonAncestor(root,root.getLeft().getLeft().getRight(), root.getLeft().getRight());
        System.out.println(ancestor.getVal());


        System.out.println("得到两个节点的最近的公共祖先节点方法2:");
        TreeNode ancestor2 = fc.getLastCommonParent(root,root.getLeft().getLeft().getRight(), root.getLeft().getRight());
        System.out.println(ancestor2.val);


        System.out.println("得到两个节点的最近的公共祖先节点方法3:");
        TreeNode ancestor3 = fc.getLowerCommonAncestorNonRecursive(root,root.getLeft().getLeft().getRight(), root.getLeft().getRight());
        System.out.println(ancestor3.val);


        System.out.println("判断二叉树是否为满二叉树,其结果为:" + root.isCompleteTreeNode(root));
        System.out.println();


        System.out.println("测试新的层次从叶节点遍历方法 levelOrderBottom");
        List<List<Integer>> levelOrderBottomResult = fc.levelOrderBottom(root);
        for(List<Integer> item:levelOrderBottomResult){
            System.out.println(item.toString());
        }

        System.out.println("测试新的层次遍历recursive方法 levelOrderBottom");
        List<List<Integer>> levelOrderResult = fc.levelOrderBottomNonRecursive(root);
        for(List<Integer> item:levelOrderResult){
            System.out.println(item.toString());
        }


        /*
        System.out.print("\n");
        boolean result = fc.BSTSearch(root, 13);
        System.out.println("13在该二叉树中查询结果为"+result);
        System.out.println("层次遍历结果为:");
        fc.LevelVisit(root);
        System.out.println();

        System.out.println("前序遍历结果为:");
        Visit v = new PreVisitImpl();
        v.visitTree(root);
        System.out.println();

        System.out.println("中序遍历结果为:");
        Visit v1 = new MiddleVisitImpl();
        v1.visitTree(root);
        System.out.println();

        System.out.println("后序遍历结果为:");
        Visit v2 = new PostVisitImpl();
        v2.visitTree(root);
        System.out.println();

        */


        //fc.getLastCommonParent(root, root.getLeft().getLeft().getRight(), root.getRight().getLeft());


        //fc.getLowestAncestor(root, root.getLeft().getLeft().getRight(), root.getRight().getLeft());




    }
}
