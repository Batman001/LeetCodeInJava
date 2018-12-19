package binaryTree2;

/**
 * Created by sunchao on 2018/12/19.
 */
public class MyNode {
    private String value;
    private MyNode left;
    private MyNode right;

    public MyNode(String value, MyNode left, MyNode right){
        this.value = value;
        this.left = left;
        this.right = right;
    }


    public void visit(){
        System.out.print(value+" ");
    }

    public MyNode getLeft(){
        return left;
    }

    public MyNode getRight(){
        return right;
    }
}

