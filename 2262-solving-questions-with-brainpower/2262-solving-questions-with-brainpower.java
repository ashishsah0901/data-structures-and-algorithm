class Solution {
    public long mostPoints(int[][] questions) {
        long dp[] = new long[questions.length];
        return dfs(questions, 0, dp);
    }
    public long dfs(int[][] Q, int i, long dp[]) {
        if (i >= Q.length) return 0;
        if (dp[i] > 0) return dp[i];
        int points = Q[i][0], jump = Q[i][1];
        return dp[i] = Math.max(dfs(Q, i + 1, dp), points + dfs(Q, i + jump + 1, dp));
    }
}