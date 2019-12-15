package listNode.listSort;

import listNode.ListNode;

/**
 * Created by yongbo1 on 2019/8/31.
 */
public class InsertionSort {
    private ListNode insertionsort(ListNode head){
        ListNode dummy = new ListNode(0),sorted_pre;
        dummy.next = head;
        ListNode prev = head;
        while(prev!= null && prev.next !=null){
            if(prev.val <=prev.next.val){
                prev = prev.next;
                continue;
            }
            sorted_pre = dummy;
            while(sorted_pre.next.val < prev.next.val){
                sorted_pre = sorted_pre.next;
            }
            ListNode curr = prev.next;
            prev.next = curr.next;
            curr.next = sorted_pre.next;
            sorted_pre.next = curr;
        }
        return dummy.next;
    }



    private ListNode insertionsort2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode sorted = head;
        ListNode sorted_curr;
        ListNode sorted_tail = head;
        ListNode curr = head.next;
        ListNode tmp;
        while (curr != null) {

            sorted_curr = sorted;
            tmp = curr.next;
            sorted_tail.next = tmp;
            while (sorted_curr != sorted_tail.next) {
                if (curr.val >= sorted_curr.val) {
                    if (sorted_curr.next == null || curr.val <= sorted_curr.next.val || sorted_curr == sorted_tail) {//将遍历的节点放置到排序的列表中
                        curr.next = sorted_curr.next;
                        sorted_curr.next = curr;
                        sorted_tail = sorted_curr == sorted_tail ? curr : sorted_tail;
                        break;
                    } else {
                        sorted_curr = sorted_curr.next;
                    }
                } else {//放在链表的头部
                    curr.next = sorted;
                    sorted = curr;
                    break;

                }
            }
            curr = tmp;
        }
        return sorted;
    }

    public static void main(String[] args) {
        int[] arr = {4,5,3,0,8,2,1};
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 0; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        head = head.next;
        InsertionSort insertionsort = new InsertionSort();
        ListNode smallestPre = insertionsort.insertionsort(head);
        while (smallestPre != null) {
            System.out.println(smallestPre.val);
            smallestPre = smallestPre.next;
        }

    }


}
