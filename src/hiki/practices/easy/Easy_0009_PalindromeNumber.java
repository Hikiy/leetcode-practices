package hiki.practices.easy;

/**
 * @author ：hiki
 * 2019/9/4 10:09
 */
public class Easy_0009_PalindromeNumber {
    //回文数

    //判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
    //
    //示例 1:
    //
    //输入: 121
    //输出: true
    //示例 2:
    //
    //输入: -121
    //输出: false
    //解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
    //示例 3:
    //
    //输入: 10
    //输出: false
    //解释: 从右向左读, 为 01 。因此它不是一个回文数。

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
}
