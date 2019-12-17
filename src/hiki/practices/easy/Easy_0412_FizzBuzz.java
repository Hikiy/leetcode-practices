package hiki.practices.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Easy_0412_FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> result = new LinkedList<>();
        for( int i = 1; i <= n; i++){
            boolean the3 = (i%3 == 0);
            boolean the5 = (i%5 == 0);

            String curr = "";

            if( the3 ){
                curr += "Fizz";
            }

            if( the5 ){
                curr += "Buzz";
            }

            if( curr.equals("")){
                curr += i;
            }
            result.add(curr);
        }
        return result;
    }
}
