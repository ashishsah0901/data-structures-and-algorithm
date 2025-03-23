class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int dp[] = new int[amount + 1];
        dp[0] = 1;
        for (int coin: coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
    private int count(int amount, int[] coins, int idx, int dp[][]) {
        if (amount < 0) {
            return 0;
        }
        if (amount == 0) {
            return 1;
        }
        if (idx >= coins.length) {
            return 0;
        }
        if (dp[amount][idx] != -1) {
            return dp[amount][idx];
        }
        int sum = 0;
        sum += count(amount, coins, idx + 1, dp);
        sum += count(amount - coins[idx], coins, idx, dp);
        return dp[amount][idx] = sum;
    }
}