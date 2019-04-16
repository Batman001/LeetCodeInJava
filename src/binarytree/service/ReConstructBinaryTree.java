package binarytree.service;

import binarytree.TreeNode;

/**
 * @author Batman on 2018/12/19.
 * @author Batman
 */
public class ReConstructBinaryTree {

    /**
     * 根据二叉树前序和中序遍历结果,构建二叉树
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {

        /**
         * 前序的第一个数定为根
         */
        TreeNode root = new TreeNode(pre[0]);
        int len=pre.length;
        //当只有一个数的时候
        if(len==1){
            root.left = null;
            root.right = null;
            return root;
        }
        //找到中序中的根位置
        int rootval=root.val;
        int i;
        for(i=0;i<len;i++){
            if(rootval==in[i])
                break;
        }
        //创建左子树
        if(i>0){
            int[] pr=new int[i];
            int[] ino=new int[i];
            for(int j=0;j<i;j++){
                pr[j]=pre[j+1];
            }
            for(int j=0;j<i;j++){
                ino[j]=in[j];
            }
            root.left=reConstructBinaryTree(pr,ino);
        }else{
            root.left=null;
        }
        //创建右子树
        if(len-i-1>0){
            int[] pr=new int[len-i-1];
            int[] ino=new int[len-i-1];
            for(int j=i+1;j<len;j++){
                ino[j-i-1]=in[j];
                pr[j-i-1]=pre[j];
            }
            root.right=reConstructBinaryTree(pr,ino);
        }else{
            root.right=null;
        }


        return root;
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        TreeNode root = new ReConstructBinaryTree().reConstructBinaryTree(preorder, inorder);

        Visit v1 = new PrevisitInStackImpl();
        Visit v2 = new MiddleVisitImpl();
        Visit v3 = new PostVisitImpl();
        System.out.printf("重建后的树的前序遍历为:\t");
        v1.visitTree(root);
        System.out.println();

        System.out.printf("重建后的树的中序遍历为:\t");
        v2.visitTree(root);
        System.out.println();

        System.out.printf("重建后的树的后序遍历为:\t");
        v3.visitTree(root);



    }

}
