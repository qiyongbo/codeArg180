package binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import static com.sun.tools.doclint.Entity.ne;
import static javafx.scene.input.KeyCode.Q;
import static javafx.scene.input.KeyCode.T;

/**
 * Created by yongbo1 on 2019/9/18.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public static TreeNode createBinTree(Integer[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        if (array.length == 1) {
            return new TreeNode(array[0]);
        }
        List<TreeNode> nodeList = new LinkedList<>();
        // 将一个数组的值依次转换为Node节点
        for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {
            Integer exp = array[nodeIndex];
            if (exp == null) {
                nodeList.add(null);
            } else {
                nodeList.add(new TreeNode(exp));
            }

        }
        // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树
        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
            if (nodeList.get(parentIndex) != null) {
                // 左孩子
                nodeList.get(parentIndex).left = nodeList
                        .get(parentIndex * 2 + 1);
                // 右孩子
                nodeList.get(parentIndex).right = nodeList
                        .get(parentIndex * 2 + 2);
            }

        }
        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
        int lastParentIndex = array.length / 2 - 1;
        if (nodeList.get(lastParentIndex) != null) {
            // 左孩子
            nodeList.get(lastParentIndex).left = nodeList
                    .get(lastParentIndex * 2 + 1);
            // 右孩子,如果数组的长度为奇数才建立右孩子
            if (array.length % 2 == 1) {
                nodeList.get(lastParentIndex).right = nodeList
                        .get(lastParentIndex * 2 + 2);
            }
        }

        return nodeList.get(0);
    }


    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 4, 6, 7, 8};
        TreeNode.createBinTree(arr);
    }

}
