package hiki.practices.medium;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @author ：hiki
 * 2019/8/30 20:31
 */
public class Medium_0005_LongestPalindromicSubstring {
    //最长回文子串
    //给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    //
    //示例 1：
    //
    //输入: "babad"
    //输出: "bab"
    //注意: "aba" 也是一个有效答案。
    //示例 2：
    //
    //输入: "cbbd"
    //输出: "bb"


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
}
