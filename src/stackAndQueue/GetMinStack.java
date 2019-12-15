package stackAndQueue;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 实现一个特殊的栈、在满足栈的基本功能上，再实现满足获取最小值的功能
 * 要求：1 poll、push、getMin的时间复杂度都是O(1)
 * 2 设计的栈类型可以使用原有的栈结构
 * 实现思路：
 * 用两个栈，一个栈存储数据，一个栈存储最小值，
 * 在数据压入数据栈的同时，比较最小值栈的顶端数据，如果等于或者小于最小值栈的栈顶的数据，压入最小值栈，如果大，不做任何操作
 * 在数据弹出栈时，比较最小值栈顶端的数据，如果等于或者小于最小值栈的栈顶数据，弹出最小值栈顶的数据
 */
public class GetMinStack {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public GetMinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int data) {
        dataStack.push(data);
        if (minStack.isEmpty() || data <= minStack.peek()) {
            minStack.push(data);
        }
    }

    public int pop() throws Exception {
        if (dataStack.isEmpty()) {
            throw new Exception("Stack is Empty");
        }
        int curr = dataStack.pop();
        if (!minStack.isEmpty() && curr == minStack.peek()) {
            minStack.pop();
        }
        return curr;
    }

    public int getMin() throws Exception {
        if (minStack.isEmpty()) {
            throw new Exception("Stack is Empty");
        }
        return minStack.peek();
    }

    public int peek() throws Exception {
        if (dataStack.isEmpty()) {
            throw new Exception("stack is Empty");
        }
        return dataStack.peek();
    }

    public static void main(String[] args) throws Exception {
        GetMinStack getMinStack = new GetMinStack();
        List<Integer> value = Arrays.asList( 3, 4, 2, 1, 5, 6, 1, 10);
        value.stream().forEach(getMinStack::push);

        for (int i = 0; i < 10; i++) {
            System.out.println("min  " + getMinStack.getMin());
            System.out.println("poll  " + getMinStack.pop());
        }


    }

}
