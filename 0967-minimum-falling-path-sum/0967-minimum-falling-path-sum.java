class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int dp[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[0][i] = matrix[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int prev = dp[i - 1][j];
                if (j > 0) {
                    prev = Math.min(dp[i - 1][j - 1], prev);
                }
                if (j < n - 1) {
                    prev = Math.min(prev, dp[i - 1][j + 1]);
                }
                dp[i][j] = Math.min(dp[i][j], matrix[i][j] + prev);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, dp[n - 1][i]);
        }
        return min;
    }
}