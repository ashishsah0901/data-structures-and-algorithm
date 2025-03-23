class Solution {
    public boolean isMatch(String t, String s) {
        int n = s.length(), m = t.length();
        boolean dp[][] = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[0][i] = false;
        }
        for (int i = 1; i <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < i; j++) {
                if (s.charAt(j) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[i][0] = flag;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1) || s.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (s.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[n][m];
    }
}