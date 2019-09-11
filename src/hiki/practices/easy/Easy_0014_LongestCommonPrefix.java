package hiki.practices.easy;

/**
 * @author ：hiki
 * 2019/9/10 17:48
 */
public class Easy_0014_LongestCommonPrefix {

    //水平扫描
    //Time complexity   O(n) n为所有字符串中字符数量的总和
    //Space complexity  O(1)
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public static void main(String[] args){
        String[] a= {"dog", "doc", "dhat"};

        System.out.println(longestCommonPrefix(a));
    }
}
