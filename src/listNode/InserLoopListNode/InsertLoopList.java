package listNode.InserLoopListNode;

import listNode.ListNode;
import listNode.listSort.ChoiceSort;

/**
 * Created by yongbo1 on 2019/9/1.
 */
public class InsertLoopList {
    public ListNode insert(ListNode head, int insertVal) {
        ListNode node = new ListNode(insertVal);
        if (head == null) {
            node.next = node;
            return node;
        }
        ListNode pre = head;
        ListNode curr = head.next;
        while (curr != head) {
            if (pre.val <= insertVal && insertVal <= curr.val) {
                break;
            } else if (pre.val > curr.val && pre.val <= insertVal && curr.val <= insertVal) {
                break;
            } else if (pre.val > curr.val && pre.val >= insertVal && curr.val >= insertVal) {
                break;
            }
            pre = curr;
            curr = curr.next;
        }
        pre.next = node;
        node.next = curr;
        if (insertVal <= head.val) {
            return node;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5};
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 0; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        curr.next = head.next;
        head = head.next.next;
        InsertLoopList solution = new InsertLoopList();
        head = solution.insert(head, 0);
        System.out.println(head);

    }
}
