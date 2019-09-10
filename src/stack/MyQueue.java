package stack;

import java.util.Stack;

/**
 * @author Batman create on 2019-08-12 17:42
 * leetcode 232 使用栈实现队列结构
 */
public class MyQueue {

    private Stack<Integer> inputStack = new Stack<>();
    private Stack<Integer> outputStack = new Stack<>();
    /**
     *  Initialize your data structure here.
     */
    public MyQueue() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        this.inputStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(outputStack.isEmpty()) {
            while(!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.peek();

    }

    public boolean isEmpty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

}
