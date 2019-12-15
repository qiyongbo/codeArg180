package binarytree;

import java.util.Stack;

/**
 * Created by yongbo1 on 2019/10/15.
 */
public class CheckSerchBinaryTree {

    public boolean isValBST(TreeNode root) {
        return true;
    }

    public boolean helper(TreeNode node, Integer low, Integer high) {
        if (node == null) {
            return true;
        }
        int val = node.val;
        if (low != null && val <= low) return false;
        if (high != null && val >= high) return false;

        if (!helper(node.left, low, val)) return false;
        if (!helper(node.right, val, high)) return false;
        return true;

    }


    public boolean inoderCheck(TreeNode root) {
        double pre = - Double.MAX_VALUE;
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                // 左节点放置到stack中
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
//                    打印节点并且
                    root = stack.pop();
                    if (pre >= root.val)
                        return false;
                    pre = root.val;
                    root = root.right;
                }
            }

        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] arr = {0};
//        TreeNode root = TreeNode.createBinTree(arr);
        TreeNode root = new TreeNode(0);
        CheckSerchBinaryTree app = new CheckSerchBinaryTree();
        boolean res = app.inoderCheck(root);
        System.out.println(res);
    }
}
