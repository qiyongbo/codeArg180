package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


/**
 * Created by yongbo1 on 2019/9/18.
 */
public class PrintTree {
    /**
     * 中序打印
     * @param root
     */
    public void inorderTraversal2(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.println(root.val);
        inorderTraversal(root.right);
    }

    /**
     * 前序打印
     * @param root
     */
    public void preorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(res, root);
        return res;

    }

    public void inorder(List<Integer> res, TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                inorder(res, root.left);
            }
            res.add(root.val);
            if (root.right != null) {
                inorder(res, root.right);
            }
        }
    }


    public void inorderStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.println(root.val);
                root = root.right;
            }
        }


    }

    public void preoderStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            System.out.println(root.val);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
    }

    /**
     * 后序打印
     * @param root
     */
    public void posorderStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> res_stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            res_stack.push(root.val);
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
        while (!res_stack.isEmpty()) {
            System.out.println(res_stack.pop());
        }
    }







    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 3, 5, 6, 8};
        PrintTree app = new PrintTree();
        TreeNode root = TreeNode.createBinTree(arr);

        app.posorderStack(root);
//        List<Integer> list = app.inorderTraversal(root);
//        for (Integer value:list) {
//            System.out.println(value);
//        }

    }
}
