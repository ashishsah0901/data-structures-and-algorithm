class Solution {
    public int[] countBits(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        if(n <= 0) return dp;
        dp[1] = 1;
        if(n <= 1) return dp;
        for(int i=2;i<=n;i++) {
            dp[i] = recurse(i,n,dp);
        }
        return dp;
    }
    int recurse(int i, int n, int[] dp) {
        if(i>n) return -1;
        if(dp[i]!=0) return dp[i];
        int ans = recurse(i>>1,n,dp) + (((i&1)==0) ? 0 : 1);
        dp[i] = ans;
        return ans;
    }
}