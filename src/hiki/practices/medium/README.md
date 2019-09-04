# 中等难度
所有试题来源于:力扣（LeetCode）
链接：https://leetcode-cn.com/
这里只是自己练习，记录笔记。

## 目录
**003.无重复字符的最长子串(滑动窗口)**
**005.最长回文子串(动态规划,中心扩展)**
**516.最长回文子序列(动态规划)**

<br/><br/><br/>

## 003.无重复字符的最长子串(滑动窗口)

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

## 005.最长回文子串(动态规划,中心扩展)
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


## 516.最长回文子序列(动态规划)
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