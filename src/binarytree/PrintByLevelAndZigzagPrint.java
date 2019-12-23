package binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;



/**
 * Created by yongbo1 on 2019/12/21.
 */
public class PrintByLevelAndZigzagPrint {


    /**
     * 本题需要申请一个队列 记录两个变量
     * 队列的意义是每打印一个节点记录一下其子节点进行打印
     * 记录的变量分别是当前的最后一个节点和下一行的最后一个节点
     * 具体步骤如下:
     * 准备工作:将头节点放入到队列中,记录当前行最后一个节点为头结点,下一行的节点先不处理
     * 开始:1将队列中的节点弹出打印
     * 2.将子节点压入到队列中,并且记录下一行最后一个节点,注意非空判断
     * 3.判断是否是最后一个元素,如果是,打印换行符,将本行最后一个节点更新为下一行的最后一个节点 这时注意检查队列是否为空,如果为空,就不用打印换行和下一行的标题信息了
     */

    public void pintByLevel(TreeNode root) {
        //边界检查
        if (root == null) {
            return;
        }
//        申请变量
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode last = root;
        TreeNode next_last = null;
        queue.offer(root);
        int level = 1;
        System.out.print("-----层级打印------\nLevel " + 1 + ":");
        while (!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            System.out.print(currNode.val + "\t");
            if (currNode.left != null) {
                queue.offer(currNode.left);
                next_last = currNode.left;
            }
            if (currNode.right != null) {
                queue.offer(currNode.right);
                next_last = currNode.right;
            }
            if (currNode == last && !queue.isEmpty()) {
                System.out.print("\n" + "Level " + (++level) + ":");
                last = next_last;
            }
        }


    }

    /**
     * zigzag 打印
     * 这里需要用到java linkedlist的双端队列属性
     * 可以将双端队列的两头当成两个栈
     * 从左边往右打印的时候,左边弹出,弹出后从右边放入,放入时先放入左节点再放入右节点,从右向左反之
     * 规律就是从那边打印,就从那边弹出节点,然后从另一边放入子节点.顺序也是那边打印先放哪边的子节点
     * 在这个规律下,不管从左往右还是从右往左,队列里面的元素都是从左向右放置的.
     * 从左边往右打印的时候,队列的左边弹出的方向是从左向右的,所以在放入右边时,也是先放左节点再放右节点的
     * 这种方式下,上一层放入的最后一个节点其实是下一层的第一个节点,最先放入的节点时下一行需要换行的节点,所以要记录下一行第一个放入的节点
     *
     * 和上面的层级打印不同的是,需要记录一下方向,换行的节点是第一个要放入的节点,而不是最后放入的节点
     */

    public void zigzagprint(TreeNode root) {
        //边界检查
        if (root == null) {
            return;
        }
//        申请变量
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode last = root;
        TreeNode next_frist = null;
        deque.offer(root);
        boolean is_left_to_right = true;
        String direct_str = " from left to right ";
        int level = 1;
        System.out.print("-----zigzag打印------\nLevel " + 1 + direct_str + ":");
        TreeNode currNode;
        while (!deque.isEmpty()) {
            if (is_left_to_right) {
                currNode = deque.pollFirst();
                System.out.print(currNode.val + "\t");
                if (currNode.left != null) {
                    deque.offerLast(currNode.left);
                    if(next_frist == null){
                        next_frist = currNode.left;
                    }
                }
                if (currNode.right != null) {
                    deque.offerLast(currNode.right);
                    if(next_frist == null){
                        next_frist = currNode.right;
                    }
                }

            } else {
                currNode = deque.pollLast();
                System.out.print(currNode.val + "\t");

                if (currNode.right != null) {
                    deque.offerFirst(currNode.right);
                    if(next_frist == null){
                        next_frist = currNode.right;
                    }
                }
                if (currNode.left != null) {
                    deque.offerFirst(currNode.left);
                    if(next_frist == null){

                        next_frist = currNode.left;
                    }
                }

            }
            if (currNode == last) {
                System.out.print("\n");
                last = next_frist;
                next_frist = null;
                is_left_to_right = is_left_to_right ? false : true;
                direct_str = is_left_to_right ? " from left to right " : " from right to left  ";
                if (!deque.isEmpty()) {
                    System.out.print("Level "
                            + (++level) +
                            direct_str
                            + ":");
                }




            }

        }
    }


    public static void main(String[] args) {
        Integer[] arr1 = {1, 4, 2, 3, 5, 6, 8, 7, 9, 10, 10, 11, 12, 13, 14};
        Integer[] arr2 = {};
        Integer[] arr3 = {1};
        Integer[] arr4 = {1, 4, 2, null, 3};
        Integer[] arr5 = {1, 2, 3, 4, 5, 6, 7};

        Integer[][] test_case = {arr1, arr2, arr3, arr4, arr5};
        PrintByLevelAndZigzagPrint app = new PrintByLevelAndZigzagPrint();

        for (Integer[] arr : test_case) {
            TreeNode root = TreeNode.createBinTree(arr);
            app.zigzagprint(root);
        }


    }
}
