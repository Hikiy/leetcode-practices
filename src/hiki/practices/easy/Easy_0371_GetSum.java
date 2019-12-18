package hiki.practices.easy;

/**
 * 371. 两整数之和
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 */
public class Easy_0371_GetSum {
    public int getSum(int a, int b) {
        while( b != 0 ){
            //与运算后左移获得进位
            int res = (a&b) << 1;
            //异或进行加法运算
            a ^= b;
            b = res;
        }
        return a;
    }
}
