# 中等难度
所有试题来源于:力扣（LeetCode）
链接：https://leetcode-cn.com/
这里只是自己练习，记录笔记。

## 目录
**0003.无重复字符的最长子串(滑动窗口)**  
**0005.最长回文子串(动态规划,中心扩展)**  
**0008.字符串转换整数 (atoi)**  
**0011.盛最多水的容器(双指针)**  
**0015.三数之和**  
**0016.最接近的三数之和**  
**0017.电话号码的字母组合**  
**0516.最长回文子序列(动态规划)**  

<br/><br/><br/>

## 0003.无重复字符的最长子串(滑动窗口)

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

### 解决方法
滑动窗口

当遇到重复字符时，直接将滑动窗口左边的下标跳到重复字符处

### 代码

```
//Time complexity   O(n)
    //Space complexity  O(min(m,n))
    public static int lengthOfLongestSubstring(String s) {
        //滑动窗口解决
        Map<Character, Integer> map = new HashMap<>();
        int i=0, count=0;
        for (int j = 0; j<s.length(); j++){
            if( map.containsKey(s.charAt(j)) ){
                i = Math.max(map.get(s.charAt(j)), i);
            }
            count = Math.max(count, j + 1 - i);
            map.put(s.charAt(j), j+1);
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(lengthOfLongestSubstring("abcekjgahuioplda"));
    }
```

## 0005.最长回文子串(动态规划,中心扩展)
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

```
输入: "babad"
输出: "bab"
```

注意: "aba" 也是一个有效答案。
示例 2：

```
输入: "cbbd"
输出: "bb"
```

### 解决方法
#### 动态规划

- 状态

  `f[i][j]` 表示 `s` 的第 `i` 个字符到 `j` 组成的子串是否是回文

- 转移方程

  1.如果一个字符串是回文串，那么在它左右分别加上一个相同的字符，那么它一定还是一个回文串

  2.如果一个字符串不是回文串，或者再回文串左右加不一样的字符，得到的一定不是回文串

- 初始化

  `f[i][i]` 为 `true`

> Time complexity   O(n2)
Space complexity  O(n2)

#### 中心扩展算法(推荐)
其实回文的中心有两种情况：`aba` 中心为 `b`， `abba` 中心为 `bb` 。所以长度为 `n` 的字符串有 2n-1个这样的中心。

所以我们可以这样解决：

- 遍历字符
- 判断中心为一个字符的情况，左右两边可以延长的最大长度
- 判断中心为两个字符的情况，左右两边可以延长的最大长度
- 取最大长度的情况

与动态规划方法相比，减少了数组开销，极大提高运算效率。

> Time complexity   O(n2)
Space complexity  O(1)

### 代码

```
//动态规划
    //Time complexity   O(n2)
    //Space complexity  O(n2)
    public static String longestPalindrome(String s) {
        Boolean[][] dp = new Boolean[s.length()][s.length()];
        String ans = "";
        for(int i = s.length()-1; i>=0; i--){
            for (int j = i;j<s.length();j++){
                if( j - i == 0) {
                    dp[i][j] = true;
                }else if( j - i == 1 && s.charAt(j) == s.charAt(i) ){
                    dp[i][j] = true;
                }else if( s.charAt(j) == s.charAt(i) && dp[i+1][j-1] ){
                    dp[i][j] = true;
                }else{
                    dp[i][j] = false;
                }

                if( dp[i][j] && j-i+1 > ans.length() ){
                    ans = s.substring(i,j+1);
                }
            }
        }
        return ans;
    }

    //中心扩展算法
    //Time complexity   O(n2))
    //Space complexity  O(1)
    //实测该算法比上面的动态规划要快几乎十倍。这应该是java数组存储开销问题
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args){
        String a = "babad";
        System.out.println(longestPalindrome(a));
        System.out.println(longestPalindrome2(a));
    }
```

## 0008.字符串转换整数 (atoi)

请你来实现一个 atoi 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

说明：

假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。


### 解决方法
没什么特殊方法，其实还挺简单的，就是遍历，然后一位位判断

### 代码

```
	//Time complexity   O(n)
    //Space complexity  O(1)
    public static int myAtoi(String str) {
        str = str.trim();
        int count = str.length();
        if( count == 0 ){
            return 0;
        }
        char first = str.charAt(0);
        if( !Character.isDigit(first) && first != '-' && first != '+' ){
            return 0;
        }

        int num = 0;
        for(int i=0; i< count; i++){
            char current = str.charAt(i);
            if( current == '.' ){
                return num;
            }
            if( i>=1 && !Character.isDigit(current) ){
                return num;
            }
            if( Character.isDigit(current) ){
                int currentnum = Integer.parseInt(String.valueOf(current));
                if( first == '-' ){
                    if( num < Integer.MIN_VALUE/10 || (num == Integer.MIN_VALUE/10 && currentnum > 8 ) ){
                        return Integer.MIN_VALUE;
                    }else{
                        num = num * 10;
                        num = num - currentnum;
                    }
                }else{
                    if( num > Integer.MAX_VALUE/10 || (num == Integer.MAX_VALUE/10 && currentnum > 7 ) ){
                        return Integer.MAX_VALUE;
                    }else{
                        num = num * 10;
                        num = num + currentnum;
                    }
                }
            }
        }
        return num;
    }

    public static void main(String[] args){
        String s = "  -0012a42";
        System.out.println(myAtoi(s));
    }
```

## 0011.盛最多水的容器(双指针)

给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。

图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例:

输入: [1,8,6,2,5,4,8,3,7]
输出: 49


### 解决方法
除了暴力解，可以使用双指针。

因为面积的大小其实取决于较小的那一条边，则通过一次循环，算出较大的面积，边长较短的那个指针进行位移即可

### 代码

```
	//双指针法
    //Time complexity   O(n)
    //Space complexity  O(1)
    public static int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length-1;

        while( i < j ){
            maxArea = Math.max(maxArea, Math.min(height[i], height[j])*(j-i));
            if( height[i] < height[j] ){
                i++;
            }else{
                j--;
            }
        }
        return maxArea;
    }


    public static void main(String[] args){
        int[] a= {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(a));
    }
```

## 0015.三数之和

给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

```
例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：

[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

### 解决方法
排序+双指针
将数组排序，遍历数组，选定一个数，再使用双指针。

### 代码

```
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
```

## 0016.最接近的三数之和
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

```
例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
```

### 解决方法
排序+双指针
将数组排序，遍历数组，选定一个数，再使用双指针。

### 代码

```
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
```

## 0017.电话号码的字母组合

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

示例:

```
输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```

### 解决方法
递归即可

### 代码

```
	//Time complexity   O(3^N×4^M)  其中 N 是输入数字中对应 3 个字母的数目（比方说 2，3，4，5，6，8）， M 是输入数字中对应 4 个字母的数目（比方说 7，9），N+M 是输入数字的总数。
    //Space complexity  O(3^N×4^M)
	static Map<Character, String> phone = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    static List<String> output = new ArrayList<String>();

    public static void backtrack(String combination, String next_digits){
        if( next_digits.length() == 0 ){
            output.add(combination);
        }else{
            char digit = next_digits.charAt(0);
            String letters = phone.get(digit);
            for (int i = 0; i<letters.length(); i++){
                backtrack(combination+letters.charAt(i), next_digits.substring(1));
            }
        }

    }

    public static List<String> letterCombinations(String digits) {
        if(digits.length()!=0){
            backtrack("", digits);
        }
        return output;
    }

    public static void main(String[] args){
        String a = "23";
        System.out.println(letterCombinations(a));
    }
```

## 0516.最长回文子序列(动态规划)
给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。

注意：子序列不是子串！

子串：必须连续，不能跳跃 子序列：可以不连续 如“pwwkew” pww,wwk 是子串，子序列是 pwk,pke等很多个子序列

示例 1:
输入:

"bbbab"
输出:

4
一个可能的最长回文子序列为 "bbbb"。

示例 2:
输入:

"cbbd"
输出:

2
一个可能的最长回文子序列为 "bb"。

### 解决方法
#### 动态规划

- 状态

  `f[i][j]` 表示 `s` 的第 `i` 个字符到 `j` 组成的子串最长的会问序列长度

- 转移方程

  1. `s[i] == s[j]` 则 `f[i][j] = f[i+1][j-1] + 2

  2. `s[i] != s[j]` 则 `f[i][j] = max(f[i+1][j], f[i][j-1])

- 初始化

  `f[i][i]` 为 1

> Time complexity   O(n2)
Space complexity  O(1)

```
//动态规划
    //Time complexity   O(n2)
    //Space complexity  O(n2)
    public static int longestPalindromeSubseq(String s) {
        if(s.length() < 1 ) return 0;
        int[][] dp = new int[s.length()][s.length()];
        for(int i = s.length()-1; i>=0; i--){
            for (int j = i;j<s.length();j++){
                if( j - i == 0) {
                    dp[i][j] = 1;
                }else if( s.charAt(j) == s.charAt(i) ){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][s.length()-1];
    }


    public static void main(String[] args){
        String a = "bbbab";
        System.out.println(longestPalindromeSubseq(a));
    }
```