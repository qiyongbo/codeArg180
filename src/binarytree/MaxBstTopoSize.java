package binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongbo1 on 2019/12/14.
 */
public class MaxBstTopoSize {

    public static void main(String[] args) {
        Integer[] arr1 = {};
        Integer[] arr2 = {1};
        Integer[] arr3 = {6, 1, 12, 0, 3, 10, 13, null, null, null, null, 4, 14, 20, 16, null, null, null, null, null, null, null, null, 2, 5, 11, 15, null, null, null, null};
        Integer[][] test_case = {arr1, arr2, arr3};
        MaxBstTopoSize app = new MaxBstTopoSize();
        for (Integer[] arr : test_case) {
            TreeNode root = TreeNode.createBinTree(arr);
            int maxBST = app.bstTopoSize2(root);
            System.out.println(maxBST);
        }

    }

    //O(n^2)的方法

    /***
     * 获取最大的二叉搜索树拓扑大小
     *
     * @param root
     * @return
     */
    private int getMaxBSTTopo(TreeNode root) {
        //边界验证 root为空 退出
        //获取以当前节点为顶点的最大拓扑结构
        //获取存在于左子树的最大拓扑结构
        //获取存在于右子树中的最大拓扑结构
        //返回上述三个值的最大值
        if (root == null) {
            return 0;
        }
        int max = maxTopo(root, root);
        max = Math.max(getMaxBSTTopo(root.left), max);
        max = Math.max(getMaxBSTTopo(root.right), max);
        return max;
    }


    /***
     * 获取以在当前节点下,已考察节点为顶点的拓扑树的大小
     *
     * @param currNode 当前节点
     * @param testNode 考察的节点
     * @return
     */
    private int maxTopo(TreeNode currNode, TreeNode testNode) {
        //根据二叉搜索的方式在currNode中查找和testNode值相同的节点,看看能否找到和testNode一样的节点
        //如果有返回以左右节点为顶点的最大拓扑大小
        if (currNode != null && testNode != null && testBstNode(currNode, testNode)) {
            return maxTopo(currNode, testNode.left) +
                    maxTopo(currNode, testNode.right) + 1;
        }
        return 0;
    }

    /***
     * 按照testNode的值去二叉搜索cur看testNode是否是currNode的二叉搜索子节点
     *
     * @param cursorNode 游标节点
     * @param testNode   考察节点
     * @param testValue  考察节点的值
     * @return
     */

    private boolean testBstNode(TreeNode cursorNode, TreeNode testNode) {
        //base case
        //如果游标节点为空,说明最后没有找到和testNode节点一致的节点.说明不是二叉搜索树,返回false
        //如果游标节点和考察节点一致,说明是二叉搜搜树,返回true
        //递归继续
        //如果游标节点的值大于考察节点的值,向左移动,否则向右移动
        if (cursorNode == null) {
            return false;
        }
        if (cursorNode == testNode) {
            return true;
        }
        return testBstNode(cursorNode.val > testNode.val ? cursorNode.left : cursorNode.right, testNode);
    }

    //O(n)的方法
    //第一层 通过后序遍历,先获取左右节点的拓扑贡献值,然后根据父节点修改拓扑贡献值
    //第二层 设计后序遍历递归函数
    // 1.base case  && 边界验证
    // 如果节点为空 返回0
    // 2.获取左右节点的最大拓扑数
    // 3.根据当前节点 修正左右子树的拓扑贡献值
    // 4.获取修正后的左右子树贡献值,并且记录下来
    // 5.返回当前最大拓扑树 左右节点的最大拓扑树 三者的最大值
    //第三层 修改最大拓扑贡献值
    // 1.base case
    // 如果节点为空或者节点已删除 返回0
    // 2.如果违反了二叉搜索树的约定,删除当前节点 返回当前节点的拓扑贡献值 (左节点拓扑贡献值 + 右节点拓扑贡献值 + 1 )
    // 3.如果没有违反,左子树向右子树走,右子树向左子树走,去修正最大拓扑贡献值
    // 4.获取修正后的要删除的拓扑贡献值,从当前节点的拓扑贡献值中减去
    // 5.返回要删除的拓扑贡献值,供父节点删除

    /**
     * 获取最大拓扑树
     *
     * @param root
     * @return
     */
    public int bstTopoSize2(TreeNode root) {
        Map<TreeNode, TopoContribute> map = new HashMap<>();
        //后序遍历二叉树,获取最大拓扑
        return poseOrderGetMaxTopoSize(root, map);
    }

    private int poseOrderGetMaxTopoSize(TreeNode root, Map<TreeNode, TopoContribute> map) {
        //1.base case  && 边界验证
        if (root == null) {
            return 0;
        }
//        2.获取左右节点的最大拓扑数
        int leftMaxTopo = poseOrderGetMaxTopoSize(root.left, map);
        int rightMaxTopo = poseOrderGetMaxTopoSize(root.right, map);
        // 3.根据当前节点 修正左右子树的拓扑贡献值
        modifyMap(root.left, root.val, map, true);
        modifyMap(root.right, root.val, map, false);
        //获取修正后的左右子树贡献值
        TopoContribute leftNewRecord = map.get(root.left);
        TopoContribute rightNewRecord = map.get(root.right);
        int leftNewMaxTopo = leftNewRecord == null ? 0 : leftNewRecord.leftTopoContribute + leftNewRecord.rightTopoContribute + 1;
        int rightNewMaxTopo = rightNewRecord == null ? 0 : rightNewRecord.leftTopoContribute + rightNewRecord.rightTopoContribute + 1;
        int currMaxTopo = leftNewMaxTopo + rightNewMaxTopo + 1;
        //TODO 这一点很重要 记录修正后的左右最大拓扑贡献
        map.put(root,new TopoContribute(leftNewMaxTopo,rightNewMaxTopo));
//        5.返回当前最大拓扑树 左右节点的最大拓扑树 三者的最大值
        return Math.max(currMaxTopo, Math.max(leftMaxTopo, rightMaxTopo));
    }

    private int modifyMap(TreeNode cursorNode, int rootVal, Map<TreeNode, TopoContribute> map, boolean isLeft) {
        // 1.base case
        // 如果节点为空或者节点已删除 返回0
        if(cursorNode == null || !map.containsKey(cursorNode)){
            return 0;
        }
        TopoContribute topuContribute = map.get(cursorNode);
        // 2.如果违反了二叉搜索树的约定,删除当前节点 返回当前节点的拓扑贡献值 (左节点拓扑贡献值 + 右节点拓扑贡献值 + 1 )
        if((isLeft && cursorNode.val > rootVal) || (!isLeft) &&  cursorNode.val < rootVal ){
            map.remove(cursorNode);
            return topuContribute.leftTopoContribute + topuContribute.rightTopoContribute + 1;
        }else{
            // 3.如果没有违反,左子树向右子树走,右子树向左子树走,去修正最大拓扑贡献值
            int minus = modifyMap(isLeft ? cursorNode.right :cursorNode.left ,rootVal,map,isLeft);
            // 4.获取修正后的要删除的拓扑贡献值,从当前节点的拓扑贡献值中减去

            if(isLeft){
                topuContribute.rightTopoContribute = topuContribute.rightTopoContribute - minus;
            }else{
                topuContribute.leftTopoContribute = topuContribute.leftTopoContribute - minus;
            }
            map.put(cursorNode,topuContribute);
            // 5.返回要删除的拓扑贡献值,供父节点删除
            return minus;
        }


    }


    private class TopoContribute {
        int leftTopoContribute;
        int rightTopoContribute;

        public TopoContribute(int leftTopoContribute, int rightTopoContribute) {
            this.leftTopoContribute = leftTopoContribute;
            this.rightTopoContribute = rightTopoContribute;
        }
    }
}
