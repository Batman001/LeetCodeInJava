package com.leetcode.train.traversalgraph.dfs;

/**
 * @author Batman create on 2019-05-15 14:46
 * LeetCode 79 给定一个二维网格和一个单词，找出该单词是否存在于网格中
 * https://leetcode-cn.com/problems/word-search/
 */
public class WordSearch {
    private boolean exists(char[][] board, String word){

        // 二维图图的行数
        int m = board.length;
        // 二维图的列数
        int n = board[0].length;
        // 每选定一个字母以后 需要记录当前位置是否已经被遍历过
        boolean[][] visited = new boolean[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if (dfs(board, word, 0, i, j, visited)){
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * 深度遍历搜索二维图
     * @param board 二维图
     * @param word 需要匹配单词
     * @param index word需要开始匹配的索引位置
     * @param row_index 二维图行索引位置
     * @param col_index 二维图列索引位置
     * @param visited 记录某位置是否已经遍历过的布尔列表
     * @return true或者false
     */
    private boolean dfs(char[][] board, String word, int index, int row_index, int col_index, boolean[][] visited) {
        if(index == word.length()){
            return true;
        }
        if(row_index<0 || col_index<0 || row_index>=board.length || col_index>=board[0].length){
            return false;
        }
        if(visited[row_index][col_index]){
            return false;
        }
        if(board[row_index][col_index] != word.charAt(index)){
            return false;
        }
        visited[row_index][col_index] = true;
        boolean res = dfs(board, word,index+1,row_index+1, col_index, visited) ||
                dfs(board, word,index+1,row_index-1, col_index, visited) ||
                dfs(board, word, index+1, row_index, col_index+1, visited) ||
                dfs(board, word, index+1, row_index, col_index-1, visited);
        visited[row_index][col_index] = false;
        return res;
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},
                {'S','F','C','S'}, {'A','D','E','E'}};
        String word = "ABCCED";
        WordSearch ws = new WordSearch();
        System.out.println("匹配结果是：" + ws.exists(board, word));
    }

}
