class Solution {
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