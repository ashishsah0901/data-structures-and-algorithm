class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp[][] = new int[n+1][5];
        for (int i = n - 1; i >= 0; i--) {
            for (int tran = 1; tran <= 4; tran++) {
                int ans = 0;
                if (tran % 2 == 1) {
                    int ans1 = prices[i] + dp[i + 1][tran - 1];
                    int ans2 = dp[i + 1][tran];
                    ans += Math.max(ans1, ans2);
                } else {
                    int ans1 = -prices[i] + dp[i + 1][tran - 1];
                    int ans2 = dp[i + 1][tran];
                    ans += Math.max(ans1, ans2);
                }
                dp[i][tran] = ans;
            }
        }
        return dp[0][4];
    }
}