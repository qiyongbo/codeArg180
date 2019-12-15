package listNode.relocation;

import listNode.ListNode;

/**
 * Created by yongbo1 on 2019/9/1.
 */
public class Relocation {
    public void relocation(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode mid = head;
        ListNode right = head.next;
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        right = mid.next;
        mid.next = null;
        merge(head, right);

    }


    public void merge(ListNode left, ListNode right) {
        ListNode tmp = null;
        while (left.next != null) {
            tmp = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = tmp;
        }
        left.next = right;

    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        ListNode head = ListNode.buildListNode(arr);
        new Relocation().relocation(head);
        head.printVal();


    }
}
