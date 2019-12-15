package listNode;

/**
 * Created by yongbo1 on 2019/7/31.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
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

    public void printVal() {
        ListNode curr = this;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }
}
