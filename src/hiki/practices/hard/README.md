# 困难难度
所有试题来源于:力扣（LeetCode）
链接：https://leetcode-cn.com/
这里只是自己练习，记录笔记。

## 目录
**004.寻找两个有序数组的中位数(二分查找、分治法)**

<br/><br/><br/>

## 004.寻找两个有序数组的中位数(二分查找、分治法)

给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:
```
nums1 = [1, 3]
nums2 = [2]
```
则中位数是 2.0
示例 2:
```
nums1 = [1, 2]
nums2 = [3, 4]
```
则中位数是 (2 + 3)/2 = 2.5

### 解决方法
数组是有序的，可以使用二分法找中位数。

假设A长度为m,B长度为n。则中位数为(m+n+1)/2【奇数】或 ( (m+n+1)/2 + (m+n+1)/2 -1 )/2【偶数】

我们以数组长度小的做二分，得到下标i  
则另一个数组的下标为(m+n+1)/2 - i

中位数需要将左右两边分开  
所以 ```A[i-1] <= B[j] && B[j-1] <= A[i]```   
然后要分情况：

- 如果A[i-1] > B[j],说明A的太大，需要A以i-1为右下标往左边继续做二分法。
- 如果B[j-1] > A[i],说明A的太小，需要A以i+1为左下标往右边继续做二分法。

到边界值时进行一些处理。
### 代码

```
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
```