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
     * @param rowIndex 二维图行索引位置
     * @param colIndex 二维图列索引位置
     * @param visited 记录某位置是否已经遍历过的布尔列表
     * @return true或者false
     */
    private boolean dfs(char[][] board, String word, int index, int rowIndex, int colIndex, boolean[][] visited) {
        if(index == word.length()){
            return true;
        }
        if(rowIndex<0 || colIndex<0 || rowIndex>=board.length || colIndex>=board[0].length){
            return false;
        }
        if(visited[rowIndex][colIndex]){
            return false;
        }
        if(board[rowIndex][colIndex] != word.charAt(index)){
            return false;
        }
        visited[rowIndex][colIndex] = true;
        boolean res = dfs(board, word,index+1,rowIndex+1, colIndex, visited) ||
                dfs(board, word,index+1,rowIndex-1, colIndex, visited) ||
                dfs(board, word, index+1, rowIndex, colIndex+1, visited) ||
                dfs(board, word, index+1, rowIndex, colIndex-1, visited);
        visited[rowIndex][colIndex] = false;
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
