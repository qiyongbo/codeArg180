package listNode.mergeListNode;

import listNode.ListNode;

/**
 * Created by yongbo1 on 2019/9/1.
 */
public class MergeListNode {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0), pre = dummy, curr1 = l1, curr2 = l2;

        while (curr1 != null || curr2 != null) {
            if (curr1 != null && curr2 != null) {
                if (curr1.val <= curr2.val) {
                    pre.next = curr1;
                    pre = curr1;
                    curr1 = curr1.next;
                } else {
                    pre.next = curr2;
                    pre = curr2;
                    curr2 = curr2.next;
                }
                continue;
            } else if (curr2 == null) {
                pre.next = curr1;
                break;
            } else {
                pre.next = curr2;
                break;
            }

        }
        return dummy.next;
    }


    public static ListNode buildListNode(int[] arr) {
        ListNode l1 = new ListNode(0);
        ListNode curr = l1;
        for (int i = 0; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        return l1.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 8};
        ListNode l1 = buildListNode(arr);

        int[] arr2 = {5, 7, 9};
        ListNode l2 = buildListNode(arr2);

        MergeListNode mergeListNode = new MergeListNode();
        ListNode listNode = mergeListNode.mergeTwoLists(l1, l2);
        System.out.println(listNode);


    }
}
