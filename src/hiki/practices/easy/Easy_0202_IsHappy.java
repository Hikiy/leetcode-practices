package hiki.practices.easy;

/**
 * 202. 快乐数
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
 * 也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 *
 * 输入: 19
 * 输出: true
 * 解释:
 * 1² + 9² = 82
 * 8² + 2² = 68
 * 6² + 8² = 100
 * 1² + 0² + 0² = 1
 */
public class Easy_0202_IsHappy {
    //快慢指针，当相等的时候说明进入循环，判断是否是等于1引起的循环
    public boolean isHappy(int n) {
        int fast = n;
        int slow = n;

        do{
            slow = sum(slow);
            fast = sum(fast);
            fast = sum(fast);
        }while( fast != slow );

        return slow == 1;
    }

    public int sum(int n){
        int sum = 0;
        while(n > 0){
            int bit = n%10;
            sum += bit * bit;
            n = n/10;
        }
        return sum;
    }
}
