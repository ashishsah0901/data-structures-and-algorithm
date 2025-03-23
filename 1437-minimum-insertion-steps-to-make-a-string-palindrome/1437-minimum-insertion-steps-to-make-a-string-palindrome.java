class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        if (n == 1) {
            return 0;
        }
        int dp[][] = new int[n][n];
        for (int i = 1; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        int len = dp[0][n - 1];
        return n - len;
    }
}