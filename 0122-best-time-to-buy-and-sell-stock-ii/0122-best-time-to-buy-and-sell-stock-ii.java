class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0, n = prices.length;
        for (int i = 1; i < n; i++) {
            int prev = prices[i - 1];
            int curr = prices[i];
            if (curr - prev > 0) {
                ans += (curr - prev);
            }
        }
        return ans;
    }
}

/*
[7,6,6,6,6,4]

[]

O(n)

O(n)
*/