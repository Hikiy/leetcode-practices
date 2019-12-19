package hiki.practices.easy;

/**
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 */
public class Easy_0160_GetIntersectionNode {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //计算两个链表长度，找出差值，然后快慢指针
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tA = headA;
        ListNode tB = headB;
        int countA = 0;
        int countB = 0;
        while( tA != null ){
            countA++;
            tA = tA.next;
        }
        while( tB != null ){
            countB++;
            tB = tB.next;
        }
        int pre;
        tA = headA;
        tB = headB;

        if( countA > countB){
            pre = countA - countB;
            for(int i = 0; i < pre; i++){
                tA = tA.next;
            }
        }else{
            pre = countB - countA;
            for(int i = 0; i < pre; i++){
                tB = tB.next;
            }
        }
        ListNode result = null;
        while(tB != null && tA != null){
            if(tB == tA){
                result = tB;
                break;
            }
            tA = tA.next;
            tB = tB.next;
        }
        return result;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode tA = headA;
        ListNode tB = headB;
        while(tA != tB){
            tA = tA == null ? headB : tA.next;
            tB = tB == null ? headA : tB.next;
        }
        return tA;
    }
}
