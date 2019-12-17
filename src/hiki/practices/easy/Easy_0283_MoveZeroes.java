package hiki.practices.easy;

/**
 * 283. 移动零
 */
public class Easy_0283_MoveZeroes {
    //其实就是双指针一样的存在
    public void moveZeroes(int[] nums) {
        int index = 0;

        for(int num : nums){
            if( num != 0 ){
                nums[index++] = num;
            }
        }

        while( index < nums.length ){
            nums[index++] = 0;
        }
    }
}
