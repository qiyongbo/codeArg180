package listNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongbo1 on 2019/8/11.
 */
public class CopyRandomNode {
    public static RandomNode copyRandomList(RandomNode head) {
        RandomNode curr = head;
        while (curr != null) {
            RandomNode tmp = curr.next;
            curr.next = new RandomNode(curr.val, curr.next, curr.random);
            curr.next.next = tmp;
            curr = tmp;

        }

        curr = head;
        RandomNode currCopy = null;
        RandomNode next = null;
        while (curr != null) {
            next = curr.next.next;
            currCopy = curr.next;
            if (currCopy.random != null) {
                currCopy.random = currCopy.random.next;
            }
            curr = next;
        }
        RandomNode res = head.next;
        curr = head;

        while (curr != null) {
            next = curr.next.next;
            currCopy = curr.next;
            if (currCopy.next != null) {
                currCopy.next = currCopy.next.next;

            }
            curr.next = next;
            curr = next;
        }
        return res;

    }


    public static void main(String[] args) {
        List<RandomNode> list = new ArrayList<>();
        RandomNode node2 = new RandomNode(2, null, null);
        node2.random = node2;
        RandomNode node1 = new RandomNode(1, node2, node2);
        RandomNode head = node1;
        RandomNode curr = head;
        System.out.println("-----------");
        while (curr != null) {
            System.out.println(curr + "|||" + curr.next + "|||" + curr.random);
            curr = curr.next;
        }
        System.out.println("-----------");
        RandomNode res = copyRandomList(head);
        curr = res;
        while (curr != null) {
            System.out.println(curr + "|||" + curr.next + "|||" + curr.random);
            curr = curr.next;
        }

        System.out.println("-----------");
        curr = head;
        while (curr != null) {
            System.out.println(curr + "|||" + curr.next + "|||" + curr.random);
            curr = curr.next;
        }


    }
}
