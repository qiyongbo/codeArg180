package stackAndQueue;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 题目：一个栈中的元素类型为整型，现在想从栈顶到栈底按照从大到小的顺序排序，只允许申请一个栈，除此之外，可申请新的变量，但不能申请新的数据结构。
 * 解题思路：
 * 1.将栈顶元素弹出，和排序的栈顶元素进行比较
 * a)如果排序栈为空，直接压入排序栈
 * b)如果排序栈栈顶顶的元素小于等于当前元，直接压入
 * c)如果培训栈栈顶的元素比当前元素大，将排序栈的元素弹出，压入数据栈，直至遇到小于等于当前元素的时候，压入当前元素
 */
public class SortStackByStack {
    public static Stack<Integer> sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> sortStack = new Stack<>();
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (sortStack.isEmpty()) {
                sortStack.push(curr);
            } else {
                int top = sortStack.peek();
                if (top <= curr) {
                    sortStack.push(curr);
                } else {
                    while (!sortStack.isEmpty() && sortStack.peek() > curr) {
                        stack.push(sortStack.pop());
                    }
                    sortStack.push(curr);
                }
            }
        }
        return sortStack;

    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> value = Arrays.asList(3, 4, 2, 7,1, 5, 6, 1, 10);
        value.stream().forEach(stack::push);
        System.out.println(stack);
        Stack<Integer> sortedStack = sortStackByStack(stack);
        System.out.println(sortedStack);
    }
}
