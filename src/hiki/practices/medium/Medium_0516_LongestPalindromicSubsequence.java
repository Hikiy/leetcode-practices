package hiki.practices.medium;

/**
 * @author ：hiki
 * 2019/8/30 20:31
 */
public class Medium_0516_LongestPalindromicSubsequence {
    //给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
    //注意是回文子序列，子序列不不是子串
    //子串：必须连续，不能跳跃 子序列：可以不连续 如“pwwkew” pww,wwk 是子串，子序列是 pwk,pke等很多个子序列
    //示例 1:
    //输入:
    //
    //"bbbab"
    //输出:
    //
    //4
    //一个可能的最长回文子序列为 "bbbb"。
    //
    //示例 2:
    //输入:
    //
    //"cbbd"
    //输出:
    //
    //2
    //一个可能的最长回文子序列为 "bb"。


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
}
