package hiki.practices.easy;

/**
 * 122. 买卖股票的最佳时机 II
 */
public class Easy_0122_MaxProfit {
    //优化版本 O(n)
    public int maxProfit2(int[] prices) {
        if( prices.length < 2 ){
            return 0;
        }
        int money = 0;

        for( int i = 1; i < prices.length; i++){
            if( prices[i] > prices[i-1] ){
                money += prices[i] - prices[i-1];
            }
        }
        return money;
    }

    //O(n)
    public int maxProfit(int[] prices) {
        if( prices.length < 2 ){
            return 0;
        }
        int money = 0;
        int i = 0;
        int j = 0;
        int max = 0;

        while( j+1 < prices.length ){
            if( prices[i] >= prices[j+1] || prices[j+1] - prices[i] < max){
                i = j+1;
                money += max;
                max = 0;
            }else{
                max = prices[j+1] - prices[i];
            }
            j++;
        }
        return money + max;
    }
}
