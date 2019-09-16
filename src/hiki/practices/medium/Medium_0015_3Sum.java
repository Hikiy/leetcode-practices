package hiki.practices.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：hiki
 * 2019/9/16 9:23
 */
public class Medium_0015_3Sum {
    //三数之和

    //给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
    //
    //注意：答案中不可以包含重复的三元组。

    //Time complexity   O(n2)
    //Space complexity  O(1)
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if( nums == null || len < 3){
            return ans;
        }
        Arrays.sort(nums);
        for(int i = 0; i<len; i++){
            if( nums[i] > 0 ){
                return ans;
            }
            if( i > 0 && nums[i] == nums[i-1])continue;
            int L = i+1;
            int R = len-1;
            while( L < R ){
                int sum = nums[i] + nums[L] + nums[R];
                if( sum == 0 ){
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while(L < R && nums[L] == nums[L+1]) L++;
                    while(L < R && nums[R] == nums[R-1]) R--;
                    L++;R--;
                }
                else if(sum < 0)L++;
                else if(sum > 0)R--;
            }
        }
        return ans;
    }

    public static void main(String[] args){
//        int[] nums = {-1,0, 1, 2, -1, -4};
//        int[] nums = {0,0,0,0};
        int[] nums = {1,-1,-1,0};
        List<List<Integer>> ans = threeSum(nums);
        System.out.println(ans);
    }
}
