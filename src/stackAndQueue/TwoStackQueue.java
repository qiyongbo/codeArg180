package stackAndQueue;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 用两个栈组成一个队列
 * 解题思路：两个栈，一个栈用来压入数据，一个栈用来弹出数据
 * 1.当弹出数据的栈没有数据时，将压入数据的栈全部依次压入到弹出栈中
 */
public class TwoStackQueue {
    private Stack<Integer> addStack;
    private Stack<Integer> popStack;

    public TwoStackQueue() {
        this.addStack = new Stack<>();
        this.popStack = new Stack<>();
    }

    public void add(int data) {
        addStack.push(data);
    }

    public Integer poll() throws Exception {
        if (popStack.isEmpty() && addStack.isEmpty()) {
            throw new Exception("the Queue is empty!");
        }
        if (popStack.isEmpty()) {
            while (!addStack.isEmpty()) {
                popStack.push(addStack.pop());
            }
        }
        return popStack.pop();
    }

    public Integer peek() throws Exception {
        if (popStack.isEmpty() && addStack.isEmpty()) {
            throw new Exception("the Queue is empty!");
        }
        if (popStack.isEmpty()) {
            while (!addStack.isEmpty()) {
                popStack.push(addStack.pop());
            }
        }
        return popStack.peek();
    }

    public static void main(String[] args) throws Exception {
        TwoStackQueue queue = new TwoStackQueue();
        List<Integer> value = Arrays.asList(3, 4, 2, 1, 5, 6, 1, 10);
        value.stream().forEach(queue::add);
        System.out.println("poll  " + queue.poll());
        queue.add(100);
        for (int i = 0; i < 10; i++) {

            System.out.println("poll  " + queue.poll());
        }

    }
}
