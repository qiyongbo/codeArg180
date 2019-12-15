package listNode.listSort;

import listNode.ListNode;

/**
 * 对链表进行选择排序
 */
public class ChoiceSort {
    public ListNode choiceSort(ListNode head) {
        ListNode smallestPre = null;//最小节点的前一个节点
        ListNode smallest = null;//最小节点
        ListNode tail = null;//排序后的结尾
        ListNode curr = head;
        while(curr != null){
            smallest = curr;
            smallestPre = getSmallestPre(curr);
            if(smallestPre != null){
                smallest = smallestPre.next;
                smallestPre.next = smallest.next;//删除samllest节点
            }
            if(curr == smallest){//说明最小值没有到curr的前面,curr需要向下移动一个指针
                curr = curr.next;
            }
            if(tail == null){//第一个元素,将head赋值为最小值
                head = smallest;
            }else{
                tail.next = smallest;//将最小值赋值到tail的结尾
            }
            tail = smallest;


        }

        return head;
    }


    public ListNode getSmallestPre(ListNode head) {
        ListNode smallestPre = null;
        ListNode small = head;
        ListNode pre = head;
        ListNode curr = head.next;

        while (curr != null) {
            if (curr.val <= small.val) {
                smallestPre = pre;
                small = curr;
            }
            pre = curr;
            curr = curr.next;
        }
        return smallestPre;

    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 4, 0, 8, 1};
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 0; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        head = head.next;
        ChoiceSort choiceSort = new ChoiceSort();
        ListNode smallestPre = choiceSort.choiceSort(head);
        while(smallestPre!=null){
            System.out.println(smallestPre.val);
            smallestPre = smallestPre.next;
        }

    }
}
