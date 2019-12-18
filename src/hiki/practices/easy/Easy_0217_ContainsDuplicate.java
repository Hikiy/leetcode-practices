package hiki.practices.easy;

import java.util.Arrays;

/**
 * 217. 存在重复元素
 */
public class Easy_0217_ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }
}
