package hiki.practices.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：hiki
 * 2019/9/17 9:30
 */
public class Medium_0017_CombinationsOfAPhoneNumber {
    //电话号码的字母组合

    //递归
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
}
