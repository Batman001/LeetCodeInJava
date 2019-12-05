package com.leetcode.train.binarytree.service;

import com.leetcode.train.binarytree.TreeNode;

import java.util.*;

/**
 * @author Batman on 2018/12/19.
 * 二叉排序树的各种方法类
 */
public class FunctionTreeNode {

    /**
     * 求两个二叉树节点的最低公共祖先节点
     * leetcode 238
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
     *
     * @param root 二叉树根节点
     * @param t1 二叉树结点 t1
     * @param t2 二叉树结点 t2
     * @return 最低公共祖先结点
     */
    public TreeNode getLastCommonParent(TreeNode root, TreeNode t1, TreeNode t2) {
        if (findNode(root.left, t1)) {
            if (findNode(root.right, t2)) {
                return root;
            } else {
                return getLastCommonParent(root.left, t1, t2);
            }
        } else {
            if (findNode(root.left, t2)) {
                return root;
            } else {
                return getLastCommonParent(root.right, t1, t2);
            }
        }
    }

    /**
     * 查找节点node是否在当前 二叉树中
     *
     * @param root 二叉树根节点
     * @param node 待查找结点
     * @return 在当前二叉树中 则返回True 否则返回False
     */
    private boolean findNode(TreeNode root, TreeNode node) {

        if (root == null || node == null) {
            return false;
        }
        if (root == node) {
            return true;
        }
        boolean found = findNode(root.left, node);

        if (!found) {
            found = findNode(root.right, node);
        }
        return found;
    }


    /**
     * Insert TreeNode Into 二叉排序树
     *
     * @param root 待插入二叉排序树的根节点
     * @param InsertNode 待插入二叉树节点
     */
    public void BSTInsert(TreeNode root, TreeNode InsertNode) {
        if (InsertNode.val < root.val) {
            if (root.left != null) {
                BSTInsert(root.left, InsertNode);
            } else {
                root.setLeftTreeNode(InsertNode);
            }
        } else {
            if (root.right != null) {
                BSTInsert(root.right, InsertNode);
            } else {
                root.setRightTreeNode(InsertNode);
            }
        }
    }

    /**
     * 二叉查找树查找数值
     * @param root 二叉树根节点
     * @param value 二叉树节点的value
     * @return true or false
     */
    private boolean BSTSearch(TreeNode root, int value) {
        if (root.val == value) {
            return true;
        }

        if (root.val > value) {
            if (root.left != null) {
                return BSTSearch(root.left, value);
            } else {
                return false;
            }
        } else {
            if (root.right != null) {
                return BSTSearch(root.right, value);
            } else {
                return false;
            }
        }
    }

    /**
     * 计算二叉树的最大深度
     *
     * @param root 二叉树的根节点
     * @return 二叉树的最大深度
     */
    public int getMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getMaxDepth(root.left);
        int rightDepth = getMaxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;

    }


    /**
     * 计算二叉树的最小深度
     */
    public int getMinDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getMinDepth(root.left);
        int rightDepth = getMaxDepth(root.right);
        return (leftDepth == 0 || rightDepth == 0) ? leftDepth + rightDepth + 1
                : Math.min(leftDepth, rightDepth) + 1;

    }

    /**
     * 层次遍历二叉树(递归实现)
     * @param root 二叉树根节点
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

        visitLevel(root.left, level - 1);
        visitLevel(root.right, level - 1);
    }


    /**
     * 一颗二叉查找树,先进行前序遍历,然后将前序遍历结果依次插入到一棵新的二叉树中,将会的得到和原来一样的二叉查找树
     *
     * @param root 二叉树根节点
     * @param result 二叉树前序遍历 并保存至result中
     */
    public void preCollect(TreeNode root, ArrayList<TreeNode> result) {
        if (root == null) {
            return;
        }
        result.add(root);
        preCollect(root.left, result);
        preCollect(root.right, result);

    }


    /**
     * 得到root根节点到某节点的路径信息
     *
     * @param root 二叉树根节点
     * @param node 二叉树某节点
     * @param path 根结点到node结点的路径存储
     * @return true or false
     */
    private boolean getNodePath(TreeNode root, TreeNode node, Stack<Integer> path) {

        if (root == node) {
            return true;
        }
        path.push(root.val);

        boolean found = false;

        if (root.left != null) {
            found = getNodePath(root.left, node, path);
        }

        if (!found && root.right != null) {
            found = getNodePath(root.right, node, path);
        }
        if (!found) {
            path.pop();
        }
        return found;
    }


    /**
     * 查看二叉树中是否有节点node
     *
     * @param root 二叉树根节点
     * @param node 二叉树某节点 node
     * @return 返回该二叉树中是否包含节点node  如果包含返回true 反则返回false
     */
    public boolean hasNode(TreeNode root, TreeNode node) {
        if (root == null || node == null) {
            return false;
        }
        if (root == node) {
            return true;
        }
        boolean found = hasNode(root.left, node);
        if (!found) {
            found = hasNode(root.right, node);
        }
        return found;
    }

    /**
     * 得到根节点root到node节点的路径信息
     *
     * @param root 二叉树根节点root
     * @param node 二叉树某节点node
     * @param path 保存从根节点到某节点node的路径信息
     * @return 如果得到该路径返回true 否则返回false
     */
    public boolean getPath(TreeNode root, TreeNode node, ArrayList<TreeNode> path) {

        if (root == node) {
            return true;
        }
        path.add(root);

        boolean found = false;
        if (root.left != null) {
            found = getPath(root.left, node, path);
        }

        if (!found && root.right != null) {
            found = getPath(root.right, node, path);
        }
        if (!found) {
            path.remove(path.size() - 1);
        }
        return found;
    }


    /**
     * 非递归实现从根节点到某一节点的路径信息
     * @param root 根节点
     * @param node 某一节点
     * @return 列表存储从根节点到某一节点的路径信息
     */
    public List<Integer> searchNode(TreeNode root, TreeNode node){
        // TODO 目前存在空指针错误 未调好该bug
        List<Integer> res = new ArrayList<>();
        if(root == null || node == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        //上一次出栈的结点
        TreeNode pre = null;
        while(p != null || !stack.isEmpty()) {
            while(p != null) {
                stack.push(p);
                //这个while循环的思想还是一直往左找，找的过程结点入栈，如果找到了就打印输出并返回。
                if(p.val == node.val){
                    for(TreeNode curNode : stack){
                        res.add(curNode.val);
                    }
                    return res;
                }
                p = p.left;
            }
            //走到这一步说明栈顶元素的左子树为null，那么就开始往栈顶元素的右子树上去找。
            if(!stack.isEmpty()) {
                p = stack.peek();
                //如果栈顶元素的右子树为null，或者右子树被遍历过，则弹栈。
                while(p.right == null || pre != null && p.right == pre) {
                    pre = stack.pop();
                    p = stack.peek();
                }
                //继续遍历p的右子树
                p = p.right;
            }
        }
        return res;

    }

    /**
     * 二叉树的层次遍历非递归
     * leetcode 102
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
     * @param root 二叉树根节点
     * @return 返回二叉树的层次遍历的结果
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() != 0) {
            List<Integer> aList = new ArrayList<>();
            for (TreeNode child : queue) {
                aList.add(child.val);
            }
            list.add(new ArrayList<>(aList));
            // 引入临时Queue 用于记录下一层的节点信息
            Queue<TreeNode> tempQueue = queue;
            // 遍历完一层的节点后 将queue置空
            queue = new LinkedList<TreeNode>();
            for (TreeNode child : tempQueue) {
                if (child.left != null) {
                    queue.add(child.getLeft());
                }
                if (child.right != null) {
                    queue.add(child.right);
                }
            }
        }
        return list;
    }



    public List<List<Integer>> level_Order(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return null;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            List<TreeNode> curLevelNodes = new ArrayList<>();
            List<Integer> temp = new ArrayList<>();
            for(TreeNode node : queue) {
                temp.add(node.val);
            }
            res.add(new ArrayList<>(temp));


        }

        return res;
    }



    /**
     * 寻找二叉树的最近的公共祖先节点(递归方法)
     *
     * @param root 二叉树的跟节点
     * @param p    二叉树某一节点p
     * @param q    二叉树某一节点q
     * @return 二叉树最近公共祖先节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    /**
     * 非递归方法实现寻找两个节点的最近公共祖先节点
     *
     * @param root 二叉树根节点
     * @param p 求两个节点的最近公共祖先节点 的某节点p
     * @param q 求两个节点的最近公共祖先节点 的某节点q
     * @return 返回二叉树的两个节点的最近公共祖先节点
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
     * 递归算法实现寻找二叉查找树的两个节点的最近公共祖先节点
     *
     * @param root binary search tree 根节点
     * @param p TreeNode 二叉树某一节点p
     * @param q TreeNode 二叉树某一节点q
     * @return  返回二叉树的两个节点的最近公共祖先节点
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
     * 二叉树的层次遍历 II
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
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
     * leetcode 107 二叉树的层次遍历 II
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
     * @param root 二叉树根节点
     * @return 层次遍历二叉树的结果 使用列表保存层次遍历的二叉树
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
     * @param root 根节点
     * @param sum 从根节点到某叶子节点的路径之和 sum值
     * @return 如果可以得到返回true 否则返回false
     */
    private boolean hasPathSum(TreeNode root, int sum) {
        return root != null && checkPathSum(root, sum, 0);
    }

    private boolean checkPathSum(TreeNode root, int sum, int brachSum) {
        if (root == null) {
            return sum == brachSum;
        }
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
     * Leetcode 112 Path Sum 判断从根节点到叶节点其路径之和是否可以得到
     * @param root 根节点
     * @param sum 从根节点到某叶子节点的路径之和 sum值
     * @return 如果可以得到返回true 否则返回false
     */
    private boolean hasPathSum2(TreeNode root, int sum) {
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
     * @param root 二叉树根节点
     * lastNode为空节点,用于对树进行连接
     */
    private TreeNode lastNode = null;
    private void flatten(TreeNode root){
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
     * @param root 二叉树根节点
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
     * @param root 二叉树根节点
     */
    private void flattenSecond(TreeNode root){
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
     * @param root 根节点root
     * @return 二叉树转换成字符串形式
     */
    public String treeNodeToString(TreeNode root){
        if(root == null){
            return "[]";
        }
        StringBuilder output = new StringBuilder();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.remove();

            if(node == null){
                output.append("null, ");
                continue;
            }

            output.append(node.val).append(", ");
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length()-2) + "]";

    }


    /**
     * 根据字符串重建二叉树
     * @param input 输入字符串
     * @return 重建后的二叉树
     */
    public TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if(input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            if(index == parts.length) {
                break;
            }

            item = parts[index++].trim();
            if(!"null".equals(item)) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if(index == parts.length) {
                break;
            }

            item = parts[index++].trim();
            if(!"null".equals(item)) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }

        }
        return root;


    }

    /**
     * 判断两个二叉树是不是完全相同
     * @param p 二叉树某节点p
     * @param q 二叉树某节点q
     * @return 如果完全相同 返回true 否则 返回false
     */
    private boolean isSameTree(TreeNode p, TreeNode q){
        if(p!=null && q!=null && p.val == q.val){
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        else {
            return p == null && q == null;
        }
    }

    /**
     * Leetcode 98 Validate Binary Search Tree 验证二叉搜索树
     * https://leetcode-cn.com/problems/validate-binary-search-tree/
     * @param root 二叉树根节点
     * @return 如果是二叉搜索树 返回true 否则返回false
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
     * 翻转二叉树
     * @param root 二叉树根节点
     */
    private void invertTree(TreeNode root){
        if(root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if(root.left != null){
            invertTree(root.left);
        }
        if(root.right != null){
            invertTree(root.right);
        }
    }

    /**
     * 翻转二叉树
     * @param root 二叉树根节点
     * @return 返回已经翻转后的二叉树
     * leetcode 226 https://leetcode-cn.com/problems/invert-binary-tree/
     */
    private TreeNode invertTree_(TreeNode root) {
        if(root == null) {
            return null;
        }
        // 先保存原始右子树
        TreeNode right = root.right;

        // 翻转左子树 并赋值给root.right
        root.right = invertTree_(root.left);

        // 翻转预先保存的右子树 并赋值与左子树
        root.left = invertTree_(right);

        // 返回翻转后的二叉树根节点
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
    private TreeNode mergeTrees(TreeNode t1, TreeNode t2){
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


    private List<TreeNode> treeList = new ArrayList<>();

    /**
     * 538. https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
     * 把二叉搜索树转换为累加树
     * 中序遍历到列表中
     * 自然的想到先用先序遍历把树转成数组，就得到了一个从小到大排序的数组，然后从后往前累加就可以了。
     * @param root 二叉树根节点
     * @return 转换成累加树的根节点
     */
    public TreeNode convertBST(TreeNode root) {
        /*
        思路：采取先序遍历的方式，把所有节点加入到列表中，然后从后面起依次向前加即可
        */
        if(root == null || root.left == null && root.right == null){
            return root;
        }
        inOrder(root);


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
    private void inOrder(TreeNode root){
        if(root.left != null){
            inOrder(root.left);
        }
        treeList.add(root);
        if(root.right != null){
            inOrder(root.right);
        }
    }

    private int sum=0;

    /**
     * 右根左 遍历顺序 然后进行累加即可
     * LeetCode 538 Convert BST to Greater Tree
     * @param root 二叉树根节点
     * @return 返回累加树的根节点
     */
    public TreeNode convertBST2(TreeNode root) {
        convert(root);
        return root;
    }

    private void convert(TreeNode cur) {
        if (cur == null) {
            return;
        }
        convert(cur.right);
        cur.val += sum;
        sum = cur.val;
        convert(cur.left);
    }

    /**
     * LeetCode 572 另一个树的子树
     * https://leetcode-cn.com/problems/subtree-of-another-tree/
     * @param s 非空二叉树根节点s
     * @param t 非空二叉树根节点t
     * @return 如果t是s的子树 返回true 否则返回false
     */
    private boolean isSubtree(TreeNode s, TreeNode t){
        if(s == null){
            return false;
        }
        if(isSameTree(s, t)){
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    /**
     * LeetCode 654 最大二叉树
     * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
     *
     * 二叉树的根是数组中的最大元素。
     * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
     * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
     * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
     * 思路:
     * 1. 创建一个根节点,其值为数组的最大值
     * 2. 创建根节点的左节点,左节点的值为数组左部分的最大值
     * 3. 创建根节点的有节点,右节点的值为数组有部分的最大值
     * @param nums 待重建二叉树的数组
     * @return 创建成功后的二叉树的根节点
     */
    public static TreeNode constructMaximumBinaryTree(int[] nums){

        return constructor(nums, 0, nums.length-1);

    }

    private static TreeNode constructor(int[] nums, int left, int right){
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
    private static int maxArrayIndex(int[] nums, int left, int right){
        int point = nums[left];
        int maxIndex = left;
        for(int i=left+1; i<=right; i++){
            if (nums[i] > point){
                maxIndex = i;
                point = nums[i];
            }
        }
        return maxIndex;
    }


    /**
     * Leetcode145 二叉树的后序遍历 非递归实现
     * 处理方法：
     * 步骤一: 非递归实现 根 -> 右孩子 -> 左孩子（使用栈结构 参考非递归实现前序遍历）
     * 步骤二: 使用第二个栈结构 对压栈结构 进行逆序输出
     * @param root 二叉树的根节点
     * @return 返回二叉树遍历的结果
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }

        Stack<TreeNode> normalStack = new Stack<>();
        Stack<TreeNode> reverseStack = new Stack<>();

        normalStack.push(root);

        while(!normalStack.isEmpty()) {
            TreeNode curNode = normalStack.pop();
            reverseStack.push(curNode);

            if(curNode.left != null) {
                normalStack.push(curNode.left);
            }

            if(curNode.right != null) {
                normalStack.push(curNode.right);
            }
        }

        while(!reverseStack.isEmpty()) {
            res.add(reverseStack.pop().val);
        }

        return res;
    }

}
