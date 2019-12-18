package hiki.practices.easy;

/**
 * 268. 缺失数字
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 输入: [3,0,1]
 * 输出: 2
 */
public class Easy_0268_MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n*(n+1)/2;

        for( int num: nums){
            sum -= num;
        }
        return sum;
    }
}
