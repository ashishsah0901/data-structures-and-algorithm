class Solution {
    private int sum(int cuts[], int i, int j, int dp[][]) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            int curr = cuts[j + 1] - cuts[i - 1]
            + sum(cuts, i, k - 1, dp)
            + sum(cuts, k + 1, j, dp);
            min = Math.min(min, curr);
        }
        return dp[i][j] = min;
    }
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int c = cuts.length;
        int newCuts[] = new int[c + 2];
        newCuts[0] = 0;
        newCuts[c + 1] = n;
        int dp[][] = new int[c + 2][c + 2];
        for (int i = 1; i <= cuts.length; i++) {
            newCuts[i] = cuts[i - 1];
        }
        for (int i = c; i >= 1; i--) {
            for (int j = i; j <= c; j++) {
                // if (i > j) continue;
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int curr = newCuts[j + 1] - newCuts[i - 1]
                    + dp[i][k - 1]
                    + dp[k + 1][j];
                    min = Math.min(min, curr);
                }
                dp[i][j] = min;
            }
        }
        return dp[1][c];
    }
}