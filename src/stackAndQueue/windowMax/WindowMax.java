package stackAndQueue.windowMax;

import java.util.Arrays;
import java.util.LinkedList;

public class WindowMax {
    public static int[] getWindowMax(int[] data, int w) {
        //空处理
        if (data == null || w < 0 || data.length < w) {
            return null;
        }
        int data_len = data.length;
        LinkedList<Integer> maxQueue = new LinkedList<>();
        int[] res = new int[data_len - w + 1];
        for (int i = 0; i < data_len; i++) {
            //从最大值队列队尾开始，将小于当前元素的脚标弹出
            int curr = data[i];
            while (!maxQueue.isEmpty() && data[maxQueue.peekLast()] <= curr) {
                maxQueue.pollLast();
            }
            //加入当前值
            maxQueue.addLast(i);
            //从最大致队列头部，弹出过期脚标
            if (maxQueue.peekFirst() < i - w) {
                maxQueue.pollFirst();
            }
            //从头部放入最大值
            if (i >= w-1) {
                res[i+1-w] = data[maxQueue.peekFirst()];
            }

        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {4, 2, 5, 2, 1, 5, 6, 65, 1};
        Arrays.stream(getWindowMax(data, 4)).forEach(x-> System.out.println(x));
    }
}
