# 简单难度
所有试题来源于:力扣（LeetCode）
链接：https://leetcode-cn.com/
这里只是自己练习，记录笔记。

## 目录
**0001.两数之和**

**0002.两数相加**
**0007.整数反转**
**0009.回文数**

<br/><br/><br/>

## 0001.两数之和

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 **两个** 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]

### 解决方法
一遍哈希表解决

- 迭代向哈希表插入数字
- 每次插入数字检查是否有符合条件的另一个数字已再哈希表中

### 代码

```
//Time complexity   O(n)
//Space complexity  O(n)
public class Easy_001_TwoSum {
    //两数之和

    //给定一个整数数组 nums 和一个目标值 target，
    //请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
    //你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

    //Time complexity   O(n)
    //Space complexity  O(n)
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for( int i = 0; i < nums.length; i++ ){
            int num = target - nums[i];
            if( map.containsKey(num) ){
                return new int[] { map.get(num), i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("No this solution!");
    }

    public static void main(String[] args){
        int[] nums = {2, 7, 11, 15};
        int target = 17;
        for (int num:twoSum(nums, target)){
            System.out.print(num + ",");
        }
    }
}
```
## 0002.两数相加
给出两个 **非空** 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 **逆序** 的方式存储的，并且它们的每个节点只能存储 **一位** 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

### 代码
```
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
```

## 0007.整数反转
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

```
输入: 123
输出: 321
```

示例 2:

```
输入: -123
输出: -321
```
示例 3:

```
输入: 120
输出: 21
```

注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

### 解决方法
取最低位，然后放在新的数字的下一位，只是多了溢出判断

### 代码

```
	//Time complexity   O(log(x)) => O(n)
    //Space complexity  O(1)
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args){
        System.out.println(reverse(547957));
        System.out.println(reverse(-49003));
        System.out.println(reverse(1463847413));
    }
```

## 0009.回文数
判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:

```
输入: 121
输出: true
```

示例 2:

```
输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
```

示例 3:

```
输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
```

### 解决方法
取首位和结尾判断即可，首位使用一个高位计数进行判断，结尾用求余获取

### 代码

```
	//Time complexity   O(n)
    //Space complexity  O(1)
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int hight = 1;
        int tmp = x;
        while (tmp >= 10) {
            hight *= 10;
            tmp /= 10;
        }
        while (x != 0) {
            if (x % 10 != x / hight) {
                return false;
            }
            x = x % hight / 10;
            hight /= 100;
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(10));
    }
```