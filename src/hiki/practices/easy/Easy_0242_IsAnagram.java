package hiki.practices.easy;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 */
public class Easy_0242_IsAnagram {
    public boolean isAnagram(String s, String t) {
        if(t.length() != s.length()){
            return false;
        }
        int flag[] = new int[26];
        for( int i = 0; i < s.length(); i++){
            flag[s.charAt(i) - 'a']++;
        }

        for( int i = 0; i < t.length(); i++){
            flag[t.charAt(i) - 'a']--;
            if( flag[t.charAt(i) - 'a'] < 0){
                return false;
            }
        }

        return true;
    }
}
