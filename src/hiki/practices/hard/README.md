# 困难难度
所有试题来源于:力扣（LeetCode）
链接：https://leetcode-cn.com/
这里只是自己练习，记录笔记。

## 目录
**004.寻找两个有序数组的中位数(二分查找、分治法)**  
**0010.正则表达式匹配(动态规划)**  

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

## 0010.正则表达式匹配(动态规划)

给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
示例 3:

输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
示例 4:

输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
示例 5:

输入:
s = "mississippi"
p = "mis*is*p*."
输出: false

### 解决方法
使用动态规划解决

**状态**：

dp[x, y]:字符串s中[0, x - 1]范围内的字符串能否匹配字符串p中[0, y - 1]范围内的字符串

**状态转移**：

（1）如果p.charAt(y) == '.', dp[x, y] = dp[x - 1, y - 1]。

（2）如果p.charAt(y) == s.charAt(x), dp[x, y] = dp[x - 1, y - 1]。

（3）如果p.charAt(y) == '*':
>  1.如果s.charAt(x) == p.charAt(y - 1) 或 p.charAt(y - 1) == '.'，  
>  1.1：使用'*'号进行匹配：dp[x - 1, y]  
>  1.2：只使用'*'号前面的那个字符匹配，不使用'*'匹配：dp[x, y - 1]  
>  1.3：'*'号前面的那个字符在匹配的过程当中一个都不使用：dp[x, y - 2]  
>  dp[x, y] = dp[x - 1, y] || dp[x, y - 1] || dp[x, y - 2]。  
>  2.如果s.charAt(x) != p.charAt(y - 1) 且 p.charAt(y - 1) != '.'  
>  *号前面的那个字符在匹配的过程当中一个都不使用，dp[x, y] = dp[x, y - 2]。


### 代码

```
    //Time complexity   O(n2)
    //Space complexity  O(n2)
    public static boolean isMatch(String s, String p) {
        int ns = s.length() + 1, np = p.length() + 1;
        boolean[][] dp = new boolean[ns][np];
        dp[0][0] = true;
        for (int i = 0; i < ns; i++) {
            for (int j = 1; j < np; j++) {
                if (i > 0 && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1))) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    if (i == 0 || (s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.')) {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    }
                }
            }
        }
        return dp[ns - 1][np - 1];
    }

    public static void main(String[] args){
        String s = "aac";
        String p = "a*c";
//        String s = "ab";
//        String p = ".*";
        System.out.println( isMatch(s, p) );
    }
```