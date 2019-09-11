package hiki.practices.medium;

/**
 * @author ：hiki
 * 2019/9/10 20:25
 */
public class Medium_0008_StringtoInteger {
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
}
