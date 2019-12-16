package hiki.practices.easy;
/**
 * 171. Excel表列序号
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
*/
public class Easy_0171_TitleToNumber {
    public int titleToNumber(String s) {
        int result = 0;
        for(int i = 0; i< s.length(); i++){
            int num = s.charAt(i) - 'A' + 1;
            result = result * 26 + num;
        }
        return result;
    }
}
