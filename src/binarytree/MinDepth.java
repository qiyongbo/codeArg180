package binarytree;

/**
 * Created by yongbo1 on 2019/12/1.
 */
public class MinDepth {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root, 1);
    }

    private int process(TreeNode root, int depth) {
        //如果有左子节点,对左子节点进行递归,获取其最小深度
        //如果有右子节点,对右子节点进行递归,获取其最小深度,然后和左几点的进行比较
        //如果左右都没有子节点,返回当前的深度
        int max_int = Integer.MAX_VALUE;
        if (root.left != null) {
            max_int = Math.min(max_int, process(root.left, depth + 1));
        }
        if (root.right != null) {
            max_int = Math.min(max_int, process(root.right, depth + 1));
        }
        if (root.left == null && root.right == null) {
            return depth;
        }
        return max_int;

    }


    public static void main(String[] args) {
//        Integer[] arr1 = {1, 4, 2, 3, 5, 6, 8, 7, 9, 10, 10, 11, 12, 13, 14};
//        Integer[] arr2 = {};
//        Integer[] arr3 = {1};
//        Integer[] arr4 = {1, 4, 2, null, 3};
        Integer[] arr5 = {1, 2, 3, 4, 5};

        Integer[][] test_case = { arr5};

        MinDepth app = new MinDepth();

        for (Integer[] arr : test_case) {
            TreeNode root = TreeNode.createBinTree(arr);
            System.out.println(app.minDepth(root));
        }
    }
}
