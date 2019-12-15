package listNode.reversByK;

import listNode.ListNode;
import listNode.josephusKill.JosepusKill;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongbo1 on 2019/8/19.
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例 :
 * <p>
 * 给定这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseByKey {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dumy = new ListNode(0);
        dumy.next = head;
        ListNode prev = dumy;
        ListNode tail = head;
        ListNode seq = head;
        ListNode last;
        int step = 0;
        while (tail != null) {
            step++;
            tail = tail.next;
            if (step == k) {
                last = seq;
                seq = reverseSeq(seq, k);
                prev.next = seq;
                last.next = tail;
                prev = last;
                seq =tail;
                step = 0;

            }
        }
        return dumy.next;

    }

    public ListNode reverseSeq(ListNode seq, int k) {
        ListNode dumy = null;
        ListNode tmp;
        int step = 1;
        while (step <= k) {
            tmp = seq.next;
            seq.next = dumy;
            dumy = seq;
            seq = tmp;
            step++;
        }
        return dumy;

    }





    public static void main(String[] args) {
        int[] arr = {};
        Set<ListNode> set = new HashSet<>();
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 0; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        ReverseByKey reverse = new ReverseByKey();
        ListNode res = reverse.reverseKGroup(head.next, 3);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
