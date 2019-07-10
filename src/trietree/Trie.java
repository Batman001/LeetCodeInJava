package trietree;

import java.util.HashMap;

/**
 * @author Batman create on 2019-07-10 09:59
 * leetcode 208 实现Trie (前缀树) https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 */
public class Trie {

    private class Node {
        /**
         * 该字符串的重复数目 该属性统计重复次数时使用
         */
        private int duplicateNum;

        /**
         * 该字符串前缀的字符串数 包括本身
         */
        private int prefixNum;

        /**
         * trie树子树 用数组实现
         */
        private Node childs[];

        /**
         * 是否为单词节点
         */
        private boolean isLeaf;

    }

    /**
     * trie树树根节点
     */
    private Node root;


    /**
     * Initialize your data structure here.
     * 构造函数 初始化一颗Trie树
     */
    public Trie() {
        root = new Node();
    }

    /**
     * 插入字符串 用循环代替迭代实现
     * @param word 带插入单词
     */
    public void insert(String word) {
        insert(this.root, word);
    }

    private void insert(Node root, String word) {
        word = word.toLowerCase();
        char[] chars = word.toCharArray();

        for(int i=0, length=chars.length; i<length; i++) {
            // 用相对于 a字母的值作为下标索引 也隐式地记录的该字母的值
            int index = chars[i] - 'a';
            if(root.childs[index] != null) {
                // 如果已经存在了前缀 该子节点的prefixNum ++
                root.childs[index].prefixNum ++;
            } else {
                // 如果不存在
                root.childs[index] = new Node();
                root.childs[index].prefixNum++;
            }

            // 如果到了字符串的结尾 则做标记
            if(i == length -1) {
                root.childs[index].isLeaf = true;
                root.childs[index].duplicateNum ++;

            }

            // root指向子节点  继续处理
            root = root.childs[index];

        }
    }

    /**
     * 得到字典树中每个word的出现次数
     */
    public HashMap<String, Integer> getAllWords(){
         return preTraversal(this.root, "");
    }

    /**
     * Trie树的前序遍历
     * @param root 子树根节点
     * @param prefix 查询到该节点前所遍历过的前缀
     */
    private HashMap<String, Integer> preTraversal(Node root, String prefix) {
        HashMap<String, Integer> map = new HashMap<>(16);

        if(root != null) {
            if(root.isLeaf) {
                // 当前即为一个单词
                map.put(prefix, root.duplicateNum);
            }

            for(int i = 0, length = root.childs.length; i<length; i++) {
                if(root.childs[i] != null) {
                    char ch = (char)(i + 'a');
                    // 递归调用前序遍历
                    String tempStr = prefix + ch;
                    map.putAll(preTraversal(root.childs[i], tempStr));

                }
            }
        }
        return map;

    }


    /** Returns if the word is in the trie. */
    public boolean search(String word) {

        char[] chars = word.toLowerCase().toCharArray();
        Node tempRoot = root;

        for(int i = 0, length = chars.length; i < length; i++) {
            int index = chars[i] - 'a';
            if(tempRoot.childs[index] == null) {
                // 如果不存在 则查找失败
                return false;
            }
            tempRoot = tempRoot.childs[index];
        }

        return tempRoot.isLeaf;


    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {

        char[] chars = prefix.toLowerCase().toCharArray();
        Node tmpRoot = root;
        for (int i = 0, length = chars.length; i < length; i++) {
            int index = chars[i] - 'a';
            if (tmpRoot.childs[index] == null) {
                return false;
            }
            tmpRoot = tmpRoot.childs[index];
        }
        return true;

    }



}
