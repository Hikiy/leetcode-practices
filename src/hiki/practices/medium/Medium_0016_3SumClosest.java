package hiki.practices.medium;

import java.util.Arrays;

/**
 * @author ：hiki
 * 2019/9/18 9:32
 */
public class Medium_0016_3SumClosest {
    //最接近的三数之和

    //给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
    // 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

    //Time complexity   O(n2)
    //Space complexity  O(1)
    public static int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        int ans= nums[0]+nums[1]+nums[2];
        Arrays.sort(nums);
        for(int i = 0; i<len; i++){
            int L = i+1;
            int R = len-1;
            while( L < R ){
                int sum = nums[i] + nums[L] + nums[R];
                if( Math.abs(target-sum) < Math.abs(target- ans) ){
                    ans = sum;
                }
                if(sum < target)L++;
                else if(sum > target)R--;
                else return ans;
            }
        }
        return ans;
    }

    public static void main(String[] args){
//        int[] a = {-1, 2, 1, -4};
        int[] a = {1, 2, 4, 8, 16, 32, 64, 128};
        System.out.println(threeSumClosest(a, 82));
    }
}
