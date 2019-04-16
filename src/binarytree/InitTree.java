package binarytree;

/**
 * @author Batman on 2018/12/19.
 * @author Batman
 * 初始化一颗二叉树
 */
public class InitTree {
    public static TreeNode init(){
        //该树为二查找树,中序遍历为从小到大进行排序
        TreeNode h = new TreeNode(17, null, null);
        TreeNode g = new TreeNode(16, null, h);
        TreeNode i = new TreeNode(19, null, null);
        TreeNode c = new TreeNode(18, g, i);
        TreeNode e = new TreeNode(9, null, null);
        TreeNode f = new TreeNode(10, e, null);
        TreeNode k = new TreeNode(7, null, null);
        TreeNode d = new TreeNode(6, null, k);
        TreeNode b = new TreeNode(8, d, f);
        TreeNode a = new TreeNode(15, b, c);
        return a;
    }
}