package binaryTree2;

/**
 * Created by sunchao on 2018/12/19.
 */
public class InitTree {
    public static MyNode init(){
        MyNode h = new MyNode("H", null, null);
        MyNode g = new MyNode("G", null, h);
        MyNode i = new MyNode("I", null, null);
        MyNode c = new MyNode("C", g, i);
        MyNode e = new MyNode("E", null, null);
        MyNode f = new MyNode("F", e, null);
        MyNode k = new MyNode("K", null, null);
        MyNode d = new MyNode("D", null, k);
        MyNode b = new MyNode("B", d, f);
        MyNode a = new MyNode("A", b, c);


        return a;
    }
}

