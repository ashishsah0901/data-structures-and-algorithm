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
    private int cuts(String s, int i, int n, int dp[]) {
        if (i == n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k < n; k++) {
            if (isPal(s, i, k)) {
                int curr = cuts(s, k + 1, n, dp) + 1;
                min = Math.min(min, curr);
            }
        }
        return dp[i] = min;
    }
    public int minCut(String s) {
        int n = s.length();
        int dp[] = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int k = i; k < n; k++) {
                if (isPal(s, i, k)) {
                    int curr = dp[k + 1] + 1;
                    min = Math.min(min, curr);
                }
            }
            dp[i] = min;
        }
        return dp[0] - 1;
    }
}