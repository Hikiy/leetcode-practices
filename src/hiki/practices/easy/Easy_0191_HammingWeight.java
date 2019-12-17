package hiki.practices.easy;

/**
 * 191. 位1的个数
 */
public class Easy_0191_HammingWeight {
    public int hammingWeight(int n) {
        int count = 0;
        int flag = 1;
        for(int i = 0; i < 32; i++){
            if ( (n & flag) != 0 ){
                count++;
            }
            flag <<= 1;
        }
        return count;
    }

    public int hammingWeight2(int n) {
        int count = 0;
        while(n != 0){
            count++;
            n &= (n-1);
        }
        return count;
    }
}