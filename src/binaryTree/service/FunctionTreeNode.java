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
     *
     * @param root
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode getLastCommonParent(TreeNode root, TreeNode t1, TreeNode t2) {
        if (findNode(root.getLeft(), t1)) {
            if (findNode(root.getRight(), t2)) {
                return root;
            } else {
                return getLastCommonParent(root.getLeft(), t1, t2);
            }
        } else {
            if (findNode(root.getLeft(), t2)) {
                return root;
            } else {
                return getLastCommonParent(root.getRight(), t1, t2);
            }
        }
    }

    /**
     * 查找节点node是否在当前 二叉树中
     *
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
     *
     * @param root
     * @param InsertNode
     */
    public void BSTInsert(TreeNode root, TreeNode InsertNode) {
        if (InsertNode.getVal() < root.getVal()) {
            if (root.getLeft() != null) {
                BSTInsert(root.getLeft(), InsertNode);
            } else {
                root.setLeftTreeNode(InsertNode);
            }
        } else {
            if (root.getRight() != null) {
                BSTInsert(root.getRight(), InsertNode);
            } else {
                root.setRightTreeNode(InsertNode);
            }
        }
    }

    /**
     * 二叉查找树查找数值
     *
     * @param root
     * @param value
     * @return
     */
    public boolean BSTSearch(TreeNode root, int value) {
        if (root.getVal() == value) {
            return true;
        }

        if (root.getVal() > value) {
            if (root.getLeft() != null) {
                return BSTSearch(root.getLeft(), value);
            } else {
                return false;
            }
        } else {
            if (root.getRight() != null) {
                return BSTSearch(root.getRight(), value);
            } else {
                return false;
            }
        }
    }

    /**
     * 计算二叉树的最深度
     *
     * @param mynode
     * @return
     */
    public int getMaxDepth(TreeNode mynode) {
        if (mynode == null) {
            return 0;
        }
        int leftDepth = getMaxDepth(mynode.getLeft());
        int rightDepth = getMaxDepth(mynode.getRight());
        return Math.max(leftDepth, rightDepth) + 1;

    }


    /**
     * 计算二叉树的最小深度
     */
    public int getMinDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getMinDepth(root.getLeft());
        int rightDepth = getMaxDepth(root.getRight());
        return (leftDepth == 0 || rightDepth == 0) ? leftDepth + rightDepth + 1
                : Math.min(leftDepth, rightDepth) + 1;

    }

    /**
     * 层次遍历二叉树(递归实现)
     *
     * @param root
     */
    public void levelVisit(TreeNode root) {
        if (root == null) {
            return;
        }
        int depth = getMaxDepth(root);
        for (int i = 1; i <= depth; i++) {
            visitLevel(root, i);
        }
    }

    private void visitLevel(TreeNode root, int level) {
        if (root == null || level < 1) {
            return;
        }
        if (level == 1) {
            root.visit();
            return;
        }

        visitLevel(root.getLeft(), level - 1);
        visitLevel(root.getRight(), level - 1);
    }


    /**
     * 一颗二叉查找树,先进行前序遍历,然后将前序遍历结果依次插入到一棵新的二叉树中,将会的得到和原来一样的二叉查找树
     *
     * @param root
     * @param result
     */

    public void preCollect(TreeNode root, ArrayList<TreeNode> result) {
        if (root == null) {
            return;
        }
        result.add(root);
        preCollect(root.getLeft(), result);
        preCollect(root.getRight(), result);

    }


    /**
     * 得到root根节点到某节点的路径信息
     *
     * @param root
     * @param node
     * @param path
     * @return
     */
    public boolean getNodePath(TreeNode root, TreeNode node, Stack<Integer> path) {

        if (root == node)
            return true;
        path.push(root.getVal());

        boolean found = false;

        if (root.getLeft() != null) {
            found = getNodePath(root.getLeft(), node, path);
        }

        if (!found && root.getRight() != null) {
            found = getNodePath(root.getRight(), node, path);
        }
        if (!found)
            path.pop();
        return found;
    }


    /**
     * 查看二叉树中是否有节点node
     *
     * @param root
     * @param node
     * @return
     */
    public boolean hasNode(TreeNode root, TreeNode node) {
        if (root == null || node == null)
            return false;
        if (root == node)
            return true;

        boolean found = hasNode(root.getLeft(), node);
        if (!found)
            found = hasNode(root.getRight(), node);
        return found;
    }

    /**
     * 得到根节点root到node节点的路径信息
     *
     * @param root
     * @param node
     * @param path
     * @return
     */
    public boolean getPath(TreeNode root, TreeNode node, ArrayList<TreeNode> path) {

        if (root == node)
            return true;

        path.add(root);

        boolean found = false;
        if (root.getLeft() != null)
            found = getPath(root.getLeft(), node, path);

        if (!found && root.getRight() != null)
            found = getPath(root.getRight(), node, path);
        if (!found) {
            path.remove(path.size() - 1);
        }
        return found;
    }

    /**
     * 二叉树的层次遍历非递归
     *
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
            Queue<TreeNode> queue2 = queue;
            queue = new LinkedList<TreeNode>();
            for (TreeNode child : queue2) {
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
     *
     * @param root 二叉树的跟节点
     * @param p    二叉树某一节点p
     * @param q    二叉树某一节点q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.getLeft(), p, q);
        TreeNode right = lowestCommonAncestor(root.getRight(), p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    /**
     * 非递归方法实现寻找两个节点的最近公共祖先节点
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode getLowerCommonAncestorNonRecursive(TreeNode root, TreeNode p, TreeNode q) {
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
        for (int i = path1.size() - 1; i >= 0; i--) {
            if (path2.contains(path1.get(i))) {
                result = path1.get(i);
                break;
            }
        }
        return result;
    }

    /**
     * 寻找二叉排序树的两个节点的最近公共祖先节点
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorBSTTree(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            int parentVal = node.val;
            if (p.val > parentVal && q.val > parentVal) {
                node = node.right;
            } else if (p.val < parentVal && q.val < parentVal) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }


    /**
     * Leetcode 107 Binary Tree Level Order Traversal II
     * create at 2018.12.21 Batman
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // 设置容器存储层节点
        // 处理层节点,从root节点开始
        List<TreeNode> levelNodes = new ArrayList<>();
        levelNodes.add(root);

        // 处理当前层节点,并生成下层节点列表
        while (levelNodes.size() != 0) {
            // 设置下层节点的存储容器
            List<TreeNode> newLevelNodes = new ArrayList<>();
            List<Integer> currentNodeVal = new ArrayList<>();
            result.add(currentNodeVal);

            for (TreeNode node : levelNodes) {
                currentNodeVal.add(node.val);

                if (node.left != null) {
                    newLevelNodes.add(node.left);
                }
                if (node.right != null) {
                    newLevelNodes.add(node.right);
                }
            }
            levelNodes = newLevelNodes;
        }

        // 将result中值进行逆置处理
        for (int i = 0; i < result.size() / 2; i++) {
            List<Integer> temp = result.get(i);
            result.set(i, result.get(result.size() - i - 1));
            result.set(result.size() - i - 1, temp);
        }

        return result;

    }

    /**
     * 递归实现层次遍历二叉树
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottomNonRecursive(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        helper(root, 0, result);

        // 将result中值进行逆置处理
        for (int i = 0; i < result.size() / 2; i++) {
            List<Integer> temp = result.get(i);
            result.set(i, result.get(result.size() - i - 1));
            result.set(result.size() - i - 1, temp);
        }

        return result;
    }

    private void helper(TreeNode root, int depth, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        if (result.size() == depth) {
            List<Integer> currentLevelNodesVals = new ArrayList<>();

            result.add(currentLevelNodesVals);
        }
        helper(root.left, depth + 1, result);
        result.get(depth).add(root.val);
        helper(root.right, depth + 1, result);
    }

    /**
     * Leetcode 112 Path Sum 判断从根节点到叶节点其路径之和是否可以得到
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        return root == null ? false : checkPathSum(root, sum, 0);
    }

    private boolean checkPathSum(TreeNode root, int sum, int brachSum) {
        if (root == null)
            return sum == brachSum;
        brachSum += root.val;

        if (root.left == null) {
            return checkPathSum(root.right, sum, brachSum);
        } else if (root.right == null) {
            return checkPathSum(root.left, sum, brachSum);
        } else {
            return checkPathSum(root.left, sum, brachSum) || checkPathSum(root.right, sum, brachSum);
        }
    }


    /**
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        sum -= root.val;
        // 如果sum为0 说明为叶子节点
        if(sum == 0 && root.left == null && root.right == null){
            return true;
        }
        return hasPathSum2(root.left, sum) || hasPathSum(root.right, sum);
    }

    /**
     * Leetcode 114 Flatten Binary Tree to Linked List
     * @param root
     */

    /**
     * lastNode为空节点,用于对树进行连接
     */
    private TreeNode lastNode = null;
    public void flatten (TreeNode root){
        if(root == null){
            return;
        }
        if(lastNode != null){
            lastNode.left = null;
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }


    /**
     * flattenNew 采用divide and conquer方法进行拉平
     * @param root
     */
    public void flattenNew(TreeNode root){
        helper(root);
    }
    private TreeNode helper(TreeNode root){
        if(root == null){
            return null;
        }

        TreeNode leftLast = helper(root.left);
        TreeNode rightLast = helper(root.right);

        // connect laftLast to root.right
        if(leftLast != null){
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        if(rightLast != null){
            return rightLast;
        }
        if(leftLast != null){
            return leftLast;
        }

        return root;
    }


    /**
     * 设置前置空节点,link 二叉树拉平后的结构
     */
    private TreeNode prev = null;

    /**
     * 根据Python的递归解法进行拉平 java实现
     * @param root
     */
    public void flattenSecond(TreeNode root){
        if(root == null){
            return ;
        }
        prev = root;
        flattenSecond(root.left);

        // store right subtree and move previous left to right
        TreeNode temp = root.right;

        root.right = root.left;

        // delete link to previous left
        root.left = null;


        // link last of flattened left to root of right subtree
        prev.right = temp;
        flattenSecond(temp);

    }

    /**
     * 将二叉树转换成字符串形式(其实为非递归层次遍历)
     * @param root
     * @return
     */
    public String treeNodeToString(TreeNode root){
        if(root == null){
            return "[]";
        }
        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.remove();

            if(node == null){
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length()-2) + "]";

    }

    /**
     * 判断两个二叉树是不是完全相同
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p!=null && q!=null && p.val == q.val){
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        else if(p==null && q==null){
            return true;
        }
        return false;
    }

    /**
     * Leetcode 98 Validate Binary Search Tree
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root){
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        for(int i=0;i<list.size()-1;i++){
            if(list.get(i) >= list.get(i+1)){
                return false;
            }
        }
        return true;
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if(node == null){
            return;
        }
        // 递归实现中序遍历 并将结果存储至list容器
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    /**
     * 反转二叉树
     */
    public TreeNode invertTree(TreeNode root){
       if(root != null){
           TreeNode temp = root.left;
           root.left = root.right;
           root.right = temp;
       }
        if(root.left != null){
            invertTree(root.left);
        }
        if(root.right != null){
            invertTree(root.right);
        }
        return root;
    }

    /**
     * LeetCode 617 Merge Two Binary Trees
     * Given two binary trees and imagine that when you put one of them to cover the other,
     * some nodes of the two trees are overlapped while the others are not.
     * You need to merge them into a new binary tree.
     * The merge rule is that if two nodes overlap,
     * then sum node values up as the new value of the merged node.
     * Otherwise, the NOT null node will be used as the node of new tree.
     * @param t1 TreeNode t1
     * @param t2 TreeNode t2
     * @return TreeNode
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2){
        if(t1 == null){
            return t2;
        }
        if(t2 == null){
            return t1;
        }
        TreeNode newRoot = new TreeNode(-1);
        newRoot.val = t1.val + t2.val;
        newRoot.left = mergeTrees(t1.left, t2.left);
        newRoot.right = mergeTrees(t1.right, t2.right);

        return newRoot;
    }


    List<TreeNode> treeList = new ArrayList<>();

    /**
     * 538. Convert BST to Greater Tree
     * 中序遍历到列表中
     * 自然的想到先用先序遍历把树转成数组，就得到了一个从小到大排序的数组，然后从后往前累加就可以了。
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        /*
        思路：采取先序遍历的方式，把所有节点加入到列表中，然后从后面起依次向前加即可
        */
        if(root == null || root.left == null && root.right == null){
            return root;
        }
        inorder(root);


        int len = treeList.size();
        // 保存所有比当前节点大（即list中排在当前节点之后的元素）的和
        int sum = treeList.get(len - 1).val;

        for(int i = len - 2; i >= 0; i--){
            int val = treeList.get(i).val;
            treeList.get(i).val += sum;
            sum += val;
        }

        return root;

    }
    private void inorder(TreeNode root){
        if(root.left != null){
            inorder(root.left);
        }
        treeList.add(root);
        if(root.right != null){
            inorder(root.right);
        }
    }


    int sum=0;

    /**
     * 右根左 遍历顺序 然后进行累加即可
     * LeetCode 538 Convert BST to Greater Tree
     * @param root
     * @return
     */
    public TreeNode convertBST2(TreeNode root) {
        convert(root);
        return root;
    }

    public void convert(TreeNode cur) {
        if (cur == null) return;
        convert(cur.right);
        cur.val += sum;
        sum = cur.val;
        convert(cur.left);
    }

    /**
     * LeetCode 572 Subtree of Another Tree
     * Given two non-empty binary trees s and t,
     * check whether tree t has exactly the same structure
     * and node values with a subtree of s.
     * A subtree of s is a tree consists of a node in s
     * and all of this node's descendants.
     * The tree s could also be considered as a subtree of itself.
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t){
        if(s == null){
            return false;
        }
        if(isSameTree(s, t)){
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    /**
     * LeetCode Maximum Binary Tree
     * The root is the maximum number in the array.
     * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
     * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
     * 思路:
     * 1. 创建一个根节点,其值为数组的最大值
     * 2. 创建根节点的左节点,左节点的值为数组左部分的最大值
     * 3. 创建根节点的有节点,右节点的值为数组有部分的最大值
     * @param nums
     * @return
     */
    public static TreeNode constructMaximumBinaryTree(int[] nums){

        return constructor(nums, 0, nums.length-1);

    }

    public static TreeNode constructor(int[] nums, int left, int right){
        if (left > right){
            return null;
        }
        int max = maxArrayIndex(nums, left, right);
        TreeNode root = new TreeNode(nums[max]);
        root.left = constructor(nums, left, max-1);
        root.right = constructor(nums, max+1, right);
        return root;
    }

    /**
     * 获取数组nums中从left索引到right索引范围内的最大值得索引
     * @param nums 目标数组
     * @param left 取目标数组的左边开始边界
     * @param right 目标数组的右边结束边界
     * @return 截取边界内的最大值的索引
     */
    public static int maxArrayIndex(int[] nums, int left, int right){
        int point = nums[left];
        int max_index = left;
        for(int i=left+1; i<=right; i++){
            if (nums[i] > point){
                max_index = i;
                point = nums[i];
            }else{
                continue;
            }
        }
        return max_index;
    }

}
