package hiki.practices.hard;

/**
 * @author ：hiki
 * 2019/8/30 20:31
 */
public class Hard_0004_MedianOfTwoSortedArrays {
    //寻找两个有序数组的中位数

    //给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
    //
    //请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
    //
    //你可以假设 nums1 和 nums2 不会同时为空。
    //
    //示例 1:
    //
    //nums1 = [1, 3]
    //nums2 = [2]
    //
    //则中位数是 2.0
    //示例 2:
    //
    //nums1 = [1, 2]
    //nums2 = [3, 4]
    //
    //则中位数是 (2 + 3)/2 = 2.5


    //Time complexity   O(min(m,n))
    //Space complexity  O(1)
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int lo = 0;
        int hi = m;
        while (lo <= hi) {
            // partition A position i
            int i = lo + (hi - lo) / 2;
            // partition B position j
            int j = (m + n + 1) / 2 - i;

            int maxLeftA = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int minRightA = i == m ? Integer.MAX_VALUE : nums1[i];

            int maxLeftB = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int minRightB = j == n ? Integer.MAX_VALUE : nums2[j];

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                // total length is even
                if ((m + n) % 2 == 0) {
                    return (double) (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2;
                } else {
                    // total length is odd
                    return (double) Math.max(maxLeftA, maxLeftB);
                }
            } else if (maxLeftA > minRightB) {
                // binary search left half
                hi = i - 1;
            } else {
                // binary search right half
                lo = i + 1;
            }
        }
        return 0.0;
    }

    public static void main(String[] args){
        int[] num1 = {2};
        int[] num2 = {1,3};
        System.out.println( findMedianSortedArrays(num1, num2) );
    }
}
