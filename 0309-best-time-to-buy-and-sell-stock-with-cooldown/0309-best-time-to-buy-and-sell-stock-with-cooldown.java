class Solution {
    private int count(int idx, int prices[], int buy, int dp[][]) {
        if (idx >= prices.length) {
            return 0;
        }
        if (dp[idx][buy] != 0) {
            return dp[idx][buy];
        }
        if (buy == 1) {
            return dp[idx][buy] = Math.max(count(idx + 1, prices, buy, dp), -prices[idx] + count(idx + 1, prices, 0, dp));
        } else {
            return dp[idx][buy] = Math.max(count(idx + 1, prices, buy, dp), prices[idx] + count(idx + 2, prices, 1, dp));
        }
    }
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n][2];
        return count(0, prices, 1, dp);
    }
}