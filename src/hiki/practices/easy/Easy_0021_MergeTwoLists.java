package hiki.practices.easy;

/**
 * 21. 合并两个有序链表
 */
public class Easy_0021_MergeTwoLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);

        ListNode temp = result;
        while( l1 != null && l2 != null ){
            if( l1.val <= l2.val ){
                temp.next = l1;
                l1 = l1.next;
            } else{
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1 == null ? l2 : l1;
        return result.next;
    }
}