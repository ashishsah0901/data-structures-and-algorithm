class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, max = 0, ans = 0;
        int dp[] = new int[n];
        int count[] = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
            if (dp[max] == dp[i]) {
                ans += count[i];
            }
            if (dp[max] < dp[i]) {
                max = i;
                ans = count[i];
            }
        }
        return ans;
    }
}