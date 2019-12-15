package stackAndQueue.largestRect;

/**
 * Created by yongbo1 on 2019/7/25.
 */

import java.util.Stack;

/**
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * 输出: 6
 * 解题思路:
 * 从第一行直至第二行,累计以该行为底的1的数量
 * heights[j] = arr[i][j]==0?0:heights[j]+1
 * 然后转化为计算直方图最大矩形的计算
 */
public class LargestRectInMatix {
    public static void main(String[] args) {
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        LargestRectInMatix test = new LargestRectInMatix();
        int area = test.maximalRectangle(matrix);
        System.out.println(area);
    }

    public int maximalRectangle(char[][] matrix) {
        int matix_hi = matrix.length;
        if (matix_hi == 0) {
            return 0;
        }
        int width = matrix[0].length;
        int[] heighs = new int[width];
        int max_area = 0;
        for (int i = 0; i < matix_hi; i++) {
            for (int j = 0; j < width; j++) {
                heighs[j] = matrix[i][j] == '0' ? 0 :  heighs[j] + 1;
            }
            max_area = Math.max(max_area, largestRect(heighs));
        }
        return max_area;
    }

    public int largestRect(int[] heights) {
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
}
