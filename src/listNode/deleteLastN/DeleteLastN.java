package listNode.deleteLastN;

/**
 * Created by yongbo1 on 2019/7/26.
 */

import listNode.ListNode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 */


public class DeleteLastN {
    public ListNode removeNthFromEnd2(ListNode head, int n) {
//        在列表头部放入哑结点,简化极端情况处理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        int length = 0;
//        遍历一次链表,直到末尾,获取链表
        while (curr.next != null) {
            length++;
            curr = curr.next;
        }
        int del_index = length - n;
        curr = dummy;
        for (int i = 0; i < del_index; i++) {
            curr = curr.next;
        }
        if (curr.next != null) {
            curr.next = curr.next.next;
        }
        return dummy.next;
    }
    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cursor1 = dummy;
        for(int i = 0;i<n;i++){
            cursor1 = cursor1.next;
        }
        ListNode cursor2 = dummy;
        while(cursor1.next!=null){
            cursor1 = cursor1.next;
            cursor2 = cursor2.next;
        }
        if(cursor2.next != null){
            cursor2.next = cursor2.next.next;
        }
        return dummy.next;
    }
}

