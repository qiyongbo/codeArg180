package binarytree;

import java.util.Stack;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by yongbo1 on 2019/11/30.
 */
public class PrintTree2 {
    public void preOrederUnRecur(TreeNode root) {
        System.out.println("preOrder");
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
//           将头结点插入到栈中
            stack.push(root);
            //开始重复操作
            while (!stack.isEmpty()) {
                //将栈顶元素弹出
                root = stack.pop();
                System.out.print(root.val + " ");
                //先将右节点放入栈中 然后将左节点放入栈中
                if (root.right != null) {
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                }
            }
        }
        System.out.println("\n=======================");
    }


    public void inOrderUnRecur(TreeNode root) {
        System.out.println("inOrder");
        //申请栈
        Stack<TreeNode> stack = new Stack<>();
        //重复操作,如果指针指向的节点不为空,将节点压入栈中,指针指向左节点.
        //  如果指针为空,从栈中弹出栈顶元素,打印其值,将指针指向栈顶的右节点
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.print(root.val + " ");
                root = root.right;
            }
        }

        System.out.println("\n=======================");
    }

    public void posOrderUnRecur1(TreeNode root) {
        System.out.println("posOrder");
        //申请两个栈 stack1 stack2
        //第一步操作是按照中右左的顺序将元素放入stack2中
        //放入的方法为,先将根节点放入stack1中,然后开始重复弹出栈中元素,存入到stack2中,然后将子元素从左至右压入到stack1中.
        //第二步操作就是讲stack2中的元素依次弹出来打印
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        if (root != null) {
            stack1.push(root);
            while (!stack1.isEmpty()) {
                root = stack1.pop();
                stack2.push(root);
                if (root.left != null) {
                    stack1.push(root.left);
                }
                if (root.right != null) {
                    stack1.push(root.right);
                }
            }
            while (!stack2.isEmpty()) {
                root = stack2.pop();
                System.out.print(root.val + " ");
            }
        }

        System.out.println("\n=======================");
    }

    public void posOrderUnRecur2(TreeNode root) {
        //申请一个栈
        //两个变量来记录弹出并打印的节点和栈顶的节点
        //将根节点压入到栈顶
        //判断栈顶节点和刚弹出并打印接节点的关系
        //1判断是否压入左节点
        //判断条件:栈顶节点有左节点,并且刚才打印的节点不是栈顶节点的左右子节点
        //2判断是否压入右子节点
        //判断条件:栈顶节点有右节点,并且刚才打印的节点不是栈顶节点的右子节点
        //3判断是否弹出并打印,然后更新打印节点的指针
        //判断条件:2,3条件都不成立
        if(root != null){
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            TreeNode printed_node = null;
            TreeNode stack_top_node;
            while (!stack.isEmpty()) {
                stack_top_node = stack.peek();
                if (stack_top_node.left != null && printed_node != stack_top_node.left && printed_node != stack_top_node.right) {
                    stack.push(stack_top_node.left);
                } else if (stack_top_node.right != null && printed_node != stack_top_node.right) {
                    stack.push(stack_top_node.right);
                } else {
                    printed_node = stack.pop();
                    System.out.print(printed_node.val + " ");
                }

            }
        }
        System.out.println("\n==============================");

    }


    public static void main(String[] args) {
        Integer[] arr1 = {1, 4, 2, 3, 5, 6, 8, 7, 9, 10, 10, 11, 12, 13, 14};
        Integer[] arr2 = {};
        Integer[] arr3 = {1};
        Integer[] arr4 = {1, 4, 2, null, 3};
        Integer[] arr5 = {1, 2, 3, 4, 5, 6, 7};

        Integer[][] test_case = {arr1, arr2, arr3, arr4, arr5};

        PrintTree2 app = new PrintTree2();

        for (Integer[] arr : test_case) {
            TreeNode root = TreeNode.createBinTree(arr);
//            app.preOrederUnRecur(root);
//            app.inOrderUnRecur(root);
//            app.posOrderUnRecur1(root);
            app.posOrderUnRecur2(root);
        }


    }
}
