package listNode;

/**
 * Created by yongbo1 on 2019/8/2.
 */
public class SplitListNode {
    public ListNode splitListNode(ListNode head, int x) {
        ListNode smallList = new ListNode(0);
        ListNode bigList = new ListNode(0);
        ListNode equalList = new ListNode(0);
        ListNode smallcurr = smallList;
        ListNode bigcurr = bigList;
        ListNode equalcur = equalList;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == x) {
                equalcur.next = curr;
                equalcur =  equalcur.next;
            } else if (curr.val > x) {
                bigcurr.next = curr;
                bigcurr =  bigcurr.next;
            } else {
                smallcurr.next = curr;
                smallcurr =  smallcurr.next;
            }
            if(curr.next == null){
                bigcurr.next = null;
                smallcurr.next = null;
                equalcur.next = null;
            }
            curr = curr.next;
        }
        if(equalList.next == null){
            smallcurr.next = bigList.next;
        }else{
            smallcurr.next = equalList.next;
            equalcur.next = bigList.next;
        }
        return smallList.next;
    }

    public ListNode partition(ListNode head, int x){
        ListNode smallList = new ListNode(0);
        ListNode bigList = new ListNode(0);
        ListNode smallcurr = smallList;
        ListNode bigcurr = bigList;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val >= x) {
                bigcurr.next = curr;
                bigcurr =  bigcurr.next;
            } else {
                smallcurr.next = curr;
                smallcurr =  smallcurr.next;
            }
            if(curr.next == null){
                bigcurr.next = null;
                smallcurr.next = null;
            }
            curr = curr.next;
        }
        smallcurr.next = bigList.next;
        return smallList.next;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,3,2,5,2};
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 0; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        head = head.next;
        SplitListNode s = new SplitListNode();
        s.partition(head,3);
    }
}
