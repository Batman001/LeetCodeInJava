package binaryTree;

/**
 * Created by sunchao on 2018/12/19.
 * @author Batman
 */
import java.util.*;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;


    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;

    }

    public TreeNode(){}

    /**
     * 二叉树根节点的构造函数
     * @param val
     */
    public TreeNode(int val){
        this.val = val;
    }


    public TreeNode getLeft(){
        return this.left;
    }

    public TreeNode getRight(){
        return this.right;
    }

    public int getVal(){
        return this.val;
    }

    public void visit(){
        System.out.print(this.val+" ");
    }


    public void setLeftTreeNode(TreeNode node){
        this.left = node;
    }

    public void setRightTreeNode(TreeNode node){
        this.right = node;
    }

    int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getMaxDepth(node.left);
        int right = getMaxDepth(node.right);
        return Math.max(left, right) + 1;
    }

    int getMinDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    int getMin(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.min(getMin(root.left), getMin(root.right)) + 1;

    }


    /**
     * 二叉树节点数量
     * @param root
     * @return
     */
    int numOfTreeNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = numOfTreeNode(root.left);
        int right = numOfTreeNode(root.right);
        return left + right + 1;
    }


    /**
     * 二叉树子节点数量
     * @param root
     * @return
     */
    int numOfChildNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return numOfChildNode(root.left) + numOfChildNode(root.right);
    }



    /**
     * 求第k层节点的个数
     * @param root
     * @param k
     * @return
     */
    int numOfkLevelTreeNode(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int numKleft = numOfkLevelTreeNode(root.left, k - 1);
        int numKright = numOfkLevelTreeNode(root.right, k - 1);
        return numKleft + numKright;
    }



    /**
     * 判断二叉树是否为平衡二叉树  ！！！！！
     * @param node
     * @return
     */
    boolean isBanlancedTreeNode(TreeNode node) {
        return MaxDepth2(node) != -1;
    }

    int MaxDepth2(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = MaxDepth2(node.left);
        int right = MaxDepth2(node.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }




    /**
     * 判断二叉树是否为完全二叉树
     * @param root
     * @return
     */
    boolean isCompleteTreeNode(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        boolean result = true;
        boolean hasNoChild = false;
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            if (hasNoChild) {
                if (current.left != null || current.right != null) {
                    result = false;
                    break;
                }
            } else {
                if (current.left != null && current.right != null) {
                    queue.add(current.left);
                    queue.add(current.right);
                } else if (current.left != null && current.right == null) {
                    queue.add(current.left);
                    hasNoChild = true;

                } else if (current.left == null && current.right != null) {
                    result = false;
                    break;
                } else {
                    hasNoChild = true;
                }
            }

        }
        return result;
    }




    /**
     * 两个二叉树是否相同
     * @param t1
     * @param t2
     * @return
     */
    boolean isSameTreeNode(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        boolean left = isSameTreeNode(t1.left, t2.left);
        boolean right = isSameTreeNode(t1.right, t2.right);
        return left && right;
    }



    /**
     * 判断两个二叉树是否为镜像
     * @param t1
     * @param t2
     * @return
     */
    boolean isMirrorTreeNode(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val != t2.val) {
            return false;
        }
        return (isMirrorTreeNode(t1.left, t2.right) && (isMirrorTreeNode(t1.right, t2.left)));
    }



    /**
     * 翻转二叉树or镜像二叉树
     * @param root
     * @return
     */
    TreeNode mirrorTreeNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTreeNode(root.left);
        TreeNode right = mirrorTreeNode(root.right);

        root.left = right;
        root.right = left;
        return root;
    }


    /**
     * 求两个二叉树节点的最低公共祖先节点
     * @param root
     * @param t1
     * @param t2
     * @return
     */
    TreeNode getLastCommonParent(TreeNode root, TreeNode t1, TreeNode t2) {
        if (findNode(root.left, t1)) {
            if (findNode(root.right, t2)) {
                return root;
            }
            else {
                return getLastCommonParent(root.left, t1, t2);
            }
        }
        else {
            if (findNode(root.left, t2)) {
                return root;
            }
            else {
                return getLastCommonParent(root.right, t1, t2);
            }
        }
    }


    /**
     * 查找节点node是否在当前 二叉树中
     * @param root
     * @param node
     * @return
     */
    boolean findNode(TreeNode root, TreeNode node) {
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
     * 二叉树前序遍历 ---  迭代解法
     * @param root
     * @return
     */


    ArrayList<Integer> preOrder(TreeNode root){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root == null){
            return list;
        }
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node.val);
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        return list;
    }


    /**
     * 二叉树前序遍历 ---  递归解决方法
     * @param root
     * @return
     */
    ArrayList<Integer> preOrderReverse(TreeNode root){
        ArrayList<Integer> result = new ArrayList<Integer>();
        preOrder2(root, result);
        return result;
    }
    void preOrder2(TreeNode root, ArrayList<Integer> result){
        if (root==null){
            return ;
        }
        result.add(root.val);
        preOrder2(root.left, result);
        preOrder2(root.right, result);
    }


    /**
     * 二叉树中序遍历
     * @param root
     * @return
     */
    ArrayList<Integer> inOrder(TreeNode root){
        ArrayList<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        while(current != null|| !stack.empty()){
            while(current != null){
                stack.add(current);
                current = current.left;
            }
            current = stack.peek();
            stack.pop();
            list.add(current.val);
            current = current.right;

        }
        return list;

    }


    /**
     * 二叉树后序遍历
     * @param root
     * @return
     */
    ArrayList<Integer> postOrder(TreeNode root){
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root == null){
            return list;
        }
        list.addAll(postOrder(root.left));
        list.addAll(postOrder(root.right));
        list.add(root.val);
        return list;
    }

}




