package hiki.practices.easy;

/**
 * 38. 报数
 */
public class Easy_0038_CountAndSay {
    public String countAndSay(int n) {
        String result = "1";
        for( int i = 2; i <= n; i++){
            StringBuilder temp = new StringBuilder();
            char flag = result.charAt(0);
            int count = 1;
            for( int j = 1; j < result.length(); j++){
                if( flag == result.charAt(j) ){
                    count ++;
                }else{
                    temp.append(count);
                    temp.append(flag);
                    flag = result.charAt(j);
                    count = 1;
                }
            }
            temp.append(count);
            temp.append(flag);
            result = temp.toString();
        }
        return result;
    }
}
