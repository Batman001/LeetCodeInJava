package com.leetcode.train.permutation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Batman on 2019/1/2.
 * @author Batman
 * LeetCode 60 Permutation Sequence
 * input: n=3, k=3
 * output: 213
 */
public class PermutationSequence {
    public String getPermutation(int n, int k){
        // to transfer it as begin from 0 rather than 1
        k--;

        List<Integer> numList = new ArrayList<>();
        for(int i=1; i<=n; i++){
            numList.add(i);
        }

        int factorial = 1;
        for(int i=2;i<n;i++){
            factorial *= i;
        }

        StringBuilder res = new StringBuilder();
        int times = n-1;
        while(times>=0){
            int indexInList = k/factorial;
            res.append(numList.get(indexInList));
            numList.remove(indexInList);

            // new k for next turn
            k = k%factorial;
            if(times != 0){
                // new (n-1)!
                factorial = factorial/times;
            }
            times--;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int n=3,k=2;
        System.out.println(new PermutationSequence().getPermutation(n,k));
    }

}
