class Solution {
    private boolean isPal(String s, int i, int j){
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    private int cuts(String s, int i, int j, int dp[][]) {
        if (i >= j) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        if (isPal(s, i, j)) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            if (isPal(s, i, k)) {
                int curr = cuts(s, k + 1, j, dp) + 1;
                min = Math.min(min, curr);
            }
        }
        return dp[i][j] = min;
    }
    public int minCut(String s) {
        int n = s.length();
        int dp[][] = new int[n][n];
        return cuts(s, 0, n - 1, dp);
    }
}