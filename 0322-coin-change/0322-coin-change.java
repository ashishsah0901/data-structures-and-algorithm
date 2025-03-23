class Solution {
    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        Arrays.fill(dp, -1);
        int ans = count(coins, amount, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    private int count(int coins[], int amount, int dp[]) {
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        if (amount == 0) {
            return 0;
        }
        if (dp[amount] != -1) {
            return dp[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int coin: coins) {
            int c1 = count(coins, amount - coin, dp);
            if (c1 != Integer.MAX_VALUE) {
                min = Math.min(min, c1 + 1);
            }
        }
        return dp[amount] = min;
    }
}