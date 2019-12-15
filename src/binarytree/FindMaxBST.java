package binarytree;


import sun.reflect.generics.tree.ReturnType;

import static javafx.scene.input.KeyCode.M;
import static javafx.scene.input.KeyCode.R;

/**
 * Created by yongbo1 on 2019/12/2.
 */
public class FindMaxBST {
    //树形db套路
    //1分析答案的可能性,要么在X的左子树中,要么在X的右子树中,要么就是X+左最大子树+右最大子树
    //2.列出需要的信息
    //左子树最大搜索二叉树的头节点 右子树的最大二叉搜索数的头节点
    //左子树最大二叉树的节点个数   右子树的最大二叉树的节点个数
    //左子树最大二叉树的最大值     右子树的最大二叉树的最小值
    //3.合并上一步的信息
    //最大二叉搜索数头节点 maxBSTHead
    //最大二叉搜索树节点个数 maxBSTSize
    //最大二叉搜索树最大值    max
    //最大二叉搜索树最小值    min
    //4.设计递归函数
    //a)base case
    //b)获取左右子树的信息
    //c)整合返回值
    //d)返回整合后的结果

    public ReturnType process(TreeNode curr) {
        //base case
        if (curr == null) {
            return new ReturnType(null, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        //获取左右子树信息
        ReturnType left_data = process(curr.left);
        ReturnType right_data = process(curr.right);
        //整合返回值
        int min = Math.min(curr.val, Math.min(left_data.min, right_data.min));
        int max = Math.max(curr.val, Math.max(left_data.max, right_data.max));
        //判断属那种情况
        //如果最大二叉搜索数在左子树或者右子树中
        TreeNode maxBSTHead = left_data.maxBSTSize > right_data.maxBSTSize ? left_data.maxBSTHead : right_data.maxBSTHead;
        int maxBSTSize = Math.max(left_data.maxBSTSize, right_data.maxBSTSize);
        //如果最大二叉树是X+左最大子树+右最大子树
        if (curr.left == left_data.maxBSTHead && curr.right == right_data.maxBSTHead
                && left_data.max < curr.val && curr.val < right_data.min) {
            maxBSTHead = curr;
            maxBSTSize = left_data.maxBSTSize + right_data.maxBSTSize + 1;
        }
        //返回整合后的结果
        return new ReturnType(maxBSTHead, maxBSTSize, max, min);


    }

    public int getMaxBST(TreeNode root) {
        return process(root).maxBSTSize;
    }

    public static void main(String[] args) {
        Integer[] arr1 = {};
        Integer[] arr2 = {1};
        Integer[] arr3 = {6, 1, 12, 0, 3, 10, 13, null, null, null, null, 4, 14, 20, 16, null, null, null, null, null, null, null, null, 2, 5, 11, 15, null, null, null, null};
        Integer[][] test_case = {arr1, arr2, arr3};
        FindMaxBST app = new FindMaxBST();
        for (Integer[] arr : test_case) {
            TreeNode root = TreeNode.createBinTree(arr);
            int maxBST = app.getMaxBST(root);
            System.out.println(maxBST);
        }

    }

    public class ReturnType {
        public TreeNode maxBSTHead;
        public int maxBSTSize;
        public int max;
        public int min;

        public ReturnType(TreeNode maxBSTHead, int maxBSTSize, int max, int min) {
            this.max = max;
            this.maxBSTHead = maxBSTHead;
            this.maxBSTSize = maxBSTSize;
            this.min = min;
        }

    }
}
