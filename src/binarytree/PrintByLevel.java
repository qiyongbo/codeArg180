package binarytree;

import java.util.LinkedList;
import java.util.Queue;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by yongbo1 on 2019/10/3.
 */
public class PrintByLevel {
    /**
     * 层级打印
     *
     * @param root
     * @return
     */
    public void printByLevel(TreeNode root) {
        // 申请一个queue,用于存放树中的节点
        Queue<TreeNode> queue = new LinkedList<>();
        //申请一个变量,记录每行的最后一个节点
        TreeNode last = root;
        //申请一个变量,记录当前节点的最后一个节点
        TreeNode nLast = null;
        //放入根节点
        queue.add(root);
        int level = 1;
        //打印层级标题
        System.out.print("Level " + level + ":");
        // 直到队列为空
        while (!queue.isEmpty()) {
            //队列中弹出节点
            TreeNode curr = queue.poll();
            //放入弹出节点的子节点
            if (curr.left != null) {
                queue.add(curr.left);
                nLast = curr.left;
            }
            if (curr.right != null) {
                queue.add(curr.right);
                nLast = curr.right;
            }
            //打印弹出的节点
            System.out.print(curr.val + " ");
            //如果是最后一个节点
            if (curr == last) {
                //更新最新一层的最后一个节点
                last = nLast;
                //打印回车+打印标题 +层级加1
                if (!queue.isEmpty()) System.out.print("\nLevel " + ++level + ":");


            }


        }

    }

    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 3, 5, 7,null, 8};
        TreeNode root = TreeNode.createBinTree(arr);
        new PrintByLevel().printByLevel(root);
    }
}
