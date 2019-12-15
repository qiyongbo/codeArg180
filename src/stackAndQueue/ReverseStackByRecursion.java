package stackAndQueue;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 题目：只用一个栈，利用递归的方法，将此栈中的元素逆序
 * 解题思路：
 * 1.首先需要创建一个递归方法，将栈底元素取出并删除
 * 2.再创建一个递归方法，把所有元素取出后，从栈顶到栈底依次压入栈中
 */
public class ReverseStackByRecursion {
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        int value = stack.pop();
        if(stack.isEmpty()){
            return value;
        }else{
            int last = getAndRemoveLastElement(stack);
            stack.push(value);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }else{
            int last = getAndRemoveLastElement(stack);
            reverse(stack);
            stack.push(last);
        }

    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> values = Arrays.asList(13,4,5,6,2,1,4,8,6,32,12);
        values.stream().forEach(stack::push);
        reverse(stack);
        System.out.println(stack);
    }
}
