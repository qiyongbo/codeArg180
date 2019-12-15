package listNode;

/**
 * Created by yongbo1 on 2019/8/2.
 */

import java.util.Stack;

/**
 * 判断一个链表是不是回形针
 */
public class IsPalindrom {

    //在不知道链表长度的情况下,不知道链表的奇偶,如果存左边的一部分,不知道中间是不是该跳过,
//   所以要取右边的,遍历一圈半链表得到结论
    public boolean isPalindrom(ListNode head) {
        if (head == null) {
            return true;
        }


        Stack<Integer> stack = new Stack<>();
        ListNode right = head.next;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            right = right.next;
            fast = fast.next.next;
        }
        while(right!=null){
            stack.push(right.val);
            right = right.next;
        }
        while(!stack.isEmpty()){
            if(stack.pop() != head.val){
                return false;
            }
            head = head.next;
        }


        return true;
    }


    //快慢指针,反转左部链表,偶数 fast为空 奇数 fast.next 为空
    public boolean isPalindrom2(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        ListNode rever = null;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            ListNode tmp = slow.next;
            slow.next = rever;
            rever = slow;
            slow = tmp;
        }
        //fast有值说明是奇数,跳过
        if(fast!=null){
            slow = slow.next;
        }
        while(slow!=null){
            if(slow.val != rever.val){
                return false;
            }
            slow = slow.next;
            rever = rever.next;
        }
        return true;


    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 2, 1};
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 0; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        head = head.next;
        IsPalindrom p = new IsPalindrom();
        System.out.println(p.isPalindrom2(head));
    }
}
