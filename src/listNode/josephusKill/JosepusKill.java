package listNode.josephusKill;

import listNode.ListNode;

/**
 * Created by yongbo1 on 2019/7/31.
 */
public class JosepusKill {

    public ListNode josephsKill(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = head;
        int i = 1;
        while (!curr.next.equals(curr)) {
            if (i % n == 0) {
                curr.next = curr.next.next;
                i++;
            }
            curr = curr.next;
            i++;

        }
        return curr;
    }

    public ListNode jusephusKill2(ListNode head, int m) {
        if (head == null || m < 1)
            return head;
        int n = 1;//统计一共有多少个节点
        ListNode last = head;
        while (last.next != head) {
            n++;
            last = last.next;
        }
        //直接用递归算出目的编号
        int des = f(n, m);
        //把目的节点取出来
        while (--des != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    private static int f(int n, int m) {
        if (n == 1) {
            return 1;
        }
        int i = (f(n - 1, m) + m - 1) % n + 1;
        return i;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 0; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        curr.next = head.next;
        JosepusKill jose = new JosepusKill();
        ListNode listNode = jose.jusephusKill2(head.next, 4);
        System.out.println(listNode.val);

    }


}


