class Solution {
    private int solve(int nums[], int i, int j, int dp[][]) {
        if (j < i) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int min = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            int curr = nums[k] * nums[i - 1] * nums[j + 1]
            + solve(nums, i, k - 1, dp)
            + solve(nums, k + 1, j, dp);
            min = Math.max(min, curr);
        }
        return dp[i][j] = min;
    }
    public int maxCoins(int[] nums) {
        int n = nums.length + 2;
        int nNums[] = new int[n];
        nNums[0] = 1;
        nNums[n - 1] = 1;
        for (int i = 0; i < n - 2; i++) {
            nNums[i + 1] = nums[i];
        }
        int dp[][] = new int[n][n];
        return solve(nNums, 1, n - 2, dp);
    }
}