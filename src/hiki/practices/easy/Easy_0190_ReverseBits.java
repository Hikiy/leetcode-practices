package hiki.practices.easy;

/**
 * 190. 颠倒二进制位
 */
public class Easy_0190_ReverseBits {
    public static int reverseBits(int n) {
        int res = 0;
        for(int i=0; i<32; i++){
            //获取最低位
            int cur = n&1;
            res = res + (cur<<(31-i));
            n = n>>1;
        }
        return res;
    }
}
