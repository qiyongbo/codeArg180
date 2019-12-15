package stackAndQueue.maxHeap;

/**
 * Created by yongbo1 on 2019/7/24.
 */

import java.util.Arrays;

import static com.sun.tools.doclint.Entity.le;

/**
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：

 二叉树的根是数组中的最大元素。
 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 通过给定的数组构建最大二叉树，并且输出这个树的根节点。

 */

public class BuildMaxTree {
    public TreeNode constructMaximumBinaryTree(int[] nums){
        return buildMaxTree(nums,0,nums.length-1);
    }

    public TreeNode buildMaxTree(int[] nums,int low,int heigh){
        if(low>heigh){
            return null;
        }
        int max_index = getMaxIndex(nums,low,heigh);
        TreeNode treeNode = new TreeNode(nums[max_index]);
        treeNode.left = buildMaxTree(nums,low,max_index-1);
        treeNode.right = buildMaxTree(nums,max_index+1,heigh);
        return treeNode;

    }

    public int getMaxIndex(int[] nums,int low,int heigh){
        int max_index = 0;
        int max_value = Integer.MIN_VALUE;
        int nums_len = nums.length;
        for(int i = low;i<=heigh;i++){
            if(max_value<=nums[i]){
                max_value = nums[i];
                max_index = i;
            }
        }
        return max_index;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,1,6,0,5};
        BuildMaxTree buildMaxTree = new BuildMaxTree();
        buildMaxTree.constructMaximumBinaryTree(arr);

    }

}
