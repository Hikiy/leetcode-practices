package hiki.practices.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：hiki
 * 2019/8/30 20:31
 */
public class Easy_0001_TwoSum {
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
