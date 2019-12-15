package binarytree;

import java.util.HashMap;


/**
 * Created by yongbo1 on 2019/12/1.
 */
public class TheMaxLegthOfOneSum {

    public int getMaxLegth(TreeNode root, int k) {
        HashMap<Integer, Integer> sumDepthMap = new HashMap<>();
        sumDepthMap.put(0, 0);
        return preOrderFind(root, k, 0, 0, 1, sumDepthMap);
    }

    public int preOrderFind(TreeNode curr, int k, int preSum, int maxLen, int depth, HashMap<Integer, Integer> sumDepthMap) {
        //递归终点:如果当前节点为null,返回最大长度
        //递归操作:
        //1.获取当前节点的值,与preSum加和,获取当前层级的和sum
        //2.判断字典中是否存在sum-k的值 如果存在,更新maxLen为当前树深减去sum-k的值
        //3.前序遍历常规操作,递归进入左子树获取最大路径,然后递归进入右子树获取最大路径.
        //4.左右子树遍历完成后,一个路径算是结束了,如果sum第一次出现的层级和当前的层级相等,删除这个key
        if (curr == null) {
            return maxLen;
        }
        int sum = preSum + curr.val;
        if (sumDepthMap.containsKey(sum - k)) {
            maxLen = Math.max(maxLen, depth - sumDepthMap.get(sum - k));
        }
        if (!sumDepthMap.containsKey(sum)) {
            sumDepthMap.put(sum, depth);
        }
        maxLen = preOrderFind(curr.left, k, sum, maxLen, depth + 1, sumDepthMap);
        maxLen = preOrderFind(curr.right, k, sum, maxLen, depth + 1, sumDepthMap);
        if (depth == sumDepthMap.get(sum)) {
            sumDepthMap.remove(sum);
        }
        return maxLen;

    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 4, 2, 3, 5, 6, 8, 7, 9, 10, 10, 11, 12, 13, 14};
        Integer[] arr2 = {};
        Integer[] arr3 = {1};
        Integer[] arr4 = {1, 4, 2, null, 3};
        Integer[] arr5 = {1, 2, 3, 4, 5, 6, 7};
        Integer[][] test_case = {arr1, arr2, arr3, arr4, arr5};
        TheMaxLegthOfOneSum app = new TheMaxLegthOfOneSum();
        for (Integer[] arr : test_case) {
            TreeNode root = TreeNode.createBinTree(arr);
            int len = app.getMaxLegth(root,8);
            System.out.println(len);
        }
    }
}
