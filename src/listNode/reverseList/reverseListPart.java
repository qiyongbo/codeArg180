package listNode.reverseList;

import listNode.ListNode;

/**
 * Created by yongbo1 on 2019/7/29.
 */
public class reverseListPart {
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        ListNode curr = head;
        ListNode reversList = null;
        ListNode mPreNode = null;
        ListNode mNode = null;
        int index = 1;
        while (curr != null) {
            if (index < m) {
                if (index == m - 1) {
                    mPreNode = curr;
                }
                curr = curr.next;
            } else if (index >= m && index <= n) {
                if (index == m) {
                    mNode = curr;

                }
                ListNode temp = curr.next;
                curr.next = reversList;
                reversList = curr;
                curr = temp;
                if (index == n) {
                    if (mPreNode == null) {
                        head = reversList;
                    } else {
                        mPreNode.next = reversList;
                    }
                }
            } else {
                if (index == n + 1) {
                    mNode.next = curr;
                }
                curr = curr.next;

            }
            index++;
        }
        return head;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        int index = 1;
        ListNode curr = head;
        ListNode mPreNode = null;
        //记录m之前的那个节点 index ==m时 或者 列表结尾处  跳出
        while (curr != null && index < m) {
            if (index == m - 1) {
                mPreNode = curr;
            }
            curr = curr.next;
            index++;
        }
        //记录m这个节点,和n之后的节点连接
        ListNode mNode = curr;
        ListNode reverse = null;
        //m到n之间 反转列表 index == n+1时 或者 列表结尾处 跳出
        while (curr != null && index >= m && index <= n) {
            ListNode tmp = curr.next;
            curr.next = reverse;
            reverse = curr;
            curr = tmp;
            index++;
        }
        //n之后
        //n+1时 前后相连
        if (mPreNode == null) {//m=1时mPreNode是null head变为翻转后的列表
            head = reverse;
        } else {
            mPreNode.next = reverse;
        }
        //将n+1节点接入反转列表的后面
        mNode.next = curr;
        return head;

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3,4,5,6,7};
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 0; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        ListNode res = new reverseListPart().reverseBetween(head.next, 1, 3);
        System.out.println(res);
    }
}
