package hiki.practices.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 136. 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现【一次】以外，其余每个元素均出现【两次】。找出那个只出现了一次的元素。
*/
public class Easy_0136_SingleNumber {
    //异或法（相同的数字二进制最终总会抵消【前提重复的数字为2次】，留下的只有唯一的）
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
        }
        return ans;
    }

    //哈希法
    public int singleNumber2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : nums) {
            Integer count = map.get(i);
            count = count == null ? 1 : ++count;
            map.put(i, count);
        }
        for (Integer i : map.keySet()) {
            Integer count = map.get(i);
            if (count == 1) {
                return i;
            }
        }
        return -1;
    }
}
