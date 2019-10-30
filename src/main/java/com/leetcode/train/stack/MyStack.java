package com.leetcode.train.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Batman create on 2019-08-12 18:03
 * leetcode 225 用队列实现栈
 */
public class MyStack {
    private Queue<Integer> inQueue;
    private Queue<Integer> outQueue;

    /** Initialize your data structure here. */
    public MyStack() {
        inQueue = new LinkedList<>();
        outQueue = new LinkedList<>();
    }

    /** Push element x onto com.leetcode.train.stack. */
    public void push(int x) {
        inQueue.offer(x);
        while(!outQueue.isEmpty()) {
            inQueue.offer(outQueue.poll());
        }
        Queue temp = inQueue;
        inQueue = outQueue;
        outQueue = temp;
    }

    /** Removes the element on top of the com.leetcode.train.stack and returns that element. */
    public int pop() {
        return outQueue.poll();
    }

    /** Get the top element. */
    public int top() {
        return outQueue.peek();

    }

    /** Returns whether the com.leetcode.train.stack is empty. */
    public boolean empty() {
        return outQueue.isEmpty();

    }


}
