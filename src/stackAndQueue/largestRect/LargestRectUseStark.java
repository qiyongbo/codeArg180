package stackAndQueue.largestRect;

/**
 * Created by yongbo1 on 2019/7/25.
 */

import java.util.Stack;

import static sun.jvm.hotspot.oops.CellTypeState.top;

/**
 * 题目:给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 时间复杂度最低的解法:
 * 用栈
 * 1.申请一个栈,先放入-1作为最小值,
 * 2.开始遍历数组,将脚标放入栈中,如果当前元素大于栈顶元素,直接放入,如果小于等于栈顶元素,
 * 将栈顶元素弹出,计算面积,直到当前元素大于栈顶元素位置,求出最大面积,记录下来.
 * 3.数组遍历结束,如果栈中还是有元素,逐一弹出,计算最大面积
 * 4.计算公式:
 * 遍历数组时:
 * i - 当前元素
 * stack[top] - 栈顶的元素 stack.pop()
 * stack[top-1] - 弹出栈顶元素后,在栈顶的元素 stack.pop()后stack.peek()
 * <p>
 * max_area = (i-(stack[top-1]+1))*arr[stack[top]]
 * <p>
 * 备注:为什么是i-(stack[top-1]+1) 而不是i-(stack[top])
 * 因为stack里面存到数据不是永远连续的
 * 数组结束时:
 * max_area = ((数组长度-1)-stack[top-1]) * arr[stack[top]
 */
public class LargestRectUseStark {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int arr_len = heights.length;
        int max_area = 0;
        for (int i = 0; i < arr_len; i++) {
            int curr = heights[i];
            if (stack.peek() != -1) {
                int top = heights[stack.peek()];
                if (top > curr) {
                    while (stack.peek() != -1 && heights[stack.peek()] >= curr) {
                        int top_index = stack.pop();
                        int area = (i - stack.peek() - 1) * heights[top_index];
                        max_area = Math.max(area, max_area);
                    }
                }
            }

            stack.push(i);
        }
        while (stack.peek() != -1) {
            int top_index = stack.pop();
            int area = (arr_len - 1 - stack.peek()) * heights[top_index];
            max_area = Math.max(area, max_area);
        }


        return max_area;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1};
        LargestRectUseStark test = new LargestRectUseStark();
        test.largestRectangleArea(arr);
    }
}
