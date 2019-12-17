package hiki.practices.easy;

import java.util.Arrays;

public class Easy_0169_MajorityElement {
    //排序法 nlogn
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length>>1];
    }

    //摩尔投票法 n
    public int majorityElement2(int[] nums) {
        int countFlag=0;
        int result = 0;
        for(int i=0;i<nums.length;i++)
        {
            //初次默认第一个。后续每次判断当countFlag为0，意味着之前元素抵消完成。
            if(countFlag==0){
                //赋值新元素作为即将可能要返回的值
                result=nums[i];
            }
            // 即将可能返回的值给当前比，相等+1，不相等-1，累计为0重新赋值新坐标元素。
            countFlag+=(result==nums[i])?1:-1;
        }
        return result;
    }
}
