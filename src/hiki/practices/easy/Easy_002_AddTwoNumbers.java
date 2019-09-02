package hiki.practices.easy;

/**
 * @author ：hiki
 * 2019/9/2 10:11
 */
public class Easy_002_AddTwoNumbers {
    //两数相加

    //给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
    // 并且它们的每个节点只能存储 一位 数字。
    //
    //如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    //
    //您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    //
    //示例：
    //
    //输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    //输出：7 -> 0 -> 8
    //原因：342 + 465 = 807

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    //Time complexity   O(max(m,n))，假设 m 和 n 分别表示 l1 和 l2 的长度，算法最多重复 max(m,n) 次。
    //Space complexity  O(max(m,n))，新列表的长度最多为 max(m,n)+1。
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumNode = new ListNode(0);
        ListNode p = l1; ListNode q = l2;
        ListNode curr = sumNode;
        int carry = 0;
        while( p != null || q != null ){
            int x = (p == null)? 0:p.val;
            int y = (q == null)? 0:q.val;
            int sum = x + y + carry;
            carry = sum/10;
            curr.next = new ListNode(sum%10);
            curr = curr.next;
            if( p != null ){
                p = p.next;
            }
            if( q != null ){
                q = q.next;
            }
        }

        if( carry > 0 ){
            curr.next = new ListNode(carry);
        }
        return sumNode.next;
    }

    public static void main(String[] args){
        ListNode x = new ListNode(2);
        x.next = new ListNode(4);
        x.next.next = new ListNode(3);

        ListNode y = new ListNode(5);
        y.next = new ListNode(6);
        y.next.next = new ListNode(4);

        ListNode sum = addTwoNumbers(x, y);
        ListNode curr = sum;
        while ( curr != null ){
            System.out.print(curr.val);
            curr = curr.next;
            if( curr != null ){
                System.out.print(" -> ");
            }
        }
    }
}
