package listNode.reverseList;

import listNode.ListNode;

/**
 * Created by yongbo1 on 2019/7/26.
 */
public class reverseList {
    public static ListNode reverseList(ListNode head) {
        ListNode cursor = head;
        ListNode res = null;
        while(cursor!=null){
            //取出当前节点的下个节点储存起来
            //将当前节点的下一个节点指向反转节点
            //右表指向储存起来的下一个节点
            ListNode nextNode = cursor.next;
            cursor.next = res;
            res=cursor;
            cursor = nextNode;



        }
        return res;

    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for(int i = 0;i<arr.length;i++){
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        reverseList(head.next);
    }
}

