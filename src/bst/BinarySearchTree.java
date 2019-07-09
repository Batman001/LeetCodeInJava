package bst;

/**
 * @author Batman create on 2019-05-28 11:14
 * 二叉查找树类
 */
public class BinarySearchTree {
    /**
     * 二叉查找树的节点类
     */
    class Node{
        int val;
        Node left, right;
        public Node(int item){
            this.val = item;
            left = right = null;
        }
    }

    /**
     * BinarySearchTree 根节点
     */
    private Node root;

    /**
     * constructor function
     */
    BinarySearchTree(){
        root = null;
    }

    BinarySearchTree(Node root){
        root = root;
    }


    private Node searchRec(Node root, int key) {
        if(root == null){
            return null;
        }
        if(root.val == key){
            return root;
        }
        else if(root.val < key){
            return searchRec(root.right, key);
        }else{
            return searchRec(root.left, key);
        }
    }


    private void deleteKey(int key){
        root = deleteRec(root, key);
    }


    /**
     * A recursive function to delete a new key in BST
     * @param root 二叉查找树根节点
     * @param key 指定节点的key值
     * @return 删除后的二叉查找树的根节点
     */
    private Node deleteRec(Node root, int key){
        /* Base Case: If the tree is empty */
        if(root==null){
            return null;
        }
        if(key < root.val){
            root.left = deleteRec(root.left, key);
        }else if (key > root.val){
            root.right = deleteRec(root.right, key);
        }
        // if key is same as root's key, then This is the node to be deleted
        else{
            // node with one children
            if(root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.val = minValue(root.right);

            // delete the inorder successor
            root.right = deleteRec(root.right, root.val);

        }
        return root;

    }

    /**
     * 返回以root为根节点的二叉查找树的最小值(从左子树中查找)
     * @param root 根节点
     * @return 返回最小值
     */
    private int minValue(Node root) {
        int minVal = root.val;
        while(root.left != null){
            minVal = root.left.val;
            root = root.left;
        }
        return minVal;
    }


    /**
     * 返回二叉查找树的父节点
     * @param root 二叉查找树的根节点
     * @param key 要查找父节点的当前节点的key值
     * @return 父亲节点
     */
    private Node findFather(Node root, int key){
        if(root == null){
            return null;
        }
        if (root.left != null && root.left.val == key){
            return root;
        }
        if(root.right != null && root.right.val == key){
            return root;
        }
        if(findFather(root.left, key) != null){
            return findFather(root.left, key);
        }else{
            return findFather(root.right, key);
        }

    }

    public void insert(int key){
        root = insertRec(root, key);
    }

    /**
     * 递归实现方法
     * @param root 二叉查找树根节点
     * @param key 插入的节点的val值
     * @return 返回插入后的根节点root
     */
    private Node insertRec(Node root, int key) {
        // if tree is empty,return a new Node
        if(root == null){
            root = new Node(key);
            return root;
        }
        /* Otherwise, recur down the tree */
        if(key < root.val){
            root.left = insertRec(root.left, key);
        }else if(key > root.val){
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    /**
     * 非递归实现方法
     * @param root 二叉查找树根节点
     * @param key 插入的节点的val值
     * @return 返回插入后的根节点root
     */
    private Node insertNoRec(Node root, int key){
        Node temp = root;
        while(temp != null){
            if(key > temp.val){
                if(temp.right == null){
                    // 直接插入
                    temp.right = new Node(key);
                    return root;
                }else{
                    temp = temp.right;
                }
            }else if(key < temp.val){
                if(temp.left == null){
                    // 直接插入
                    temp.left = new Node(key);
                    return root;
                }else{
                    temp = temp.left;
                }
            }
        }
        return root;
    }


    void inorder(){
        inorderRec(root);
    }

    private void inorderRec(Node root){
        if(root != null){
            inorderRec(root.left);
            System.out.printf(root.val + " ");
            inorderRec(root.right);
        }
    }


    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
        20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Search a node of the given tree");
        BinarySearchTree subTree = new BinarySearchTree(tree.searchRec(tree.root, 30));
        subTree.inorder();

        System.out.println("Inorder traversal of the given tree");
        tree.inorder();

        System.out.println("\nDelete 20");
        tree.deleteKey(20);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();


        System.out.println("\nDelete 30");
        tree.deleteKey(30);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();

        System.out.println("\nDelete 50");
        tree.deleteKey(50);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();

    }



}
