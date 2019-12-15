package Matrix;

import java.util.HashMap;

import static com.sun.tools.doclint.Entity.sum;

/**
 * Created by yongbo1 on 2019/12/1.
 */
public class MaxSubArrayLen {

    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //从0到i的和
        int sum = 0;
        //和为k最大子数组长度
        int len = 0;
        //这个map存储的key为数组中从0到i的和,value为达到这个和的最小索引.
        // 为什么存最小的索引,因为最终是看从i到这个key的和是否为k,所以存索引越小,子数组长度越大
        HashMap<Integer, Integer> sum_firstIndex_map = new HashMap<>();
        //存储和为0的最小索引:-1
        sum_firstIndex_map.put(0, -1);
        //从数组的第一个元素开始遍历
        //依次和上一个元素加和得到从0到i的和 赋值给sum
        //检查map中的key是否存在sum-k 的键,如果有,i减去那个索引就是符合条件的 长度,和将它和len的最大值赋值给len
        //检查map中的key是有存在sum,如果没有保存,如果有,什么也不做
        int nums_len = nums.length;
        for (int i = 0; i < nums_len; i++) {
            sum += nums[i];
            if (sum_firstIndex_map.containsKey(sum - k)) {
                len = Math.max(i - sum_firstIndex_map.get(sum - k), len);
            }
            if (!sum_firstIndex_map.containsKey(sum)) {
                sum_firstIndex_map.put(sum, i);
            }
        }
        return len;


    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,3};
        int k = 6;
        MaxSubArrayLen app = new MaxSubArrayLen();
        System.out.println(app.maxSubArrayLen(nums,k));
    }
}
