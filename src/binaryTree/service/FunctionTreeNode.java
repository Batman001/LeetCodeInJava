package binaryTree.service;

/**
 * Created by sunchao on 2018/12/19.
 * @author Batman
 * 二叉排序树的各种方法类
 */

import binaryTree.TreeNode;

import java.util.*;

public class FunctionTreeNode {

    /**
     * 求两个二叉树节点的最低公共祖先节点
     * @param root
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode getLastCommonParent(TreeNode root, TreeNode t1, TreeNode t2) {
        if (findNode(root.getLeft(), t1)) {
            if (findNode(root.getRight(), t2)) {
                return root;
            }
            else {
                return getLastCommonParent(root.getLeft(), t1, t2);
            }
        }
        else {
            if (findNode(root.getLeft(), t2)) {
                return root;
            }
            else {
                return getLastCommonParent(root.getRight(), t1, t2);
            }
        }
    }

    /**
     * 查找节点node是否在当前 二叉树中
     * @param root
     * @param node
     * @return
     */
    public boolean findNode(TreeNode root, TreeNode node) {
        if (root == null || node == null) {
            return false;
        }
        if (root == node) {
            return true;
        }
        boolean found = findNode(root.getLeft(), node);
        if (!found) {
            found = findNode(root.getRight(), node);
        }
        return found;
    }


    /**
     * Insert TreeNode Into 二叉排序树
     * @param root
     * @param InsertNode
     */
    public void BSTInsert(TreeNode root, TreeNode InsertNode){
        if(InsertNode.getVal() < root.getVal()){
            if(root.getLeft()!=null){
                BSTInsert(root.getLeft(), InsertNode);
            }
            else{
                root.setLeftTreeNode(InsertNode);
            }
        }
        else {
            if (root.getRight() != null) {
                BSTInsert(root.getRight(), InsertNode);
            } else {
                root.setRightTreeNode(InsertNode);
            }
        }
    }

    /**
     * 二叉查找树查找数值
     * @param root
     * @param value
     * @return
     */
    public  boolean BSTSearch(TreeNode root, int value) {
        if (root.getVal() == value) {
            return true;
        }

        if (root.getVal() > value) {
            if (root.getLeft() != null) {
                return BSTSearch(root.getLeft(), value);
            } else {
                return false;
            }
        }
        else {
            if (root.getRight() != null) {
                return BSTSearch(root.getRight(), value);
            } else {
                return false;
            }
        }
    }

    /**
     * 计算二叉树的最深度
     * @param mynode
     * @return
     */
    public int getMaxDepth(TreeNode mynode){
        if(mynode == null){
            return 0;
        }
        int leftDepth = getMaxDepth(mynode.getLeft());
        int rightDepth = getMaxDepth(mynode.getRight());
        return Math.max(leftDepth,rightDepth)+ 1;

    }



    /**
     * 层次遍历二叉树
     * @param root
     */
    public void levelVisit(TreeNode root){
        if (root == null){
            return ;
        }
        int depth = getMaxDepth(root);
        for(int i=1;i <= depth;i++){
            visitLevel(root, i);
        }
    }

    private void visitLevel(TreeNode root, int level) {
        if (root == null || level < 1){
            return ;
        }
        if(level == 1){
            root.visit();
            return;
        }

        visitLevel(root.getLeft(), level - 1);
        visitLevel(root.getRight(), level -1);
    }


    /**
     * 一颗二叉查找树,先进行前序遍历,然后将前序遍历结果依次插入到一棵新的二叉树中,将会的得到和原来一样的二叉查找树
     * @param root
     * @param result
     */

    public void preCollect(TreeNode root, ArrayList<TreeNode> result){
        if (root==null){
            return;
        }
        result.add(root);
        preCollect(root.getLeft(), result);
        preCollect(root.getRight(),result);

    }


    /**
     * 得到root根节点到某节点的路径信息
     * @param root
     * @param node
     * @param path
     * @return
     */
    public boolean getNodePath(TreeNode root,TreeNode node,Stack<Integer> path){

        if (root == node)
            return true;
        path.push(root.getVal());

        boolean found=false;

        if(root.getLeft() != null){
            found = getNodePath(root.getLeft(), node,path);
        }

        if(!found && root.getRight()!=null){
            found = getNodePath(root.getRight(),node, path);
        }
        if(!found)
            path.pop();
        return found;
    }


    /**
     * 查看二叉树中是否有节点node
     * @param root
     * @param node
     * @return
     */
    public boolean hasNode(TreeNode root, TreeNode node){
        if (root == null || node == null)
            return false;
        if(root == node)
            return true;

        boolean found = hasNode(root.getLeft(),node);
        if(!found)
            found = hasNode(root.getRight(),node);
        return found;
    }

    /**
     * 得到根节点root到node节点的路径信息
     * @param root
     * @param node
     * @param path
     * @return
     */
    public boolean getPath(TreeNode root, TreeNode node, ArrayList<TreeNode> path){

        if(root == node)
            return true;

        path.add(root);

        boolean found = false;
        if(root.getLeft() != null)
            found = getPath(root.getLeft(), node, path);

        if(!found && root.getRight()!=null)
            found = getPath(root.getRight(), node, path);
        if(!found) {
            path.remove(path.size() - 1);
        }
        return found;
    }

    /**
     * 二叉树的层次遍历非递归
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (root == null)
            return list;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (queue.size() != 0) {
            List<Integer> alist = new ArrayList<Integer>();
            for (TreeNode child : queue)
                alist.add(child.getVal());
            list.add(new ArrayList<Integer>(alist));
            Queue<TreeNode> queue2=queue;
            queue=new LinkedList<TreeNode>();
            for(TreeNode child:queue2){
                if (child.getLeft() != null)
                    queue.add(child.getLeft());
                if (child.getRight() != null)
                    queue.add(child.getRight());
            }
        }
        return list;
    }


    /**
     * 寻找二叉树的最近的公共祖先节点(递归方法)
     * @param root 二叉树的跟节点
     * @param p   二叉树某一节点p
     * @param q   二叉树某一节点q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root==p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.getLeft(), p, q);
        TreeNode right = lowestCommonAncestor(root.getRight(), p, q);
        if(left!=null && right!=null){
            return root;
        }
        return left==null ? right : left;
    }

    /**
     * 非递归方法实现寻找两个节点的最近公共祖先节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode getLowerCommonAncestorNonRecursive(TreeNode root, TreeNode p, TreeNode q){
        // 首先寻找根节点root到节点p的路径1
        ArrayList<TreeNode> path1 = new ArrayList<>();
        getPath(root, p, path1);
        path1.add(p);

        // 然后寻找根节点root到节点q的路径2
        ArrayList<TreeNode> path2 = new ArrayList<>();
        getPath(root, q, path2);
        path2.add(q);

        // 然后根据路径1和路径2,从后向前寻找出现的最先公共节点
        TreeNode result = null;
        for(int i=path1.size()-1;i>=0;i--){
            if(path2.contains(path1.get(i))){
                result = path1.get(i);
                break;
            }
        }
        return result;
    }

    /**
     * 寻找二叉排序树的两个节点的最近公共祖先节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorBSTTree(TreeNode root, TreeNode p, TreeNode q){
        TreeNode node = root;
        while(node!=null){
            int parentVal = node.val;
            if(p.val > parentVal && q.val > parentVal){
                node = node.right;
            }
            else if(p.val < parentVal && q.val < parentVal){
                node = node.left;
            }else{
                return node;
            }
        }
        return null;
    }
}






