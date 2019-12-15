package stackAndQueue.maxHeap;

import java.util.HashMap;
import java.util.Stack;

public class GetMaxTree {

    public void popStackSetMap(Stack<TreeNode> stack, HashMap<TreeNode, TreeNode> map) {
        TreeNode pop = stack.pop();
        if (stack.isEmpty()) {
            map.put(pop, null);
        } else {
            map.put(pop, stack.peek());
        }
    }


    public TreeNode getMaxTree(int[] nums) {
        TreeNode[] nArr = new TreeNode[nums.length];
        int arr_len = nums.length;
        for (int i = 0; i < arr_len; i++) {
            nArr[i] = new TreeNode(nums[i]);
        }
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, TreeNode> lBigMap = new HashMap<>();
        HashMap<TreeNode, TreeNode> rBigMap = new HashMap<>();
        for (int i = 0; i < arr_len; i++) {
            TreeNode curTreeNode = nArr[i];
            while (!stack.isEmpty() && stack.peek().val < curTreeNode.val) {
                popStackSetMap(stack, lBigMap);
            }
            stack.push(curTreeNode);
        }
        while(!stack.isEmpty()){
            popStackSetMap(stack,lBigMap);
        }
        for (int i = arr_len-1; i >= 0; i--) {
            TreeNode curTreeNode = nArr[i];
            while (!stack.isEmpty() && stack.peek().val < curTreeNode.val) {
                popStackSetMap(stack, rBigMap);
            }
            stack.push(curTreeNode);
        }
        while(!stack.isEmpty()){
            popStackSetMap(stack,rBigMap);
        }
        TreeNode head = null;
        for(int i=0;i != nArr.length;i++){
            TreeNode curTreeNode = nArr[i];
            TreeNode left = lBigMap.get(curTreeNode);
            TreeNode right = rBigMap.get(curTreeNode);
            if( left == null && right == null){
                head = curTreeNode;
            }else if(left == null){
                if(right.left == null){
                    right.left = curTreeNode;
                }else{
                    right.right = curTreeNode;
                }
            }else if(right == null){
                if(left.left == null){
                    left.left = curTreeNode;
                }else{
                    left.right = curTreeNode;
                }
            }else{
                TreeNode parent = left.val < right.val ?left :right;
                if(parent.left == null){
                    parent.left = curTreeNode;
                }else{
                    parent.right = curTreeNode;
                }
            }
        }
        return head;



    }

    public static void main(String[] args) {
        int[] arr = {3,2,1,6,0,5};
        GetMaxTree maxTree = new GetMaxTree();
        TreeNode maxTree1 = maxTree.getMaxTree(arr);
        System.out.println(maxTree1);

    }
}

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
