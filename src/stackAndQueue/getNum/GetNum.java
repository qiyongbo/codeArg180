package stackAndQueue.getNum;

/**
 * Created by yongbo1 on 2019/7/25.
 */

import java.util.LinkedList;

/**
 * 最大值减去最小值小于等于数组数量的个数
 */
public class GetNum {
    public static int getNum(int[] arr, int num) {
        int arr_len = arr.length;
        if (arr_len == 0) {
            return 0;
        }
        int res = 0;
        LinkedList<Integer> qMax = new LinkedList<>();
        LinkedList<Integer> qMin = new LinkedList<>();
        int i = 0;
        while (i < arr_len) {
            int j = i;
            while (j < arr_len) {
                int curr = arr[j];
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= curr) {
                    qMax.pollLast();
                }
                qMax.addLast(j);
                while(!qMin.isEmpty() && arr[qMin.pollLast()] >= curr){
                    qMin.pollLast();
                }
                qMin.addLast(j);
                if(arr[qMax.peekLast()] - arr[qMin.peekLast()] > num){
                    break;
                }
                j++;
            }
            res +=(j-i);
            if(qMax.peekFirst() == i){
                qMax.pollFirst();
            }
            if(qMin.peekFirst()==i){
                qMin.pollFirst();
            }
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,4,5};
        int num = GetNum.getNum(arr, 3);
        System.out.println(num);
    }
}
