class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0, n = nums.length;
        for(int i : nums) {
            sum += i;
        }
        if(sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        boolean dp[][] = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = false;
                } else if (nums[i-1] == j) {
                    dp[i][j] = true;
                } else {
                    boolean v1 = dp[i-1][j];
                    boolean v2 = j > nums[i-1] ? dp[i-1][j-nums[i-1]] : false;
                    dp[i][j] = v1 || v2;
                }
            }
        }
        return dp[n][sum];
    }
}