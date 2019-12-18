package hiki.practices.easy;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 */
public class Easy_0121_MaxProfit {
    public int maxProfit(int[] prices){
        if( prices.length < 2 ){
            return 0;
        }
        int i = 0;
        int j = 0;

        int earn;
        int max = 0;

        while( j+1 < prices.length ){
            if( prices[i] >= prices[j+1] || prices[j+1] < prices[i] ){
                i = j+1;
            }
            earn = prices[j+1] - prices[i];
            max = Math.max(max, earn);
            j++;
        }
        return max;
    }

    //原理和上面的一样，但是这个是计算最低的地方到当前位置的利润
    public int maxProfit2(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}
